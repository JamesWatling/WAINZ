package application;

import images.TaggableImage;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class ImagePdfExporter {
	
	private static PdfWriter writer;
	private Document document;
	
	private final int incidentImageWidth = 450;
	private final int incidentImageHeight = 337; //4:3 aspect ratio

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
			addReportImage(img);
			addImageDescription(description);
			document.close();
			
			File pdf = new File(filename);
			Desktop.getDesktop().open(pdf);
		} catch (DocumentException e) {
			System.out.println("Error generating PDF Document");
		} catch (FileNotFoundException e) {
			System.out.println("Error creating PDF file");
		} catch (IOException e) {
			System.out.println("Error loading PDF to view");
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
			
			//scale the image down the the appropriate sizes
			float imageWidth = docImg.getWidth();
			float imageHeight = docImg.getHeight();
			float drawWidthPercent, drawHeightPercent = 0;
			double aspectRatio = imageWidth / (1.0 * imageHeight);
			
			System.out.println("width, height: " + imageWidth + ", " + imageHeight);
			System.out.println(aspectRatio);
			
			//check image is bigger than max size so we need to scale it
			if (aspectRatio > 1 && imageWidth > incidentImageWidth) { 	 
				drawWidthPercent = incidentImageWidth / imageWidth;
				docImg.scalePercent(drawWidthPercent * 100);
				//double check the height
				//if (docImg.getScaledHeight() > incidentImageHeight) {
				//	drawHeightPercent = incidentImageHeight / docImg.getScaledHeight();
				//	docImg.scalePercent(drawHeightPercent * 100);
				//}
			} else if (imageHeight > incidentImageHeight) { 
				drawHeightPercent = incidentImageHeight / imageHeight;
				docImg.scalePercent(drawHeightPercent * 100);
				//double check the width
				if (docImg.getScaledWidth() > incidentImageWidth) {
					drawWidthPercent = incidentImageWidth / docImg.getScaledWidth();
					docImg.scalePercent(drawWidthPercent * 100);
				}
			}
			
			float imageRenderedWidth = docImg.getScaledWidth();
			//now want to center the horizontally and vertically on the doc
			int docWidth = 595; //at 72 dpi
			float drawX = (docWidth - imageRenderedWidth)/2.0f;			
			docImg.setAbsolutePosition(drawX, 350f);
			
			document.add(docImg);
		} catch (IOException e) {
			throw new DocumentException();
		}
	}
	
	public void addImageDescription(String description) throws DocumentException {
		Paragraph imageDesc = new Paragraph();
		addEmptyLine(imageDesc, 26);
		imageDesc.add("Description of Incident: ");
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
			new ImagePdfExporter(filename, img, description);
		}
		System.exit(0);
	}
}
