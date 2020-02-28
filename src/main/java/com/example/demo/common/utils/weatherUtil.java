//package com.example.demo.common.utils;
//
//import org.apache.http.client.ResponseHandler;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.BasicResponseHandler;
//import org.apache.http.impl.client.DefaultHttpClient;
//
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//
//public class weatherUtil {
//
//	public static void main(String[] args) {
//
//		System.out.println(getWeatherInfo2());
//	}
//
//	public static String getWeatherInfo2() {
//		StringBuilder info = new StringBuilder();
//		try {
//			DefaultHttpClient httpclient = new DefaultHttpClient();
//			HttpGet httget = new HttpGet("http://m.weather.com.cn/data/101050101.html");
//			ResponseHandler<String> responseHandler = new BasicResponseHandler();
//			String responseBody = httpclient.execute(httget, responseHandler);
//			System.out.println(responseBody);
//			JsonParser jp = new JsonParser();
//			JsonElement jse = jp.parse(responseBody);
//			JsonObject jso = jse.getAsJsonObject().get("weatherinfo").getAsJsonObject();
//			// String updTime = jso.get("fchh").getAsString();
//			// if(updTime != null){
//			// //温度
//			// String j = jso.get("temp1").getAsString();//今天
//			// String m = jso.get("temp2").getAsString();//明天
//			// //天气情况
//			// String j_weather = jso.get("weather1").getAsString();//今天
//			// String m_weather = jso.get("weather2").getAsString();//明天
//			// //风向风力
//			// String j_wind = jso.get("wind1").getAsString();//今天
//			// String m_wind = jso.get("wind2").getAsString();//明天
//			// info.append("今天：").append(j).append(" ").append(j_weather).append("
//			// ").append(j_wind).append("\n");
//			// info.append("明天：").append(m).append(" ").append(m_weather).append("
//			// ").append(m_wind).append("\n");
//			// }
//			String updTime = jso.get("fchh").getAsString();
//			if (updTime != null) {
//				if (!updTime.trim().equals("18")) {
//					// 温度
//					String j = jso.get("temp1").getAsString();// 今天
//					String m = jso.get("temp2").getAsString();// 明天
//					// 天气情况
//					String j_weather = jso.get("weather1").getAsString();// 今天
//					String m_weather = jso.get("weather2").getAsString();// 明天
//					// 风向风力
//					String j_wind = jso.get("wind1").getAsString();// 今天
//					String m_wind = jso.get("wind2").getAsString();// 明天
//					info.append("今天：").append(j).append(" ").append(j_weather).append(" ").append(j_wind).append("\n");
//					info.append("明天：").append(m).append(" ").append(m_weather).append(" ").append(m_wind).append("\n");
//				} else {
//					// 18
//					// 温度
//					String temp1 = jso.get("temp1").getAsString();// 今天
//					String temp2 = jso.get("temp2").getAsString();// 今天
//					String temp3 = jso.get("temp3").getAsString();// 今天
//					String j = temp1.split("~")[1] + "~" + temp2.split("~")[0];
//					String m = temp2.split("~")[1] + "~" + temp3.split("~")[0];// 明天
//					// 天气情况
//					String weather1 = jso.get("weather1").getAsString();
//					String weather2 = jso.get("weather2").getAsString();
//					String weather3 = jso.get("weather3").getAsString();
//					String j_weather = "";
//					String j_weather_part1 = "";
//					String j_weather_part2 = "";
//					// 判断是否有转
//					if (weather1.indexOf("转") > 0) {
//						// 有
//						j_weather_part1 = weather1.split("转")[1];
//					} else {
//						j_weather_part1 = weather1;
//					}
//					if (weather2.indexOf("转") > 0) {
//						// 有
//						j_weather_part2 = weather2.split("转")[0];
//					} else {
//						j_weather_part2 = weather2;
//					}
//					if (j_weather_part1.equalsIgnoreCase(j_weather_part2)) {
//						j_weather = j_weather_part1;// 今天
//					} else {
//						j_weather = j_weather_part1 + "转" + j_weather_part2;// 今天
//					}
//					String m_weather = "";
//					String m_weather_part1 = "";
//					String m_weather_part2 = "";
//					// 判断是否有转
//					if (weather2.indexOf("转") > 0) {
//						// 有
//						m_weather_part1 = weather2.split("转")[1];
//					} else {
//						m_weather_part1 = weather2;
//					}
//					if (weather3.indexOf("转") > 0) {
//						// 有
//						m_weather_part2 = weather3.split("转")[0];
//					} else {
//						m_weather_part2 = weather3;
//					}
//					if (m_weather_part1.equalsIgnoreCase(m_weather_part2)) {
//						m_weather = m_weather_part1;// 今天
//					} else {
//						m_weather = m_weather_part1 + "转" + m_weather_part2;// 明天
//					}
//					// 风向风力
//					String j_wind = jso.get("wind2").getAsString();// 今天
//					String m_wind = jso.get("wind3").getAsString();// 明天
//					info.append("今天：").append(j).append(" ").append(j_weather).append(" ").append(j_wind).append("\n");
//					info.append("明天：").append(m).append(" ").append(m_weather).append(" ").append(m_wind).append("\n");
//				}
//			}
//		} catch (Exception e) {
//		}
//		return info.toString();
//	}
//}
