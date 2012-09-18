package gui;

import images.ImageTag;
import images.TaggableImage;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ToolTipManager;

import application.ImageLoader;


public class ApplicationWindow extends JFrame implements ActionListener, WindowListener, MouseListener {
	private static DisplayMode mode = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode();
	private static Dimension dim = new Dimension(mode.getWidth(), mode.getHeight());
	
	
	private static final Dimension RIGHT_PANEL_SIZE = new Dimension(dim.width * 3 / 5, dim.height - 110);
	private static final Dimension IMAGE_CANVAS_SIZE = new Dimension(dim.width * 3 / 5, 600);
	private ImageLoader imageLoader;
	private static final long serialVersionUID = 1L;
	private static final Dimension leftPaneSize = new Dimension(dim.width * 1 / 5, dim.height - 110);
	
	private static List<TaggableImage> importedImageList;

	private ImageGridPanel imageGrid;
	
	private Canvas mainImageViewCanvas;
	private static Font mainImageViewCanvasFont = new Font("Arial", Font.BOLD, 14);
	
	private ImageIcon infoIcon = new ImageIcon("lib/information-icon.png");

	public ApplicationWindow(){
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		ToolTipManager.sharedInstance().setLightWeightPopupEnabled(false);
		setImportedImageList(new ArrayList<TaggableImage>());
		setLayout(new FlowLayout());
		setResizable(false);
		addWindowListener(this);
		initialiseMenus();
		initialiseWindow();
		initialiseApplication();

		pack();
		setVisible(true);
	}

	public void initialiseApplication(){
		imageLoader = new ImageLoader();
	}

	public void initialiseMenus(){
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		menuBar.add(file);
		JMenuItem importItem = new JMenuItem("Import");
		JMenuItem exportItem = new JMenuItem("Export");
		JMenuItem quitItem = new JMenuItem("Quit");
		importItem.addActionListener(this);
		exportItem.addActionListener(this);
		quitItem.addActionListener(this);
		file.add(importItem);
		file.add(exportItem);
		file.add(quitItem);

		JMenu option = new JMenu("Option");
		menuBar.add(option);

		JMenuItem flagItem = new JMenuItem("Flag Image");
		JMenuItem unflagItem = new JMenuItem("Unflag Image");
		JMenuItem preferencesItem = new JMenuItem("Preferences");
		flagItem.addActionListener(this);
		unflagItem.addActionListener(this);
		preferencesItem.addActionListener(this);
		
				
		option.add(flagItem);
		option.add(unflagItem);
		option.add(preferencesItem);

		JMenu help = new JMenu("Help");
		menuBar.add(help);

		JMenuItem about = new JMenuItem("About");
		JMenuItem manual = new JMenuItem("Manual");
		about.addActionListener(this);
		manual.addActionListener(this);
			
		//Setting shortcuts and Mnemonics for Options Menu
		flagItem.setMnemonic('F');
		flagItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.Event.CTRL_MASK));
		unflagItem.setMnemonic('U');
		unflagItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.Event.CTRL_MASK));
		preferencesItem.setMnemonic('P');
		preferencesItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.Event.CTRL_MASK));
		
		//Setting shortcuts and Mnemonics for File Menu
		importItem.setMnemonic('I');
		importItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.Event.CTRL_MASK));
		exportItem.setMnemonic('E');
		exportItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.Event.CTRL_MASK));
		quitItem.setMnemonic('W');
		quitItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.Event.CTRL_MASK));
		
		//Setting shortcuts and Mnemonics for Help Menu
		about.setMnemonic('A');
		about.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.Event.CTRL_MASK));
		manual.setMnemonic('M');
		manual.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.Event.CTRL_MASK));
			
				
		help.add(manual);
		help.add(about);
		setJMenuBar(menuBar);
	}
	private void initialiseWindow(){
		JPanel rightPanel = new JPanel();

		rightPanel.setPreferredSize(RIGHT_PANEL_SIZE);
		rightPanel.setLayout(new BorderLayout());
		
		mainImageViewCanvas = new Canvas() {
			private static final long serialVersionUID = 2491198060037716312L;
		     
			public void paint(Graphics g){
				System.out.println("GROWLER");
				setSize(IMAGE_CANVAS_SIZE);
				Image currentImage;
				if(imageGrid.getSelectedImage()==null)
					return;
				else {
					currentImage = imageGrid.getSelectedImage().getImage();
				}
				
				int canvasWidth = getWidth();
				int canvasHeight = getHeight();
				int imageWidth = currentImage.getWidth(this);
				int imageHeight = currentImage.getHeight(this);
				int drawWidth, drawHeight = 0;

				double aspectRatio = imageWidth/imageHeight;
				if(aspectRatio > 1){
					// wider than it is tall, scale to fit on canvas
					drawWidth = canvasWidth;
					drawHeight = (int)(drawWidth/aspectRatio * 1.0);
				} else { 
					// taller than it is wide, height should be the same as canvasHeight
					// and width scaled down appropriately
					drawHeight = canvasHeight;
					drawWidth = (int)(aspectRatio*drawHeight);
				}
				
				// get position required for painting to center the image jm 180912
				int widthOffset = canvasWidth - imageWidth;
				int heightOffset = canvasHeight - imageHeight;
				
				int xPos = widthOffset / 2;
				int yPos = heightOffset / 2;
				
				// paint canvas background black, paint image jm 180912
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, getWidth(), getHeight());
				g.drawImage(currentImage, xPos, yPos, imageWidth, imageHeight, this);
				
				// paint image filename label underneath image jm 180912
				String filename = imageGrid.getSelectedImage().getFileName();
				g.setFont(mainImageViewCanvasFont);
				FontMetrics fm = g.getFontMetrics();
				int strWidth = fm.stringWidth(filename);
				int strX = (canvasWidth-strWidth)/2;
				g.setColor(Color.WHITE);				
				g.drawString(imageGrid.getSelectedImage().getFileName(), strX, canvasHeight - 20);
			}
		};
		
		JLabel infoLabel = new JLabel(infoIcon);
		infoLabel.setBounds(10, 10, infoIcon.getIconWidth(), infoIcon.getIconHeight());
		infoLabel.addMouseListener(this);
		//rightPanel.add(infoLabel);

		mainImageViewCanvas.setPreferredSize(new Dimension(RIGHT_PANEL_SIZE.width,RIGHT_PANEL_SIZE.height-30));

		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BorderLayout());
		
		JPanel flagButtonsPanel = new JPanel();
		flagButtonsPanel.setLayout(new FlowLayout());
		
		JButton flagButton = new JButton("Flag Image");
		JButton unflagButton = new JButton("Unflag Image");
		flagButton.addActionListener(this);
		unflagButton.addActionListener(this);
		
		flagButtonsPanel.add(flagButton);
		flagButtonsPanel.add(unflagButton);
		
		buttonsPanel.add(flagButtonsPanel, BorderLayout.NORTH);
		JButton metadatabutton = new JButton("Show Metadata");
		metadatabutton.addActionListener(this);
		buttonsPanel.add(metadatabutton, BorderLayout.SOUTH);
		buttonsPanel.setPreferredSize(new Dimension(RIGHT_PANEL_SIZE.width,60));
		
		rightPanel.add(buttonsPanel, BorderLayout.SOUTH);
		rightPanel.add(mainImageViewCanvas, BorderLayout.CENTER);
		
		
		//leftPanel
		imageGrid = new ImageGridPanel(null, this);
		
		JScrollPane leftPane = new JScrollPane(imageGrid);
		leftPane.setPreferredSize(leftPaneSize);
		
		ToolTipManager.sharedInstance().setInitialDelay(0);
		
		int dismissDelay = ToolTipManager.sharedInstance().getDismissDelay();

	    // Keep the tool tip showing
	    dismissDelay = Integer.MAX_VALUE;
	    ToolTipManager.sharedInstance().setDismissDelay(dismissDelay);

		add(leftPane);
		add(rightPanel);
		pack();
		setVisible(true);
	}
	
	public Canvas getMainCanvas(){
		return mainImageViewCanvas;
	}

	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		//Debug:
		//System.out.println(action); //jm 070912

		if (action.equals("Import")) {
			//import features
			setImportedImageList(imageLoader.importImages(this));
			imageGrid.setImageList(getImportedImageList());
			imageGrid.initialise();
			imageGrid.repaint();
			mainImageViewCanvas.repaint();
		}
		else if (action.equals("Export")) {
			//export features
		}
		else if (action.equals("Quit")) {
			//quit popup
			int n = JOptionPane.showConfirmDialog(
				    this,
				    "Would you like to exit now?",
				    "Quit",
				    JOptionPane.YES_NO_OPTION);
			if(n == 0){System.exit(0);}
		}
		else if (action.equals("Flag Image")) {
			//flag currently selected image
			imageGrid.getSelectedImage().setTag(ImageTag.INFRINGEMENT);
			imageGrid.repaint();
		}
		else if (action.equals("Unflag Image")) {
			//unflag the selected image
			imageGrid.getSelectedImage().setTag(ImageTag.UNTAGGED);
			imageGrid.repaint();
		}
		else if (action.equals("Preferences")) {
			//open preferences window
		}
		else if (action.equals("Manual")) {
			//manual features
		}
		else if (action.equals("About")) {
			//about dialog
		}
		else if (action.equals("Show Metadata")){
			JOptionPane.showMessageDialog(
					null,
					imageGrid!=null&&imageGrid.getSelectedImage()!=null&&imageGrid.getSelectedImage().getMetaData()!=null?
							imageGrid.getSelectedImage().getMetaData():
								"NO METADATA");
		}
	}

	public void windowOpened(WindowEvent e) {}
	public void windowClosing(WindowEvent e) {
		//TODO Uncomment later and ovveride default action
		///int promptOnClose = JOptionPane.showConfirmDialog(this, "Are you sure you want to close?");
		
		//if (promptOnClose ==0){
			
		System.exit(0);
		//}
	}
	public void windowClosed(WindowEvent e) {
		
	}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}

	public static List<TaggableImage> getImportedImageList() {
		return importedImageList;
	}

	private void setImportedImageList(List<TaggableImage> importedImageList) {
		ApplicationWindow.importedImageList = importedImageList;
	}
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
}
