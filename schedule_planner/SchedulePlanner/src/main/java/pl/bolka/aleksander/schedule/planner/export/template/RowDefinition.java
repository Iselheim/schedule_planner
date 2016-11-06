package pl.bolka.aleksander.schedule.planner.export.template;

/**
 * Created by Aleksander on 2016-10-22.
 */
public class RowDefinition {

    private String rowName;

    private DocumentAttributes documentAttributes;

    private boolean unique;

    public RowDefinition(){
        unique = false;
    }

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public DocumentAttributes getDocumentAttributes() {
        return documentAttributes;
    }

    public void setDocumentAttributes(DocumentAttributes documentAttributes) {
        this.documentAttributes = documentAttributes;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public boolean isUnique() {
        return unique;
    }

}
