package test;

import static org.junit.Assert.assertTrue;
import gui.ApplicationWindow;
import images.TaggableImage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ImageGridPanelTests {

	private List<TaggableImage> images = new ArrayList<TaggableImage> ();
	
	  @Test
	    public void nullImage(){
		  
		  	gui.ImageGridPanel gridPanel = new gui.ImageGridPanel(images, new ApplicationWindow());
		  	try{
		  		gridPanel.browse("next");
		  	}
		
		  	catch(Exception e){
		  		assertTrue(e == null);
		  	}
	    }
	  
	  @Test
	    public void validImage(){
		  
		  	gui.ImageGridPanel gridPanel = new gui.ImageGridPanel(images, new ApplicationWindow());
		  	images.add(new TaggableImage(new File("lib/about.jpg")));
		  	images.add(new TaggableImage(new File("lib/wai-default.jpg")));
		  	try{
		  		gridPanel.browse("next");
		  	}
		
		  	catch(Exception e){
		  		assertTrue(e != null);
		  	}
	    }
	  
	  
	  @Test
	    public void validImageGetPanels(){
		  
		  	gui.ImageGridPanel gridPanel = new gui.ImageGridPanel(images, new ApplicationWindow());
		  	images.add(new TaggableImage(new File("lib/about.jpg")));
		  	images.add(new TaggableImage(new File("lib/wai-default.jpg")));
		  	
		  	assertTrue(gridPanel.getPanels() != null);
		  	
	    }
	  

	  
	  
}
