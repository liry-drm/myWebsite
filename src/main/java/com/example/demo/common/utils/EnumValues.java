package com.example.demo.common.utils;

public class EnumValues {

	public enum SystemLogTypes {

		logout(0,"退出"),
		login(1,"登录"),
		add(2,"新增"),
		update(3,"修改"),
		delete(4,"删除"),
		startservice(5,"开启服务"),
		stopservice(6,"关闭服务"),
		opfailure(7,"操作失败"),
		opexception(8,"操作异常");
		
		private int typeId;
		private String typeName;
		private String selected;
		private SystemLogTypes(int logtypeId,String logTypeName){
			this.typeId = logtypeId;
			this.typeName = logTypeName;
		}
		
		public int getTypeId() {
			return typeId;
		}
		public void setTypeId(int typeId) {
			this.typeId = typeId;
		}
		public String getTypeName() {
			return typeName;
		}
		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		public String getSelected() {
			return selected;
		}

		public void setSelected(String selected) {
			this.selected = selected;
		}
		
		//通过typeId获取对应的枚举对象
		public static String getSystemLogTypesByTypeId(String typeId) {
			for (SystemLogTypes systemLogType : SystemLogTypes.values()) {
				if (typeId.equals(systemLogType.getTypeId())) {
					return systemLogType.getTypeName();
				}
			}
			return null;
		}
		
		
	}
	
	public enum UserQuestions {

		farther("1","我父亲的姓名?"),mother("2","我母亲的姓名?"),smallschool("3","我的小学校名?"),colleage("4","我的大学校名?"),lover("5","我最爱的人?"),bornaddress("6","我的出生地?");
		
		private String typeId;
		private String typeName;
		private String selected;
		private UserQuestions(String typeId,String typeName){
			this.typeId = typeId;
			this.typeName = typeName;
		}
		public String getTypeId() {
			return typeId;
		}

		public void setTypeId(String typeId) {
			this.typeId = typeId;
		}

		public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		public String getSelected() {
			return selected;
		}
		public void setSelected(String selected) {
			this.selected = selected;
		}
		
		//通过typeId获取对应的枚举对象
		public static String getUserQuestionByTypeId(String typeId) {
			for (UserQuestions userQuestion : UserQuestions.values()) {
				if (typeId.equals(userQuestion.getTypeId())) {
					return userQuestion.getTypeName();
				}
			}
			return null;
		}
		
	}
	
	public enum WeixinMenuTypes {

		view("1","view菜单"),click("2","点击事件"),media("3","永久素材");
		
		private String typeId;
		private String typeName;
		private String selected;
		private WeixinMenuTypes(String typeId,String typeName){
			this.typeId = typeId;
			this.typeName = typeName;
		}
		public String getTypeId() {
			return typeId;
		}
		public void setTypeId(String typeId) {
			this.typeId = typeId;
		}
		public String getTypeName() {
			return typeName;
		}
		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}
		public String getSelected() {
			return selected;
		}
		public void setSelected(String selected) {
			this.selected = selected;
		}
		
		//通过typeId获取对应的枚举对象
		public static String getWeixinMenuTypeByTypeId(String typeId) {
			for (WeixinMenuTypes weixinMenuType : WeixinMenuTypes.values()) {
				if (typeId.equals(weixinMenuType.getTypeId())) {
					return weixinMenuType.getTypeName();
				}
			}
			return null;
		}

	}
	
	public enum WeixinKeywordMsgTypes {

		text("1","文本消息"),media("2","永久素材");
		
		private String typeId;
		private String typeName;
		private String selected;
		private WeixinKeywordMsgTypes(String typeId,String typeName){
			this.typeId = typeId;
			this.typeName = typeName;
		}
		public String getTypeId() {
			return typeId;
		}
		public void setTypeId(String typeId) {
			this.typeId = typeId;
		}
		public String getTypeName() {
			return typeName;
		}
		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}
		public String getSelected() {
			return selected;
		}
		public void setSelected(String selected) {
			this.selected = selected;
		}
		
		//通过typeId获取对应的枚举对象
		public static String getWeixinKeywordMsgTypeByTypeId(String typeId) {
			for (WeixinKeywordMsgTypes weixinKeywordMsgType : WeixinKeywordMsgTypes.values()) {
				if (typeId.equals(weixinKeywordMsgType.getTypeId())) {
					return weixinKeywordMsgType.getTypeName();
				}
			}
			return null;
		}

	}
	
	public enum PayPlats {

		weixinpay("weixinpay","微信支付平台"),alipay("alipay","支付宝支付平台");
		
		private String typeId;
		private String typeName;
		private String selected;
		private PayPlats(String typeId,String typeName){
			this.typeId = typeId;
			this.typeName = typeName;
		}
		public String getTypeId() {
			return typeId;
		}
		public void setTypeId(String typeId) {
			this.typeId = typeId;
		}
		public String getTypeName() {
			return typeName;
		}
		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}
		public String getSelected() {
			return selected;
		}
		public void setSelected(String selected) {
			this.selected = selected;
		}
		
		//通过typeId获取对应的枚举对象
		public static String getPayPlatByTypeId(String typeId) {
			for (PayPlats payPlat : PayPlats.values()) {
				if (typeId.equals(payPlat.getTypeId())) {
					return payPlat.getTypeName();
				}
			}
			return null;
		}

	}
	
	public enum PayTypes {

		weixinmppay("weixinMp","微信公众号支付"),weixinh5pay("weixinH5","微信H5支付"),weixinminipropay("weixinMini","微信小程序支付"),weixinpcpay("weixinPC","微信PC支付"),alih5pay("alih5pay","支付宝H5支付"),alipcpay("alipcpay","支付宝PC支付");
		
		private String typeId;
		private String typeName;
		private String selected;
		private PayTypes(String typeId,String typeName){
			this.typeId = typeId;
			this.typeName = typeName;
		}
		public String getTypeId() {
			return typeId;
		}
		public void setTypeId(String typeId) {
			this.typeId = typeId;
		}
		public String getTypeName() {
			return typeName;
		}
		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}
		public String getSelected() {
			return selected;
		}
		public void setSelected(String selected) {
			this.selected = selected;
		}
		
		//通过typeId获取对应的枚举对象
		public static String getPayTypeNameByTypeId(String typeId) {
			for (PayTypes payType : PayTypes.values()) {
				if (typeId.equals(payType.getTypeId())) {
					return payType.getTypeName();
				}
			}
			return null;
		}

	}
	
	public enum PayStatus {

		waitpay(1,"待支付"),paying(2,"已支付"),paysuccess(3,"支付成功"),paycancel(4,"取消支付"),payerror(5,"支付失败"),payclose(6,"关闭支付"),payrefund(7,"已退款");
		
		private Integer typeId;
		private String typeName;
		private String selected;
		private PayStatus(Integer typeId,String typeName){
			this.typeId = typeId;
			this.typeName = typeName;
		}
		public Integer getTypeId() {
			return typeId;
		}
		public void setTypeId(Integer typeId) {
			this.typeId = typeId;
		}
		public String getTypeName() {
			return typeName;
		}
		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}
		public String getSelected() {
			return selected;
		}
		public void setSelected(String selected) {
			this.selected = selected;
		}
		
		//通过typeId获取对应的枚举对象
		public static String getPayStatusByTypeId(Integer typeId) {
			for (PayStatus payStatus : PayStatus.values()) {
				if (typeId.equals(payStatus.getTypeId())) {
					return payStatus.getTypeName();
				}
			}
			return null;
		}

	}

}
