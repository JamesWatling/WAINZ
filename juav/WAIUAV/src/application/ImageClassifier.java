package application;

import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.CvRect;
import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.CvSeq;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_objdetect.CvHaarClassifierCascade;

import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
import static com.googlecode.javacv.cpp.opencv_core.IPL_DEPTH_8U;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2GRAY;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
import static com.googlecode.javacv.cpp.opencv_core.cvLoad;
import static com.googlecode.javacv.cpp.opencv_objdetect.cvHaarDetectObjects;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSeqElem;
import static com.googlecode.javacv.cpp.opencv_core.cvPoint;
import static com.googlecode.javacv.cpp.opencv_core.CV_AA;
import static com.googlecode.javacv.cpp.opencv_core.cvRectangle;
import static com.googlecode.javacv.cpp.opencv_core.cvClearMemStorage;
import static com.googlecode.javacv.cpp.opencv_core.cvGet2D;
/**
 * The class is used for the image processing. 
 * THe data.xml is the classifier trained by opencv. 
 * @author AgriSoft
 *
 */
public class ImageClassifier {
	// the cascade definition to be used for detection
	private static final String CASCADE_FILE = "lib/data.xml";
	
	/**
	 * The method will read in a picture and return the a new one with classified rectangle
	 * on. If the opencv throws exception, the method will catch that and pop up a window
	 * @param fileName - the input image for the image processing
	 * @return BufferedImage - the processed image by opencv classifier
	 */
	public BufferedImage findRiverImage(String fileName) {
		try{
		// load the original image
		IplImage originalImage= cvLoadImage(fileName, 1);
		
		// create a new image of the same size as the original one
		IplImage grayImage = IplImage.create(originalImage.width(), originalImage.height(), IPL_DEPTH_8U, 1);
		
		// convert the original image to grayscale
		cvCvtColor(originalImage, grayImage, CV_BGR2GRAY);
		
		CvMemStorage storage = CvMemStorage.create();
		
		// instantiate a classifier cascade to be used for detection using the cascade definition
		CvHaarClassifierCascade cascade = new CvHaarClassifierCascade(cvLoad(CASCADE_FILE));
		
		// detect the rivers
		CvSeq rivers = cvHaarDetectObjects(grayImage, cascade, storage, 1.2, 1, 0);
		
		// iterate over the discovered rivers and draw red rectangles around them
		for (int i = 0; i < rivers.total(); i++) {
			CvRect r = new CvRect(cvGetSeqElem(rivers, i));
			double blue = cvGet2D(originalImage, r.y(), r.x()).blue();
			double green = cvGet2D(originalImage, r.y(), r.x()).green();
			double red = cvGet2D(originalImage, r.y(), r.x()).red();
			
			if(red >= 85 && red <= 140 && green >= 100 && green <= 140 && blue >=105 && blue <= 145) {
				cvRectangle(originalImage, cvPoint(r.x(), r.y()), cvPoint(r.x() + r.width(), r.y() + r.height()), CvScalar.RED, 3, CV_AA, 0);
			}
        }
		
        cvClearMemStorage(storage);

		return originalImage.getBufferedImage();
		}catch(UnsatisfiedLinkError e)
		{
			JOptionPane.showConfirmDialog(
				    null,
				    "Opencv is not properly installed!",
				    "Error",
				    JOptionPane.YES_OPTION);
		}
		catch(NoClassDefFoundError e)
		{
			JOptionPane.showConfirmDialog(
				    null,
				    "There is a file permission problem!",
				    "Error",
				    JOptionPane.YES_OPTION);
		}
		return null;
	}
	
}