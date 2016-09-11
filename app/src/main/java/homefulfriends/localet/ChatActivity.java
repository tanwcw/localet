package homefulfriends.localet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent launchactivity= new Intent(ChatActivity.this,MapActivity.class);
        startActivity(launchactivity);
    }
}
