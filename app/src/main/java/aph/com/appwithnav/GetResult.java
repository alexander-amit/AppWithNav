package aph.com.appwithnav;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class GetResult extends AppCompatActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    public static final String GLOBAL_PREF="GLOBAL_PREF";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pref = getSharedPreferences(GLOBAL_PREF,MODE_PRIVATE);
        String qList = pref.getString("QUESLIST","NON");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_result);
    }
}
