package pl.bolka.aleksander.schedule.planner.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.bolka.aleksander.schedule.planner.config.ScreensConfig;
import pl.bolka.aleksander.schedule.planner.fx.column.ScheduleRow;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by Aleksander Bołka on 2016-06-22.
 */
public class AddDataChooseController extends FXController {

    private static final String PATH = "/pl/bolka/aleksander/schedule/planner/fx/fxml/AddDataChoose.fxml";

    @FXML
    private Button addLecturers;

    @FXML
    private Button addSubject;

    @FXML
    private Button addSpecialization;

    @FXML
    private Button addFaculty;

    @FXML
    private Button addSemester;

    @FXML
    private Button addGroup;

    @FXML
    private Button addDay;

    @FXML
    private Button addRoom;

    @FXML
    private Button mainMenu;

    public AddDataChooseController(ScreensConfig flow) {
        super(flow);
    }

    @FXML
    public void initialize() {
        setButtons();
    }

    private void setButtons() {
        setAddLecturerButton();
        setAddRoomButton();
        setMainMainButton();
        setAddSubjectButton();
        setAddSemesterButton();
        setAddSpecializationButton();
        setAddDataStudentGroupButton();
    }

    private void setAddRoomButton() {
        addRoom.setOnAction(event -> flow.loadAddDataRoomController());
    }

    private void setAddLecturerButton() {
        addLecturers.setOnAction(event -> flow.loadAddDataLecturerController());
    }

    private void setMainMainButton() {
        mainMenu.setOnAction((ActionEvent event) -> flow.loadStartPageController());
    }

    private void setAddSubjectButton(){addSubject.setOnAction(event -> flow.loadAddDataSubjectController());}

    private void setAddSemesterButton(){
        addSemester.setOnAction(event -> flow.loadAddDataSemesterController());
    }

    private void setAddSpecializationButton(){
        addSpecialization.setOnAction(event -> flow.loadAddDataSpecializationController());
    }

    private void setAddDataStudentGroupButton(){
        addGroup.setOnAction(event -> flow.loadAddDataStudentGroupController());
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
