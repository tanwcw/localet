package homefulfriends.localet;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest.Builder;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChatActivity extends Activity {

    private class IBM extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... input) {
            final ConversationService service = new ConversationService("2016-07-11");
            service.setUsernameAndPassword("486ba223-ffe4-471d-9298-6077153070a8", "doDFV1RkJB7t");

            MessageRequest newMessage = new Builder()
                    .inputText(input[0])
                    // Replace with the context obtained from the initial request
                    //.context(...)
                    .build();

            String workspaceId = "48a2b3f0-63e4-4dd5-828f-f91ff525b75f";

            MessageResponse response = service
                    .message(workspaceId, newMessage)
                    .execute();

            //String str = response.toString();
            //str = str.replaceAll("[", "{");
            //str = str.replaceAll("]", "}");
            //Log.e("response: ", String.valueOf(response));
            //Log.e("response: ",(JSONObject)(response.toString()));
            //Log.e("response: ", String.valueOf(response));
            //Log.e("response: ", String.valueOf(response));

            try {
                Log.e("response: ", response.toString());
                JSONObject result = new JSONObject(response.toString());
                JSONArray intents = result.getJSONArray("intents");
                JSONArray entities = result.getJSONArray("entities");
                String intent = "";
                String value = "";
                for (int i = 0; i< intents.length(); i++){
                    intent = intents.getJSONObject(i).getString("intent");
                }
                for (int i = 0; i< entities.length(); i++){
                    value = entities.getJSONObject(i).getString("value");
                }

                if (intent.equals("others")){
                    //call up mapactivity
                } else {
                    //
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return "Error";
            }
        }

        protected void onPostExecute(String result) {
            TextView showText = (TextView) findViewById(R.id.textView);
            showText.setText(result);
        }
    }
    RelativeLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }

    public void onClick(View v) {
        final ImageView button = (ImageView) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editText);
                String input = editText.getText().toString();
                new IBM().execute(input);
            }
        });
    }
}
