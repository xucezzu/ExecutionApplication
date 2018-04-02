package com.bubble.execute.utils.baidu;

import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;

import java.util.Map;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/30
 * 版权所有 © 徐长策
 */
public class InitConfig {
    /**
     * appId appKey 和 secretKey
     */
    private String appId;

    private String appKey;

    private String secretKey;

    /**
     * 纯在线或者离在线融合
     */
    private TtsMode ttsMode;

    /**
     * 初始化的其它参数，用于setParam
     */
    private Map<String, String> params;

    /**
     * 合成引擎的回调
     */
    private SpeechSynthesizerListener listener;

    public InitConfig(String appId,
                      String appKey,
                      String secretKey,
                      TtsMode ttsMode,
                      Map<String, String> params,
                      SpeechSynthesizerListener listener) {
        this.appId = appId;
        this.appKey = appKey;
        this.secretKey = secretKey;
        this.ttsMode = ttsMode;
        this.params = params;
        this.listener = listener;
    }

    public SpeechSynthesizerListener getListener() {
        return listener;
    }

    public Map<String, String> getParams() {
        return params;
    }


    public String getAppId() {
        return appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
