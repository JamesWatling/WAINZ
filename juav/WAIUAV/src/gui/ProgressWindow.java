package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 * This class displays progress information when importing or analyzing images.
 * @author yuyang2
 *
 */
public class ProgressWindow extends JDialog {
	private static final long serialVersionUID = 1L;
	private JProgressBar progressBar;
	
	/**
	 * Constructor for the ProgressWindow
	 * @param panel - JPanel
	 * @param type - Integer
	 */
	public ProgressWindow(JPanel panel, int type) {
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBorderPainted(false);
		progressBar.setForeground(new Color(0, 210, 40));
		progressBar.setBackground(new Color(188, 190, 194));
		
		if(type == 0) progressBar.setString("Please wait while the images are being imported...");
		else progressBar.setString("Please wait while the images are being processed...");
		
		add(progressBar);
		
		setUndecorated(true);
		setAlwaysOnTop(true);
		setResizable(false);
		setPreferredSize(new Dimension(400, 30));
		pack();
		setLocationRelativeTo(panel);
		setVisible(true);
	}
	
	/**
	 * Control the bar progress
	 * @param progress - Integer
	 */
	public void setValue(int progress) {
		progressBar.setValue(progress);
	}

}