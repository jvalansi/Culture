package com.example.culture;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
	private Board mBoard;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public ImageAdapter(Context c, Board board) {
    	mContext = c;
    	mBoard = board;
	}

	public int getCount() {

		return this.mBoard.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setBackgroundColor(Color.BLACK);
        imageView.setImageResource(mThumbIds[mBoard.getTile(position)]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.bola, R.drawable.cruz,
            R.drawable.vazio
    };

}