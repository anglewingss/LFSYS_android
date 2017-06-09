package com.wangby.www.lfsys_android.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.wangby.www.lfsys_android.Activity.text.ChildView;
import com.wangby.www.lfsys_android.Activity.text.ExpandTabView;
import com.wangby.www.lfsys_android.Object.Confing;
import com.wangby.www.lfsys_android.R;
import com.wangby.www.lfsys_android.connect.Function;
import com.wangby.www.lfsys_android.connect.Post;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.content.DialogInterface.BUTTON_POSITIVE;


/**
 * Created by 王炳炎 on 2017/5/17.
 */
public class IssueActivity extends Activity implements View.OnClickListener {



    //二级菜单

    private ExpandTabView expandTabView;
    private ArrayList<View> mViewArray = new ArrayList<View>();
    private ChildView viewLeft;




    Dialog mDialog;
    Context mContext;
    TextView takePic;
    TextView cancelTxt;
    TextView selectAlbum;
    Uri imageUri;
    ImageView picture;
    Spinner mSpinner;
    List<String> data_list;
    ArrayAdapter<String> arr_adapter;
    Post goods;
    TextView textView;

    EditText goodsname;
    EditText goodplace;
    TextView goodstime;
    EditText tezhengs;
    EditText xiangxi;
    String string[][]={
        { "其他","光盘",
                "眼镜",
                "印章",
                "车票"},{
                "水瓶",
                "安全帽",
                "雨伞",
                "背包",
                "其他"},{
                "上衣",
                "裤子",
                "手表",
                "围巾",
                "帽子",
                "鞋子",
                "其他"},{
                "钱包",
                "现金",
            "身份证",
                "学生证",
                "一卡通",
                "银行卡",
                "其他"},{
                "笔",
                "笔记本",
                "文件夹",
                "书","其他"},{
                "USB",
                "电脑",
                "耳机",
                "外设装备",
                "其他"},{
                "篮球",
                "足球",
                "球拍",
                "其他"}
    };

         String s1[]={                            "光盘",
                                         "眼镜",
                                         "印章",
                                         "车票",
                                         "水瓶",
                                         "安全帽",
                                         "雨伞",
                                         "背包",
                                         "其他",
                                         "上衣",
                                         "裤子",
                                         "手表",
                                         "围巾",
                                         "帽子",
                                         "鞋子",
                                         "其他",
                                         "钱包",
                                         "现金",
                                         "学生证",
                                         "一卡通",
                                         "银行卡",
                                         "其他",
                                         "笔",
                                         "笔记本",
                                         "文件夹",
                                         "书",
                                         "USB",
                                         "电脑",
                                         "耳机",
                                         "外设装备",
                                         "其他",
                                         "其他",
                                         "篮球",
                                         "足球",
                                         "球拍",
                                         "其他",
                                         "身份证",
                                         "其他"};

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if(msg.what==1){
                Toast.makeText(mContext, "发布成功", Toast.LENGTH_SHORT).show();
                finish();
            }else if(msg.what==0){
                Toast.makeText(mContext, "发布失败", Toast.LENGTH_SHORT).show();
            }else if(msg.what==2){
                showDialog();
            }
            super.handleMessage(msg);}
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_found);
        mContext = this;
        goods = new Post();
        ini();
        //二级菜单初始化
        initow();


    }

    private void initow() {
        initView();
        initVaule();
        initListener();
    }

    private void initView() {

        //初始化控件
        expandTabView = (ExpandTabView) findViewById(R.id.expandtab_view);
        viewLeft = new ChildView(this);

    }

    private void initVaule() {

        mViewArray.add(viewLeft);

        //设置顶部数据信息
        ArrayList<String> mTextArray = new ArrayList<String>();
        mTextArray.add("全部");
        expandTabView.setValue(mTextArray, mViewArray);
        expandTabView.setTitle(viewLeft.getShowText(), 0);

    }

    private void initListener() {


        viewLeft.setOnSelectListener(new ChildView.OnSelectListener() {

            @Override
            public void getValue(String showText) {

                onRefresh(viewLeft,showText);

            }
        });


    }

    //视图被点击后刷新数据
    private void onRefresh(View view, String showText) {

        expandTabView.onPressBack();
        int position = getPositon(view);
        if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
            expandTabView.setTitle(showText, position);
        }
        Toast.makeText(this, showText, Toast.LENGTH_SHORT).show();

    }

    //获取当前的view
    private int getPositon(View tView) {
        for (int i = 0; i < mViewArray.size(); i++) {
            if (mViewArray.get(i) == tView) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onBackPressed() {

        if (!expandTabView.onPressBack()) {
            finish();
        }

    }












    public void ini(){
        xiangxi = (EditText) findViewById(R.id.xiangxis);
        tezhengs = (EditText) findViewById(R.id.tezheng);
        goodstime = (TextView) findViewById(R.id.issue_time);

        goodstime.setOnClickListener(new View.OnClickListener() {
            Calendar c = Calendar.getInstance();
            @Override
            public void onClick(View v) {

                DatePickerDialog d = new DatePickerDialog(IssueActivity.this,DatePickerDialog.THEME_HOLO_LIGHT,
                        // 绑定监听器
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                monthOfYear++;
                                goodstime.setText( year + "年" + monthOfYear
                                        + "月" + dayOfMonth + "日");
                                SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                String time=year+"-"+monthOfYear+"-"+dayOfMonth+" 11:45:55";
                                Date date = null;
                                try {
                                    date = format.parse(time);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                goods.setTime( date.getTime());
                            }
                        }
                        // 设置初始日期
                        , c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
                        .get(Calendar.DAY_OF_MONTH));
                d.getDatePicker().setMaxDate((new Date()).getTime());
                d.show();

            }
        });


        goodplace= (EditText) findViewById(R.id.goodplaces);
        goodsname = (EditText) findViewById(R.id.goodsnames);
//        data_list = new ArrayList<String>();
//        data_list.add("生活用品");
//        data_list.add("衣服及配件");
//        data_list.add("现金及证件");
//        data_list.add("文具用品");
//        data_list.add("电子用品");
//        data_list.add("运动物品");
//        data_list.add("其他");
//        mSpinner = (Spinner) findViewById(R.id.spinner1);
//        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
//        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mSpinner.setAdapter(arr_adapter);
//        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(position!=6){
//                    n = position+2;
//                }else{
//                    n = 1;
//                }
//                goods.setId(n);
//                a++;
//                new Thread(new Runnable() {
//                    public void run() {
//                        Message m = handler.obtainMessage();
//                        m.what=2;
//                        handler.sendMessage(m);
//                    }
//                }).start();
//
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                goods.setSubKindID(0);
//                goods.setSubKindName(data_list.get(0));
//            }
//        });

        textView = (TextView) findViewById(R.id.issue_time_text);
        if(Confing.islost){
            textView.setText("丢失时间");
        }
        picture = (ImageView) findViewById(R.id.imgiss);
        mDialog = new Dialog(mContext, R.style.MyDialog);
        mDialog.setContentView(R.layout.issue_dialog);
        mDialog.setCanceledOnTouchOutside(true);//对话框的外面点击，是否让对话框消失
        takePic = (TextView) mDialog.findViewById(R.id.take_apictures);
        cancelTxt = (TextView) mDialog.findViewById(R.id.select_cancel);
        selectAlbum = (TextView) mDialog.findViewById(R.id.select_photo);

        takePic.setOnClickListener( this);
        selectAlbum.setOnClickListener(this);
        cancelTxt.setOnClickListener(this);

    }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.take_apictures:
                takeph();
                mDialog.dismiss();
                break;
            case R.id.select_photo:
                openAlbum();
            case R.id.select_cancel:
                mDialog.dismiss();
                break;

        }
    }

    private void takeph() {
        File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT < 24) {
            imageUri = Uri.fromFile(outputImage);
        } else {
            imageUri = FileProvider.getUriForFile(IssueActivity.this, "com.example.cameraalbumtest.fileprovider", outputImage);
        }
        // 启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, 1);
    }

    public void  sendss(View v){
        Toast.makeText(mContext, "id="+Confing.goodId+"id2="+Confing.SubKindID, Toast.LENGTH_SHORT).show();
        if(!TextUtils.isEmpty(goodsname.getText().toString().trim())&&!TextUtils.isEmpty(tezhengs.getText().toString().trim())){
            if(Confing.islost){
                goods.setType(2001);
            }else {
                goods.setType(2002);
            }
            goods.setId(Confing.goodId);
            goods.setSubKindID(Confing.SubKindID);
            goods.setPlace(goodplace.getText().toString());
            goods.setGoodsName(goodsname.getText().toString());
            goods.setDecp(tezhengs.getText().toString());
            goods.setDatail(xiangxi.getText().toString());
            goods.setPublishTime(new Date().getTime());
            goodstime.getText().toString();
            goods.setStuNum(Confing.user.getStuNum());
            new Thread(new Runnable() {
                public void run() {
                    Message m = handler.obtainMessage();
                    if(Confing.islost){
                        if( Function.setLost(goods)){
                            m.what=1;
                            handler.sendMessage(m);
                        }else {
                            m.what=0;
                            handler.sendMessage(m);
                        }

                    }else {
                        if(Function.setFound(goods)){
                            m.what=1;
                            handler.sendMessage(m);
                        }else {
                            m.what=0;
                            handler.sendMessage(m);
                        }
                    }


                }
            }).start();
        }else {

            Toast.makeText(mContext, "不能为空", Toast.LENGTH_SHORT).show();
        }



    }

    public void addimg(View v){
        mDialog.show();
    }

    public void back(View v){
        finish();
    }

    private void openAlbum() {
        if (ContextCompat.checkSelfPermission(IssueActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(IssueActivity.this, new String[]{ Manifest.permission. WRITE_EXTERNAL_STORAGE }, 1);
        } else {
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.setType("image/*");
            startActivityForResult(intent, 2); // 打开相册
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    try {
                        // 将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    // 判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        // 4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    } else {
                        // 4.4以下系统使用这个方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        Log.d("TAG", "handleImageOnKitKat: uri is " + uri);
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath); // 根据图片路径显示图片
    }


    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    int n;
    int a=0;
    void showDialog(){
        final AlertDialog.Builder builder=new AlertDialog.Builder(this)
                .setTitle("类别");
        builder.setItems(string[n%8-1],new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String s = string[n-1][which];
            if(s.equals("其他")){
                switch (n){
                    case 1:
                        goods.setSubKindID(40);
                        break;
                    case 2:
                        goods.setSubKindID(9);
                        break;
                    case 3:
                        goods.setSubKindID(18);
                        break;
                    case 4:
                        goods.setSubKindID(24);
                        break;
                    case 5:
                        goods.setSubKindID(34);
                        break;
                    case 6:
                        goods.setSubKindID(33);
                        break;
                    case 7:
                        goods.setSubKindID(38);
                        break;
                }

                return;
            }
            int i = 1;
            for(String ss:s1){
                if(s.equals(ss)) {
                    goods.setSubKindID(i);
                    return;
                }
                i++;
            }


            }
        });
        if(a>1){
            builder.create().show();
        }
    }


}
//    AlertDialog.Builder builder = new AlertDialog.Builder(mContext).setTitle("类别").setMultiChoiceItems(
//            string[n%8-1]
//            , null, null)
//                                 .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//        @Override
//        public void onClick(DialogInterface dialog, int which) {
//            String s = string[n-1][which];
//            if(s.equals("其他")){
//                switch (n){
//                    case 1:
//                        goods.setSubKindID(40);
//                        break;
//                    case 2:
//                        goods.setSubKindID(9);
//                        break;
//                    case 3:
//                        goods.setSubKindID(18);
//                        break;
//                    case 4:
//                        goods.setSubKindID(24);
//                        break;
//                    case 5:
//                        goods.setSubKindID(34);
//                        break;
//                    case 6:
//                        goods.setSubKindID(33);
//                        break;
//                    case 7:
//                        goods.setSubKindID(38);
//                        break;
//                }
//
//                return;
//            }
//            int i = 1;
//            for(String ss:s1){
//                if(s.equals(ss)) {
//                    goods.setSubKindID(i);
//                    return;
//                }
//                i++;
//            }
//
//        }
//    }).setNegativeButton("取消", null);
//


//                                 "光盘",
//                                         "眼镜",
//                                         "印章",
//                                         "车票",
//                                         "水瓶",
//                                         "安全帽",
//                                         "雨伞",
//                                         "背包",
//                                         "其他",
//                                         "上衣",
//                                         "裤子",
//                                         "手表",
//                                         "围巾",
//                                         "帽子",
//                                         "鞋子",
//                                         "其他",
//                                         "钱包",
//                                         "现金",
//                                         "学生证",
//                                         "一卡通",
//                                         "银行卡",
//                                         "其他",
//                                         "笔",
//                                         "笔记本",
//                                         "文件夹",
//                                         "书",
//                                         "USB",
//                                         "电脑",
//                                         "耳机",
//                                         "外设装备",
//                                         "其他",
//                                         "其他",
//                                         "篮球",
//                                         "足球",
//                                         "球拍",
//                                         "其他",
//                                         "身份证",
//                                         "其他"
