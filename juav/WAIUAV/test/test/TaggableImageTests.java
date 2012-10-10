package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import images.TaggableImage;

import org.junit.Test;

public class TaggableImageTests {

	
	
	 @Test
	    public void testImportNullImage() {
	    	
		 	images.TaggableImage image;
	    	
			try {
				image = new images.TaggableImage(new File("some invalid location"));
				assertTrue(image.getImage() == null);
			} catch (Exception e) {
			}    	
	    }
	    
	 
	   @Test
	    public void testImportValidImage() {
	    	
		   	images.TaggableImage image;
	    	
			try {
				image = new images.TaggableImage(new File("lib/about.jpg"));
				assertFalse(image.getImage() == null);
			} catch (Exception e) {
			}   	
	    }
	 
	 
}
