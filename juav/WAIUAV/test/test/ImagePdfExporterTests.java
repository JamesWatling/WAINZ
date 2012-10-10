package test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import images.TaggableImage;
import org.junit.Test;

public class ImagePdfExporterTests {


    @Test
    public void testImportNullImage() {
    	
    	application.ImagePdfExporter pdfexporter;
		try {
			pdfexporter = new application.ImagePdfExporter("Test", new TaggableImage (null), "Test2");
			assertTrue(pdfexporter == null);
		} catch (Exception e) {
		}    	
    }
    
    
    @Test
    public void testImportValidImage() {
    	
    	application.ImagePdfExporter pdfexporter;
		try {
			pdfexporter = new application.ImagePdfExporter("Test", new TaggableImage (new File("lib/about.jpg")), "Test2");
			assertFalse(pdfexporter == null);
		} catch (Exception e) {
		}    	
    }
    
    
    @Test
    public void testPresenceOfPdfViewer() {
    	
    	application.ImagePdfExporter pdfexporter = null;
    	
    	  if (Desktop.isDesktopSupported()) {
    		    try {
    		        File manual = new File("lib/manual.pdf");
    		        Desktop.getDesktop().open(manual);
    		    } catch (IOException ex) {
    		        System.out.println("no application registered for PDFs");
    		        JOptionPane prompt = new JOptionPane("No Pdf viewer available");
    		        prompt.setVisible(true);
    		        assertFalse(pdfexporter == null);
    		    }
    		    
    		}
    	  assertTrue(pdfexporter == null);
    }
    
  

}
