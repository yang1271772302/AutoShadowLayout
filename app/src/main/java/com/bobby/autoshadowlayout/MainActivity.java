package com.bobby.autoshadowlayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bobby.autolayout.AutoShadowLayout;


public class MainActivity extends Activity {

    AutoShadowLayout shadow1;
    AutoShadowLayout shadow2;
    AutoShadowLayout shadow3;
    AutoShadowLayout shadow4;

    private boolean sha1 = false;
    private boolean sha2 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shadow1 = (AutoShadowLayout) findViewById(R.id.auto1);
        shadow2 = (AutoShadowLayout) findViewById(R.id.auto2);
        shadow3 = (AutoShadowLayout) findViewById(R.id.auto3);
        shadow4 = (AutoShadowLayout) findViewById(R.id.auto4);

        shadow2.setPress(sha2);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sha1 = !sha1;
                shadow1.setPress(sha1);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sha2 = !sha2;
                shadow2.setPress(sha2);
            }
        });

        shadow4.setOnClickListener(new AutoShadowLayout.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"我被点击了",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
