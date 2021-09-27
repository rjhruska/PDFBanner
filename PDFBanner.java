import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// PBFBanner is a java program to create batch print page separators.  It can create a legal size blank page separator,
//           and a letter size banner page with the batch, timestamp, page range and number of documents.  The output is
//           not intended for publication and is for temporary use only.  The legal page separators are reused
//           and the banner page is recycled once the batch proceeded.
// Sample Usage:
// java PDFBanner [pdfFile] [pageSize] [duplexFlag] [queueName] [bannerType] [minPage] [maxPage] [pageCount]
// java PDFBanner BATCH01.pdf LETTER DUPLEX DEV_BATCH01 END 1 7 314

public class PDFBanner {

       public static void main(String[] args) throws IOException {

           String pdfFile = args[0];
           String pageSize = args[1];
           String duplexFlag = args[2];
           String queueName = args[3];
           String bannerType = args[4];
           String minPage = args[5];
           String maxPage = args[6];
           String pageCount = args[7];
           String pdfTemplate = "LetterTemplate.pdf";
           String pdfLegalTemplate = "LegalTemplate.pdf";
           //  36 units = .5 inches, in iText's world
           float pageWidth = 612; //8.5 inches
           float pageHeight = 792; //11 inches

           if (pageSize.equals("LEGAL")) {
                    pdfTemplate = pdfLegalTemplate;
                    pageHeight = 1008; //14 inches
                  }
                  PdfReader reader = new PdfReader(pdfTemplate);
                  PdfWriter writer = new PdfWriter(pdfFile);
                  PdfDocument pdfDoc = new PdfDocument(reader, writer);

                  if (duplexFlag.equals("DUPLEX")) {
                    pdfDoc.setDefaultPageSize(new com.itextpdf.kernel.geom.PageSize(pageWidth,pageHeight));
                    pdfDoc.addNewPage();
                  }
                  Document document = new Document(pdfDoc);

                  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
                  LocalDateTime now = LocalDateTime.now();

                  if (!bannerType.equals("BLANK")) {
                    document.add(new Paragraph("BANNER NAME " + Paths.get(pdfFile).getFileName().toString()));
                    document.add(new Paragraph(bannerType + " BANNER PAGE FOR " + queueName + " TIME " + dtf.format(now)));
                    document.add(new Paragraph("Page Count Group " + minPage + " to " + maxPage + " pages"));
                    if (!(pageCount.matches("0"))) {
                      document.add(new Paragraph("Containing " + pageCount + " documents"));
				    }
                  } else {
                    document.add(new Paragraph(" "));
           		  }

           document.close();

       }

}
