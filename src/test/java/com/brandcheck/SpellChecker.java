package com.brandcheck;

import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.List;

public class SpellChecker {
    private static final JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());

    public static List<RuleMatch> checkSpelling(String text) throws IOException {
        return langTool.check(text);
    }
}