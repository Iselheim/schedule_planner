package pl.bolka.aleksander.schedule.planner.export.template;

/**
 * Created by Aleksander on 2016-10-22.
 */
public class ColumnDefinition {

    public static final int BASE_WIGHT = 20;

    private String header;

    private DocumentAttributes documentAttributes;

    private int weight;

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

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
