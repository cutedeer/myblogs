package com.sushuzhuang.myblogs.utils;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * @author su
 * @version v1.0
 *          <p>返回结果类</p>
 */

public class Result {
    /**
     * 返回值
     */
    private int returnCode = 200;
    /**
     * 提示信息
     */
    private String returnMsg = "操作成功";
    /**
     * 返回数据
     */
    private Object returnData = new HashMap<String,Object>();

    /**
     * 构造方法
     */
    public Result(Object returnData) {
        this.returnCode = HttpStatus.OK.value();
        this.returnMsg = "操作成功";
        this.returnData = returnData;
    }

    public Result(int returnCode, String returnMsg) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }

    public Result(int returnCode, String returnMsg, Object returnData) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
        this.returnData = returnData;
    }

	public Result() {
		super();
	}

	public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public Object getReturnData() {
        return returnData;
    }

    public void setReturnData(Object returnData) {
        this.returnData = returnData;
    }
}
