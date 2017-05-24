package com.wangby.www.lfsys_android.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wangby.www.lfsys_android.R;

import java.util.ArrayList;

import static com.wangby.www.lfsys_android.R.id.text_view;

/**
 * Created by 王炳炎 on 2017/5/24.
 */

public class MessageListviewAdapter extends BaseAdapter {


        Context mContext;
        ArrayList<String> list = null;
        public MessageListviewAdapter(Context mContext,ArrayList<String> messlist) {
            this.mContext = mContext;
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
                LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(R.layout.message_list_tmie, null);
            }
            TextView context = (TextView) view.findViewById(text_view);
            context.setText(list.get(position));
            return view;
        }

    }

