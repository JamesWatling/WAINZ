package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class ImageClassifierTests {

	
	
	
	  @Test
	    public void nullImage(){
	    	application.ImageClassifier classifier = new application.ImageClassifier();	    	
	    	try {
				classifier.findRiverImage("lib/jpg");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				assertTrue(e != null);
				//This should popup saying that opencv is not installed or no valid image
			}
	    }
	  
	  
	  
	  @Test
	    public void validImage(){
	    	application.ImageClassifier classifier = new application.ImageClassifier();	    	
	    	try {
				classifier.findRiverImage("lib/about.jpg");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				assertTrue(e == null);
				//This should popup saying that there is a file permission problem with opencv 
			}
	    }
}
