package com.wangby.www.lfsys_android.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.wangby.www.lfsys_android.Object.Confing;
import com.wangby.www.lfsys_android.R;
import com.wangby.www.lfsys_android.View.MessageListviewAdapter;

public class MessageActivity extends Activity {


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

    public void break_message(View v){
        finish();
    }

}
