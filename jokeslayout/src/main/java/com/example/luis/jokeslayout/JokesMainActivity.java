package com.example.luis.jokeslayout;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.example.luis.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;


/**
 * Created by Luis on 1/12/2016.
 */
public class JokesMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jokes_activity_main);

        new EndpointsAsyncTask().execute();

        String joke = getIntent().getStringExtra("JOKE");
        TextView textJoke = (TextView)findViewById(R.id.jokes_intent_text_view);
        textJoke.setText(joke);
    }


    class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
        private MyApi myApiService = null;
        private Context context;

        @Override
        protected String doInBackground(Void... params) {
            if(myApiService == null) {

                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://cryptic-ground-119019.appspot.com/_ah/api/");

                myApiService = builder.build();
            }

            try {
                return myApiService.myJoke().execute().getJoke();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            TextView gcmTextView = (TextView)findViewById(R.id.jokes_GCM_text_view);
            gcmTextView.setText(result);
        }
    }
}