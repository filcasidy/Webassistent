package Webassistent.utils;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
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
     * @param borderless      should the table be borderless
     * @return the html object as document
     */
    public static Document createTable(List<String> headings, List<List<String>> rowsWithEntries, String tableHeader, int tableWidth, boolean borderless) {
        Document doc = Document.createShell("");
        if (!tableHeader.isEmpty()) {
            doc.body().appendElement("h1").addClass("animated fadeInDown").text(tableHeader);
        }
        Element table = doc.body().appendElement("table").addClass("table animated fadeInDown").attr("style",
                "width:" + tableWidth + "% ; table-layout: fixed");
        if (!borderless) {
            table.addClass("table-bordered");
        }
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
     * @param borderless      should the table be borderless
     * @return the html object as document
     */
    public static Document createTable(List<String> headings, List<List<String>> rowsWithEntries, String tableHeader, boolean borderless) {
        return createTable(headings, rowsWithEntries, tableHeader, 100, borderless);
    }

    /**
     * Creates a table.
     *
     * @param headings        list with headings
     * @param rowsWithEntries list with lists. Each list contains the row entries
     * @param borderless      should the table be borderless
     * @return the html object as document
     */
    public static Document createTable(List<String> headings, List<List<String>> rowsWithEntries, boolean borderless) {
        return createTable(headings, rowsWithEntries, "", 100, borderless);
    }

    /**
     * Creates a list with values which are needed for example for table heading or table row.
     *
     * @param informationValues values
     * @return {@link List} with given information values
     */
    public static List<String> createListWithInformationValues(String... informationValues) {
        List<String> listWithInformationValues = new ArrayList<>();
        for (int i = 0; i < informationValues.length; i++) {
            listWithInformationValues.add(informationValues[i]);
        }
        return listWithInformationValues;
    }


    public static Document createPanel(String title) {
        Document doc = Document.createShell("");
        Element panel = doc.body().appendElement("div").addClass("panel panel-default animated fadeInDown");
        panel.attr("id", "panel");
        Element panelHeading = panel.appendElement("div").addClass("panel-heading");
        panelHeading.appendElement("h1").addClass("panel-title").appendText(title);
        Element panelBody = panel.appendElement("div").addClass("panel-body");
        panelBody.attr("id", "panelBody");
        return doc;
    }

    /**
     * Creates a simple panel with title and text.
     *
     * @param title title of the panel
     * @param text  text in the panel
     * @return the html object as document
     */
    public static Document createPanel(String title, String text) {
        Document doc = createPanel(title);
        doc.getElementById("panelBody").text(text);
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
     * @param title  title of the panel
     * @param text   text in the panel
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
