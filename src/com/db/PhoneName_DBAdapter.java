package com.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PhoneName_DBAdapter {
	public static final String DB1_KEY_ID = "_id";
	public static final String DB1_KEY_NAME = "name";
	public static final String DB1_KEY_PHONE = "phone";

	private static final String TAG = "DBAdapter";
	private static final String DATABASE_NAME = "HelpWanted_PhoneName";
	private static final String DATABASE_TABLE1 = "PhoneName";
	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_CREATE1 = "create table PhoneName (_id integer primary key autoincrement, "
			+ "name text not null, phone text not null);";

	private final Context context;

	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public PhoneName_DBAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE1);
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
	public PhoneName_DBAdapter open() throws SQLException {
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
	 * @param name
	 *            ����
	 * @param phone
	 *            �ֻ���
	 * @return id��, -1Ϊ����
	 */
	public long insertTitle(String name, String phone) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(DB1_KEY_NAME, name);
		initialValues.put(DB1_KEY_PHONE, phone);
		return db.insert(DATABASE_TABLE1, null, initialValues);
	}

	/**
	 * ɾ��һ������
	 * 
	 * @param id
	 *            id��
	 * @return �����Ƿ�ɹ�
	 */
	public boolean deleteTitle(long id) {
		return db.delete(DATABASE_TABLE1, DB1_KEY_ID + "=" + id, null) > 0;
	}

	/**
	 * ���������Ϣ
	 * 
	 * @return ���ݼ�ָ��
	 */
	public Cursor getAllTitles() {
		return db.query(DATABASE_TABLE1, new String[] { DB1_KEY_ID,
				DB1_KEY_NAME, DB1_KEY_PHONE }, null, null, null, null, null);
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
		Cursor mCursor = db.query(true, DATABASE_TABLE1, new String[] {
				DB1_KEY_ID, DB1_KEY_NAME, DB1_KEY_PHONE }, DB1_KEY_ID + "="
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
	 * @param name
	 *            ����
	 * @param phone
	 *            �ֻ���
	 * @return �����Ƿ�ɹ�
	 */
	public boolean updateTitle(long rowId, String name, String phone) {
		ContentValues args = new ContentValues();
		args.put(DB1_KEY_NAME, name);
		args.put(DB1_KEY_PHONE, phone);
		return db.update(DATABASE_TABLE1, args, DB1_KEY_ID + "=" + rowId, null) > 0;
	}
}