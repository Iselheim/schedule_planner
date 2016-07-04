package pl.bolka.aleksander.schedule.planner.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import pl.bolka.aleksander.schedule.planner.config.ScreensConfig;
import pl.bolka.aleksander.schedule.planner.fx.column.HourColumn;
import pl.bolka.aleksander.schedule.planner.fx.column.RoomColumn;
import pl.bolka.aleksander.schedule.planner.model.entity.Day;
import pl.bolka.aleksander.schedule.planner.model.entity.Room;
import pl.bolka.aleksander.schedule.planner.model.filter.DayFilter;
import pl.bolka.aleksander.schedule.planner.model.services.DayRepositoryService;
import pl.bolka.aleksander.schedule.planner.model.services.RoomRepositoryService;
import pl.bolka.aleksander.schedule.planner.model.services.WeekRepositoryService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksander Bołka on 2016-07-01.
 */
public class AddDataRoomController extends FXController {

    private static final String PATH = "/pl/bolka/aleksander/schedule/planner/fx/fxml/AddDataRoom.fxml";

    @FXML
    public TableView<Room> roomTable;

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

    Room room;

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
            room = getSelectedItemFromTable(roomTable);
            roomRepositoryService.delete(room);
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
                Room room = new Room();
                setRoom(room);
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
            setColumns(hourTable, new HourColumn("Godziny"), day.getHour());
        });
        List<Day> days = dayRepositoryService.findAll();
        datePicker.setDayCellFactory(getCallBackForDates(getLocalDates(days)));
    }

    private List<LocalDate> getLocalDates(List<Day> days) {
        List<LocalDate> localDates = new ArrayList<>();
        for (Day d : days) {
            localDates.add(d.getDate().toLocalDate());
        }
        return localDates;
    }

    private Callback<DatePicker, DateCell> getCallBackForDates(List<LocalDate> dates) {
        final Callback<DatePicker, DateCell> dayCellFactory =
                new Callback<DatePicker, DateCell>() {
                    @Override
                    public DateCell call(final DatePicker datePicker) {
                        return new DateCell() {
                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);
                                if (!dates.contains(item)) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                                }
                            }
                        };
                    }
                };
        return dayCellFactory;
    }

    private void setChangeButton() {
        change.setDisable(true);
        change.setOnAction(event -> {
            roomRepositoryService.add(setRoom(room));
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
                room = getSelectedItemFromTable(roomTable);
            }
            number.setText(room.getNumber());
            roomSpace.setText(room.getRoomSpace() + "");
        });
    }

    private void setRoomTable() {
        List<Room> rooms = roomRepositoryService.findAll();
        setColumns(roomTable, new RoomColumn("Sale"), rooms);
    }

    private void setNavigateButtons() {
        setMainMainButton();
        setBackButton();
    }

    private Room setRoom(Room room) {
        room.setNumber(number.getText());
        room.setRoomSpace(Integer.parseInt(roomSpace.getText()));
        room.setDay(dayRepositoryService.findAll());
//        room.setWeek(weekRepositoryService.findAll());
        roomRepositoryService.add(room);
        return room;
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
