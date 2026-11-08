package com.ss.union.game.sdk.core.glide.load.engine;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class q {
    private boolean a;
    private final Handler b = new Handler(Looper.getMainLooper(), new a());

    q() {
    }

    synchronized void a(Resource<?> resource) {
        if (this.a) {
            this.b.obtainMessage(1, resource).sendToTarget();
        } else {
            this.a = true;
            resource.recycle();
            this.a = false;
        }
    }

    private static final class a implements Handler.Callback {
        static final int a = 1;

        a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            ((Resource) message.obj).recycle();
            return true;
        }
    }
}
