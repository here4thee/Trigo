package dengdeng.xm.trigo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import me.james.biuedittext.BiuEditText;
import android.content.Context;

public class MusicActivity extends Activity {

	BiuEditText Mmusic_name;
	Button Mmusic_search;
	TextView Mmusic_surprise;
	InputMethodManager imm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music);
		Mmusic_name = (BiuEditText) findViewById(R.id.music_name);
		Mmusic_search = (Button) findViewById(R.id.music_search);
		Mmusic_surprise = (TextView) findViewById(R.id.music_surprise);
		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

		Mmusic_search.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick (View view) {
				String musicName = Mmusic_name.getText().toString();
				imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
				if (musicName.equals("dengdeng")) {
					Mmusic_surprise.setText(R.string.surprise_e);
				}
				else if (musicName.equals("小思颖")) {
					Mmusic_surprise.setText(R.string.surprise_c);
				}
				else {
					Mmusic_surprise.setText(R.string.find_error);
				}
			}

		});

	}

}
