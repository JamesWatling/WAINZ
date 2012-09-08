package images;
import java.awt.image.BufferedImage;

/**
 * Class containing an image and an associated tag
 * of the type ImageTag
 * Images can be tagged as UNTAGGED, NONINFRINGEMENT or INFRINGMENT
 * @author James McCann (mccannjame), 300192420
 */

public class TaggableImage {
	
	private BufferedImage image;
	private String fileName;
	private ImageTag tag;
	
	/**
	 * Constructs a new TaggableImage with an untagged status
	 * @param image - image stored with this taggable
	 */
	public TaggableImage(BufferedImage image, String file){
		this.image = image;
		tag = ImageTag.UNTAGGED;
		fileName = file;
	}
	
	public void setTag(ImageTag newTag) {
		tag = newTag;
	}
	
	public ImageTag getTag() { return tag; }
	public BufferedImage getImage() { return image; }
}
