package org.example;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Iniciando Web Scraping...");

            // Extrair links do PDF
            List<String> pdfUrls = PDFDownload.extractPdfLinks();

            // Baixar os arquivos
            List<String> downloadedFiles = PDFDownload.downloadPdfs(pdfUrls);

            // Compactando arquivos em um .zip
            ZipCreator.createZip(downloadedFiles, "anexos_I_II.zip");

            System.out.println("Arquivos baixados e compactados com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}