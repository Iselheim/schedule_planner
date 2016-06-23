package pl.bolka.aleksander.schedule.planner.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by Aleksander Bołka on 2016-06-23.
 */
public final class ObservableListMapper {

    public static <E> ObservableList<E> translateToObsList(List<E> dtos){
        ObservableList<E> obsList = FXCollections.observableArrayList();
        obsList.addAll(dtos);
        return obsList;
    }
}
