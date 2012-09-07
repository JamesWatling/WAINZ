import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;


public class ApplicationWindow extends JFrame{

	public ApplicationWindow(){
		setLayout(new BorderLayout());
		setSize(800, 600);
		JMenuBar menuBar = new JMenuBar();
		//menuBar

		JMenu file = new JMenu("File");
		menuBar.add(file);

		JMenuItem importItem = new JMenuItem("Import");
		JMenuItem exportItem = new JMenuItem("Export");
		JMenuItem quitItem = new JMenuItem("Quit");
		file.add(importItem);
		file.add(exportItem);
		file.add(quitItem);

		JMenu option = new JMenu("Option");
		menuBar.add(option);

		JMenuItem flagItem = new JMenuItem("Flag Image");
		JMenuItem unflagItem = new JMenuItem("Unflag Image");
		JMenuItem preferencesItem = new JMenuItem("Preferences");
		option.add(flagItem);
		option.add(unflagItem);
		option.add(preferencesItem);

		JMenu help = new JMenu("Help");
		menuBar.add(help);

		JMenuItem manual = new JMenuItem("Manual");
		JMenuItem about = new JMenuItem("About");
		help.add(manual);
		help.add(about);


		setJMenuBar(menuBar);

		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();

		JScrollPane listScrollPane = new JScrollPane(leftPanel);
		JScrollPane pictureScrollPane = new JScrollPane(rightPanel);

		//Create a split pane with the two scroll panes in it.
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				listScrollPane, pictureScrollPane);

		splitPane.setDividerLocation(150);

		//Provide minimum sizes for the two components in the split pane
		Dimension minimumSize = new Dimension(100, 50);
		listScrollPane.setMinimumSize(minimumSize);
		pictureScrollPane.setMinimumSize(minimumSize);
		listScrollPane.setMaximumSize(minimumSize);
		pictureScrollPane.setMaximumSize(minimumSize);
		splitPane.setMaximumSize(minimumSize);
		splitPane.setMinimumSize(minimumSize);

		leftPanel.add(new JLabel("Growl"));
		rightPanel.add(new JLabel("ER"));



		add(splitPane);

		setVisible(true);
	}
}
