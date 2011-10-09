package croll.android.agricola;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class PlayerActivity extends Activity {

	private int fieldPoints_ = -1;
	private int totalPoints_ = 0;
	TextView fieldPointText_, totalPointsText_;
	Map<Integer, Button> fieldButtonMap = new HashMap<Integer, Button>();
	Drawable unselectedField_, selectedField_;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player_layout);
		
		fieldPointText_ = (TextView) findViewById(R.id.fieldPoints);
		totalPointsText_ = (TextView) findViewById(R.id.total_points);
		fieldButtonMap.put(R.id.field_points_01, (Button) findViewById(R.id.field_points_01));
		fieldButtonMap.put(R.id.field_points_2,  (Button) findViewById(R.id.field_points_2));
		fieldButtonMap.put(R.id.field_points_3,  (Button) findViewById(R.id.field_points_3));
		fieldButtonMap.put(R.id.field_points_4,  (Button) findViewById(R.id.field_points_4));
		fieldButtonMap.put(R.id.field_points_5,  (Button) findViewById(R.id.field_points_5));
		
		unselectedField_ = this.getResources().getDrawable(R.drawable.field);
		selectedField_ = this.getResources().getDrawable(R.drawable.field_selected);
		
		updateFieldPointText(fieldPoints_);
		
		updateTotalPoints();
	}

	public void onClickHandler(View view) {
		Button button;
		int buttonId = view.getId();
 		switch (buttonId) {
 		case R.id.field_points_01:
 			fieldPoints_ = -1;
 			updateFieldPointText(fieldPoints_);
 			updateSelectedField(buttonId);
 			break;
 		case R.id.field_points_2:
 			fieldPoints_ = 1;
 			updateFieldPointText(fieldPoints_);
 			updateSelectedField(buttonId);
 			break;
 		case R.id.field_points_3:
 			fieldPoints_ = 2;
 			updateFieldPointText(fieldPoints_);
 			updateSelectedField(buttonId);
 			break;
 		case R.id.field_points_4:
 			fieldPoints_ = 3;
 			updateFieldPointText(fieldPoints_);
 			updateSelectedField(buttonId);
 			break;
 		case R.id.field_points_5:
 			fieldPoints_ = 4;
 			updateFieldPointText(fieldPoints_);
 			updateSelectedField(buttonId);
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
	
	private void updateSelectedField(int fieldId)
	{
		//First reset all butons to unselected
		for (Map.Entry<Integer, Button> e : fieldButtonMap.entrySet())
		    e.getValue().setBackgroundDrawable(unselectedField_);

		// Then set the passed in button as the selected field
		fieldButtonMap.get(fieldId).setBackgroundDrawable(selectedField_);
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
