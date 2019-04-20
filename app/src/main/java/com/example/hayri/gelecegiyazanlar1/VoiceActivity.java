package com.example.hayri.gelecegiyazanlar1;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class VoiceActivity extends AppCompatActivity {
    private Button btnKaydet;
    private Button btnCal;
    private Button btnDurdur;
    private MediaRecorder recorder;
    private MediaPlayer player;
    //nereye kaydedilecek 3gb sıkıştırılmış müzik dosyası
    private final String filepath = Environment.getExternalStorageDirectory().getPath() + "/gizlises.3gb";
    //ses kayıt kodu 200
    public static final int PERMISSIONCODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);

        btnCal = findViewById(R.id.btnCal);
        btnDurdur = findViewById(R.id.btnDur);
        btnKaydet = findViewById(R.id.btnKaydet);
        if(checkPermission()){
            btnKaydet.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 startRecord();
                                             }
                                         }

            );
            btnDurdur.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stopRecord();
                }
            });
            btnCal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startPlay();
                }
            });
        }else{requestPermissions();}

    }
    public  void startRecord(){
        //Kayıt işlemi için ayarlamalar
        recorder=new MediaRecorder();
        //kayıt kaynağı mikrofon buna erişmek
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        //Encode tipi uygulamanın kaç mb olduğu threegpp yi alıyor encodeluyor
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(filepath);

        //kayıt işleminibaşlatmak
        //kayıt başlayabilecekmi kontrol için try catch
        try{
            //recorder hazırla mikrofon aç mikrafon çalışıyormu
            recorder.prepare();
            recorder.start();
            Toast.makeText(getApplicationContext(),"Kayıt yapılıyor",Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void stopRecord(){
        if(recorder!= null)
        {
            Toast.makeText(getApplicationContext(),"Kayıt Durdu ",Toast.LENGTH_SHORT).show();
            recorder.stop();
            recorder.reset();
            recorder.release();
             recorder = null;
        }
    }
    public void startPlay(){
        player=new MediaPlayer();
        //ses geçişi sesin sadece sağa gelmesi sola gelmesi dönerek gelmesi setVolume
        player.setVolume(1.0f,1.0f);
        try{
            player.setDataSource(filepath);
            player.prepare();
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player.stop();
                    //tekrar başlayabilmesi için
                    player.release();
                    //player içini boşaltmak
                    player=null;
                }
            });
        }
        catch (Exception e)
        {e.printStackTrace();}
    }
    //birden fazla izin olduğu için bu fonksiyon kullanıldı kullanıcıdan izin alma fonk
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case PERMISSIONCODE:
                if(grantResults.length>0){
                    //izin alınmışmı alınmamışmı kontrol
                    boolean permissionRecord=grantResults[0]==PackageManager.PERMISSION_GRANTED;
                    boolean permissionStore=grantResults[1]==PackageManager.PERMISSION_GRANTED;
                    //bunlar ikiside true gelirse izin alınmıştır
                    if(permissionRecord && permissionStore) {
                        Toast.makeText(getApplicationContext(), "İzinler var", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"İzin ver",Toast.LENGTH_SHORT).show();

                        }
                    }
                break;
        }
    }
    public boolean checkPermission(){
        int result= ContextCompat.checkSelfPermission(getApplicationContext(),WRITE_EXTERNAL_STORAGE);
        int result1=ContextCompat.checkSelfPermission(getApplicationContext(),RECORD_AUDIO);
        return result==PackageManager.PERMISSION_GRANTED && result1== PackageManager.PERMISSION_GRANTED;
    }
    private void requestPermissions(){
        ActivityCompat.requestPermissions(VoiceActivity.this,new String[] {RECORD_AUDIO,WRITE_EXTERNAL_STORAGE},200);
    }
}
