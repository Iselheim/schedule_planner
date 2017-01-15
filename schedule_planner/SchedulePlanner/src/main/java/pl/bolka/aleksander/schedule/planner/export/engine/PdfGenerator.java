package pl.bolka.aleksander.schedule.planner.export.engine;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.bolka.aleksander.schedule.planner.export.data.ExportData;
import pl.bolka.aleksander.schedule.planner.export.data.Position;
import pl.bolka.aleksander.schedule.planner.export.template.Template;
import pl.bolka.aleksander.schedule.planner.export.template.TemplateImpl;
import pl.bolka.aleksander.schedule.planner.model.entity.Hour;
import pl.bolka.aleksander.schedule.planner.model.filter.HourFilter;
import pl.bolka.aleksander.schedule.planner.model.services.AbstractRepositoryService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;


/**
 * Created by Aleksander on 2016-10-22.
 */
@Component
public class PdfGenerator {

    private Document document;

    private Template template;

    private int columnCount;

    @Autowired
    private AbstractRepositoryService<Hour, HourFilter> hourRepository;

    public PdfGenerator() {
        this.template = new TemplateImpl(6);
    }

    public PdfGenerator(Template template) {
        this.template = template;
    }

    public void generate(Map<DayEnum, Map<Position, ExportData>> data, int groupCount) throws IOException, DocumentException {
        document = new Document();
        prepareFile();
        document.open();
        setColumnCount(groupCount);

        PdfPTable table = prepareTable();

        // first row
        PdfPCell tableHeader = getHeader();
        table.addCell(tableHeader);

        Set<DayEnum> days = getDays();

        List<Hour> hours = hourRepository.findAll();
        hours.sort(Comparator.comparing(Hour::getId));

        days.forEach(dayEnum -> {
            table.addCell(getDayRow(dayEnum.name()));
            int rowCount = 14;
            int columnCount = 8;
            setNumbers(groupCount, table, dayEnum);
            rowCount = setRowSmallerRowCountIfNeeded(data, dayEnum, rowCount);

            Cell[][] tab = new Cell[rowCount][columnCount];
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < columnCount; j++) {
                    if (j == 0) {
                        tab[i][j] = new Cell("" + (i + 1));
                    }
                    if (j == 1) {
                        tab[i][j] = new Cell(hours.get(i).toString());
                    }
                    Map<Position, ExportData> positionMap = data.get(dayEnum);
                    ExportData exportData = positionMap.get(new Position(i, j));
                    if (exportData != null) {
                        Cell cell = new Cell(exportData.getContent());
                        if (exportData.getHeigt() != 1 && exportData.getWeight() == 1) {
                            for (int ii = 0; ii < exportData.getHeigt(); ii++) {
                                tab[i + ii][j] = new Cell(false);
                            }
                            cell.setRowSpan(exportData.getHeigt());
                        } else if (exportData.getHeigt() == 1 && exportData.getWeight() != 1) {
                            tab[i][j + exportData.getWeight() - 1] = new Cell(false);
                            cell.setColSpan(exportData.getWeight());
                        } else if (exportData.getHeigt() != 1 && exportData.getWeight() != 1) {
                            for (int ii = 0; ii < exportData.getHeigt(); ii++) {
                                for (int jj = 0; jj < exportData.getWeight(); jj++) {
                                    tab[i + ii][j + jj] = new Cell(false);
                                }
                            }
                            cell.setColSpan(exportData.getWeight());
                            cell.setRowSpan(exportData.getHeigt());
                        }
                        tab[i][j] = cell;
                    } else if (tab[i][j] == null) {
                        tab[i][j] = new Cell("");
                    }
                }
            }
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < columnCount; j++) {
                    Cell cell = tab[i][j];
                    if (cell.isDraw()) {
                        PdfPCell pdfCell = getCell(cell.getContent());
                        pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        pdfCell.setColspan(cell.getColSpan());
                        pdfCell.setRowspan(cell.getRowSpan());
                        table.addCell(pdfCell);
                    }
                }
            }

        });

        document.add(table);
        document.close();
    }

    private int setRowSmallerRowCountIfNeeded(Map<DayEnum, Map<Position, ExportData>> data, DayEnum dayEnum, int rowCount) {
        if (data.get(dayEnum).isEmpty()) {
            rowCount = 4;
        }
        return rowCount;
    }

    private void setNumbers(int groupCount, PdfPTable table, DayEnum dayEnum) {
        if (dayEnum.equals(DayEnum.Poniedzialek)) {
            addGroupNumbers(table, groupCount);
        }
    }

    private PdfPCell getCell(String content) {
        return new PdfPCell(new Phrase(content, getFont()));
    }

    private void addGroupNumbers(PdfPTable table, int groupCount) {
        addEmptyCells(table);
        for (int i = 1; i <= groupCount; i++) {
            PdfPCell groupColumn = new PdfPCell(new Phrase(i + "", getFont()));
            groupColumn.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(groupColumn);
        }
    }

    private Font getFont() {
        return FontFactory.getFont(FontFactory.TIMES_ROMAN, 5);
    }

    private void addEmptyCells(PdfPTable table) {
        addEmptyCell(table);
        addEmptyCell(table);
    }

    private void addEmptyCell(PdfPTable table) {
        PdfPCell emptyCell = new PdfPCell(new Phrase("", getFont()));
        table.addCell(emptyCell);
    }

    private PdfPTable prepareTable() throws DocumentException {
        PdfPTable table = new PdfPTable(columnCount);

        table.setWidths(template.getColumnWidths());
        table.setWidthPercentage(100);
        table.setSpacingBefore(0f);
        table.setSpacingAfter(0f);
        return table;
    }

    private void setColumnCount(int groupCount) {
        columnCount = 2 + groupCount;
    }

    private void prepareFile() throws DocumentException, FileNotFoundException {
        try {
            File file = new File("D:/WorkSpace/schedule_planner/schedule_planner/SchedulePlanner/plan.pdf");
            file.setWritable(true);
            file.delete();
        } catch (Exception e) {

        }
        PdfWriter.getInstance(document, new FileOutputStream("D:/WorkSpace/schedule_planner/schedule_planner/SchedulePlanner/plan.pdf"));
    }

    private Set<DayEnum> getDays() {
        Set<DayEnum> dayEnumSet = new LinkedHashSet<>();
        dayEnumSet.add(DayEnum.Poniedzialek);
        dayEnumSet.add(DayEnum.Wtorek);
        dayEnumSet.add(DayEnum.Sroda);
        dayEnumSet.add(DayEnum.Czwartek);
        dayEnumSet.add(DayEnum.Piatek);
        return dayEnumSet;
    }

    private PdfPCell getHeader() {
        PdfPCell tableHeader = new PdfPCell(new Phrase("WYDZIAŁ TELEKOMUNIKACJI INFORMATYKI I ELEKTROTECHNIKI\n" +
                "kierunek  TELEINFORMATYKA\n" +
                " 1  ROK  semestr II(lato) rok akademicki 2015/2016\n" +
                "STUDIA  STACJONARNE  PIERWSZEGO STOPNIA"));
        tableHeader.setColspan(columnCount);
        tableHeader.setRowspan(4);
        tableHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableHeader.setPadding(5.0f);
        tableHeader.setBackgroundColor(Template.tableHeaderColor);
        return tableHeader;
    }

    private PdfPCell getDayRow(String name) {
        PdfPCell day = new PdfPCell(new Phrase(name, getFont()));
        day.setColspan(columnCount);
        day.setHorizontalAlignment(Element.ALIGN_CENTER);
        day.setBackgroundColor(Template.dayHeaderColor);
        return day;
    }


    public Template getTemplate() {
        return template;
    }
}

class Cell {

    private String content;

    private boolean draw = true;

    private int rowSpan = 1;

    private int colSpan = 1;

    public Cell(String content) {
        this.content = content;
    }

    public Cell(boolean draw) {
        this.draw = draw;
    }

    public int getRowSpan() {
        return rowSpan;
    }

    public void setRowSpan(int rowSpan) {
        this.rowSpan = rowSpan;
    }

    public int getColSpan() {
        return colSpan;
    }

    public void setColSpan(int colSpan) {
        this.colSpan = colSpan;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDraw() {
        return draw;
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }
}
