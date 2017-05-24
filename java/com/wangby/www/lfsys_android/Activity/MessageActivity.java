package com.wangby.www.lfsys_android.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.wangby.www.lfsys_android.Object.Confing;
import com.wangby.www.lfsys_android.R;
import com.wangby.www.lfsys_android.View.MessageListviewAdapter;

public class MessageActivity extends AppCompatActivity {


    ListView listView;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        mContext = this;
        listView = (ListView) findViewById(R.id.message_list);
        listView.setAdapter(new MessageListviewAdapter(mContext, Confing.message.talk));

    }

}
