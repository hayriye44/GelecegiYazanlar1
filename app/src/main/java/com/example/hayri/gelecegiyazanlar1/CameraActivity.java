package com.example.hayri.gelecegiyazanlar1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaTimestamp;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class CameraActivity extends AppCompatActivity {
    Button takePhoto;
    Button recordVideo;
    //video ve photo işlemine kod veriyor sabit olacak ve nesne oluşturulmasını static sağlıyor.Final kodun değiştirilmesini önlüyor
    private static final int VIDEO_ACTION_CODE=101;
    private static final int IMAGE_ACTION_CODE=102;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        takePhoto=(Button)findViewById(R.id.take_photo);
        recordVideo=(Button)findViewById(R.id.record_video);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            takeNewPhoto();
            }
        });
        recordVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordNewVideo();
            }
        });

    }

    @Override
    public void onActivityResult( int requestCode,int resultCode,Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK) return;
        switch(requestCode){
            case VIDEO_ACTION_CODE:
                //önce video adresi sonra set
                VideoView videoView=((VideoView)findViewById(R.id.videoPreview));
                videoView.setVideoURI(data.getData());
                //MediController bu aktivity içinde kullanılacağı için context
                videoView.setMediaController(new MediaController(this));
                videoView.requestFocus();
                videoView.start();
                break;

            case  IMAGE_ACTION_CODE:
                //videoyu urı ile yaptık bunu bundle ile ikiside yapılabilir.
                //Bundle 2 activity arası veri alışverişi
                Bundle extras=data.getExtras();
                ((ImageView)findViewById(R.id.imagePreview)).setImageBitmap((Bitmap)extras.get("data"));
                break;
            default:
                break;
        }
    }

    private  void recordNewVideo(){
        //Media store bir sınıf bu sınıfla yeni çekilen videoyu oluşturucaz
        Intent takePictureIntent =new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        //oynatacağız
        startActivityForResult(takePictureIntent,VIDEO_ACTION_CODE);
    }
    private void  takeNewPhoto(){

        Intent takePhotoIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePhotoIntent,IMAGE_ACTION_CODE);
    }
}
