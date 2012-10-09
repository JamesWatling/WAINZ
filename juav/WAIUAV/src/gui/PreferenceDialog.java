package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * This class is used to display preferences information of the application.
 *
 */
public class PreferenceDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel general = new JPanel();
	private JPanel appearance = new JPanel();
	private JPanel export = new JPanel();
	private JButton apply = new JButton("Apply");
	private JButton cancel = new JButton("Cancel");
	
	private JCheckBox checkbox1 = new JCheckBox("Minimize window when I click Close button");
	private JCheckBox checkbox2 = new JCheckBox("Warn me before quitting");
	private JRadioButton radiobutton1 = new JRadioButton("Default");
	private JRadioButton radiobutton2 = new JRadioButton("Look and feel 2");
	private JRadioButton radiobutton3 = new JRadioButton("Look and feel 3");
	private JRadioButton radiobutton4 = new JRadioButton("Save images to");
	private JTextField textfield = new JTextField();
	private JButton button = new JButton("Browse");
	private JRadioButton radiobutton5 = new JRadioButton("Always ask me where to save images");
	
	public PreferenceDialog(JFrame parent) {
		super(parent, "Preferences", true);
		
		setLayout(null);
		
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder title = BorderFactory.createTitledBorder(loweredetched, "General");
		title.setTitleJustification(TitledBorder.LEFT);
		general.setBorder(title);
		general.setLayout(new BoxLayout(general, BoxLayout.Y_AXIS));
		general.add(checkbox1);
		if(ApplicationWindow.minimize) checkbox1.setSelected(true); 
		general.add(Box.createVerticalStrut(10));
		general.add(checkbox2);
		if(ApplicationWindow.warning) checkbox2.setSelected(true);
		general.setBounds(10, 10, 400, 90);
		
		title = BorderFactory.createTitledBorder(loweredetched, "Appearance");
		title.setTitleJustification(TitledBorder.LEFT);
		appearance.setBorder(title);
		appearance.setLayout(new BoxLayout(appearance, BoxLayout.Y_AXIS));
		appearance.add(radiobutton1);
		appearance.add(Box.createVerticalStrut(10));
		appearance.add(radiobutton2);
		appearance.add(Box.createVerticalStrut(10));
		appearance.add(radiobutton3);
		ButtonGroup group1 = new ButtonGroup(); 
		group1.add(radiobutton1);
		if(ApplicationWindow.lookAndFeel == 1) radiobutton1.setSelected(true);
		group1.add(radiobutton2);
		if(ApplicationWindow.lookAndFeel == 2) radiobutton2.setSelected(true);
		group1.add(radiobutton3);
		if(ApplicationWindow.lookAndFeel == 3) radiobutton3.setSelected(true);
		appearance.setBounds(10, 110, 400, 120);
		
		title = BorderFactory.createTitledBorder(loweredetched, "Export");
		title.setTitleJustification(TitledBorder.LEFT);
		export.setBorder(title);
		export.setLayout(null);
		radiobutton4.setBounds(7, 22, 130, 20);
		export.add(radiobutton4);
		textfield.setBounds(140, 22, 150, 20);
		textfield.setEditable(false);
		export.add(textfield);
		button.setBounds(295, 22, 80, 20);
		button.addActionListener(this);
		export.add(button);
		if(!ApplicationWindow.ask) {
			radiobutton4.setSelected(true);
			textfield.setText(ApplicationWindow.location);
		}
		radiobutton5.setBounds(7, 52, 300, 20);
		export.add(radiobutton5);
		if(ApplicationWindow.ask) radiobutton5.setSelected(true);
		ButtonGroup group2 = new ButtonGroup(); 
		group2.add(radiobutton4);
		group2.add(radiobutton5);
		export.setBounds(10, 240, 400, 85);
		
		apply.setBounds(328, 335, 80, 20);
		apply.addActionListener(this);
		cancel.setBounds(240, 335, 80, 20);
		cancel.addActionListener(this);
		
		add(general);
		add(appearance);
		add(export);
		add(apply);
		add(cancel);
		
		setResizable(false);
		setPreferredSize(new Dimension(420, 400));
		pack();
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		if(action.equals("Apply")) {
			try {
				PrintStream out = new PrintStream(new File("settings.data"));
				if(checkbox1.isSelected()) out.println("minimize=true");
				else out.println("minimize=false");
				if(checkbox2.isSelected()) out.println("warning=true");
				else out.println("warning=false");
				if(radiobutton1.isSelected()) out.println("1");
				else if(radiobutton2.isSelected()) out.println("2");
				else if(radiobutton3.isSelected()) out.println("3");
				if(radiobutton4.isSelected()) out.println("ask=fasle " + textfield.getText());
				else if(radiobutton5.isSelected()) out.println("ask=true");
				out.close();
				dispose();
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			}
		} else if(action.equals("Cancel")) {
			dispose();
		} else {
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setDialogTitle("Choose export folder");
			int returnVal = fc.showSaveDialog(fc);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				textfield.setText(fc.getSelectedFile().getPath());
			}
		}
	}

}