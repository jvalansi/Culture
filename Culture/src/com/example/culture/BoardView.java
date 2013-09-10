package com.example.culture;

import android.content.Context;
import android.widget.GridView;

public class BoardView extends GridView {

	public BoardView(Context context) {
		super(context);
	}

	
	@Override
	public void setLayoutParams(android.view.ViewGroup.LayoutParams params) {
		// TODO Auto-generated method stub
		android.view.ViewGroup.LayoutParams p = new LayoutParams(3,3);
		super.setLayoutParams(p);
	}
}
