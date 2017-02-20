package pl.bolka.aleksander.schedule.planner.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import pl.bolka.aleksander.schedule.planner.config.ScreensConfig;
import pl.bolka.aleksander.schedule.planner.fx.column.HourColumn;
import pl.bolka.aleksander.schedule.planner.fx.column.RoomColumn;
import pl.bolka.aleksander.schedule.planner.model.entity.Day;
import pl.bolka.aleksander.schedule.planner.model.entity.FreeRoom;
import pl.bolka.aleksander.schedule.planner.model.filter.DayFilter;
import pl.bolka.aleksander.schedule.planner.model.services.DayRepositoryService;
import pl.bolka.aleksander.schedule.planner.model.services.RoomRepositoryService;
import pl.bolka.aleksander.schedule.planner.model.services.WeekRepositoryService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Aleksander Bołka on 2016-07-01.
 */
public class AddDataRoomController extends FXController {

    private static final String PATH = "/pl/bolka/aleksander/schedule/planner/fx/fxml/AddDataRoom.fxml";

    @FXML
    public TableView<FreeRoom> roomTable;

    @FXML
    public TextField number;

    @FXML
    public TextField roomSpace;

    @FXML
    public Button change;

    @FXML
    public Button back;

    @FXML
    public Button mainMenu;

    @FXML
    public Button add;

    @FXML
    public Button addNewRoom;

    @FXML
    public Button delete;

    @FXML
    public TableView hourTable;

    @FXML
    public DatePicker datePicker;

    @Autowired
    RoomRepositoryService roomRepositoryService;

    @Autowired
    DayRepositoryService dayRepositoryService;

    @Autowired
    WeekRepositoryService weekRepositoryService;

    FreeRoom freeRoom;

    public AddDataRoomController(ScreensConfig flow) {
        super(flow);
    }

    @Override
    public void initialize() {

        datePicker.setVisible(false);
        hourTable.setVisible(false);

        setButtons();
        setRoomTableListener();
        setDatePicker();
    }

    private void setButtons() {
        setNavigateButtons();
        setAddButton();
        setAddNewRoom();
        setChangeButton();
        setDeleteButton();
    }

    private void setDeleteButton() {
        delete.setDisable(true);
        delete.setOnAction(event -> {
            freeRoom = getSelectedItemFromTable(roomTable);
            roomRepositoryService.delete(freeRoom);
            roomTable.getSelectionModel().clearSelection();
            setRoomTable();
        });
    }

    private void setAddNewRoom() {
        addNewRoom.setOnAction(event -> {
            add.setDisable(false);
            change.setDisable(true);
            number.clear();
            roomSpace.clear();
        });
    }

    private void setAddButton() {
        change.setDisable(true);
        add.setOnAction(event -> {
            change.setDisable(true);
            if (!number.getText().isEmpty()) {
                FreeRoom freeRoom = new FreeRoom();
                setRoom(freeRoom);
                setRoomTable();
            }
        });
    }

    private void setDatePicker() {
        datePicker.setOnAction(event -> {
            LocalDate localDate = datePicker.getValue();
            DayFilter dayFilter = new DayFilter();
            dayFilter.setDate(Date.valueOf(localDate));
            Day day = dayRepositoryService.findOne(dayFilter);
            Hibernate.initialize(day.getHour());
            setColumn(hourTable, new HourColumn("Godziny"), day.getHour());
        });
        List<Day> days = dayRepositoryService.findAll();
        datePicker.setDayCellFactory(CallbackForDatePickers.getCallBackForDates(getLocalDates(days)));
    }

    private List<LocalDate> getLocalDates(List<Day> days) {
        List<LocalDate> localDates = days.stream().map(d -> d.getDate().toLocalDate()).collect(Collectors.toList());
        return localDates;
    }

    private void setChangeButton() {
        change.setDisable(true);
        change.setOnAction(event -> {
            roomRepositoryService.save(setRoom(freeRoom));
            setRoomTable();
        });
    }

    private void setRoomTableListener() {
        setRoomTable();
        roomTable.getSelectionModel().selectedItemProperty().addListener(observable -> {
            change.setDisable(false);
            delete.setDisable(false);
            add.setDisable(true);
            if (getSelectedItemFromTable(roomTable) != null) {
                freeRoom = getSelectedItemFromTable(roomTable);
            }
            number.setText(freeRoom.getNumber());
            roomSpace.setText(freeRoom.getRoomSpace() + "");
        });
    }

    private void setRoomTable() {
        List<FreeRoom> freeRooms = roomRepositoryService.findAll();
        setColumn(roomTable, new RoomColumn("Sale"), freeRooms);
    }

    private void setNavigateButtons() {
        setMainMainButton();
        setBackButton();
    }

    private FreeRoom setRoom(FreeRoom freeRoom) {
        freeRoom.setNumber(number.getText());
        freeRoom.setRoomSpace(Integer.parseInt(roomSpace.getText()));
        freeRoom.setDay(dayRepositoryService.findAll());
//        freeRoom.setWeek(weekRepositoryService.findAll());
        roomRepositoryService.save(freeRoom);
        return freeRoom;
    }

    @Override
    public String getPath() {
        return PATH;
    }

    private void setBackButton() {
        back.setOnAction((ActionEvent event) -> flow.loadAddDataChooseController());
    }

    private void setMainMainButton() {
        mainMenu.setOnAction((ActionEvent event) -> flow.loadStartPageController());
    }
}
