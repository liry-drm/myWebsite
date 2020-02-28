package com.example.demo.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class getPagesPic {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		for (int i = 2; i < 5; i++) {
			//crawl("https://j.yaseh8.com/pic/view/15" + genRandomNum(i));
		}
	}

	public static int crawl(String uri) throws IOException, ClientProtocolException {
		String dir = uri.substring(uri.lastIndexOf("/") + 1);
		// 创建httpclient实例
		CloseableHttpClient httpclient = HttpClients.createDefault();
		URL tempurl = new URL(uri);// 6692
		// 得到一个文档对象
		Document doc = Jsoup.parse(tempurl, 1000000);
		// 获取指定的 <img />
		Elements elements = doc.getElementsByTag("img");
		if (elements.size() < 1) {
			return 0;
		}
		String title = doc.title();
		int count = 0;
		for (Element element : elements) {
			// 获取 <img /> 的 src
			String url = element.attr("src");// data-original
			if ("".equals(url)) {
				continue;
			}
			if (url.contains("comic-thumb.png")) {
				url = element.attr("data-original");
			}
			if (url.contains(".png")) {
				continue;
			}
			if (url.contains("tuijian.gif")) {
				continue;
			}
			System.out.println("*********************************图片路径:" + url);
			// 再发请求最简单了，并由于该链接是没有 https:开头的，得人工补全 ?
			HttpGet PicturehttpGet = new HttpGet(url);
			CloseableHttpResponse pictureResponse = httpclient.execute(PicturehttpGet);
			HttpEntity pictureEntity = pictureResponse.getEntity();
			InputStream inputStream = pictureEntity.getContent();
			File file = new File("D:\\work\\crawl\\picture\\" + dir);
			if (!file.exists()) {
				file.mkdirs();
			}
			// 使用 common-io 下载图片到本地，注意图片名不能重复 ?
			FileUtils.copyToFile(inputStream,
					new File("D:\\work\\crawl\\picture\\" + dir + "\\" + genRandomNum(8) + ".jpg"));
			count++;
			pictureResponse.close(); // pictureResponse关闭
		}
		httpclient.close(); // httpClient关闭
		System.err.println("******************" + dir + "_" + title + "共下载" + count + "张图****************************");
		return count;
	}

	public static String genRandomNum(int pwd_len) {
		String[] beforeShuffle = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		List<String> list = Arrays.asList(beforeShuffle);
		Collections.shuffle(list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
		}
		String afterShuffle = sb.toString();
		String result = afterShuffle.substring(0, pwd_len);
		return result;
	}
}