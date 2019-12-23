/**
 * copy right 2012 sctiyi all rights reserved
 * create time:下午03:08:21
 * author:ftd
 */
package com.example.demo.common.utils;

public class Constants {

	public static final String USER_SESSION_NAME = "userInfo";
	public static final String USER_SESSION_CLIENT = "userClient";
	public static final String USER_SESSION_MENU = "userMenu";
	public static final String USER_SESSION_FUNCTION = "userFunctions";
	public static final String CURRENT_MENU_ID = "__currentMenuId";
	public static final int IMAGE_RESIZE_WIDTH = 150;
	public static final int IMAGE_RESIZE_HEIGHT = 150;
	public static final int PAGE_SIZE = 10;

	/**
	 * api接口返回状�?公共码表 start
	 */
	public static final Integer API_RESULT_SUCCESS = 0;
	public static final Integer API_RESULT_FAILURE = 1;
	public static final Integer API_RESULT_TIMEOUT = 95;
	public static final Integer API_RESULT_SUBMIT_DUPLICATE = 96;
	public static final Integer API_RESULT_PARAMTER_ERROR = 97;
	public static final Integer API_RESULT_TOKEN_ERROR = 98;
	public static final Integer API_RESULT_ORTHER_ERROR = 99;
	/**
	 * api接口返回状�?公共码表 end
	 */

	/**
	 * 数据状态，启用、停用
	 */
	public static final Integer STATUS_ENABLE = 1;
	public static final Integer STATUS_DISABLE = 1;

	// 网络资源 Url
	/**
	 * 魅族天气接口
	 */
	public static final String MEIZU_WEATHER_URL = "http://aider.meizu.com/app/weather/listWeather";
	/**
	 * 每日一文接口
	 */
	public static final String MRYW_TODAY_URL = "https://interface.meiriyiwen.com/article/today";
	public static final String MRYW_DAY_URL = "https://interface.meiriyiwen.com/article/day";
	public static final String MRYW_RANDOM_URL = "https://meiriyiwen.com/random";

	/**
	 * 金山每日一句
	 */
	public static final String ONE_WORD_EVERYDAY_URL1 = "https://api.ooopn.com/ciba/api.php";
	public static final String ONE_WORD_EVERYDAY_URL = "http://open.iciba.com/dsapi/";//date=2019-11-21
	public static final String ONE_WORD_URL = "https://api.xygeng.cn/dailywd/api/id.php";//id=1
	public static final String RONDOM_ONE_WORD_URL = "https://api.xygeng.cn/dailywd/api";
	
	
	/**
	 * 必应网站url
	 */
	public static final String BING_URL = "http://s.cn.bing.net";
	
	/**
	 * 必应每日一图接口
	 */
	public static final String BING_ONE_PIC_URL = "https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=";
	
	/**
	 * mtime网api接口
	 */
	public static final String TIME_MOVIE_HOT_URL = "https://api-m.mtime.cn/Showtime/LocationMovies.api";
	public static final String TIME_MOVIE_DETAIL_URL = "https://ticket-api-m.mtime.cn/movie/detail.api";
	public static final String TIME_MOVIE_ACTORS_URL = "https://api-m.mtime.cn/Movie/MovieCreditsWithTypes.api";
	public static final String TIME_MOVIE_COMING_URL = "https://api-m.mtime.cn/Movie/MovieComingNew.api";
	public static final String TIME_MOVIE_COMMENTS_URL = "https://ticket-api-m.mtime.cn/movie/hotComment.api";
	
	/**
	 * 知乎日报api接口
	 * 
	 */
	public static final String ZHIHU_NEWS_URL = "http://news-at.zhihu.com/api/3/news/latest";
	public static final String ZHIHU_NEWS_DETAIL_URL = "http://news-at.zhihu.com/api/4/news/";  //拼接id,（9660723）获取详情
	public static final String ZHIHU_NEWS_BEFORE_URL = "http://news-at.zhihu.com/api/4/news/before/";  //拼接日期,（20191120）获取前一天往期新闻
	
	
	
	// 排序规则： descend 降序
	public static final String ORDER_DESC = "descend";
	// 排序规则： ascend 升序
	public static final String ORDER_ASC = "ascend";

	// 按钮
	public static final String TYPE_BUTTON = "1";
	// 菜单
	public static final String TYPE_MENU = "0";
}
