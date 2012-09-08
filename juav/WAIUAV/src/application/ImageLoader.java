package application;

import gui.ApplicationWindow;
import images.TaggableImage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

public class ImageLoader {
	
	JFileChooser fileChooser;
	private File[] selectedImageFiles;
	
	public ImageLoader() {
		fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(true);
	}
	
	public List<TaggableImage> importImages(ApplicationWindow app) {
		List<TaggableImage> result = new ArrayList<TaggableImage>();
		fileChooser.showOpenDialog(app);
		selectedImageFiles = fileChooser.getSelectedFiles();
		for (File file: selectedImageFiles) {
			result.add(new TaggableImage(file));
		}
		return result;
	}
}
