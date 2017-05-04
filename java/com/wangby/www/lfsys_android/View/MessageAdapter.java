package com.wangby.www.lfsys_android.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wangby.www.lfsys_android.Object.MessageUse;
import com.wangby.www.lfsys_android.R;

import java.util.ArrayList;

/**
 * Created by 王炳炎 on 2017/5/4.
 */
public class MessageAdapter extends BaseAdapter {

    Context context;
    ArrayList<MessageUse> list = null;
    public MessageAdapter(Context mContext, ArrayList<MessageUse> messlist) {
        this.context = mContext;
        this.list = messlist;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if(convertView != null){
            view = convertView;
        }else {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.message_list, null);
        }
        MessageUse messageUse = list.get(position);
        TextView name = (TextView) view.findViewById(R.id.textView_message_name);
        TextView conent = (TextView) view.findViewById(R.id.textView_message_content);
        name.setText(messageUse.name);
        conent.setText(messageUse.talk.get(messageUse.talk.size()-1));
        return view;
    }

}
