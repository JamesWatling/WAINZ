package application;

import gui.ApplicationWindow;
import images.TaggableImage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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
		int action = ImportImageAction(app);
		
		System.out.println(action);
		
		if (action ==0){ //Overwrite Existing Images
			selectedImageFiles = fileChooser.getSelectedFiles();
			for (File file: selectedImageFiles) {
				result.add(new TaggableImage(file));
			}
			
			return result;
		}
		if (action ==1){ // Merge Images
			File [] newImages = fileChooser.getSelectedFiles();
			for (File file: newImages) {
				result.add(new TaggableImage(file));
			}
			for (File file: selectedImageFiles) {
				result.add(new TaggableImage(file));
			}
			
			
			return result;
		}
		else{ //Cancel Action
			return null;
	}
		
	}
	
	public int ImportImageAction(ApplicationWindow app){
		
		if(selectedImageFiles == null){
			return 0; //will overwrite existing image set (which will be empty)
		}
		Object[] options = {"Yes, Overwrite",
                "Yes, Add to existing",
                "Do Nothing"};
			int n = JOptionPane.showOptionDialog(app, "Overwrite existing images or add to set "
			+ "with that ham?",
			"A Silly Question",
			JOptionPane.YES_NO_CANCEL_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,
			options,
			options[2]);
			return n;
			
	}
}
