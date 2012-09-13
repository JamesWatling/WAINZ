package gui;
import images.ImageTag;
import images.TaggableImage;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class ImageGridPanel extends JPanel implements MouseListener{

	private static final long serialVersionUID = 1L;
	private static final Dimension gridPanelSize = new Dimension(100, 100);
	private List<TaggableImage> images;
	private List<ImageThumbPanel> imageThumbPanels;
	private TaggableImage selectedImage;
	private ApplicationWindow window;
	private Canvas canvas;
	//private TaggableImage selectedImage;

	public ImageGridPanel(List<TaggableImage> imageList, ApplicationWindow window) {
		this.window = window;
		this.canvas = window.getMainCanvas();
		if(imageList == null || imageList.size()==0)
			selectedImage=null;
		else
			selectedImage = imageList.get(0);
		imageThumbPanels = new ArrayList<ImageThumbPanel>();
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
		
		
		//Debug:
		System.out.println("initialise imageGrid");
		ImageThumbPanel itp;
		if(images != null){
			imageThumbPanels.clear();
			for(TaggableImage timg: images){
				itp = new ImageThumbPanel(timg, gridPanelSize);
				imageThumbPanels.add(itp);
				itp.addMouseListener(this);
				add(itp);
			}
			if(images.size()<10){
				System.out.println("growler");
				for(int i = images.size();i<10;i++){
					itp = new ImageThumbPanel(null, gridPanelSize);
					imageThumbPanels.add(itp);
					add(itp);
				}
			}
		}
		revalidate();
		repaint();
	}

	public void mouseClicked(MouseEvent e) {
		for(ImageThumbPanel i : imageThumbPanels){
			i.setSelected(false);
			i.imageLabel().setBorder(null);
		}
		ImageThumbPanel clickedThumb = (ImageThumbPanel)e.getSource();
		clickedThumb.setSelected(true);
		clickedThumb.imageLabel().setBorder(BorderFactory.createLineBorder(Color.red, 3));
		setSelectedImage(clickedThumb.getImage());
		window.setFlagButton(clickedThumb.getImage().getTag()==ImageTag.INFRINGEMENT);
		canvas.repaint();
	}
	private void setSelectedImage(TaggableImage image) {selectedImage = image;}
	public TaggableImage getSelectedImage(){return selectedImage;}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
