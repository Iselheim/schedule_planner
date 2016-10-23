package pl.bolka.aleksander.schedule.planner.export.template;

/**
 * Created by Aleksander on 2016-10-22.
 */
public class ColumnDefinition {

    private String header;

    private DocumentAttributes documentAttributes;

    public DocumentAttributes getDocumentAttributes() {
        return documentAttributes;
    }

    public void setDocumentAttributes(DocumentAttributes documentAttributes) {
        this.documentAttributes = documentAttributes;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

}
