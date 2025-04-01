package org.example.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PDFProcessor {
    public static File convertToCSV(File pdfFile) throws IOException {
        // Cria diretório de saída
        new File("output").mkdirs();
        File csvFile = new File("output/procedimentos.csv");

        try (PDDocument doc = PDDocument.load(pdfFile);
             FileWriter writer = new FileWriter(csvFile)) {

            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(doc);

            // Escreve cabeçalho
            writer.write("Procedimento,Tipo,Descrição\n");

            // Processa linha por linha
            for (String line : text.split("\n")) {
                if (isDataLine(line)) {
                    String[] parts = line.split("\\s{2,}");
                    if (parts.length >= 3) {
                        String tipo = translateType(parts[1]);
                        writer.write(String.format("%s,%s,%s\n",
                                parts[0], tipo, parts[2]));
                    }
                }
            }
        }

        return csvFile;
    }

    private static boolean isDataLine(String line) {
        return line.matches(".*\\d+.*") && !line.contains("Código");
    }

    private static String translateType(String abbreviation) {
        return abbreviation.equals("OD") ? "Seg. Odontológico" :
                abbreviation.equals("AMB") ? "Seg. Ambulatorial" : abbreviation;
    }
}
