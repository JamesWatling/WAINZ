package application;

import gui.ApplicationWindow;
import images.TaggableImage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
/**
 * The imageLoader class is used to load the image adn show the fileChooser
 * @author AgriSoft
 *
 */
public class ImageLoader {
	private JFileChooser fileChooser;
	private File[] selectedImageFiles;

	/**
	 * Constructor of the imageLoader class
	 */
	public ImageLoader() {
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new ImageFileFilter());
		fileChooser.setMultiSelectionEnabled(true);
	}

	/**
	 * Import a list of images by the user, then return the list
	 *  of images from the user input
	 * @param app - ApplicaitonWindow
	 * @return List<TaggableImage> - the list of images input
	 */
	public List<TaggableImage> importImages(ApplicationWindow app) {
		List<TaggableImage> result = new ArrayList<TaggableImage>();
		int i = fileChooser.showOpenDialog(app);
		if (i == JFileChooser.CANCEL_OPTION) { return null; } 
		int action = ImportImageAction(app);
		
		if (action == 0){ //Overwrite Existing Images
			selectedImageFiles = fileChooser.getSelectedFiles();
			for (File file: selectedImageFiles) {
				result.add(new TaggableImage(file));
			}
			return result;
		} else if (action == 1){ // Merge Images
			//moved here to add the existing images first jm 091012
			for (TaggableImage file: ApplicationWindow.getImportedImageList()) {
				result.add((file));
			}
			File [] newImages = fileChooser.getSelectedFiles();
			for (File file: newImages) {
				result.add(new TaggableImage(file));
			}
			return result;
		} else { 
			//cancelled return existing
			return null;
		}
	}

	/**
	 * Will pop up a dialog to ask the user whether 
	 * to overwriting 0 or add to existing one 1.
	 * @param app - ApplicationWindow
	 * @return Integer - overwriting or no select image 0, add to existing one 1, cancel 2 
	 */
	public int ImportImageAction(ApplicationWindow app) {		
		if(selectedImageFiles == null || selectedImageFiles.length == 0){
			return 0; //will overwrite existing image set (which will be empty)
		}
		Object[] options = {"Yes, Overwrite", "Yes, Add to existing", "Cancel"};
		int n = JOptionPane.showOptionDialog(app, "Overwrite existing images or add to set ",
				"Import Action",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[2]);
		return n;
	}

	/**
	 * The class is used to control the images' format. only jpg, png, gif and bmp allowed
	 * @author AgriSoft
	 *
	 */
	public class ImageFileFilter extends FileFilter {
		private final String[] okFileExtensions = new String[] {"jpg", "png", "gif", "bmp"};
		
		@Override
		public boolean accept(File file)
		{
			for (String extension : okFileExtensions)
			{
				if (file.getName().toLowerCase().endsWith(extension) || file.isDirectory())
				{
					return true;
				}
			}
			return false;
		}

		public String getDescription() {
			return null;
		}
	}
	
	// for testing
	public void setSelectedFiletoDefaultValue(File[] input) {
		selectedImageFiles = input;
	}

	// for testing
	public JFileChooser fileChooser() {
		return fileChooser;
	}
	
}
