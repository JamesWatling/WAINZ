package images;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * Representation of a taggable image
 * for display in image grid
 * @author James McCann (mccannjame), 300192420
 *
 */
public class ImageThumbnail {
	
	private BufferedImage thumb;

	public ImageThumbnail(TaggableImage image, Dimension size) {
		//resize the argument image to the argument size and store
		
		thumb = (BufferedImage) image.getImage().getScaledInstance(28, 28,Image.SCALE_DEFAULT);
	}

	public BufferedImage getThumb() {
		return thumb;
	}

}
