package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.*;

public class LoadingImageTest2 {

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
}