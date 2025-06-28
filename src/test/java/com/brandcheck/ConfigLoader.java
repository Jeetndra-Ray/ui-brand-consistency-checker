package com.brandcheck;

import java.io.*;
import java.util.*;

public class ConfigLoader {
    public static List<String> getUrls() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("config.properties");
        prop.load(fis);
        return Arrays.asList(prop.getProperty("urls").split(","));
    }
}