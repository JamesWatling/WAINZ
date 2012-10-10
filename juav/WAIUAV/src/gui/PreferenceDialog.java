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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


/**
 * This class is used to display preferences information of the application.
 * @author Yang Yu
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
	private JCheckBox proxyCheckbox = new JCheckBox("Use Proxy");
	private JTextField proxyUrlField = new JTextField();
	private JTextField proxyPortField = new JTextField();
	private JRadioButton radiobutton4 = new JRadioButton("Save images to");
	private JTextField textfield = new JTextField();
	private JButton button = new JButton("Browse");
	private JRadioButton radiobutton5 = new JRadioButton("Always ask me where to save images");
	
	/**
	 * Constructor for the preference dialog
	 * @param parent - JFrame
	 */
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
		
		title = BorderFactory.createTitledBorder(loweredetched, "Proxy settings for map");
		title.setTitleJustification(TitledBorder.LEFT);
		appearance.setBorder(title);
		appearance.setLayout(null);
		proxyCheckbox.setBounds(5, 20, 100, 20);
		appearance.add(proxyCheckbox);
		JLabel label = new JLabel("Http Proxy URL");
		label.setBounds(10, 50, 100, 20);
		appearance.add(label);
		proxyUrlField.setBounds(10, 70, 300, 20);
		appearance.add(proxyUrlField);
		label = new JLabel("Http Proxy Port");
		label.setBounds(10, 95, 100, 20);
		appearance.add(label);
		proxyPortField.setBounds(10, 115, 300, 20);
		appearance.add(proxyPortField);
		if(ApplicationWindow.useProxy == true) proxyCheckbox.setSelected(true);
		proxyUrlField.setText(ApplicationWindow.proxyUrl);
		proxyPortField.setText(ApplicationWindow.proxyPort + "");
		appearance.setBounds(10, 110, 400, 150);
		
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
		export.setBounds(10, 260, 400, 85);
		
		apply.setBounds(328, 355, 80, 20);
		apply.addActionListener(this);
		cancel.setBounds(240, 355, 80, 20);
		cancel.addActionListener(this);
		
		add(general);
		add(appearance);
		add(export);
		add(apply);
		add(cancel);
		
		setResizable(false);
		setPreferredSize(new Dimension(420, 390));
		pack();
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		if(action.equals("Apply")) {
			try {
				PrintStream out = new PrintStream(new File("settings.data"));
				if(checkbox1.isSelected()) out.println("minimize=true");
				else out.println("minimize=false");
				if(checkbox2.isSelected()) out.println("warning=true");
				else out.println("warning=false");
				if(proxyCheckbox.isSelected()) out.println("proxy=true");
				else out.println("proxy=false");
				out.println("proxyUrl="+proxyUrlField.getText());
				out.println("proxyPort="+proxyPortField.getText());
				if(radiobutton4.isSelected()) out.println("ask=false " + textfield.getText());
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