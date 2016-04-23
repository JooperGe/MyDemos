package com.jooper.mydemos.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

public class DBManager {
	private final int BUFFER_SIZE = 400000;
	public static final String DB_NAME = "mecpic.db"; // ��������ݿ��ļ���
	public static final String PACKAGE_NAME = "com.example.tjise_dataservice";
	public static final String DB_PATH = "/data"
			+ Environment.getDataDirectory().getAbsolutePath() + "/"
			+ PACKAGE_NAME + "/databases"; // ���ֻ��������ݿ��λ��

	private SQLiteDatabase database;
	private Context context;

	public DBManager(Context context) {
		this.context = context;
		this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
	}

	public SQLiteDatabase openDatabase() {

		return this.database;
	}

	private SQLiteDatabase openDatabase(String dbfile) {
		try {
			// if (!(new File(dbfile).exists())) { //
			// �ж����ݿ��ļ��Ƿ���ڣ�����������ִ�е��룬����ֱ�Ӵ����ݿ�
			// InputStream is = this.context.getResources().openRawResource(
			// R.raw.mecpic); // ����������ݿ�
			// FileOutputStream fos = new FileOutputStream(dbfile);
			// byte[] buffer = new byte[BUFFER_SIZE];
			// int count = 0;
			// while ((count = is.read(buffer)) > 0) {
			// fos.write(buffer, 0, count);
			// }
			// fos.close();
			// is.close();
			// }
			SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,
					null);
			return db;
		} catch (Exception e) {
			Log.e("Database", "File not found");
			e.printStackTrace();
		}
		return null;
	}

	public void closeDatabase() {
		this.database.close();
	}

}
