package com.fcamara.parking.api.services;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.springframework.core.io.InputStreamResource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

public abstract class AbstractReportService {

    public InputStreamResource generateReport(String title, List<String> columnHeaders, List<Map<String, String>> rows, Map<String, Long> summary) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(outStream);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph(title));

            float[] columnWidths = new float[columnHeaders.size()];
            for (int i = 0; i < columnHeaders.size(); i++) {
                columnWidths[i] = 150F;
            }
            Table table = new Table(columnWidths);

            for (String header : columnHeaders) {
                table.addCell(header);
            }

            for (Map<String, String> row : rows) {
                for (String header : columnHeaders) {
                    table.addCell(row.get(header));
                }
            }

            document.add(table);

            document.add(new Paragraph("\nResumo:"));
            for (Map.Entry<String, Long> entry : summary.entrySet()) {
                document.add(new Paragraph(entry.getKey() + ": " + entry.getValue()));
            }

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new InputStreamResource(new ByteArrayInputStream(outStream.toByteArray()));
    }
}