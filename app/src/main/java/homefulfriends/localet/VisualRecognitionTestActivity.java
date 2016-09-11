package homefulfriends.localet;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifierOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

public class VisualRecognitionTestActivity extends Activity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_recognition);

        tv = (TextView) findViewById(R.id.textView2);

        VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
        service.setApiKey("6725ea55a750127ed2401d4fcfdbc9cea62787f3");

        System.out.println("Classify an image");
        //Uri path = Uri.parse("android.resource://homefulfriends.localet/" + R.drawable.icon);
//    URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/5/54/Golden_Gate_Bridge_0002.jpg");
//    Image image = ImageIO.read(url);
//    FileUtils.copyURLToFile(URL, File);
        AssetManager assetManager = getAssets();
        InputStream is = null;
        try {
            is = assetManager.open("icon.png");
        } catch (IOException e) {
            tv.setText("error");
        }
        ClassifyImagesOptions options = new ClassifyImagesOptions.Builder()
                .images(new File(String.valueOf(is)))
                .build();
        VisualClassification result = service.classify(options).execute();
        tv.setText(result.toString());
    }
}

