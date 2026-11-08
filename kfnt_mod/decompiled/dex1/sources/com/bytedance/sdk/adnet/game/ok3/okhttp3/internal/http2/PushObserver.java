package com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http2;

import com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface PushObserver {
    public static final PushObserver CANCEL = new PushObserver() { // from class: com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http2.PushObserver.1
        @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http2.PushObserver
        public boolean onData(int i, BufferedSource bufferedSource, int i2, boolean z) {
            bufferedSource.skip(i2);
            return true;
        }

        @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http2.PushObserver
        public boolean onHeaders(int i, List<Header> list, boolean z) {
            return true;
        }

        @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http2.PushObserver
        public boolean onRequest(int i, List<Header> list) {
            return true;
        }

        @Override // com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.http2.PushObserver
        public void onReset(int i, ErrorCode errorCode) {
        }
    };

    boolean onData(int i, BufferedSource bufferedSource, int i2, boolean z);

    boolean onHeaders(int i, List<Header> list, boolean z);

    boolean onRequest(int i, List<Header> list);

    void onReset(int i, ErrorCode errorCode);
}
