    var webSocket = null;
    function onMessage(event) {
		if(event.data=="l_i_r_u_y_i__say"){
			getOnline(document.getElementById('sid').value);
			return;
		}
		if(!document.getElementById('messages').innerHTML){
			document.getElementById('messages').innerHTML
				+= currentTime()+ event.data;
		}else{
			document.getElementById('messages').innerHTML
            += '<br />' + currentTime()+ event.data;
		}
    }
    function onOpen(event) {
		document.getElementById('status').innerHTML="在线";
		document.getElementById('status').style.color = 'green';
		document.getElementById('status').title=document.getElementById('status').text;
		$(".roomName").text("'" +document.getElementById('sid').value+"'" );
    }
    function onError(event) {
		webSocket = null;
		$(".infoTip").text("发生错误!");
		syalert.syopen('infoTip');
    }
    function onClose(event) {
		document.getElementById('status').innerHTML="离线";
		document.getElementById('status').style.color = 'red';
		document.getElementById('status').title=document.getElementById('status').text;
		webSocket = null;
		document.getElementById('messages').innerHTML
            += '<br />' + currentTime()+ document.getElementById('nickname').value+" 退出房间";
		//getOnline(document.getElementById('sid').value);
		$("#count").text("");
		$(".roomName").text(document.getElementById('sid').value);
		$("#users").html("");
    }
	
    function connect() {//进入房间
		if(webSocket !== null){
			$(".infoTip").text("已进入房间!");
			syalert.syopen('infoTip');
			return;
		}
        var sid = document.getElementById('sid').value;
        var nickname = document.getElementById('nickname').value;
        if (url == '' || nickname == '') {
            $(".infoTip").text("房间号和用户名不能为空!");
			syalert.syopen('infoTip');
            return;
        }
        var serverHot = 'localhost:8091';// window.location.host
        var url = 'ws://' + serverHot + '/websocket/groupChat/' + sid + '/' + nickname;
        webSocket = new WebSocket(url);
        webSocket.onerror = function(event) {
            onError(event);
        };
        webSocket.onopen = function(event) {
            onOpen(event);
        };
        webSocket.onmessage = function(event) {
            onMessage(event);
        };
        webSocket.onclose = function(event) {
            onClose(event);
        };
    }
    function disconnect() {//退出房间
		if(webSocket !== null){
			webSocket.close();
		}
	}
    function start() {//发送消息
        var text = document.getElementById('content').value;
		if(webSocket==null){
			$(".infoTip").text("请先进入聊天室!");
			syalert.syopen('infoTip');
			return;
		}
		if(!text){
			return;
		}
        webSocket.send(text);
        document.getElementById('content').value = '';
    } 
	function backMsg() {//撤回消息
        alert("撤回");
    }
	function getOnlineNumber(sid) {//
		$.ajax({
			type : "GET",
			url : "http://localhost:8091/info/count?sid="+sid,
			success : function(data) {
				$("#count").text(data);
			},
			error : function() {
				$(".infoTip").text("获取在线人数失败");
				syalert.syopen('infoTip');
			}
		});
	}
	function getOnline(sid) {//获取在线
		$.ajax({
			type : "GET",
			url : "http://localhost:8091/info/online?sid="+sid,
			success : function(data) {
				var temp = data.map(function(item,i,data){
				    return "<li>"+item+"</li>";
				},this);
				$("#count").text(temp.length);
				$("#users").html(temp.join(""));
			},
			error : function() {
				$(".infoTip").text("获取在线人员失败");
				syalert.syopen('infoTip');
			}
		});
	}
	function currentTime() {//获取在线
		let  date=new Date();
		return date.toLocaleString('chinese', { hour12: false })+"&emsp;"
	}
	function clearMsg(){
		document.getElementById('messages').innerHTML = '';
		syalert.syhide('clearMsg');
	}
	
	$( "#messages" ).contextmenu(function(e) {
		syalert.syopen('clearMsg');
		//e.stopPropagation(); //  阻止事件冒泡
	});
	// 禁用右键 
	document.oncontextmenu = new Function("return false");
	document.onselectstart = new Function("return false");

	//getOnline(document.getElementById('sid').value);