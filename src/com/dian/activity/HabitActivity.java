package com.dian.activity;

import java.util.ArrayList;
import java.util.List;

import com.me.dian.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HabitActivity extends Activity {

	private ListView listView;
	private List<DemoClass> lt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_habit);
		listView = (ListView) this.findViewById(R.id.listView_habit);
		lt = new ArrayList<DemoClass>();
		DemoClass d1 = new DemoClass();
		d1.time = "2013年05月";
		d1.month = "5";
		d1.content = "平均8:22 起床，学习时间169小时";
		lt.add(d1);

		DemoClass d2 = new DemoClass();
		d2.time = "2013年04月";
		d2.month = "4";
		d2.content = "平均8:04 起床，学习时间178小时";
		lt.add(d2);

		DemoClass d3 = new DemoClass();
		d3.time = "2013年03月";
		d3.month = "3";
		d3.content = "平均9:46 起床，学习时间56小时";
		lt.add(d3);
		listView.setAdapter(new MyAdapter());
	}

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return lt.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return lt.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			if (arg1 == null) {
				arg1 = View.inflate(HabitActivity.this, R.layout.habit_item,
						null);
			}
			final TextView timeView = (TextView) arg1
					.findViewById(R.id.habit_textView_time_detail);
			final TextView monthView = (TextView) arg1
					.findViewById(R.id.habit_textView_month);
			final TextView contentView = (TextView) arg1
					.findViewById(R.id.habit_textView_content);

			timeView.setText(lt.get(arg0).time);
			monthView.setText(lt.get(arg0).month);
			contentView.setText(lt.get(arg0).content);
			contentView.setTag(arg0);
			contentView.setOnClickListener(new View.OnClickListener() {

				@SuppressLint("ShowToast")
				@Override
				public void onClick(View arg0) {
					Toast.makeText(
							HabitActivity.this,
							lt.get(Integer.parseInt(arg0.getTag().toString())).content,
							3000).show();
				}
			});

			return arg1;
		}

	}

	class DemoClass {
		String time;
		String month;
		String content;
	}
}
