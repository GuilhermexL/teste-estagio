package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCreator {

    public static void createZip(List<String> files, String zipName) throws IOException {

        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipName))) {

            for (String file : files) {
                File fileToZip = new File(file);
                try (FileInputStream fis = new FileInputStream(fileToZip)) {
                    ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                    zipOut.putNextEntry(zipEntry);

                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = fis.read(bytes)) >= 0) {
                        zipOut.write(bytes, 0, length);
                    }
                }
                // Opcional: deletar os PDFs após compactar
                fileToZip.delete();
            }
        }
        System.out.println("Arquivo ZIP criado: " + zipName);
    }
}