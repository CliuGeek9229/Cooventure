package com.example.cooventure;

import com.example.cooventure.app.MyApplication;
import com.example.cooventure.entity.Action;
import com.example.cooventure.util.ActionManager;

import android.os.Bundle;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends Activity implements OnClickListener {
	private MyApplication app;
	private String name;
	private String pw;
	private int _id;
	
	private TextView back_333;
	private TextView title_333;
	private TextView time_333;
	private TextView address_333;
	private TextView details_333;
	private Button over_333;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_details);
		init();
		
		ActionManager am = new ActionManager(DetailActivity.this);
		Action action = new Action();
		action = am.Action_id(_id);
		am.close();
		
		title_333.setText(action.getTitle());
		time_333.setText(action.getData()+"  "+action.getTime());
		address_333.setText(action.getLocate());
		details_333.setText(action.getDetail());
		
		
		
		
		
	}
	
	void init(){
		app = (MyApplication) getApplication();
		name = app.getName();
		pw = app.getPw();
		_id = app.get_id();
		back_333 = (TextView) findViewById(R.id.back_222);
		title_333 = (TextView) findViewById(R.id.title_222);
		time_333 = (TextView) findViewById(R.id.time_222);
		address_333 = (TextView) findViewById(R.id.address_222);
		details_333 = (TextView) findViewById(R.id.details_2222);
		over_333 = (Button) findViewById(R.id.over_222);
		
		back_333.setOnClickListener(this);
		over_333.setOnClickListener(this);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	void over(){
		ActionManager am = new ActionManager(DetailActivity.this);
		
		am.update("state","1",_id);
		am.close();
		Toast.makeText(getApplicationContext(), "当前活动已关闭！", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.back_222:
			finish();
			break;
		case R.id.over_222:
			over();
			finish();
			break;
		}
	}

}
