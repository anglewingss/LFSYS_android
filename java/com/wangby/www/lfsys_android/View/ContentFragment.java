package com.wangby.www.lfsys_android.View;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.wangby.www.lfsys_android.Object.MessageUse;
import com.wangby.www.lfsys_android.R;
import com.wangby.www.lfsys_android.Tool.SqlTool;
import com.wangby.www.lfsys_android.connect.Function;
import com.wangby.www.lfsys_android.connect.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王炳炎 on 2017/4/24.
 */
public class ContentFragment extends Fragment {

    ListView listView;
    Context mContext;
    List<Post> goodslist;
    SqlTool sqlTool;
    String type;

    public static ContentFragment getFragment(String type){
        Bundle arguments = new Bundle();
        arguments.putString("TYPE", type);//放入map
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
        sqlTool = new SqlTool(mContext);
        type = getArguments().getString("TYPE");
        inilistview();
        return contentView;
    }

    private void inilistview() {
        switch(type){
            case "goods_lost":
                goodslist = sqlTool.getGood("lost");
                if(goodslist!=null){
                    listView.setAdapter(new GoodsAdapter(mContext, goodslist));
                    Toast.makeText(mContext, "有数据lost", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mContext, "没有数据lost", Toast.LENGTH_SHORT).show();
                }
                new Thread(new Runnable() {
                    public void run() {
                        List<Post> list = Function.showLost();
                        if(list !=null) {
                            sqlTool.delect("lost");
                            sqlTool.saveGoods(list, "lost");
                        }
                    }
                }).start();
                break;
            case "goods_found":
                goodslist = sqlTool.getGood("found");
                if(goodslist!=null){
                    Toast.makeText(mContext, "有数据found", Toast.LENGTH_SHORT).show();
                    listView.setAdapter(new GoodsAdapter(mContext, goodslist));
                }else {
                    Toast.makeText(mContext, "没有数据found", Toast.LENGTH_SHORT).show();
                }
//                new Thread(new Runnable() {
//                    public void run() {
//                        List<Post> list = Function.showFound();
//                        if(list !=null) {
//                            sqlTool.delect("Found");
//                            sqlTool.saveGoods(list, "Found");
//                        }
//                    }
//                }).start();
                break;
            case "issue":
                listView.setAdapter(new IssueAdapter(mContext));
                break;
            case "message":
                //通过Sql获取信息
                ArrayList<MessageUse> messlist = new ArrayList<MessageUse>();
                for(int i = 0; i<4;i++){
                    MessageUse ad = new MessageUse();
                    ad.name="小明";
                    ad.talk.add("你好");
                    ad.talk.add("hai");
                    messlist.add(ad);
                }
                listView.setAdapter(new MessageAdapter(mContext,messlist));
                break;
        }
    }

    @Override
    public void onStop() {

        switch(type){
            case "goods_lost":
                Toast.makeText(mContext, "走了lost", Toast.LENGTH_SHORT).show();
                goodslist = sqlTool.getGood("lost");
                if(goodslist!=null){
                    listView.setAdapter(new GoodsAdapter(mContext, goodslist));
                }
//                new Thread(new Runnable() {
//                    public void run() {
//                        List<Post> list = Function.showLost();
//                        sqlTool.saveGoods(list,"lost");
//                    }
//                }).start();
                break;
            case "goods_found":
                Toast.makeText(mContext, "走了found", Toast.LENGTH_SHORT).show();
                goodslist = sqlTool.getGood("found");
                if(goodslist!=null){
                    listView.setAdapter(new GoodsAdapter(mContext, goodslist));
                }
//                new Thread(new Runnable() {
//                    public void run() {
//                        List<Post> list = Function.showFound();
//                        sqlTool.saveGoods(list,"found");
//                    }
//                }).start();
                break;
        }
        super.onStop();
    }
}
