package com.example.cooventure;

import com.example.cooventure.app.MyApplication;
import com.example.cooventure.entity.User;
import com.example.cooventure.util.UserManager;

import android.os.Bundle;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	
	private TextView BackToLogin;
	private Button register;
	private EditText Rname;
	private EditText Rpwd;
	private EditText RTpwd;
	private EditText Rrealname;
	private EditText Rschool;
	private EditText Rprofession;
	private RadioGroup sex;
	private RadioButton man;
	private RadioButton woman;
	private String RadioSelect = "男";
	MyApplication app;
	
	private boolean fff;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
		init();
		
		BackToLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
	sex.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup arg0, int arg1) {
			// TODO Auto-generated method stub
			if(RegisterActivity.this.man.getId() == arg1){
				RadioSelect = "男";
			}
			if(RegisterActivity.this.woman.getId() == arg1){
				RadioSelect = "女";
			}
		}
	});
		
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				fff = true;
				
				String name = Rname.getText().toString();
				String pwd = Rpwd.getText().toString();
				String Tpwd = RTpwd.getText().toString();
				String realname = Rrealname.getText().toString();
				String school = Rschool.getText().toString();
				String profession = Rprofession.getText().toString();
				
				other_cases(name,pwd,Tpwd,realname,school,profession);
				
				
				
			}
		});
		
		
		
		
	}
	void other_cases(String A,String B,String C,String D,String E,String F){
		if(A.equals("")){
			Rname.setError("请输入用户名");
			fff = false;
		}
		if(B.equals("")){
			Rpwd.setError("请输入密码");
			fff = false;
		}
		if(C.equals("")){
			RTpwd.setError("请再次输入密码");
			fff = false;
		}
		if(D.equals("")){
			Rrealname.setError("请输入真实姓名");
			fff = false;
		}
		if(E.equals("")){
			Rschool.setError("请输入学校");
			fff = false;
		}
		if(F.equals("")){
			Rprofession.setError("请输入专业");
			fff = false;
		}
		if(B.length()<6){
			Rpwd.setError("密码必须大于6位并小于16位！");
			fff = false;
		}
		if(C.length()<6){
			RTpwd.setError("确认密码必须大于6位并小于16位！");
			fff = false;
		}
		
		if(!B.equals(C)){
			Toast.makeText(getApplicationContext(), "两次输入的密码不一致，请重新输入！", Toast.LENGTH_SHORT).show();
			fff = false;
		}
		
		UserManager usermanager = new UserManager(RegisterActivity.this);
		boolean isexist = usermanager.IsExist(A);
		if(!fff){
			Toast.makeText(getApplicationContext(), "请在对应项处进行修改！", Toast.LENGTH_SHORT).show();
		}
		else {if(isexist){
			Toast.makeText(getApplicationContext(), "该用户名已存在，请重新输入！", Toast.LENGTH_SHORT).show();
			Rname.setText("");
		}else{
			User user = new User();
			user.setName(A);
			user.setPwd(B);
			user.setRealname(D);
			user.setSchool(E);
			user.setProfession(F);
			user.setSex(RadioSelect);
			usermanager.insert(user);
			usermanager.close();
			
			Toast.makeText(getApplicationContext(), "恭喜您！注册成功！欢迎加入睿创网！", Toast.LENGTH_SHORT).show();
			
			Intent i = new Intent();
			
			app = (MyApplication) getApplication();
			app.setName(A);
			app.setPw(B);
			
			i.setClass(RegisterActivity.this, MainActivity.class);
			startActivity(i);
			
			LoginActivity.B11.finish();
			finish();
		}
		}
	}
	void init()
	{
		BackToLogin = (TextView) findViewById(R.id.BackToLogin);
		
		register = (Button) findViewById(R.id.register);
		
		Rname = (EditText) findViewById(R.id.Rname);
		Rpwd = (EditText) findViewById(R.id.Rpwd);
		RTpwd = (EditText) findViewById(R.id.RTpwd);
		Rrealname = (EditText) findViewById(R.id.RrealName);
		Rschool = (EditText) findViewById(R.id.Rschool);
		Rprofession = (EditText) findViewById(R.id.Rprofession);
		
		sex = (RadioGroup) findViewById(R.id.sex);
		man = (RadioButton) findViewById(R.id.man);
		man.setChecked(true);
		woman = (RadioButton) findViewById(R.id.woman);
		
		fff = true;
		
		Rname.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
		Rpwd.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
		RTpwd.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
		Rrealname.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
		Rschool.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
		Rprofession.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
