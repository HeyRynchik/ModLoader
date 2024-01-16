package com.heyrynchik.downloader.core;

import com.heyrynchik.modloader;
import net.minecraft.client.Minecraft;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Downloader {

    protected ArrayList<URL> urlList;
    protected ArrayList<String> installedFiles = new ArrayList<>();


    public Downloader(List<URL> urlList) throws IOException {
        this.urlList = (ArrayList<URL>) urlList;
        init();
    }

    protected void init() throws IOException {
    }

    public boolean IsAlreadyInstalled(String fileName) throws IOException {
        return Files.exists(Path.of(fileName));
    }

    public void download() {
        try {
            for (URL url : urlList) {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_MOVED_TEMP) {
                    String newLocation = connection.getHeaderField("Location");
                    connection = (HttpURLConnection) new URL(newLocation).openConnection();
                }

                String suggestedFileName = getFileNameFromUrl(connection.getURL());
                Path savePath = findSavePath(suggestedFileName);

                if (!(IsAlreadyInstalled(savePath.toString()))) {
                    try (BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
                         FileOutputStream out = new FileOutputStream(savePath.toString())) {

                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = in.read(buffer)) != -1) {
                            out.write(buffer, 0, bytesRead);
                        }
                        modloader.LOGGER.info(savePath.toAbsolutePath() + " has been installed");
                    }
                }
//                Minecraft.getInstance().destroy(); // To activate mods
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getFileNameFromUrl(URL url) {
        String urlString = url.toString();
        return urlString.substring(urlString.lastIndexOf('/') + 1);
    }

    public boolean cancel() {
        return false;
    }


    private Path findSavePath(String fileName) throws IOException {
        Path modsFolder = Path.of("mods");
        if (Files.exists(modsFolder)) {
            return modsFolder.resolve(fileName);
        } else {
            Path parentFolder = modsFolder.getParent();
            if (parentFolder != null && Files.exists(parentFolder)) {
                return parentFolder.resolve("mods").resolve(fileName);
            } else {
                throw new FileNotFoundException("Mods folder not found!");
            }
        }
    }

}