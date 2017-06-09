package com.wangby.www.lfsys_android.View;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wangby.www.lfsys_android.Object.Confing;
import com.wangby.www.lfsys_android.R;
import com.wangby.www.lfsys_android.connect.User;

import static com.wangby.www.lfsys_android.Object.Confing.user;

/**
 * Created by 王炳炎 on 2017/5/7.
 */
public class PersonalFragment extends Fragment{

    TextView name;
    TextView num;
    TextView phone;
    TextView oredit;

    Context mContext;
    View contentView;
    public static PersonalFragment getFragment(){
        Bundle arguments = new Bundle();
        PersonalFragment tabContentFragment = new PersonalFragment();
        tabContentFragment.setArguments(arguments);
        return tabContentFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.personal_upside2, null);
        mContext = getActivity();
        ini();
        conent();

        return contentView;
    }
    private void conent() {
        if(Confing.LOGIN_STATE) {
            User u = Confing.user;
            name.setText(" " + u.getName());
            num.setText(" " + u.getStuNum());
            phone.setText(" " + u.getPhone());
            oredit.setText(" " + u.getOredit());
        }else {
            TextView t = (TextView) contentView.findViewById(R.id.tv_submit);
            t.setVisibility(View.GONE);
        }
    }

    private void ini() {
        name = (TextView)contentView. findViewById(R.id.personal_name);
        num = (TextView)contentView. findViewById(R.id.personal_num);
        phone = (TextView) contentView.findViewById(R.id.personal_phone);
        oredit = (TextView)contentView. findViewById(R.id.personal_oredit);
    }



    @Override
    public void onResume() {
        if(Confing.LOGIN_STATE){
            TextView t = (TextView) contentView.findViewById(R.id.tv_submit);
            t.setVisibility(View.VISIBLE);
            ImageView personal_img = (ImageView) contentView.findViewById(R.id.personal_img);
            personal_img.setImageDrawable(mContext.getResources().getDrawable(R.drawable.personal_finishal));
        }
        conent();
        super.onResume();
    }
}
