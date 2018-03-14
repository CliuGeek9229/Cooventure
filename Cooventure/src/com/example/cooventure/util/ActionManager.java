package com.example.cooventure.util;

import java.util.ArrayList;
import java.util.List;

import com.example.cooventure.entity.Action;
import com.example.cooventure.entity.User;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class ActionManager {

	private MyOpenHelper helper;
	private SQLiteDatabase db;
	
	public ActionManager(Context context){
		helper = new MyOpenHelper(context);
		db = helper.getWritableDatabase();
	}
	public void insert(Action action){
		String sql = "insert into actionInfo(_id,manager,title,time,locate,username,data,state,detail) values(null,?,?,?,?,?,?,?,?)";
		db.execSQL(sql,new String[]{action.getManager(),action.getTitle(),action.getTime(),action.getLocate(),action.getUsername(),action.getData(),action.getState(),action.getDetail()});
	}
	public void update(String column,String value,int key){
		String sql = "Update actionInfo set "+column+" = '"+value+"' where _id = "+key;
		db.execSQL(sql);
	}
	public void delete(int id){
		String sql = "delete from actionInfo where _id = "+id;
		db.execSQL(sql);
	}
	public List<Action> query(){
		ArrayList<Action> action = new ArrayList<Action>();
		Cursor cs = db.rawQuery("select * from actionInfo", null);
		while(cs.moveToNext()){
			Action action1 = new Action();
			action1.set_id(cs.getInt(cs.getColumnIndex("_id")));
			action1.setManager(cs.getString(cs.getColumnIndex("Manager")));
			action1.setTitle(cs.getString(cs.getColumnIndex("title")));
			action1.setTime(cs.getString(cs.getColumnIndex("time")));
			action1.setData(cs.getString(cs.getColumnIndex("data")));
			action1.setLocate(cs.getString(cs.getColumnIndex("locate")));
			action1.setDetail(cs.getString(cs.getColumnIndex("detail")));
			action1.setState(cs.getString(cs.getColumnIndex("state")));
			action1.setUsername(cs.getString(cs.getColumnIndex("username")));
			action.add(action1);
		}
		cs.close();
		return action;
	}
	public List<Action> Actionquery(String name){
		ArrayList<Action> action = new ArrayList<Action>();
		Cursor cs = db.rawQuery("select * from actionInfo where username = '"+name+"'", null);
		while(cs.moveToNext()){
			Action action1 = new Action();
			action1.set_id(cs.getInt(cs.getColumnIndex("_id")));
			action1.setManager(cs.getString(cs.getColumnIndex("manager")));
			action1.setTitle(cs.getString(cs.getColumnIndex("title")));
			action1.setTime(cs.getString(cs.getColumnIndex("time")));
			action1.setData(cs.getString(cs.getColumnIndex("data")));
			action1.setLocate(cs.getString(cs.getColumnIndex("locate")));
			action1.setDetail(cs.getString(cs.getColumnIndex("detail")));
			action1.setState(cs.getString(cs.getColumnIndex("state")));
			action1.setUsername(cs.getString(cs.getColumnIndex("username")));
			action.add(action1);
		}
		cs.close();
		return action;
	}
	
	public Action Action_id(int Id){
		Action action1 = new Action();
		Cursor cs = db.rawQuery("select * from actionInfo where _id = '"+Id+"'", null);
		while(cs.moveToNext()){
			//Action action1 = new Action();
			action1.set_id(cs.getInt(cs.getColumnIndex("_id")));
			action1.setManager(cs.getString(cs.getColumnIndex("manager")));
			action1.setTitle(cs.getString(cs.getColumnIndex("title")));
			action1.setTime(cs.getString(cs.getColumnIndex("time")));
			action1.setData(cs.getString(cs.getColumnIndex("data")));
			action1.setLocate(cs.getString(cs.getColumnIndex("locate")));
			action1.setDetail(cs.getString(cs.getColumnIndex("detail")));
			action1.setState(cs.getString(cs.getColumnIndex("state")));
			action1.setUsername(cs.getString(cs.getColumnIndex("username")));
		
		}
		cs.close();
		return action1;
	}
	
	public void close(){
		db.close();
	}
	
	
	
	
}

