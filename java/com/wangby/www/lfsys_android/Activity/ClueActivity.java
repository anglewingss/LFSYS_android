package com.wangby.www.lfsys_android.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wangby.www.lfsys_android.Object.Confing;
import com.wangby.www.lfsys_android.R;
import com.wangby.www.lfsys_android.connect.Clue;
import com.wangby.www.lfsys_android.connect.Function;

import java.util.Date;

import static com.wangby.www.lfsys_android.Object.Confing.goods;

public class ClueActivity extends Activity {

    EditText editText;
    Clue clue;
    Context mContext;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if(msg.what==1){
                Toast.makeText(mContext, "发布成功", Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(mContext, "发布失败", Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);}
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clue);
        mContext = this;
        editText = (EditText) findViewById(R.id.txt_clue);
        clue = new Clue();
        clue.setGoodsName(goods.getGoodsName());
        clue.setGoodsID(goods.getId());
        clue.setProviderID(Confing.user.getStuNum());
    }

    public void breakclue(View v){
        finish();
    }


    public void send_clue(View v){
        Date date=new Date();
        clue.setTime(date.getTime());

        if(!TextUtils.isEmpty(editText.getText().toString().trim())){
            clue.setContent(editText.getText().toString().trim());
            new Thread(new Runnable() {
                public void run() {
                    Function.setClue(clue);
                    Message m = handler.obtainMessage();
                    if(Function.setClue(clue)){
                        m.what=1;
                        handler.sendMessage(m);
                    }else {
                        m.what=0;
                        handler.sendMessage(m);
                    }
                }
            }).start();



        }else {

            Toast.makeText(mContext, "不能为空", Toast.LENGTH_SHORT).show();
        }


    }
}
