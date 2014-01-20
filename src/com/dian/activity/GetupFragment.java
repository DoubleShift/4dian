package com.dian.activity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import com.dian.logic.Api;
import com.dian.util.DBManager;
import com.me.bean.Account;
import com.me.bean.GetDate;
import com.me.bean.GetUpList;
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


public class GetupFragment extends Fragment {
    private static final String TAG = "GetupFragment";

    private LinearLayout chartLayout = null;

    private GetDate nowtime = new GetDate();// 得到当前时间
	private String seriesText = nowtime.getYear() + "年" + nowtime.getMonth()+ "月起床记录";// 本月折线图名称

	private GetUpList gplist = null;// api list

	private int MINHOUR = 0;
	private int MAXHOUR = 24;

	private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset(); // 进行显示数据

	private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer(); // 构造显示用渲染图

	private XYSeries mCurrentSeries; // 主折线图

	private XYSeriesRenderer mCurrentRenderer;

	private String mDateFormat;

	private GraphicalView mChartView; // chart控件
	
	private DBManager db;

    static GetupFragment newInstance() {
        GetupFragment newFragment = new GetupFragment();
        return newFragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获得起床的数据表
        Log.d(TAG, "GetupFragment-----onCreate");
        Api hc = new Api();
	//	gplist = hc.GetUpDate(nowtime.getYear(), nowtime.getMonth(), 1);
		
//        db = new DBManager(getActivity().getApplicationContext());
//		
//		
//		db.addGetUpList(gplist);
//		Account user = new Account("1","4dian","4dian@qq.com","123456","2.png", "男","12-17岁","忘记啦","南昌","http://4dian.me","我是美女","2");
//		db.setUserInfo(user);
//		user.setArchive("考研", "高考考研", "2500", "2014-03-09", "1.4");
//		db.setUserArchive(user);
		
		Random r = new Random();
		for(int i =0;i<nowtime.GetMonthDays();i++){
			TimeManage tm = new TimeManage("1", "2013", "03", Integer.toString(i), r.nextDouble() * 5, r.nextDouble() * 5, r.nextDouble() * 5, r.nextDouble() * 5, 24, "就是想睡觉");
			db.addTimeManage(tm);
		}
		
		initChart(nowtime.GetMonthDays());// 当前月最大天数为x轴
		db.closeDB();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        Log.d(TAG, "GetupFragment-----onCreateView");
        View view = inflater.inflate(R.layout.home_getup_layout, container, false);
        
        chartLayout =(LinearLayout) view.findViewById(R.id.getupchart);
        
        if (mChartView == null) {
			mChartView = ChartFactory.getLineChartView(getActivity().getApplicationContext(), mDataset,mRenderer);
			chartLayout.addView(mChartView);

		} else {
			
			mChartView.repaint();
		}
        
        return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "GetupFragment-----onDestroy");
    }
    
	/////////////////Chart/////////////////////
	public void initChart(int maxDay) {
		// 创建 1跟折线图（画笔、渲染）

		XYSeries thisMonthSeries = new XYSeries(seriesText);
		mDataset.addSeries(thisMonthSeries);
		mCurrentSeries = thisMonthSeries;
		XYSeriesRenderer seriesRenderer = new XYSeriesRenderer();

		/*
		 * 可以多个折线
		 */
		// 设置seriesRenderer风格
		int pen_color = this.getResources().getColor(R.color.title);
		seriesRenderer.setColor(pen_color); // 画笔颜色
		// seriesRenderer.setFillBelowLine(true); // 确定填充
		// seriesRenderer.setFillBelowLineColor(Color.RED); // 填充颜色
		seriesRenderer.setPointStyle(PointStyle.CIRCLE); // 设置画笔风格
		// seriesRenderer.setLineWidth(3.0f); // 设置画笔宽度

		mRenderer.addSeriesRenderer(seriesRenderer);// 这条折线添加到图表中

		// 设置图表风格

		mRenderer.setShowGrid(true);
		mRenderer.setShowCustomTextGrid(true);
		mRenderer.setXLabelsAlign(Align.LEFT);
		mRenderer.setYLabelsAlign(Align.LEFT);
		mRenderer.setChartTitleTextSize(20);

		mRenderer.setLegendTextSize(15);

		mRenderer.setAxesColor(Color.GRAY); // 设置XY轴颜色
		mRenderer.setLabelsColor(Color.LTGRAY);// 设置轴标签颜色

		int color = this.getResources().getColor(R.color.vpi__bright_foreground_inverse_holo_light); // 外围背景色

		mRenderer.setBackgroundColor(Color.WHITE);// 设置图表内部颜色
		mRenderer.setMarginsColor(color);// 设置周围的黑框颜色

		mRenderer.setPanEnabled(true); // 是否可以拖动
		mRenderer.setShowGrid(true); // 设置背景格子
		mRenderer.setClickEnabled(false);

	    
		// mRenderer.setZoomRate(1.1f);

		mRenderer.setFitLegend(true);// 调整合适的位置
		// 可变的部分
		mRenderer.setChartTitle("");// 圖表的title

		mRenderer.setYAxisMin(MINHOUR); // 设置Y维度最小值
		mRenderer.setXAxisMin(0); // 设置X维度最小值
		mRenderer.setYAxisMax(MAXHOUR); // 设置Y维度最大值
		mRenderer.setXAxisMax(maxDay);// 设置X维度最大值

		mRenderer.setXLabels(maxDay); // 设置X坐标分成31份
		mRenderer.setYLabels(14); // 设置Y轴显示的刻度标签的个数

		mRenderer.setXLabelsColor(pen_color);
		mRenderer.setYLabelsColor(0, pen_color);// 第一个参数一定为0，其他的报错，why？

		// 缩放图表功能
		mRenderer.setPanLimits(new double[] { 0, maxDay, 0, MAXHOUR });// 设置x，y轴变化范围的,但是有bug，反复上下拖拽，y的变化范围会增加
		// mRenderer.setZoomEnabled(false);//缩放
		// mRenderer.setZoomLimits(new double[] { 0, maxDay, 0, 24});// 缩放范围
		// mRenderer.setZoomButtonsVisible(false);//缩放按钮

		int length = mRenderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			((XYSeriesRenderer) mRenderer.getSeriesRendererAt(i))
					.setFillPoints(true);
		}

		mCurrentRenderer = seriesRenderer;

		// // 数据绘制 To Do
		// double x = 0;
		// double y = 0;
		//
		//
		//
		//
		// for (int i = 0; i < xyValues.length; i++) {
		// x = xyValues[i][0];
		// y = xyValues[i][1];
		// mCurrentSeries.add(x, y);
		// }
		
		HashMap<Integer , Double> map =getValue(31);

		for(Entry<Integer , Double> entry:map.entrySet())
		  {
			mCurrentSeries.add(entry.getKey(), entry.getValue());
		   
		  }
	}
    
	/**
	 * 数据入口
	 * 
	 * @return x、y 坐标集
	 */

	private HashMap<Integer , Double>  getValue(int maxday) {
		
		HashMap<Integer , String>  list = db.getGetUpList("1", "2013", "06", maxday);
		HashMap<Integer , Double>  values = new HashMap<Integer , Double>();
	
		MINHOUR = 24;
		MAXHOUR = 0;
		
		for(Entry<Integer, String> entry:list.entrySet())
		  {
			int d = entry.getKey();
			String t = entry.getValue();
			
			String h = t.substring(11, 13);
			String m = t.substring(14, 16);
			double a = Double.parseDouble(h);
			double b = Double.parseDouble(m);
			a = a + b / 60;

			values.put(d, a);

			if (MINHOUR > Integer.parseInt(h)) {
				MINHOUR = Integer.parseInt(h);
			}

			if (MAXHOUR < Integer.parseInt(h)) {
				MAXHOUR = Integer.parseInt(h);
			}
		  }

		// 获取最大最小hour
		if (MINHOUR < 2) {
			MINHOUR = 0;
		} else {
			MINHOUR = MINHOUR - 2;
		}

		if (MAXHOUR > 22) {
			MAXHOUR = 24;
		} else {
			MAXHOUR = MAXHOUR + 2;
		}

		return values;

	}

}