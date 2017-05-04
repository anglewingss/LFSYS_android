package com.wangby.www.lfsys_android.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import com.wangby.www.lfsys_android.R;

public class SplashActivity extends Activity {
    Context mContext;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
