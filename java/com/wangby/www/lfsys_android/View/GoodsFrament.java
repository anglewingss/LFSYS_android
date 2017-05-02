package com.wangby.www.lfsys_android.View;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wangby.www.lfsys_android.Object.Goods;
import com.wangby.www.lfsys_android.R;
import com.wangby.www.lfsys_android.Tool.SqlTool;

import java.util.ArrayList;

/**
 * Created by 王炳炎 on 2017/5/2.
 */

public class GoodsFrament extends Fragment {


    private static final String EXTRA_CONTENT = "content";

    ListView listView;
    ArrayList<Goods> goodsList;
    SqlTool sqlTool;
    Context mContext;
    public static GoodsFrament getFramet(String content){
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_CONTENT, content);
        GoodsFrament tabFragment = new GoodsFrament();
        tabFragment.setArguments(arguments);
        return tabFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_content, null);
//        String s = getArguments().getString(EXTRA_CONTENT);
        mContext = getActivity();
        listView = (ListView) contentView.findViewById(R.id.listview);
        goodsList = SqlTool.getTextGoods();

        listView.setAdapter(new GoodsAdapter(mContext, goodsList));


        return contentView;
    }









}
