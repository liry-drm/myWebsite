
window['Home'] = {};
Home.imgs = new Array();
Home.currentInx = 0;
Home.getBankground = function() {
	$.ajax({
		type : "GET",
		url : "http://localhost:8091/info/onePicture/5",
		success : function(data) {
			if (data.code == 1) {
				Home.imgs = data.data.images;
				$("body").css("background", 'url(\"' + Home.imgs[Home.currentInx].url + '\")');
				$("._info").attr("title", Home.imgs[Home.currentInx].copyright);
				$("#nextPic").addClass("gray");
				$("#nextPic").attr("disabled", true).css("pointer-events", "none");
			} else if (data.code == 0) {
				alert(data.message);
			}
		},
		error : function() {
			alert("获取背景图失败！");
		}
	});
}
Home.oneWord = function() {
	$.ajax({
		type : "GET",
		url : "http://localhost:8091/info/oneWord",
		success : function(data) {
			if (data.code == 1) {
				$("h2").html(data.data.txt);
				/*if (data.data.txt.length > 42) {
					$("h2").removeClass("h2_title").addClass("h22_title");
				}*/
				$(".oneword h2").lettering();
				$(".wordauthor >span").html("—————" + data.data.author);
				for(var i=1;i<=data.data.txt.length;i=i+2){
					var r = Math.floor(Math.random()*256).toString(16);
					var g = Math.floor(Math.random()*256).toString(16);
					var b = Math.floor(Math.random()*256).toString(16);
					$('.char'+i).css('color','#'+r+g+b);
				}
			} else if (data.code == 0) {
				alert(data.message);
			}
		},
		error : function() {
			alert("获取每日一句失败！");
		}
	});
}
Home.picinfo = function(obj) {
	let url = "https://cn.bing.com/search?q=" + $(obj).attr("title").substring(0, $(obj).attr("title").indexOf("，")) + "&qs=n";
	let url2 = "https://www.baidu.com/s?wd=" + $(obj).attr("title").substring(0, $(obj).attr("title").indexOf("，"))
		+ "&rsv_spt=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_dl=ib&rsv_sug3=2&rsv_n=2";
	window.open(url2);
}
Home.switchPic = function(flg) {
	if (flg) {
		Home.currentInx++;
		$("#nextPic").removeClass("gray");
		$("#nextPic").removeAttr("disabled").css("pointer-events", "auto");
	} else {
		Home.currentInx--;
		$("#prePic").removeClass("gray");
		$("#prePic").removeAttr("disabled").css("pointer-events", "auto");
	}
	$("body").css("background", 'url(\"' + Home.imgs[Home.currentInx].url + '\")');
	$("._info").attr("title", Home.imgs[Home.currentInx].copyright);
	if (Home.currentInx == 4) {
		$("#prePic").addClass("gray");
		$("#prePic").attr("disabled", true).css("pointer-events", "none");
		return;
	}
	if (Home.currentInx == 0) {
		$("#nextPic").addClass("gray");
		$("#nextPic").attr("disabled", true).css("pointer-events", "none");
		return;
	}
}
Home.displayEchart=function(){
	$('#echartdom').toggle()
	if($('#echartdom').is(':hidden')){
		$('#echartbtn').removeClass("echartBtnOut").addClass("echartBtnBack");
		$('#echartbtn').attr("title","点击显示");
	}else{
		$('#echartbtn').removeClass("echartBtnBack").addClass("echartBtnOut");
		$('#echartbtn').attr("title","点击隐藏");
	}
}
Home.echart = function() {
	//基于准备好的dom，初始化echarts实例
	var cChart = echarts.init(document.getElementById('echartdom'));
	var names = []; //类别数组（用于存放饼图的类别）
	var brower = [];
	$.ajax({
		type : 'get',
		url : "http://localhost:8091/info/echartTestData",
		dataType : "json", //返回数据形式为json
		success : function(data) {
			//请求成功时执行该函数内容，result即为服务器返回的json对象
			$.each(data.list, function(index, item) {
				names.push(item.value); //挨个取出类别并填入类别数组 
				brower.push({
					name : item.value,
					value : item.name
				});
			});
			var option={ //加载数据图表            
				title : {
					text : '男人一生中女人的重要程度',
					left : 60,
					top : 0,
					textStyle : { //图例文字的样式
						color : '#3A7BD5',
						fontSize : 16
					},
					textAlign : 'left'
				},
				legend : {
					textStyle : { //图例文字的样式
						color : '#000',
						fontSize : 12
					},
					type : 'scroll',
					orient : 'vertical',
					left : 280,
					top : 32,
					bottom : 20,
					data : names
				},
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
				series : [ {
					name : '',
					type : 'pie',
					radius : '55%',
					center : [ '34%', '40%' ],
					data : brower,
					itemStyle : {
						emphasis : {
							shadowBlur : 10,
							shadowOffsetX : 0,
							shadowColor : 'rgba(0, 0, 0, 0.5)'
						},
						normal : {
							color : function(params) {
								//自定义颜色
								var colorList = [ '#2059be', '#198577',
									'#a8674e'
								];
								return colorList[params.dataIndex]
							}
						}
					}
				} ]
			};
			cChart.setOption(option);
		},
		error : function(errorMsg) {
			//请求失败时执行该函数
			alert("图表请求数据失败!");
		}
	});
}
Home.particle = function() {
	var canvas = document.querySelector("#canvas");
	var context = canvas.getContext('2d');
	var cw,
		ch;
	var stars = [];

	//当窗口大小改变时
	~~function setSize() {
		window.onresize = arguments.callee;
		cw = window.innerWidth;
		ch = window.innerHeight;
		canvas.height = ch;
		canvas.width = cw;
	}();

	function Star() {
	}
	;
	Star.prototype = {
		init : function() {
			this.w = rand(0, cw);
			this.h = rand(0, ch);
			this.r = 1.5;
			this.speedX = rand(-1, 1);
			this.speedY = rand(-1, 1);
		},
		draw : function() {
			context.fillStyle = 'white';
			context.beginPath();
			context.arc(this.w, this.h, this.r, 0, Math.PI * 2);
			context.fill();
		},
		move : function() {
			this.w += this.speedX;
			this.h += this.speedY;
			if (this.w < 0 || this.w > cw) {
				this.speedX *= -1;
			}
			if (this.h < 0 || this.h > ch) {
				this.speedY *= -1;
			}
			this.draw();
		}
	}

	function Line() {
	}
	;
	Line.prototype = {
		//星星之间的连线
		initStarLine : function() {
			this.colorStar = '#6699cc';
			this.colorStop = '#9966cc';
		},
		//鼠标与星星之间的连线
		initNewLine : function() {
			this.colorStar = '#6699cc';
			this.colorStop = '#ff6666';
		},
		drawLine : function(ow, oh, nw, nh) {
			var dx = ow - nw;
			var dy = oh - nh;
			var d = Math.sqrt(dx * dx + dy * dy);
			if (d < 60) {
				var line = context.createLinearGradient(ow, oh, nw, nh);
				context.beginPath();
				context.moveTo(ow, oh);
				context.lineTo(nw, nh);
				line.addColorStop(0, this.colorStar);
				line.addColorStop(1, this.colorStop);
				context.StrokeWidth = 1;
				context.strokeStyle = line;
				context.stroke();
				context.restore();
			}
		}
	}
	//生成范围在min~max之间的随机数
	function rand(min, max) {
		return Math.random() * (max - min) + min;
	}
	function create(num) {
		for (var i = 0; i < num; i++) {
			var star = new Star();
			star.init();
			star.draw();
			stars.push(star);
		}
	}
	create(400);
	setTimeout(function() {
		context.clearRect(0, 0, cw, ch);
		for (var i of stars) {
			i.move();
			for (var j = 0; j < stars.length / 2; j++) {
				var line = new Line();
				line.initStarLine();
				line.drawLine(i.w, i.h, stars[j].w, stars[j].h);
			} }
		setTimeout(arguments.callee, 1000 / 60);
	}, 1000 / 60);

	document.onmousemove = function(e) {
		var e = e || window.event;
		var mw = e.clientX;
		var mh = e.clientY;
		for (var i of stars) {
			var line = new Line();
			line.initNewLine();
			line.drawLine(i.w, i.h, mw, mh); }
	}
}
Home.uploadBg=function(dom) {
/*		var typelist = [ 'xbm', 'bmp', 'webp', 'jpeg', 'svgz', 'gif', 'jpg', 'ico', 'tiff', 'png',
			'svg', 'jfif', 'pipeg', 'pip', 'tif' ];
		if (($(dom).val() && ($.inArray($(dom).val().substring(
				$(dom).val().lastIndexOf('.') + 1).toLowerCase(), typelist) < 0))
			|| ($("#userimg").val() && ($.inArray($("#userimg").val().substring(
				$("#userimg").val().lastIndexOf('.') + 1).toLowerCase(), typelist) < 0))) {
			Msg.fail("文件格式错误，请选择正确文件", 3);
			if (dom.outerHTML) {
				dom.outerHTML = dom.outerHTML;
			} else {
				dom.value = "";
			}
			$("#userimg").val("");
			$("#pic").attr("src", "");
			$("#pic").hide();
			return;
		}*/
		var url = null; //用于预览的临时图片路径
		//判断当前浏览器版本
		var browserCfg = {};
		var ua = window.navigator.userAgent;
		if (ua.indexOf("MSIE") >= 1) { // IE >6
			browserCfg.ie = true;
			url = dom.value;
		} else if (ua.indexOf("Firefox") >= 1) { // Firefox 
			browserCfg.firefox = true;
			url = window.URL.createObjectURL(dom.files[0]);
		} else if (ua.indexOf("Chrome") >= 1) { // Chrome 
			browserCfg.chrome = true;
			url = window.URL.createObjectURL(dom.files[0]);
		}
/*		var filesize; //文件大小
		if (browserCfg.firefox || browserCfg.chrome) {
			filesize = dom.files[0].size;
		} else if (browserCfg.ie) {
			$("#pic").attr("src", url);
			var obj_img = document.getElementById('pic');
			filesize = obj_img.fileSize;
		}
		if (filesize >= 200 * 1024) { //图片大小不超过200k
			Msg.fail("上传的图片不能超过200k！", 3);
			if (dom.outerHTML) {
				dom.outerHTML = dom.outerHTML;
			} else {
				dom.value = "";
			}
			$("#userimg").val("");
			$("#pic").attr("src", "");
			$("#pic").hide();
			return;
		}
		if (url) { //预览显示
			$("#pic").attr("src", url);
			$("#pic").show();
		}*/
		if (url) { //预览显示
			$("body").css("background", 'url(\"' + url + '\")');
		}
	}
$(function() {
	Home.getBankground();
	Home.oneWord();
	Home.particle();
	Home.echart();
	$("#bgimg").change(function() {
		 var url = null;
		 if (window.createObjectURL!=undefined) {
		  	url = window.createObjectURL(this.files[0]) ;
		 } else if (window.URL!=undefined) { // mozilla(firefox)
		  	url = window.URL.createObjectURL(this.files[0]) ;
		 } else if (window.webkitURL!=undefined) { // webkit or chrome
		  	url = window.webkitURL.createObjectURL(this.files[0]) ;
		 }
		 if (url) {
		  $("#bg2").attr("src", url);
		  $("#bg2")[0].style.display='block';
 		  $("#removeImg2")[0].style.display='block';
		}
	});
})