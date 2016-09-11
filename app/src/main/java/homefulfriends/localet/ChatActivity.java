package homefulfriends.localet;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest.Builder;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChatActivity extends Activity {
    TextView resultView;
    public static String res;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Chat Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://homefulfriends.localet/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Chat Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://homefulfriends.localet/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

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

                for (int i = 0; i < intents.length(); i++) {
                    intent = intents.getJSONObject(i).getString("intent");
                }
                for (int i = 0; i < entities.length(); i++) {
                    value = entities.getJSONObject(i).getString("value");
                }

                if (intent.equals("others")) {
                    //call up mapactivity
                    Intent i = new Intent(getApplicationContext(), MapActivity.class);
                    startActivity(i);
                } else {
                    if (intent.equals("review")) {
                        //yelp
                        String[] lol = {};
                        res = YelpAPI.main(lol);

                    } else {
                        //history
                        String[] lol = {"Golden_Gate_Bridge", "history" };
                        res = WikiScraper.main(lol);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return "Error";
            }
            return "";
        }


        protected void onPostExecute(String result) {
            TextView showText = (TextView) findViewById(R.id.textView);
            resultView.setText(res);
            showText.setText(result);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        resultView = (TextView) findViewById(R.id.result);
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

        Intent launchactivity= new Intent(ChatActivity.this,MapActivity.class);
        startActivity(launchactivity);
    }
}
