package gui;
import images.TaggableImage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ImageGridPanel extends JPanel {

	private static final Dimension gridPanelSize = new Dimension(100, 100);
	private List<TaggableImage> images;
	private TaggableImage selectedImage;

	public ImageGridPanel(List<TaggableImage> imageList) {
		//TODO fill with actual images
		images = imageList;
		setLayout(new GridLayout(0, 2));
		initialise();
	}
	
	public void setImageList(List<TaggableImage> newImageList) {
		images = newImageList;
	}

	public void initialise() {
		//trash the existing contents of the image panel if there
		//are already images (on a reload)
		removeAll();
		
		Debug: System.out.println("initialise imageGrid");
		ImageThumbPanel itp;
		if(images != null){
			for(TaggableImage timg: images){
				for (int j = 0; j < 30; j++) {
					Debug: System.out.println("adding image to grid");
					itp = new ImageThumbPanel(timg, gridPanelSize);
					add(itp);
				}
			}
		}
		revalidate();
		repaint();
	}
}
