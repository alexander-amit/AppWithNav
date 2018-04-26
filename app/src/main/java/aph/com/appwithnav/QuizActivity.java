package aph.com.appwithnav;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aph.com.appwithnav.model.Question;

public class QuizActivity extends AppCompatActivity implements RewardedVideoAdListener {
    private RewardedVideoAd mRewardedVideoAd;
    int qIndex;
    Button ans1, ans2, ans3, ans4;
    Question currQues;
    String corrAns,exp;
    TextView header,ques;
    private long createdMillis;
    private long nowMillis;
    int creditConins;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    public static final String GLOBAL_PREF="GLOBAL_PREF";

    String qListJson = "[{\"id\":\"string\",\"stream\":\"string\",\"year\":\"string\",\"subject\":\"string\",\"ques\":\"q1\",\"opt1\":\"string\",\"opt2\":\"string\",\"opt3\":\"string\",\"opt4\":\"string\",\"correctAns\":\"1\",\"explanation\":\"string\"},{\"id\":\"string\",\"stream\":\"string\",\"year\":\"string\",\"subject\":\"string\",\"ques\":\"q2\",\"opt1\":\"string\",\"opt2\":\"string\",\"opt3\":\"string\",\"opt4\":\"string\",\"correctAns\":\"2\",\"explanation\":\"string\"},{\"id\":\"string\",\"stream\":\"string\",\"year\":\"string\",\"subject\":\"string\",\"ques\":\"q3\",\"opt1\":\"string\",\"opt2\":\"string\",\"opt3\":\"string\",\"opt4\":\"string\",\"correctAns\":\"3\",\"explanation\":\"string\"},{\"id\":\"string\",\"stream\":\"string\",\"year\":\"string\",\"subject\":\"string\",\"ques\":\"q4\",\"opt1\":\"string\",\"opt2\":\"string\",\"opt3\":\"string\",\"opt4\":\"string\",\"correctAns\":\"4\",\"explanation\":\"string\"}]";
    List<Question> qList;
    private String Tag = "QuizActivity";
    private String currCoins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pref = getSharedPreferences(GLOBAL_PREF,MODE_PRIVATE);
        editor = pref.edit();
        editor.putString("QUESLIST",qListJson);
        editor.apply();
        //MobileAds.initialize(this, "ca-app-pub-6876139477910913/2453850406");
        MobileAds.initialize(this, "ca-app-pub-3940256099942544/5224354917");

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);

        Log.i("earn activity","done-------------cosnt");
        /*
        mRewardedVideoAd.loadAd("ca-app-pub-6876139477910913/2453850406",
                new AdRequest.Builder().build());
*/
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                new AdRequest.Builder().build());

        qIndex = -1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        header = findViewById(R.id.textView11);
        ques = findViewById(R.id.textView10);


        ans1 = findViewById(R.id.button7);
        ans2 = findViewById(R.id.button6);
        ans3 = findViewById(R.id.button5);
        ans4 = findViewById(R.id.button3);
        ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(corrAns.equals("1")){
                Toast.makeText(getApplicationContext(), "Correct Ans!", Toast.LENGTH_LONG).show();}
                else
                {
                    Toast.makeText(getApplicationContext(),"Incorrect Ans!" , Toast.LENGTH_LONG).show();
                }
                initialize();

            }
        });

        ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(corrAns.equals("2")){
                    Toast.makeText(getApplicationContext(), "Correct Ans!", Toast.LENGTH_LONG).show();}
                else
                {
                    Toast.makeText(getApplicationContext(),"Incorrect Ans!" , Toast.LENGTH_LONG).show();
                }
                initialize();

            }

        });
        ans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(corrAns.equals("3")){
                    Toast.makeText(getApplicationContext(), "Correct Ans!", Toast.LENGTH_LONG).show();}
                else
                {
                    Toast.makeText(getApplicationContext(),"Incorrect Ans!" , Toast.LENGTH_LONG).show();
                }
                initialize();
            }

        });
        ans4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(corrAns.equals("4")){
                    Toast.makeText(getApplicationContext(), "Correct Ans!", Toast.LENGTH_LONG).show();}
                else
                {
                    Toast.makeText(getApplicationContext(),"Incorrect Ans!" , Toast.LENGTH_LONG).show();
                }
                initialize();
            }
        });
        Gson gson = new Gson();
        Type type = new TypeToken<List<Question>>() {}.getType();
        qList = gson.fromJson(qListJson, type);
        initialize();
    }

    private String checkAns() {
        return (corrAns);
    }

    private void initialize() {
        qIndex++;
        Log.i(Tag,"going to call Get Result activity" + qIndex);
        if(qIndex>=4){
            //mRewardedVideoAd.show();
            if (mRewardedVideoAd.isLoaded()) {
                mRewardedVideoAd.show();
            }
            else{
                Intent intent = new Intent(this, GetResult.class);
                Log.i(Tag,"going to call Get Result activity");
                startActivity(intent);

            }

        }
        else {
            currQues = qList.get(qIndex);
            ques.setText(currQues.getQues());
            ans1.setText(currQues.getOpt1());
            ans2.setText(currQues.getOpt1());
            ans3.setText(currQues.getOpt1());
            ans4.setText(currQues.getOpt1());
            corrAns = currQues.getCorrAns();
        }
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Toast.makeText(getApplicationContext(), "Correct Explanation will come after a video Ad. Don't Close it", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {
        createdMillis = System.currentTimeMillis();

    }

    @Override
    public void onRewardedVideoAdClosed() {

        calculateCredit();


    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        calculateCredit();

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {


    }

    @Override
    public void onRewardedVideoCompleted() {
        calculateCredit();
    }

    private void calculateCredit() {
        nowMillis = System.currentTimeMillis();
        creditConins = (int)((nowMillis - this.createdMillis) / 1000);
        Toast.makeText(getApplicationContext(), "Congratulations!! You have earned " + creditConins + "coins", Toast.LENGTH_LONG).show();
        currCoins = pref.getString("COINS","0");
        int coins = Integer.valueOf(currCoins);
        coins = coins + creditConins;
        editor.putString("COINS", String.valueOf(coins));
        editor.apply();
        Toast.makeText(this, "Your Total Credit coins is " + coins + "coins", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, GetResult.class);
        startActivity(intent);
    }
}
