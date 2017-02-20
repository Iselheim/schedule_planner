package pl.bolka.aleksander.schedule.planner.export.engine;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.bolka.aleksander.schedule.planner.export.data.ExportData;
import pl.bolka.aleksander.schedule.planner.export.template.Position;
import pl.bolka.aleksander.schedule.planner.model.entity.Hour;
import pl.bolka.aleksander.schedule.planner.model.entity.HourForSchedule;
import pl.bolka.aleksander.schedule.planner.model.entity.Schedule;
import pl.bolka.aleksander.schedule.planner.model.filter.HourFilter;
import pl.bolka.aleksander.schedule.planner.model.filter.ScheduleFilter;
import pl.bolka.aleksander.schedule.planner.model.services.AbstractRepositoryService;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Aleksander on 2017-01-15.
 */
@Component
public class PdfExporter {

    @Autowired
    private PdfGenerator pdfGenerator;

    @Autowired
    private AbstractRepositoryService<Schedule, ScheduleFilter> scheduleRepositoryService;

    @Autowired
    private AbstractRepositoryService<Hour, HourFilter> hourRepositoryService;

    public PdfExporter() {
    }

    public void getPdf() {
        List<Schedule> schedules = scheduleRepositoryService.findAll();
        Map<DayEnum, Map<Position, ExportData>> data = prepareStructure();
        for (Schedule schedule : schedules) {
            if (!schedule.isPrinted()) {
                Map<Position, ExportData> exportDataMap = data.get(DayEnum.getDay(schedule.getDay().stream().findAny().get().getName()));
                Position position = getPosition(schedule.getHour(), schedule.getStudentGroup().getNumber());
                ExportData exportData = ExportData.getExportData(schedule);
                getGroupsTogether(schedules, schedule, exportData);
                exportDataMap.put(position, exportData);
            }
        }

        try {
            pdfGenerator.generate(data, 6);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }

    }

    private void getGroupsTogether(List<Schedule> schedules, Schedule schedule, ExportData exportData) {
//        exportData.setWeight(0);
        schedules.forEach(schedule1 -> {
            if (schedule1 != schedule) {
                schedule1.getHour().sort(Comparator.comparing(HourForSchedule::getTimeFrom));
                schedule.getHour().sort(Comparator.comparing(HourForSchedule::getTimeFrom));
                if (schedule1.getSubject().getName().equals(schedule.getSubject().getName()) &&
                        schedule1.getLecturer().getLastName().equals(schedule.getLecturer().getLastName()) &&
                        schedule1.getSemester().getNumber() == schedule.getSemester().getNumber() &&
                        schedule1.getSubject().getName().equals(schedule.getSubject().getName()) &&
                        schedule1.getFreeRoom().getNumber().equals(schedule.getFreeRoom().getNumber()) &&
                        schedule1.getDay().get(0).getDate().equals(schedule.getDay().get(0).getDate()) &&
                        schedule1.getHour().get(0).getTimeFrom().equals(schedule.getHour().get(0).getTimeFrom())) {
                    exportData.setWeight(exportData.getWeight() + 1);
                    schedule.setPrinted(true);
                    schedule1.setPrinted(true);
                }
            }
        });
    }

    private Position getPosition(List<HourForSchedule> hour, int groupNumber) {
        Position position = new Position();
        position.setGridY(groupNumber + 1);
        hour.sort(Comparator.comparing(HourForSchedule::getTimeFrom));
        HourForSchedule hourForSchedule = hour.get(0);
        List<Hour> hours = hourRepositoryService.findAll();
        Long xAxis = 0l;
        for (Hour hour1 : hours) {
            if (hour1.getTimeFrom().equals(hourForSchedule.getTimeFrom())) {
                xAxis = hour1.getId();
            }
        }
        position.setGridX(xAxis.intValue() - 1);
        return position;
    }

    private Map<DayEnum, Map<Position, ExportData>> prepareStructure() {
        Map<DayEnum, Map<Position, ExportData>> data = new HashMap<>();
        data.put(DayEnum.Poniedzialek, getMapForDay());
        data.put(DayEnum.Wtorek, getMapForDay());
        data.put(DayEnum.Sroda, getMapForDay());
        data.put(DayEnum.Czwartek, getMapForDay());
        data.put(DayEnum.Piatek, getMapForDay());
        return data;
    }

    private Map<Position, ExportData> getMapForDay() {
        Map<Position, ExportData> dataForDay = new HashMap<>();
        return dataForDay;
    }
}
