package com.example.hayri.gelecegiyazanlar1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SmsActivity extends AppCompatActivity {
    private EditText etTelNumara;
    private EditText etMesaj;
    private Button btnGonder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        etMesaj=(EditText)findViewById(R.id.etMesaj);
        etTelNumara=(EditText) findViewById(R.id.etTelNo);
        btnGonder=(Button) findViewById(R.id.btnGönder);
        btnGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=etTelNumara.getText().toString();
                String message=etMesaj.getText().toString();
                Log.d("Number",number);
                Log.d("Mesaj",message);
                //Uygulamalar haberlebilir.ör ins fotoyu farklı uyg paylaşma bunu sağlayan uri
                Uri uri= Uri.parse("smsto:"+number);
                //mesaj uygulamasını açmaya yarıyor
                Intent intent=new Intent(Intent.ACTION_SENDTO,uri);
                intent.putExtra("sms_body",message);
                startActivity(intent);

               }
            });
    }
}
