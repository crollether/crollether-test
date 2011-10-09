package croll.android.agricola;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;


public class PlayerActivity extends Activity {

	private int fieldPoints_ = -1;
	private int totalPoints_ = 0;
	TextView fieldPointText_, totalPointsText_;
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player_layout);
		
		fieldPointText_ = (TextView) findViewById(R.id.fieldPoints);
		totalPointsText_ = (TextView) findViewById(R.id.total_points);
		
		updateFieldPointText(fieldPoints_);
		
		updateTotalPoints();
	}

	public void onClickHandler(View view) {
 		switch (view.getId()) {
 		case R.id.field_points_01:
 			fieldPoints_ = -1;
 			updateFieldPointText(fieldPoints_);
 			break;
 		case R.id.field_points_2:
 			fieldPoints_ = 1;
 			updateFieldPointText(fieldPoints_);
 			break;
 		case R.id.field_points_3:
 			fieldPoints_ = 2;
 			updateFieldPointText(fieldPoints_);
 			break;
 		case R.id.field_points_4:
 			fieldPoints_ = 3;
 			updateFieldPointText(fieldPoints_);
 			break;
 		case R.id.field_points_5:
 			fieldPoints_ = 4;
 			updateFieldPointText(fieldPoints_);
 			break;
 		}
 		updateTotalPoints();
	}
	
	private void updateFieldPointText(int fieldPoints)
	{
		String fieldPointsString = "Field points: ";
		fieldPointsString = fieldPointsString.concat(String.valueOf(fieldPoints));
		fieldPointText_.setText(fieldPointsString);
	}
	
	private void updateTotalPoints()
	{
		totalPoints_ = fieldPoints_;
		updateTotalPointsText();
	}
	
	private void updateTotalPointsText()
	{
		
		String totalPointsString = "Total points: ";
		totalPointsString = totalPointsString.concat(String.valueOf(totalPoints_));
		totalPointsText_.setText(totalPointsString);
	}
}
