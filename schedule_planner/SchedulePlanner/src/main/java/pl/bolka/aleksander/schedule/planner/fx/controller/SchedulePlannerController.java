package pl.bolka.aleksander.schedule.planner.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import pl.bolka.aleksander.schedule.planner.config.ScreensConfig;
import pl.bolka.aleksander.schedule.planner.exceptions.NotSupportedYetException;
import pl.bolka.aleksander.schedule.planner.fx.column.*;
import pl.bolka.aleksander.schedule.planner.fx.controller.validate.*;
import pl.bolka.aleksander.schedule.planner.model.entity.*;
import pl.bolka.aleksander.schedule.planner.model.filter.*;
import pl.bolka.aleksander.schedule.planner.model.services.AbstractRepositoryService;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//            Semester semester = getSelectedItemFromTable(semesterTable);
//            List<StudentGroup> studentGroups = getSelectedItemsFromTable(groupTable);
//            Subject subject = getSelectedItemFromTable(subjectTable);
//            Lecturer lecturer = getSelectedItemFromTable(lecturerTable);
//            FreeRoom freeRoom = getSelectedItemFromTable(roomsTable);
//            List<Week> weeks = getSelectedItemsFromTable(weekTable);
//            Day day = getSelectedItemFromTable(dayTable);
//            List<Hour> hours = getSelectedItemsFromTable(hourTable);
//
//            for (StudentGroup studentGroup : studentGroups) {
//                Schedule schedule = new Schedule();
//                schedule.setSemester(semester);
//                schedule.setStudentGroup(studentGroup);
//                schedule.setSubject(subject);
//                schedule.setLecturer(lecturer);
//                schedule.setHour(hours);
//                schedule.setDay(day);
//                schedule.setWeek(weeks);
//                schedule.setFreeRoom(freeRoom);
//
//                plan.add(schedule);
//                dataController.updateData(schedule);
//            }

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
//            scheduleRow.setBusyRoom(schedule.getFreeRoom());
//            scheduleRow.setDay(schedule.getDay());
            scheduleRow.setHoursFrom(getTimeFrom(schedule));
            scheduleRow.setHoursTo(getTimeTo(schedule));
            scheduleRows.add(scheduleRow);
        }

        Map<TableColumn, String> tableColumns = new HashMap<>();

        tableColumns.put(new SemesterColumn("Semestr"), "semester");
        tableColumns.put(new StudentGroupColumn("Grupy"), "studentGroup");
        tableColumns.put(new SubjectColumn("Przedmiot"), "subject");
        tableColumns.put(new LecturerColumn("Wykładowaca"), "lecturer");
        tableColumns.put(new RoomColumn("Sala"), "freeRoom");
        tableColumns.put(new DayColumn("Dzień"), "day");
        tableColumns.put(new TimeColumn("Od"), "timeFrom");
        tableColumns.put(new TimeColumn("Do"), "timeTo");

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
            RoomViewTO freeRoom = getSelectedItemFromTable(roomsTable);
            if (freeRoom != null) {
                setWeekTable(freeRoom);
            }
        });
    }

    private void setLecturerTableListener() {
        lecturerTable.getSelectionModel().selectedItemProperty().addListener(observable -> {
            SubjectViewTO subject = getSelectedItemFromTable(subjectTable);
            if (subject != null) {
                setRoomTable(subject);
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

    private void setRoomTable(SubjectViewTO subject) {
        setColumn(roomsTable, new RoomColumn("Sale"), subject.getLecturers().values());
    }

    private void setWeekTable(RoomViewTO room) {
        setColumn(weekTable, new WeekColumn("Tygodnie"), room.getWeeks().values());
    }

    private void setDayTable(WeekViewTO week) {
        setColumn(dayTable, new DayValidateColumn("Dzień tygodnia"), week.getDays().values());
    }

    private void setHourTable(DayViewTO day) {
        setColumn(hourTable, new HourColumn("Godziny"), day.getHours().values());
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
}
