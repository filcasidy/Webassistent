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

    /**
     * Creates a simple panel with title and text.
     *
     * @param title title of the panel
     * @param text  text in the panel
     * @return the html object as document
     */
    public static Document createPanel(String title, String text) {
        Document doc = Document.createShell("");
        Element panel = doc.body().appendElement("div").addClass("panel panel-default animated fadeInDown");
        panel.attr("id", "panel");
        Element panelHeading = panel.appendElement("div").addClass("panel-heading");
        panelHeading.appendElement("h1").addClass("panel-title").text(title);
        Element panelBody = panel.appendElement("div").addClass("panel-body").text(text);
        panelBody.attr("id", "panelBody");
        return doc;
    }

    /**
     * Creates a simple panel with title,footer and text.
     *
     * @param title title of the panel
     * @param text  text in the panel
     * @param url   the url which will be added in the footer
     * @return the html object as document
     */
    public static Document createPanel(String title, String text, String url) {
        Document doc = createPanel(title, text);
        doc.getElementById("panel").appendElement("div").addClass("panel-footer").appendElement("a").attr("href", url).text("Link");
        return doc;
    }


    /**
     * Creates a simple panel with title, text and a list of points.
     *
     * @param title        title of the panel
     * @param text         text in the panel
     * @param points list with points
     * @return the html object as document
     */
    public static Document createPanelWithListOfPoints(String title, String text, List<String> points) {
        Document doc = createPanel(title, text);
        Element bulletPointsTable = doc.getElementById("panelBody").appendElement("dl");
        for (String command : points) {
            bulletPointsTable.appendElement("dd").attr("style", "font-weight: bold").text(command);
        }
        return doc;
    }
}
