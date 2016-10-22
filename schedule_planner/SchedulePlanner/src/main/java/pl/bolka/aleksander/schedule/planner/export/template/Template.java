package pl.bolka.aleksander.schedule.planner.export.template;

import java.util.List;
import java.util.Set;

/**
 * Created by Aleksander on 2016-10-22.
 */
public interface Template {

    Set<ColumnDefinition> getColumnsDefefinition();

    List<RowsDefinition> getRowsDefinition();
}
