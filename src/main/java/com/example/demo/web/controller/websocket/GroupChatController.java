package com.example.demo.web.controller.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.example.demo.common.utils.StringUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ServerEndpoint("/websocket/groupChat/{sid}/{username}")
public class GroupChatController {

	// 组id-->组成员list（成员名称-->成员session）
	private static ConcurrentHashMap<String, List<Map<String, Session>>> groupMemberInfoMap = new ConcurrentHashMap<>();

	// 收到消息调用的方法，群成员发送消息
	@OnMessage
	public void onMessage(@PathParam("sid") String sid, @PathParam("username") String username, String message) {
		// 先一个群组内的成员发送消息
		String text = username + ": " + message;
		try {
			sendMessageAll(sid, text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 建立连接调用的方法，群成员加入
	@OnOpen
	public void onOpen(Session session, @PathParam("sid") String sid, @PathParam("username") String username) {
		List<Map<String, Session>> sessionList = groupMemberInfoMap.get(sid);
		if (sessionList == null) {
			sessionList = new ArrayList<>();
			groupMemberInfoMap.put(sid, sessionList);
		}
		Map<String, Session> map = new HashMap<>();
		map.put(username, session);
		sessionList.add(map);
		try {
			sendMessageAll(sid, username + "	进入房间");
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("Connection connected");
		log.info("用户名：{},进入的房间: {}, 房间内在线人数: {}", username, sid, sessionList.size());
		try {
			sendMessageAll(sid, "l_i_r_u_y_i__say");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 关闭连接调用的方法，群成员退出
	@OnClose
	public void onClose(Session session, @PathParam("sid") String sid, @PathParam("username") String username) {
		List<Map<String, Session>> sessionList = groupMemberInfoMap.get(sid);
		Iterator<Map<String, Session>> it = sessionList.iterator();
		while (it.hasNext()) {
			Map<String,Session> map = (Map<String,Session>) it.next();
			if (map.get(username) != null) {
				it.remove();
			}
		}
		try {
			sendMessageAll(sid, username + "	退出房间");
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("Connection closed");
		log.info("用户名：{},退出的房间: {}, 房间内在线人数: {}", username, sid, sessionList.size());
		if(sessionList.size()<1) {
			groupMemberInfoMap.remove(sid);
		}
		try {
			sendMessageAll(sid, "l_i_r_u_y_i__say");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 传输消息错误调用的方法
	@OnError
	public void OnError(Throwable error) {
		log.info("Connection error");
		error.printStackTrace();
	}

	/**
	 * 指定发送消息
	 * 
	 * @param message 消息
	 * @param to      目标名称
	 * @throws IOException
	 */
	public void sendMessageTo(String message, String to) throws IOException {
		for (List<Map<String, Session>> sessionList : groupMemberInfoMap.values()) {
			for (Map<String, Session> map : sessionList) {
				Session session = (Session) map.get(to);
				synchronized(session) {
					session.getAsyncRemote().sendText(message);
				}
				break;
			}
		}
	}

	/**
	 * 服务端推送消息至指定房间
	 * 
	 * @param sid     房间号 空则发送至所有房间
	 * @param message 发送的消息
	 * @throws IOException
	 */
	public void sendMessageAll(String sid, String message) throws IOException {
		List<Map<String, Session>> sessions = new ArrayList<>();// 会话list
		if (!StringUtil.isEmpty(sid)) {
			sessions = groupMemberInfoMap.get(sid);
		} else {
			Collection<List<Map<String, Session>>> values = groupMemberInfoMap.values();
			for (List<Map<String, Session>> list : values) {
				sessions.addAll(list);
			}
		}
		if(CollectionUtils.isEmpty(sessions)) {
			return;
		}
		for (Map<String, Session> map : sessions) {
			Collection<Session> values2 = map.values();
			for (Session session : values2) {
				synchronized(session) {
					session.getAsyncRemote().sendText(message);
				}
			}
		}
		// 发送消息
		// sessions.forEach(item -> {});
	}

	/**
	 * 获取在线人数
	 * 
	 * @param sid 房间号 空则查询所有房间在线人数
	 * @return
	 */
	public static synchronized int getOnlineCount(String sid) {
		int onlineCount = 0;
		KeySetView<String, List<Map<String, Session>>> keySet = groupMemberInfoMap.keySet();
		if (StringUtil.isEmpty(sid)) {
			for (String key : keySet) {
				List<Map<String, Session>> sessionList = groupMemberInfoMap.get(key);
				onlineCount += sessionList.size();
			}
		} else {
			List<Map<String, Session>> sessionList = groupMemberInfoMap.get(sid);
			onlineCount = sessionList.size();
		}
		return onlineCount;
	}
	
	public static synchronized List<String> getOnline(String sid) {
		List<String> resList=new ArrayList<>();
		KeySetView<String, List<Map<String, Session>>> keySet = groupMemberInfoMap.keySet();
		if (StringUtil.isEmpty(sid)) {
			for (String key : keySet) {
				List<Map<String, Session>> sessionList = groupMemberInfoMap.get(key);
				sessionList.forEach(item -> {
					resList.addAll(new ArrayList<>(item.keySet()));
				});
			}
		} else {
			List<Map<String, Session>> sessionList = groupMemberInfoMap.get(sid);
			sessionList.forEach(item -> {
				resList.addAll(new ArrayList<>(item.keySet()));
			});
		}
		return resList;
	}
}
