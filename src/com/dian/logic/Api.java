package com.dian.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dian.util.Http;
import com.me.bean.GetUpList;

import android.util.Log;

/**
 * @author chenx
 * @email fjjwlzd@163.com
 * @version 2014-1-20 下午7:25:46
 */

public class Api {

	private String baseurl = "http://4dian.sinaapp.com";
	private String api_getup = "/api/getup";// get 当月list,值为空的话签到一次
	private String api_info = "/api/settings"; // get 用户信息，post 设置用户信息
	private String api_timeManage = "/api/timeassignment";// get 用户时间分配，post
															// 设置当天时间分配结果

	private String api_login = "api/login/"; // 登入验证
	private String api_reg = "api/reg/";// 注册
	private String api_pass = "api/pass/"; // 修改密码
	private String api_challenge = "api/challenge/";// get 得到挑战信息， post 设置挑战信息

	/**
	 * 注册登录模块 
	 * POST /api/signup/email/xxx@xxx.xxx/password/xxxxxx 用户注册接口 
	 * POST /api/login/email/xxx@xxx.xxx/password/xxxxxx 用户登录接口
	 * 
	 * 用户设置模块 
	 * GET /api/settings/uid.json 读取用户设置接口 
	 * POST /api/settings/uid.json写入用户设置接口
	 * 
	 * 早起模块 
	 * GET /api/getup/yyyymm/uid 读取用户yyyymm的起床记录 
	 * POST/api/get1up/uid写入用户当前(SAE服务器时间)的起床时间
	 * 
	 * 时间分配模块
	 * GET  /api/timeassignment/yyyymmdd/uid.json 读取用户yyyymmdd的时间分配记录
	 *  POST /api/timeassignment/yyyymmdd/uid.json写入用户yyyymmdd的时间分配记录
	 */
	public Api() {
		// TODO Auto-generated constructor stub
		super();
	}  
	/*
	 * http://4dian.sinaapp.com/api/getup/201303/1 baseurl+api+y，m，uid
	 */

	public GetUpList GetUpDate(int id ,int year, int month) {


		String month2 ;

		// 给数字加0
		if (month < 10) {
			month2 = "0" + month;
		} else {
			month2 = "" +month;
		}

		String url = baseurl + api_getup + "/" + year + month2 + "/" + id;
		
		String str = Http.get(url);

		// String str = get("http://10.0.2.2/1.txt");//测试地址
		
		GetUpList gplist = null;
		try {
			HashMap<Integer, String> getup_list = new HashMap<Integer, String>();

			JSONObject jsonObject = new JSONObject(str);

			int jmonth = jsonObject.getInt("month");
			int jyear = jsonObject.getInt("year");
			int juid = jsonObject.getInt("uid");

			JSONArray jsonArray = jsonObject.getJSONArray("getup_list");// 一个月的列表
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject item = jsonArray.getJSONObject(i); // 单次记录

				getup_list.put(
						item.getInt("gp_day"),
						item.getString("gp_time"));

			}
			Log.v("ApiGet", "uid:" + juid + "year:" + jyear + "month:" + jmonth);

			 gplist = new GetUpList(juid,jyear,jmonth,getup_list);
		} catch (JSONException e) {

			e.printStackTrace();
		}
		return gplist;

	}

}
