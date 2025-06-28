package com.brandcheck;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportExporter {

    public static void exportToCSV(List<ValidationResult> results, String fileName) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
            // Header with extra column
            String[] header = {"URL", "Tag", "Text", "Spacing OK", "Spelling OK", "Font", "Font Size", "Font Weight", "Issue Description"};
            writer.writeNext(header);

            for (ValidationResult result : results) {
                String[] row = {
                        result.getUrl(),
                        result.getTag(),
                        result.getText(),
                        String.valueOf(result.isSpacingOK()),
                        String.valueOf(result.isSpellingOK()),
                        result.getFont(),
                        result.getFontSize(),
                        result.getFontWeight(),
                        IssueDescriber.describeIssue(result) // ✅ NEW COLUMN
                };
                writer.writeNext(row);
            }

            System.out.println("✅ CSV exported with issues: " + fileName);
        }
    }
}
