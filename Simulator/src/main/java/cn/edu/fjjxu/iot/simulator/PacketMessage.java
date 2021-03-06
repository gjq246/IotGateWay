package cn.edu.fjjxu.iot.simulator;
/**
 * 解码器解决粘包问题
 * @author Administrator
 *
 */
public class PacketMessage {

	//消息head
    private PacketHead head;
    //消息body
    private String content;
 
    public PacketMessage(PacketHead head, String content) {
        this.head = head;
        this.content = content;
    }
 
    public PacketHead getHead() {
        return head;
    }
 
    public void setHead(PacketHead head) {
        this.head = head;
    }
 
    public String getContent() {
        return content;
    }
 
    public void setContent(String content) {
        this.content = content;
    }
 
    @Override
    public String toString() {
        return String.format("[head=%d,length=%d,content=%s]",head.getHead(),head.getLength(),content);
    }
}
