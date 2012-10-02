package application;

import java.awt.image.BufferedImage;

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

public class ImageClassifier {
	private static final String CASCADE_FILE = "lib/data.xml";
	
	public ImageClassifier() {
		//new JavaCvErrorCallback().redirectError();
		//findRiverImage(fileName);	
	}
	
	public BufferedImage findRiverImage(String fileName) {
		System.out.println(fileName);
		IplImage originalImage= cvLoadImage(fileName, 1);
		IplImage grayImage = IplImage.create(originalImage.width(), originalImage.height(), IPL_DEPTH_8U, 1);
		cvCvtColor(originalImage, grayImage, CV_BGR2GRAY);
		CvMemStorage storage = CvMemStorage.create();
		CvHaarClassifierCascade cascade = new CvHaarClassifierCascade(cvLoad(CASCADE_FILE));
		CvSeq rivers = cvHaarDetectObjects(grayImage, cascade, storage, 1.2, 1, 0);
		
		for (int i = 0; i < rivers.total(); i++) {
			CvRect r = new CvRect(cvGetSeqElem(rivers, i));
			cvRectangle(originalImage, cvPoint(r.x(), r.y()), cvPoint(r.x() + r.width(), r.y() + r.height()), CvScalar.RED, 3, CV_AA, 0);
        }
		
        cvClearMemStorage(storage);

		return originalImage.getBufferedImage();
	}
	
}

