package com.example.culture;

import com.example.culture.ImageAdapter;
import com.example.culture.MainActivity;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {

	private Board board;
	private Game game;
	private GridView gridview;
	private Button button;

	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    this.board = new Board();
	    this.game = new Game(board);
	    
	    setContentView(R.layout.activity_main);
	    this.gridview = (GridView) findViewById(R.id.gridView1);
	    button = (Button) findViewById(R.id.button1);
	    //    GridView gridview = new GridView(getApplicationContext());
	    gridview.setNumColumns(this.board.getWidth());
	    gridview.setAdapter(new ImageAdapter(this, board));
	    
	    
	    gridview.setOnItemClickListener(new OnItemClickListener() {
	    	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	    		game.setSelection(position);
	    		v.setBackgroundColor(Color.YELLOW);
	    	}
	    });
	    
	    button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!game.gameOver()){
					game.switchPlayer();
					game.playTurn();
					gridview.invalidateViews();
				} 
				if(game.gameOver()){
					button.setText("Game Over");

				}
			}
		});
	}	
	    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
