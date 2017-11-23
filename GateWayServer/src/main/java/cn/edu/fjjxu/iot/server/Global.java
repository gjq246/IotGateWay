package cn.edu.fjjxu.iot.server;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public final class Global {  
    
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);  
     
} 
