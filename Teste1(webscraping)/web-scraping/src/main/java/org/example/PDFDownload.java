package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PDFDownload {
    private static final String TARGET_URL = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";

    public static List<String> extractPdfLinks() throws IOException {
        List<String> pdfLinks = new ArrayList<>();
        Document doc = Jsoup.connect(TARGET_URL).get();
        Elements links = doc.select("a[href$=.pdf]");

        for (Element link : links) {
            String title = link.text();
            if (title.contains("Anexo I") || title.contains("Anexo II")) {
                pdfLinks.add(link.attr("abs:href"));
                System.out.println("Encontrado: " + title + " (" + link.attr("abs:href") + ")");
            }
        }
        return pdfLinks;
    }

    public static List<String> downloadPdfs(List<String> urls) throws IOException {
        List<String> downloadedFiles = new ArrayList<>();

        int count = 1;
        for (String url : urls) {
            String fileName = "anexo_" + (count++) + ".pdf";
            FileUtils.copyURLToFile(new URL(url), new File(fileName));
            downloadedFiles.add(fileName);
            System.out.println("Baixado: " + fileName);
        }
        return downloadedFiles;
    }
}
