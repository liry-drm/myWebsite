package com.example.demo.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)

public class WeatherInfo extends BaseModel {

	private static final long serialVersionUID = 1L;

	private String cityName;// 城市名
	private String current_temperature;// 气温区间
	private String cold_condition;// 感冒情况
	private List<Weather> weatherList;// 天气

	WeatherInfo() {
		super();
	}

	public WeatherInfo(String cityName) {
		super();
		this.cityName = cityName;
	}

}