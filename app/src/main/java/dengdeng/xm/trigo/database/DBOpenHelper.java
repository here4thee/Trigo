package dengdeng.xm.trigo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * Create and modify database.
 *
 */
public class DBOpenHelper extends SQLiteOpenHelper {
	
	private static final int VERSION = 1;
	private static final String DBNAME = "trigo.db";
	public DBOpenHelper(Context context) {
		super(context, DBNAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS userinfo(id INTEGER PRIMARY KEY AUTOINCREMENT, email text not null, password text not null, name text, whatsup text, profile text, target integer, times integer, round integer, record integer);");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
}