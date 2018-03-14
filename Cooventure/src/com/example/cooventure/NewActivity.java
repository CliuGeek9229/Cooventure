package com.example.cooventure;

import java.util.Calendar;

import com.example.cooventure.app.MyApplication;
import com.example.cooventure.entity.Action;
import com.example.cooventure.util.ActionManager;

import android.os.Bundle;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class NewActivity extends Activity implements OnClickListener {
	private String pw;
	private String name;
	
	private TextView backtoMain;
	private EditText Activitytitle;
	private EditText Activitylocate;
	private TextView M_data1;
	private TextView M_time1;
	private EditText M_manager;
	private EditText M_detail1;
	private Button M_Add;
	
	private String title;
	private String locate;
	private String data;
	private String time;
	private String manager;
	private String detail;
	
	private MyApplication app;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_new);
		
		init();
	}
	
	void init(){
		backtoMain = (TextView) findViewById(R.id.backtomain);
		
		Activitytitle = (EditText) findViewById(R.id.ActivityTitle);
		Activitylocate = (EditText) findViewById(R.id.ActivityLocate);
		M_data1 = (TextView) findViewById(R.id.M_data);
		M_time1 = (TextView) findViewById(R.id.M_time);
		M_manager = (EditText) findViewById(R.id.M_Manager);
		M_detail1 = (EditText) findViewById(R.id.M_detail);
		
		M_Add = (Button) findViewById(R.id.M_ADD);
		
		backtoMain.setOnClickListener(NewActivity.this);
		M_Add.setOnClickListener(NewActivity.this);
		M_data1.setOnClickListener(this);
		M_time1.setOnClickListener(this);
		
		app = (MyApplication) getApplication();
		name = app.getName();
		pw = app.getPw();
	}
	void Same(){

		finish();
	}
	
	void Add(){
		boolean now = Other_cases();
		if(now){
			ActionManager am = new ActionManager(NewActivity.this);
			Action a = new Action();
			a.setTitle(title);
			a.setLocate(locate);
			a.setData(data);
			a.setTime(time);
			a.setManager(manager);
			a.setDetail(detail);
			a.setState("0");
			a.setUsername(name);
			am.insert(a);
			
			Toast.makeText(getApplicationContext(), "名为："+title+" 的新活动添加成功！", Toast.LENGTH_SHORT).show();
			am.close();
			Same();
		}else{
			Toast.makeText(getApplicationContext(), "添加新活动失败！请您完善活动信息！", Toast.LENGTH_SHORT).show();
		}
		
		
		
	}
	boolean Other_cases(){
		
		title = Activitytitle.getText().toString();
		locate = Activitylocate.getText().toString();
		data = M_data1.getText().toString();
		time = M_time1.getText().toString();
		manager = M_manager.getText().toString();
		detail = M_detail1.getText().toString();
		
		if(title.equals("")){
			Activitytitle.setError("标题不能为空！");
			return false;
		}
		if(locate.equals("")){
			Activitylocate.setError("活动地点不能为空！");
			return false;
		}
		if(data.equals("请选择日期")){
			M_data1.setError("日期不能为空！");
			return false;
		}
		if(time.equals("请选择时间")){
			M_time1.setError("时间不能为空！");
			return false;
		}
		if(manager.equals("")){
			M_manager.setError("主办单位不能为空！");
			return false;
		}
		if(detail.equals("")){
			M_detail1.setError("活动详情不能为空！");
			return false;
		}
		return true;
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
		case R.id.backtomain:
			Same();
			break;
		case R.id.M_ADD:
			Add();
			break;
		case R.id.M_data:
			Calendar c = Calendar.getInstance();
			new DatePickerDialog(NewActivity.this, new DatePickerDialog.OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker arg0, int year, int month, int day) {
					// TODO Auto-generated method stub
					M_data1.setText(year+"-"+month+"-"+day);
				}
			}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
			break;
		case R.id.M_time:
			Calendar c1 = Calendar.getInstance();
			new TimePickerDialog(NewActivity.this,new TimePickerDialog.OnTimeSetListener() {
				
				@Override
				public void onTimeSet(TimePicker arg0, int hour, int minute) {
					// TODO Auto-generated method stub
					M_time1.setText(hour+":"+minute);
				}
			},c1.get(Calendar.HOUR_OF_DAY),c1.get(Calendar.MINUTE),true).show();
			break;
		}
	}

}
