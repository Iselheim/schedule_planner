package pl.bolka.aleksander.schedule.planner.fx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import pl.bolka.aleksander.schedule.planner.config.ScreensConfig;
import pl.bolka.aleksander.schedule.planner.model.entity.*;

import java.util.ArrayList;
import java.util.List;

public class ManualSelectByGroupsController extends FXController {

	public ManualSelectByGroupsController(ScreensConfig flow) {
		super(flow);
	}

	private static final String PATH = "/pl/bolka/aleksander/schedule/planner/fx/fxml/ManualSelectByGroups.fxml";
	
//	@Autowired
//	private Database database;

	@FXML
	private TableView<Day> DayTable;

	@FXML
	private TableView<Lecturer> LecturerTable;

	@FXML
	private TableView<ScheduleInterface> ScheduleTable;

	@FXML
	private TableView<Hour> HourTable;

	@FXML
	private TableView<Subject> SubjectTable;

	@FXML
	private TableView<StudentGroup> GroupTable;

	@FXML
	private TableView<FreeRoom> FreeRoomsTable;

	@FXML
	private Button Add;

	@FXML
	private Button Save;

	private List<ScheduleInterface> schedules = new ArrayList<>();
	private ObservableList<ScheduleInterface> scheduleList = FXCollections.observableArrayList();

	@FXML
	public void initialize() {

//		Add.setDisable(true);
//
//		setGroup();
//		setScheduleFromDatabase();
//
//		GroupTable.getSelectionModel().selectedItemProperty().addListener((Observable obs) -> {
//			SubjectTable.getColumns().clear();
//			LecturerTable.getColumns().clear();
//			DayTable.getColumns().clear();
//			HourTable.getColumns().clear();
//			FreeRoomsTable.getColumns().clear();
//			if (GroupTable.getSelectionModel().getSelectedItem() != null) {
//				StudentGroup grupa = GroupTable.getSelectionModel().getSelectedItem();
//				setSubjects(grupa);
//			}
//		});
//
//		SubjectTable.getSelectionModel().selectedItemProperty().addListener((Observable obs) -> {
//			LecturerTable.getColumns().clear();
//			DayTable.getColumns().clear();
//			HourTable.getColumns().clear();
//			FreeRoomsTable.getColumns().clear();
//			Subject przedmiot;
//			if (SubjectTable.getSelectionModel().getSelectedItem() != null) {
//				try {
//					przedmiot = SubjectTable.getSelectionModel().getSelectedItem();
//					setLecturers(przedmiot);
//				} catch (Throwable ex) {
//					Logger.getLogger(ManualSelectByGroupsController.class.getName()).log(Level.SEVERE, null, ex);
//				}
//
//			}
//
//		});
//		LecturerTable.getSelectionModel().selectedItemProperty().addListener((Observable obs) -> {
//			DayTable.getColumns().clear();
//			HourTable.getColumns().clear();
//			FreeRoomsTable.getColumns().clear();
//			if (GroupTable.getSelectionModel().getSelectedItem() != null) {
//				StudentGroup grupa = GroupTable.getSelectionModel().getSelectedItem();
//				int space = grupa.getQuantity();
//				setFreeRooms(space);
//
//			}
//		});
//
//		FreeRoomsTable.getSelectionModel().selectedItemProperty().addListener((Observable obs) -> {
//			DayTable.getColumns().clear();
//			HourTable.getColumns().clear();
//			if (FreeRoomsTable.getSelectionModel().getSelectedItem() != null) {
//				FreeRoom sala = FreeRoomsTable.getSelectionModel().getSelectedItem();
//				setDays(sala);
//
//			}
//		});
//
//		DayTable.getSelectionModel().selectedItemProperty().addListener((Observable obs) -> {
//			HourTable.getColumns().clear();
//			if (DayTable.getSelectionModel().getSelectedItem() != null) {
//				Day dzien = DayTable.getSelectionModel().getSelectedItem();
//				setHours(dzien);
//			}
//		});
//
//		HourTable.getSelectionModel().selectedItemProperty().addListener((Observable obs) -> {
//			if (HourTable.getSelectionModel().getSelectedItem() != null) {
//				Add.setDisable(false);
//			} else {
//				Add.setDisable(true);
//			}
//		});
//
//		Add.setOnAction((ActionEvent t) -> {
//			StudentGroup studentGroup = GroupTable.getSelectionModel().getSelectedItem();
//			Subject subject = SubjectTable.getSelectionModel().getSelectedItem();
//			Lecturer lecturer = LecturerTable.getSelectionModel().getSelectedItem();
//			FreeRoom room = FreeRoomsTable.getSelectionModel().getSelectedItem();
//			Day day = DayTable.getSelectionModel().getSelectedItem();
//			Hour hour = HourTable.getSelectionModel().getSelectedItem();
//			BusyRoom busyRoom = new BusyRoom(room);
//			ScheduleInterface schedule = new Schedule(subject, lecturer, room, day, hour, studentGroup);
//
//			database.merge(busyRoom);
//			ScheduleInterface temporarySchedule = new TemporarySchedule(schedule);
//			database.addTemporarySchedule(temporarySchedule);
//			schedules.add(schedule);
//
//			setSchedule(temporarySchedule);
//		});
//
//		Save.setOnAction((ActionEvent t) -> {
//			database.saveSchudele(schedules);
//		});

	}

//	private void setGroup() {
//		TableColumn<StudentGroup, String> column = new TableColumn<>("Grupa");
//		column.setPrefWidth(GroupTable.getPrefWidth());
//		GroupTable.getColumns().clear();
//		if (GroupTable.getColumns().add(column)) {
//			setGroupTableColumnCellFactory(column);
//		}
//	}
//
//	private void setGroupTableColumnCellFactory(TableColumn<StudentGroup, String> column) {
//		column.setCellValueFactory(new PropertyValueFactory<>("text"));
//		ObservableList<StudentGroup> group = getObservableListForGroupTable();
//		GroupTable.setItems(group);
//	}
//
//	private ObservableList<StudentGroup> getObservableListForGroupTable() {
//		ObservableList<StudentGroup> group = database.getFreeGrupa();
//		return getGroupsWithFreeSubjects(group);
//	}
//
//	private ObservableList<StudentGroup> getGroupsWithFreeSubjects(ObservableList<StudentGroup> group) {
//		ObservableList<StudentGroup> groups = FXCollections.observableArrayList();
//		for (StudentGroup studentGroup : group) {
////			Hibernate.initialize(studentGroup.getSubject());
////			if (!studentGroup.getSubject().isEmpty()) {
////				groups.add(studentGroup);
////			}
//		}
//		return groups;
//	}
//	
//	@Transactional
//	private void setSubjects(StudentGroup group) {
//		TableColumn<Subject, String> column = new TableColumn<>("Przedmiot");
//		column.setPrefWidth(SubjectTable.getPrefWidth());
//		SubjectTable.getColumns().clear();
//		if (SubjectTable.getColumns().add(column)) {
//			setSubjectTableColumnCellFactory(column, group);
//		}
//	}
//
//	private void setSubjectTableColumnCellFactory(TableColumn<Subject, String> column, StudentGroup group) {
//		column.setCellValueFactory(new PropertyValueFactory<>("text"));
//		ObservableList<Subject> list = FXCollections.observableArrayList();
////		List<Subject> przedmiot = group.getSubject();
////		list.addAll(przedmiot);
//		SubjectTable.setItems(list);
//	}
//
//	private void setLecturers(Subject subject) throws Throwable {
//		TableColumn<Lecturer, String> column = new TableColumn<>("Wykładowca");
//		column.setPrefWidth(LecturerTable.getPrefWidth());
//		LecturerTable.getColumns().clear();
//		if (LecturerTable.getColumns().add(column)) {
//			column.setCellValueFactory(new PropertyValueFactory<>("text"));
//			ObservableList<Lecturer> list = FXCollections.observableArrayList();
//			List<Lecturer> lecturer = subject.getLecturer();
//			list.addAll(lecturer);
//			LecturerTable.setItems(list);
//		}
//	}
//
//	private void setFreeRooms(int space) {
//		TableColumn<FreeRoom, String> column = new TableColumn<>("Sale");
//		column.setPrefWidth(FreeRoomsTable.getPrefWidth());
//		FreeRoomsTable.getColumns().clear();
//		if (FreeRoomsTable.getColumns().add(column)) {
//			column.setCellValueFactory(new PropertyValueFactory<>("text"));
//			ObservableList<FreeRoom> saleTableNames = database.getFreeRooms(space);
//			FreeRoomsTable.setItems(saleTableNames);
//		}
//	}
//
//	private void setDays(FreeRoom room) {
//		TableColumn<Day, String> column = new TableColumn<>("Dzien");
//		double prefWidth = DayTable.getPrefWidth();
//		column.setPrefWidth(prefWidth);
//		DayTable.getColumns().clear();
//		if (DayTable.getColumns().add(column)) {
//			column.setCellValueFactory(new PropertyValueFactory<>("text"));
//			ObservableList<Day> dzienTableNames = database.getDays(room);
//			DayTable.setItems(dzienTableNames);
//		}
//	}
//
//	private void setHours(Day day) {
//		TableColumn<Hour, String> column = new TableColumn<>("Godzina");
//		column.setPrefWidth(HourTable.getPrefWidth());
//		if (HourTable.getColumns().add(column)) {
//			column.setCellValueFactory(new PropertyValueFactory<>("text"));
//			ObservableList<Hour> godzinaTableNames = database.getHours(day).sorted();
//			HourTable.setItems(godzinaTableNames);
//
//		}
//	}
//
//	private void setSchedule(ScheduleInterface scheduleInterface) {
//		List<TableColumn<ScheduleInterface, String>> tableColumns = new LinkedList<>();
//		TableColumn<ScheduleInterface, String> indexColumn = new TableColumn<>("#");
//		indexColumn.setSortable(false);
//		indexColumn.setCellValueFactory(
//				column -> new ReadOnlyObjectWrapper<>(ScheduleTable.getItems().indexOf(column.getValue()) + 1)
//						.asString());
//		tableColumns.add(indexColumn);
//		tableColumns.add(generateTemporaryScheduleColumn("Grupa", "group"));
//		tableColumns.add(generateTemporaryScheduleColumn("Przedmiot", "subject"));
//		tableColumns.add(generateTemporaryScheduleColumn("Wykładowca", "lecturer"));
//		tableColumns.add(generateTemporaryScheduleColumn("Sala", "room"));
//		tableColumns.add(generateTemporaryScheduleColumn("Dzień", "day"));
//		tableColumns.add(generateTemporaryScheduleColumn("Godzina", "hour"));
//		ScheduleTable.getColumns().clear();
//		for (TableColumn<ScheduleInterface, String> tableColumn : tableColumns) {
//			ScheduleTable.getColumns().add(tableColumn);
//		}
//		scheduleList.add(scheduleInterface);
//		refresh();
//		ScheduleTable.getItems().add(scheduleInterface);
//	}
//
//	private TableColumn<ScheduleInterface, String> generateTemporaryScheduleColumn(String name, String propertyName) {
//		TableColumn<ScheduleInterface, String> column = new TableColumn<>(name);
//		column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
//		return column;
//	}
//
//	private void refresh() {
//		clearTableView();
//		setGroup();
//	}
//
//	private void clearTableView() {
//		HourTable.getColumns().clear();
//		DayTable.getColumns().clear();
//		FreeRoomsTable.getColumns().clear();
//		LecturerTable.getColumns().clear();
//		SubjectTable.getColumns().clear();
//		GroupTable.getColumns().clear();
//	}
//
//	private void setScheduleFromDatabase() {
//		List<Schedule> schedulesFromDatabase = database.getSchedule();
//		setScheduleFromList(schedulesFromDatabase);
//	}
//
//	private void setScheduleFromList(List<Schedule> schedulesFromDatabase) {
//		for (Schedule schedule : schedulesFromDatabase) {
//			setSchedule(schedule);
//		}
//	}

	@Override
	public String getPath() {
		return PATH;
	}

}
