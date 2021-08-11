package com.mindhub.homebanking.models;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.mindhub.homebanking.dtos.TransactionDTO;

import static org.hibernate.id.uuid.Helper.format;

public class TransactionPDFExporter {

    private List<Transaction>listTransactions;
    public TransactionPDFExporter(List<Transaction> listTransactions) {

        this.listTransactions = listTransactions;
    }

    private void writeTableHeader(PdfPTable table) {

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(4);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);

        font.setColor(Color.white);

        cell.setPhrase(new Phrase("Date", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Transaction Type", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Amount", font));
        table.addCell(cell);


    }


    private void writeTableData(PdfPTable table) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for(Transaction transaction:listTransactions){
            table.addCell(transaction.getDate().format(formatter));
            table.addCell(transaction.getDescription());
            table.addCell(String.valueOf(transaction.getTransactionType()));
            table.addCell((String.format(String.valueOf(transaction.getAmount()), "$0,0.00")));


        }

}


    public void usePDFExport(HttpServletResponse response) throws DocumentException, IOException {
        var doc= new Document(PageSize.A4);
        PdfWriter.getInstance(doc, response.getOutputStream());
        doc.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA);

        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Transactions", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        doc.add(p);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {5.0f, 4.5f, 1.0f, 4.0f});
        table.setSpacingBefore(10);


        writeTableData(table);

        doc.add(table);

        doc.close();



    }


}