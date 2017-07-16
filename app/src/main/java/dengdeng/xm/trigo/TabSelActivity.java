package dengdeng.xm.trigo;

import java.util.ArrayList;

import dengdeng.xm.trigo.database.Query;
import dengdeng.xm.trigo.domain.AccountInfo;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class TabSelActivity extends Activity  {

	TabHost tabHost;
	ImageView Ttab_sel_music;
	ImageView Ttab_sel_more;
	String email;
	Query Mquery;
	ArrayList<AccountInfo> users;
	int user_index;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_sel);
		
		tabHost = (TabHost)findViewById(android.R.id.tabhost);
		tabHost.setup();
		Ttab_sel_music = (ImageView)findViewById(R.id.tab_sel_music);
		Ttab_sel_more = (ImageView)findViewById(R.id.tab_sel_more);
		
		Intent intent = getIntent();
		email = intent.getStringExtra("email");
		
		LayoutInflater inflater = LayoutInflater.from(this);
		View view_home = inflater.inflate(R.layout.tab_home, tabHost.getTabContentView());
		View view_start = inflater.inflate(R.layout.tab_start, tabHost.getTabContentView());
		
		Mquery = new Query(this);
		users = Mquery.queryAll();
		int i;
		
        for(i = 0; i < users.size(); i++){
            // check email
            if(users.get(i).getEmail().equals(email)){
            	user_index = i;
                break;
            }
        }
        AccountInfo user = users.get(user_index);

		TextView Thome_whatsup = (TextView) view_home.findViewById(R.id.home_whatsup);
		TextView Thome_rankMe_number = (TextView) view_home.findViewById(R.id.home_rankMe_number);
		ImageView Thome_rankMe_profile = (ImageView) view_home.findViewById(R.id.home_rankMe_profile);
		TextView Thome_rankMe_name = (TextView) view_home.findViewById(R.id.home_rankMe_name);
		TextView Thome_rankMe_times = (TextView) view_home.findViewById(R.id.home_rankMe_times);
		ImageView Thome_rankMe_heart = (ImageView) view_home.findViewById(R.id.home_rankMe_heart);
		ImageView Thome_rank1_profile = (ImageView) view_home.findViewById(R.id.home_rank1_profile);
		TextView Thome_rank1_name = (TextView) view_home.findViewById(R.id.home_rank1_name);
		TextView Thome_rank1_times = (TextView) view_home.findViewById(R.id.home_rank1_times);
		ImageView Thome_rank1_heart = (ImageView) view_home.findViewById(R.id.home_rank1_heart);
		ImageView Thome_rank2_profile = (ImageView) view_home.findViewById(R.id.home_rank2_profile);
		TextView Thome_rank2_name = (TextView) view_home.findViewById(R.id.home_rank2_name);
		TextView Thome_rank2_times = (TextView) view_home.findViewById(R.id.home_rank2_times);
		ImageView Thome_rank2_heart = (ImageView) view_home.findViewById(R.id.home_rank2_heart);
		ImageView Thome_rank3_profile = (ImageView) view_home.findViewById(R.id.home_rank3_profile);
		TextView Thome_rank3_name = (TextView) view_home.findViewById(R.id.home_rank3_name);
		TextView Thome_rank3_times = (TextView) view_home.findViewById(R.id.home_rank3_times);
		ImageView Thome_rank3_heart = (ImageView) view_home.findViewById(R.id.home_rank3_heart);

		Thome_whatsup.setText(user.getWhatsup());
		Thome_rankMe_number.setText(Integer.toString(user_index + 1));
		Thome_rankMe_profile.setImageResource(R.drawable.user2);
		Thome_rankMe_name.setText(user.getEmail());
		Thome_rankMe_times.setText(Integer.toString(user.getRound()));
		Thome_rankMe_heart.setImageResource(R.drawable.heart1);
		
		if(user_index == 0) {
			Thome_rank1_profile.setImageResource(R.drawable.user2);
			Thome_rank1_name.setText(user.getEmail());
			Thome_rank1_times.setText(Integer.toString(user.getRound()));
			Thome_rank1_heart.setImageResource(R.drawable.heart1);
			if(users.size() > 1) {
				Thome_rank2_profile.setImageResource(R.drawable.user5);
				Thome_rank2_name.setText(users.get(1).getEmail());
				Thome_rank2_times.setText(Integer.toString(users.get(1).getRound()));
				Thome_rank2_heart.setImageResource(R.drawable.heart);
				if(users.size() > 2) {
					Thome_rank3_profile.setImageResource(R.drawable.user4);
					Thome_rank3_name.setText(users.get(2).getEmail());
					Thome_rank3_times.setText(Integer.toString(users.get(2).getRound()));
					Thome_rank3_heart.setImageResource(R.drawable.heart);
				}
				else {
					Thome_rank3_profile.setImageResource(R.drawable.me);
					Thome_rank3_name.setText("Trigo");
					Thome_rank3_times.setText("0");
					Thome_rank3_heart.setImageResource(R.drawable.heart);
				}
			}
			else {
				Thome_rank2_profile.setImageResource(R.drawable.me);
				Thome_rank2_name.setText("Trigo");
				Thome_rank2_times.setText("0");
				Thome_rank2_heart.setImageResource(R.drawable.heart);
				Thome_rank3_profile.setImageResource(R.drawable.me);
				Thome_rank3_name.setText("Trigo");
				Thome_rank3_times.setText("0");
				Thome_rank3_heart.setImageResource(R.drawable.heart);
			}
		}
		else if(user_index == 1) {
			Thome_rank1_profile.setImageResource(R.drawable.user5);
			Thome_rank1_name.setText(users.get(0).getEmail());
			Thome_rank1_times.setText(Integer.toString(users.get(0).getRound()));
			Thome_rank1_heart.setImageResource(R.drawable.heart);
			Thome_rank2_profile.setImageResource(R.drawable.user2);
			Thome_rank2_name.setText(user.getEmail());
			Thome_rank2_times.setText(Integer.toString(user.getRound()));
			Thome_rank2_heart.setImageResource(R.drawable.heart1);
			if(users.size() > 2){
				Thome_rank3_profile.setImageResource(R.drawable.user4);
				Thome_rank3_name.setText(users.get(2).getEmail());
				Thome_rank3_times.setText(Integer.toString(users.get(2).getRound()));
				Thome_rank3_heart.setImageResource(R.drawable.heart);
			}
			else {
				Thome_rank3_profile.setImageResource(R.drawable.me);
				Thome_rank3_name.setText("Trigo");
				Thome_rank3_times.setText("0");
				Thome_rank3_heart.setImageResource(R.drawable.heart);
			}
		}
		else if(user_index == 2) {
			Thome_rank1_profile.setImageResource(R.drawable.user5);
			Thome_rank1_name.setText(users.get(0).getEmail());
			Thome_rank1_times.setText(Integer.toString(users.get(0).getRound()));
			Thome_rank1_heart.setImageResource(R.drawable.heart);
			Thome_rank2_profile.setImageResource(R.drawable.user4);
			Thome_rank2_name.setText(users.get(1).getEmail());
			Thome_rank2_times.setText(Integer.toString(users.get(1).getRound()));
			Thome_rank2_heart.setImageResource(R.drawable.heart);
			Thome_rank3_profile.setImageResource(R.drawable.user2);
			Thome_rank3_name.setText(user.getEmail());
			Thome_rank3_times.setText(Integer.toString(user.getRound()));
			Thome_rank3_heart.setImageResource(R.drawable.heart1);
		}
		else {
			Thome_rank1_profile.setImageResource(R.drawable.user5);
			Thome_rank1_name.setText(users.get(0).getEmail());
			Thome_rank1_times.setText(Integer.toString(users.get(0).getRound()));
			Thome_rank1_heart.setImageResource(R.drawable.heart);
			Thome_rank2_profile.setImageResource(R.drawable.user4);
			Thome_rank2_name.setText(users.get(1).getEmail());
			Thome_rank2_times.setText(Integer.toString(users.get(1).getRound()));
			Thome_rank2_heart.setImageResource(R.drawable.heart);
			Thome_rank3_profile.setImageResource(R.drawable.user3);
			Thome_rank3_name.setText(users.get(2).getEmail());
			Thome_rank3_times.setText(Integer.toString(users.get(2).getRound()));
			Thome_rank3_heart.setImageResource(R.drawable.heart);
		}
		
		ImageView Tstart_button = (ImageView) view_start.findViewById(R.id.start_button);
		
		View tab_sel_home = LayoutInflater.from(this).inflate(R.layout.tab_style, null);
		TextView tab_sel_start_text = (TextView) tab_sel_home.findViewById(R.id.tab_text);
		tab_sel_start_text.setText(email);
		
		View tab_sel_start = LayoutInflater.from(this).inflate(R.layout.tab_style, null);
		TextView tab_sel_history_text = (TextView) tab_sel_start.findViewById(R.id.tab_text);
		tab_sel_history_text.setText("START");
		
		tabHost.addTab(tabHost.newTabSpec("tab_sel_home").setIndicator(tab_sel_home).setContent(R.id.tab_home_layout));
		tabHost.addTab(tabHost.newTabSpec("tab_sel_start").setIndicator(tab_sel_start).setContent(R.id.tab_start_layout));
		tabHost.setCurrentTab(0);
		
		Tstart_button.setOnClickListener(new ImageView.OnClickListener(){

			@Override
			public void onClick(View view) {
				
				Intent intent = new Intent();
                intent.setClass(TabSelActivity.this,MonitorActivity.class);
                intent.putExtra("email", email);
				TabSelActivity.this.startActivityForResult(intent,1);
                
			}
        	
        });
		
		Ttab_sel_music.setOnClickListener(new ImageView.OnClickListener(){

			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
                intent.setClass(TabSelActivity.this,MusicActivity.class);
                TabSelActivity.this.startActivityForResult(intent,1);
			}
        	
        });
		
		Ttab_sel_more.setOnClickListener(new ImageView.OnClickListener(){

			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
                intent.setClass(TabSelActivity.this,MoreActivity.class);
                intent.putExtra("email", email);
				TabSelActivity.this.startActivityForResult(intent,1);
			}
        	
        });
	}

}
