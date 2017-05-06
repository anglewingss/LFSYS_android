package com.wangby.www.lfsys_android.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wangby.www.lfsys_android.R;

import java.util.ArrayList;

/**
 * Created by 王炳炎 on 2017/5/6.x
 */
public class PersonalAdapter extends BaseAdapter {

    ArrayList<String> list = new ArrayList<String>();
    Context mContext;
    public PersonalAdapter(Context mContext) {
        this.mContext = mContext;
        list.add("1");
        list.add("2");

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
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(position==0){
            view = layoutInflater.inflate(R.layout.personal_context, null);
        }else {
            view = layoutInflater.inflate(R.layout.lssue_list, null);
        }
        return view;
    }
}
