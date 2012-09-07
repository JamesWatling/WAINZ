package application;

import gui.ApplicationWindow;

import javax.swing.JFileChooser;

public class ImageLoader {
	
	JFileChooser fileChooser;
	
	public ImageLoader() {
		fileChooser = new JFileChooser();
	}
	
	public void importImages(ApplicationWindow app) {
		fileChooser.showOpenDialog(app);
	}
}
