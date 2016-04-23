package com.jooper.mydemos;

import java.util.Timer;
import java.util.TimerTask;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.jooper.mydemos.base.BaseAct;

/**
 * 动态曲线图
 * 
 * @author Jooper
 *
 */
public class ChartDynamicTest extends BaseAct {

	private LinearLayout ll_chart;
	private Context mContext;

	// 曲线
	private Timer timer = new Timer();
	private TimerTask task;
	private static int gx;
	private static int j;

	private static double flag = 1;
	private Handler handler;
	private String title = "pulse";
	private XYSeries series;
	private GraphicalView chart;
	private XYMultipleSeriesDataset mDataset;
	private XYMultipleSeriesRenderer renderer;
	private int addX = -1;
	double addY = 10;
	int[] xv = new int[300];
	int[] yv = new int[300];
	int[] hua = new int[] { 9, 10, 11, 12, 13, 14, 13, 12, 11, 10, 9, 8, 7, 6,
			7, 8, 9, 10, 11, 10, 10 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chart_test);

		mContext = this;

		ll_chart = (LinearLayout) findViewById(R.id.ll_chart);

		initChart();
	}

	private void initChart() {

		// 这个类用来放置曲线上的所有点，是一个点的集合，根据这些点画出曲线
		series = new XYSeries(title);

		// 创建一个数据集的实例，这个数据集将被用来创建图表
		mDataset = new XYMultipleSeriesDataset();

		// 将点集添加到这个数据集中
		mDataset.addSeries(series);

		// 以下都是曲线的样式和属性等等的设置，renderer相当于一个用来给图表做渲染的句柄
		int color = Color.GREEN;
		PointStyle style = PointStyle.DIAMOND;
		renderer = buildRenderer(color, style, true);

		// 设置好图表的样式
		setChartSettings(renderer, "X", "Y", 0, 100, 4, 16, Color.WHITE,
				Color.WHITE);

		// 生成图表
		chart = ChartFactory.getLineChartView(mContext, mDataset, renderer);

		// 将图表添加到布局中去
		ll_chart.addView(chart, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));

		/*
		 * thread = new Thread(){ public void arrayList(int u) { ArrayList
		 * arrayList = new ArrayList();
		 * arrayList.add(HardwareControler.readADC()); } };
		 */
		// 这里的Handler实例将配合下面的Timer实例，完成定时更新图表的功能
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// 刷新图表
				updateChart();
				super.handleMessage(msg);
			}
		};

		task = new TimerTask() {
			@Override
			public void run() {
				Message message = new Message();
				message.what = 1;
				handler.sendMessage(message);
			}
		};

		timer.schedule(task, 1, 20); // 曲线
	}

	protected XYMultipleSeriesRenderer buildRenderer(int color,
			PointStyle style, boolean fill) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();

		// 设置图表中曲线本身的样式，包括颜色、点的大小以及线的粗细等
		XYSeriesRenderer r = new XYSeriesRenderer();
		r.setColor(Color.RED);
		// r.setPointStyle(null);
		// r.setFillPoints(fill);
		r.setLineWidth(1);
		renderer.addSeriesRenderer(r);

		return renderer;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		timer.cancel();
	}

	protected void setChartSettings(XYMultipleSeriesRenderer renderer,
			String xTitle, String yTitle, double xMin, double xMax,
			double yMin, double yMax, int axesColor, int labelsColor) {
		// 有关对图表的渲染可参看api文档
		renderer.setChartTitle(title);
		renderer.setXTitle(xTitle);
		renderer.setYTitle(yTitle);
		renderer.setXAxisMin(xMin);
		renderer.setXAxisMax(xMax);
		renderer.setYAxisMin(yMin);
		renderer.setYAxisMax(yMax);
		renderer.setAxesColor(axesColor);
		renderer.setLabelsColor(labelsColor);
		renderer.setShowGrid(true);
		renderer.setGridColor(Color.GREEN);
		renderer.setXLabels(100);
		renderer.setYLabels(40);
		renderer.setXTitle("Time");
		renderer.setYTitle("mmHg");
		renderer.setYLabelsAlign(Align.RIGHT);
		renderer.setPointSize((float) 3);
		renderer.setShowLegend(true);
	}

	private void updateChart() {

		// 移除数据集中旧的点集
		mDataset.removeSeries(series);

		// 判断当前点集中到底有多少点，因为屏幕总共只能容纳100个，所以当点数超过100时，长度永远是100
		int length = series.getItemCount();
		int bz = 0;
		// addX = length;
		if (length > 300) {
			length = 300;
			bz = 1;
		}
		addX = length;
		// TODO 将旧的点集中x和y的数值取出来放入backup中，并且将x的值加1，造成曲线向右平移的效果
		for (int i = 0; i < length; i++) {
			xv[i] = (int) series.getX(i) - bz;
			yv[i] = (int) series.getY(i);
		}

		// 点集先清空，为了做成新的点集而准备
		series.clear();
		mDataset.addSeries(series);
		// 将新产生的点首先加入到点集中，然后在循环体中将坐标变换后的一系列点都重新加入到点集中
		// 这里可以试验一下把顺序颠倒过来是什么效果，即先运行循环体，再添加新产生的点
		series.add(addX, addY);
		addY++;
		if (addY > 10) {// 循环改变Y轴的值

			addY = 1;
		}
		for (int k = 0; k < length; k++) {
			series.add(xv[k], yv[k]);
		}

		// 在数据集中添加新的点集
		// mDataset.addSeries(series);

		// 视图更新，没有这一步，曲线不会呈现动态
		// 如果在非UI主线程中，需要调用postInvalidate()，具体参考api
		chart.invalidate();
	} // 曲线
}
