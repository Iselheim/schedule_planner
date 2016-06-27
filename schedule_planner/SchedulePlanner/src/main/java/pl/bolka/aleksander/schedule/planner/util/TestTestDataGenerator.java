package pl.bolka.aleksander.schedule.planner.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.bolka.aleksander.schedule.planner.model.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.*;

@Repository
public class TestTestDataGenerator {

//    @Autowired
    EntityManager em;

    @Transactional
    public void generate() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Week> query = cb.createQuery(Week.class);
        Root<Week> root = query.from(Week.class);

        TypedQuery<Week> typedQuery = em.createQuery(query);
        List<Week> weeksList = typedQuery.getResultList();
        Set<Week> weeks = new HashSet<>(weeksList);


        Semester semester = new Semester();
        semester.setNumber(2);
        semester.setYear(1);

        semester.setWeek(weeks);


        Faculty faculty = new Faculty();
        faculty.setName("Wydział Telekomunikacji, Informatyki i Elektrotechniki");
        faculty.setShortcut("WTIiE");


        Specialization specialization = new Specialization();
        specialization.setName("Teleinformatyka");
        specialization.setShortcut("TIN");
        specialization.setType(StudiesType.DZIENNE);
        specialization.setGrade(Grade.INZYNIER);
        specialization.setSemesterCount(7);

        specialization.setFaculty(faculty);

        List<Specialization> specializations = new ArrayList<>();
        specializations.add(specialization);

        faculty.setSpecialization(specializations);

        List<Subject> subjects = new ArrayList<>();

        Subject matematykaCw = new Subject();
        matematykaCw.setName("Matematyka");
        matematykaCw.setFaculty(faculty);
        matematykaCw.setHours(15);
        matematykaCw.setSubjectType(SubjectType.CWICZENIA);
        matematykaCw.setSemester(semester);



        List<Lecturer> lecturers = new ArrayList<>();

        Lecturer lecturer = new Lecturer();
//        lecturer.setFaculty(faculty);
        lecturer.setFirstName("Katarzyna");
        lecturer.setLastName("Borkowska");
        List<Subject> subjectsForBorkowska = new ArrayList<>();
        subjectsForBorkowska.add(matematykaCw);
        lecturer.setSubject(subjectsForBorkowska);
        lecturer.setUniversityDegree("Dr");
        lecturers.add(lecturer);
        matematykaCw.setLecturer(lecturers);



        List<Room> rooms = new ArrayList<>();
        Room room = new Room();
        room.setNumber("2AN");
        room.setWeek(weeks);
        room.setRoomSpace(200);
        rooms.add(room);

        matematykaCw.setRoom(rooms);

        subjects.add(matematykaCw);

        semester.setSubject(subjects);


        faculty.setSubjects(subjects);

        List<StudentGroup> studentGroups = new ArrayList<>();
        for( int i =0; i < 6; i++){
            StudentGroup studentGroup = new StudentGroup();
            studentGroup.setNumber(i+1);
            studentGroup.setSemester(semester);
            studentGroup.setQuantity(20);
            studentGroup.setSpecialization(specialization);
            studentGroups.add(studentGroup);
        }

        specialization.setGroup(studentGroups);
        semester.setGroup(studentGroups);

    List<Object> list = new ArrayList<>();
        list.add(semester);
        list.add(faculty);
        list.add(specialization);
        list.add(matematykaCw);
        list.add(lecturer);
        list.add(room);
        list.addAll(studentGroups);

        for (Object o : list){
            em.persist(o);

        }

    }


    @Transactional("jpaTransactionManager")
    public void testWeeks() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Day> query = cb.createQuery(Day.class);
        Root<Day> root = query.from(Day.class);
        query.orderBy(cb.asc(root.<Date>get("date")));
//		root.select(Hour.class);

        TypedQuery<Day> typedQuery = em.createQuery(query);
        List<Day> days = typedQuery.getResultList();

        List<Week> weeks = new ArrayList<Week>();

        int i = 1;
        Set<Day> daysSet = new LinkedHashSet<>();
        for (Day day : days) {
            daysSet.add(day);
            if (i % 7 == 0) {
                Week week = new Week();
                week.setWeekOfSemester(i / 7);
                week.setDays(daysSet);
                weeks.add(week);
                daysSet = new LinkedHashSet<Day>();
            }
            i++;
        }
        for (Week week : weeks) {
            em.persist(week);
        }

    }

    private Date nextDay(Date sqlDate) {
        LocalDate date = new LocalDate(sqlDate.getTime(), DateTimeZone.UTC);
        LocalDate tomorrow = date.plusDays(1);

        DateTime startOfDay = tomorrow.toDateTimeAtStartOfDay(DateTimeZone.UTC);

        java.sql.Date sqlTomorrow = new java.sql.Date(startOfDay.getMillis());
        return sqlTomorrow;
    }

}
