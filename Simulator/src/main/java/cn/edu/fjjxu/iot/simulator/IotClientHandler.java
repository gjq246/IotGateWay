package cn.edu.fjjxu.iot.simulator;

import org.apache.log4j.*;

import com.alibaba.fastjson.JSON;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class IotClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = Logger.getLogger(IotClientHandler.class.getName());
    
    private ChannelHandlerContext ctx;
    
    public RGBLED rgbled=new RGBLED();

    public IotClientHandler() {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //与服务端建立连接后
    	logger.info("client channelActive..");
        this.ctx = ctx;
        String firstMessage="请求连接。。。";
        ctx.writeAndFlush(new PacketMessage(new PacketHead(firstMessage.getBytes("UTF-8").length,PacketConstant.HEAD_DATA),firstMessage));
    }
    
    public boolean sendMessage(String msg)
    {
    	boolean result;
    	try{
    		PacketMessage packetMessage=new PacketMessage(new PacketHead(msg.getBytes("UTF-8").length,PacketConstant.HEAD_DATA),msg);
    		logger.info("回传数据："+packetMessage.toString());
    		ctx.writeAndFlush(packetMessage);
    		result=true;
    	}catch (Exception e) {
			// TODO: handle exception
    		result=false;
    		logger.error(e.getMessage());
		}
    	return result;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
    	
    	PacketMessage packetMessage = (PacketMessage) msg;
        try {
        	logger.info("收到数据:" + packetMessage.getContent());
        	
			Message message = JSON.parseObject(packetMessage.getContent(), Message.class);

			switch (message.getCode()) {
			case 3:
				//收到控制指令做出响应
				Device device=JSON.parseObject(message.getData(),Device.class);
				if(device.getDevicecode().equals("RGBLED")){
					//温湿度
					//DHT11 dht11=JSON.parseObject(device.getData(),DHT11.class);
					logger.info("控制RGBLED的状态为:"+device.getData());
					rgbled=JSON.parseObject(device.getData(),RGBLED.class);
					
				}else{
					logger.info("无法处理这种设备："+device.getDevicecode());
				}
				break;
			default:
				break;
			}
 
        }catch (Exception e) {
			// TODO: handle exception
        	logger.error("channelRead exception:"+e.getMessage());
		} finally {
            ReferenceCountUtil.release(msg);
        }
        
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
    	logger.error("client exceptionCaught..");
        // 释放资源
        logger.error("Unexpected exception from downstream:"
                + cause.getMessage());
        ctx.close();
        
//        2017-11-27 00:24:27 [ERROR]-[cn.edu.fjjxu.iot.simulator.IotClientHandler] client exceptionCaught..
//        2017-11-27 00:24:27 [ERROR]-[cn.edu.fjjxu.iot.simulator.IotClientHandler] Unexpected exception from downstream:java.lang.IndexOutOfBoundsException: readerIndex(8) + length(144) exceeds writerIndex(80): PooledUnsafeDirectByteBuf(ridx: 8, widx: 80, cap: 80)
//        2017-11-27 00:24:27 [ERROR]-[cn.edu.fjjxu.iot.simulator.IotClientHandler] client exceptionCaught..
        
    }

}

