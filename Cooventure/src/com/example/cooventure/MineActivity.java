package com.example.cooventure;

import java.util.ArrayList;

import com.example.cooventure.app.MyApplication;
import com.example.cooventure.entity.Action;
import com.example.cooventure.entity.User;
import com.example.cooventure.util.ActionManager;
import com.example.cooventure.util.UserManager;

import android.os.Bundle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MineActivity extends Activity {
	private TextView M_BackToMain;
	
	private TextView M_Name;
	private TextView M_Realname;
	private TextView M_Sex;
	private TextView M_School;
	private TextView M_Profession;
	
	private TextView M_MyActivity;
	
	private Button Logout;
	
	private String pw;
	private String name;
	
	private MyApplication app;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_mine);
		
		init();
		
		UserManager usermanager = new UserManager(MineActivity.this);
        ArrayList<User> user = new ArrayList<User>();
        user = (ArrayList<User>) usermanager.selectUser(name);
        usermanager.close();
        int num = user.size();
        int index = num-1;

        while(num>0){
        M_Name.setText(user.get(index).getName());
        M_Realname.setText(user.get(index).getRealname());
        M_Sex.setText(user.get(index).getSex());
        M_School.setText(user.get(index).getSchool());
        M_Profession.setText(user.get(index).getProfession());
        num--;
        }
        
        
        
        
		M_BackToMain.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				finish();
			}
		});
		M_MyActivity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent();
				i.setClass(MineActivity.this, DetailActivity.class);
				startActivity(i);
			}
		});
		Logout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dialog1();
			}
		});
		M_MyActivity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent();
				i.setClass(MineActivity.this, ModifyActivity.class);
				startActivity(i);
			}
		});
		
		
		
	}
	
	

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		UserManager usermanager = new UserManager(MineActivity.this);
        ArrayList<User> user = new ArrayList<User>();
        user = (ArrayList<User>) usermanager.selectUser(name);
        usermanager.close();
        int num = user.size();
        int index = num-1;

        while(num>0){
        M_Name.setText(user.get(index).getName());
        M_Realname.setText(user.get(index).getRealname());
        M_Sex.setText(user.get(index).getSex());
        M_School.setText(user.get(index).getSchool());
        M_Profession.setText(user.get(index).getProfession());
        num--;
        }
		
		
	}



	private void dialog1(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("提示"); //设置标题
        builder.setMessage("是否确认退出?"); //设置内容
        builder.setIcon(R.drawable.ic_launcher);//设置图标，图片id即可
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //关闭dialog
//                Toast.makeText(MineActivity.this, "确认" + which, Toast.LENGTH_SHORT).show();
                Intent i = new Intent();

				i.setClass(MineActivity.this, LoginActivity.class);
				startActivity(i);
				
				MainActivity.A11.finish();
				finish();
               // finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
//                Toast.makeText(MineActivity.this, "取消" + which, Toast.LENGTH_SHORT).show();
            }
        });
        //参数都设置完成了，创建并显示出来
        builder.create().show();
    }
	
	
	
	
	void init(){
		M_BackToMain = (TextView) findViewById(R.id.BackToMain);
		M_Name = (TextView) findViewById(R.id.M_name);
		M_Realname = (TextView) findViewById(R.id.M_realname);
		M_Sex = (TextView) findViewById(R.id.M_sex);
		M_School = (TextView) findViewById(R.id.M_school);
		M_Profession = (TextView) findViewById(R.id.M_profession);
		M_MyActivity = (TextView) findViewById(R.id.M_myActivity);
		Logout = (Button) findViewById(R.id.Logout);
		
		app = (MyApplication) getApplication();
		name = app.getName();
		pw = app.getPw();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
