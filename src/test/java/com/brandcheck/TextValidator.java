package com.brandcheck;

public class TextValidator {
    public static boolean hasOnlyOneSpaceBetweenWords(String text) {
        return text.trim().matches("^(\\S+\\s)*\\S+$");
    }
}