package images;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
				//e.printStackTrace();
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
		String data = "";
		data += "<html>\n";
		data += "<table>\n";
		
		if(metadata == null)
			return "No Metadata Available";
		List<String> gpsData = new ArrayList<String>();
		List<String> generalData = new ArrayList<String>();
		
		for(Directory directory : metadata.getDirectories()) {
		    for(Tag tag : directory.getTags()) {
		    	if(
		    			tag.toString().contains("GPS") &&
		    			!tag.toString().contains("Processing Method"))
		    		gpsData.add(tag.toString().split("]")[1]);
		    	else if(
		    			!(tag.toString().contains("Thumbnail") ||
		    			tag.toString().contains("Exif"))&&
		    			(tag.toString().contains("Image Width") ||
		    			tag.toString().contains("Image Height"))
	    			)
		    		generalData.add(tag.toString().split("]")[1]);
		    }
		}
		for(int i = 0;i< ((generalData.size()>gpsData.size())?generalData.size():gpsData.size());i++){
    		if(generalData.size()>gpsData.size()){
	    		if(i<gpsData.size())
	    			data+=
	    				"<tr><td>" + gpsData.get(i) + "</td><td>"+generalData.get(i) + "</td></tr>";
	    		else
	    			data+=
	    				"<tr><td>" + "</td><td>"+generalData.get(i) + "</td></tr>";
    		}
    		else {
    			if(i<generalData.size())
	    			data+=
	    				"<tr><td>" + gpsData.get(i) + "</td><td>" + generalData.get(i) + "</td></tr>";
	    		else
	    			data+=
	    				"<tr><td>" + gpsData.get(i) + "</td><td>" + "</td></tr>";
    		}
    	}
		data += "</table>\n";
		data += "</html>\n";
		
		return data;
	}
}