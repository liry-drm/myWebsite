package com.example.demo.model;

import lombok.Data;

@Data
public class Weather {
	private String date;// 时间
	private String cityName;// 城市名
	private String weather;// 天气
	private String temperature;// 气温区间
	private String wind_power;// 风力
	private String wind_direction;// 风向

	Weather() {
		super();
	}

	public Weather(String cityName) {
		super();
		this.cityName = cityName;
	}

}