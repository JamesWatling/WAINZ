package gui;

import images.ImageTag;
import images.ImageThumbnail;
import images.TaggableImage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel to render a thumbnail image with corresponding filename shown below
 * Also displays the tag of the image
 * @author mccannjame
 * jm 080912
 */
public class ImageThumbPanel extends JPanel {
	
	private static final String flag_overlay = "flag20.png";
	
	private TaggableImage image;
	private ImageTag tag; 
	private String fileName;
	private boolean selected;

	public ImageThumbPanel(TaggableImage ti, Dimension dim) {
		image = ti;//new ImageThumbnail(ti, new Dimension(dim.width-10, dim.height-20));
		//tag = ti.getTag();
		fileName = "testing.jpg"; //TODO replace with actual filenames jm 080512
		initLayout(dim);
	}
	
	public void initLayout(Dimension dim) {
		setPreferredSize(dim);
		setSize(dim);
		setLayout(new FlowLayout());
		
		JLabel imageLabel = new JLabel(new ImageIcon(image.getImage()));
		imageLabel.setPreferredSize(new Dimension(dim.width-10, dim.height-20));
		imageLabel.setSize(new Dimension(dim.width-10, dim.height-20)); 
		add(imageLabel);
		
		//add string and tag below image
		JLabel nameLabel = new JLabel();
		nameLabel.setText(fileName);
		add(nameLabel);
	}
	
	public void toggleSelected() { selected = !selected; }

}
