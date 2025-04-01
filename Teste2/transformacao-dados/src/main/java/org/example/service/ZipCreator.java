package org.example.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCreator {
    public static File createZip(File fileToZip) throws IOException {

        // Colocando meu nome no arquivo Zip
        String seuNome = "Guilherme";
        File zipFile = new File("output/Teste_" + seuNome + ".zip");

        try (FileOutputStream fos = new FileOutputStream(zipFile);
             ZipOutputStream zos = new ZipOutputStream(fos);
             FileInputStream fis = new FileInputStream(fileToZip)) {

            zos.putNextEntry(new ZipEntry(fileToZip.getName()));

            // Evitando carregamento arquivos grande na memória
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }
        }

        return zipFile;
    }
}