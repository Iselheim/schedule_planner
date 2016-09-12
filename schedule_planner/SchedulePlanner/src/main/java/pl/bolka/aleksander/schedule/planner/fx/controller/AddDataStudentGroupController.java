package pl.bolka.aleksander.schedule.planner.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import pl.bolka.aleksander.schedule.planner.config.ScreensConfig;
import pl.bolka.aleksander.schedule.planner.fx.column.StudentGroupColumn;
import pl.bolka.aleksander.schedule.planner.model.entity.StudentGroup;
import pl.bolka.aleksander.schedule.planner.model.filter.StudentGroupFilter;
import pl.bolka.aleksander.schedule.planner.model.services.AbstractRepositoryService;

/**
 * Created by Aleksander on 2016-09-06.
 */
public class AddDataStudentGroupController extends FXController {

    private static final String PATH = "/pl/bolka/aleksander/schedule/planner/fx/fxml/AddDataStudentGroups.fxml";

    @FXML
    public TableView<StudentGroup> groupTable;

    @FXML
    public Button back;

    @FXML
    public Button mainMenu;

    @FXML
    public Button add;

    @FXML
    public Button delete;

    @FXML
    public Button change;

    @FXML
    public TextField quantity;

    @FXML
    public TextField number;


    @Autowired
    private AbstractRepositoryService<StudentGroup,StudentGroupFilter> studentGroupRepositoryService;

    @Override
    public void initialize() {
        setNavigateButtons();

        setGroupTable();
        setCreationButtons();
        setTableListener();
    }

    private void setCreationButtons() {
        add.setDisable(true);
        change.setDisable(true);
        delete.setDisable(true);

        setAddButton();
        setDeleteButton();
    }

    private void setAddButton() {
        add.setOnAction(event -> {
            StudentGroup studentGroup= new StudentGroup();
            studentGroup.setNumber(Integer.parseInt(number.getText()));
            studentGroup.setQuantity(Integer.parseInt(quantity.getText()));
            studentGroupRepositoryService.add(studentGroup);
        });
    }

    private void setDeleteButton(){
        delete.setOnAction(event -> {
            StudentGroup studentGroup = getSelectedItemFromTable(groupTable);
            studentGroupRepositoryService.delete(studentGroup);
        });
    }

    private void setGroupTable() {
        setColumns(groupTable,new StudentGroupColumn("Grupy"), studentGroupRepositoryService.findAll());
    }

    private void setTableListener() {
        groupTable.getSelectionModel().selectedItemProperty().addListener(observable -> {
            StudentGroup selectedItemFromTable = getSelectedItemFromTable(groupTable);
            quantity.setText(selectedItemFromTable.getQuantity() + "");
            number.setText(selectedItemFromTable.getNumber() + "");
        });
    }

    public AddDataStudentGroupController(ScreensConfig flow) {
        super(flow);
    }

    private void setNavigateButtons() {
        setMainMainButton();
        setBackButton();
    }

    private void setBackButton() {
        back.setOnAction((ActionEvent event) -> flow.loadAddDataChooseController());
    }

    private void setMainMainButton() {
        mainMenu.setOnAction((ActionEvent event) -> flow.loadStartPageController());
    }

    @Override
    public String getPath() {
        return PATH;
    }
}
