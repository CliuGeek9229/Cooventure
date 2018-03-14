package com.example.cooventure;

import com.example.cooventure.app.MyApplication;
import com.example.cooventure.entity.Action;
import com.example.cooventure.util.ActionManager;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class OtherActivity extends Activity implements OnClickListener {
	
	private MyApplication app;
	private String name;
	private String pw;
	private int _id;
	
	
	private TextView address_1;
	private TextView time_1;
	private TextView manager_1;
	private TextView state_1;
	private Button return_1;
	private Button delete_1;
	private TextView detail_1;
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ActionManager am = new ActionManager(OtherActivity.this);
		Action action = new Action();
		action = am.Action_id(_id);
		am.close();
		
		address_1.setText(action.getLocate());
		time_1.setText(action.getData()+"  "+action.getTime());
		manager_1.setText(action.getManager());
		String state111 = action.getState();
		if(state111.equals("0")){
			state_1.setText("活动未结束");
		}else{
			state_1.setText("活动已结束");
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_other);
		init();
		
		ActionManager am = new ActionManager(OtherActivity.this);
		Action action = new Action();
		action = am.Action_id(_id);
		am.close();
		
		address_1.setText(action.getLocate());
		time_1.setText(action.getData()+"  "+action.getTime());
		manager_1.setText(action.getManager());
		String state111 = action.getState();
		if(state111.equals("0")){
			state_1.setText("活动未结束");
		}else{
			state_1.setText("活动已结束");
		}
		
		
		
		
	}
	
	
	
	
	void init(){
		app = (MyApplication) getApplication();
		name = app.getName();
		pw = app.getPw();
		_id = app.get_id();
		
		address_1 = (TextView) findViewById(R.id.Address);
		time_1 = (TextView) findViewById(R.id.Time);
		manager_1 = (TextView) findViewById(R.id.Manager);
		state_1 = (TextView) findViewById(R.id.state);
		return_1 = (Button) findViewById(R.id.return11);
		delete_1 = (Button) findViewById(R.id.delete);
		detail_1  = (TextView) findViewById(R.id.details);
		
		return_1.setOnClickListener(this);
		delete_1.setOnClickListener(this);
		detail_1.setOnClickListener(this);
		
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
		switch (v.getId()){
		case R.id.return11 :
			finish();
			break;
		case R.id.delete:
			ActionManager am = new ActionManager(OtherActivity.this);
			am.delete(_id);
			am.close();
			finish();
			break;
		case R.id.details:
			Intent i = new Intent();
			i.setClass(OtherActivity.this, DetailActivity.class);
			startActivity(i);
			break;
		}
	}

}
