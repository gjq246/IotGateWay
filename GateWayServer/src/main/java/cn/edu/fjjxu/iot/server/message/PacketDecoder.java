package cn.edu.fjjxu.iot.server.message;

import java.nio.charset.Charset;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class PacketDecoder extends ByteToMessageDecoder {

	 @Override
	    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
	 
	        int length = byteBuf.readInt();
	        int version = byteBuf.readInt();
	 
	        byte[] body = new byte[length];
	        byteBuf.readBytes(body);
	 
	        String content = new String(body, Charset.forName("UTF-8"));
	 
	        PacketMessage packetMessage = new PacketMessage(new PacketHead(length,version),content);
	 
	        list.add(packetMessage);
	    }
}
