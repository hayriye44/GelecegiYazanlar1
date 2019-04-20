package com.example.hayri.gelecegiyazanlar1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button openPhotoAndVideo;
    Button openVoice;
    Button openMap;
    Button openWeb;
    Button dialCall;
    Button sendSms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openPhotoAndVideo = (Button) findViewById(R.id.open_photo_and_video);
        openPhotoAndVideo.setOnClickListener(this);
        sendSms=(Button) findViewById(R.id.send_sms);
        sendSms.setOnClickListener(this);
        openMap=(Button) findViewById(R.id.open_map);
        openMap.setOnClickListener(this);
        openVoice=(Button) findViewById(R.id.open_voice);
        openVoice.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == openPhotoAndVideo) {
            Intent photoAndVideoIntent = new Intent(MainActivity.this, CameraActivity.class);
            startActivity(photoAndVideoIntent);
        }
        else if(view==sendSms)
        {
            Intent sendsmsIntent=new Intent(MainActivity.this,SmsActivity.class);
            startActivity(sendsmsIntent);
        }
        else if(view==openMap)
        {
            Intent openMap=new Intent(MainActivity.this,HaritaActivity.class);
            startActivity(openMap);
        }
        else if(view==openVoice)
        {
            Intent openVoice=new Intent(MainActivity.this,VoiceActivity.class);
            startActivity(openVoice);
        }
    }
}

