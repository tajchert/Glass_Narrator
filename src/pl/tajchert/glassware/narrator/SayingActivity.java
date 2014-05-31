package pl.tajchert.glassware.narrator;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.google.android.glass.media.Sounds;

public class SayingActivity extends Activity{
	private AudioManager audio;

	@Override
	public boolean onKeyDown(int keycode, KeyEvent event) {
		if (keycode == KeyEvent.KEYCODE_DPAD_CENTER) {
			
			Log.i(Tools.AWESOME_TAG, "KEYCODE_DPAD_CENTER");
			audio.playSoundEffect(Sounds.SUCCESS);
			return true;
		}
		return false;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.select_number);
		
		
		audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	@Override
	protected void onPause() {
		super.onPause();
	}

	private class SayText extends AsyncTask<String, Void, String> {
		private String ang;

		@Override
		protected String doInBackground(String... params) {
			ang = params[0];
			return "Executed";
		}

		@Override
		protected void onPostExecute(String result) {
		}

		@Override
		protected void onPreExecute() {
		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}
	}
}
