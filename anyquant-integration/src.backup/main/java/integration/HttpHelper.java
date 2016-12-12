package integration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HttpHelper {

	/**
	 * 向指定URL发�?�GET方法的请�?
	 * 
	 * @param url
	 *            发�?�请求的URL
	 * @param param
	 *            请求参数，请求参数应该是name1=value1&name2=value2的形�?
	 * @return URL�?代表远程资源的响�?
	 */

	public String sendGetSina(String url) {
		StringBuffer result = new StringBuffer();
		BufferedReader in = null;
		try {
			String urlName = url;
			URL realUrl = new URL(urlName);
			// 打开和URL之间的连�?
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属�?
			conn.setConnectTimeout(1000);
			// 建立实际的连�?
			conn.connect();
			// 定义BufferedReader输入流来读取URL的响�?
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			System.out.println("发�?�GET请求出现异常�?" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入�?
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result.toString();
	}
	
	public String sendGet(String url, String param) {
		StringBuffer result = new StringBuffer();
		BufferedReader in = null;
		try {
			String urlName = url + "?" + param;
			URL realUrl = new URL(urlName);
			// 打开和URL之间的连�?
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属�?
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setRequestProperty("X-Auth-Code", "f8297c9bd20b3cc05d1782e2e664da76");
			conn.setConnectTimeout(1000);
			// 建立实际的连�?
			conn.connect();
			// 定义BufferedReader输入流来读取URL的响�?
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			System.out.println("发�?�GET请求出现异常�?" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入�?
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result.toString();
	}
}
