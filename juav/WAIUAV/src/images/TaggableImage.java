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
 * Class containing an image and an associated tag of the type ImageTag.
 * Images can be tagged as UNTAGGED, NONINFRINGEMENT or INFRINGMENT.
 * 
 * @author James McCann (mccannjame), 300192420
 */

public class TaggableImage {
	private File file;
	private BufferedImage image;
	private String fileName;
	private ImageTag tag;
	private Metadata metadata;
	
	/**
	 * Constructs a new TaggableImage with an untagged status.
	 * @param image - image stored with this taggable
	 */
	public TaggableImage(File f) {
		try {
			file = f;
			image = ImageIO.read(f);
			fileName = f.getName();
			tag = ImageTag.UNTAGGED;
			
			try {
				metadata = JpegMetadataReader.readMetadata(f);
			} catch(JpegProcessingException e) {
				e.printStackTrace();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public File getSource() { return this.file; }
	
	public BufferedImage getImage() { return image; }
	
	public Image getImage(int width, int height) { 
		return image.getScaledInstance(width, height, Image.SCALE_FAST); 
	}
	
	public void setImage(BufferedImage image) { this.image = image; }
	
	public ImageTag getTag() { return tag; }
	
	public void setTag(ImageTag newTag) { tag = newTag; }
	
	public String getFileName() { return fileName; }
	
	public String getMetaData() {
		String data = "<html>";
		
		if(metadata == null)
			return "No Metadata Available";
		for(Directory directory : metadata.getDirectories()) {
		    for(Tag tag : directory.getTags()) {
		    	data += "<p>" + tag.toString() + "</p>";
		    }
		}
		
		data += "</html>";
		
		return data;
	}
	
	public String metadatastring(){
		javaxt.io.Image i = new javaxt.io.Image(fileName);
		return i.getGpsTags()+"";
	}
}
