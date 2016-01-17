package com.example.luis.builditbigger; /**
 * Created by Luis on 1/17/2016.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.Jokes;
import com.example.luis.jokeslayout.JokesMainActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    InterstitialAd InterAd;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        InterAd = new InterstitialAd(this);
        InterAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        InterAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Intent intent = new Intent(context, JokesMainActivity.class);
                String joke = Jokes.getJoke();
                intent.putExtra("JOKE", joke);
                startActivity(intent);
            }
        });

        requestNewInterAd();

    }


    @Override
    protected void onResume() {
        super.onResume();
        requestNewInterAd();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void intentJoke(View v) {
        String joke = Jokes.getJoke();
        Intent intent = new Intent(this, JokesMainActivity.class);
        intent.putExtra("JOKE", joke);
        if (InterAd.isLoaded()) {
            InterAd.show();
        } else {
            startActivity(intent);
        }
    }

    private void requestNewInterAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("4E7ACDCCB46B1946F0FF4FF0FA4B019C")
                .build();

        InterAd.loadAd(adRequest);
    }
}
