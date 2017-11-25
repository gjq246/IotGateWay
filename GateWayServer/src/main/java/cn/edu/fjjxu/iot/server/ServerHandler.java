package cn.edu.fjjxu.iot.server;

import org.apache.log4j.Logger;

import cn.edu.fjjxu.iot.server.message.PacketMessage;
import cn.edu.fjjxu.iot.service.MessageService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class ServerHandler extends ChannelInboundHandlerAdapter {

	private static final Logger logger = Logger.getLogger(ServerHandler.class);
	
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
//        System.out.println("server channelRead..");
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
//        String body = new String(req, "UTF-8");
    	
    	PacketMessage packetMessage = (PacketMessage) msg;
        try {
            // Do something with msg
        	logger.info("server get :" + packetMessage.getContent());
        	MessageService messageService=new MessageService();
        	messageService.parseMessage(ctx, packetMessage.getContent());
 
        } catch (Exception e) {
			// TODO: handle exception
        	logger.error("channelRead exception:"+e.getMessage());
		}finally {
            //ByteBuf是一个引用计数对象，这个对象必须显示地调用release()方法来释放
            //or ((ByteBuf)msg).release();
            ReferenceCountUtil.release(msg);
        }
        
        logger.info(ctx.channel().id().toString());//facae811
        
        logger.info(ctx.channel().remoteAddress().toString());///127.0.0.1:56331
        
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    	logger.info("server channelReadComplete..");
        ctx.flush();//刷新后才将数据发出到SocketChannel
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
    	logger.error("server exceptionCaught:"+cause.getMessage());
        ctx.close();
    }
     

}