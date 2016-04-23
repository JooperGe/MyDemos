package com.jooper.mydemos;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.jooper.mydemos.adapter.HomeAdapter;
import com.jooper.mydemos.adapter.HomeAdapter.OnItemClickLitener;
import com.jooper.mydemos.view.DividerGridItemDecoration;

/**
 * RecyclerView
 * 
 * @author Jooper
 *
 */
public class RecyclerViewAct extends Activity {

	private RecyclerView mRecyclerView;
	private List<String> mDatas;
	private HomeAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_recycler_view);

		initData();

		mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
		mAdapter = new HomeAdapter(this, mDatas);

		mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
				StaggeredGridLayoutManager.VERTICAL));
		mRecyclerView.setAdapter(mAdapter);

		mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
		// 设置item动画
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());

		initEvent();
	}

	private void initEvent() {
		mAdapter.setOnItemClickLitener(new OnItemClickLitener() {
			@Override
			public void onItemClick(View view, int position) {
				Toast.makeText(RecyclerViewAct.this, position + " click",
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onItemLongClick(View view, int position) {
				Toast.makeText(RecyclerViewAct.this, position + " long click",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	protected void initData() {
		mDatas = new ArrayList<String>();
		for (int i = 'A'; i < 'z'; i++) {
			mDatas.add("" + (char) i);
		}
	}
}
