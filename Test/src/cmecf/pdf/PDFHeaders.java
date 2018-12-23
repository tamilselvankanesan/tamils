package cmecf.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.itextpdf.io.IOException;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.PdfException;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfObject;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfStream;
import com.itextpdf.kernel.pdf.PdfVersion;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.filespec.PdfFileSpec;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Link;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;


/**
 * Adds PDF headers in the margins of the given PDF. The result returned by
 * <code>getResult()</code> is the path to another PDF file in the tmp
 * directory. Errors are written to stdout but are not thrown. If no header was
 * successfully added the path to the input file is returned as the result. By
 * default no transcript header is added unless
 * <code>addTranscriptHeader(true)</code> is called.
 * <p>
 * PDF Manipulation routines are modified samples of code by:<br>
 * -- Copyright 2002 by Bruno Lowagie --
 * </p>
 * @author Dovev Hefetz. <code>addHeader()</code> is the core of this class and was written
 * by Long Nguyen.
 */
public class PDFHeaders extends PDFModify {

  private Color   color;
  private int     offset;
  private String  position;
  private String  header = "";
  private boolean addTranscriptHeader = false;

    public static final String[] FONTS = {
        "C:\\Tamil\\WinSCP_Docs\\cmjb\\LiberationSans-Regular.ttf"
    };

  /**
   * The transcript header that is added to the top of the PDF if specified
   * using setTranscriptHeader().
   *
   */
  final String TRANSCRIPT_HEADER = "AVAILABLE AT PUBLIC TERMINAL FOR VIEWING ONLY";


  /**
   * Creates a new <code>PDFHeaders</code> instance.
   *
   * @param color the header color
   * @param position top or bottom
   * @param offset how much to offset the header from the edge of the document
   */
  public PDFHeaders(Color color, String position, int offset) {
    this.color    = color;
    this.position = position;
    this.offset   = offset;
  }


  /**
   * Instructs addHeaders() to add the specified header to the PDF.
   *
   * @param header the header to add to the PDF.
   */
  public void setHeader(String header) {
    this.header = header;
  }


  /**
   * Tells addHeaders() whether to add a transcript header to the PDF. Without
   * any call to this function, a transcript header will not be added.
   *
   * @param b a <code>boolean</code> value
   */
  public void setTranscriptHeader(boolean b) {
    addTranscriptHeader = b;
  }


  /**
   * Calls addHeader for each header requested to the PDF
   *
   */
  public void addHeaders() {

    if (addTranscriptHeader) {
      String originalPosition = this.position;
      try {
        this.position = "top";
        addHeader(TRANSCRIPT_HEADER, 'T');

        // We successfully added the transcript header to the top, so
        // position the other header on the bottom if there is one.
        this.position = "bottom";
      }
      catch (Exception e) {
        this.position = originalPosition;
        e.printStackTrace();
      }
    }

    // assume a length of 1 means a single space snuck in by mistake
    if (header.length() > 1) {
      try {
        addHeader(header, 'H');
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }


  /**
   * Adds the specified header to the PDF. The result is placed in a new PDF
   * file whose name includes the current state as described in
   * <code>PreparePDFForDisplay</code>.
   *
   * @param rawheader the text of the header to add
   * @param state the character representing the current state
   * @exception Exception if an error occurs
   * @see PreparePDFForDisplay
   */
  private void addHeader(String rawheader, char state) throws Exception {
    startStep(state);
    
    String TmpFirstLine = new String();
    String FirstLine    = new String();
    Paragraph pFirstLine = new Paragraph();
    String SecondLine   = new String();
    Paragraph pSecondLine = new Paragraph();
    

    // we create a reader for a certain document
    PdfDocument pdfDoc = new PdfDocument(new PdfReader(inputFile) , new PdfWriter(outputFile));
    Document doc = new Document(pdfDoc);
    
    try {
      // we retrieve the total number of pages
      int n = pdfDoc.getNumberOfPages(); // original page numbers

      //pattern match: replace <pagetotal> with the actual number of pages
      String pattTotal = "<pagetotal>";
      Pattern rTotal = Pattern.compile(pattTotal);
      Matcher mTotal = rTotal.matcher( rawheader );
      String header = mTotal.replaceAll( Integer.toString(n) );

      // we add content
      int i = 0;
      int p = 0;
      PdfCanvas canvas = null;
      Rectangle pageSize;
      while (i < n) {
        p++;
        i++;

        // we retrieve (and set) the size of this page
        PdfPage page = pdfDoc.getPage(i);
        System.out.println("Stream count -> "+page.getContentStreamCount());
        page.getFirstContentStream();
        pageSize = page.getPageSize();
        canvas = new PdfCanvas(page);
        Rectangle thisPageSize = page.getPageSizeWithRotation();
        float thisPageHeight = thisPageSize.getHeight();
        float thisPageBottom = thisPageSize.getBottom();
                
        PdfFont pf = new PdfFontFactory().createFont(FONTS[0], PdfEncodings.CP1252, true);
//        pf.setSubset(true);

        //pattern match: replace <pagenum> with the actual page number
        String patt = "<pagenum>";
        Pattern r = Pattern.compile(patt);
        Matcher m = r.matcher( header );
        String finalheader = m.replaceAll(Integer.toString(p));
        Paragraph pFinalHeader = new Paragraph(finalheader);
        setParagraphFont(pFinalHeader, pf, color, 12);

        // We are going to make the text appear wrapped.
        int StringLength = finalheader.length();
        if (StringLength > 90) {
          TmpFirstLine = finalheader.substring(0,90);
          int LastSpaceInFirstLine = TmpFirstLine.lastIndexOf(' ');
          FirstLine = finalheader.substring(0,LastSpaceInFirstLine);
          pFirstLine = new Paragraph(FirstLine);
          setParagraphFont(pFirstLine, pf, color, 12);
          SecondLine = finalheader.substring(LastSpaceInFirstLine,StringLength);
          pSecondLine = new Paragraph(SecondLine);
          setParagraphFont(pSecondLine, pf, color, 12);
        }

        float textposition = 0;

        if ( position.equals("bottom") ) {
          // fixes a scanner bug
          try {
            Rectangle thisBoxSize = page.getCropBox();
            if ( thisBoxSize.getBottom() > thisPageSize.getBottom() ) {
              thisPageBottom = thisBoxSize.getBottom();
            }
          } catch (Exception de) {
            // de.printStackTrace();
          }
          textposition = thisPageBottom + offset;
        } else {
          //the default is to put the header at the top
          textposition = thisPageBottom + thisPageHeight - offset;
        }

        if (StringLength > 90) { 
          doc.showTextAligned(pFirstLine, thisPageSize.getWidth() / 2, textposition, i, TextAlignment.CENTER, VerticalAlignment.BOTTOM, 0);
          doc.showTextAligned(pSecondLine, thisPageSize.getWidth() / 2, textposition - 12, i, TextAlignment.CENTER, VerticalAlignment.BOTTOM, 0);
//          canvas.beginText().setFontAndSize(pf, 12).moveText(thisPageSize.getWidth() / 2-7, textposition).showText(FirstLine).endText();
//          canvas.beginText().setFontAndSize(pf, 12).moveText( thisPageSize.getWidth() / 2, textposition).showText("I want to believe").endText();
//          canvas.saveState();
        } else {
          doc.showTextAligned(pFinalHeader, thisPageSize.getWidth() / 2, textposition, i, TextAlignment.CENTER, VerticalAlignment.BOTTOM, 0);
        }
      }
      // step 5: we close the document
      doc.close();
      endStep();
    } catch (Exception e) {
      if (doc != null) {
        try {
          doc.close();
        } catch (Exception ex) {
          System.out.print("PDFHeaders: unable to close the document: ");
          ex.printStackTrace();
        }
      }
      stepBack();
      throw e;
    }
    }


  /**
   * <code>convertTo_1_4</code> converts a PDF to 1.4 specs if its
   * original version is later than 1.4. The results of the conversion are
   * written to a file in tmp and that filename is returned. If the document's
   * version is 1.4 or earlier it does nothing, and the given input filename
   * is returned.

   * @param infile The path to the PDF file
   * @return the infile if nothing was done; the name of the new temp file
   * otherwise.
   * @exception IOException if an error occurs
   * @exception FileNotFoundException if an error occurs
   * @exception DocumentException if an error occurs
   */
  public static String convertTo_1_4(String infile) throws IOException, FileNotFoundException {

    int pdfVersion = getPDFVersion(infile);
    if (pdfVersion > 1) {
      return convertTo1Dot4Spec(infile);
    }
    return infile;
  }
  
  private static int getPDFVersion(String infile){
    try(PdfReader reader = new PdfReader(infile); PdfDocument pdfDoc = new PdfDocument(reader);) {
      return convertVersion(pdfDoc.getPdfVersion().toString());
    }catch (Exception e) {
      System.out.println("Unable to get the PDF version number for the file "+infile);
      e.printStackTrace();
    }
    return 0;
  }
  
  private static String convertTo1Dot4Spec(String infile) {
    try {
      File aFile = File.createTempFile("pdf", ".tmp", new File( "/tmp/" ));
      String outfile = aFile.getAbsolutePath();
      WriterProperties props = new WriterProperties().setPdfVersion(PdfVersion.PDF_1_4);
      try (PdfReader reader = new PdfReader(infile);
          PdfWriter writer = new PdfWriter(new FileOutputStream(outfile), props);
          PdfDocument pdfDoc = new PdfDocument(reader, writer);) {
        pdfDoc.close();
      }
      return outfile;
    }
    catch (Exception e) {
      System.out.print("Error trying to convert PDF: ");
      e.printStackTrace();
    }
    return infile;
  }
  
  /*  On iText7.* the function getPdfVersion() will return a static PDFVersion value follow by the format PDF_*_* ("*" stands for the specific version like PDF_1_4, PDF_2_0)
  /*  This function will covert the PDFVersion's String to a corresponding int value. For expamle: PDF_1_4 will return 1, and PDF_1_5 or PDF_2_0 will return 2.
  /*
   * 
   */
  public static int convertVersion(String version){
    String[] str = version.split("\\-");
    float ver = Float.parseFloat(str[str.length - 1]);
    int retVer = (int)(ver + 0.5);
    System.out.println(retVer);
    return retVer;
  }
  
  public static void setParagraphFont(Paragraph p, PdfFont pf, Color color, float fontSize){
    p.setFont(pf)
     .setFontColor(color)
     .setFontSize(fontSize);
  }
  public static void main(String[] args) throws IOException, FileNotFoundException, java.io.IOException {
    String header = "Case: 7:18-bk-01849-TAH  Doc #: 3  Filed: 11/05/18  Desc: Main Document   Page <pagenum> of <pagetotal> Entered 11/05/18 10:58:23 Filed: 11/05/18";
    boolean transcript = false;
    String doc = "C:\\Tamil\\WinSCP_Docs\\cmjb\\cmjb_live.pdf";
    PDFHeaders headers = new PDFHeaders(new DeviceRgb(0,0,255), "top", 25);
    headers.setDocument(doc);
    headers.setTranscriptHeader(transcript);
    headers.setHeader(header);
//    headers.addHeaders();
//    System.out.println(headers.getOutputFile());
    
//      headers.add();
      System.out.println(headers.getOutputFile());
//    File file = new File(doc);
//    file.getParentFile().mkdirs();
//    headers.manipulatePdf("C:\\Tamil\\WinSCP_Docs\\cmjb\\cmjb_live.pdf", "C:\\Tamil\\WinSCP_Docs\\cmjb\\stream%s");
    
//    File file = new File("C:\\Tamil\\WinSCP_Docs\\cmjb");
//    file.getParentFile().mkdirs();
    headers.javascript("C:\\Tamil\\WinSCP_Docs\\cmjb\\cmjb_liv2e.pdf");
//    headers.gotoAction("C:\\Tamil\\WinSCP_Docs\\cmjb\\cmjb_liv2e.pdf");
  }
  
  public void add() throws FileNotFoundException, java.io.IOException{
    startStep('H');
    PdfDocument pdf = new PdfDocument(new PdfReader(inputFile) , new PdfWriter(outputFile));
    
    PdfPage page1 = pdf.getFirstPage();
    
    PdfCanvas pdfCanvas = new PdfCanvas(page1);
    Rectangle rectangle = new Rectangle(36, 650, 100, 100);
    pdfCanvas.rectangle(rectangle);
    pdfCanvas.stroke();
    
    
    PdfCanvas pdfCanvas1 = new PdfCanvas(
        page1.newContentStreamBefore(), page1.getResources(), pdf);
    rectangle = new Rectangle(100, 700, 100, 100);
    pdfCanvas1.saveState()
            .setFillColor(Color.CYAN)
            .rectangle(rectangle)
            .fill()
            .restoreState();
    Canvas canvas = new Canvas(pdfCanvas1, pdf, rectangle);
    canvas.add(new Paragraph("Dr. Jekyll and Mr. Hyde"));
    canvas.close();
    System.out.println();
  }
  
  public void manipulatePdf(String src, String dest) throws IOException, FileNotFoundException, java.io.IOException {
    PdfDocument pdfDoc = new PdfDocument(new PdfReader(new FileInputStream(src)));
    PdfObject obj;
    for (int i = 1; i <= pdfDoc.getNumberOfPdfObjects(); i++) {
        obj = pdfDoc.getPdfObject(i);
        if (obj != null && obj.isStream()) {
             byte[] b;
             try {
                  b = ((PdfStream) obj).getBytes();
             } catch (PdfException exc) {
                  b = ((PdfStream)obj).getBytes(false);
             }
             FileOutputStream fos = new FileOutputStream(String.format(dest, i));
             fos.write(b);
             fos.close();
         }
    }
    pdfDoc.close();
}
  
  
  public void javascript(String dest) throws IOException, FileNotFoundException {
    PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
    pdf.getCatalog().setOpenAction(PdfAction.createJavaScript("this.print(true);"));
    Document document = new Document(pdf);
    Link link = new Link("here", PdfAction.createJavaScript("app.alert('Boo!');"));
    Paragraph p = new Paragraph().add("Click ").add(link.setFontColor(Color.BLUE)).add(" if you want to be scared.");
    document.add(p);
    document.close();
}
  private void gotoAction(String dest) throws IOException, FileNotFoundException {
    PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
    Document doc = new Document(pdfDoc);
    PdfFileSpec spec = PdfFileSpec.createExternalFileSpec(pdfDoc, "C:\\Tamil\\WinSCP_Docs\\cmjb\\cmjb_liv11e.txt", true);
    PdfAction action = PdfAction.createLaunch(spec);
    Paragraph p = new Paragraph(new Link("Click to open test.txt", action));
    doc.add(p);
    doc.close();
  }
}