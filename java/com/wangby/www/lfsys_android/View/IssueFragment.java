package com.wangby.www.lfsys_android.View;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
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
public class IssueFragment extends Fragment {

    private static final String EXTRA_CONTENT = "content";

    ListView listView;
    SqlTool sqlTool;
    Context mContext;
    public static IssueFragment getFramet(String content){
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_CONTENT, content);
        IssueFragment tabFragment = new IssueFragment();
        tabFragment.setArguments(arguments);
        return tabFragment;
    }

    //        String s = getArguments().getString(EXTRA_CONTENT);
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_content, null);
        mContext = getActivity();
        listView = (ListView) contentView.findViewById(R.id.listview);
        listView.setAdapter(new IssueAdapter(mContext, inilssueList()));
        return contentView;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private ArrayList<Issue> inilssueList() {
        ArrayList<Issue> lssueList = new ArrayList<Issue>();
        lssueList.add(new Issue(mContext.getDrawable(R.drawable.myself),"我的发布"));
        lssueList.add(new Issue(mContext.getDrawable(R.drawable.lost_title),"失物发布"));
        lssueList.add(new Issue(mContext.getDrawable(R.drawable.found_title),"拾物发布"));
        return lssueList;
    }

}
