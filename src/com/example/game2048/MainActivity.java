package com.example.game2048;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private static MainActivity mainActivity;

	Button btnBack;
	Button btnAgain;
	GameView gameView;
	TextView tvScore;

	public MainActivity() {
		mainActivity = this;
	}
	//单例模式获取本对象
	public static MainActivity getInstance() {
		return mainActivity;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		gameView = (GameView) findViewById(R.id.gameView);
		tvScore = (TextView) findViewById(R.id.tvScore);
		tvScore.setText(0 + "");
		
		btnBack = (Button) findViewById(R.id.btn_back);
		btnBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				gameView.backLastView();
			}
		});
		
		btnAgain = (Button) findViewById(R.id.btn_again);
		btnAgain.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				gameView.againGame();
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
