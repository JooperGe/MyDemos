package com.jooper.mydemos;

import android.os.Bundle;
import android.os.Environment;

import com.jooper.mydemos.MyUtils.CopyFileForDb;
import com.jooper.mydemos.base.BaseAct;

public class DataBaseAct extends BaseAct {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_database_test);

		String oldPath = "data/data/com.cchong.BloodAssistant/databases/BloodAssistant.BodyCheck.db";

		String newPath = Environment.getExternalStorageDirectory()
				+ "/jooper2/BloodAssistant.BodyCheck.db";

		// Process process = null;
		// try {
		// process = Runtime.getRuntime().exec("su");
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// try {
		// String command = "chmod 777 /" + oldPath;
		// // String command = "su - chmod 777 "
		// // + "data/data/comm.cchong.BloodAssistant/databases/";
		//
		// Runtime runtime = Runtime.getRuntime();
		//
		// @SuppressWarnings("unused")
		// Process proc = runtime.exec(command);
		//
		// } catch (IOException e) {
		//
		// e.printStackTrace();
		// }
		CopyFileForDb.copyFile(oldPath, newPath);

		// // -------------------------
		// // String[] commands = new String[] { "/system/bin/sh", "-c",
		// // "chmod -R 777 /" + oldPath };
		// String[] commands = new String[] { "/system/bin/sh", "-c",
		// "chmod -R 777 /data/misc/123.sh" };
		// Process process = null;
		// DataOutputStream dataOutputStream = null;
		// try {
		// process = Runtime.getRuntime().exec("su");
		// dataOutputStream = new DataOutputStream(process.getOutputStream());
		// int length = commands.length;
		// for (int i = 0; i < length; i++) {
		// dataOutputStream.writeBytes(commands[i] + "\n");
		// }
		// dataOutputStream.writeBytes("exit\n");
		// dataOutputStream.flush();
		// process.waitFor();
		// } catch (Exception e) {
		// } finally {
		// try {
		// if (dataOutputStream != null) {
		// dataOutputStream.close();
		// }
		// process.destroy();
		// } catch (Exception e) {
		// }
		// }
		// // -------------------------

		//
		// File dir = new
		// File("data/data/comm.cchong.BloodAssistant/databases/");
		//
		// if (dir.exists() || dir.isDirectory()) {
		//
		// Log.d("Jooper", "dir������");
		// } else {
		//
		// Log.d("Jooper", "dir����");
		// }

		// File file = new File(dir, "BloodAssistant.BodyCheck.db");

		// DatabaseHelper databaseHelper = new DatabaseHelper(DataBaseAct.this,
		// "BloodAssistant.BodyCheck");
		//
		// SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

		// DBManager dbm = new DBManager(DataBaseAct.this);
		// SQLiteDatabase database = dbm.openDatabase();
		// Cursor cs = database.rawQuery("select * from bmi", null);
		// while (cs.moveToNext()) {
		// int i = cs.getColumnCount();
		// System.out.println(i);
		// }
	}
}
