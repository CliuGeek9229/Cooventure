package com.example.cooventure;

import java.util.ArrayList;

import com.example.cooventure.app.MyApplication;
import com.example.cooventure.entity.Action;
import com.example.cooventure.entity.User;
import com.example.cooventure.util.ActionAdapter;
import com.example.cooventure.util.ActionManager;
import com.example.cooventure.util.UserManager;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	static Activity A11;
	private TextView M_name1;
	private TextView M_realname1;
	private TextView M_right1;
	private TextView M_me1;
	private TextView M_sum1;
	private TextView M_new1;
	
	private ListView M_action;
	
	private String pw;
	private String name;
	private int num = 0;
	
	private MyApplication app;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		init();
		
		A11 = this;
		
        
        UserManager usermanager = new UserManager(MainActivity.this);
        User user = new User();
        user = usermanager.Check(name);
        usermanager.close();
        
        ActionManager am = new ActionManager(MainActivity.this);
        ArrayList<Action> action = new ArrayList<Action>();
        action = (ArrayList<Action>) am.Actionquery(name);
        
        final ArrayList<Action> action1 = action;
        
        num = action.size();
        am.close();
        
        ActionAdapter adapter = new ActionAdapter(getApplicationContext(), R.layout.liststyle, action);
        M_action.setAdapter(adapter);
        
        AdapterView.OnItemClickListener listViewListener = new	AdapterView.OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) { 
				app.set_id(action1.get(arg2).get_id());
//				Toast.makeText(getApplicationContext(),action1.get(arg2).getState(), Toast.LENGTH_LONG).show();
				Intent i = new Intent();
				i.setClass(MainActivity.this, OtherActivity.class);				
				startActivity(i);
			}};
		M_action.setOnItemClickListener(listViewListener);

        M_name1.setText(name);
        M_realname1.setText(user.getRealname());
        
		
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		UserManager usermanager = new UserManager(MainActivity.this);
        User user = new User();
        user = usermanager.Check(name);
        usermanager.close();
        
        ActionManager am = new ActionManager(MainActivity.this);
        ArrayList<Action> action = new ArrayList<Action>();
        action = (ArrayList<Action>) am.Actionquery(name);
        
        final ArrayList<Action> action1 = action;
        
        num = action.size();
        am.close();
        
        ActionAdapter adapter = new ActionAdapter(getApplicationContext(), R.layout.liststyle, action);
        M_action.setAdapter(adapter);
        
        AdapterView.OnItemClickListener listViewListener = new	AdapterView.OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) { 
				app.set_id(action1.get(arg2).get_id());
//				Toast.makeText(getApplicationContext(),action1.get(arg2).getState(), Toast.LENGTH_LONG).show();
				Intent i = new Intent();
				i.setClass(MainActivity.this, OtherActivity.class);				
				startActivity(i);
			}};
		M_action.setOnItemClickListener(listViewListener);

        
        
        
        
        
        M_name1.setText(name);
        M_realname1.setText(user.getRealname());
		
		
	}
	void Same(){
		Intent i = new Intent();
		i.setClass(MainActivity.this, MineActivity.class);
		startActivity(i);
	}
	void Another(){
		Intent i = new Intent();
		i.setClass(MainActivity.this, NewActivity.class);
		startActivity(i);
	}
	
	void init(){
		M_name1 = (TextView) findViewById(R.id.M_name);
		M_realname1 = (TextView) findViewById(R.id.M_realname);
		M_right1 = (TextView) findViewById(R.id.M_right);
		M_me1 = (TextView) findViewById(R.id.M_me);
		M_sum1 = (TextView) findViewById(R.id.M_sum);
		M_new1 = (TextView) findViewById(R.id.M_new);
		M_action = (ListView) findViewById(R.id.listView1);
		
		M_name1.setOnClickListener(MainActivity.this);
		M_realname1.setOnClickListener(MainActivity.this);
		M_right1.setOnClickListener(MainActivity.this);
		M_me1.setOnClickListener(MainActivity.this);
		M_sum1.setOnClickListener(MainActivity.this);
		M_new1.setOnClickListener(MainActivity.this);
		
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
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.M_name:
			Same();
			break;
		case R.id.M_realname:
			Same();
			break;
		case R.id.M_right:
			Same();
			break;
		case R.id.M_me:
			Same();
			break;
		case R.id.M_sum:
			Toast.makeText(getApplicationContext(), "您目前有"+num+"条活动信息！", Toast.LENGTH_SHORT).show();
			break;
		case R.id.M_new:
			Another();
			break;
		}
	}

}
