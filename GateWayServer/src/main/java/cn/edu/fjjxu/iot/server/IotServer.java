package cn.edu.fjjxu.iot.server;

import java.util.logging.Logger;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class IotServer {
	
	private static final Logger logger = Logger
            .getLogger(IotServer.class.getName());

//    public void bind(int port) throws Exception {
//        EventLoopGroup bossGroup = new NioEventLoopGroup();
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//        try {
//            // 配置服务器的NIO线程租
//            ServerBootstrap b = new ServerBootstrap();
//            b.group(bossGroup, workerGroup)
//                    .channel(NioServerSocketChannel.class)
//                    .option(ChannelOption.SO_BACKLOG, 1024)
//                    .childHandler(new ChildChannelHandler());
//
//            // 绑定端口，同步等待成功
//            ChannelFuture f = b.bind(port).sync();
//            logger.info("服务器启动成功！");
//            // 等待服务端监听端口关闭
//            f.channel().closeFuture().sync();
//            
//        } finally {
//            // 优雅退出，释放线程池资源
//            bossGroup.shutdownGracefully();
//            workerGroup.shutdownGracefully();
//        }
//    }
//
//    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
//        @Override
//        protected void initChannel(SocketChannel arg0) throws Exception {
//            System.out.println("server initChannel..");
//            arg0.pipeline().addLast(new MyServerHandler());
//        }
//    }
    
    
    public void run(){  
        EventLoopGroup boosGroup = new NioEventLoopGroup();  
        EventLoopGroup workGroup = new NioEventLoopGroup();  
        
      EventLoopGroup bossGroup = new NioEventLoopGroup();
      EventLoopGroup workerGroup = new NioEventLoopGroup();
         
        try {  
                 ServerBootstrap bootstrap = new ServerBootstrap();  
                 bootstrap.group(boosGroup, workGroup);  
                 bootstrap.channel(NioServerSocketChannel.class);  
                 bootstrap.childHandler(new ChildChannelHandler());  
                 //System.err.println("服务器开启待客户端链接.....");  
                 
                 Channel ch = bootstrap.bind(7397).sync().channel();
                 logger.info("web socket 7379服务器启动成功！");
                 
//               // 配置服务器的NIO线程租
               ServerBootstrap b = new ServerBootstrap();
               b.group(bossGroup, workerGroup)
                       .channel(NioServerSocketChannel.class)
                       .option(ChannelOption.SO_BACKLOG, 1024)
                       .childHandler(new ServerHandler());
   
               // 绑定端口，同步等待成功
               ChannelFuture f = b.bind(9000).sync();
               logger.info("服务器启动成功！");
               
               
               
               ch.closeFuture().sync();
               
               
               
               // 等待服务端监听端口关闭
               f.channel().closeFuture().sync();
                 
                 
                 
                 
        } catch (Exception e) {  
                 e.printStackTrace();  
        }finally{  
                 boosGroup.shutdownGracefully();  
                 workGroup.shutdownGracefully();  
                 
               bossGroup.shutdownGracefully();
               workerGroup.shutdownGracefully();
        }  
}  
    

    public static void main(String[] args) throws Exception {
//        int port = 9000;
//        if (args != null && args.length > 0) {
//            try {
//                port = Integer.valueOf(args[0]);
//            } catch (NumberFormatException e) {
//
//            }
//        }

        new IotServer().run(); 
        
        //new MyServer().bind(port);
        
         
    }
}
