package com.example.demo.web.controller.websocket;

import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

@Component
@ServerEndpoint("/websocket/{sid}")
public class WebSocketServerController {

	// 收到消息调用的方法
	@OnMessage
	public void onMessage(Session session, String message,@PathParam("sid") String sid) {
		try {
			System.out.println("收到消息了"+message);
			session.getBasicRemote().sendText("服务端发给你的");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 建立连接调用的方法
	@OnOpen
	public void onOpen(@PathParam("sid") String sid) {
		System.out.println("Client connected");
	}

	// 关闭连接调用的方法
	@OnClose
	public void onClose(@PathParam("sid") String sid) {
		System.out.println("Connection closed");
	}

	// 传输消息错误调用的方法
	@OnError
	public void OnError(Throwable error,@PathParam("sid") String sid) {
		System.out.println("Connection error");
	}
}
