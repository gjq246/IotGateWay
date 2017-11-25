package cn.edu.fjjxu.iot.service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import cn.edu.fjjxu.iot.server.message.Device;
import cn.edu.fjjxu.iot.server.message.Gate;
import cn.edu.fjjxu.iot.server.message.Message;
import cn.edu.fjjxu.iot.server.message.PacketHead;
import cn.edu.fjjxu.iot.server.message.PacketMessage;
import cn.edu.fjjxu.iot.server.message.Result;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class WebMessageService {
	
private static final Logger logger = Logger.getLogger(WebMessageService.class);
	
	public void parseMessage(ChannelHandlerContext ctx, String msg) {
		
		try {
			
			ChannelService channelService = new ChannelService();

			Message message = JSON.parseObject(msg, Message.class);

			switch (message.getCode()) {
			
			//实时数据,{"code":2,"data":"{\"clientdeviceid\":\"02010001\",\"devicecode\":\"RBGLED\",\"data\":\"\"}"}
			case 2:
				
				Device device=JSON.parseObject(message.getData(),Device.class);
				
				Device deviceNew=channelService.getDevice(device.getClientdeviceid());
				
				Message message2=new Message();
				message2.setCode(2);				
				message2.setData(JSON.toJSONString(deviceNew));
				
				TextWebSocketFrame tws = new TextWebSocketFrame(JSON.toJSONString(message2));  
				
				ctx.channel().writeAndFlush(tws);  

				break;
			//发送控制指令，{"code":3,"data":"{\"clientdeviceid\":\"02010001\",\"devicecode\":\"RBGLED\",\"data\":\"{\"power\":\"off\",\"color\":\"r\"}\"}"}
			case 3:
				
//				Result result=JSON.parseObject(message.getData(),Result.class);
//				channelService.updateResult(ctx, result);

				break;			

			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("解析数据包异常：" + e.getMessage());
		}

	}
	
	public void sendResult(ChannelHandlerContext ctx, int code,boolean success,String msg){
		try {
			Result result=new Result();
			result.setSuccess(success);
			result.setMsg(msg);
			
			Message message=new Message();
			message.setCode(code);
			message.setData(JSON.toJSONString(result));
			
			String msgStr = JSON.toJSONString(message);
			
			PacketMessage packetMessage=new PacketMessage(new PacketHead(msgStr.getBytes("UTF-8").length,1),msgStr);
			logger.info(packetMessage.toString());
			ctx.writeAndFlush(packetMessage);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("sendResult异常：" + e.getMessage());
		}
		
	}
	
}
