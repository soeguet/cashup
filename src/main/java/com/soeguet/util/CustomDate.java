package com.soeguet.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CustomDate {

    public void saveDate(String datumString, String path) {

        try {
            File file = new File( path + "\\datum.txt");
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write(datumString.getBytes());
                fileOutputStream.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveTime(String uhrzeitString, String path) {

        System.out.println("UHRZEIT");
        try {
            File file = new File( path + "\\uhrzeit.txt");
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write(uhrzeitString.getBytes());
                fileOutputStream.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
