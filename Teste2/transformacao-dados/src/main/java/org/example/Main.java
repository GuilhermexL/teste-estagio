package org.example;

import org.example.service.PDFDownload;
import org.example.service.PDFProcessor;
import org.example.service.ZipCreator;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {

            // Obter arquivo por meio do link
            String pdfUrl = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf";

            // Fluxograma do projeto principal
            File pdf = PDFDownload.downloadPDF(pdfUrl);
            File csv = PDFProcessor.convertToCSV(pdf);
            File zip = ZipCreator.createZip(csv);

            // Resultados
            System.out.println("\nProcesso concluído!");
            System.out.println("PDF baixado em: " + pdf.getPath());
            System.out.println("CSV gerado em: " + csv.getPath());
            System.out.println("ZIP criado em: " + zip.getPath());

        } catch (Exception e) {
            System.err.println("\nOcorreu um erro:");
            e.printStackTrace();
            System.err.println("\nDica: Verifique se o link do PDF está válido!");
        }
    }
}