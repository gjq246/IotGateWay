package cn.edu.fjjxu.iot.simulator;

import java.util.ArrayList;
import java.util.List;
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

			for (int i = 0; i < 1; i++) {
				
				message=new Message();
				message.setCode(1);
				gate=new Gate();
				gate.setGateid("1");
				message.setData(JSON.toJSONString(gate));
				
				clientHandler.sendMessage(JSON.toJSONString(message));

				TimeUnit.SECONDS.sleep(4);

				message=new Message();
				message.setCode(2);
				
				List<Device> devicelist = new ArrayList<Device>();
				
				Device d1=new Device();
				DHT11 dht11=new DHT11();
				dht11.setTemp("30");
				dht11.setHumi("65");
				
				d1.setClientdeviceid("01010001");
				d1.setDevicecode("DHT11");
				d1.setData(JSON.toJSONString(dht11));
				
				Device d2=new Device();
				RGBLED rgbled=new RGBLED();
				rgbled.setPower("on");
				rgbled.setColor("r");
				d2.setClientdeviceid("02010001");
				d2.setDevicecode("RGBLED");
				d2.setData(JSON.toJSONString(rgbled));
				
				devicelist.add(d1);
				devicelist.add(d2);				
						
				message.setData(JSON.toJSONString(devicelist));
				
				clientHandler.sendMessage(JSON.toJSONString(message));

				TimeUnit.SECONDS.sleep(4);

				// Thread.sleep(1000);

				clientHandler.sendMessage("发送数据测试3");
			}

			// 等待客户端链路关闭
			f.channel().closeFuture().sync();
		} finally {
			// 优雅退出，释放NIO线程组
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		new IotClient().connect(Integer.parseInt(Config.getObject("server.port")), Config.getObject("server.address"));

	}

}
