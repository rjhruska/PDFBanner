# PDFBanner
Open source banner creation code using iText libraries

PBFBanner is a java program to create batch print page separators.  It can create a legal size blank page separator,
          or a letter size banner page with the batch, timestamp, page range and number of documents.  The output is
          not intended for publication and is for temporary use only.  The legal page separators are reused
          and the banner page is recycled once the batch proceeded.

Sample Usage:
java pdfbanner [pdfFile] [pageSize] [duplexFlag] [queueName] [bannerType] [minPage] [maxPage] [pageCount]

1) The code was compiled with JDK jdk_32_1.8.0_66.

2) Libraries:

   ..\com\itextpdf
   ..\ch\qos

3) Templates are used and can contain water marks or other common content.

   LegalTemplate.pdf
   LetterTemplate.pdf

4) Compile command: javac PDFBanner.java

5) Run PDFBanner:

   java PDFBanner C:\TMP\BATCH01_BLANK.pdf LEGAL  DUPLEX DEV_BATCH01 BLANK 1 7 314
   java PDFBanner C:\TMP\BATCH01_BEGIN.pdf LETTER DUPLEX DEV_BATCH01 BEGIN 1 7 314
   
6) Output will created in the filename passed in the first parameter (full path names can be used).
