package com.fz.chatrobot;

import java.util.ArrayList;

/**
 * Created by 冯政 on 2017/6/16.
 */

public class VoiceBean {
    public ArrayList<WsBean> ws;
    public class WsBean{
        public ArrayList<CwBean> cw;
    }
    public class CwBean{
        public String w;
    }
}
