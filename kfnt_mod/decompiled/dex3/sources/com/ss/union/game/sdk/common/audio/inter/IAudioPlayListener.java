package com.ss.union.game.sdk.common.audio.inter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IAudioPlayListener {
    void onBlockEnd();

    void onBlockStart();

    void onBufferingUpdate(int i);

    void onError(int i, String str);

    void onPause();

    void onPlay();

    void onPlayCompletion();

    void onPlayProgress(int i, int i2, int i3);

    void onPrepare();

    void onSeekEnd();

    void onSeekStart();

    void onStop();
}
