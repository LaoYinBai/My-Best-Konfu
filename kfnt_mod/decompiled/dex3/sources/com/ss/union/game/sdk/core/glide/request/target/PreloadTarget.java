package com.ss.union.game.sdk.core.glide.request.target;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.ss.union.game.sdk.core.glide.RequestManager;
import com.ss.union.game.sdk.core.glide.request.transition.Transition;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class PreloadTarget<Z> extends SimpleTarget<Z> {
    private static final int a = 1;
    private static final Handler b = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.ss.union.game.sdk.core.glide.request.target.PreloadTarget.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            ((PreloadTarget) message.obj).a();
            return true;
        }
    });
    private final RequestManager c;

    public static <Z> PreloadTarget<Z> obtain(RequestManager requestManager, int i, int i2) {
        return new PreloadTarget<>(requestManager, i, i2);
    }

    private PreloadTarget(RequestManager requestManager, int i, int i2) {
        super(i, i2);
        this.c = requestManager;
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public void onResourceReady(Z z, Transition<? super Z> transition) {
        b.obtainMessage(1, this).sendToTarget();
    }

    void a() {
        this.c.clear(this);
    }
}
