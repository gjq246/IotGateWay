package cn.edu.fjjxu.iot.simulator;

import org.apache.log4j.*;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class IotClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = Logger
            .getLogger(IotClientHandler.class.getName());
    
    private ChannelHandlerContext ctx;

//    private final ByteBuf firstMessage;

    public IotClientHandler() {
//        byte[] req = "QUERY TIME ORDER".getBytes();
//        firstMessage = Unpooled.buffer(req.length);
//        firstMessage.writeBytes(req);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //与服务端建立连接后
    	logger.info("client channelActive..");
        this.ctx = ctx;
        String firstMessage="QUERY TIME ORDER";
        ctx.writeAndFlush(new PacketMessage(new PacketHead(firstMessage.getBytes("UTF-8").length,1),firstMessage));
    }
    
    public boolean sendMessage(String msg)
    {
    	boolean result;
    	try{
//    		ByteBuf msgByte;
//    		byte[] req = msg.getBytes();
//    		msgByte = Unpooled.buffer(req.length);
//    		msgByte.writeBytes(req);
//    		ctx.writeAndFlush(msgByte);
    		PacketMessage packetMessage=new PacketMessage(new PacketHead(msg.getBytes("UTF-8").length,1),msg);
    		logger.info(packetMessage.toString());
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
//        System.out.println("client channelRead..");
//        //服务端返回消息后
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
//        String body = new String(req, "UTF-8");
//        System.out.println("Now is :" + body);
    	
    	PacketMessage packetMessage = (PacketMessage) msg;
        try {
            // Do something with msg
        	logger.info("client get :" + packetMessage.getContent());
 
        } finally {
            //ByteBuf是一个引用计数对象，这个对象必须显示地调用release()方法来释放
            //or ((ByteBuf)msg).release();
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
    }

}

