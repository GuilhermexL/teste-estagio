package org.example.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class PDFDownload {
    public static File downloadPDF(String fileUrl) throws IOException {
        // Cria diretório se não existir
        new File("downloads").mkdirs();

        // Define o local de salvamento
        File destination = new File("downloads/Anexo_I.pdf");

        System.out.println("Baixando arquivo...");
        URL url = new URL(fileUrl);
        Files.copy(url.openStream(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

        return destination;
    }
}