package Webassistent.utils;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

/**
 * Special utils for creating tables, texts etc as html elements.
 */
public class HtmlCreatorUtils {

    /**
     * Creates a table.
     *
     * @param headings        list with headings
     * @param rowsWithEntries list with lists. Each list contains the row entries
     * @param tableHeader     the header of the table
     * @param tableWidth      the width of the table in percent
     * @return the html object as document
     */
    public static Document createTable(List<String> headings, List<List<String>> rowsWithEntries, String tableHeader, int tableWidth) {
        Document doc = Document.createShell("");
        if (!tableHeader.isEmpty()) {
            doc.body().appendElement("h1").addClass("animated fadeInDown").text(tableHeader);
        }
        Element table = doc.body().appendElement("table").addClass("table table-bordered animated fadeInDown").attr("style", "width:" + tableWidth + "%");
        Element tableHeaderBeginning = table.appendElement("tr");
        for (String header : headings) {
            tableHeaderBeginning.appendElement("th").text(header);
        }
        for (List<String> row : rowsWithEntries) {
            Element tableEntry = table.appendElement("tr");
            for (String entrie : row) {
                tableEntry.appendElement("td").text(entrie);
            }
        }
        return doc;
    }

    /**
     * Creates a table.
     *
     * @param headings        list with headings
     * @param rowsWithEntries list with lists. Each list contains the row entries
     * @param tableHeader     the header of the table
     * @return the html object as document
     */
    public static Document createTable(List<String> headings, List<List<String>> rowsWithEntries, String tableHeader) {
        return createTable(headings, rowsWithEntries, tableHeader, 100);
    }

    /**
     * Creates a table.
     *
     * @param headings        list with headings
     * @param rowsWithEntries list with lists. Each list contains the row entries
     * @return the html object as document
     */
    public static Document createTable(List<String> headings, List<List<String>> rowsWithEntries) {
        return createTable(headings, rowsWithEntries, "", 100);
    }

    public static Document createPanel(String title, String text, String url){
        Document doc = Document.createShell("");
        Element panel = doc.body().appendElement("div").addClass("panel panel-default animated fadeInDown");
        Element panelHeading = panel.appendElement("div").addClass("panel-heading");
        panelHeading.appendElement("h1").addClass("panel-title").text(title);
        panel.appendElement("div").addClass("panel-body").text(text);
        panel.appendElement("div").addClass("panel-footer").appendElement("a").attr("href",url).text("Link");
        return  doc;
    }
}
