package com.example.cooventure;

import java.util.ArrayList;

import com.example.cooventure.app.MyApplication;
import com.example.cooventure.entity.User;
import com.example.cooventure.util.UserManager;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	static Activity B11;
	private TextView register;
	private Button login;
	private EditText typein_name;
	private EditText typein_pwd;
	MyApplication app;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		init();
		
		B11  = this;
		
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent();
				i.setClass(LoginActivity.this, RegisterActivity.class);
				startActivity(i);
			}
		});
		
		
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				UserManager usermanager = new UserManager(LoginActivity.this);
				String Typein_name = typein_name.getText().toString();
				String Typein_password = typein_pwd.getText().toString();
				boolean Exist = usermanager.IsTrue(Typein_name, Typein_password);
				usermanager.close();
				if(Exist){
					Intent i = new Intent();
					

					
					app.setName(Typein_name);
					app.setPw(Typein_password);
					
					i.setClass(LoginActivity.this, MainActivity.class);
					startActivity(i);
					finish();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "该用户不存在或密码错误！", Toast.LENGTH_SHORT).show();
				}
				typein_name.setText("");
				typein_pwd.setText("");
				
				
			}
		});
		
		
		
	}
	
	void init(){
		register =  (TextView) findViewById(R.id.register);
		login = (Button) findViewById(R.id.login);
		typein_name = (EditText) findViewById(R.id.name);
		typein_pwd = (EditText) findViewById(R.id.password);
		app = (MyApplication) getApplication();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
