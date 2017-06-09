package com.wangby.www.lfsys_android.Activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.Toast;

import com.wangby.www.lfsys_android.Object.Confing;
import com.wangby.www.lfsys_android.R;
import com.wangby.www.lfsys_android.Tool.SqlTool;
import com.wangby.www.lfsys_android.View.GoodsAdapter;
import com.wangby.www.lfsys_android.connect.Post;

import java.util.List;

public class MyGoodsActivity extends AppCompatActivity {
    private SqlTool sqlTool;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_goods);
        mContext = this;
        sqlTool = new SqlTool(mContext);
        final List<Post> goodslist = sqlTool.searchmyGood(Confing.user.getStuNum());
        ListView listView = (ListView) findViewById(R.id.search_list);

        GoodsAdapter goodsAdapter = new GoodsAdapter(mContext,goodslist);
        listView.setAdapter(goodsAdapter);
    }








    public void back(View v){
        finish();
    }

}
