package com.fz.voicerecognitiondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 将“12345678”替换成您申请的APPID，申请地址：http://www.xfyun.cn // 请勿在“=”与appid之间添加任何空字符或者转义符
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=59424c3d");
    }
    public void startListen(View view){
        //创建SpeechRecognizer对象。第二个参数：本地听写时传InitListener
        SpeechRecognizer mIat=SpeechRecognizer.createRecognizer(this,null);
        //设置听写参数
        mIat.setParameter(SpeechConstant.DOMAIN,"iat");
        mIat.setParameter(SpeechConstant.LANGUAGE,"zh_cn");
        mIat.setParameter(SpeechConstant.ACCENT,"mandarin");
        //开始听写
        mIat.startListening(mRecoListener);
    }
    //听写监听器
    private RecognizerListener mRecoListener=new RecognizerListener() {
        @Override
        //volume音量值0~30,
        public void onVolumeChanged(int i, byte[] bytes) {

        }

        @Override
        //开始录音
        public void onBeginOfSpeech() {

        }

        @Override
        //结束录音
        public void onEndOfSpeech() {

        }

        @Override
        //听写结果回调接口（返回Json格式结果）
        //一般情况下会通过onResult接口多次返回结果，完整的识别内容是多次结果的累加
        //b等于true时会话结束
        public void onResult(RecognizerResult recognizerResult, boolean b) {
            System.out.println(recognizerResult.getResultString());
            System.out.println("isLast:"+b);

        }

        @Override
        //会话发生错误回调接口
        public void onError(SpeechError speechError) {

        }

        @Override
        //扩展用接口
        public void onEvent(int i, int i1, int i2, Bundle bundle) {

        }
    };
    public void startListenUI(View view){
        //1.创建RecognizerDialog对象
        RecognizerDialog mDialog=new RecognizerDialog(this,null);
        //2.设置accent、language等参数
        mDialog.setParameter(SpeechConstant.LANGUAGE,"zh_cn");
        mDialog.setParameter(SpeechConstant.ACCENT,"mandarin");
        //3.设置回调接口
        mDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b) {
                System.out.println(recognizerResult.getResultString());
                System.out.println("isLast:"+b);
            }

            @Override
            public void onError(SpeechError speechError) {

            }
        });
        //4.显示dialog，接收语音输入
        mDialog.show();
    }

    public void startSpeak(View view){
        //1.创建SpeechSynthesizer对象。第二个参数：本地合成时传InitListener
        SpeechSynthesizer mTts=SpeechSynthesizer.createSynthesizer(this,null);
        //2.合成参数设置
        mTts.setParameter(SpeechConstant.VOICE_NAME,"xiaomei");//设置发音人
        mTts.setParameter(SpeechConstant.SPEED,"50");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME,"80");//设置音量，0~100
        mTts.setParameter(SpeechConstant.ENGINE_TYPE,SpeechConstant.TYPE_CLOUD);//设置云端
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH,"./sdcard/iflytek.pcm");//设置合成音频保存位置
        //3.开始合成
        mTts.startSpeaking("草木人生，铭记过往，一些人，一些事，最终很难淡出我们的记忆，毕竟山水一路，曾经相依，毕竟漂泊的心，曾是彼此的停靠。时光滴答，未曾停歇，红尘烟火，这一路谁能安然走过，也许只有摒弃喧嚣，摒弃繁华，摒弃情感，才能让心平坦如镜，才能让灵魂记住一段来路，忘记一封花信已没有了签收的地址",null);
    }

}
