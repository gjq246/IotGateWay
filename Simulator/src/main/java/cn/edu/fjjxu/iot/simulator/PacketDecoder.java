package cn.edu.fjjxu.iot.simulator;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.log4j.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class PacketDecoder extends ByteToMessageDecoder {

	private static final Logger logger = Logger.getLogger(PacketDecoder.class);
	
	@Override
	protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list)
			throws Exception {
        try{
        	
        	 // 可读长度必须大于基本长度  ,头部两个int是8字节
            if (byteBuf.readableBytes() >= 8) {  
                // 防止socket字节流攻击  
                // 防止，客户端传来的数据过大  
                // 因为，太大的数据，是不合理的  
                if (byteBuf.readableBytes() > 2048) {  
                	byteBuf.skipBytes(byteBuf.readableBytes());  
                }
                
             // 记录包头开始的index  
                int beginReader;  
      
                while (true) {  
                    // 获取包头开始的index  
                    beginReader = byteBuf.readerIndex();  
                    // 标记包头开始的index  
                    byteBuf.markReaderIndex();  
                    // 读到了协议的开始标志，结束while循环，
                    if (byteBuf.readInt() == PacketConstant.HEAD_DATA) {  
                        break;  
                    }  
      
                    // 未读到包头，略过一个字节  
                    // 每次略过，一个字节，去读取，包头信息的开始标记  
                    byteBuf.resetReaderIndex();  
                    byteBuf.readByte();  
      
                    // 当略过，一个字节之后，  
                    // 数据包的长度，又变得不满足  
                    // 此时，应该结束。等待后面的数据到达  
                    if (byteBuf.readableBytes() < 8) {  
                        return;  
                    }  
                }  
      
                // 消息的长度      
                int length = byteBuf.readInt();  
                // 判断请求数据包数据是否到齐  
                if (byteBuf.readableBytes() < length) {  
                    // 还原读指针  
                	byteBuf.readerIndex(beginReader);  
                    return;  
                }  
                
                byte[] body = new byte[length];
    			
    			byteBuf.readBytes(body);
//    			logger.info("body:");
    			
    					
    			String content = new String(body, Charset.forName("UTF-8"));
    	        
    			PacketMessage packetMessage = new PacketMessage(new PacketHead(length, PacketConstant.HEAD_DATA), content);
    	
    			list.add(packetMessage);
            }
		
			
        }catch (Exception e) {
			// TODO: handle exception
        	logger.error("@@@@@@PacketDecoder:"+e.getMessage());
		}
	}
}
