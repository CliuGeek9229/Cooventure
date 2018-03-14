package com.example.cooventure.util;

import java.util.ArrayList;
import java.util.List;

import com.example.cooventure.entity.User;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserManager {

	private MyOpenHelper helper;
	private SQLiteDatabase db;
	
	public UserManager(Context context){
		helper = new MyOpenHelper(context);
		db = helper.getWritableDatabase();
	}
	
	public void insert(User user){
		String sql = "insert into userInfo values(null,?,?,?,?,?,?)";
		db.execSQL(sql,new String[]{user.getName(),user.getPwd(),user.getRealname(),user.getSex(),user.getSchool(),user.getProfession()});
	}
	public void update(String column,String value,int key){
		String sql = "Update userInfo set "+column+" = '"+value+"' where _id = "+key;
		db.execSQL(sql);
	}
	public void delete(int id){
		String sql = "delete from userInfo where _id = "+id;
		db.execSQL(sql);
	}
	public List<User> query(){
		ArrayList<User> user = new ArrayList<User>();
		Cursor cs = db.rawQuery("select * from userInfo", null);
		while(cs.moveToNext()){
			User user1 = new User();
			user1.set_id(cs.getInt(cs.getColumnIndex("_id")));
			user1.setName(cs.getString(cs.getColumnIndex("username")));
			user1.setPwd(cs.getString(cs.getColumnIndex("pwd")));
			user1.setRealname(cs.getString(cs.getColumnIndex("realname")));
			user1.setSex(cs.getString(cs.getColumnIndex("sex")));
			user1.setSchool(cs.getString(cs.getColumnIndex("school")));
			user1.setProfession(cs.getString(cs.getColumnIndex("profession")));
			user.add(user1);
		}
		cs.close();
		return user;
	}
	
	public List<User> selectUser(String name){
		ArrayList<User> user = new ArrayList<User>();
		Cursor cs = db.rawQuery("select * from userInfo where username = '"+name+"'", null);
		while(cs.moveToNext()){
			User user1 = new User();
			user1.set_id(cs.getInt(cs.getColumnIndex("_id")));
			user1.setName(cs.getString(cs.getColumnIndex("username")));
			user1.setPwd(cs.getString(cs.getColumnIndex("pwd")));
			user1.setRealname(cs.getString(cs.getColumnIndex("realname")));
			user1.setSex(cs.getString(cs.getColumnIndex("sex")));
			user1.setSchool(cs.getString(cs.getColumnIndex("school")));
			user1.setProfession(cs.getString(cs.getColumnIndex("profession")));
			user.add(user1);
		}
		cs.close();
		return user;
	}
	
	public User Check(String name){
		User user = new User();
		Cursor cs = db.rawQuery("select * from userInfo where username = '"+name+"'", null);
		while(cs.moveToNext()){
			User user1 = new User();
			user1.set_id(cs.getInt(cs.getColumnIndex("_id")));
			user1.setName(cs.getString(cs.getColumnIndex("username")));
			user1.setPwd(cs.getString(cs.getColumnIndex("pwd")));
			user1.setRealname(cs.getString(cs.getColumnIndex("realname")));
			user1.setSex(cs.getString(cs.getColumnIndex("sex")));
			user1.setSchool(cs.getString(cs.getColumnIndex("school")));
			user1.setProfession(cs.getString(cs.getColumnIndex("profession")));
			user = user1;
		}
		cs.close();
		
		return user;
	}
	
	
	public boolean IsExist(String name){
		Cursor cs = db.rawQuery("select * from userInfo where username = '"+name+"'", null);
		while(cs.moveToNext()){
		if(cs.getString(cs.getColumnIndex("username")).equals(name)){
			return true;
		}}
		cs.close();
		return false;
		
	}
	public boolean IsTrue(String name,String pwd){
		Cursor cs = db.rawQuery("select * from userInfo where username = '"+name+"'", null);
		while(cs.moveToNext()){
		if(cs.getString(cs.getColumnIndex("username")).equals(name) && cs.getString(cs.getColumnIndex("pwd")).equals(pwd)){
			return true;
		}}
		return false;
	}
	public void close(){
		db.close();
	}
	
	
}
