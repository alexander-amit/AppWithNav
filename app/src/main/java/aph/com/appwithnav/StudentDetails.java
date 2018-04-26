package aph.com.appwithnav;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import android.widget.RadioGroup.OnCheckedChangeListener;


public class StudentDetails extends AppCompatActivity implements AdListener {
    private AdView adView;
    private AdView adView2;
    private InterstitialAd interstitialAd;
    private RadioGroup radioYear;
    private RadioGroup radioStream;
    private SharedPreferences.Editor editor;
    public static final String GLOBAL_PREF="GLOBAL_PREF";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        editor = getSharedPreferences(GLOBAL_PREF,MODE_PRIVATE).edit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        adView = new AdView(this, "244420492797716_244420806131018", AdSize.BANNER_HEIGHT_50);
        adView2 = new AdView(this, "244420492797716_244665339439898", AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        LinearLayout adContainer2 = (LinearLayout) findViewById(R.id.banner_container2);
        AdSettings.addTestDevice("HASHED ID");
        adContainer.addView(adView);
        adContainer2.addView(adView2);
        adView.setAdListener(this);
        adView2.setAdListener(this);
        adView.loadAd();
        adView2.loadAd();
        radioYear = (RadioGroup) findViewById(R.id.radioYear);
        radioStream = (RadioGroup) findViewById(R.id.radioStream);
        interstitialAd = new InterstitialAd(this, "244420492797716_244575756115523");
        interstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                Log.i("StudentDetails", "Ad error");
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

            @Override
            public void onInterstitialDisplayed(Ad ad) {
                finish();

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                finish();
            }
        });
        interstitialAd.loadAd();
    }

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
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

    public void saveDetails(View view) {
        interstitialAd.show();
    }

    public void checkButtonYear(View view) {
        int radioId = radioYear.getCheckedRadioButtonId();
        //RadioButton checkedId = findViewById(radioId);
        switch (radioId) {
            case R.id.radioFirst:
                Toast.makeText(getApplicationContext(), "Selected Year: First", Toast.LENGTH_SHORT).show();
                editor.putString("YEAR","F");
                break;
            case R.id.radioSecond:
                Toast.makeText(getApplicationContext(), "Selected Year: Second", Toast.LENGTH_SHORT).show();
                editor.putString("YEAR","S");
                break;
            case R.id.radioThird:
                editor.putString("YEAR","T");
                Toast.makeText(getApplicationContext(), "Selected Year: Third", Toast.LENGTH_SHORT).show();
                break;
        }
        editor.apply();
        if(radioStream.getCheckedRadioButtonId()!=-1){
            Button checkedId = findViewById(R.id.button2);
            checkedId.setBackgroundColor(Color.parseColor("#3f51b5"));
            checkedId.setEnabled(true);
        }
    }

    public void checkButtonStream(View view) {
        int radioId = radioStream.getCheckedRadioButtonId();

        switch (radioId) {
            case R.id.radioComm:
                Toast.makeText(getApplicationContext(), "Selected Stream: Commerce", Toast.LENGTH_SHORT).show();
                editor.putString("STREAM","BCOM");
                break;
            case R.id.radioArt:
                Toast.makeText(getApplicationContext(), "Selected Stream: Art", Toast.LENGTH_SHORT).show();
                editor.putString("STREAM","BART");
                break;
        }
        editor.apply();
     if(radioYear.getCheckedRadioButtonId()!=-1){
         Button checkedId = findViewById(R.id.button2);
         checkedId.setBackgroundColor(Color.parseColor("#3f51b5"));
         checkedId.setEnabled(true);
     }
    }

}
