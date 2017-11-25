package cn.edu.fjjxu.iot.service;

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

public class MessageService {

	private static final Logger logger = Logger.getLogger(MessageService.class);
	
	public void parseMessage(ChannelHandlerContext ctx, String msg) {

		//List<TestPerson> listPerson = JSON.parseArray(str,TestPerson.class);
		//String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
		// JSON.toJSON(list)
		
		try {
			
			ChannelService channelService = new ChannelService();

			Message message = JSON.parseObject(msg, Message.class);

			switch (message.getCode()) {
			//网关登记验证,{"code":1,"data":"{\"gateid\":1}"}
			
			case 1:
				
				Gate gate=JSON.parseObject(message.getData(),Gate.class);
				channelService.addChannel(ctx, gate);

				break;
			//发送实时数据,{"code":2,"data":"[{\"clientdeviceid\":\"01010001\",\"devicecode\":\"DHT11\",\"data\":\"{\"temp\":\"40\",\"humi\":\"75\"}\"},{\"clientdeviceid\":\"02010001\",\"devicecode\":\"RBGLED\",\"data\":\"{\"power\":\"on\",\"color\":\"r\"}\"}]"}
			case 2:
				
				List<Device> devicelist = JSON.parseArray(message.getData(),Device.class);
				channelService.updateDevices(ctx, devicelist);

				break;
			//发送控制指令，websocket部分,{"code":3,"data":"{\"clientdeviceid\":\"02010001\",\"devicecode\":\"RBGLED\",\"data\":\"{\"power\":\"off\",\"color\":\"r\"}\"}"}
			//这里是接收指令结果
			case 3:
				
				Result result=JSON.parseObject(message.getData(),Result.class);
				channelService.updateResult(ctx, result);

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
