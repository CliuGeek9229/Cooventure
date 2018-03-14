package com.example.cooventure;

import java.util.ArrayList;

import com.example.cooventure.app.MyApplication;
import com.example.cooventure.entity.User;
import com.example.cooventure.util.UserManager;

import android.os.Bundle;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ModifyActivity extends Activity implements OnClickListener {
	private String name;
	private String pw;
	MyApplication app;
	
	private TextView back;
	private EditText username;
	private EditText password;
	private EditText realname;
	private EditText school;
	private EditText profession;
	private RadioGroup sex;
	private RadioButton man;
	private RadioButton woman;
	private Button modify;
	
	private String sex1 = "";
	private String sex2 = "";
	private String pwd2 = "";
	private String realname2 = "";
	private String school2 = "";
	private String profession2 = "";
	
	int _id = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_modify);
		
		init();
		
		UserManager usermanager = new UserManager(ModifyActivity.this);
        ArrayList<User> user = new ArrayList<User>();
        user = (ArrayList<User>) usermanager.selectUser(name);
        usermanager.close();
        
        int num = user.size();
        int index = num-1;

        while(num>0){
        	_id  = user.get(index).get_id();
        username.setText(user.get(index).getName());
        
        realname2 = user.get(index).getRealname();
        realname.setText(realname2);
        
        sex2 = sex1 = user.get(index).getSex();
        
        pwd2 = user.get(index).getPwd();
        password.setText(pwd2);
        
        school2 = user.get(index).getSchool();
        school.setText(school2);
        
        profession2 = user.get(index).getProfession();
        profession.setText(profession2);
        num--;
        }
        if(sex1.equals("男")){
        	sex2 = "男";
        	man.setChecked(true);
        }else{
        	sex2 = "女";
        	woman.setChecked(true);
        }
		
		username.setEnabled(false);
		
		sex.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				if(ModifyActivity.this.man.getId() == arg1){
					sex2 = "男";
				}
				if(ModifyActivity.this.woman.getId() == arg1){
					sex2 = "女";
				}
			}
		});
		
		
	}
	
	void init(){
		app = (MyApplication) getApplication();
		name = app.getName();
		pw = app.getPw();
		
		back = (TextView) findViewById(R.id.M_back1);
		username = (EditText) findViewById(R.id.M_name1);
		password = (EditText) findViewById(R.id.M_pwd1);
		realname = (EditText) findViewById(R.id.M_realName1);
		school = (EditText) findViewById(R.id.M_school1);
		profession = (EditText) findViewById(R.id.M_profession1);
		sex = (RadioGroup) findViewById(R.id.sex);
		man = (RadioButton) findViewById(R.id.M_man1);
		woman = (RadioButton) findViewById(R.id.M_woman1);
		modify = (Button) findViewById(R.id.modify);
		
		back.setOnClickListener(this);
		modify.setOnClickListener(this);
	}
	void doModify(){
		String pwd1 = password.getText().toString();
		String realname1 = realname.getText().toString();
		String school1 = school.getText().toString();
		String profession1 = profession.getText().toString();
		if(pwd1 != pwd2){
			UserManager um = new UserManager(ModifyActivity.this);
			um.update("pwd", pwd1, _id);
			um.close();
		}
		if(realname1 != realname2){
			UserManager um = new UserManager(ModifyActivity.this);
			um.update("realname", realname1, _id);
			um.close();
		}
		if(school1 != school2){
			UserManager um = new UserManager(ModifyActivity.this);
			um.update("school", school1, _id);
			um.close();
		}
		if(profession1 != profession2){
			UserManager um = new UserManager(ModifyActivity.this);
			um.update("profession", profession1, _id);
			um.close();
		}
		if(sex1 != sex2){
			UserManager um = new UserManager(ModifyActivity.this);
			um.update("sex", sex2, _id);
			um.close();
		}
		finish();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.M_back1:
			finish();
			break;
		case R.id.modify:
			doModify();
			break;
		}
	}

}
