package pl.bolka.aleksander.schedule.planner.export.engine;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import pl.bolka.aleksander.schedule.planner.export.data.ExportData;
import pl.bolka.aleksander.schedule.planner.export.template.Template;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Aleksander on 2016-10-22.
 */
public class PdfGenerator {

    private PDDocument document = new PDDocument();

    private Template template;

    public PdfGenerator(Template template) {
        this.template = template;
    }

    public InputStream generate(ExportData data) throws IOException {
        PDPageContentStream pdPageContentStream = getPdPageContentStream();
        pdPageContentStream.close();
        InputStream is = getFinalInputStream();
        return is;
    }

    private InputStream getFinalInputStream() throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        document.save(os);
        document.close();
        return new ByteArrayInputStream(os.toByteArray());
    }

    private PDPageContentStream getPdPageContentStream() throws IOException {
        PDPage page = new PDPage();
        document.addPage(page);
        return new PDPageContentStream(document, page);
    }

    public Template getTemplate() {
        return template;
    }
}
