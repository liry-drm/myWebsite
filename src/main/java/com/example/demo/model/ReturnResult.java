package com.example.demo.model;

import com.example.demo.model.enums.ResultStatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 结果返回对象
 * @author lry
 *
 * @param <T>
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class ReturnResult extends BaseModel{

	private static final long serialVersionUID = -4459853194783840244L;	

	private Integer code;
	
	private String message;
	
	private Object data;
	
	public static final int SUCCESS = 1;
	
	public static final int FAILURE = 0;

	/**
	 * 判断返回码是否成功
	 * @return
	 */
	public boolean succeed(){
		return this.code == ReturnResult.SUCCESS;
	}

	public static ReturnResult ok() {
		return new ReturnResult(SUCCESS, "请求成功", null);
	}
	public static ReturnResult ok(String message) {
		return new ReturnResult(SUCCESS, message, null);
	}
	public static ReturnResult ok(Object data) {
		return new ReturnResult(SUCCESS, "请求成功", data);
	}
	public static ReturnResult ok(String message,Object data) {
		return new ReturnResult(SUCCESS, message, data);
	}
	public static ReturnResult err() {
		return new ReturnResult(FAILURE, "请求失败", null);
	}
	public static ReturnResult err(String message) {
		return new ReturnResult(FAILURE, message, null);
	}
	
	public ReturnResult(ResultStatusCode resultStatusCode){
        this(resultStatusCode.getCode(), resultStatusCode.getMsg(), null);
    }
	public ReturnResult(ResultStatusCode resultStatusCode, Object data){
		this(resultStatusCode.getCode(), resultStatusCode.getMsg(), data);
	}
}
