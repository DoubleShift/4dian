package com.dian.activity;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import com.dian.util.DBManager;
import com.me.bean.GetDate;
import com.me.bean.TimeManage;
import com.me.dian.R;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ShowRateFragment extends Fragment {
	private static final String TAG = "ShowRateFragment";

	private DefaultRenderer renderer = new DefaultRenderer(); // 构造显示用渲染图

	private GraphicalView mChartView;// 使用的view
	private LinearLayout layout;
	private ImageView img_score;

	private GetDate nowtime = new GetDate();// 得到当前时间
	private DBManager db ;
	private TimeManage tm;
	private String uid ="1";

	static ShowRateFragment newInstance() {
		ShowRateFragment newFragment = new ShowRateFragment();
		return newFragment;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "ShowRateFragment-----onCreate");
		
		db = new DBManager(getActivity().getApplicationContext());
		tm= db.getTimeManage(
				"1",
				Integer.toString(nowtime.getYear()),
				"0"+Integer.toString(nowtime.getMonth()),
				Integer.toString(nowtime.getDay()));
		
		initChart();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d(TAG, "ShowRateFragment-----onCreateView");
		View view = inflater.inflate(R.layout.timemanagement_show_rate_layout,container, false);

		if (mChartView == null) {
			img_score = (ImageView) view.findViewById(R.id.score);
			layout = (LinearLayout) view.findViewById(R.id.timemanagechart);
			mChartView = ChartFactory.getPieChartView(getActivity().getApplicationContext(),
					buildCategoryDataset("时间分配", getName(), getValues()),
					renderer);
			layout.addView(mChartView);
			showScore(2);// 显示评分动画，1~4
		} else {
			mChartView.repaint();
			
		}
		return view;

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "ShowRateFragment-----onDestroy");
	}

	public void initChart() {
		

		renderer = buildCategoryRenderer(getColor());

		
		renderer.setShowCustomTextGrid(false);
		renderer.setShowLabels(true);
		renderer.setFitLegend(true);
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15); // 图例文字大小
		renderer.setLabelsColor(Color.BLACK); // 图例标签的颜色
		renderer.setPanEnabled(false); // 是否可以拖动
		int color = this.getResources().getColor(R.color.vpi__background_holo_light); // 外围背景色
		renderer.setBackgroundColor(color);
	}

	/*
	 * 评分动画
	 */
	private void showScore(int score) {
		
		switch (score) {
		case 1:
			img_score.setBackgroundResource(R.drawable.a);
			break;
		case 2:
			img_score.setBackgroundResource(R.drawable.b);
			break;
		case 3:
			img_score.setBackgroundResource(R.drawable.c);
			break;
		case 4:
			img_score.setBackgroundResource(R.drawable.d);
			break;

		}

		Animation scaleAnimation = new ScaleAnimation(0.0f, 1.3f, 0.0f, 1.3f,
				Animation.RELATIVE_TO_SELF, 0.2f, Animation.RELATIVE_TO_SELF,
				0.5f);

		scaleAnimation.setDuration(1500); // 动画持续时间
		img_score.setAnimation(scaleAnimation); // 设置动画
		scaleAnimation.startNow(); // 启动动画
	}

	protected String[] getName() {
		String[] names = { 
				this.getResources().getString(R.string.sleep),
				this.getResources().getString(R.string.regular),
				this.getResources().getString(R.string.waste),
				this.getResources().getString(R.string.invest),
				this.getResources().getString(R.string.unknown)};
		return names;
	}

	protected double[] getValues() {
		
		double[] values = new double[] { 
				tm.getSleep(),
				tm.getRegular(), 
				tm.getWaste(), 
				tm.getInvest(), 
				24.0-tm.getSleep()-tm.getRegular()-tm.getWaste()-tm.getInvest()};
		return values;
	}

	protected int[] getColor() {

		int[] colors = { 
				this.getResources().getColor(R.color.sleep),
				this.getResources().getColor(R.color.regular),
				this.getResources().getColor(R.color.waste),
				this.getResources().getColor(R.color.invest),
				this.getResources().getColor(R.color.unknown)};
		return colors;
	}

	/*
	 * 添加颜色标记
	 */
	protected DefaultRenderer buildCategoryRenderer(int[] colors) {
		for (int color : colors) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(color);
			renderer.addSeriesRenderer(r);// 添加一个数据项
		}
		return renderer;
	}

	/*
	 * 添加数据项，name，values和colors的长度要一样
	 */
	protected CategorySeries buildCategoryDataset(String title, String name[],
			double[] values) {
		CategorySeries series = new CategorySeries(title);

		for (int i = 0; i < name.length; i++) {
			series.add(name[i], values[i]);
		}

		return series;
	}

}