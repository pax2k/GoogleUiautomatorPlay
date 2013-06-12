package no.koteng.awesomeapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SummaryActivity extends Activity {

	private UserData userData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary);

		userData = (UserData) getIntent().getSerializableExtra(
				Util.USER_DATA_INTENT);

		TextView username = (TextView) findViewById(R.id.username_text);
		TextView realName = (TextView) findViewById(R.id.realName_text);
		TextView email = (TextView) findViewById(R.id.email_text);
		ImageView flagSelected = (ImageView) findViewById(R.id.flag_selected);

		username.setText(userData.getUsername());
		realName.setText(userData.getRealName());
		email.setText(userData.getEmail());

		Bitmap flagBitmap = Util.getBitmapFromAsset(this,
				userData.getSelectedImageUrl());
		flagSelected.setBackground(new BitmapDrawable(getResources(),
				flagBitmap));
	}
}
