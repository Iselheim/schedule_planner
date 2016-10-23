package pl.bolka.aleksander.schedule.planner.export.engine;

import org.junit.Before;
import org.junit.Test;
import pl.bolka.aleksander.schedule.planner.export.data.ExportData;
import pl.bolka.aleksander.schedule.planner.export.template.TemplateImpl;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;


/**
 * Created by Aleksander on 2016-10-22.
 */
public class PdfGeneratorTest {

    private PdfGenerator generator;

    @Before
    public void init() {
        generator = new PdfGenerator(mock(TemplateImpl.class));
    }

    @Test
    public void isThereAnyContent() throws IOException {
        ExportData data = new ExportData();
        InputStream file = generator.generate(data);
        assertThat(file, notNullValue());
        assertThat(file.available(), is(greaterThan(0)));
    }

    @Test
    public void testSetTemplate() {
        assertThat(generator.getTemplate(),notNullValue());
        assertThat(generator.getTemplate().getColumnDefinitions(), notNullValue());
        assertThat(generator.getTemplate().getRowsDefinitions(), notNullValue());
        assertThat(generator.getTemplate().getColumnDefinitions(), is(not(empty())));
        assertThat(generator.getTemplate().getRowsDefinitions(), is(not(empty())));
    }

}