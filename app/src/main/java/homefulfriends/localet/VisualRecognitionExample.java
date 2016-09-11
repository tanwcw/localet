package homefulfriends.localet;

import android.content.res.AssetManager;
import android.media.Image;
import android.net.Uri;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifierOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class VisualRecognitionExample {

  public static void main(String[] args) {

    VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
    service.setApiKey("6725ea55a750127ed2401d4fcfdbc9cea62787f3");

    System.out.println("Classify an image");
    //Uri path = Uri.parse("android.resource://homefulfriends.localet/" + R.drawable.icon);
//    URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/5/54/Golden_Gate_Bridge_0002.jpg");
////    Image image = ImageIO.read(url);
//    FileUtils.copyURLToFile(URL, File);
    AssetManager assetManager = getAssets();
    InputStream is;
    try {
      is = assetManager.open("icon.png");
    } catch (IOException e) {
      e.printStackTrace();
    }
    ClassifyImagesOptions options = new ClassifyImagesOptions.Builder()
            .images(new File(is))
            .build();
    VisualClassification result = service.classify(options).execute();
    System.out.println(result);
//
//    System.out.println("Classify an image");
//    ClassifyImagesOptions options = new ClassifyImagesOptions.Builder()
//        .images(new File("../res/drawable/resources/icon.png"))
//        .build();
//    VisualClassification result = service.classify(options).execute();
//    System.out.println(result);


//    System.out.println("Create a classifier with positive and negative images");
//    ClassifierOptions createOptions = new ClassifierOptions.Builder()
//        .classifierName("foo")
//        .addClass("car", new File("src/test/resources/visual_recognition/car_positive.zip"))
//        .addClass("baseball", new File("src/test/resources/visual_recognition/baseball_positive.zip"))
//        .negativeExamples(new File("src/test/resources/visual_recognition/negative.zip"))
//        .build();
//    VisualClassifier foo = service.createClassifier(createOptions).execute();
//    System.out.println(foo);

//    System.out.println("Classify using the 'Car' classifier");
//    options = new ClassifyImagesOptions.Builder()
//        .images(new File("src/test/resources/visual_recognition/car.png"))
//        .classifierIds("car")
//        .build();
//    result = service.classify(options).execute();
//    System.out.println(result);
//
//    System.out.println("Update a classifier with more positive images");
//    ClassifierOptions updateOptions = new ClassifierOptions.Builder()
//        .addClass("car", new File("src/test/resources/visual_recognition/car_positive.zip"))
//        .build();
//    VisualClassifier updatedFoo = service.updateClassifier(foo.getId(), updateOptions).execute();
//    System.out.println(updatedFoo);

  }
}
