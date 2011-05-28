package com.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SettingValue_DBAdapter {
	public static final String DB2_KEY_ID = "_id";
	public static final String DB2_KEY_MODE = "mode";
	public static final String DB2_KEY_AVALUE = "avalue";

	private static final String TAG = "DBAdapter";
	private static final String DATABASE_NAME = "HelpWanted_SettingValue";
	private static final String DATABASE_TABLE2 = "SettingValue";
	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_CREATE2 = "create table SettingValue (_id integer primary key autoincrement, "
			+ "mode text not null, avalue text not null);";

	private final Context context;

	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public SettingValue_DBAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE2);
		}

		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS titles");
			onCreate(db);
		}
	}

	/**
	 * 打开数据库
	 * 
	 * @return DBAdapter对象
	 * @throws SQLException
	 */
	public SettingValue_DBAdapter open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	/**
	 * 关闭数据库
	 */
	public void close() {
		DBHelper.close();
	}

	/**
	 * 向数据库中插入一个组数据
	 * 
	 * @param mode
	 *            模式
	 * @param avalue
	 *            加速度值
	 * @return id号, -1为错误
	 */
	public long insertTitle(String mode, String avalue) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(DB2_KEY_MODE, mode);
		initialValues.put(DB2_KEY_AVALUE, avalue);
		return db.insert(DATABASE_TABLE2, null, initialValues);
	}

	/**
	 * 删除一组数据
	 * 
	 * @param id
	 *            id号
	 * @return 操作是否成功
	 */
	public boolean deleteTitle(long id) {
		return db.delete(DATABASE_TABLE2, DB2_KEY_ID + "=" + id, null) > 0;
	}

	/**
	 * 获得所有信息
	 * 
	 * @return 数据集指针
	 */
	public Cursor getAllTitles() {
		return db.query(DATABASE_TABLE2, new String[] { DB2_KEY_ID,
				DB2_KEY_MODE, DB2_KEY_AVALUE }, null, null, null, null, null);
	}

	/**
	 * 获得指定信息
	 * 
	 * @param rowId
	 *            id号
	 * @return 数据集指针
	 * @throws SQLException
	 *             SQL异常
	 */
	public Cursor getTitle(long rowId) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE2, new String[] {
				DB2_KEY_ID, DB2_KEY_MODE, DB2_KEY_AVALUE }, DB2_KEY_ID + "="
				+ rowId, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	/**
	 * 修改数据
	 * 
	 * @param rowId
	 *            id号
	 * @param mode
	 *            模式
	 * @param avalue
	 *            加速度值
	 * @return 操作是否成功
	 */
	public boolean updateTitle(long rowId, String mode, String avalue) {
		ContentValues args = new ContentValues();
		args.put(DB2_KEY_MODE, mode);
		args.put(DB2_KEY_AVALUE, avalue);
		return db.update(DATABASE_TABLE2, args, DB2_KEY_ID + "=" + rowId, null) > 0;
	}
}