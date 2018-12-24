package com.bubble.execute.thread.task;

import android.content.Context;

import com.baidu.tts.auth.AuthInfo;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;
import com.bubble.execute.thread.Priority;
import com.bubble.execute.thread.PriorityRunnable;
import com.bubble.execute.utils.baidu.InitConfig;
import com.bubble.execute.utils.LogUtil;
import com.bubble.execute.utils.baidu.MessageListener;
import com.bubble.execute.utils.baidu.OfflineResource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/30
 * 版权所有 © 徐长策
 */
public class SpeechTask extends PriorityRunnable {
    private Context mContext;
    private String mText;
    private SpeechSynthesizer mSpeechSynthesizer;
    private String offlineVoice = OfflineResource.VOICE_MALE;
    private SpeechSynthesizerListener listener = new MessageListener();

    public SpeechTask(Context context, String text) {
        super(Priority.IMMEDIATE);
        this.mContext = context;
        this.mText = text;
    }

    @Override
    public void run() {
        InitConfig initConfig = new InitConfig("8804208", "3QqiPU2nmuUXnqLnvBtBmEkw", "65a4248530310e2e262ae89dec5db191", TtsMode.MIX, getParams(), listener);
        mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        mSpeechSynthesizer.setContext(mContext);
        mSpeechSynthesizer.setSpeechSynthesizerListener(initConfig.getListener());
        mSpeechSynthesizer.setAppId(initConfig.getAppId());
        mSpeechSynthesizer.setApiKey(initConfig.getAppKey(), initConfig.getSecretKey());
        // 授权检测接口(只是通过AuthInfo进行检验授权是否成功。选择纯在线可以不必调用auth方法。
        AuthInfo authInfo = mSpeechSynthesizer.auth(TtsMode.MIX);
        if (!authInfo.isSuccess()) {
            // 离线授权需要网站上的应用填写包名。本demo的包名是com.baidu.tts.sample，定义在build.gradle中
            String errorMsg = authInfo.getTtsError().getDetailMessage();
        }
        setParams(initConfig.getParams());
        // 初始化tts
        mSpeechSynthesizer.initTts(TtsMode.MIX);
        int result = mSpeechSynthesizer.speak(mText);
        LogUtil.d("发音结果：" + result);
    }

    /**
     * 合成的参数，可以初始化时填写，也可以在合成前设置。
     *
     * @return
     */
    private Map<String, String> getParams() {
        Map<String, String> params = new HashMap<String, String>();
        // 以下参数均为选填
        // 设置在线发声音人： 0 普通女声（默认） 1 普通男声 2 特别男声 3 情感男声<度逍遥> 4 情感儿童声<度丫丫>
        params.put(SpeechSynthesizer.PARAM_SPEAKER, "2");
        // 设置合成的音量，0-9 ，默认 5
        params.put(SpeechSynthesizer.PARAM_VOLUME, "9");
        // 设置合成的语速，0-9 ，默认 5
        params.put(SpeechSynthesizer.PARAM_SPEED, "5");
        // 设置合成的语调，0-9 ，默认 5
        params.put(SpeechSynthesizer.PARAM_PITCH, "5");

        params.put(SpeechSynthesizer.PARAM_MIX_MODE, SpeechSynthesizer.MIX_MODE_DEFAULT);
        // 该参数设置为TtsMode.MIX生效。即纯在线模式不生效。
        // MIX_MODE_DEFAULT 默认 ，wifi状态下使用在线，非wifi离线。在线状态下，请求超时6s自动转离线
        // MIX_MODE_HIGH_SPEED_SYNTHESIZE_WIFI wifi状态下使用在线，非wifi离线。在线状态下， 请求超时1.2s自动转离线
        // MIX_MODE_HIGH_SPEED_NETWORK ， 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线
        // MIX_MODE_HIGH_SPEED_SYNTHESIZE, 2G 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线

        // 离线资源文件， 从assets目录中复制到临时目录，需要在initTTs方法前完成
        OfflineResource offlineResource = createOfflineResource(offlineVoice);
        // 声学模型文件路径 (离线引擎使用), 请确认下面两个文件存在
        params.put(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE, offlineResource.getTextFilename());
        params.put(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE,
                offlineResource.getModelFilename());
        return params;
    }

    private OfflineResource createOfflineResource(String voiceType) {
        OfflineResource offlineResource = null;
        try {
            offlineResource = new OfflineResource(mContext, voiceType);
        } catch (IOException e) {
            // IO 错误自行处理
            e.printStackTrace();
        }
        return offlineResource;
    }

    private void setParams(Map<String, String> params) {
        if (params != null) {
            for (Map.Entry<String, String> e : params.entrySet()) {
                mSpeechSynthesizer.setParam(e.getKey(), e.getValue());
            }
        }
    }
}
