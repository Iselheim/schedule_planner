package pl.bolka.aleksander.schedule.planner.export.engine;

import org.junit.Before;
import org.junit.Test;
import pl.bolka.aleksander.schedule.planner.export.data.ExportData;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;


/**
 * Created by Aleksander on 2016-10-22.
 */
public class PdfGeneratorTest {

    private PdfGenerator generator;

    @Before
    public void init(){
        generator = new PdfGenerator();
    }

    @Test
    public void isThereAnyContent() throws IOException {
        ExportData data = new ExportData();
        InputStream file = generator.generate(data);
        assertThat(file,notNullValue());
        assertThat(file.available(),is(greaterThan(0)));
    }

}