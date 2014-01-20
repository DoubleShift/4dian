package com.dian.logic;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Service;
import android.content.Context;

import android.content.Intent;

import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

/**
 * @author chenx
 * @email fjjwlzd@163.com
 * @version 2014-1-20 下午7:26:06
 */
public class MainService extends Service implements Runnable {
	private boolean isrun = true;
	public static ArrayList<Activity> allActivity = new ArrayList<Activity>();// 保存所有Activity
	public static ArrayList<Task> allTask = new ArrayList<Task>(); // task 列队

	// 添加task
	public static void newTask(Task task) {
		allTask.add(task);
	}

	// 从集合中通过name获取Activity对象
	// 由于这个比较搓逼的比较方法，所以2个activity的名字不能有包含关系
	public static Activity getActivityByName(String name) {
		for (Activity ac : allActivity) {
			if (ac.getClass().getName().indexOf(name) >= 0) {
				return ac;
			}
		}
		return null;
	}

	// 启动线程
	@Override
	public void onCreate() {
		super.onCreate();
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		isrun = false;
	}

	@Override
	public void run() {
		// 线程没有停止的话，service是不会停止的，ondestroy中设为false，就可以停止
		while (isrun) {
			Log.e("service:", "run");
			Task lastTask = null;
			synchronized (allTask) {
				if (allTask.size() > 0) {
					lastTask = allTask.get(0);// 取任务
					doTask(lastTask);// 执行任务
				}
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}

	}

	// 更新UI
	private Handler hand = new Handler() {
		ICommonActivity ia;

		@Override
		public void handleMessage(Message msg) {
			// TODO
			super.handleMessage(msg);
			switch (msg.what) {
			case Task.TASK_Login:// 登入
				ia = (ICommonActivity) MainService
						.getActivityByName("LoginActivity");
				ia.refresh("TASK_Login", msg.obj);
				break;

			}
		}
	};

	// 执行任务
	public void doTask(Task task) {
		Message msg = hand.obtainMessage();
		msg.what = task.getTaskID();
		String result = "";
		String param = "";
		try {
			switch (task.getTaskID()) {
			case Task.TASK_Login:// 获取所有服务器分组
				// result = api.getAllGroup();
				// msg.obj = result;
				break;

			}
		} catch (Exception e) {
			msg.what = -100;
		}
		hand.sendMessage(msg);// 发送更新UI的消息给主线程
		allTask.remove(task);// 执行完任务移除列队
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	public static void exitApp(Context con) {
		for (Activity ac : allActivity) {
			ac.finish();
		}
		// 结束服务
		Intent it = new Intent(con, MainService.class);
		con.stopService(it);
	}

}
