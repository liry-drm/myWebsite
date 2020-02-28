package com.example.demo.web.controller.infoData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.exception.ProjectException;
import com.example.demo.common.utils.AddressUtil;
import com.example.demo.common.utils.CommonUtils;
import com.example.demo.common.utils.Constants;
import com.example.demo.common.utils.StringUtil;
import com.example.demo.common.utils.en_decode.EscapeUnescape;
import com.example.demo.common.utils.net.HttpUtil;
import com.example.demo.common.utils.net.NetUtils;
import com.example.demo.model.ReturnResult;
import com.example.demo.web.controller.websocket.GroupChatController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RestController
@RequestMapping("info")
@SuppressWarnings("unchecked")
@CrossOrigin
public class InfoController {

	/**
	 * 获取每日一文
	 * 
	 * @param date 指定日期
	 * @return
	 * @throws ProjectException
	 */
	@GetMapping("article")
	public ReturnResult queryArticle(String date) throws ProjectException {
		ReturnResult res = new ReturnResult();
		String param;
		String responseStr;
		try {
			if (StringUtils.isNotBlank(date)) {
				param = "dev=1&date=" + date;
				responseStr = HttpUtil.sendSSLPost(Constants.MRYW_DAY_URL, param);
			} else {
				param = "dev=1";
				responseStr = HttpUtil.sendSSLPost(Constants.MRYW_TODAY_URL, param);
			}
			Map<String, Object> responseMap = JSON.parseObject(EscapeUnescape.unescape(responseStr), Map.class);
			responseMap = (Map<String, Object>) responseMap.get("data");
			responseMap.remove("date");
			responseMap.remove("digest");
			responseMap.put("wordCount", responseMap.get("wc"));
			responseMap.remove("wc");
			res.setCode(ReturnResult.SUCCESS);
			res.setData(responseMap);
		} catch (Exception e) {
			String message = "获取文章失败";
			res.setCode(ReturnResult.FAILURE);
			res.setMessage(message);
			log.error(message, e);
			throw new ProjectException(message);
		}
		return res;
	}

	/**
	 * 随机获取一文
	 * 
	 * @return
	 * @throws ProjectException
	 */
	@GetMapping("random_article")
	public ReturnResult queryRandomArticle() throws ProjectException {
		ReturnResult res = new ReturnResult();
		String responseStr;
		try {
			responseStr = HttpUtil.sendGet(Constants.MRYW_RANDOM_URL, null);
			Map<String, Object> parseRandom_article_html = parseRandom_article_html(responseStr);
			res.setCode(ReturnResult.SUCCESS);
			res.setData(parseRandom_article_html);
		} catch (Exception e) {
			String message = "获取文章失败";
			res.setCode(ReturnResult.FAILURE);
			res.setMessage(message);
			log.error(message, e);
			throw new ProjectException(message);
		}
		return res;
	}

    private Map<String,Object> parseRandom_article_html(String html) {
    	Map<String, Object> map = new HashMap<>();
    	String temp_title = html.substring(html.indexOf("title>")+6,html.lastIndexOf("</title>"));
    	String[] tempStr = temp_title.split(" ");
    	map.put("title", tempStr[0]);
    	map.put("author", tempStr[1]);
    	String content=html.substring(html.indexOf("class=\"article_text\">")+21).substring(0,html.substring(html.indexOf("class=\"article_text\">")+21).indexOf("</div>"));
    	map.put("content", content.trim());
    	map.put("wordCount", content.trim().length());
		return map;
    }
	
	private String message;

	/**
	 * 获取热映影片
	 * 
	 * @return
	 * @throws ProjectException
	 */
	@GetMapping("hotMoives")
	public ReturnResult getMoiveHot() throws ProjectException {
		ReturnResult res = new ReturnResult();
		try {
			res.setCode(ReturnResult.SUCCESS);
			res.setData(
					JSON.parseObject(HttpUtil.sendSSLPost(Constants.TIME_MOVIE_HOT_URL, "locationId=328"), Map.class));
		} catch (Exception e) {
			message = "获取热映影片信息失败";
			log.error(message, e);
			res.setCode(ReturnResult.FAILURE);
			res.setMessage(message);
			throw new ProjectException(message);
		}
		return res;
	}

	/**
	 * 获取即将上映影片
	 * 
	 * @return
	 * @throws ProjectException
	 */
	@GetMapping("comingMoives")
	public ReturnResult getMovieComing() throws ProjectException {
		ReturnResult res = new ReturnResult();
		try {
			res.setCode(ReturnResult.SUCCESS);
			res.setData(JSON.parseObject(HttpUtil.sendSSLPost(Constants.TIME_MOVIE_COMING_URL, "locationId=328"),
					Map.class));
		} catch (Exception e) {
			message = "获取即将上映影片信息失败";
			log.error(message, e);
			res.setCode(ReturnResult.FAILURE);
			res.setMessage(message);
			throw new ProjectException(message);
		}
		return res;
	}

	/**
	 * 获取影片详情
	 * 
	 * @param id 影片id
	 * @return
	 * @throws ProjectException
	 */
	@GetMapping("detail_moive")
	public ReturnResult getDetail(@NotBlank(message = "{required}") String id)
			throws ProjectException {
		ReturnResult res = new ReturnResult();
		try {
			res.setCode(ReturnResult.SUCCESS);
			Map<String, Object> data = JSON.parseObject(
					HttpUtil.sendSSLPost(Constants.TIME_MOVIE_DETAIL_URL, "locationId=328&movieId=" + id), Map.class);
			data.remove("msg");
			data.remove("code");
			res.setData(data);
		} catch (Exception e) {
			message = "获取影片详情失败";
			log.error(message, e);
			res.setCode(ReturnResult.FAILURE);
			res.setMessage(message);
			throw new ProjectException(message);
		}
		return res;
	}

	/**
	 * 获取电影的影评
	 * 
	 * @param id 电影id
	 * @return
	 * @throws ProjectException
	 */
	@GetMapping("comments_moive")
	public ReturnResult getComments(@NotBlank(message = "{required}") String id)
			throws ProjectException {
		ReturnResult res = new ReturnResult();
		try {
			Map<String, Object> data = JSON.parseObject(
					HttpUtil.sendSSLPost(Constants.TIME_MOVIE_COMMENTS_URL, "locationId=328&movieId=" + id), Map.class);
			data.remove("msg");
			data.remove("code");
			res.setData(data);
			res.setCode(ReturnResult.SUCCESS);
		} catch (Exception e) {
			message = "获取影片评论失败";
			log.error(message, e);
			res.setCode(ReturnResult.FAILURE);
			res.setMessage(message);
			throw new ProjectException(message);
		}
		return res;
	}

	/**
	 * 查询城市的天气情况相关信息
	 * 
	 * @param areaId 城市id
	 * @return
	 * @throws ProjectException
	 */
	@GetMapping("weather")
	public ReturnResult queryWeather(@NotBlank(message = "{required}") String areaId)
			throws ProjectException {
		ReturnResult res = new ReturnResult();
		try {
			Map<String, Object> data = JSON
					.parseObject(HttpUtil.sendPost(Constants.MEIZU_WEATHER_URL, "cityIds=" + areaId), Map.class);
			res.setData(data);
			res.setCode(ReturnResult.SUCCESS);
		} catch (Exception e) {
			String message = "天气查询失败";
			res.setCode(ReturnResult.FAILURE);
			res.setMessage(message);
			throw new ProjectException(message);
		}
		return res;
	}

	/**
	 * 获取每日一句
	 * 
	 * @param type
	 * @param date 指定日期
	 * @return
	 * @throws ProjectException
	 */
	@GetMapping("oneWord")
	public ReturnResult oneWord(String type, String date) throws ProjectException {
		ReturnResult res = new ReturnResult();
		Map<String, Object> data;
		try {
			if (!StringUtil.isEmpty(date)) {
				// String sendPost = HttpUtil.sendPost(Constants.ONE_WORD_EVERYDAY_URL1, "type="
				// + type);//根据类型返回，js、json、text
				data = JSON.parseObject(HttpUtil.sendGet(Constants.ONE_WORD_EVERYDAY_URL, "date=" + date), Map.class);
			} else {
				data = JSON.parseObject(HttpUtil.sendSSLPost(Constants.RONDOM_ONE_WORD_URL,null), Map.class);
			}
			res.setData(data);
			res.setCode(ReturnResult.SUCCESS);
		} catch (Exception e) {
			String message = "每日一句获取失败";
			res.setCode(ReturnResult.FAILURE);
			res.setMessage(message);
			throw new ProjectException(message);
		}
		return res;
	}

	/**
	 * 获取每日一图
	 * 
	 * @param num 张数
	 * @return
	 * @throws ProjectException
	 */
	@GetMapping(value = { "onePicture/{num}", "onePicture" })
	public ReturnResult onePic(@PathVariable(value = "num", required = false) Integer num)
			throws ProjectException {
		ReturnResult res = new ReturnResult();
		if (null == num) {
			num = 1;
		}
		try {
			Map<String, Object> data = JSON.parseObject(HttpUtil.sendGet(Constants.BING_ONE_PIC_URL + num), Map.class);
			data.remove("tooltips");
			List<Map<String, Object>> images = (List<Map<String, Object>>) data.get("images");
			data.put("imageCount", images.size());
			for (Map<String, Object> img : images) {
				String url = img.get("url").toString();
				String copyright = img.get("copyright").toString();
				img.clear();
				img.put("url", Constants.BING_URL + url);
				img.put("copyright", copyright);
			}
			res.setData(data);
			res.setCode(ReturnResult.SUCCESS);
		} catch (Exception e) {
			String message = "获取每日一图失败";
			res.setCode(ReturnResult.FAILURE);
			res.setMessage(message);
			throw new ProjectException(message);
		}
		return res;
	}

	/**
	 * 获取知乎日报
	 * 
	 * @return
	 * @throws ProjectException
	 */
	@GetMapping("day_news")
	public ReturnResult getNews() throws ProjectException {
		ReturnResult res = new ReturnResult();
		try {
			res.setCode(ReturnResult.SUCCESS);
			res.setData(JSON.parseObject(HttpUtil.sendGet(Constants.ZHIHU_NEWS_URL), Map.class));
		} catch (Exception e) {
			message = "获取知乎日报失败";
			log.error(message, e);
			res.setCode(ReturnResult.FAILURE);
			res.setMessage(message);
			throw new ProjectException(message);
		}
		return res;
	}

	/**
	 * 获取新闻详情
	 * 
	 * @param id 新闻id
	 * @return
	 * @throws ProjectException
	 */
	@GetMapping("news_detail")
	public ReturnResult getNewsDetail(@NotBlank(message = "{required}") String id) throws ProjectException {
		ReturnResult res = new ReturnResult();
		try {
			res.setCode(ReturnResult.SUCCESS);
			Map<String, Object> data = JSON.parseObject(HttpUtil.sendGet(Constants.ZHIHU_NEWS_DETAIL_URL + id),
					Map.class);
			res.setData(data);
		} catch (Exception e) {
			message = "获取影片详情失败";
			log.error(message, e);
			res.setCode(ReturnResult.FAILURE);
			res.setMessage(message);
			throw new ProjectException(message);
		}
		return res;
	}

	/**
	 * 获取指定日期前一天新闻
	 * 
	 * @param date 日期，如20191122
	 * @return
	 * @throws ProjectException
	 */
	@GetMapping("newsByDate")
	public ReturnResult getNewsByDate(@NotBlank(message = "{required}") String date) throws ProjectException {
		ReturnResult res = new ReturnResult();
		try {
			res.setCode(ReturnResult.SUCCESS);
			Map<String, Object> data = JSON.parseObject(HttpUtil.sendGet(Constants.ZHIHU_NEWS_BEFORE_URL + date),
					Map.class);
			res.setData(data);
		} catch (Exception e) {
			message = "获取指定日期前一天新闻失败";
			log.error(message, e);
			res.setCode(ReturnResult.FAILURE);
			res.setMessage(message);
			throw new ProjectException(message);
		}
		return res;
	}
	/**
	 * 获取指定日期前一天新闻
	 * 
	 * @param date 日期，如20191122
	 * @return
	 * @throws ProjectException
	 */
	@GetMapping("echartTestData")
	public String echartTestData() throws ProjectException {
		String data="{\n" + 
				"	\"list\": [{\n" + 
				"		\"value\": \"妈妈\",\n" + 
				"		\"name\":" + CommonUtils.getRandom(100, 150) +"\n"+
				"	},\n" + 
				"	{\n" + 
				"		\"value\": \"老婆\",\n" + 
				"		\"name\":"+CommonUtils.getRandom(80, 120) +"\n"+
				"	},\n" + 
				"	{\n" + 
				"		\"value\": \"情人\",\n" + 
				"		\"name\":"+ CommonUtils.getRandom(100, 140) +"\n"+
				"	}]\n" + 
				"}";

		return data;
	}
	@GetMapping("count")
	public String getOnlineCount() throws ProjectException {
		int onlineCount = GroupChatController.getOnlineCount("");
		return String.valueOf(onlineCount);
	}
	@GetMapping("online")
	public List<String> getOnline() throws ProjectException {
		return GroupChatController.getOnline("");
	}
	@GetMapping("city")
	public String getCityInfo(String ip,HttpServletRequest request) throws ProjectException {
		if(StringUtil.isEmpty(ip)) {
			ip = NetUtils.getIpAddr(request);
		}
		String info = AddressUtil.getCityInfo(ip);
		if(StringUtil.isEmpty(info)){
			info= "错误ip或未定位该IP";
		};
		return info;
	}
}














