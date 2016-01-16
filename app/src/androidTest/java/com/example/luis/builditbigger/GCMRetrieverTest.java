package com.example.luis.builditbigger;

import android.test.AndroidTestCase;

import com.example.luis.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by Luis on 1/16/2016.
 */
public class GCMRetrieverTest extends AndroidTestCase {

    public void testRetrieve() {

        MyApi myApiService = null;

        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://cryptic-ground-119019.appspot.com/_ah/api/");
            // end options for devappserver

            myApiService = builder.build();
        }

        String result = "";
        try {
            result =  myApiService.myJoke().execute().getJoke();
        } catch (IOException e) {
            result = e.getMessage();
        }

        assertEquals(result.equals(""), false);
    }
}
