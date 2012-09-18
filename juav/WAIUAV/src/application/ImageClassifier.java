package application;

import com.googlecode.javacv.cpp.opencv_core.CvMat;
import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_objdetect.CascadeClassifier;
import com.googlecode.javacv.cpp.opencv_objdetect.CvHaarClassifierCascade;

public class ImageClassifier {
	/*private static String cascadeName = "lib/data.xml";
	
	public static void main(String args[]) {
		IplImage image;
		CascadeClassifier cascade, nestedCascade;
		double scale = 1.3;
		image = cvLoadImage("lena_gray.jpg",1);
			namedWindow( "result", 1 );
			if(!cascade.load(cascadeName)) {
				System.out.println("ERROR: Could not load classifier cascade");
				return;
			}
			
			if(!image.empty()) {
				detectAndDraw(image, cascade, scale);
				waitKey(0);
			}
	}
			
			public void detectAndDraw( IplImage img, CascadeClassifier cascade, double scale)
		 {
			    int i = 0;
			
			    vector<Rect> faces;
			     static CvScalar[] colors =  { CV_RGB(0,0,255),
			         CV_RGB(0,128,255),
			         CV_RGB(0,255,255),
			         CV_RGB(0,255,0),
			         CV_RGB(255,128,0),
			         CV_RGB(255,255,0),
			         CV_RGB(255,0,0),
			         CV_RGB(255,0,255)} ;
			 
			     Mat gray, smallImg( cvRound (img.rows/scale), cvRound(img.cols/scale), CV_8UC1 );
			 
			     cvtColor( img, gray, CV_BGR2GRAY );
			     resize( gray, smallImg, smallImg.size(), 0, 0, INTER_LINEAR );
			     equalizeHist( smallImg, smallImg );
			
			
			
			    cascade.detectMultiScale( smallImg, faces,
			         1.1, 2, 0
			         //|CV_HAAR_FIND_BIGGEST_OBJECT
			 //|CV_HAAR_DO_ROUGH_SEARCH
			         |CV_HAAR_SCALE_IMAGE
			         ,
			         Size(30, 30) );
			 
		     t = (double)cvGetTickCount() - t;
			     printf( "detection time = %g ms\n", t/((double)cvGetTickFrequency()*1000.) );
			     for( vector<Rect>::const_iterator r = faces.begin(); r != faces.end(); r++, i++ )
			     {
			         Mat smallImgROI;
			         vector<Rect> nestedObjects;
			         Point center;
			         Scalar color = colors[i%8];
			         int radius;
			         center.x = cvRound((r->x + r->width*0.5)*scale);
			         center.y = cvRound((r->y + r->height*0.5)*scale);
			         radius = cvRound((r->width + r->height)*0.25*scale);
			         circle( img, center, radius, color, 3, 8, 0 );
			         smallImgROI = smallImg(*r);
			     }
			     cv::imshow( "result", img );
			 }	*/
	
	
	
	
}