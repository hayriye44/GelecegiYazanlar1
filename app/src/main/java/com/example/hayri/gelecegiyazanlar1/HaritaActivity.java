package com.example.hayri.gelecegiyazanlar1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.URI;

public class HaritaActivity extends AppCompatActivity {
    Button openMapPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harita);
        openMapPage=(Button)findViewById(R.id.open_map_page);
        openMapPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri geoLocation=Uri.parse("geo:41.0138400,28.9496600");
                showMap(geoLocation);
            }
        });
    }
    public void showMap(Uri geoLocation)
    {
        Intent intent =new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if(intent.resolveActivity(getPackageManager())!=null)
        {
            startActivity(intent);
        }
    }
}
