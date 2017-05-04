package com.wangby.www.lfsys_android.Object;

import android.graphics.drawable.Drawable;

/**
 * Created by 王炳炎 on 2017/5/2.
 */

public class Issue extends Goods {
    public Drawable drawable;
    public String title;
    public Issue(Drawable drawable, String title){
        this.drawable = drawable;
        this.title = title;
    }
}
