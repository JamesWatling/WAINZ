package application;

import images.TaggableImage;

import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ImagePdfExporter {
	
	private Document document;

	/**
	 * Setup the document to be exported, add some default
	 * content/metadata
	 */
	public ImagePdfExporter(String filename, TaggableImage img, String description) {
		document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(filename));
			document.open();
			addMetaData();
			addReportImage(img);
			addImageDescription(description);
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
			waiLogo.setAbsolutePosition(25f, 25f);
			document.add(waiLogo);
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
	
	/**
	 * Generates a PDF with static data
	 * To be used for testing and layout changes
	 */
	public static void main(String[] args) {
		String filename = System.getProperty("user.home") + "/wainz-pdf-export.pdf";
		
	}
}
