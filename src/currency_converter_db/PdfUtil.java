package currency_converter_db;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/** PDF output built with iText 5 (the Preview buttons). */
public final class PdfUtil {

    private static final BaseColor BLUE = new BaseColor(0, 82, 147);
    private static final BaseColor STRIPE = new BaseColor(234, 241, 248);

    private PdfUtil() { }

    private static File newFile(String name) {
        File dir = new File("reports");
        if (!dir.exists()) dir.mkdirs();
        String stamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(dir, name.replaceAll("[^A-Za-z0-9]+", "_") + "_" + stamp + ".pdf");
    }

    private static void heading(Document doc, String title) throws Exception {
        Paragraph p = new Paragraph("Currency Converter System",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BLUE));
        p.setAlignment(Element.ALIGN_CENTER);
        doc.add(p);
        Paragraph t = new Paragraph(title, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13));
        t.setAlignment(Element.ALIGN_CENTER);
        doc.add(t);
        String who = Session.username == null ? "-" : Session.username;
        Paragraph m = new Paragraph("Generated " + new SimpleDateFormat("dd MMM yyyy, HH:mm").format(new Date())
                + "   |   User: " + who, FontFactory.getFont(FontFactory.HELVETICA, 9, BaseColor.GRAY));
        m.setAlignment(Element.ALIGN_CENTER);
        m.setSpacingAfter(12f);
        doc.add(m);
    }

    /** Full table report (landscape A4). Returns the file or null on failure. */
    public static File tableReport(String title, String[] headers, List<String[]> rows) {
        try {
            File f = newFile(title);
            Document doc = new Document(PageSize.A4.rotate(), 30, 30, 30, 30);
            PdfWriter.getInstance(doc, new FileOutputStream(f));
            doc.open();
            heading(doc, title + " Report");

            PdfPTable table = new PdfPTable(headers.length);
            table.setWidthPercentage(100);
            table.setHeaderRows(1);
            Font hf = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9, BaseColor.WHITE);
            for (String h : headers) {
                PdfPCell c = new PdfPCell(new Phrase(h, hf));
                c.setBackgroundColor(BLUE);
                c.setPadding(5f);
                table.addCell(c);
            }
            Font bf = FontFactory.getFont(FontFactory.HELVETICA, 8);
            int i = 0;
            for (String[] r : rows) {
                for (String v : r) {
                    PdfPCell c = new PdfPCell(new Phrase(v == null ? "" : v, bf));
                    c.setPadding(4f);
                    if (i % 2 == 1) c.setBackgroundColor(STRIPE);
                    table.addCell(c);
                }
                i++;
            }
            doc.add(table);
            Paragraph total = new Paragraph("Total records: " + rows.size(),
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9));
            total.setSpacingBefore(10f);
            doc.add(total);
            doc.close();
            open(f);
            return f;
        } catch (Exception e) {
            AppLog.error("PDF report failed", e);
            JOptionPane.showMessageDialog(null, "Could not create PDF: " + e.getMessage(),
                    "PDF error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /** Small two-column receipt (label / value) used by the converter. */
    public static File receipt(String title, List<String[]> lines) {
        try {
            File f = newFile(title);
            Document doc = new Document(PageSize.A5, 36, 36, 36, 36);
            PdfWriter.getInstance(doc, new FileOutputStream(f));
            doc.open();
            heading(doc, title);
            PdfPTable table = new PdfPTable(new float[]{40f, 60f});
            table.setWidthPercentage(100);
            Font lf = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
            Font vf = FontFactory.getFont(FontFactory.HELVETICA, 10);
            for (String[] l : lines) {
                PdfPCell a = new PdfPCell(new Phrase(l[0], lf));
                a.setBackgroundColor(STRIPE);
                a.setPadding(6f);
                PdfPCell b = new PdfPCell(new Phrase(l[1], vf));
                b.setPadding(6f);
                table.addCell(a);
                table.addCell(b);
            }
            doc.add(table);
            doc.close();
            open(f);
            return f;
        } catch (Exception e) {
            AppLog.error("PDF receipt failed", e);
            JOptionPane.showMessageDialog(null, "Could not create PDF: " + e.getMessage(),
                    "PDF error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private static void open(File f) {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                Desktop.getDesktop().open(f);
                return;
            }
        } catch (Exception ignored) { }
        JOptionPane.showMessageDialog(null, "PDF saved to:\n" + f.getAbsolutePath());
    }
}
