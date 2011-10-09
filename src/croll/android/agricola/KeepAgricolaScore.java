package croll.android.agricola;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

public class KeepAgricolaScore extends Activity {
	private EditText text;
	SharedPreferences preferences;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        text = (EditText) findViewById(R.id.editText1);
        
        Button button = (Button) findViewById(R.id.button2);
		// Initialize preferences
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		Integer numberofplayers = preferences.getInt("playernum", 1);

		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean farmersofthemoor = preferences.getBoolean("farmersofthemoor", false); 
				Integer numberofplayers = preferences.getInt("playernum", 1);
				Toast.makeText(
						KeepAgricolaScore.this,
						"You entered: Farmers of the moor: " + farmersofthemoor + " and num of players: " + numberofplayers, Toast.LENGTH_LONG).show();

			}
		});

		Button buttonChangePreferences = (Button) findViewById(R.id.button4);
		buttonChangePreferences.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Editor edit = preferences.edit();
				String username = preferences.getString("username", "n/a");
				// We will just revert the current user name and save again
				StringBuffer buffer = new StringBuffer();
				for (int i = username.length() - 1; i >= 0; i--) {
					buffer.append(username.charAt(i));
				}
				edit.putString("username", buffer.toString());
				edit.commit();
				// A toast is a view containing a quick little message for the
				// user. We give a little feedback
				Toast.makeText(KeepAgricolaScore.this,
						"Reverted string sequence of user name.",
						Toast.LENGTH_LONG).show();
			}
		});
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout1);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.setGravity(Gravity.CENTER_HORIZONTAL);
		for (int i = 1; i <= numberofplayers; i++)
		{
			Button b = new Button(this);
		    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		        LinearLayout.LayoutParams.WRAP_CONTENT, 
		        LinearLayout.LayoutParams.WRAP_CONTENT);
			b.setLayoutParams(params);
			b.setText("Player " + i);
			b.setOnClickListener(new OnClickListener() 
			{
				public void onClick(View v) 
				{
					Intent i = new Intent(KeepAgricolaScore.this, PlayerActivity.class);
		 			startActivity(i);
				}
			});
			ll.addView(b);
		}
		layout.addView(ll);
    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}
 // This method is called once the menu is selected
 	@Override
 	public boolean onOptionsItemSelected(MenuItem item) {
 		switch (item.getItemId()) {
 		// We have only one menu option
 		case R.id.settings:
 			// Launch Preference activity
 			Intent i = new Intent(KeepAgricolaScore.this, Preferences.class);
 			startActivity(i);
 			// Some feedback to the user
 			Toast.makeText(KeepAgricolaScore.this,
 					"Here you can enter your user credentials.",
 					Toast.LENGTH_LONG).show();
 			break;

 		}
 		return true;
 	}
    
    // This method is called at button click because we assigned the name to the
 	// "On Click property" of the button
 	public void myClickHandler(View view) {
 		switch (view.getId()) {
 		case R.id.button3:
 			RadioButton celsiusButton = (RadioButton) findViewById(R.id.radioButton1);
 			RadioButton fahrenheitButton = (RadioButton) findViewById(R.id.radioButton2);
 			if (text.getText().length() == 0) {
 				Toast.makeText(this, "Please enter a valid number. Foo",
 						Toast.LENGTH_LONG).show();
 				return;
 			}

 			float inputValue = Float.parseFloat(text.getText().toString());
 			if (celsiusButton.isChecked()) {
 				text.setText(String
 						.valueOf(convertFahrenheitToCelsius(inputValue)));
 				celsiusButton.setChecked(false);
 				fahrenheitButton.setChecked(true);
 			} else {
 				text.setText(String
 						.valueOf(convertCelsiusToFahrenheit(inputValue)));
 				fahrenheitButton.setChecked(false);
 				celsiusButton.setChecked(true);
 			}
 			break;
 		}
 	}

 	// Converts to celsius
 	private float convertFahrenheitToCelsius(float fahrenheit) {
 		return ((fahrenheit - 32) * 5 / 9);
 	}

 	// Converts to fahrenheit
 	private float convertCelsiusToFahrenheit(float celsius) {
 		return ((celsius * 9) / 5) + 32;
 	}
}