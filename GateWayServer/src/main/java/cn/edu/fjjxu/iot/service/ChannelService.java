package cn.edu.fjjxu.iot.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import cn.edu.fjjxu.iot.server.GateChannel;
import cn.edu.fjjxu.iot.server.Global;
import cn.edu.fjjxu.iot.server.message.Device;
import cn.edu.fjjxu.iot.server.message.Gate;
import cn.edu.fjjxu.iot.server.message.Result;
import io.netty.channel.ChannelHandlerContext;

public class ChannelService {
	
	private static final Logger logger = Logger.getLogger(ChannelService.class);
	
	private MessageService messageService;
	
	public void addChannel(ChannelHandlerContext ctx,Gate gate){
		messageService=new MessageService();
		try{
			
			//如果存在要移除
			if(Global.gateChannelDataList!=null && Global.gateChannelDataList.size()>0){
				for(int i=0;i<Global.gateChannelDataList.size();i++){
					if(Global.gateChannelDataList.get(i).getGateid().equals(gate.getGateid())){
						
						try{
							
							Global.gateChannelDataList.get(i).getGateChannel().close();
						}catch (Exception e) {
							// TODO: handle exception
							logger.error("添加网关关闭连接异常："+e.getMessage());
						}
						
						Global.gateChannelDataList.remove(i);
					}
				}
			}
			
			GateChannel gateChannel=new GateChannel();
			
			gateChannel.setChannelid(ctx.channel().id().toString());
			gateChannel.setClientaddress(ctx.channel().remoteAddress().toString());
			gateChannel.setGateChannel(ctx);
			gateChannel.setGatedevicelist(new ArrayList<Device>());
			gateChannel.setGateid(gate.getGateid());
			gateChannel.setLastDataTime(new Timestamp((new Date()).getTime()));
			
			Global.gateChannelDataList.add(gateChannel);
			//添加到Global
			logger.info("添加网关成功："+gate.getGateid());
			
			messageService.sendResult(ctx, 1, true, "ok");
			
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("添加网关异常："+e.getMessage());
			messageService.sendResult(ctx, 1, false, "error");
		}
		
	}
	
	/**
     * 通过gateid检索网络通道是否存在
     * @param gateid
     * @return
     */
    public GateChannel getGateChannel(String gateid){
    	
    	for(GateChannel gc:Global.gateChannelDataList){
    		
    		if(gc.getGateid().equals(gateid)){
    			return gc;
    		}
    		
    	}
    	
    	
    	return null;
    	
    }
    /**
     * 通过clientdeviceid检索网络通道是否存在
     * @param clientdeviceid
     * @return
     */
    public GateChannel getDeviceChannel(String clientdeviceid){
    	
    	for(GateChannel gc:Global.gateChannelDataList){
    		
    		if(gc.getGatedevicelist()!=null && gc.getGatedevicelist().size()>0){
    			
    			for(Device d:gc.getGatedevicelist()){
    				
    				if(d.getClientdeviceid().equals(clientdeviceid)){

    					return gc;
    					
    				}
    				
    			}
    		}
    		
    	}
    	
    	
    	return null;
    	
    }
    /**
     * 通过ctx检索网络通道是否存在
     * @param ctx
     * @return
     */
    public GateChannel getGateChannel(ChannelHandlerContext ctx){
    	
       for(GateChannel gc:Global.gateChannelDataList){
    		
    		if(gc.getChannelid().equals(ctx.channel().id().toString())){
    			return gc;
    		}
    		
    	}    	
    	
    	return null;
    	
    }
    
    public void updateDevices(ChannelHandlerContext ctx,List<Device> devicelist){
    	messageService=new MessageService();
    	if(devicelist!=null && devicelist.size()>0){
    		
    		GateChannel gc=getGateChannel(ctx);
    		if(gc!=null){
    			logger.info("连接已经建立过了，准备更新设备信息......");
    			//暂时用清除重建方法
    			if(gc.getGatedevicelist()!=null && gc.getGatedevicelist().size()>0){
    				gc.getGatedevicelist().clear();
    			}
    			for(Device d:devicelist){
    				logger.info(d.getClientdeviceid());
    				logger.info(d.getDevicecode());
    				logger.info(d.getData());
    				gc.getGatedevicelist().add(d);
    			}
    		}
    		messageService.sendResult(ctx, 2, true, "ok");
    		
    	}else{
    		messageService.sendResult(ctx, 2, false, "null");
    	}
    }
    
    public void updateResult(ChannelHandlerContext ctx,Result result){
    	
    	GateChannel gc=getGateChannel(ctx);
		if(gc!=null){
			gc.setResult(result);
		}
    	
    }
    
    public Device getDevice(String clientdeviceid){
    	
    	for(GateChannel gc:Global.gateChannelDataList){
    		
    		if(gc.getGatedevicelist()!=null && gc.getGatedevicelist().size()>0){
    			
    			for(Device d:gc.getGatedevicelist()){
    				
    				if(d.getClientdeviceid().equals(clientdeviceid)){

    					return d;
    					
    				}
    				
    			}
    		}
    		
    	}
    	
    	
    	return null;
    	
    }

}
