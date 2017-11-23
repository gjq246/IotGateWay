package cn.edu.fjjxu.iot.server;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println("server channelRead..");
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        
        
        
        System.out.println("The time server receive order:" + body);
//        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(
//                System.currentTimeMillis()).toString() : "BAD ORDER";
        String currentTime = new Date(System.currentTimeMillis()).toString()+"中文测试";
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.write(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server channelReadComplete..");
        ctx.flush();//刷新后才将数据发出到SocketChannel
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        System.out.println("server exceptionCaught:"+cause.getMessage());
        ctx.close();
    }
    
    /** 
     * unicode 转字符串 
     */  
    public static String unicode2String(String unicode) {  
       
        StringBuffer string = new StringBuffer();  
       
        String[] hex = unicode.split("\\\\u");  
       
        for (int i = 1; i < hex.length; i++) {  
       
            // 转换出每一个代码点  
            int data = Integer.parseInt(hex[i], 16);  
       
            // 追加成string  
            string.append((char) data);  
        }  
       
        return string.toString();  
    }  

}