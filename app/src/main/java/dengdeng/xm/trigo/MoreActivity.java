package dengdeng.xm.trigo;

import java.util.ArrayList;

import dengdeng.xm.trigo.database.Query;
import dengdeng.xm.trigo.domain.AccountInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class MoreActivity extends Activity {

	TextView Mmore_name;
	EditText Mmore_target_data;
	EditText Mmore_whatsup_data;
	TextView Mmore_times_data;
	TextView Mmore_round_data;
	TextView Mmore_record_data;
	Button Mmore_save;
	Query Mquery;
	ArrayList<AccountInfo> users;
	AccountInfo user;
	
	String email;
	int user_index;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more);

		// match id
		Mmore_name = (TextView)findViewById(R.id.more_name);
		Mmore_target_data = (EditText)findViewById(R.id.more_target_data);
		Mmore_whatsup_data = (EditText)findViewById(R.id.more_whatsup_data);
		Mmore_times_data = (TextView)findViewById(R.id.more_times_data);
		Mmore_round_data = (TextView)findViewById(R.id.more_round_data);
		Mmore_record_data = (TextView)findViewById(R.id.more_record_data);
		Mmore_save = (Button)findViewById(R.id.more_save);
		
		Intent intent = getIntent();
		email = intent.getStringExtra("email");
		
		Mmore_name.setText(email);
		
		Mquery = new Query(this);
		users = Mquery.queryAll();
		int i;
        for(i = 0; i < users.size(); i++){
            // match email
            if(users.get(i).getEmail().equals(email)){
            	user_index = i;
            	break;
            }
        }
        user = users.get(user_index);

		Mmore_target_data.setHint(Integer.toString(user.getTarget()));
		Mmore_whatsup_data.setHint(user.getWhatsup());
		Mmore_times_data.setText(Integer.toString(user.getTimes()));
		Mmore_round_data.setText(Integer.toString(user.getRound()));
		Mmore_record_data.setText(Integer.toString(user.getRecord()));
		
		Mmore_save.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View view) {
				String target;
				String whatsup;
				if(Mmore_target_data.getText().toString().equals("")){
					target = Mmore_target_data.getHint().toString();
				}
				else{
					target = Mmore_target_data.getText().toString();
				}

				if(Mmore_whatsup_data.getText().toString().equals("")){
					whatsup = Mmore_whatsup_data.getHint().toString();
				}
				else{
					whatsup = Mmore_whatsup_data.getText().toString();
				}
                user.setTarget(Integer.parseInt(target));
                user.setWhatsup(whatsup);
                Mquery.updateTarget(user);
                Mquery.updateWhatsup(user);
                Intent intent = new Intent();
                intent.setClass(MoreActivity.this,TabSelActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
                MoreActivity.this.finish();
			}
        	
        });
		
	}

}
