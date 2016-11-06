package pl.bolka.aleksander.schedule.planner.export.template;

import com.itextpdf.text.BaseColor;

/**
 * Created by Aleksander on 2016-10-22.
 */
public interface Template {

    BaseColor dayHeaderColor = new BaseColor(255,204,153);

    BaseColor tableHeaderColor = new BaseColor(204, 255, 255);

    BaseColor mergeRegionColor = new BaseColor(255,241,204);

    float[] getColumnWidths();

//    Set<ColumnDefinition> getColumnDefinitions();

//    List<RowDefinition> getRowsDefinitions();


}
