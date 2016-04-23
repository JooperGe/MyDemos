package com.jooper.mydemos;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.jooper.mydemos.base.BaseAct;

/**
 * 摄像头有关-测试
 * 
 * @author Jooper
 *
 */
public class CameraTest extends BaseAct implements OnClickListener {

	private Button btn_open_camera, btn_close_camera, btn_open_splash,
			btn_close_splash;

	private TextView tv_camera;

	private Camera mCamera;
	private Parameters mParameters;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_camera_test);

		btn_open_camera = (Button) findViewById(R.id.btn_open_camera);
		btn_open_camera.setOnClickListener(this);
		btn_close_camera = (Button) findViewById(R.id.btn_close_camera);
		btn_close_camera.setOnClickListener(this);
		btn_open_splash = (Button) findViewById(R.id.btn_open_flash);
		btn_open_splash.setOnClickListener(this);
		btn_close_splash = (Button) findViewById(R.id.btn_close_flash);
		btn_close_splash.setOnClickListener(this);

		tv_camera = (TextView) findViewById(R.id.tv_camera);

		checkFlashlight();
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_open_camera:

			break;
		case R.id.btn_close_camera:

			break;
		case R.id.btn_open_flash:

			openFlashlight();

			break;
		case R.id.btn_close_flash:

			closeFlashlight();

			break;
		}
	}

	// 检测当前设备是否配置闪光灯
	private boolean checkFlashlight() {
		if (!getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_CAMERA_FLASH)) {

			tv_camera.setText("当前设备没有闪光灯");

			return false;
		} else {

			tv_camera.setText("有闪光灯");

		}
		return true;
	}

	// 打开闪光灯
	@SuppressLint("NewApi")
	void openFlashlight() {

		try {
			mCamera = Camera.open();
			int textureId = 0;
			mCamera.setPreviewTexture(new SurfaceTexture(textureId));
			mCamera.startPreview();

			mParameters = mCamera.getParameters();

			mParameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
			mCamera.setParameters(mParameters);

		} catch (Exception e) {

			Log.d("Jooper", "异常");
		}
	}

	// 关闭闪光灯
	void closeFlashlight() {

		if (mCamera != null) {
			mParameters = mCamera.getParameters();
			mParameters.setFlashMode(Parameters.FLASH_MODE_OFF);
			mCamera.setParameters(mParameters);
			mCamera.stopPreview();
			mCamera.release();
			mCamera = null;
		}
	}

}
