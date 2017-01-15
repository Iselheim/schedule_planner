package pl.bolka.aleksander.schedule.planner.export.data;

import pl.bolka.aleksander.schedule.planner.model.entity.Schedule;

/**
 * Created by Aleksander on 2016-10-22.
 */
public class ExportData {

    private int heigt = 1;

    private int weight = 1;

    private String content;

    private ExportData(){

    }

    public static ExportData getExportData(Schedule schedule){
        ExportData exportData = new ExportData();
        exportData.setHeigt(schedule.getHour().size());
        exportData.setContent(getContent(schedule));
        return exportData;
    }

    private static String getContent(Schedule schedule) {
        return schedule.getSubject().getName().toUpperCase() +
                "\n" +
                schedule.getSubject().getSubjectType().getShortcut() +
                " " +
                (schedule.getWeek().size() > 7 ? "c2tg " : "") +
                schedule.getLecturer().toString() +
                " " +
                schedule.getFreeRoom().getNumber();
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeigt() {

        return heigt;
    }

    public void setHeigt(int heigt) {
        this.heigt = heigt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
