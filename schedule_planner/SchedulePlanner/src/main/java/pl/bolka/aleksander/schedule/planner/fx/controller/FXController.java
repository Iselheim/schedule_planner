package pl.bolka.aleksander.schedule.planner.fx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import pl.bolka.aleksander.schedule.planner.config.ScreensConfig;

import java.util.List;

public abstract class FXController {

	protected final static Logger logger = Logger.getLogger(FXController.class);

	protected ScreensConfig flow;

	public FXController(ScreensConfig flow) {
		this.flow = flow;
	}

	public abstract String getPath();

	@FXML
	public abstract void initialize();

	protected <E> E getSelectedItemFromTable(TableView<E> tableView) {
		return tableView.getSelectionModel().getSelectedItem();
	}

	protected <E> List<E> getSelectedItemsFromTable(TableView<E> tableView) {
		return tableView.getSelectionModel().getSelectedItems();
	}

	public  <E> ObservableList<E> translateToObsList(List<E> dtos){
		ObservableList<E> obsList = FXCollections.observableArrayList();
		obsList.addAll(dtos);
		return obsList;
	}

	public <E> ObservableList<E> translateToObsList(E dto){
		ObservableList<E> obsList = FXCollections.observableArrayList();
		obsList.addAll(dto);
		return obsList;
	}

	protected void setColumns(TableView tableView, TableColumn tableColumn, List observableList){
		tableView.getColumns().clear();
		tableColumn.setPrefWidth(tableView.getPrefWidth());
		tableView.getColumns().setAll(tableColumn);
		tableView.setItems(translateToObsList(observableList));
	}

	public void setSelectionModeMultiple(TableView tableView) {
		tableView.getSelectionModel().setSelectionMode(
				SelectionMode.MULTIPLE
		);
	}

}
