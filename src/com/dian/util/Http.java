package com.dian.util;

import java.io.IOException;
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

import android.util.Log;

/**
 * @author chenx
 * @email fjjwlzd@163.com
 * @version 2014-1-20 下午7:33:25
 */
public class Http {

	private HttpParams httpParams; // post 的参数
	private HttpClient httpClient;// 共有的

	public Http() {
		super();
		getHttpClient();

	}

	public HttpClient getHttpClient() {

		// 创建 HttpParams 以用来设置 HTTP 参数（这一部分不是必需的）

		this.httpParams = new BasicHttpParams();

		// 设置连接超时和 Socket 超时，以及 Socket 缓存大小

		HttpConnectionParams.setConnectionTimeout(httpParams, 20 * 1000);

		HttpConnectionParams.setSoTimeout(httpParams, 20 * 1000);

		HttpConnectionParams.setSocketBufferSize(httpParams, 8192);

		// 设置重定向，缺省为 true

		HttpClientParams.setRedirecting(httpParams, true);

		// 设置 user agent

		String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2) Gecko/20100115 Firefox/3.6";
		HttpProtocolParams.setUserAgent(httpParams, userAgent);

		// 创建一个 HttpClient 实例

		// 注意 HttpClient httpClient = new HttpClient(); 是Commons HttpClient

		// 中的用法，在 Android 1.5 中我们需要使用 Apache 的缺省实现 DefaultHttpClient

		httpClient = new DefaultHttpClient(httpParams);

		return httpClient;
	}

	public static String get(String url) {
		String str = "";

		HttpGet request = new HttpGet(url);
		HttpClient httpClient = new DefaultHttpClient();
		try {
			
				Log.v("HttpGet", url);
			HttpResponse response = httpClient.execute(request);

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				str = EntityUtils.toString(response.getEntity());
				Log.v("HttpGet", str);

			} else {
				Log.v("HttpGet", "请求失败");

			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;

	}

	public String Post(String url, List<NameValuePair> params) {

		/* 建立HTTPPost对象 */
		HttpPost httpRequest = new HttpPost(url);

		String strResult = "doPostError";

		try {
			/* 添加请求参数到请求对象 */
			httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			/* 发送请求并等待响应 */
			HttpResponse httpResponse = httpClient.execute(httpRequest);
			/* 若状态码为200 ok */
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				/* 读返回数据 */
				strResult = EntityUtils.toString(httpResponse.getEntity());

			} else {
				strResult = "Error Response: "
						+ httpResponse.getStatusLine().toString();
			}
		} catch (ClientProtocolException e) {
			strResult = e.getMessage().toString();
			e.printStackTrace();
		} catch (IOException e) {
			strResult = e.getMessage().toString();
			e.printStackTrace();
		} catch (Exception e) {
			strResult = e.getMessage().toString();
			e.printStackTrace();
		}

		Log.v("strResult", strResult);

		return strResult;
	}

}
