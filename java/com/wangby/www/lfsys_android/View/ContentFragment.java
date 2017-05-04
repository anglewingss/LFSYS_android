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
 * Created by 王炳炎 on 2017/4/24.
 */
public class ContentFragment extends Fragment {


    ListView listView;
    ArrayList<Goods> goodsList;
    SqlTool sqlTool;
    Context mContext;
    public static ContentFragment newInstance(String content){
        Bundle arguments = new Bundle();
        ContentFragment tabContentFragment = new ContentFragment();
        tabContentFragment.setArguments(arguments);
        return tabContentFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_content, null);
        mContext = getActivity();
        listView = (ListView) contentView.findViewById(R.id.listview);
        goodsList = SqlTool.getTextGoods();
        listView.setAdapter(new GoodsAdapter(mContext, goodsList));
        return contentView;
    }

}
