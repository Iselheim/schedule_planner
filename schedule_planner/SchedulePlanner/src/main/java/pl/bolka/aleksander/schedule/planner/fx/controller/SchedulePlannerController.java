package pl.bolka.aleksander.schedule.planner.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.bolka.aleksander.schedule.planner.config.ScreensConfig;
import pl.bolka.aleksander.schedule.planner.exceptions.NotSupportedYetException;
import pl.bolka.aleksander.schedule.planner.fx.column.*;
import pl.bolka.aleksander.schedule.planner.fx.controller.validate.*;
import pl.bolka.aleksander.schedule.planner.model.entity.*;
import pl.bolka.aleksander.schedule.planner.model.filter.*;
import pl.bolka.aleksander.schedule.planner.model.services.AbstractRepositoryService;

import java.sql.Time;
import java.util.*;

/**
 * Created by Aleksander on 2016-09-12.
 */
public class SchedulePlannerController extends FXController {

    private static final String PATH = "/pl/bolka/aleksander/schedule/planner/fx/fxml/SchedulePlanner.fxml";

    @FXML
    public TableView<SemesterViewTO> semesterTable;

    @FXML
    public TableView<StudentGroupViewTO> groupTable;

    @FXML
    public TableView<SubjectViewTO> subjectTable;

    @FXML
    public TableView<LecturerViewTO> lecturerTable;

    @FXML
    public TableView<RoomViewTO> roomsTable;

    @FXML
    public TableView<WeekViewTO> weekTable;

    @FXML
    public TableView<DayViewTO> dayTable;

    @FXML
    public TableView<HourViewTO> hourTable;

    @FXML
    public TableView<Schedule> scheduleTable;

    @FXML
    public Button add;

    @FXML
    public Button save;

    @FXML
    public Button exportToPdf;

    @FXML
    public Button back;

    @FXML
    public Button mainMenu;

    @Autowired
    private AbstractRepositoryService<Semester, SemesterFilter> semesterRepositoryService;

    @Autowired
    private AbstractRepositoryService<StudentGroup, StudentGroupFilter> studentGroupRepositoryService;

    @Autowired
    private AbstractRepositoryService<Subject, SubjectFilter> subjectRepositoryService;

    @Autowired
    private AbstractRepositoryService<Schedule, ScheduleFilter> scheduleRepositoryService;

    @Autowired
    private AbstractRepositoryService<Lecturer, LecturerFilter> lecturerRepositoryService;

    @Autowired
    private AbstractRepositoryService<FreeRoom, RoomFilter> roomRepositoryService;

    @Autowired
    private AbstractRepositoryService<Week, WeekFilter> weekRepositoryService;

    private List<Schedule> plan;

    private ViewDataController dataController;

    public SchedulePlannerController(ScreensConfig flow) {
        super(flow);
    }

    @Override
    public void initialize() {
        try {
            setViewDataController();
        } catch (NotSupportedYetException e) {
            return;
        }

        setButtons();

        clearAllTableView();
        setTableListeners();
        setMultiSelection();

        plan = new ArrayList<>();
    }

    private void setViewDataController() throws NotSupportedYetException {
        if (scheduleRepositoryService.findAll().isEmpty()) {
            dataController = new ViewDataController();
            dataController.setRooms(roomRepositoryService.findAll());
            dataController.setWeeks(weekRepositoryService.findAll());
            dataController.setSemesters(semesterRepositoryService.findAll());
            dataController.setGroups(studentGroupRepositoryService.findAll());
            dataController.setLecturers(lecturerRepositoryService.findAll());
            dataController.setSubjects(subjectRepositoryService.findAll());
            dataController.fillValidateStructure();
        }else {
            throw new NotSupportedYetException("Schedule in database is not empty!");
        }
    }

    private void setButtons() {
        setNavigationButtons();

        setAddButton();
        setSaveButton();
    }

    private void setSaveButton() {
        save.setOnAction(event -> {
//            scheduleRepositoryService.addAll(plan);
            clearAllTableView();
        });
    }

    private void setAddButton() {
        add.setOnAction(event -> {
            //TODO
            SemesterViewTO semesterViewTO = getSelectedItemFromTable(semesterTable);
            List<StudentGroupViewTO> groupViewTOS = getSelectedItemsFromTable(groupTable);
            SubjectViewTO subjectViewTO = getSelectedItemFromTable(subjectTable);
            LecturerViewTO lecturerViewTO = getSelectedItemFromTable(lecturerTable);
            RoomViewTO roomViewTO = getSelectedItemFromTable(roomsTable);
            List<WeekViewTO> weekViewTOS = getSelectedItemsFromTable(weekTable);
            DayViewTO dayViewTO = getSelectedItemFromTable(dayTable);
            List<HourViewTO> hourViewTOS = getSelectedItemsFromTable(hourTable);

            for (StudentGroupViewTO studentGroup : groupViewTOS) {
                plan.add(dataController.getSchedule(semesterViewTO,studentGroup,subjectViewTO,lecturerViewTO,roomViewTO,weekViewTOS,dayViewTO,hourViewTOS));
            }
            clearAllTableView();
            setScheduleTable();

        });
    }

    private void setScheduleTable() {
        //TODO wyświetylanie tablie z ulozonym palenm
        List<ScheduleRow> scheduleRows = new ArrayList<>();

        for (Schedule schedule : plan) {
            ScheduleRow scheduleRow = new ScheduleRow();
            scheduleRow.setSemester(schedule.getSemester());
            scheduleRow.setStudentGroup(schedule.getStudentGroup());
            scheduleRow.setLecturer(schedule.getLecturer());
            scheduleRow.setRoom(schedule.getFreeRoom());
            scheduleRow.setDay(schedule.getDay().get(0));
            scheduleRow.setHoursFrom(getTimeFrom(schedule));
            scheduleRow.setHoursTo(getTimeTo(schedule));
            scheduleRow.setSubject(schedule.getSubject());
            scheduleRows.add(scheduleRow);
        }

        Map<TableColumn, String> tableColumns = new LinkedHashMap<>();

        tableColumns.put(new SemesterColumn("Semestr"), "semester");
        tableColumns.put(new StudentGroupColumn("Grupa"), "studentGroup");
        tableColumns.put(new SubjectColumn("Przedmiot"), "subject");
        tableColumns.put(new LecturerColumn("Wykładowca"), "lecturer");
        tableColumns.put(new RoomColumn("Sala"), "room");
        tableColumns.put(new DayColumn("Dzień"), "day");
        tableColumns.put(new TimeColumn("Od"), "hoursFrom");
        tableColumns.put(new TimeColumn("Do"), "hoursTo");

        addColumnsForScheduleTable(scheduleTable, tableColumns, scheduleRows);

    }

    private Time getTimeTo(Schedule schedule) {
        Time time = null;
        List<Hour> hours = schedule.getHour();
        for (Hour hour : hours) {
            Time timeFrom = hour.getTimeTo();
            if (time == null) {
                time = timeFrom;
            } else {
                if (time.compareTo(timeFrom) > 0) {
                    time = timeFrom;
                }
            }
        }
        return time;
    }

    private Time getTimeFrom(Schedule schedule) {
        Time time = null;
        List<Hour> hours = schedule.getHour();
        for (Hour hour : hours) {
            Time timeFrom = hour.getTimeFrom();
            if (time == null) {
                time = timeFrom;
            } else {
                if (time.compareTo(timeFrom) < 0) {
                    time = timeFrom;
                }
            }
        }
        return time;
    }

    private void setMultiSelection() {
        setSelectionModeMultiple(weekTable);
        setSelectionModeMultiple(hourTable);
//        setSelectionModeMultiple(dayTable);
        setSelectionModeMultiple(groupTable);
    }

    private void setTableListeners() {
        setSemesterTableListener();
        setGroupTableListener();
        setSubjectTableListener();
        setLecturerTableListener();
        setRoomTableListener();
        setWeekTableListener();
        setDayTableListener();
    }

    private void setDayTableListener() {
        dayTable.getSelectionModel().selectedItemProperty().addListener(observable -> {
            DayViewTO day = getSelectedItemFromTable(dayTable);
            if (day != null) {
                setHourTable(day);
            }
        });
    }

    private void setWeekTableListener() {
        weekTable.getSelectionModel().selectedItemProperty().addListener(observable -> {
            WeekViewTO week = getSelectedItemFromTable(weekTable);
            if (week != null) {
                setDayTable(week);
            }
        });
    }

    private void setRoomTableListener() {
        roomsTable.getSelectionModel().selectedItemProperty().addListener(observable -> {
            List<RoomViewTO> roomViewTOS = getSelectedItemsFromTable(roomsTable);
            if (roomViewTOS != null && !roomViewTOS.isEmpty()) {
                setWeekTable(roomViewTOS);
            }
        });
    }

    private void setLecturerTableListener() {
        lecturerTable.getSelectionModel().selectedItemProperty().addListener(observable -> {
            LecturerViewTO lecturerViewTO = getSelectedItemFromTable(lecturerTable);
            if (lecturerViewTO != null) {
                setRoomTable(lecturerViewTO);
            }
        });
    }

    private void setSubjectTableListener() {
        subjectTable.getSelectionModel().selectedItemProperty().addListener(observable -> {
            SubjectViewTO subject = getSelectedItemFromTable(subjectTable);
            if (subject != null) {
                setLecturerTable(subject);
            }
        });
    }

    private void setGroupTableListener() {
        groupTable.getSelectionModel().selectedItemProperty().addListener(observable -> {
            StudentGroupViewTO studentGroup = getSelectedItemFromTable(groupTable);
            if (studentGroup != null) {
                setSubjectTable(studentGroup);
            }
        });
    }

    private void setSemesterTableListener() {
        semesterTable.getSelectionModel().selectedItemProperty().addListener(observable -> {
            SemesterViewTO semester = getSelectedItemFromTable(semesterTable);
            if (semester != null) {
                setGroupTable(semester);
            }
        });
    }

    private void setSemesterTableData() {
        setColumn(semesterTable, new SemesterViewTOColumn("Semestr"), dataController.getViewTOStructure().values());
    }

    private void setGroupTable(SemesterViewTO semester) {
        setColumn(groupTable, new StudentGroupViewTOColumn("Grupy"), semester.getGroups().values());
    }

    private void setSubjectTable(StudentGroupViewTO studentGroup) {
        setColumn(subjectTable, new SubjectViewTOColumn("Przedmiot"), studentGroup.getSubjects().values());
    }

    private void setLecturerTable(SubjectViewTO subject) {
        setColumn(lecturerTable, new LecturerViewTOColumn("Wykładowcy"), subject.getLecturers().values());
    }

    private void setRoomTable(LecturerViewTO lecturerViewTO) {
        setColumn(roomsTable, new RoomViewTOColumn("Sale"), lecturerViewTO.getRooms().values());
    }

    private void setWeekTable(List<RoomViewTO> room) {
        setColumn(weekTable, new WeekViewTOColumn("Tygodnie"), getWeeksFromRooms(room));
    }

    private List<WeekViewTO> getWeeksFromRooms(List<RoomViewTO> room) {
        ArrayList list = new ArrayList(room.get(0).getWeeks().values());
        for (RoomViewTO roomViewTO : room) {
            list.retainAll(roomViewTO.getWeeks().values());
        }
        return list;
    }

    private void setDayTable(WeekViewTO week) {
        setColumn(dayTable, new DayViewTOColumn("Dzień tygodnia"), week.getDays().values());
    }

    private void setHourTable(DayViewTO day) {
        setColumn(hourTable, new HourViewTOColumn("Godziny"), day.getHours().values());
    }

    private void setNavigationButtons() {
        setBackButton();
        setMainMainButton();
    }

    private void setBackButton() {
        back.setOnAction((ActionEvent event) -> flow.loadStartPageController());
    }


    private void setMainMainButton() {
        mainMenu.setOnAction((ActionEvent event) -> flow.loadStartPageController());
    }

    private void clearAllTableView() {
        semesterTable.getColumns().clear();
        groupTable.getColumns().clear();
        subjectTable.getColumns().clear();
        lecturerTable.getColumns().clear();
        roomsTable.getColumns().clear();
        weekTable.getColumns().clear();
        dayTable.getColumns().clear();
        hourTable.getColumns().clear();
        scheduleTable.getColumns().clear();
        setSemesterTableData();
    }

    @Override
    public String getPath() {
        return PATH;
    }

    protected void addColumnsForScheduleTable(TableView tableView, Map<TableColumn, String> tableColumns, Collection<ScheduleRow> observableList) {
        tableColumns.keySet().forEach(tableColumn -> tableColumn.setPrefWidth(tableView.getPrefWidth() / tableColumns.size()));
        tableView.getColumns().clear();
        Set<Map.Entry<TableColumn, String>> entries = tableColumns.entrySet();
        for (Map.Entry<TableColumn, String> entry : entries) {
            TableColumn tableColumn = entry.getKey();
            tableColumn.setCellValueFactory(new PropertyValueFactory<ScheduleRow, String>(entry.getValue()));
        }
        tableView.getColumns().addAll(tableColumns.keySet());
        tableView.setItems(translateToObsList(observableList));
    }
}
