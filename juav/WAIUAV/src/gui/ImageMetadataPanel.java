package gui;

import images.TaggableImage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class ImageMetadataPanel extends JPanel {
	
	private ApplicationWindow parent;
	private Dimension size;
	private TaggableImage currentImage;
	private JLabel metaDataLabel;
	
	private static BufferedImage PLACEHOLDER_IMAGE;
	private static JLabel placeHolderLabel;
	
	public ImageMetadataPanel(ApplicationWindow parent, Dimension size) {
		this.parent = parent;
		this.size = size;
		loadPlaceholder();
	}
	
	public TaggableImage getCurrentImage() { return currentImage; }
	public void setCurrentImage(TaggableImage img) { currentImage = img; }
	public void setMetaDataLabelText(String text) { metaDataLabel = new JLabel(text); }
	
	public void initialise() {
		setPreferredSize(size);
		setMaximumSize(size);
		setBackground(new Color(153, 157, 158));
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		placeHolderLabel = new JLabel(new ImageIcon(PLACEHOLDER_IMAGE.getScaledInstance(size.width, 
					size.height, Image.SCALE_FAST)));
	}
	
	public void paintComponent(Graphics g) {
		removeAll();
		if(currentImage ==  null) {
			//draw the placeholder
			add(placeHolderLabel);
		} else {
			//draw image metadata
			add(metaDataLabel);
		}
	}
	
	imageMetadataPanel.setLayout(new BorderLayout());
	imageMetadataPanel.setSize(IMAGE_METADATA_PANEL_SIZE);
	metaDataLabel.setSize(2*IMAGE_METADATA_PANEL_SIZE.width/3,IMAGE_METADATA_PANEL_SIZE.height);
	metaDataLabel.setVerticalAlignment(JLabel.TOP);
	imageMetadataPanel.add(metaDataLabel, BorderLayout.WEST);
	imageMetadataPanel.setBackground(null);
	
    
		try {
			String latitudeS = metaDataLabel.getText().split("GPS Latitude - ", 2)[1].split("</td>", 2)[0];
			String longitudeS = metaDataLabel.getText().split("GPS Longitude - ")[1].split("</td>",2)[0];
			
			Double latitudeNum1 = Double.parseDouble(latitudeS.split((char) 0x00B0+"", 2)[0]);
			Double latitudeNum2 = Double.parseDouble(latitudeS.split((char) 0x00B0+"", 2)[1].split("' ", 2)[0]);
			Double latitudeNum3 = Double.parseDouble(latitudeS.split("' ", 2)[1].split("\"", 2)[0]);
			
			Double longitudeNum1 = Double.parseDouble(longitudeS.split((char) 0x00B0+"", 2)[0]);
			Double longitudeNum2 = Double.parseDouble(longitudeS.split((char) 0x00B0+"", 2)[1].split("' ", 2)[0]);
			Double longitudeNum3 = Double.parseDouble(longitudeS.split("' ", 2)[1].split("\"", 2)[0]);
			
			Double latitude; 
			Double longitude;
			
			if(latitudeNum1>=0)
				latitude = latitudeNum1+latitudeNum2/60+latitudeNum3/3600;
			else 
				latitude = latitudeNum1-latitudeNum2/60-latitudeNum3/3600;
			
			if(longitudeNum1>=0)
				longitude= longitudeNum1+longitudeNum2/60+longitudeNum3/3600;
			else
				longitude= longitudeNum1-longitudeNum2/60-longitudeNum3/3600;
			
			URLConnection con = null;
			if(noConnection );
			else if(useProxy)
				con = new URL("http",proxyUrl,proxyPort,"http://maps.google.com/maps/api/staticmap?" +
					"center="+latitude+",%20"+longitude +
					"&zoom=7&size="
					+IMAGE_METADATA_PANEL_SIZE.width/3+"x"+
					IMAGE_METADATA_PANEL_SIZE.height+
					"&maptype=roadmap&sensor=false&" +
					"markers=||"+latitude+",%20"+longitude).openConnection();
			else
				con = new URL("http://maps.google.com/maps/api/staticmap?" +
						"center="+latitude+",%20"+longitude +
						"&zoom=7&size="
						+IMAGE_METADATA_PANEL_SIZE.width/3+"x"+
						IMAGE_METADATA_PANEL_SIZE.height+
						"&maptype=roadmap&sensor=false&" +
						"markers=||"+latitude+",%20"+longitude).openConnection();
			InputStream is = con.getInputStream();
			byte bytes[] = new byte[con.getContentLength()];
			Toolkit tk = getToolkit();
			BufferedImage map = ImageIO.read(is);
			tk.prepareImage(map, -1, -1, null);
			imageMetadataPanel.add(new JLabel(new ImageIcon(map)),BorderLayout.EAST);
		} catch (IOException e1) {
	    	//e1.printStackTrace();
	    	JLabel errorLabel = new JLabel("<html><p>No Internet connection</p></html>");
	    	errorLabel.setVerticalAlignment(JLabel.TOP);
			imageMetadataPanel.add(errorLabel,BorderLayout.EAST);
			noConnection = true;
		} catch (NullPointerException e1) {
	    	//e1.printStackTrace();
	    	JLabel errorLabel = new JLabel("<html><p>No Internet connection</p></html>");
	    	errorLabel.setVerticalAlignment(JLabel.TOP);
			imageMetadataPanel.add(errorLabel,BorderLayout.EAST);
		} catch (Exception e1){
			//e1.printStackTrace();
	    	JLabel errorLabel = new JLabel("<html><p>No Internet connection </p><p>or no valid metadata</p></html>");
	    	errorLabel.setVerticalAlignment(JLabel.TOP);
			imageMetadataPanel.add(errorLabel,BorderLayout.EAST);
		}
	
	public static void loadPlaceholder() {
		String metadataPlaceholderPath = "lib/metadata-panel-default.png";
		try {
			PLACEHOLDER_IMAGE = ImageIO.read(new File(metadataPlaceholderPath));
		} catch (IOException e) { e.printStackTrace(); }
	}

}
