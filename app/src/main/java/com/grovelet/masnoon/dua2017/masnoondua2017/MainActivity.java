package com.grovelet.masnoon.dua2017.masnoondua2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.grovelet.masnoon.dua2017.masnoondua2017.R;

public class MainActivity extends AppCompatActivity {

    ImageButton dua;
    public static Ads ads;
    public static GoogleAnalytics analytics;
    public static Tracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ads = new Ads(this, true, true, true);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window statusBar = getWindow();
            statusBar.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            statusBar.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            statusBar.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
            statusBar.setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        setContentView(R.layout.activity_main);
        RelativeLayout adView = (RelativeLayout)findViewById(R.id.adViewCon);

        ads.loadInterstitial();
        ads.loadBanner(adView);

        analytics = GoogleAnalytics.getInstance(this);
        analytics.setLocalDispatchPeriod(10);
        tracker = analytics.newTracker(getResources().getString(R.string.analytics_id));
        tracker.enableAutoActivityTracking(true);

        dua = (ImageButton) findViewById(R.id.dua);

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(3000);

        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(fadeIn);
        dua.setAnimation(animation);


        dua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DuaList.class));
                ads.showInterstitial(false);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ads.showInterstitial(true);
    }
}
