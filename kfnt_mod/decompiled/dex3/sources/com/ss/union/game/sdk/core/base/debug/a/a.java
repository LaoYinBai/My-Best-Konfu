package com.ss.union.game.sdk.core.base.debug.a;

import com.ss.union.game.sdk.common.audio.core.LGAudioPlayer;
import com.ss.union.game.sdk.common.audio.inter.AudioPlayListenerAdapter;
import com.ss.union.game.sdk.common.audio.inter.IAudioControl;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static volatile a b;
    IAudioControl a;

    public static a a() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    private a() {
    }

    public void b() {
        if (this.a == null || !this.a.isPlaying()) {
            e();
            this.a = d();
            this.a.playAsset("music_suwei.mp3");
        }
    }

    public void a(String str) {
        if (this.a != null && this.a.isPlaying() && this.a.getPlayUrl().equals(str)) {
            return;
        }
        e();
        this.a = d();
        this.a.play(str);
    }

    public void c() {
        e();
    }

    private IAudioControl d() {
        return LGAudioPlayer.newAudioPlayer().setLooping(true).registerPlayListener(new AudioPlayListenerAdapter() { // from class: com.ss.union.game.sdk.core.base.debug.a.a.1
            @Override // com.ss.union.game.sdk.common.audio.inter.AudioPlayListenerAdapter, com.ss.union.game.sdk.common.audio.inter.IAudioPlayListener
            public void onStop() {
                super.onStop();
                a.this.e();
            }

            @Override // com.ss.union.game.sdk.common.audio.inter.AudioPlayListenerAdapter, com.ss.union.game.sdk.common.audio.inter.IAudioPlayListener
            public void onError(int i, String str) {
                super.onError(i, str);
                a.this.e();
            }

            @Override // com.ss.union.game.sdk.common.audio.inter.AudioPlayListenerAdapter, com.ss.union.game.sdk.common.audio.inter.IAudioPlayListener
            public void onPlayCompletion() {
                super.onPlayCompletion();
                a.this.e();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.a != null) {
            this.a.stop();
        }
        this.a = null;
    }
}
