package com.wangby.www.lfsys_android.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wangby.www.lfsys_android.R;
import com.wangby.www.lfsys_android.connect.Clue;

import java.util.List;

/**
 * Created by 王炳炎 on 2017/5/25.
 */
public class XiansuoAdapter extends BaseAdapter {

    private  List<Clue> list =null;
    private Context mContext;

    public XiansuoAdapter(Context mContext, List<Clue> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    int a=0;
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
            view = layoutInflater.inflate(R.layout.clue_list, null);
        }
        Clue clue = list.get(position);
        TextView textView1 = (TextView) view.findViewById(R.id.clue_time);
        TextView textView2 = (TextView) view.findViewById(R.id.clue_context);
        textView1.setText("时间："+clue.getTime());
        textView2.setText("    "+clue.getContent());

        return view;
    }

}
