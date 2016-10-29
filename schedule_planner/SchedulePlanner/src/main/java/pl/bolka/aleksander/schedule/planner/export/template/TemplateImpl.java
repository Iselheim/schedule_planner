package pl.bolka.aleksander.schedule.planner.export.template;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Aleksander on 2016-10-23.
 */
public class TemplateImpl implements Template {

    private Set<ColumnDefinition> columnDefinitions;

    private List<RowDefinition> rowsDefinitions;

    public TemplateImpl(){
        columnDefinitions = new LinkedHashSet<>();
        rowsDefinitions = new LinkedList<>();
        setDefinitions();
    }

    private void setDefinitions(){
        setColumnDefinitionsForPdf();
        setRowsDefinitionsForPdf();
    }

    private void setColumnDefinitionsForPdf(){
        DocumentAttributes documentAttributes = new DocumentAttributes();
        documentAttributes.setFontSize(12);

        ColumnDefinition columnDefinition = new ColumnDefinition();
        columnDefinition.setDocumentAttributes(documentAttributes);
        columnDefinitions.add(columnDefinition);
    }

    private void setRowsDefinitionsForPdf(){
        DocumentAttributes documentAttributes = new DocumentAttributes();
        documentAttributes.setFontSize(10);

        RowDefinition rowsDefinition = new RowDefinition();
        rowsDefinition.setDocumentAttributes(documentAttributes);
        rowsDefinitions.add(rowsDefinition);
    }

    @Override
    public Set<ColumnDefinition> getColumnDefinitions() {
        return columnDefinitions;
    }

    @Override
    public List<RowDefinition> getRowsDefinitions() {
        return rowsDefinitions;
    }
}
