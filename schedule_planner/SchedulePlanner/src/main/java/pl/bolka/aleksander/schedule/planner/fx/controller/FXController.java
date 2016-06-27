package pl.bolka.aleksander.schedule.planner.fx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.bolka.aleksander.schedule.planner.config.ScreensConfig;

import java.util.List;

public abstract class FXController {

	protected ScreensConfig flow;

	public FXController(ScreensConfig flow) {
		this.flow = flow;
	}

	public abstract String getPath();

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

}
