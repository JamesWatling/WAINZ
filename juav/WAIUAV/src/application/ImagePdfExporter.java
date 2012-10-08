package application;

import images.TaggableImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class ImagePdfExporter {
	
	private static PdfWriter writer;
	private Document document;
	private Font h1 = new Font(Font.FontFamily.HELVETICA, 36, Font.BOLD);

	/**
	 * Setup the document to be exported, add some default
	 * content/metadata
	 */
	public ImagePdfExporter(String filename, TaggableImage img, String description) {
		document = new Document();
		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
			document.open();
			addMetaData();
			addDocumentHeader();
			//addReportImage(img);
			//addImageDescription(description);
			document.close();
		} catch (DocumentException e) {
			System.out.println("Error generating PDF Document");
		} catch (FileNotFoundException e) {
			System.out.println("Error creating PDF file");
		}	
	}

	public void addMetaData() {
		document.addAuthor("Water Action Initiative New Zealand");
		document.addCreator("WAINZ UAVTool");
	}
	
	public void addDocumentHeader() throws DocumentException {
		try {
			com.itextpdf.text.Image waiLogo = 
				com.itextpdf.text.Image.getInstance("lib/wai-pdf-header-logo.png");
			waiLogo.scalePercent(25);
			waiLogo.setAbsolutePosition(25f, 760f); //from lower left
			
			//Incident Report Header
			Paragraph header = new Paragraph("Incident Report", h1);
			
			document.add(waiLogo);
			absText("Incident Report", 30, true, 200, 760);
		} catch (IOException e) {
			throw new DocumentException();
		}
	}
	
	public void addReportImage(TaggableImage img) throws DocumentException {
		try {
			com.itextpdf.text.Image docImg = 
				com.itextpdf.text.Image.getInstance(img.getImage(), null);
			document.add(docImg);
		} catch (IOException e) {
			throw new DocumentException();
		}
	}
	
	public void addImageDescription(String description) throws DocumentException {
		Paragraph imageDesc = new Paragraph();
		addEmptyLine(imageDesc, 1);
		imageDesc.add(description);
		document.add(imageDesc);
	}
	

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	private void absText(String text, int size, boolean bold, int x, int y) {
		try {
			int fontSize = size;
			PdfContentByte cb = writer.getDirectContent();
			BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			cb.saveState();
			cb.beginText();
			cb.moveText(x, y);
			cb.setFontAndSize(bf, fontSize);
			cb.showText(text);
			cb.endText();
			cb.restoreState();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Generates a PDF with static data
	 * To be used for testing and layout changes
	 */
	public static void main(String[] args) {
		TaggableImage img = null;
		String filename = System.getProperty("user.home") + "/wainz-pdf-export.pdf";
		File file = new File("lib/uav-sample.jpg");
		img = new TaggableImage(file);
		String description = "WAINZ UAVTool - report for sample UAV captured image.";
		if (img!=null) {
			ImagePdfExporter export = new ImagePdfExporter(filename, img, description);
		}
		System.exit(0);
	}
}
