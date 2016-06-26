package pl.bolka.aleksander.schedule.planner.controller.fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import pl.bolka.aleksander.schedule.planner.config.ScreensConfig;

import java.util.List;

public abstract class FXController {

	protected ScreensConfig flow;

	public FXController(ScreensConfig flow) {
		this.flow = flow;
	}

	public abstract String getPath();

	protected <E> E getDTOFromTable(TableView<E> tableView) {
		return tableView.getSelectionModel().getSelectedItem();
	}

	public  <E> ObservableList<E> translateToObsList(List<E> dtos){
		ObservableList<E> obsList = FXCollections.observableArrayList();
		obsList.addAll(dtos);
		return obsList;
	}

	public static <E> ObservableList<E> translateToObsList(E dto){
		ObservableList<E> obsList = FXCollections.observableArrayList();
		obsList.addAll(dto);
		return obsList;
	}
}
