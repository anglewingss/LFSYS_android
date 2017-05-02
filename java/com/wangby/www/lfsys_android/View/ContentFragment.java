package com.wangby.www.lfsys_android.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wangby.www.lfsys_android.R;

/**
 * Created by 王炳炎 on 2017/4/24.
 */
public class ContentFragment extends Fragment {


    private static final String EXTRA_CONTENT = "content";

    public static ContentFragment newInstance(String content){
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_CONTENT, content);
        ContentFragment tabContentFragment = new ContentFragment();
        tabContentFragment.setArguments(arguments);
        return tabContentFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_content, null);
        String s = getArguments().getString(EXTRA_CONTENT);
        ListView listView = (ListView) contentView.findViewById(R.id.listview);
        return contentView;
    }





}
