package com.example.culture;

import android.R.color;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.PorterDuff.Mode;
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
            imageView.setLayoutParams(new GridView.LayoutParams(60, 75));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        Tile tile = mBoard.getTile(position);
        Unit unit = tile.getUnit();
        City city = tile.getCity();
        imageView.setBackgroundColor(tileColors[tile.getType().ordinal()]);
        Bitmap b1 = null;
        Bitmap b2 = null;
        int c = 0;
        if(city != null){
            b1=BitmapFactory.decodeResource(this.mContext.getResources(),R.drawable.village);
//            b1 = replace(b1, Color.LTGRAY, Color.TRANSPARENT);
            c = playerColors[city.getId()];
        } 
		if(unit != null){
            b2=BitmapFactory.decodeResource(this.mContext.getResources(),R.drawable.sphere);
            b2 = replace(b2, Color.LTGRAY, Color.TRANSPARENT);
            c = playerColors[unit.getId()];
        }
		Bitmap b = overlay(b1, b2);
        imageView.setImageBitmap(b);
    	imageView.setColorFilter(new LightingColorFilter(Color.WHITE, c));            
		return imageView;
    }

    int [] playerColors = {Color.RED, Color.BLACK, Color.MAGENTA, Color.CYAN};
    int [] tileColors = {Color.GREEN, Color.BLUE};
    
    private Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
    	if(bmp1 == null){
    		return bmp2;
    	} else if( bmp2 == null){
    		return bmp1;
    	}
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, new Matrix(), null);
        return bmOverlay;
    }
    
    private Bitmap replace(Bitmap bmp, int color1, int color2){
    	int picw = bmp.getWidth();
    	int pich = bmp.getHeight();
        int[] pix = new int[picw*pich];
		bmp.getPixels(pix , 0, picw, 0, 0, picw, pich);
		for (int i = 0; i < pix.length; i++) {
			if(pix[i] > color1){
				pix[i] = color2;
			}
		}
        Bitmap bm = Bitmap.createBitmap(pix, picw, pich,
                Bitmap.Config.ARGB_8888);  

		return bm;
    }
    
}