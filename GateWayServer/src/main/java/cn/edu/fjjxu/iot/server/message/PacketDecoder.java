package cn.edu.fjjxu.iot.server.message;

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
			int length = byteBuf.readInt();
			logger.info("length:"+length);
			int version = byteBuf.readInt();
			logger.info("version:"+version);
			byte[] body = new byte[length];
			
			byteBuf.readBytes(body);
			logger.info("body:");
			
					
			String content = new String(body, Charset.forName("UTF-8"));
	        
			PacketMessage packetMessage = new PacketMessage(new PacketHead(length, version), content);
	
			list.add(packetMessage);
        }catch (Exception e) {
			// TODO: handle exception
        	logger.error("@@@@@@PacketDecoder:"+e.getMessage());
		}
	}
}
