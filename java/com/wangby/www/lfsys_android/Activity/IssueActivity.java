package com.wangby.www.lfsys_android.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wangby.www.lfsys_android.Object.Confing;
import com.wangby.www.lfsys_android.R;
import com.wangby.www.lfsys_android.connect.Function;
import com.wangby.www.lfsys_android.connect.Post;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by 王炳炎 on 2017/5/17.
 */
public class IssueActivity extends Activity implements View.OnClickListener {

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
    EditText goodstime;
    EditText tezhengs;
    EditText xiangxi;

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if(msg.what==1){
                Toast.makeText(mContext, "发布成功", Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(mContext, "发布失败", Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);}
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_found);
        mContext = this;
        goods = new Post();
        ini();


    }

    public void ini(){
        xiangxi = (EditText) findViewById(R.id.xiangxis);
        tezhengs = (EditText) findViewById(R.id.tezheng);
        goodstime = (EditText) findViewById(R.id.issue_time);
        goodplace= (EditText) findViewById(R.id.goodplaces);
        goodsname = (EditText) findViewById(R.id.goodsnames);
        data_list = new ArrayList<String>();
        data_list.add("生活用品");
        data_list.add("体育用品");
        data_list.add("学习用品");
        data_list.add("其他");
        mSpinner = (Spinner) findViewById(R.id.spinner1);
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(arr_adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                goods.setSubKindID(position);
                goods.setSubKindName(data_list.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                goods.setSubKindID(0);
                goods.setSubKindName(data_list.get(0));
            }
        });

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
        if(!TextUtils.isEmpty(goodsname.getText().toString().trim())){
            if(Confing.islost){
                goods.setType(0);
            }else {
                goods.setType(1);
            }
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

}
