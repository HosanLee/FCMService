package org.androidtown.fcmservice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    TextView tokenId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tokenId = (TextView)findViewById(R.id.tokenId);
        tokenId.setText(FirebaseInstanceId.getInstance().getToken());
        Log.i("로그",FirebaseInstanceId.getInstance().getToken());
        getIntent().getExtras();

        FirebaseMessaging.getInstance().subscribeToTopic("test");
    }
}
