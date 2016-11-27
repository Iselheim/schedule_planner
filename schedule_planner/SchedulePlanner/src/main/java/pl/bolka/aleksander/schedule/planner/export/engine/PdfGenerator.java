package pl.bolka.aleksander.schedule.planner.export.engine;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import pl.bolka.aleksander.schedule.planner.export.data.ExportData;
import pl.bolka.aleksander.schedule.planner.export.template.Template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by Aleksander on 2016-10-22.
 */
public class PdfGenerator {

    private Document document;

    private Template template;

    public PdfGenerator(Template template) {
        this.template = template;
    }

    public void generate(List<ExportData> data, int groupCount) throws IOException, DocumentException {
        document = new Document();
        try {
            File file = new File("D:/WorkSpace/schedule_planner/schedule_planner/SchedulePlanner/plan.pdf");
            file.setWritable(true);
            file.delete();
        } catch (Exception e) {

        }
        PdfWriter.getInstance(document, new FileOutputStream("D:/WorkSpace/schedule_planner/schedule_planner/SchedulePlanner/plan.pdf"));
        document.open();

        PdfPTable table = new PdfPTable(2 + groupCount);

        table.setWidths(template.getColumnWidths());
        table.setWidthPercentage(100);
        table.setSpacingBefore(0f);
        table.setSpacingAfter(0f);

        // first row
        PdfPCell tableHeader = new PdfPCell(new Phrase("WYDZIAŁ TELEKOMUNIKACJI INFORMATYKI I ELEKTROTECHNIKI\n" +
                "kierunek  TELEINFORMATYKA\n" +
                " 1  ROK  semestr II(lato) rok akademicki 2015/2016\n" +
                "STUDIA  STACJONARNE  PIERWSZEGO STOPNIA"));
        tableHeader.setColspan(2 + groupCount);
        tableHeader.setRowspan(4);
        tableHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableHeader.setPadding(5.0f);
        tableHeader.setBackgroundColor(Template.tableHeaderColor);
        table.addCell(tableHeader);

        Set<DayEnum> dayEnumSet = new HashSet<>();

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 8; j++) {
                PdfPCell pdfPCell;
                if (j == 0) {
                    pdfPCell = new PdfPCell(new Phrase("" + i % 10));
                } else {
                    pdfPCell = new PdfPCell(new Phrase(" "));
                }
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setPadding(5.0f);
                pdfPCell.setBackgroundColor(Template.tableHeaderColor);
                if (i == 5 && j == 3) {
                    pdfPCell.setColspan(3);
                    pdfPCell.setRowspan(3);
                }
                table.addCell(pdfPCell);
            }
        }


        document.add(table);
        document.close();
    }


    public Template getTemplate() {
        return template;
    }
}

enum DayEnum {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}
