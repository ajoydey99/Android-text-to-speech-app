package com.ajoy.texttospeech;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech ttsobject;
    int result;
    EditText et;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.entry_text);

        ttsobject = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS) {

                    result = ttsobject.setLanguage(Locale.US);

                }
                else {
                    Toast.makeText(MainActivity.this,"Feature not supported on your device",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void doAction(View view) {
        switch (view.getId())
        {
            case R.id.bspeak:

                if(result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){
                    Toast.makeText(MainActivity.this,"Feature not supported on your device",Toast.LENGTH_SHORT).show();
                }
                else {
                    text = et.getText().toString();
                    ttsobject.speak(text,TextToSpeech.QUEUE_FLUSH,null);
                }
                break;
            case R.id.bstop:

                if(ttsobject != null) {
                    ttsobject.stop();
                }

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(ttsobject != null) {
            ttsobject.stop();
            ttsobject.shutdown();
        }
    }
}
