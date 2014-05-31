package pl.tajchert.glassware.narrator;

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;

public class SayingActivity extends Activity implements TextToSpeech.OnInitListener{
	private AudioManager audio;
	private GestureDetector mGestureDetector;
	private TextToSpeech ttobj;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.select_number);
		
		mGestureDetector = createGestureDetector(this);
		
		
		ttobj = new TextToSpeech(this, this);
		ttobj.setLanguage(Locale.US);
		
		audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
	}
	private void speakOut(String in) {
		if(ttobj == null){
			return;
		}
		ttobj.stop();
		ttobj.speak(in, TextToSpeech.QUEUE_FLUSH, null);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	@Override
	protected void onPause() {
		super.onPause();
	}
	@Override
	protected void onDestroy() {
	    if(ttobj != null) {
	    	ttobj.stop();
	    	ttobj.shutdown();
	    }
	    super.onDestroy();
	}

	@Override
	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {
			int result = ttobj.setLanguage(Locale.US);
			if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.d(Tools.AWESOME_TAG, "This Language is not supported");
			} else {
				Log.d(Tools.AWESOME_TAG, "Speech is enabled");
			}
		} else {
			Log.d(Tools.AWESOME_TAG, "Initilization Failed!");
		}
	}
	
	private GestureDetector createGestureDetector(Context context) {
		GestureDetector gestureDetector = new GestureDetector(context);
		gestureDetector.setBaseListener(new GestureDetector.BaseListener() {
			@Override
			public boolean onGesture(Gesture gesture) {
				if (gesture == Gesture.TAP) {
					Log.i(Tools.AWESOME_TAG, "KEYCODE_DPAD_CENTER");
					speakOut("Test, I am a robber");
					return true;
				} else if (gesture == Gesture.TWO_TAP) {
					Log.i(Tools.AWESOME_TAG, "TWO_TAP");
					return true;
				} else if (gesture == Gesture.SWIPE_RIGHT) {
					Log.i(Tools.AWESOME_TAG, "SWIPE_RIGHT");
					return true;
				} else if (gesture == Gesture.SWIPE_LEFT) {
					Log.i(Tools.AWESOME_TAG, "SWIPE_LEFT");
					return true;
				}
				return false;
			}
		});
		return gestureDetector;
	}

	@Override
	public boolean onGenericMotionEvent(MotionEvent event) {
		if (mGestureDetector != null) {
			return mGestureDetector.onMotionEvent(event);
		}
		return false;
	}

}
