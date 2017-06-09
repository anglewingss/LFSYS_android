package com.wangby.www.lfsys_android.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wangby.www.lfsys_android.Object.Confing;
import com.wangby.www.lfsys_android.R;
import com.wangby.www.lfsys_android.Tool.SqlTool;
import com.wangby.www.lfsys_android.connect.User;

import static com.wangby.www.lfsys_android.Object.Confing.user;

/**
 * Created by 王炳炎 on 2017/5/13.
 */
public class PersonalActivity extends Activity {

    TextView name;
    TextView num;
    TextView phone;
    TextView oredit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        ini();
        conent();

  }

    private void conent() {
        User u = Confing.user;
        name.setText(" "+u.getName());
        num.setText(" "+u.getStuNum());
        phone.setText(" "+u.getPhone());
        oredit.setText(" "+u.getOredit());
    }

    private void ini() {
        name = (TextView) findViewById(R.id.personal_name);
        num = (TextView) findViewById(R.id.personal_num);
        phone = (TextView) findViewById(R.id.personal_phone);
        oredit = (TextView) findViewById(R.id.personal_oredit);
    }

    public void exit(View v){
        Confing.LOGIN_STATE=false;
        SqlTool sqlTool = new SqlTool(this);
        sqlTool.delect("user");
        finish();
    }
    public void breakp(View v){
        finish();
    }

}
