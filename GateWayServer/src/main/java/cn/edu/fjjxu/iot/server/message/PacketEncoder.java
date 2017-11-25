package cn.edu.fjjxu.iot.server.message;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<PacketMessage>{

	@Override
    protected void encode(ChannelHandlerContext channelHandlerContext, PacketMessage packetMessage, ByteBuf byteBuf) throws Exception {
 
        int length = packetMessage.getHead().getLength();
        int version = packetMessage.getHead().getVersion();
        String content = packetMessage.getContent();
 
        byteBuf.writeInt(length);
        byteBuf.writeInt(version);
        byteBuf.writeBytes(content.getBytes(Charset.forName("UTF-8")));
 
    }
}
