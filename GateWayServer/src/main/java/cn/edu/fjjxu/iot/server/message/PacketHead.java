package cn.edu.fjjxu.iot.server.message;

public class PacketHead {
	
	//数据长度
    private int length;
 
    //数据版本
    private int head;
 
 
    public PacketHead(int length, int head) {
        this.length = length;
        this.head = head;
    }
 
    public int getLength() {
        return length;
    }
 
    public void setLength(int length) {
        this.length = length;
    }

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}
 
    
}
