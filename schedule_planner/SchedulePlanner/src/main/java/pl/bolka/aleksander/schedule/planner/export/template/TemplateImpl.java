package pl.bolka.aleksander.schedule.planner.export.template;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Aleksander on 2016-10-23.
 */
public class TemplateImpl implements Template {



    private List<Float> columnWidths;

    public TemplateImpl(int groupCount){
        columnWidths = new LinkedList<>();
        columnWidths.add(5f);
        columnWidths.add(10f);
        for (int i = 0; i < groupCount; i++) {
            columnWidths.add(20f);
        }
    }

    @Override
    public float[] getColumnWidths() {
        float[] floats = new float[columnWidths.size()];
        int i =0;
        for (Float columnWidth : columnWidths) {
            floats[i] = columnWidth;
            i++;
        }
        return floats;
    }

    public void setColumnWidths(List<Float> columnWidths) {
        this.columnWidths = columnWidths;
    }
//    private Set<ColumnDefinition> columnDefinitions;

//    private List<RowDefinition> rowsDefinitions;

//    private PdfMargins pdfMargins;

//    public TemplateImpl(int groupsSize) {
//        columnDefinitions = new LinkedHashSet<>();
//        rowsDefinitions = new LinkedList<>();
//        pdfMargins = new PdfMargins();
//        setDefinitions(groupsSize);
//    }

//    private void setDefinitions(int groupsSize) {
//        setColumnDefinitionsForPdf(groupsSize);
//        setRowsDefinitionsForPdf();
//        setMargins();
//    }
//
//    private void setMargins() {
//        pdfMargins.setTop(10);
//        pdfMargins.setBottom(20);
//        pdfMargins.setLeft(10);
//        pdfMargins.setRight(10);
//    }
//
//    private void setColumnDefinitionsForPdf(int groupsSize) {
//        DocumentAttributes documentAttributes = new DocumentAttributes();
////        documentAttributes.setFontSize(12);
//
//        ColumnDefinition lpColumn = new ColumnDefinition();
//        lpColumn.setDocumentAttributes(documentAttributes);
//        lpColumn.setHeader("");
//        lpColumn.setWeight(ColumnDefinition.BASE_WIGHT);
//
//        columnDefinitions.add(lpColumn);
//
//        ColumnDefinition hours = new ColumnDefinition();
//        hours.setDocumentAttributes(documentAttributes);
//        hours.setHeader("");
//        hours.setWeight(ColumnDefinition.BASE_WIGHT * 3);
//
//        columnDefinitions.add(hours);
//
//        for (int i = 0; i < groupsSize; i++) {
//            ColumnDefinition columnDefinition = new ColumnDefinition();
//            columnDefinition.setDocumentAttributes(documentAttributes);
//            columnDefinitions.add(columnDefinition);
//        }
//    }
//
//    private void setRowsDefinitionsForPdf() {
//        DocumentAttributes documentAttributes = new DocumentAttributes();
////        documentAttributes.setFontSize(10);
//
//        RowDefinition dayRow= new RowDefinition();
//        dayRow.setDocumentAttributes(documentAttributes);
//        rowsDefinitions.add(dayRow);
//
//        RowDefinition columnNameRow = new RowDefinition();
//        columnNameRow.setDocumentAttributes(documentAttributes);
//        columnNameRow.setUnique(true);
//        rowsDefinitions.add(columnNameRow);
//
//        for(int row =0; row < 12; row++){
//            RowDefinition rowsDefinition = new RowDefinition();
//            rowsDefinition.setDocumentAttributes(documentAttributes);
//            rowsDefinitions.add(rowsDefinition);
//        }
//
//    }
//
//    @Override
//    public Set<ColumnDefinition> getColumnDefinitions() {
//        return columnDefinitions;
//    }
//
//    @Override
//    public List<RowDefinition> getRowsDefinitions() {
//        return rowsDefinitions;
//    }
//
//    public PdfMargins getPdfMargins() {
//        return pdfMargins;
//    }
//
//    public void setPdfMargins(PdfMargins pdfMargins) {
//        this.pdfMargins = pdfMargins;
//    }
}
