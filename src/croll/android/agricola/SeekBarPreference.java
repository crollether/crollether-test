/* The following code was written by Matthew Wiggins 
 * and is released under the APACHE 2.0 license 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package croll.android.agricola;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.preference.DialogPreference;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.LinearLayout;


public class SeekBarPreference extends DialogPreference implements SeekBar.OnSeekBarChangeListener
{
  private static final String androidns="http://schemas.android.com/apk/res/android";

  private SeekBar mSeekBar;
  private TextView mSplashText,mValueText;
  private Context mContext;

  private String mDialogMessage, mSuffix;
  private int mDefault, mMax, mValue, mMin = 0;

  public SeekBarPreference(Context context, AttributeSet attrs) { 
    super(context,attrs); 
    mContext = context;

    mDialogMessage = attrs.getAttributeValue(androidns,"dialogMessage");
    mSuffix = attrs.getAttributeValue(androidns,"text");
    mDefault = attrs.getAttributeIntValue(androidns,"defaultValue", 1);
    mMin = attrs.getAttributeIntValue(androidns,"min", 1);
    mMax = attrs.getAttributeIntValue(androidns,"max", 5);
    mMax -= mMin;

  }
  @Override 
  protected View onCreateDialogView() {
    LinearLayout.LayoutParams params;
    LinearLayout layout = new LinearLayout(mContext);
    layout.setOrientation(LinearLayout.VERTICAL);
    layout.setPadding(6,6,6,6);

    mSplashText = new TextView(mContext);
    if (mDialogMessage != null)
      mSplashText.setText(mDialogMessage);
    layout.addView(mSplashText);

    mValueText = new TextView(mContext);
    mValueText.setGravity(Gravity.CENTER_HORIZONTAL);
    mValueText.setTextSize(32);
    params = new LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.FILL_PARENT, 
        LinearLayout.LayoutParams.WRAP_CONTENT);
    layout.addView(mValueText, params);

    mSeekBar = new SeekBar(mContext);
    mSeekBar.setOnSeekBarChangeListener(this);
    layout.addView(mSeekBar, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

    if (shouldPersist())
      mValue = getPersistedInt(mDefault)-mMin;

    mSeekBar.setMax(mMax);
    setValueText(mValue);
    mSeekBar.setProgress(mValue);
    return layout;
  }
  
  @Override
  protected void onDialogClosed(boolean positiveResult)
  {
	  if (positiveResult)
	  {
		  if (shouldPersist())
			  persistInt(mValue+mMin);
		  callChangeListener(new Integer(mValue));
	  }
  }
  
  @Override 
  protected void onBindDialogView(View v) {
    super.onBindDialogView(v);
    mSeekBar.setMax(mMax);
    mSeekBar.setProgress(mValue);
  }
  @Override
  protected void onSetInitialValue(boolean restore, Object defaultValue)  
  {
    super.onSetInitialValue(restore, defaultValue);
    if (restore) 
      mValue = shouldPersist() ? getPersistedInt(mDefault)-mMin : 0;
    else 
      mValue = (Integer)defaultValue;
  }

  public void onProgressChanged(SeekBar seek, int value, boolean fromUser)
  {
	  setValueText(value);
	    
	  mValue = value;
  }
  public void onStartTrackingTouch(SeekBar seek) {}
  public void onStopTrackingTouch(SeekBar seek) {}

  public void setMax(int max) { mMax = max; }
  public int getMax() { return mMax; }

  public void setProgress(int progress) { 
    mValue = progress;
    if (mSeekBar != null)
      mSeekBar.setProgress(progress); 
  }
  public int getProgress() { return mValue; }
  
  private void setValueText(int value)
  {
	  int displayValue = value + mMin;
	  String t = String.valueOf(displayValue);
	  if (mSuffix != null)
	  {
	  	t = t.concat(mSuffix);
	  	if (displayValue > 1)
	  	{
	  		t = t.concat("s");
	  	}
	  }
	  mValueText.setText(t);
  }
}

