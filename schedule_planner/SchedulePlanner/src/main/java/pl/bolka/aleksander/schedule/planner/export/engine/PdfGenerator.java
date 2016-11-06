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

import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by Aleksander on 2016-10-22.
 */
public class PdfGenerator {

    private Document document;

    private Template template;

    public PdfGenerator(Template template) {
        this.template = template;
    }

    public void generate(ExportData data) throws IOException, DocumentException {
        document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("D:/WorkSpace/schedule_planner/schedule_planner/SchedulePlanner/plan.pdf"));
        document.open();

        PdfPTable table = new PdfPTable(data.getGroupCount());

        table.setWidths(template.getColumnWidths());
        table.setWidthPercentage(100);
        table.setSpacingBefore(0f);
        table.setSpacingAfter(0f);

        // first row
        PdfPCell tableHeader = new PdfPCell(new Phrase("WYDZIAŁ TELEKOMUNIKACJI INFORMATYKI I ELEKTROTECHNIKI\n" +
                "kierunek  TELEINFORMATYKA\n" +
                " 1  ROK  semestr II(lato) rok akademicki 2015/2016\n" +
                "STUDIA  STACJONARNE  PIERWSZEGO STOPNIA"));
        tableHeader.setColspan(data.getGroupCount());
        tableHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableHeader.setPadding(5.0f);
        tableHeader.setBackgroundColor(Template.tableHeaderColor);
        table.addCell(tableHeader);


        document.add(table);
        document.close();
    }


    public Template getTemplate() {
        return template;
    }
}
