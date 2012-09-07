package gui;
import java.util.List;

import javax.swing.JPanel;


public class ImageGrid extends JPanel {
	
	private List<TaggableImage> images;
	private TaggableImage selectedImage;
	
	public ImageGrid(List<TaggableImage> images) {
		this.images = images;
	}

}
