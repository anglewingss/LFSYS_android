package com.wangby.www.lfsys_android.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.wangby.www.lfsys_android.Object.Confing;
import com.wangby.www.lfsys_android.R;
import com.wangby.www.lfsys_android.View.ContentFragment;
import com.wangby.www.lfsys_android.View.PersonalFragment;
import com.wangby.www.lfsys_android.connect.Function;
import com.wangby.www.lfsys_android.connect.Post;

import java.util.ArrayList;
import java.util.List;

import com.wangby.www.lfsys_android.Tool.SqlTool;

/**
 * Created by 王炳炎 on 2017/4/26.
 */
public class HomeActivity extends AppCompatActivity {

    private SqlTool sqlTool;

    Context mContext;
    private Animation mRefreshAnim;
    FloatingActionButton fab;
    //下框栏
    private TabLayout mTabTl;
    //滑动内容窗口
    private ViewPager mContentVp;
    //内容布局
    private List<Fragment> tabFragments;
    //内容配置器
    private ContentPagerAdapter contentAdapter;
    //顶框
    Toolbar toolbar;
    String[] str = new String[]{"失物求助", "拾物招领", "物品发布",  "个人中心"};

    View tab_search=null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //加载顶框
        toolbar = (Toolbar) findViewById(R.id.topp);
        setSupportActionBar(toolbar);

        sqlTool = new SqlTool(this);

        mContext = this;

        mTabTl = (TabLayout) findViewById(R.id.tl_tab);
        mContentVp = (ViewPager) findViewById(R.id.vp_content);

        inify();
        initContent();

        initTab();

    }

    private void inify() {

        mRefreshAnim = AnimationUtils.loadAnimation(mContext, R.anim.anim_rotate_refresh);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnim();
            }
        });
    }
    public void stopAnim() {
        mRefreshAnim.reset();
        fab.clearAnimation();

//        fab.setBackgroundResource(R.drawable.search);
    }

    public void startAnim() {
        mRefreshAnim.reset();
        fab.clearAnimation();
//        fab.setBackgroundResource(R.drawable.search_refresh);
        fab.startAnimation(mRefreshAnim);

        new Thread(new Runnable() {
            public void run() {
                List<Post> list=null;
                list = Function.showLost();
                if(list !=null) {
                    sqlTool.delect("lost");
                    sqlTool.saveGoods(list, "lost");
                }

                List<Post> listf=null;

                listf = Function.showFound();
                if(listf !=null) {
                    sqlTool.delect("Found");
                    sqlTool.saveGoods(listf, "Found");
                }

            }
        }).start();
        mHandler.sendEmptyMessageDelayed(0, 1000);
    }

    public void exit(View v){
        Confing.LOGIN_STATE=false;
        SqlTool sqlTool = new SqlTool(this);
        sqlTool.delect("user");
        finish();
    }
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if(msg.what==0){
                stopAnim();
            }
            super.handleMessage(msg);}
    };

    /**
     * 登陆事件
     * @param v
     */
    public void imageview(View v){
        if(Confing.LOGIN_STATE){
            startActivity(new Intent(this, PersonalActivity.class));
        }else {

//            Intent newIntent = new Intent(this, LoginActivity.class);
//            newIntent.putExtra("state",0 );
//            // 开始一个新的 Activity等候返回结果
//            startActivityForResult(newIntent, 0);

            startActivity(new Intent(this, LoginActivity.class));
        }
    }


    /**
     * 配置下边框
     */
    private void initTab() {
        mTabTl.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabTl.setTabTextColors(ContextCompat.getColor(this, R.color.gray), ContextCompat.getColor(this, R.color.blue));
        mTabTl.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.white));
        ViewCompat.setElevation(mTabTl, 10);
        mTabTl.setupWithViewPager(mContentVp);
        mTabTl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                toolbar.setTitle(str[tab.getPosition()]);
                Confing.fragments=tab.getPosition();
                if(tab.getPosition()>1){
                    tab_search = findViewById(R.id.tab_search);
                    tab_search.setVisibility(View.GONE);
                    stopAnim();
                    fab.setVisibility(View.GONE);

                }else {
                    tab_search = findViewById(R.id.tab_search);
                    tab_search.setVisibility(View.VISIBLE);
                    fab.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }



    /**
     * 配置Fragment界面
     */
    ContentFragment goods_lost;
    ContentFragment goods_found;
    private void initContent() {
        tabFragments = new ArrayList<>();
        goods_lost=ContentFragment.getFragment("goods_lost");
        goods_found= ContentFragment.getFragment("goods_found");
        tabFragments.add(goods_lost);
        tabFragments.add(goods_found);
        tabFragments.add(ContentFragment.getFragment("issue"));
//        tabFragments.add(ContentFragment.getFragment("message"));
        tabFragments.add(PersonalFragment.getFragment());
        contentAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        mContentVp.setAdapter(contentAdapter);

    }


    /**
     * 顶栏添加
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    /**
     * 顶栏事件
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(this, SearchActivity.class));
        return super.onOptionsItemSelected(item);
    }


    /**
     * Fragment界面  资源配置器
     */
    class ContentPagerAdapter extends FragmentPagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabFragments.get(position);
        }

        @Override
        public int getCount() {
            return tabFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return str[position];
        }
    }


}
