package cn.edu.fjjxu.iot.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.*;

import com.alibaba.fastjson.JSON;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class IotClient {

	private static final Logger logger = Logger.getLogger(IotClient.class.getName());

	private IotClientHandler clientHandler = new IotClientHandler();

	public void connect(int port, String host) throws Exception {
		// 配置客户端NIO线程组
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline p = ch.pipeline();
					p.addLast(new PacketDecoder());
					p.addLast(new PacketEncoder());
					p.addLast(clientHandler);
				}
			});

			// 发起异步连接操作
			ChannelFuture f = b.connect(host, port).sync();

			logger.info("客户端已经连接..");
			
			Message message;
			Gate gate;
			message=new Message();
			message.setCode(1);
			gate=new Gate();
			gate.setGateid(Config.getObject("gateid"));
			message.setData(JSON.toJSONString(gate));
			//登记网关
			clientHandler.sendMessage(JSON.toJSONString(message));

			TimeUnit.SECONDS.sleep(4);
			
			int max=20;
	        int min=10;
	        Random random = new Random();
	        
//	        String[] led={"r","g","b"};
	        clientHandler.rgbled.setPower("on");
	        clientHandler.rgbled.setColor("g");

	        String dht11id=Config.getObject("dht11.deviceid");
	        String rgbledid=Config.getObject("rgbled.deviceid");
	        		
			while (true) {

				message=new Message();
				message.setCode(2);
				
				List<Device> devicelist = new ArrayList<Device>();
				
				Device d1=new Device();
				DHT11 dht11=new DHT11();
				int t = random.nextInt(max)%(max-min+1) + min;
				dht11.setTemp(String.valueOf(t));
				int h = random.nextInt(max)%(max-min+1) + min;
				dht11.setHumi(String.valueOf(h));
				
				d1.setClientdeviceid(dht11id);
				d1.setDevicecode("DHT11");
				d1.setData(JSON.toJSONString(dht11));
				
				Device d2=new Device();
//				RGBLED rgbled=new RGBLED();
//				rgbled.setPower("on");
//				int i=random.nextInt(2)%(2-0+1) + 0;
//				rgbled.setColor("b");
				d2.setClientdeviceid(rgbledid);
				d2.setDevicecode("RGBLED");
				d2.setData(JSON.toJSONString(clientHandler.rgbled));
				
				devicelist.add(d1);
				devicelist.add(d2);				
						
				message.setData(JSON.toJSONString(devicelist));
				
				clientHandler.sendMessage(JSON.toJSONString(message));

				//每隔5秒上报一次
				TimeUnit.SECONDS.sleep(5);
				
			}

			// 等待客户端链路关闭
			//f.channel().closeFuture().sync();
		} finally {
			// 优雅退出，释放NIO线程组
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		new IotClient().connect(Integer.parseInt(Config.getObject("server.port")), Config.getObject("server.address"));

	}

}
