package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;



public class LoadingImageTests {

	
	
   @Test
    public void testAddtoExistingImage(){
    	gui.ApplicationWindow appw = new gui.ApplicationWindow();
    	application.ImageLoader imageLoader = new application.ImageLoader();
    	//assertTrue(imageLoader.getSelectedFile() == null);
    	File[] files = {
    			new File("sample/test.jpg"),
    	    	new File("sample/test2.jpg")
    	};
    	imageLoader.setSelectedFiletoDefaultValue(files);
    	imageLoader.fileChooser().showOpenDialog(appw);
    	System.out.println(imageLoader.importImages(appw));
    	
    }
	   
	   
    @Test
    public void testImportNullImage() {
    	application.ImageLoader imageLoader = new application.ImageLoader();    	
    	gui.ApplicationWindow appw = new gui.ApplicationWindow();    	
    	assertEquals(imageLoader.ImportImageAction(appw), 0);
    	try{
    		imageLoader.importImages(appw);
    	}catch(Exception e){
    		assertTrue(true);
    	}
    }
	
}
