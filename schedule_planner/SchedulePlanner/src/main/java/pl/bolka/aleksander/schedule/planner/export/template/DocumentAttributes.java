package pl.bolka.aleksander.schedule.planner.export.template;

import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * Created by Aleksander on 2016-10-22.
 */
public class DocumentAttributes {

    private static final PDFont font = PDType1Font.TIMES_ROMAN;

    private int fontSize;

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public static PDFont getFont() {
        return font;
    }
}
