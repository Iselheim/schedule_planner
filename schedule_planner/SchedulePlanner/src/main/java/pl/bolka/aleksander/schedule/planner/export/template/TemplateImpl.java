package pl.bolka.aleksander.schedule.planner.export.template;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Aleksander on 2016-10-23.
 */
public class TemplateImpl implements Template {



    private List<Float> columnWidths;

    public TemplateImpl(int groupCount){
        columnWidths = new LinkedList<>();
        columnWidths.add(5f);
        columnWidths.add(10f);
        for (int i = 0; i < groupCount; i++) {
            columnWidths.add(20f);
        }
    }

    @Override
    public float[] getColumnWidths() {
        float[] floats = new float[columnWidths.size()];
        int i =0;
        for (Float columnWidth : columnWidths) {
            floats[i] = columnWidth;
            i++;
        }
        return floats;
    }

}
