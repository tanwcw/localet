package homefulfriends.localet;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends Activity {
    Button button;
    ImageView imageView;
    static final int CAM_REQUEST = 1;
    private boolean isCameraClicked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        button  = (Button) findViewById(R.id.myButton);
        imageView = (ImageView) findViewById(R.id.imageView1);
        button.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent, CAM_REQUEST);
            }
        });

    }

    private File getFile(){
        File folder = new File("sdcard/localet_photo");
        if(!folder.exists()){
            folder.mkdir();
        }
        File image_file = new File(folder, "cam_image.jpg");
        return image_file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        String path = "sdcard/localet/cam_image.jpg";
        imageView.setImageDrawable(Drawable.createFromPath(path));
        Intent launchactivity= new Intent(MainActivity.this, ChatActivity.class);
        startActivity(launchactivity);
    }

}