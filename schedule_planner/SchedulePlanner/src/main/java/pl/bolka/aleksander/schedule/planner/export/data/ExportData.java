package pl.bolka.aleksander.schedule.planner.export.data;

import java.util.List;
import java.util.Map;

/**
 * Created by Aleksander on 2016-10-22.
 */
public class ExportData {

    private List<Map<Integer,String>> data;

    public List<Map<Integer, String>> getData() {
        return data;
    }

    public void setData(List<Map<Integer, String>> data) {
        this.data = data;
    }
}
