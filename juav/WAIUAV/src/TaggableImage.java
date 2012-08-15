import java.awt.AWTException;
import java.awt.Robot;


public class TaggableImage {
	
	//testing
	
	public static void main (String args []) throws AWTException{
	 
	Robot robot = new Robot();
	for (int i =0; i <1920; i++){
		for (int j =0; j<1080; j++){
			 robot.mouseMove(i,j);
		}
	}
     robot.mouseMove(300, 550);
	}

}
