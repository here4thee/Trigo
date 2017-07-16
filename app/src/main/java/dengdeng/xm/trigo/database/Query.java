package dengdeng.xm.trigo.database;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import dengdeng.xm.trigo.database.DBOpenHelper;
import dengdeng.xm.trigo.domain.AccountInfo;

public class Query{

	private DBOpenHelper helper;
	private SQLiteDatabase db;
	
	public Query(Context context) {
		helper = new DBOpenHelper(context);
	}
	
	public void addAccountInfo(AccountInfo accountInfo) {
		db = helper.getWritableDatabase();
		if(accountInfo != null){
			db.execSQL("insert into userinfo (id,email,password,name,whatsup,profile,target,times,round,record) values (?,?,?,?,?,?,?,?,?,?);",
					new Object[] { null, accountInfo.getEmail(), accountInfo.getPassword(), accountInfo.getName(), accountInfo.getWhatsup(),
					accountInfo.getProfile(), accountInfo.getTarget(), accountInfo.getTimes(), accountInfo.getRound(), 
					accountInfo.getRecord() });
		}
	}
	
    public void updateName(AccountInfo accountInfo) {
    	db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", accountInfo.getName());
        db.update("userinfo", cv, "email = ?", new String[]{accountInfo.getEmail()});
    }
    
    public void updateWhatsup(AccountInfo accountInfo) {
    	db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();  
        cv.put("whatsup", accountInfo.getWhatsup());  
        db.update("userinfo", cv, "email = ?", new String[]{accountInfo.getEmail()});
    }
    
    public void updateProfile(AccountInfo accountInfo) {
    	db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("profile", accountInfo.getProfile());
        db.update("userinfo", cv, "email = ?", new String[]{accountInfo.getEmail()});
    }
    
    public void updateTarget(AccountInfo accountInfo) {
    	db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();  
        cv.put("target", accountInfo.getTarget());
        db.update("userinfo", cv, "email = ?", new String[]{accountInfo.getEmail()});
    }
    
    public void updateTimes(AccountInfo accountInfo) {
    	db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();  
        cv.put("times", accountInfo.getTimes());
        db.update("userinfo", cv, "email = ?", new String[]{accountInfo.getEmail()});
    }
    
    public void updateRound(AccountInfo accountInfo) {
    	db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();  
        cv.put("round", accountInfo.getRound());
        db.update("userinfo", cv, "email = ?", new String[]{accountInfo.getEmail()});
    }
    
    public void updateRecord(AccountInfo accountInfo) {
    	db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();  
        cv.put("record", accountInfo.getRecord());
        db.update("userinfo", cv, "email = ?", new String[]{accountInfo.getEmail()});
    }
    
    public ArrayList<AccountInfo> queryAll() {
    	db = helper.getWritableDatabase();
    	ArrayList<AccountInfo> users = new ArrayList<AccountInfo>();  
        Cursor c = db.rawQuery("select * from userinfo order by round desc;", null);
        while (c.moveToNext()) {
        	AccountInfo user = new AccountInfo();
        	user.setId(c.getInt(c.getColumnIndex("id")));
        	user.setEmail(c.getString(c.getColumnIndex("email")));
        	user.setPassword(c.getString(c.getColumnIndex("password")));
        	user.setName(c.getString(c.getColumnIndex("name")));
        	user.setWhatsup(c.getString(c.getColumnIndex("whatsup")));
        	user.setProfile(c.getString(c.getColumnIndex("profile")));
        	user.setTarget(c.getInt(c.getColumnIndex("target")));
        	user.setTimes(c.getInt(c.getColumnIndex("times")));
        	user.setRound(c.getInt(c.getColumnIndex("round")));
        	user.setRecord(c.getInt(c.getColumnIndex("record")));
            users.add(user);
        }  
        c.close();
        return users;
    }
}
