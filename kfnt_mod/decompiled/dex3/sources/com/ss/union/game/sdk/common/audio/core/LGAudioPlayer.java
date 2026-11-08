package com.ss.union.game.sdk.common.audio.core;

import android.media.MediaPlayer;
import com.ss.union.game.sdk.common.audio.inter.IAudioControl;
import com.ss.union.game.sdk.common.audio.inter.IAudioDurationListener;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGAudioPlayer {
    public static IAudioControl newAudioPlayer() {
        return a.a();
    }

    public static void getAudioDuration(String str, final IAudioDurationListener iAudioDurationListener) {
        if (iAudioDurationListener == null) {
            return;
        }
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(str);
            mediaPlayer.setAudioStreamType(3);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.ss.union.game.sdk.common.audio.core.LGAudioPlayer.1
                @Override // android.media.MediaPlayer.OnPreparedListener
                public void onPrepared(MediaPlayer mediaPlayer2) {
                    iAudioDurationListener.onDuration(mediaPlayer2.getDuration());
                    try {
                        mediaPlayer2.release();
                    } catch (Throwable unused) {
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
            iAudioDurationListener.onDuration(0);
        }
    }
}
