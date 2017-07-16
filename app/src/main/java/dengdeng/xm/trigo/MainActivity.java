package dengdeng.xm.trigo;

import java.util.ArrayList;

import dengdeng.xm.trigo.domain.AccountInfo;
import dengdeng.xm.trigo.database.Query;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ImageView Mexit;
	ImageView Msignup;
	EditText Memail;
	EditText Mpassword;
	Button Mlogin;
	TextView Mforget_pwd;
	ImageView Mqq;
	ImageView Mwechat;
	ImageView Mweibo;
	
	Query Mquery;
	ArrayList<AccountInfo> users;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // match id
        Mexit = (ImageView)findViewById(R.id.main_exit);
        Msignup = (ImageView)findViewById(R.id.main_signup);
        Memail = (EditText)findViewById(R.id.main_email);
        Mpassword = (EditText)findViewById(R.id.main_password);
        Mlogin = (Button)findViewById(R.id.main_login_button);
        Mforget_pwd = (TextView)findViewById(R.id.main_forget_pwd);
        Mqq = (ImageView)findViewById(R.id.main_qq);
        Mwechat = (ImageView)findViewById(R.id.main_wechat);
        Mweibo = (ImageView)findViewById(R.id.main_weibo);
        
        Mquery = new Query(this);
        
        Mexit.setOnClickListener(new ImageView.OnClickListener(){

			@Override
			public void onClick(View view) {
				
				AlertDialog alert = new AlertDialog.Builder(MainActivity.this).create();
				alert.setTitle("Exit");
				alert.setMessage("Are you sure to exit?");
				alert.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
					
				});
				alert.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(MainActivity.this, "哼！竟然真的抛弃了人家！", Toast.LENGTH_SHORT).show();
						finish();
					}
					
				});
				alert.show();
			}
        	
        });
        
        Msignup.setOnClickListener(new ImageView.OnClickListener(){

			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
                intent.setClass(MainActivity.this,SignupActivity.class);
                MainActivity.this.startActivityForResult(intent,1);
			}
        	
        });
        
        Mlogin.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View view) {
		        users = Mquery.queryAll();
				String email = Memail.getText().toString();
                String password = Mpassword.getText().toString();
                int checkEmail = 0;
                int checkPWD = 0;
                int i;
                for(i = 0; i < users.size(); i++){
                    // check email
                    if(users.get(i).getEmail().equals(email)){
                    	checkEmail = 1;
                        // check password
                        if(users.get(i).getPassword().equals(password)){
                        	checkPWD = 1;
                            break;
                        }
                    }
                }
                if(checkEmail == 0){
                    Toast.makeText(MainActivity.this, "User not exists!", Toast.LENGTH_SHORT).show();
                }
                else {
                	if(checkPWD == 0){
                		Toast.makeText(MainActivity.this, "Wrong password!", Toast.LENGTH_SHORT).show();
                	}
                	else{
                		Toast.makeText(MainActivity.this, "Have fun with Trigo!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this,TabSelActivity.class);
                        intent.putExtra("email", email);
                        startActivity(intent);
                        MainActivity.this.finish();
                	}
                }
			}
        	
        });
        
        Mforget_pwd.setOnClickListener(new TextView.OnClickListener(){

			@Override
			public void onClick(View view) {
				Toast.makeText(MainActivity.this, "Still developing...", Toast.LENGTH_SHORT).show();
			}
        	
        });
        
        Mqq.setOnClickListener(new ImageView.OnClickListener(){

			@Override
			public void onClick(View view) {
				Toast.makeText(MainActivity.this, "Still developing...", Toast.LENGTH_SHORT).show();
			}
        	
        });
        
        Mwechat.setOnClickListener(new ImageView.OnClickListener(){

			@Override
			public void onClick(View view) {
				Toast.makeText(MainActivity.this, "Still developing...", Toast.LENGTH_SHORT).show();
			}
        	
        });
        
        Mweibo.setOnClickListener(new ImageView.OnClickListener(){

			@Override
			public void onClick(View view) {
				Toast.makeText(MainActivity.this, "Still developing...", Toast.LENGTH_SHORT).show();
			}
        	
        });
        
    }

}
