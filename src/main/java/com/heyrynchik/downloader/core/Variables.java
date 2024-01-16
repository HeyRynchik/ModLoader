package com.heyrynchik.downloader.core;

import com.heyrynchik.connect.DB.core.DocumentManager;
import com.heyrynchik.connect.DB.parse.UrlExtractor;

import java.net.URL;
import java.util.ArrayList;

import static com.heyrynchik.connect.DB.core.Variables.*;

public class Variables {
    public static volatile ArrayList<URL> urlList = (ArrayList<URL>) UrlExtractor.extractUrls(new DocumentManager(getCollection(), getDatabase()).getDocument(getTitle()));
}