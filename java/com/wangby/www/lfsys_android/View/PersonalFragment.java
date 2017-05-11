package com.wangby.www.lfsys_android.View;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangby.www.lfsys_android.R;

/**
 * Created by 王炳炎 on 2017/5/7.
 */
public class PersonalFragment extends Fragment{

    Context mContext;
    public static PersonalFragment getFragment(){
        Bundle arguments = new Bundle();
        PersonalFragment tabContentFragment = new PersonalFragment();
        tabContentFragment.setArguments(arguments);
        return tabContentFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.personal_upside, null);
        mContext = getActivity();

        return contentView;
    }

}
