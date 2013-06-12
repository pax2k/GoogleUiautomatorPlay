package no.koteng.awesomeapp;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class FlagActivity extends Activity {

	private GridView gridView;
	private String[] flagList;
	private UserData userData;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flag);

		userData = (UserData) getIntent().getSerializableExtra(
				Util.USER_DATA_INTENT);
		readFlags();

		gridView = (GridView) findViewById(R.id.gridView1);
		gridView.setAdapter(new FlagAdapter(this, flagList));
	}

	private void readFlags() {
		AssetManager asserAssetManager = getAssets();

		try {
			flagList = asserAssetManager.list("flags");
		} catch (IOException e) {
			throw new RuntimeException("Can not read flag content: " + e);
		}
	}

	private class FlagAdapter extends ArrayAdapter<String> {
		private Context context;
		private String[] values;

		public FlagAdapter(Context context, String[] values) {
			super(context, R.layout.flag_item, values);

			this.context = context;
			this.values = values;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {

			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.flag_item, parent,
						false);
			}

			final String flagPath = values[position];
			String flagDescription = flagPath.substring(0,
					flagPath.indexOf("."));
			Bitmap image = Util.getBitmapFromAsset(context, flagPath);

			ImageView imageView = (ImageView) convertView
					.findViewById(R.id.image);
			imageView.setBackground(new BitmapDrawable(getResources(), image));
			imageView.setContentDescription(flagDescription);
			imageView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					startSummaryActivity(flagPath);
				}
			});

			return convertView;
		}

		private void startSummaryActivity(String imagePathUrl) {
			userData.setSelectedImageUrl(imagePathUrl);
			Intent nextActivity = new Intent(FlagActivity.this,
					SummaryActivity.class);
			nextActivity.putExtra(Util.USER_DATA_INTENT, userData);
			startActivity(nextActivity);
		}
	}
}