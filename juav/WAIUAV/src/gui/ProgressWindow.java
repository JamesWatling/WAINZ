package gui;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

public class ProgressWindow extends JWindow implements Runnable {
	 public static final int LOAD_WIDTH = 455;  
	    public static final int LOAD_HEIGHT = 295; 
	    public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;  
	    public static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;  
	    public JProgressBar progressbar;  
	    public JLabel label;
	    private boolean completed;
	    
	    public ProgressWindow(boolean completed) {  
	    	this.completed = completed;

	        label = new JLabel(new ImageIcon("lib/wai-default.jpg"));  
	        label.setBounds(0, 0, LOAD_WIDTH, LOAD_HEIGHT - 15);  
	        
	        progressbar = new JProgressBar();  
	       
	        progressbar.setStringPainted(true);  
	      
	        progressbar.setBorderPainted(false);  
	        
	        progressbar.setForeground(new Color(0, 210, 40));  
	        
	        progressbar.setBackground(new Color(188, 190, 194));  
	        progressbar.setBounds(0, LOAD_HEIGHT - 15, LOAD_WIDTH, 15);  
	       
	        this.add(label);  
	        this.add(progressbar);  
	        
	        this.setLayout(null);  
	        
	        this.setLocation((WIDTH - LOAD_WIDTH) / 2, (HEIGHT - LOAD_HEIGHT) / 2);  
	        
	        this.setSize(LOAD_WIDTH, LOAD_HEIGHT);  
	        
	        this.setVisible(true);  
	    }  
	  
	    public void run() {
	    	progressbar.setIndeterminate(true);
	       while(!completed)
	            try {  
	                Thread.sleep(100);  
	            } catch (InterruptedException e) {  
	                e.printStackTrace();  
	            }  
	       	
	        }  
	    
	        //progressbar.setString("Completed");
	        //JOptionPane.showMessageDialog(this, "COMPLETED");  
	        //this.dispose();
	  
	  
	} 

