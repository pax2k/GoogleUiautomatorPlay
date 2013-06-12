package no.koteng.awesomeapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText username;
	private EditText realName;
	private EditText email;
	private Button ok;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		username = (EditText) findViewById(R.id.username);
		realName = (EditText) findViewById(R.id.realName);
		email = (EditText) findViewById(R.id.email);
		ok = (Button) findViewById(R.id.ok);

		ok.setOnClickListener(okButtonClicked());
	}

	private OnClickListener okButtonClicked() {
		return new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				validateInputAndStartNextActivityIfValid();
			}
		};
	}

	private void validateInputAndStartNextActivityIfValid() {
		boolean valid = isValidEditText(username) && isValidEditText(realName) && isValidEditText(email);
		
		if(valid){
			String usernameText = username.getText().toString();
			String realNameText = realName.getText().toString();
			String emailText = email.getText().toString();
			
			Intent intent = new Intent(this, FlagActivity.class);
			intent.putExtra(Util.USER_DATA_INTENT, new UserData(usernameText, realNameText, emailText));
			startActivity(intent);
		}else{
			Toast.makeText(this, "Please check input  values..", Toast.LENGTH_SHORT).show();
		}
	}

	private boolean isValidEditText(EditText textToValidate) {
		return textToValidate.getText() != null
				&& !"".equals(textToValidate.getText())
				&& textToValidate.getText().length() >= 3;
	}

}
