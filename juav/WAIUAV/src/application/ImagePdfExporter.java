package application;

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
	public ImagePdfExporter(String filename, Image img, String description) {
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
	
	public void addReportImage(Image img) throws DocumentException {
		try {
			com.itextpdf.text.Image docImg = 
				com.itextpdf.text.Image.getInstance(img, null);
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
}
