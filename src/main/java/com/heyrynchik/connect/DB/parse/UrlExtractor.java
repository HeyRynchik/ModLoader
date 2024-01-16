package com.heyrynchik.connect.DB.parse;

import org.bson.Document;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UrlExtractor {

    public static List<URL> extractUrls(Document document) {
        List<URL> urls = new ArrayList<>();

        // Ensure the document has the "mods" field
        if (document.containsKey("mods")) {
            Object modsObject = document.get("mods");

            // Check if "mods" is an ArrayList
            if (modsObject instanceof ArrayList) {
                ArrayList<?> modsList = (ArrayList<?>) modsObject;

                // Iterate over the elements and convert the URLs to URL objects
                for (Object modElement : modsList) {
                    if (modElement instanceof String) {
                        try {
                            URL url = new URL((String) modElement);
                            urls.add(url);
                        } catch (MalformedURLException e) {
                            // Handle the MalformedURLException as needed
                            e.printStackTrace();
                        }
                    } else {
                        // Handle other types within the ArrayList, if necessary
                        System.out.println("Unsupported type within 'mods' ArrayList: " + getType(modElement));
                    }
                }
            } else {
                // Handle other cases or log a message
                System.out.println("The 'mods' field is neither a Document nor a String nor an ArrayList.");
                System.out.println("Actual type: " + getType(modsObject));
            }
        }

        return urls;
    }

    public static String getType(Object o) {
        return (o != null) ? o.getClass().getName() : "null";
    }
}
