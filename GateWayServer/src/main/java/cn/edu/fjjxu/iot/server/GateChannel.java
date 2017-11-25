package cn.edu.fjjxu.iot.server;

import java.sql.Timestamp;
import java.util.List;

import cn.edu.fjjxu.iot.server.message.Device;
import cn.edu.fjjxu.iot.server.message.Result;
import io.netty.channel.ChannelHandlerContext;

/**
 * 网关数据包
 * @author Administrator
 *
 */
public class GateChannel {
	
	private ChannelHandlerContext gateChannel;//连接通道
	
	private String responseData;//响应内容
	
	private String error;//错误信息
	
	private String gateid;//网关编号
	private String gatekey;//网关主键
	
	private String channelid;//连接通道id
	private String clientaddress;//通信主机地址，/127.0.0.1:56331
	private String clientport;//通信主机端口
	
	private Timestamp lastDataTime;//最后一次实时数据时间
	
	private Result result;//最后一次从网关返回的控制指令结果，不能并发
	
	private List<Device> gatedevicelist;

	public ChannelHandlerContext getGateChannel() {
		return gateChannel;
	}

	public void setGateChannel(ChannelHandlerContext gateChannel) {
		this.gateChannel = gateChannel;
	}

	public String getResponseData() {
		return responseData;
	}

	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getGateid() {
		return gateid;
	}

	public void setGateid(String gateid) {
		this.gateid = gateid;
	}

	public String getGatekey() {
		return gatekey;
	}

	public void setGatekey(String gatekey) {
		this.gatekey = gatekey;
	}

	public Timestamp getLastDataTime() {
		return lastDataTime;
	}

	public void setLastDataTime(Timestamp lastDataTime) {
		this.lastDataTime = lastDataTime;
	}	

	public String getChannelid() {
		return channelid;
	}

	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}

	public String getClientaddress() {
		return clientaddress;
	}

	public void setClientaddress(String clientaddress) {
		this.clientaddress = clientaddress;
	}

	public String getClientport() {
		return clientport;
	}

	public void setClientport(String clientport) {
		this.clientport = clientport;
	}

	public List<Device> getGatedevicelist() {
		return gatedevicelist;
	}

	public void setGatedevicelist(List<Device> gatedevicelist) {
		this.gatedevicelist = gatedevicelist;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}	
	
	

}
