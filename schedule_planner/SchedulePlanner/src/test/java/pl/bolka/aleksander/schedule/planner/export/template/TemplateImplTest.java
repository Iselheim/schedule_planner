package pl.bolka.aleksander.schedule.planner.export.template;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by Aleksander on 2016-10-23.
 */
public class TemplateImplTest {

    TemplateImpl template ;

    @Before
    public void init(){
        template = new TemplateImpl();
    }

    @Test
    public void testGetColumnsDefinitions(){
        assertThat(template.getColumnDefinitions(),notNullValue());
    }

    @Test
    public void testColumnsNotEmpty(){
        assertThat(template.getColumnDefinitions(),is(not(empty())));
    }

    @Test
    public void testGetRowsDefinitions(){
        assertThat(template.getRowsDefinitions(),notNullValue());
    }

    @Test
    public void testRowsNotEmpty(){
        assertThat(template.getRowsDefinitions(),is(not(empty())));
    }

}