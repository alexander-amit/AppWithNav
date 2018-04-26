package aph.com.appwithnav;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class quesFeedActivity extends AppCompatActivity {
    Button selectSubject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        selectSubject = (Button) findViewById(R.id.navigation_notifications);
        selectSubject.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
            configSubjects();

            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_feed);
    }

    public void configSubjects(){
        Intent intent = new Intent(this, StudentDetails.class);
        Log.i("quesFeedActivity","going to call earn money activity");
        startActivity(intent);
    }
}
