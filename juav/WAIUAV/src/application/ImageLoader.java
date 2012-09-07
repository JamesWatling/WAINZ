package application;

import gui.ApplicationWindow;
import javax.swing.JFileChooser;
import java.io.File;

public class ImageLoader {
	
	JFileChooser fileChooser;
	
	public ImageLoader() {
		fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(true);
	}
	
	public File[] importImages(ApplicationWindow app) {
		fileChooser.showOpenDialog(app);
		return fileChooser.getSelectedFiles();
	}
}
