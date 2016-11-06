package pl.bolka.aleksander.schedule.planner.export.template;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by Aleksander on 2016-10-23.
 */
public class TemplateImplTest {

    TemplateImpl template;

    @Before
    public void init() {
        template = new TemplateImpl(0);
    }

    @Test
    public void testGetColumnsDefinitions() {
        assertThat(template.getColumnDefinitions(), notNullValue());
    }

    @Test
    public void testColumnsNotEmpty() {
        assertThat(template.getColumnDefinitions(), is(not(empty())));
    }

    @Test
    public void testGetRowsDefinitions() {
        assertThat(template.getRowsDefinitions(), notNullValue());
    }

    @Test
    public void testRowsNotEmpty() {
        assertThat(template.getRowsDefinitions(), is(not(empty())));
    }

    @Test
    public void testGroupNumberZero() {
        Template template = new TemplateImpl(0);
        assertThat(template.getColumnDefinitions().size(), equalTo(2));
    }

    @Test
    public void testRandomGroupNumber() {
        Template template = new TemplateImpl(1);
        assertThat(template.getColumnDefinitions().size(), equalTo(2 + 1));


        template = new TemplateImpl(3);
        assertThat(template.getColumnDefinitions().size(), equalTo(2 + 3));

        template = new TemplateImpl(6);
        assertThat(template.getColumnDefinitions().size(), equalTo(2 + 6));

        template = new TemplateImpl(123);
        assertThat(template.getColumnDefinitions().size(), equalTo(2 + 123));
    }

    @Test
    public void testRowsIsTwelve(){
        assertThat(template.getRowsDefinitions().size(),equalTo(14));
    }

    @Test
    public void testDefaultUniqueness(){
        assertThat(template.getRowsDefinitions().get(3).isUnique(),is(false));
        assertThat(template.getRowsDefinitions().get(13).isUnique(),is(false));
        assertThat(template.getRowsDefinitions().get(12).isUnique(),is(false));
        assertThat(template.getRowsDefinitions().get(7).isUnique(),is(false));
        assertThat(template.getRowsDefinitions().get(1).isUnique(),is(true));
    }

    @Test
    public void testMargins(){
        assertThat(template.getPdfMargins(),is(not(nullValue())));
        assertThat(template.getPdfMargins().getTop(),equalTo(10));
        assertThat(template.getPdfMargins().getBottom(),equalTo(20));
        assertThat(template.getPdfMargins().getRight(),equalTo(10));
        assertThat(template.getPdfMargins().getLeft(),equalTo(10));
    }

}