package pl.bolka.aleksander.schedule.planner.export.engine;

import org.junit.Before;
import pl.bolka.aleksander.schedule.planner.export.template.Template;
import pl.bolka.aleksander.schedule.planner.export.template.TemplateImpl;


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

//    @Test
//    public void testFile() throws IOException, DocumentException {
//        Map<Position, ExportData> mapSmall = new HashMap<>();
//        Map<DayEnum, Map<Position, ExportData>> map = new HashMap<>();
//        mapSmall.put(new Position(1,2),getExportData1());
//        mapSmall.put(new Position(3,2),getExportData2());
//        mapSmall.put(new Position(0,2),getExportData0());
//        map.put(DayEnum.Poniedzialek, mapSmall);
//        map.put(DayEnum.Wtorek, mapSmall);
//        map.put(DayEnum.Sroda, mapSmall);
//        map.put(DayEnum.Czwartek, mapSmall);
//        map.put(DayEnum.Piatek, mapSmall);
//        generator.generate(map, 6);
//    }
//
//    private ExportData getExportData2() {
//        ExportData exportData = ExportData.getExportData(new Schedule());
//        exportData.setHeigt(2);
//        exportData.setWeight(2);
//        exportData.setContent("2x2");
//        return exportData;
//    }
//
//    private ExportData getExportData1() {
//        ExportData exportData = ExportData.getExportData(new Schedule());
//        exportData.setHeigt(2);
//        exportData.setContent("2x1");
//        return exportData;
//    }
//
//    private ExportData getExportData0() {
//        ExportData exportData = ExportData.getExportData(new Schedule());
//        exportData.setContent("1x1");
//        return exportData;
//    }
//
//    @Test
//    public void testSetTemplate() {
//        assertThat(generator.getTemplate(), notNullValue());
//
//    }

}