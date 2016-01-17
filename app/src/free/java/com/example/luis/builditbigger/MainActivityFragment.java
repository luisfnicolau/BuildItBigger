package com.example.luis.builditbigger; /**
 * Created by Luis on 1/17/2016.
 */

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.Jokes;
import com.example.luis.myapplication.backend.myApi.MyApi;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    View rootView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        new EndpointsAsyncTask().execute();



            final AdView mAdView = (AdView) rootView.findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .addTestDevice("4E7ACDCCB46B1946F0FF4FF0FA4B019C")
                    .build();
            mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                mAdView.setVisibility(View.GONE);
            }
        });

        TextView byLibraryTextView = (TextView) rootView.findViewById(R.id.jokes_direct_text_view);
        byLibraryTextView.setText(Jokes.getJoke());


        return rootView;
    }


    class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
        private MyApi myApiService = null;
        private ProgressBar spinner;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            spinner = (ProgressBar)rootView.findViewById(R.id.progressBar);
            spinner.setVisibility(View.VISIBLE);
        }

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
            spinner.setVisibility(View.GONE);
            TextView gcmTextView = (TextView)rootView.findViewById(R.id.jokes_GCM_text_view);
            gcmTextView.setText(result);
        }
    }
}
