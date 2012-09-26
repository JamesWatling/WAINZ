package images;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

/**
 * Class containing an image and an associated tag
 * of the type ImageTag
 * Images can be tagged as UNTAGGED, NONINFRINGEMENT or INFRINGMENT
 * @author James McCann (mccannjame), 300192420
 */

public class TaggableImage {
	private File file;
	private BufferedImage image;
	private String fileName;
	private ImageTag tag;
	private Metadata metadata;
	
	/**
	 * Constructs a new TaggableImage with an untagged status
	 * @param image - image stored with this taggable
	 */
	public TaggableImage(File f){
		try {
			file = f;
			image = ImageIO.read(f);
			tag = ImageTag.UNTAGGED;
			fileName = f.getName();
			try {
				metadata = JpegMetadataReader.readMetadata(f);
			} catch(JpegProcessingException e) {}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setTag(ImageTag newTag) { tag = newTag; }
	public ImageTag getTag() { return tag; }
	public BufferedImage getImage() { return image; }
	public Image getImage(int width, int height) { return image.getScaledInstance(width, height, Image.SCALE_FAST); }

	public String getFileName() {
		return fileName;
	}
	
	public String getMetaData() {
		String data = "<html>";
		
		if(metadata==null)
			return "No Metadata";
		for (Directory directory : metadata.getDirectories()) {
		    for (Tag tag : directory.getTags()) {
		    	data += "<p>" + tag.toString() + "</p>";
		    }
		}
		
		data += "</html>";
		
		return data;
	}
	
	public File getSource() { return this.file; }
}
