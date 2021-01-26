package com.example.demo.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.zip.GZIPInputStream;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.Weather;
import com.example.demo.model.WeatherInfo;

/**
 * 
 * 获取天气预报工具类。通过get请求向网站http://wthrcdn.etouch.cn/weather_mini获取某个
 * 城市的天气状况数据，数据格式是Json
 * 
 * @author liruyi
 * 
 */
public class WeatherUtils {

	public static void main(String[] args) {
		System.out.println(GetWeatherDataByCityName("成都").toJson());
	}

	public static WeatherInfo GetWeatherDataByCityName(String cityName) {
		WeatherInfo weatherInfo = new WeatherInfo(cityName);
		GetWeather(WeatherUtils.GetWeatherData(cityName), weatherInfo);
		return weatherInfo;
	}

	/**
	 * 通过城市名称获取该城市的天气信息
	 * 
	 * @param cityName
	 * @return
	 */

	public static String GetWeatherData(String cityname) {
		StringBuilder sb = new StringBuilder();
		try {
			// cityname = URLEncoder.encode(cityName, "UTF-8");
			String weather_url = "http://wthrcdn.etouch.cn/weather_mini?city=" + cityname;

			URL url = new URL(weather_url);
			URLConnection conn = url.openConnection();
			InputStream is = conn.getInputStream();
			GZIPInputStream gzin = new GZIPInputStream(is);
			InputStreamReader isr = new InputStreamReader(gzin, "utf-8"); // 设置读取流的编码格式，自定义编码
			BufferedReader reader = new BufferedReader(isr);
			String line = null;
			while ((line = reader.readLine()) != null) {
                sb.append(line + " ");
            }
			reader.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 将JSON格式天气数据进行解析 ，返回该城市的天气预报
	 * 
	 * @param weatherInfobyJson JSON格式天气数据
	 * @param weatherInfo
	 * @param info
	 * @return
	 */
	public static void GetWeather(String weatherInfobyJson, WeatherInfo weatherInfo) {
		// 响应json
		JSONObject data = JSONObject.parseObject(weatherInfobyJson);
		if (data.getInteger("status") != 1000) // 未获取
        {
            return;
        }

		LinkedList<Weather> list = new LinkedList<>();
		// 所有数据
		data = data.getJSONObject("data");
		// 城市名
		String cityName = data.getString("city");
		// 昨天天气
//		JSONObject yesterday = data.getJSONObject("yesterday");
		// 获取预测的天气预报信息(今天及后续4天,共5天)
		JSONArray forecast = data.getJSONArray("forecast");
		for (Object object : forecast) {
			list.add(obj2Weather(object, cityName));
		}
		weatherInfo.setCold_condition(data.getString("ganmao"));
		weatherInfo.setCurrent_temperature(data.getString("wendu") + "℃");
		weatherInfo.setWeatherList(list);
		// list.forEach((weather) -> System.out.println(weather));
	}

	/**
	 * JSONO型天气数据转WeatherInfo对象
	 * 
	 * @param weatherObj
	 * @param cityName   城市名
	 * @return
	 */
	public static Weather obj2Weather(Object weatherObj, String cityName) {
		JSONObject weatherMap = (JSONObject) weatherObj;
		Weather weatherInfo = new Weather(cityName);
		weatherInfo.setDate(weatherMap.getString("date"));
		weatherInfo.setWeather(weatherMap.getString("type"));
		weatherInfo.setTemperature(
				weatherMap.getString("low").substring(2) + "~" + weatherMap.getString("high").substring(2));
		weatherInfo.setWind_direction(weatherMap.getString("fengxiang"));
		String fengli = weatherMap.getString("fengli");
		// StringUtils.ordinalIndexOf(fengli, "[", 2) //获取指定字符第几次出现的索引
		weatherInfo.setWind_power(fengli.substring(fengli.lastIndexOf("[") + 1, fengli.indexOf("]")));
		weatherInfo.setWind_direction(weatherMap.getString("fengxiang"));
		return weatherInfo;
	}
}
