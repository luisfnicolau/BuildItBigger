package com.example.luis.jokeslayout;

import android.test.AndroidTestCase;

import com.example.luis.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by Luis on 1/16/2016.
 */
public class GCMRetriverTest extends AndroidTestCase {

    public void testRetrieve() {
//        EndpointsAsyncTask endpoint = new EndpointsAsyncTask();
//        endpoint.execute();

        MyApi myApiService = null;

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

        String result = "";
        try {
                result =  myApiService.myJoke().execute().getJoke();
            } catch (IOException e) {
                result = e.getMessage();
            }

        assertEquals(result.equals(""), false);
    }


//    class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
//        private MyApi myApiService = null;
//        private Context context;
//
//        @Override
//        protected String doInBackground(Void... params) {
//            if(myApiService == null) {  // Only do this once
////                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
////                        .setRootUrl("http://10.0.2.2:8080/_ah/api/") // 10.0.2.2 is localhost's IP address in Android emulator
////                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
////                            @Override
////                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
////                                abstractGoogleClientRequest.setDisableGZipContent(true);
////                            }
////                        });
//                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
//                        .setRootUrl("https://cryptic-ground-119019.appspot.com/_ah/api/");
//                // end options for devappserver
//
//                myApiService = builder.build();
//            }
//
//
//            try {
////                return myApiService.sayHi(name).execute().getData();
//                return myApiService.myJoke().execute().getJoke();
//            } catch (IOException e) {
//                return e.getMessage();
//            }
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            assertEquals(true, result.equals(""));
//        }
//    }
}
