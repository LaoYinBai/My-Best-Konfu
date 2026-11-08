package com.ss.union.game.sdk.common.audio.inter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IAudioControl {
    int getBufferPercentage();

    int getCurrentPosition();

    int getDuration();

    String getPlayUrl();

    boolean isPlaying();

    void pause();

    void play();

    void play(String str);

    void playAsset(String str);

    void playOrPause();

    IAudioControl registerPlayListener(IAudioPlayListener iAudioPlayListener);

    void release();

    void seekTo(int i);

    IAudioControl setLooping(boolean z);

    IAudioControl setVolume(float f, float f2);

    void stop();
}
