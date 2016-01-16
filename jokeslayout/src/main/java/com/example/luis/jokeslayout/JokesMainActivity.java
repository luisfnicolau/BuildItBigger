package com.example.luis.jokeslayout;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Pair;
import android.widget.TextView;

import com.example.Jokes;
import com.example.luis.myapplication.backend.myApi.MyApi;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

//import com.example.luis.builditbigger.backend.myApi.MyApi;
//import com.google.api.client.extensions.android.http.AndroidHttp;
//import com.google.api.client.extensions.android.json.AndroidJsonFactory;


/**
 * Created by Luis on 1/12/2016.
 */
public class JokesMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jokes_activity_main);

        String joke = getIntent().getStringExtra("JOKE");
        TextView textJoke = (TextView)findViewById(R.id.jokes_intent_text_view);
        textJoke.setText(joke);

        textJoke = (TextView)findViewById(R.id.jokes_direct_text_view);
        textJoke.setText(Jokes.getJoke());

        new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Manfred"));

        boolean isPaid = getIntent().getBooleanExtra("isPaid", true);


        if (!isPaid) {
            AdView mAdView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .addTestDevice("4E7ACDCCB46B1946F0FF4FF0FA4B019C")
                    .build();
            mAdView.loadAd(adRequest);
        }
    }

    class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
        private MyApi myApiService = null;
        private Context context;

        @Override
        protected String doInBackground(Pair<Context, String>... params) {
            if(myApiService == null) {  // Only do this once
//                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
//                        .setRootUrl("http://10.0.2.2:8080/_ah/api/") // 10.0.2.2 is localhost's IP address in Android emulator
//                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                            @Override
//                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//                                abstractGoogleClientRequest.setDisableGZipContent(true);
//                            }
//                        });
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://cryptic-ground-119019.appspot.com/_ah/api/");
                // end options for devappserver

                myApiService = builder.build();
            }

            context = params[0].first;
            String name = params[0].second;

            try {
//                return myApiService.sayHi(name).execute().getData();
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