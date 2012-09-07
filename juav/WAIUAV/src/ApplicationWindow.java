import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class ApplicationWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApplicationWindow(){
		// TODO Auto-generated method stub
		setLayout(new FlowLayout());
		//setPreferredSize(new Dimension(800, 600));
		setResizable(false);
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
		leftPanel.setLayout(new GridLayout(0, 2));
		for(int i = 0;i<20;i++){
			JLabel l = new JLabel(new ImageIcon("a.jpg"));
			l.setBackground(new Color(0, 0, 255));
			l.setPreferredSize(new Dimension(100, 100));
			leftPanel.add(l);
		}
		JScrollPane leftPane = new JScrollPane(leftPanel);

		JPanel rightPanel = new JPanel();


		leftPane.setPreferredSize(new Dimension(250, 600));
		leftPanel.setBackground(new Color(225, 0, 0));

		rightPanel.setPreferredSize(new Dimension(550, 600));
		rightPanel.setBackground(new Color(0, 225, 0));

		add(leftPane);
		add(rightPanel);

		pack();
		setVisible(true);
	}
}
