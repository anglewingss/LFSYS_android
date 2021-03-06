package com.wangby.www.lfsys_android.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.wangby.www.lfsys_android.Object.Confing;
import com.wangby.www.lfsys_android.R;
import com.wangby.www.lfsys_android.Tool.SqlTool;
import com.wangby.www.lfsys_android.View.GoodsAdapter;
import com.wangby.www.lfsys_android.connect.Post;

import java.util.List;

/**
 * Created by 王炳炎 on 2017/4/26.
 */
public class SearchActivity extends Activity implements View.OnClickListener {

    private EditText searchStr;
    private Button searchButton;
    private Button searchBack;
    private ListView listView;
    private SqlTool sqlTool;
    Context mContext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();

        initInfo();
    }

    private void initInfo() {
        searchBack.setOnClickListener(this);
        searchButton.setOnClickListener(this);
    }

    private void initView() {
        searchStr= (EditText) findViewById(R.id.search_input);
        searchButton= (Button) findViewById(R.id.search_button);
        searchBack= (Button) findViewById(R.id.search_back);
        listView= (ListView) findViewById(R.id.search_list);
        mContext = this;
        sqlTool = new SqlTool(mContext);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.search_back:
                this.finish();
                break;
            case R.id.search_button:
                final List<Post> goodslist = sqlTool.searchGood(searchStr.getText().toString());
                if(goodslist==null){
                    Toast.makeText(mContext, "没有找到", Toast.LENGTH_SHORT).show();
                    break;
                }
                listView.setAdapter(new GoodsAdapter(mContext, goodslist));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Confing.goods=goodslist.get(position);
                        mContext.startActivity(new Intent(mContext, GoodsActivity.class));
                    }
                });
                break;
        }
    }
}
