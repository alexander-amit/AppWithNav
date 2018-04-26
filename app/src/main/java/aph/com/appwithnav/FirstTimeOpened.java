package aph.com.appwithnav;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.ads.*;

public class FirstTimeOpened extends AppCompatActivity implements AdListener {

    private AdView adView;
    private AdView adView2;
    private AdView adView3;
    private String Tag = "FirstTimeOpened";
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    public static final String GLOBAL_PREF="GLOBAL_PREF";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pref = getSharedPreferences(GLOBAL_PREF,MODE_PRIVATE);
        editor = pref.edit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_opened);
        AdSettings.addTestDevice("b20ad6ab-f8a4-419d-842c-c7ebfa797c87");
        adView = new AdView(this, "244420492797716_245930375980061", AdSize.BANNER_HEIGHT_50);
        adView2 = new AdView(this, "244420492797716_244675336105565", AdSize.BANNER_HEIGHT_50);
        adView3 = new AdView(this, "244420492797716_244595759446856", AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        LinearLayout adContainer2 = (LinearLayout) findViewById(R.id.banner_container2);
        LinearLayout adContainer3 = (LinearLayout) findViewById(R.id.banner_container3);
        adContainer.addView(adView);
        adContainer2.addView(adView2);
        adContainer3.addView(adView3);


        adView.setAdListener(this);
        adView2.setAdListener(this);
        adView3.setAdListener(this);
        adView.loadAd();
        adView2.loadAd();
        adView3.loadAd();

    }

    public void configSubjects(View view) {
        Intent intent = new Intent(this, StudentDetails.class);
        Log.i("FirstTimeOpened","going to call earn money activity");
        startActivity(intent);
    }

    public void startQuiz(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        Log.i(Tag,"going to call Quiz activity");
        startActivity(intent);

    }
    @Override
    protected void onDestroy() {
        editor.remove(StringConstants.YEAR);
        editor.remove(StringConstants.STREAM);
        editor.apply();


        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!(pref.getString("YEAR","NON").equals("NON")) && !(pref.getString("STREAM","NON").equals("NON")))
        {   Log.i(Tag,pref.getString("YEAR","NON")+pref.getString("STREAM","NON"));
            Button start_quiz = findViewById(R.id.button2);
            start_quiz.setEnabled(true);
            start_quiz.setBackgroundColor(Color.parseColor("#3f51b5"));
            getIntent().getExtras();
        }
    }

    @Override
    public void onError(Ad ad, AdError adError) {
        Toast.makeText(this, "Error: " + adError.getErrorMessage(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAdLoaded(Ad ad) {

    }

    @Override
    public void onAdClicked(Ad ad) {

    }

    @Override
    public void onLoggingImpression(Ad ad) {

    }
}
