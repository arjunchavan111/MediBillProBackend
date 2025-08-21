import { Injectable } from '@angular/core';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';
import { Purchase } from '../models/purchase.model';

@Injectable({
  providedIn: 'root'
})
export class PdfGeneratorService {

  generateInvoice(purchase: Purchase): void {
    const doc = new jsPDF();

    // Header
    doc.setFontSize(18);
    doc.text('MEDBILL PRO', 10, 15);
    doc.setFontSize(10);
    doc.text('Ganpati Medical', 10, 21);
    doc.text('Email: pravinsawant101@gmail.com | Phone: +91-7875689635', 10, 27);

    // Invoice Title
    doc.setFontSize(16);
    doc.text('INVOICE', 90, 40);

    // Invoice Details
    doc.setFontSize(12);
    doc.text(`Invoice Number: INV-${purchase.purchaseId}`, 10, 50);
    doc.text(`Date: ${new Date(purchase.date).toLocaleDateString()}`, 10, 56);
    doc.text(`Due Date: ${new Date(purchase.date).toLocaleDateString()}`, 10, 62); // Optional: adjust logic
    doc.text(`Status: ${purchase.status}`, 10, 68);

    // Bill To Section
    doc.text('BILL TO:', 10, 80);
    doc.text(`${purchase.supplierName}`, 10, 86);
    doc.text(`Email: supplier@email.com`, 10, 92); // Optional: add email if available
    doc.text(`Phone: ${purchase.mobileNumber}`, 10, 98);
    doc.text(`Address: ${purchase.supplierAddress}`, 10, 104);

    // Table Columns
    const tableColumn = ["Product", "Qty", "Unit Price", "Total"];
    const tableRows: any[] = [];

    let subtotal = 0;

    purchase.products?.forEach(product => {
      const total = product.quantity * product.costPrice;
      subtotal += total;
      tableRows.push([
        product.productName,
        product.quantity.toString(),
        `Rs ${product.costPrice.toFixed(2)}`,
        `Rs ${total.toFixed(2)}`
      ]);
    });

    // Table
    autoTable(doc, {
      startY: 115,
      head: [tableColumn],
      body: tableRows,
    });

    // Summary
    const gstRate = 18;
    const gstAmount = subtotal * gstRate / 100;
    const grandTotal = subtotal + gstAmount;
    const finalY = (doc as any).lastAutoTable.finalY + 10;

    doc.text(`Subtotal: Rs ${subtotal.toFixed(2)}`, 140, finalY);
    doc.text(`Tax (${gstRate}% GST): Rs ${gstAmount.toFixed(2)}`, 140, finalY + 6);
    doc.text(`TOTAL: Rs ${grandTotal.toFixed(2)}`, 140, finalY + 12);

    // Footer
    doc.setFontSize(10);
    doc.text('Thank you for your business!', 10, finalY + 25);
    doc.text('This is a computer-generated invoice.', 10, finalY + 31);
    doc.text('Notes:', 10, finalY + 37);
    doc.text('Regular monthly prescription', 10, finalY + 43);

    // Save PDF
    doc.save(`invoice_${purchase.purchaseId}.pdf`);
  }
}
