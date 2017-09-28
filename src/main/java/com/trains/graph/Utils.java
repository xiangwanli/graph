/**
 * The Train Problems
 * @Title: com.trains.graph
 * @Description: Tools
 * @author: xiangwanli
 * @date 2017年4月10日
 * @version 1.0.0
 */
package com.trains.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @ClassName: Utils
 * @Description: Tools
 * @author xiangwanli
 * @date 2017年4月10日
 *
 */
public class Utils {

	/**
	 * 
	 * @Description: getStringFromStram: TODO
	 * @param in
	 * @return
	 * @throws IOException String
	 */
	public static String getStringFromStram(InputStream in) {
		InputStreamReader is = new InputStreamReader(in);
		StringBuilder sb = new StringBuilder();
		BufferedReader buffReader = new BufferedReader(is);
		String read;
		try {
			read = buffReader.readLine();
			while (read != null) {
				sb.append(read + "\n");
				read = buffReader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				buffReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return sb.toString();
	}
}
