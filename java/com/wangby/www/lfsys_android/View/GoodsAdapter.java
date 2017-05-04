package com.wangby.www.lfsys_android.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wangby.www.lfsys_android.Object.Goods;
import com.wangby.www.lfsys_android.R;
import com.wangby.www.lfsys_android.Tool.ImgTool;

import java.util.ArrayList;

public class GoodsAdapter extends BaseAdapter {
	
	private ArrayList<Goods> list;
	private Context context;

	//通过构造方法接受要显示的新闻数据集合
	public GoodsAdapter(Context context,ArrayList<Goods> list){
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if(convertView != null){
			view = convertView;
		}else {
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.goods_list, null);
		}
		ImgTool img = (ImgTool) view.findViewById(R.id.imageView);
		TextView textView = (TextView) view.findViewById(R.id.textView);
		TextView textView1 = (TextView) view.findViewById(R.id.textView2);
		Goods dataMode = list.get(position);
		textView1.setText(dataMode.Gname);
		textView.setText("地点："+dataMode.place+"\n时间："+dataMode.time+"\n"+dataMode.Uname+dataMode.Uid);
//		img.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.nullimg, null));
		img.setImageUrl(dataMode.Gimg);
		return view;
	}


}
