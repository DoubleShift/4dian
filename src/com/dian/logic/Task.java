package com.dian.logic;

import java.util.Map;
/**
 * @author  chenx
 * @email   fjjwlzd@163.com
 * @version 2014-1-20 下午7:25:46
 */
public class Task {
	private int taskID;// 任务编号
	private Map taskParam;// 任务参数
	public static final int TASK_Login = 0;// 登入

	public Task(int id, Map param) {
		this.taskID = id;
		this.taskParam = param;
	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public Map getTaskParam() {
		return taskParam;
	}

	public void setTaskParam(Map taskParam) {
		this.taskParam = taskParam;
	}
}
