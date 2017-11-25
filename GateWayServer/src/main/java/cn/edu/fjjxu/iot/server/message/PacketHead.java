package cn.edu.fjjxu.iot.server.message;

public class PacketHead {
	
	//数据长度
    private int length;
 
    //数据版本
    private int version;
 
 
    public PacketHead(int length, int version) {
        this.length = length;
        this.version = version;
    }
 
    public int getLength() {
        return length;
    }
 
    public void setLength(int length) {
        this.length = length;
    }
 
    public int getVersion() {
        return version;
    }
 
    public void setVersion(int version) {
        this.version = version;
    }

}
