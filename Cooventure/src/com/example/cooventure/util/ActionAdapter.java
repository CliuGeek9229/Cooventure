package com.example.cooventure.util;

import java.util.List;

import com.example.cooventure.R;
import com.example.cooventure.entity.Action;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ActionAdapter extends ArrayAdapter<Action> {

	private int res;
	
	public ActionAdapter(Context context, int textViewResourceId,
			List<Action> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		res = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Action action = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(res, null);
		TextView ActionTitle = (TextView) view.findViewById(R.id.actiontitle);
		TextView ActionTime = (TextView) view.findViewById(R.id.actiontime);
		
		ActionTitle.setText(action.getTitle());
		ActionTime.setText(action.getData()+"  "+action.getTime());
		
		
		return view;
	}

	
}
