package pl.bolka.aleksander.schedule.planner.util;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.bolka.aleksander.schedule.planner.model.entity.Day;
import pl.bolka.aleksander.schedule.planner.model.entity.FreeRoom;
import pl.bolka.aleksander.schedule.planner.model.entity.Hour;
import pl.bolka.aleksander.schedule.planner.model.entity.Room;
import pl.bolka.aleksander.schedule.planner.model.entity.Schedule;
import pl.bolka.aleksander.schedule.planner.model.entity.ScheduleInterface;
import pl.bolka.aleksander.schedule.planner.model.entity.StudentGroup;
import pl.bolka.aleksander.schedule.planner.model.entity.Subject;

@Repository
public class Database {

	@Autowired
	private EntityManager entityManager;

	public Database() {

	}

//	public EntityManager getEntityManager() {
//		return entityManager;
//	}
//
//	public void setEntityManager(EntityManager entityManager) {
//		this.entityManager = entityManager;
//	}

	@Transactional("jpaTransactionManager")
	public ObservableList<StudentGroup> getFreeGrupa() {
		ObservableList<StudentGroup> grupy = FXCollections.observableArrayList();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StudentGroup> criteriaQuery = criteriaBuilder.createQuery(StudentGroup.class);
		
		Root<StudentGroup> group = criteriaQuery.from(StudentGroup.class);
		criteriaQuery.select(group);
		
		TypedQuery<StudentGroup> query = entityManager.createQuery(criteriaQuery);
		
		List<StudentGroup> groups = query.getResultList();
		grupy.addAll(groups);
		return grupy;
	}

	public ObservableList<Subject> getPrzedmiotByGrupa(StudentGroup group) {
		ObservableList<Subject> subjectOL = FXCollections.observableArrayList();
		if (group == null) {
			return subjectOL;
		}
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Subject> criteriaQuery = criteriaBuilder.createQuery(Subject.class);
		Root<Subject> subject = criteriaQuery.from(Subject.class);
		criteriaQuery.select(subject);
		criteriaQuery.where(criteriaBuilder.equal(subject.get("group").get("id"), group.getId()));
		// criteriaQuery.where()
		TypedQuery<Subject> query = entityManager.createQuery(criteriaQuery);
		List<Subject> list = query.getResultList();
		subjectOL.addAll(list);
		return subjectOL;
	}

	private FreeRoom getWolneSale(Room room) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<FreeRoom> criteriaQuery = criteriaBuilder.createQuery(FreeRoom.class);
		Root<FreeRoom> rooms = criteriaQuery.from(FreeRoom.class);
		Path<Room> path = (Path) rooms.get("room");
		criteriaQuery.select(rooms).where(criteriaBuilder.equal(path, room));
		TypedQuery<FreeRoom> query = entityManager.createQuery(criteriaQuery);
		FreeRoom wolneSales = query.getSingleResult();
		return wolneSales;
	}

	private List<FreeRoom> getFreeRoomsBySpace(int space) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<FreeRoom> criteriaQuery = criteriaBuilder.createQuery(FreeRoom.class);
		Root<FreeRoom> sale = criteriaQuery.from(FreeRoom.class);
		Path<Integer> freeSpace = (Path) sale.get("roomSpace");
		criteriaQuery.select(sale).where(criteriaBuilder.greaterThanOrEqualTo(freeSpace, space));
		TypedQuery<FreeRoom> query = entityManager.createQuery(criteriaQuery);
		List<FreeRoom> freeRooms = query.getResultList();
		return freeRooms;
	}

	public boolean isEmpty() {
		TypedQuery<StudentGroup> query = entityManager.createQuery("Select d from StudentGroup d", StudentGroup.class);
		List<StudentGroup> list = query.getResultList();
		return list.isEmpty();
	}

	public ObservableList<FreeRoom> getFreeRooms(int space) {
		ObservableList<FreeRoom> freeRoomses = FXCollections.observableArrayList();
		List<FreeRoom> roomses = getFreeRoomsBySpace(space);
		freeRoomses.addAll(roomses);
		return freeRoomses;
	}

	public ObservableList<Day> getDays(FreeRoom room) {
		ObservableList<Day> days = FXCollections.observableArrayList();
		if (room == null) {
			return days;
		}

		Collection<Day> collection = room.getDay();
		days.addAll(collection);
		return days;
	}

	public ObservableList<Hour> getHours(Day day) {
		ObservableList<Hour> hours = FXCollections.observableArrayList();
		if (day == null) {
			return hours;
		}
		Collection<Hour> collection = day.getHour();
		hours.addAll(collection);
		return hours;
	}

	public ScheduleInterface addTemporarySchedule(ScheduleInterface scheduleInterface) {
		FreeRoom freeRoom = removeUsedEntity(scheduleInterface);
		persist(freeRoom);
		return merge(scheduleInterface);

	}

	private FreeRoom removeUsedEntity(ScheduleInterface scheduleInterface) {
		StudentGroup group = scheduleInterface.getGroup();
		Subject subject = scheduleInterface.getSubject();
		removeSubjectFromGroup(group, subject);
		FreeRoom freeRoom = scheduleInterface.getFreeRoom();
		Hour hour = scheduleInterface.getHour();
		Day day = scheduleInterface.getDay();
		return removeHourFromFreeRoom(freeRoom, hour, day);

	}

	private void removeSubjectFromGroup(StudentGroup group, Subject subject) {
//		List<Subject> subjects = group.getSubject();
//		subjects.remove(subject);
//		group.setSubject(subjects);
	}

	private FreeRoom removeHourFromFreeRoom(FreeRoom freeRoom, Hour hour, Day day) {
		Set<Day> days = freeRoom.getDay();
		if (!days.isEmpty()) {
			removeAppropriateHourFromDay(days, day, hour);
		}
		return freeRoom;
	}

	private void removeAppropriateHourFromDay(Set<Day> days, Day day, Hour hour) {
		for (Day day1 : days) {
			if (day1.equals(day)) {
				removeHourFromDay(day1, hour);
			}
		}
	}

	private void removeHourFromDay(Day day, Hour hour) {
		List<Hour> hours = day.getHour();
		hours.remove(hour);
	}

	public void saveSchudele(List<ScheduleInterface> schedules) {
		for (ScheduleInterface schedule : schedules) {
			merge(schedule);
		}
	}

	public List<Schedule> getSchedule() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Schedule> criteriaQuery = criteriaBuilder.createQuery(Schedule.class);
		Root<Schedule> root = criteriaQuery.from(Schedule.class);
		criteriaQuery.select(root);
		TypedQuery<Schedule> typedQuery = entityManager.createQuery(criteriaQuery);
		List<Schedule> schedules = typedQuery.getResultList();
		return schedules;
	}

	public void persist(Object object) {
		entityManager.persist(object);
		// entityManager.getTransaction().begin();
		// try {
		// entityManager.persist(object);
		// entityManager.getTransaction().commit();
		// } catch (Exception e) {
		// e.printStackTrace();
		// entityManager.getTransaction().rollback();
		// }
	}

	public <T> T merge(T entity) {
		T resultEntity = null;
		// entityManager.getTransaction().begin();

		resultEntity = entityManager.merge(entity);
		// try {
		// resultEntity = entityManager.merge(entity);
		// entityManager.getTransaction().commit();
		// } catch (Exception e) {
		// e.printStackTrace();
		// entityManager.getTransaction().rollback();
		// }
		return resultEntity;
	}

	

}
