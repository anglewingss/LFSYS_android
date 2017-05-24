package com.wangby.www.lfsys_android.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangby.www.lfsys_android.Object.Confing;
import com.wangby.www.lfsys_android.R;
import com.wangby.www.lfsys_android.connect.Post;

public class GoodsActivity extends AppCompatActivity {

    ImageView img;
    TextView name;
    TextView place;
    TextView time;
    TextView decp;
    TextView datail;
    TextView remark;
    Post goods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        ini();
        set();
    }

    private void set() {
        name.setText(goods.getGoodsName());
        place.setText(goods.getPlace());
        time.setText(goods.getTime()+"");
        decp.setText(goods.getDecp());
        datail.setText(goods.getDatail());
        remark.setText(goods.getRemark());
    }

    private void ini() {
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


}
