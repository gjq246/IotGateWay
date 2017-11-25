package cn.edu.fjjxu.iot.server;

import java.util.ArrayList;
import java.util.List;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public final class Global {  
    
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);  
    
    public static List<GateChannel> gateChannelDataList = new ArrayList<GateChannel>();
    
        
} 
