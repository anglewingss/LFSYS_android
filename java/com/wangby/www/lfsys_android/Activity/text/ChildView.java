package com.wangby.www.lfsys_android.Activity.text;

import java.util.ArrayList;
import java.util.LinkedList;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.wangby.www.lfsys_android.Object.Confing;
import com.wangby.www.lfsys_android.R;


public class ChildView extends LinearLayout {
	
	private ListView regionListView;   //主ListView
	private ListView plateListView;    //子ListView
	
	//主ListView每一个Item对应的text
	private String LeftFaString[] = new String[] {
			"生活用品",
			"现金及证件",
			"衣服及配件",
			"运动物品",
			"文具用品",
			"电子用品",
			"其他"
			};
	//子ListView每一个Item对应的text..采用了二维数组的实现方式..
	private String LeftCh1String[][] = new String[][] {
			{"水瓶", "安全帽", "雨伞", "背包", "其他"},
			{"钱包", "现金", "身份证", "学生证", "一卡通", "银行卡", "其他"},
			{"上衣", "裤子", "手表", "围巾", "帽子", "鞋子", "其他"},
			{"篮球", "足球", "球拍", "其他"},
			{"笔", "笔记本", "文件夹", "书","其他"},
			{"USB", "电脑", "耳机", "外设装备", "其他"},
			{ "光盘", "眼镜", "印章", "车票","其他"}
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
//	//主ListView每一个Item对应的text
//	private String LeftFaString[] = new String[] { "美食", "快餐小吃", "火锅", "海鲜/烧烤",
//			"特色菜", "香锅/烤鱼", "地方菜", "东南亚菜", "西餐", "日韩料理" };
//	//子ListView每一个Item对应的text..采用了二维数组的实现方式..
//	private String LeftCh1String[][] = new String[][] {
//			{ "全部" },
//			{ "全部", "中式简餐", "地方小吃", "盖浇饭", "米粉米线", "面馆", "麻辣烫", "黄焖鸡米饭",
//					"鸭脖卤味", "饺子馄饨", "炸鸡炸串", "包子/粥", "零食", "生煎锅贴", "冒菜" },
//			{ "全部", "其他火锅" }, { "全部", "小龙虾" }, { "全部" }, { "全部", "香锅", "烤鱼" },
//			{ "全部", "鲁菜", "川菜", "其他" }, { "全部" },
//			{ "全部", "意面披萨", "西式快餐", "其他西餐" }, { "全部", "韩式简餐", "韩国料理" } };

	//添加主ListView中的数据信息
	private ArrayList<String> groups = new ArrayList<String>();
	
	//添加子ListView中的数据信息
	private LinkedList<String> childrenItem = new LinkedList<String>();
	
	//稀疏数组
	private SparseArray<LinkedList<String>> children = new SparseArray<LinkedList<String>>();
	//为ListView设置适配器
	private TextAdapter plateListViewAdapter;
	private TextAdapter earaListViewAdapter;
	//监听事件的设置
	private OnSelectListener mOnSelectListener;
	
	private int tEaraPosition = 0;     //用于保存当前主ListView被点击的Item对应的Position.
	private int tBlockPosition = 0;	   //用于保存当前子ListView被点击的Item对应的Position.
	
	private String showString = "";

	public ChildView(Context context) {
		super(context);
		init(context);
	}

	public ChildView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}


	private void init(Context context) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		//加载布局,绑定ID.
		inflater.inflate(R.layout.view_region, this, true);
		regionListView = (ListView) findViewById(R.id.listView);
		plateListView = (ListView) findViewById(R.id.listView2);

		//初始化ListView中每一个item对应的text
		for(int i=0;i<LeftFaString.length;i++){
			groups.add(LeftFaString[i]);
			LinkedList<String> tItem = new LinkedList<String>();
			for(int j=0;j<LeftCh1String[i].length;j++){
				
				tItem.add(LeftCh1String[i][j]);
				
			}
			children.put(i, tItem);
		}

		//主ListView列表项的适配器
		earaListViewAdapter = new TextAdapter(context, groups,
				R.drawable.choose,
				R.drawable.choose_eara_item_selector);
		earaListViewAdapter.setTextSize(12);
		earaListViewAdapter.setSelectedPositionNoNotify(tEaraPosition);
		
		regionListView.setAdapter(earaListViewAdapter);
		
		earaListViewAdapter
				.setOnItemClickListener(new TextAdapter.OnItemClickListener() {

					@Override
					public void onItemClick(View view, int position) {
						if (position < children.size()) {
							childrenItem.clear();
							//获取这一页的所有数据信息..然后唤醒适配器更新数据
							childrenItem.addAll(children.get(position));
							plateListViewAdapter.notifyDataSetChanged();
							if(position!=6){
								Confing.goodId = position+2;
                				}else{
								Confing.goodId = 1;
                				}
						}
					}
				});
		
		if (tEaraPosition < children.size())
			childrenItem.addAll(children.get(tEaraPosition));
		
		
		//子ListView的适配器
		plateListViewAdapter = new TextAdapter(context, childrenItem,
				R.drawable.choose_item_right,
				R.drawable.choose_plate_item_selector);
		plateListViewAdapter.setTextSize(12);
		plateListViewAdapter.setSelectedPositionNoNotify(tBlockPosition);
		plateListView.setAdapter(plateListViewAdapter);
		//设置当Item被点击后触发的监听.
		plateListViewAdapter
				.setOnItemClickListener(new TextAdapter.OnItemClickListener() {

					@Override
					public void onItemClick(View view, final int position) {
						//获取被点击的Item的文字数据
						showString = childrenItem.get(position);
            			if(showString.equals("其他")){
							switch (Confing.goodId){
                    			case 1:
									Confing.SubKindID=40;
                        break;
                    case 2:
						Confing.SubKindID=9;
                        break;
                    case 3:
						Confing.SubKindID=18;
                        break;
                    case 4:
						Confing.SubKindID=24;
                        break;
                    case 5:
						Confing.SubKindID=34;
                        break;
                    case 6:
						Confing.SubKindID=33;
                        break;
                    case 7:
						Confing.SubKindID=38;
                        break;
                }
            }else {
            int i = 1;
            for(String ss:s1){
                if(showString.equals(ss)) {
					Confing.SubKindID=i;
                }
                i++;
            }}



						if (mOnSelectListener != null) {
							
							mOnSelectListener.getValue(showString);
						}

					}
				});
		
		if (tBlockPosition < childrenItem.size())
			showString = childrenItem.get(tBlockPosition);
		setDefaultSelect();

	}
    
	//设置当前Item的Position.
	public void setDefaultSelect() {
		//默认选择的Item项
		regionListView.setSelection(tEaraPosition);
		plateListView.setSelection(tBlockPosition);
	}

	public String getShowText() {
		return showString;
	}

	public void setOnSelectListener(OnSelectListener onSelectListener) {
		mOnSelectListener = onSelectListener;
	}

	public interface OnSelectListener {
		public void getValue(String showText);
	}

}
