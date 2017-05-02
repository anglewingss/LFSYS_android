package com.wangby.www.lfsys_android.View;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wangby.www.lfsys_android.Object.Issue;
import com.wangby.www.lfsys_android.R;
import com.wangby.www.lfsys_android.Tool.SqlTool;

import java.util.ArrayList;

/**
 * Created by 王炳炎 on 2017/5/2.
 */
public class IssueFrament extends Fragment {

    private static final String EXTRA_CONTENT = "content";

    ListView listView;
    ArrayList<Issue> lssueList;
    SqlTool sqlTool;
    Context mContext;
    View contentView;
    public static IssueFrament getFramet(String content){
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_CONTENT, content);
        IssueFrament tabFragment = new IssueFrament();
        tabFragment.setArguments(arguments);
        return tabFragment;
    }

    //        String s = getArguments().getString(EXTRA_CONTENT);
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_content, null);
        mContext = getActivity();
        listView = (ListView) contentView.findViewById(R.id.listview);
//        inilssueList();
//        listView.setAdapter(new IssueAdapter(mContext, lssueList));
        return contentView;
    }

    private void inilssueList() {
//        ImageView img = contentView.findViewById()
//        Issue add = new Issue();



    }

}
