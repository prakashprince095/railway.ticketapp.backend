package com.sparx.railway.ticketapp.backend.util;

import com.sparx.railway.ticketapp.backend.entities.PassangerDetailEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component
public class DocumentUtil {

    Logger logger = LoggerFactory.getLogger(DocumentUtil.class);

    public void generateBookinTicketPDF(String pnrNo, List<PassangerDetailEntity> passangerDetailList, ByteArrayOutputStream outputStream) throws DocumentException, IOException {
        // Creating the Object of Document
        logger.info("the method for generating the pdf document has started executing");
        Document document = new Document(PageSize.A4);
        // Getting instance of PdfWriter
        PdfWriter.getInstance(document, outputStream);
        // Opening the created document to change it
        document.open();
        // Creating font
        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(20);
        // Creating paragraph
        Paragraph paragraph1 = new Paragraph("Seat No list ", fontTiltle);
        // Aligning the paragraph in the document
        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
        // Adding the created paragraph in the document
        document.add(paragraph1);
        // Creating a table with 5 columns
        PdfPTable table = new PdfPTable(5);  // Changed from 4 to 5 columns
        // Setting width of the table, its columns, and spacing
        table.setWidthPercentage(100f);
        table.setWidths(new int[]{2, 2, 2, 2, 2});  // Updated for 5 columns (adjusted proportions)
        table.setSpacingBefore(5);

        // Create Table Cells for the table header
        PdfPCell cell = new PdfPCell();
        // Setting the background color and padding of the table cell
        cell.setBackgroundColor(CMYKColor.BLUE);
        cell.setPadding(5);

        // Creating font for the header
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);

        // Adding headings for the 5 columns
        cell.setPhrase(new Phrase("PNR No", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Passanger Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Age", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Seat No", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Coach No", font));  // Added 5th column header
        table.addCell(cell);

        // Iterating the list of passengers and adding data
        for (PassangerDetailEntity passangerDetail : passangerDetailList) {
            // Adding passenger details to each row
            table.addCell(pnrNo);  // Add PNR No
            table.addCell(passangerDetail.getPassangerName());  // Add Passenger Name
            table.addCell(String.valueOf(passangerDetail.getAge()));  // Add Age
            table.addCell(passangerDetail.getSeatNo());  // Add Seat No
            table.addCell(passangerDetail.getCoachNo());  // Add Coach No (5th column)
        }

         // Adding the created table to the document
        document.add(table);
        // Closing the document
        document.close();

    }
}
