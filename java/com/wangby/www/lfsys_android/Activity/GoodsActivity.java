package com.wangby.www.lfsys_android.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wangby.www.lfsys_android.Object.Confing;
import com.wangby.www.lfsys_android.R;
import com.wangby.www.lfsys_android.Tool.DateTool;
import com.wangby.www.lfsys_android.View.XiansuoAdapter;
import com.wangby.www.lfsys_android.connect.Clue;
import com.wangby.www.lfsys_android.connect.Function;
import com.wangby.www.lfsys_android.connect.Post;

import java.util.List;

public class GoodsActivity extends Activity {

    ImageView img;
    TextView t;
    TextView cc;
    TextView ct;
    TextView name;
    TextView place;
    TextView time;
    TextView decp;
    TextView datail;
    TextView remark;
    ListView goodslist;
    Context mContext;
    Post goods;
    List<Clue> list;

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if(goods.getStuNum()==Confing.user.getStuNum()){
                goodslist.setAdapter(new XiansuoAdapter(mContext,list));
            }
            super.handleMessage(msg);}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        mContext = this;
        ini();
        set();
    }

    private void set() {
        name.setText(goods.getGoodsName());
        place.setText(goods.getPlace());
        time.setText(DateTool.format(goods.getTime()));
        decp.setText(goods.getDecp());
        datail.setText(goods.getDatail());
        remark.setText(goods.getRemark());
        new Thread(new Runnable() {
            public void run() {
                list = Function.showClue(goods.getId());
               if(list!=null){
                   Message m = handler.obtainMessage();
                   handler.sendMessage(m);
                }

            }
        }).start();
    }

    private void ini() {
        t= (TextView) findViewById(R.id.times);
        ct= (TextView) findViewById(R.id.xiansuo_text);
        cc = (TextView) findViewById(R.id.cc);
        if(Confing.islost){
            cc.setVisibility(View.VISIBLE);
            ct.setVisibility(View.VISIBLE);
        }else{
            cc.setVisibility(View.GONE);
            ct.setVisibility(View.GONE);
            t.setText("拾物时间");
        }
        goodslist = (ListView) findViewById(R.id.good_list_xiansuo);
        img = (ImageView) findViewById(R.id.goods_img);
        name = (TextView) findViewById(R.id.goods_name);
        place = (TextView) findViewById(R.id.goods_place);
        time = (TextView) findViewById(R.id.goods_time);
        decp = (TextView) findViewById(R.id.goods_decp);
        datail = (TextView) findViewById(R.id.goods_datail);
        remark = (TextView) findViewById(R.id.goods_remark);
        goods = Confing.goods;
    }

    public void breakp(View v){
        finish();
    }
    public void clue(View v){
        if(Confing.LOGIN_STATE){
            startActivity(new Intent(this, ClueActivity.class));
        }else {
            Toast.makeText(mContext, "请先进行登陆", Toast.LENGTH_SHORT).show();
        }

    }



}
