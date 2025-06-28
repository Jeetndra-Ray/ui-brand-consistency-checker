package com.brandcheck;

public class ValidationResult {
    private final String url;
    private final String tag;
    private final String text;
    private final boolean spacingOK;
    private final boolean spellingOK;
    private final String font;
    private final String fontSize;
    private final String fontWeight;
    private String screenshotPath;


    public ValidationResult(String url, String tag, String text,
                            boolean spacingOK, boolean spellingOK,
                            String font, String fontSize, String fontWeight) {
        this.url = url;
        this.tag = tag;
        this.text = text;
        this.spacingOK = spacingOK;
        this.spellingOK = spellingOK;
        this.font = font;
        this.fontSize = fontSize;
        this.fontWeight = fontWeight;
    }

    // ✅ Add these getter methods

    public String getUrl() {
        return url;
    }

    public String getTag() {
        return tag;
    }

    public String getText() {
        return text;
    }

    public boolean isSpacingOK() {
        return spacingOK;
    }

    public boolean isSpellingOK() {
        return spellingOK;
    }

    public String getFont() {
        return font;
    }

    public String getFontSize() {
        return fontSize;
    }

    public String getFontWeight() {
        return fontWeight;
    }

    public String getScreenshotPath() {
        return screenshotPath;
    }

    public void setScreenshotPath(String screenshotPath) {
        this.screenshotPath = screenshotPath;
    }

}
