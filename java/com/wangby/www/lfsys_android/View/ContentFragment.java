package com.wangby.www.lfsys_android.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wangby.www.lfsys_android.Activity.GoodsActivity;
import com.wangby.www.lfsys_android.Activity.IssueActivity;
import com.wangby.www.lfsys_android.Activity.MessageActivity;
import com.wangby.www.lfsys_android.Object.Confing;
import com.wangby.www.lfsys_android.Object.MessageUse;
import com.wangby.www.lfsys_android.R;
import com.wangby.www.lfsys_android.Tool.SqlTool;
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

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Confing.goods=goodslist.get(position);
                        mContext.startActivity(new Intent(mContext, GoodsActivity.class));
                    }
                });
                break;
            case "goods_found":
                goodslist = sqlTool.getGood("found");
                if(goodslist!=null){
                    Toast.makeText(mContext, "有数据found", Toast.LENGTH_SHORT).show();
                    listView.setAdapter(new GoodsAdapter(mContext, goodslist));
                }else {
                    Toast.makeText(mContext, "没有数据found", Toast.LENGTH_SHORT).show();
                }

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Confing.goods=goodslist.get(position);
                        mContext.startActivity(new Intent(mContext, GoodsActivity.class));
                    }
                });
                break;
            case "issue":
                listView.setAdapter(new IssueAdapter(mContext));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position){
                            case 0:
                                mContext.startActivity(new Intent(mContext, IssueActivity.class));
                                break;
                            case 1:
                                mContext.startActivity(new Intent(mContext, IssueActivity.class));
                                break;
                            case 2:
                                mContext.startActivity(new Intent(mContext, IssueActivity.class));
                                break;
                        }

                    }
                });
                break;
            case "message":
                //通过Sql获取信息
                final ArrayList<MessageUse> messlist = new ArrayList<MessageUse>();
                for(int i = 0; i<4;i++){
                    MessageUse ad = new MessageUse();
                    ad.name="小明";
                    ad.talk.add("你好");
                    ad.talk.add("hai");
                    messlist.add(ad);
                }
                listView.setAdapter(new MessageAdapter(mContext,messlist));

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Confing.message=messlist.get(position);
                        mContext.startActivity(new Intent(mContext, MessageActivity.class));
                    }
                });
                break;
        }
    }

    @Override
    public void onStop() {

        switch(type){
            case "goods_lost":
//                Toast.makeText(mContext, "走了lost", Toast.LENGTH_SHORT).show();
                goodslist = sqlTool.getGood("lost");
                if(goodslist!=null){
                    listView.setAdapter(new GoodsAdapter(mContext, goodslist));
                }
                break;
            case "goods_found":
//                Toast.makeText(mContext, "走了found", Toast.LENGTH_SHORT).show();
                goodslist = sqlTool.getGood("found");
                if(goodslist!=null){
                    listView.setAdapter(new GoodsAdapter(mContext, goodslist));
                }
                break;
        }
        super.onStop();
    }












}
