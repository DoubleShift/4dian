package com.dian.activity;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import com.dian.util.DBManager;
import com.me.bean.GetDate;
import com.me.bean.TimeManage;
import com.me.dian.R;

import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class TimeRateFragment extends Fragment {
	private static final String TAG = "TimeRateFragment";

	private GraphicalView mChartView;// 使用的view

	private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();// 渲染器
	private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();// 数据集

	private LinearLayout chartLayout = null;

	private DBManager db;
	private ArrayList list;

	static TimeRateFragment newInstance() {
		TimeRateFragment newFragment = new TimeRateFragment();
		return newFragment;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "TimeRateFragment-----onCreate");
		GetDate nowtime = new GetDate();// 得到当前时间

		db = new DBManager(getActivity().getApplicationContext());

		list = db.getTimeManage("1", "2013", "03");

		initChart(nowtime.GetMonthDays());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d(TAG, "TimeRateFragment-----onCreateView");
		View view = inflater.inflate(R.layout.home_time_rate_layout, container,
				false);
		chartLayout = (LinearLayout) view.findViewById(R.id.timeratechart);

		if (mChartView == null) {
			mChartView = ChartFactory
					.getBarChartView(getActivity().getApplicationContext(),
							mDataset, mRenderer, Type.STACKED);// Type.STACKED,在一个x轴上显示多个数据
			chartLayout.addView(mChartView);

		} else {
			mChartView.repaint();
		}
		return view;

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "TimeRateFragment-----onDestroy");
	}

	public void initChart(int MaxDay) {
		int[] colors = { this.getResources().getColor(R.color.unknown),
				this.getResources().getColor(R.color.sleep),
				this.getResources().getColor(R.color.regular),
				this.getResources().getColor(R.color.waste),
				this.getResources().getColor(R.color.invest) };

		String[] titles = { this.getResources().getString(R.string.unknown),
				this.getResources().getString(R.string.sleep),
				this.getResources().getString(R.string.regular),
				this.getResources().getString(R.string.waste),
				this.getResources().getString(R.string.invest) };

		int colorinner = this.getResources().getColor(R.color.unknown);// 内部颜色
		mRenderer.setBackgroundColor(colorinner);

		int colorground = this.getResources().getColor(
				R.color.vpi__background_holo_light); // 外围背景色
		mRenderer.setMarginsColor(colorground);// 设置周围的黑框颜色

		int pen_color = this.getResources().getColor(R.color.title);
		mRenderer.setXLabelsColor(pen_color); // x轴label颜色

		mRenderer.setPanLimits(new double[] { 0, 31, 0, 24 });// 设置拉动的范围
		// mRenderer.setZoomLimits(new double[] { 0.5, 20, 1, 150 });//设置缩放的范围
		// mRenderer.setRange(new double[]{0d, 5d, 0d, 100d}); //设置chart的视图范围
		mRenderer.setFitLegend(true);// 调整合适的位置

		mRenderer.setAxisTitleTextSize(16);
		mRenderer.setChartTitleTextSize(20);
		mRenderer.setLabelsTextSize(15);
		mRenderer.setLegendTextSize(15);

		mRenderer.setXAxisMin(0);
		mRenderer.setXAxisMax(MaxDay);
		mRenderer.setYAxisMin(0);
		mRenderer.setYAxisMax(24);

		mRenderer.setXLabels(14);// 设置14个，然后setFitLegend（true）可以不显示所有的xlabels。
		mRenderer.setYLabels(0);// 不显示数字

		mRenderer.setXLabelsAlign(Align.LEFT);
		mRenderer.setYLabelsAlign(Align.LEFT);

		mRenderer.setAxesColor(Color.GRAY); // 设置XY轴颜色
		mRenderer.setLabelsColor(Color.LTGRAY);// 设置轴标签颜色

		mRenderer.setPanEnabled(true, false);// 拖动
		mRenderer.setZoomEnabled(true);// 缩放
		// mRenderer.setZoomRate(1.1f);
		mRenderer.setBarSpacing(0.1f);

		int length = colors.length;
		for (int i = 0; i < length; i++) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(colors[i]);
			mRenderer.addSeriesRenderer(r);
		}

		mRenderer.getSeriesRendererAt(0).setDisplayChartValues(false);
		mRenderer.getSeriesRendererAt(1).setDisplayChartValues(false);
		mRenderer.getSeriesRendererAt(2).setDisplayChartValues(false);
		mRenderer.getSeriesRendererAt(3).setDisplayChartValues(false);

		length = titles.length;
		/*
		 * 优先加入的应该是较大的值，所有图表都是从底部开始绘制的。 也就是说要+5个，先是一个24的灰色条，然后是睡眠，换算公式 睡眠 = 固定+
		 * 睡眠 固定 = 浪费+固定 浪费 = 投资+浪费 投资 = 投资
		 */
		double[][] s = new double[5][MaxDay];
		for (int j = 0; j < list.size(); j++) {
			TimeManage tm = (TimeManage) list.get(j);
			s[0][j] = tm.getSleep();
			s[1][j] = tm.getRegular() + s[0][j];
			s[2][j] = tm.getWaste() + s[1][j];
			s[3][j] = tm.getInvest() + s[2][j];
			s[4][j] = tm.getUnknow();

		}
		for (int i = 0; i < length; i++) {
			CategorySeries series = new CategorySeries(titles[i]);

			for (int k = 0; k < MaxDay; k++) {
				series.add(s[length - i - 1][k]);
			}
			mDataset.addSeries(series.toXYSeries());
		}

	}

}