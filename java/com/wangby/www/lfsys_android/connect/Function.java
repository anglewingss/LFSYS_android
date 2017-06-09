package com.wangby.www.lfsys_android.connect;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

public class Function {

	private static final NetWorking net = new NetWorking("10.10.60.57",12345);

	public static User login(User user){
		User temp = null;
		try {
			DataPackage data=new DataPackage(MessageType.login,user.toString());
			DataPackage result=net.request(data);
			if(result.getString().equals(Error.loginError)){
				temp = null;
			}else{
				temp = new User(result.getString());
			}
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return temp;
	}

	public static List<Post> showMyPublish(int stuNum){
		List<Post> list = new LinkedList<Post>();
		try {
			DataPackage data=new DataPackage(MessageType.queryMyPublish,String.valueOf(stuNum));
			DataPackage result=net.request(data);
			if(result.getString().equals(Error.showError)){
				list = null;
			}else{
				JSONArray json = new JSONArray(result.getString());
				for(int i=0;i<json.length();i++){
					String str = json.getString(i);
					list.add(new Post(str));
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Post> showFound(){
		List<Post> list = new LinkedList<Post>();
		try {
			DataPackage data=new DataPackage(MessageType.queryFound,"");
			DataPackage result=net.request(data);
			if(result.getString().equals(Error.showError)){
				list = null;
			}else{
				JSONArray json = new JSONArray(result.getString());
				for(int i=0;i<json.length();i++){
					String str = json.getString(i);
					list.add(new Post(str));
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Post> showLost(){
		List<Post> list = new LinkedList<Post>();
		try {
			DataPackage data=new DataPackage(MessageType.queryLost,"");
			DataPackage result=net.request(data);
			if(result.getString().equals(Error.showError)){
				list = null;
			}else{
				JSONArray json = new JSONArray(result.getString());
				for(int i=0;i<json.length();i++){
					String str = json.getString(i);
					list.add(new Post(str));
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Argument> showArgument(long fnum){
		List<Argument> list = new LinkedList<Argument>();
		try {
			DataPackage data=new DataPackage(MessageType.queryArgument,String.valueOf(fnum));
			DataPackage result=net.request(data);
			if(result.getString().equals(Error.showError)){
				list = null;
			}else{
				JSONArray json = new JSONArray(result.getString());
				for(int i=0;i<json.length();i++){
					String str = json.getString(i);
					list.add(new Argument(str));
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Clue> showClue(long lnum){
		List<Clue> list = new LinkedList<Clue>();
		try {
			DataPackage data=new DataPackage(MessageType.queryClue,String.valueOf(lnum));
			DataPackage result=net.request(data);
			if(result.getString().equals(Error.showError)){
				list = null;
			}else{
				JSONArray json = new JSONArray(result.getString());
				for(int i=0;i<json.length();i++){
					String str = json.getString(i);
					list.add(new Clue(str));
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static boolean setFound(Post found){
		boolean bool = false;
		try {
			DataPackage data = new DataPackage(MessageType.uploadFound,found.toString());
			DataPackage result=net.request(data);
			if(result.getString().equals(Error.setFinsh)){
				bool=true;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return bool;
	}

	public static boolean setLost(Post lost){
		boolean bool = false;
		try {
			DataPackage data = new DataPackage(MessageType.uploadLost,lost.toString());
			DataPackage result=net.request(data);
			if(result.getString().equals(Error.setFinsh)){
				bool=true;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return bool;
	}

	public static boolean setArgument(Argument argument){
		boolean bool = false;
		try {
			DataPackage data = new DataPackage(MessageType.uploadArgument,argument.toString());
			DataPackage result=net.request(data);
			if(result.getString().equals(Error.setFinsh)){
				bool=true;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return bool;
	}

	public static boolean setClue(Clue clue){
		boolean bool = false;
		try {
			DataPackage data = new DataPackage(MessageType.uploadClue,clue.toString());
			DataPackage result=net.request(data);
			if(result.getString().equals(Error.setFinsh)){
				bool=true;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return bool;
	}
}

