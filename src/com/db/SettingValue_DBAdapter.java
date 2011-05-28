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
	 * �����ݿ�
	 * 
	 * @return DBAdapter����
	 * @throws SQLException
	 */
	public SettingValue_DBAdapter open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	/**
	 * �ر����ݿ�
	 */
	public void close() {
		DBHelper.close();
	}

	/**
	 * �����ݿ��в���һ��������
	 * 
	 * @param mode
	 *            ģʽ
	 * @param avalue
	 *            ���ٶ�ֵ
	 * @return id��, -1Ϊ����
	 */
	public long insertTitle(String mode, String avalue) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(DB2_KEY_MODE, mode);
		initialValues.put(DB2_KEY_AVALUE, avalue);
		return db.insert(DATABASE_TABLE2, null, initialValues);
	}

	/**
	 * ɾ��һ������
	 * 
	 * @param id
	 *            id��
	 * @return �����Ƿ�ɹ�
	 */
	public boolean deleteTitle(long id) {
		return db.delete(DATABASE_TABLE2, DB2_KEY_ID + "=" + id, null) > 0;
	}

	/**
	 * ���������Ϣ
	 * 
	 * @return ���ݼ�ָ��
	 */
	public Cursor getAllTitles() {
		return db.query(DATABASE_TABLE2, new String[] { DB2_KEY_ID,
				DB2_KEY_MODE, DB2_KEY_AVALUE }, null, null, null, null, null);
	}

	/**
	 * ���ָ����Ϣ
	 * 
	 * @param rowId
	 *            id��
	 * @return ���ݼ�ָ��
	 * @throws SQLException
	 *             SQL�쳣
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
	 * �޸�����
	 * 
	 * @param rowId
	 *            id��
	 * @param mode
	 *            ģʽ
	 * @param avalue
	 *            ���ٶ�ֵ
	 * @return �����Ƿ�ɹ�
	 */
	public boolean updateTitle(long rowId, String mode, String avalue) {
		ContentValues args = new ContentValues();
		args.put(DB2_KEY_MODE, mode);
		args.put(DB2_KEY_AVALUE, avalue);
		return db.update(DATABASE_TABLE2, args, DB2_KEY_ID + "=" + rowId, null) > 0;
	}
}