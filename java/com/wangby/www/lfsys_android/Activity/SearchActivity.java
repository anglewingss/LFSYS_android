package com.wangby.www.lfsys_android.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wangby.www.lfsys_android.R;

/**
 * Created by 王炳炎 on 2017/4/26.
 */
public class SearchActivity extends Activity implements View.OnClickListener {

    private EditText searchStr;
    private Button searchButton;
    private Button searchBack;

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
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.search_back:
                this.finish();
                break;
            case R.id.search_button:
                searchStr.setText("搜索"+searchStr.getText());
                break;
        }
    }
}
