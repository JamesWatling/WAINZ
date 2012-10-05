package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;



public class LoadingImageTest {

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
