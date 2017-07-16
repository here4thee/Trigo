package dengdeng.xm.trigo;

import java.util.ArrayList;

import dengdeng.xm.trigo.database.Query;
import dengdeng.xm.trigo.domain.AccountInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends Activity {
	
	EditText Semail;
	EditText Spassword;
	Button Ssignup;
	Query Mquery;
	ArrayList<AccountInfo> users;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		
		Semail = (EditText)findViewById(R.id.signup_email);
        Spassword = (EditText)findViewById(R.id.signup_password);
        Ssignup = (Button)findViewById(R.id.signup_signup_button);
        
        Mquery = new Query(this);
        
        Ssignup.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View view) {
				users = Mquery.queryAll();
				String email = Semail.getText().toString();
                String password = Spassword.getText().toString();
                if(email.equals("") || password.equals("")) {
                    Toast.makeText(SignupActivity.this, "Email or Password can't be empty!", Toast.LENGTH_SHORT).show();
                }
                else{
	                int checkEmail = 0;
	                int i;
	                for(i = 0; i < users.size(); i++){
	                    // check email
	                    if(users.get(i).getEmail().equals(email)){
	                    	checkEmail = 1;
	                    	break;
	                    }
	                }
	                if(checkEmail == 1){
	                	Toast.makeText(SignupActivity.this, "User already exists!", Toast.LENGTH_SHORT).show();
	                }
	                else{
	                	AccountInfo user = new AccountInfo(email, password);
	                	Mquery.addAccountInfo(user);
	                    Toast.makeText(SignupActivity.this, "Have fun with Trigo!", Toast.LENGTH_SHORT).show();
	                    Intent intent = new Intent();
	                    intent.setClass(SignupActivity.this,MainActivity.class);
	                    startActivity(intent);
	                    SignupActivity.this.finish();
	                }
                }
			}
        	
        });
	}

}
