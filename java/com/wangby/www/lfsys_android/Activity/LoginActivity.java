package com.wangby.www.lfsys_android.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wangby.www.lfsys_android.Object.Confing;
import com.wangby.www.lfsys_android.R;
import com.wangby.www.lfsys_android.Tool.SqlTool;
import com.wangby.www.lfsys_android.connect.Function;
import com.wangby.www.lfsys_android.connect.User;

/**
 * Created by 王炳炎 on 2017/5/11.
 */

public class LoginActivity extends Activity{

    private AutoCompleteTextView name =null;
    private EditText password =null;
    private CheckBox checkBox =null;
    private SqlTool sqlTool = null;
    private  boolean isChecked = true;
    private Context context=null;


    private TextView textView = null;

    private static final String[] COUNTRIES = new String[] {"1614010","1514010", "14141010", "13141010"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ini();
//        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
//        sqlTool = new SqlTool(this);
//        context = this;
//        User user = sqlTool.getUser();
//        if(user!=null){
//            name.setText(user.name);
//            password.setText(user.password);
//        }else {
//            Toast.makeText(this, "没有sql", Toast.LENGTH_SHORT).show();
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                    android.R.layout.simple_dropdown_item_1line, COUNTRIES);
//            name.setAdapter(adapter);
//        }

    }

    private void ini() {
        password = (EditText) findViewById(R.id.password);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        name =(AutoCompleteTextView) findViewById(R.id.name);
        textView = (TextView) findViewById(R.id.textView2);
        context=this;
        sqlTool = new SqlTool(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        name.setAdapter(adapter);
    }

    public void login(View v){
        String username = name.getText().toString().trim();
        String spassword = password.getText().toString().trim();
        final User user = new User();
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(spassword)) {
            Toast.makeText(this, "密码或账号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        isChecked = checkBox.isChecked();
        if(!isChecked)
            sqlTool.delect("user");
        Toast.makeText(context, spassword+Integer.parseInt(username), Toast.LENGTH_SHORT).show();
        user.setStuNum(Integer.parseInt(username));
        user.setPassword(spassword);
        new Thread(new Runnable() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "正在登陆", Toast.LENGTH_SHORT).show();
                    }
                });
                User result_user = Function.login(user);
                if (result_user!=null){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "登陆成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                    if(isChecked){
                        sqlTool.delect("user");
                        sqlTool.saveUser(result_user);
                    }
                    Confing.LOGIN_STATE = true;
                    LoginActivity.this.finish();
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "登陆失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }}).start();











    }
//
//    protected void onStop() {
//        isChecked = checkBox.isChecked();
//        if(isChecked){
//            String username = name.getText().toString().trim();
//            String spassword = password.getText().toString().trim();
//            final User fuser = new User();
//            fuser.name = username;
//            fuser.password = spassword;
//            sqlTool.delect("user");
//            sqlTool.saveUser(fuser);
//        }else {
//            sqlTool.delect("user");
//        }
//        super.onStop();
//        getDelegate().onStop();
//        login.this.finish();
//    }
//
//
//
//
//
//    private String requestLogin(User user) {
//        try {
//            URL url = new URL("http://www.godzone.cn/user");
//            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
//            connect.setRequestMethod("POST");
//            connect.setConnectTimeout(5*1000);
//            connect.setRequestProperty("Content-Length","45");
//            connect.setRequestProperty("Cache-Control","max-age=0");
//            connect.setRequestProperty("Origin","http://www.godzone.cn");
//            connect.setDoOutput(true);
//            String s = "StuNum="+user.name+"&UPasswd="+user.password+"&action=login";
//            connect.getOutputStream().write(s.getBytes());
//
//            int code = connect.getResponseCode();
//            if(code == 200){
//                InputStream input = connect.getInputStream();
//                String result = StreamTool.getString(input);
//                return result;
//            }
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


}
