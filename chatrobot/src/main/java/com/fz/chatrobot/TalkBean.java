package com.fz.chatrobot;

/**
 * Created by 冯政 on 2017/6/16.
 */

public class TalkBean {
    public TalkBean(String askStr, boolean b, int i){
        this.content=askStr;
        this.imageId=i;
        this.isAsk=b;
    }
    public String content;
    public boolean isAsk;
    public int imageId;
}
