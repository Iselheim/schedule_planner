package pl.bolka.aleksander.schedule.planner.export.data;

import pl.bolka.aleksander.schedule.planner.model.entity.Schedule;

/**
 * Created by Aleksander on 2016-10-22.
 */
public class ExportData {

    private Position position;

    private String content;

    private ExportData(){

    }

    public static ExportData getExportData(Schedule schedule){
        //TODO
        return new ExportData();
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
