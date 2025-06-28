package com.brandcheck;

import java.util.ArrayList;
import java.util.List;


public class IssueDescriber {

    public static String describeIssue(ValidationResult result) {
        List<String> issues = new ArrayList<>();

        String tag = result.getTag();
        String text = result.getText();
        String font = result.getFont();
        String fontSize = result.getFontSize();
        String fontWeight = result.getFontWeight();

        if (text.trim().isEmpty()) issues.add("Text is empty");

        if (!result.isSpacingOK()) issues.add("Spacing issue");
        if (!result.isSpellingOK()) issues.add("Spelling mistake");

        if ((tag.equalsIgnoreCase("h1") || tag.equalsIgnoreCase("h2") || tag.equalsIgnoreCase("h3")) &&
                !font.toLowerCase().contains("montserrat")) {
            issues.add("Wrong font for heading");
        }

        if (tag.equalsIgnoreCase("p")) {
            if (!font.toLowerCase().contains("segoe ui")) {
                issues.add("Wrong font for paragraph");
            }
            if (!fontSize.equals("14px")) {
                issues.add("Wrong paragraph font size");
            }
        }

        return issues.isEmpty() ? "No issues" : String.join("; ", issues);
    }
}
