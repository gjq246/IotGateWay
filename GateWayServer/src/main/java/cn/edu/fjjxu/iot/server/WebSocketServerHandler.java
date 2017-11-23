package cn.edu.fjjxu.iot.server;

import io.netty.buffer.ByteBuf;  
import io.netty.buffer.Unpooled;  
import io.netty.channel.ChannelFuture;  
import io.netty.channel.ChannelFutureListener;  
import io.netty.channel.ChannelHandlerContext;  
import io.netty.channel.SimpleChannelInboundHandler;  
import io.netty.handler.codec.http.DefaultFullHttpResponse;  
import io.netty.handler.codec.http.FullHttpRequest;  
import io.netty.handler.codec.http.HttpResponseStatus;  
import io.netty.handler.codec.http.HttpVersion;  
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;  
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;  
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;  
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;  
import io.netty.handler.codec.http.websocketx.WebSocketFrame;  
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;  
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;  
import io.netty.util.CharsetUtil;   
import java.util.Calendar;  
import java.util.GregorianCalendar;  
import java.util.logging.Level;  
import java.util.logging.Logger;   
 
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object>{   
         private static final Logger logger = Logger.getLogger(WebSocketServerHandshaker.class.getName());  
         private WebSocketServerHandshaker handshaker;  
          
          
         @Override  
         public void channelActive(ChannelHandlerContext ctx) throws Exception {  
                   Global.group.add(ctx.channel());  
                   System.out.println("客户端与服务器段开启");  
         }   
         @Override  
         public void channelInactive(ChannelHandlerContext ctx) throws Exception {  
                   Global.group.remove(ctx.channel());  
                   System.out.println("客户端与服务器链接关闭！");  
         }   
           
         @Override  
         public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {  
                   ctx.flush();  
         }   
         private void handlerWebSocketFrame(ChannelHandlerContext ctx,  
                            WebSocketFrame frame) {  
                    
                   if(frame instanceof CloseWebSocketFrame){  
                            handshaker.close(ctx.channel(), (CloseWebSocketFrame)frame.retain());  
                   }  
                   if(frame instanceof PingWebSocketFrame){  
                            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));  
                   }  
                    
                   if(!(frame instanceof TextWebSocketFrame)){  
                            System.err.println("本例仅支持文本！");  
                            throw new UnsupportedOperationException(String.format(  
                                               "%s frame types not supported", frame.getClass().getName()));  
                   }  
                    
                   String request = ((TextWebSocketFrame)frame).text();  
                   System.out.println("服务器收到:" + request);  
                   if (logger.isLoggable(Level.FINE)) {  
                            logger.fine(String.format("%s received %s", ctx.channel(),request));  
                   }  
                    
                    
                   TextWebSocketFrame tws = new TextWebSocketFrame(getDateTime()  
                                     + "(" +ctx.channel().remoteAddress() + ") ：" + request);  
                   // 群发  
                   Global.group.writeAndFlush(tws);  
                   // 返回【谁发的发给谁】  
                   // ctx.channel().writeAndFlush(tws);  
         }   
         private void handleHttpRequest(ChannelHandlerContext ctx,FullHttpRequest req) {  
                    
                   if(!req.decoderResult().isSuccess() ||  
                                     !("websocket".equals(req.headers().get("Upgrade")))){  
                            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(  
                                               HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));  
                            return;  
                   }  
                    
                   WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://localhost:7397/websocket", null, false);  
                   handshaker = wsFactory.newHandshaker(req);  
                   if(null == handshaker){  
                            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());  
                   }else{  
                            handshaker.handshake(ctx.channel(), req);  
                   }  
                    
         }   
         private void sendHttpResponse(ChannelHandlerContext ctx,  
                            FullHttpRequest req, DefaultFullHttpResponse res) {  
                   if(res.status().code() != 200){  
                            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(),  
                                               CharsetUtil.UTF_8);  
                            res.content().writeBytes(buf);  
                            buf.release();  
                   }  
                    
                   ChannelFuture future = ctx.channel().writeAndFlush(res);  
                   if (!isKeepAlive(req) || res.status().code() != 200) {  
                            future.addListener(ChannelFutureListener.CLOSE);  
                   }  
                    
         }  
          
         private static boolean isKeepAlive(FullHttpRequest req){  
                   return false;  
         }  
          
         @Override  
         public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)  
                            throws Exception {  
                   cause.printStackTrace();  
                   ctx.close();  
         }    
         private String getDateTime() {  
                   // Calendar calendar = Calendar.getInstance();  
                   Calendar calendar = new GregorianCalendar();  
                   java.util.Date date = new java.util.Date();  
                   calendar.setTime(date);  
                   String sHour = null;  
                   String sMinute = null;  
                   String sSecond = null;  
                   String sYear = null;  
                   String sMonth = null;  
                   String sDay = null;  
                   int year = calendar.get(Calendar.YEAR);  
                   int month = calendar.get(Calendar.MONTH) + 1;  
                   int day = calendar.get(Calendar.DATE);  
                   int hour = calendar.get(Calendar.HOUR_OF_DAY);  
                   int minute = calendar.get(Calendar.MINUTE);  
                   int second = calendar.get(Calendar.SECOND);  
//                   int milliSecond = calendar.get(Calendar.MILLISECOND);   
                   sYear = String.valueOf(year);  
                   if (month < 10) {  
                            sMonth = "0" + month;  
                   } else  
                            sMonth = String.valueOf(month);  
                   if (day < 10) {  
                            sDay = "0" + day;  
                   } else  
                            sDay = String.valueOf(day);   
                   if (hour < 10) {  
                            sHour = "0" + hour;  
                   } else {  
                            sHour = String.valueOf(hour);  
                   }   
                   if (minute < 10) {  
                            sMinute = "0" + minute;  
                   } else {  
                            sMinute = String.valueOf(minute);  
                   }   
                   if (second < 10) {  
                            sSecond = "0" + second;  
                   } else {  
                            sSecond = String.valueOf(second);  
                   }   
                   return sYear + "-" + sMonth + "-" + sDay + " " + sHour + ":" + sMinute + ":" + sSecond;  
         }
		@Override
		protected void channelRead0(ChannelHandlerContext arg0, Object arg1) throws Exception {
			// TODO Auto-generated method stub
			if(arg1 instanceof FullHttpRequest){  
                handleHttpRequest(arg0, ((FullHttpRequest) arg1));  
	       }else if(arg1 instanceof WebSocketFrame){  
	                handlerWebSocketFrame(arg0, (WebSocketFrame) arg1);  
	       } 
			
		}    
}   