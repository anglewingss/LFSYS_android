package com.wangby.www.lfsys_android.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.wangby.www.lfsys_android.R;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if(false){
            checkVersion();
        }else {
            enterHome();
        }

    }

    private void checkVersion() {

    }

    private void enterHome() {
        getWindow().getDecorView().postDelayed(new Runnable() {
            public void run()
            {
                SplashActivity.this.finish();
                SplashActivity.this.startActivity(new Intent(SplashActivity.this, HomeActivity.class));}
        }, 2000L);
    }


}
