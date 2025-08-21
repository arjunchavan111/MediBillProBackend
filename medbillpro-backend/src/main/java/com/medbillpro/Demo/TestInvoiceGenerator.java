package com.medbillpro.Demo;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

public class TestInvoiceGenerator {
    public static void main(String[] args) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\dell\\OneDrive\\Desktop\\DATA\\invoice.pdf"));
            document.open();
            document.add(new Paragraph("Invoice #INV-1001"));
            document.add(new Paragraph("Customer: John Doe"));
            document.add(new Paragraph("Amount: ₹ 1,500.00"));
            document.add(new Paragraph("Date: 2025-08-13"));
            document.add(new Paragraph("Thank you for your business!"));
            document.close();
            System.out.println("Test invoice created at C:/invoices/invoice.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
