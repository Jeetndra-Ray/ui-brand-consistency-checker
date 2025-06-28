package com.brandcheck;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlReportExporter {

    public static void exportToHTML(List<ValidationResult> results, String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath);

        writer.write("<html><head><title>Branding Validation Report</title>");
        writer.write("<style>");
        writer.write("table { border-collapse: collapse; width: 100%; font-family: Arial; }");
        writer.write("th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }");
        writer.write("th { background-color: #f2f2f2; }");
        writer.write(".pass { background-color: #d4edda; }");
        writer.write(".fail { background-color: #f8d7da; }");
        writer.write("</style></head><body>");
        writer.write("<h2>Branding Validation Report</h2>");
        writer.write("<table>");
        writer.write("<tr><th>URL</th><th>Tag</th><th>Text</th><th>Spacing OK</th><th>Spelling OK</th><th>Font</th><th>Font Size</th><th>Font Weight</th></tr>");

        for (ValidationResult result : results) {
            writer.write("<tr>");
            writer.write("<td>" + result.getUrl() + "</td>");
            writer.write("<td>" + result.getTag() + "</td>");
            writer.write("<td>" + result.getText() + "</td>");
            writer.write("<td class='" + (result.isSpacingOK() ? "pass" : "fail") + "'>" + result.isSpacingOK() + "</td>");
            writer.write("<td class='" + (result.isSpellingOK() ? "pass" : "fail") + "'>" + result.isSpellingOK() + "</td>");
            writer.write("<td>" + result.getFont() + "</td>");
            writer.write("<td>" + result.getFontSize() + "</td>");
            writer.write("<td>" + result.getFontWeight() + "</td>");
            writer.write("</tr>");
        }

        writer.write("</table></body></html>");
        writer.close();
    }
}
