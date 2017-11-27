package cn.edu.fjjxu.iot.server.message;
/**
 * 通讯协议V1.0
 * 
 * 一、Socket部分
 * 
 *       通讯服务器《------------》树莓派网关
 *       
 * 1.网关登记验证(指令码1,第一版暂不用数据库检查，直接返回成功)
 * 客户端发送{"code":1,"data":"JSON字符串"}
 * 例如：{"code":1,"data":"{\"gateid\":1}"}
 * id采用UUID生成作为消息的唯一编号
 * 
 * 服务端回应{"code":1,"data":"JSON字符串"}
 * 例如：{"code":1,"data":"{\"success\":true,\"msg\":\"ok\"}"}
 * 或者{"code":1,"data":"{\"success\":false,\"msg\":\"error\"}"}
 * 
 * 2.发送实时数据(指令码2)
 * 客户端发送{"code":2,"data":"JSON字符串"},
 * 例如：{"code":2,"data":"[{\"clientdeviceid\":\"01010001\",\"devicecode\":\"DHT11\",\"data\":\"{\"temp\":\"40\",\"humi\":\"75\"}\"},{\"clientdeviceid\":\"02010001\",\"devicecode\":\"RBGLED\",\"data\":\"{\"power\":\"on\",\"color\":\"r\"}\"}]"}
 *  
 * 服务端回应{"code":2,"data":"JSON字符串"}
 * 例如：{"code":2,"data":"{\"success\":true,\"msg\":\"ok\"}"}
 * 或者{"code":2,"data":"{\"success\":false,\"msg\":\"error\"}"}
 * 
 * 3.发送控制指令(指令码3)
 * 服务端发送{"code":3,"data":"JSON字符串"}
 * 例如：{"code":3,"data":"{\"clientdeviceid\":\"02010001\",\"devicecode\":\"RBGLED\",\"data\":\"{\"power\":\"off\",\"color\":\"r\"}\"}"}
 * 
 * 客户端回应
 * {"code":3,"data":"JSON字符串"}
 * 例如：{"code":3,"data":"{\"success\":true,\"msg\":\"ok\"}"}
 * 或者{"code":3,"data":"{\"success\":false,\"msg\":\"error\"}"}
 * 
 * 二、WebSocket部分
 * 
 *      通讯服务器《------------》web中的js代码
 *      
 * 1.取实时数据(指令码2)
 * js发送{"code":2,"data":"JSON字符串"}
 * 例如{"code":2,"data":"{\"clientdeviceid\":\"02010001\",\"devicecode\":\"RBGLED\",\"data\":\"\"}"}
 * {"code":2,"data":"{\"clientdeviceid\":\"01010001\",\"devicecode\":\"DHT11\",\"data\":\"\"}"}
 * 
 * 服务器回应{"code":2,"data":"JSON字符串"}
 * 例如：{"code":2,"data":"{\"clientdeviceid\":\"02010001\",\"devicecode\":\"RBGLED\",\"data\":\"{\"power\":\"on\",\"color\":\"r\"}\"}"}
 * 
 * 2.发送控制指令(指令码3)
 * js发送{"code":3,"data":"JSON字符串"}
 * 例如：{"code":3,"data":"{\"clientdeviceid\":\"02010001\",\"devicecode\":\"RGBLED\",\"data\":\"{\\\"power\\\":\\\"off\\\",\\\"color\\\":\\\"r\\\"}\"}"}
 * 
 * 服务端回应{"code":2,"data":"JSON字符串"}
 * 例如：{"code":3,"data":"{\"success\":true,\"msg\":\"ok\"}"}
 * 或者{"code":3,"data":"{\"success\":false,\"msg\":\"error\"}"}
 * 
 * @author GJQ
 *
 */
public class Message {
	private int code;//指令码
	private String data;//数据
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	
	

}
