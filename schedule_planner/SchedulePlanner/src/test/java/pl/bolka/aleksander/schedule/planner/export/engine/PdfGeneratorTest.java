package pl.bolka.aleksander.schedule.planner.export.engine;

import com.itextpdf.text.DocumentException;
import org.junit.Before;
import org.junit.Test;
import pl.bolka.aleksander.schedule.planner.export.template.Template;
import pl.bolka.aleksander.schedule.planner.export.template.TemplateImpl;

import java.io.IOException;
import java.util.LinkedList;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;


/**
 * Created by Aleksander on 2016-10-22.
 */
public class PdfGeneratorTest {

    private PdfGenerator generator;

    @Before
    public void init() {
        Template template = new TemplateImpl(6);
        generator = new PdfGenerator(template);
    }
//
//    @Test
//    public void isThereAnyContent() throws IOException {
//        ExportData data = new ExportData();
//        InputStream file = generator.generate(data);
//        assertThat(file, notNullValue());
//        assertThat(file.available(), is(greaterThan(0)));
//    }

    @Test
    public void testFile() throws IOException, DocumentException {
        generator.generate(new LinkedList<>(),6);
    }

    @Test
    public void testSetTemplate() {
        assertThat(generator.getTemplate(), notNullValue());

    }

}