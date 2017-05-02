package com.wangby.www.lfsys_android.Tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 王炳炎 on 2017/3/23.
 */

public class ImgTool extends ImageView {
    public ImgTool(Context context) {
        super(context);
    }
    public ImgTool(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public ImgTool(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private Handler handler = new Handler(){

        public void handleMessage(Message msg) {

            Bitmap bitmap = (Bitmap) msg.obj;

            ImgTool.this.setImageBitmap(bitmap);
        };
    };

    public void setImageUrl(final String url_str){


        new Thread(new Runnable() {

            @Override
            public void run() {
                try{
                    //获取url对应的图片资源，bitmap
                    URL url = new URL(url_str);

                    HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();

                    openConnection.setRequestMethod("GET");
                    openConnection.setConnectTimeout(5*1000);

                    int code = openConnection.getResponseCode();
                    if(code == 200){
                        InputStream inputStream = openConnection.getInputStream();


                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        Message msg = Message.obtain();
                        msg.obj = bitmap;
                        handler.sendMessage(msg);


                    }


                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
