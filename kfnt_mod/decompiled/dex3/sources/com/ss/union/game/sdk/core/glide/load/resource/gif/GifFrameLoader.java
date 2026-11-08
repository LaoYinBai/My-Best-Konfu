package com.ss.union.game.sdk.core.glide.load.resource.gif;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.ss.union.game.sdk.core.glide.Glide;
import com.ss.union.game.sdk.core.glide.RequestBuilder;
import com.ss.union.game.sdk.core.glide.RequestManager;
import com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder;
import com.ss.union.game.sdk.core.glide.load.Key;
import com.ss.union.game.sdk.core.glide.load.Transformation;
import com.ss.union.game.sdk.core.glide.load.engine.DiskCacheStrategy;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool;
import com.ss.union.game.sdk.core.glide.request.BaseRequestOptions;
import com.ss.union.game.sdk.core.glide.request.RequestOptions;
import com.ss.union.game.sdk.core.glide.request.target.SimpleTarget;
import com.ss.union.game.sdk.core.glide.request.transition.Transition;
import com.ss.union.game.sdk.core.glide.signature.ObjectKey;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import com.ss.union.game.sdk.core.glide.util.Util;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class GifFrameLoader {
    final RequestManager a;
    private final GifDecoder b;
    private final Handler c;
    private final List<FrameCallback> d;
    private final BitmapPool e;
    private boolean f;
    private boolean g;
    private boolean h;
    private RequestBuilder<Bitmap> i;
    private a j;
    private boolean k;
    private a l;
    private Bitmap m;
    private Transformation<Bitmap> n;
    private a o;
    private c p;

    public interface FrameCallback {
        void onFrameReady();
    }

    interface c {
        void a();
    }

    GifFrameLoader(Glide glide, GifDecoder gifDecoder, int i, int i2, Transformation<Bitmap> transformation, Bitmap bitmap) {
        this(glide.getBitmapPool(), Glide.with(glide.getContext()), gifDecoder, null, a(Glide.with(glide.getContext()), i, i2), transformation, bitmap);
    }

    GifFrameLoader(BitmapPool bitmapPool, RequestManager requestManager, GifDecoder gifDecoder, Handler handler, RequestBuilder<Bitmap> requestBuilder, Transformation<Bitmap> transformation, Bitmap bitmap) {
        this.d = new ArrayList();
        this.a = requestManager;
        handler = handler == null ? new Handler(Looper.getMainLooper(), new b()) : handler;
        this.e = bitmapPool;
        this.c = handler;
        this.i = requestBuilder;
        this.b = gifDecoder;
        a(transformation, bitmap);
    }

    void a(Transformation<Bitmap> transformation, Bitmap bitmap) {
        this.n = (Transformation) Preconditions.checkNotNull(transformation);
        this.m = (Bitmap) Preconditions.checkNotNull(bitmap);
        this.i = this.i.apply((BaseRequestOptions<?>) new RequestOptions().transform(transformation));
    }

    Transformation<Bitmap> a() {
        return this.n;
    }

    Bitmap b() {
        return this.m;
    }

    void a(FrameCallback frameCallback) {
        if (this.k) {
            throw new IllegalStateException("Cannot subscribe to a cleared frame loader");
        }
        if (this.d.contains(frameCallback)) {
            throw new IllegalStateException("Cannot subscribe twice in a row");
        }
        boolean zIsEmpty = this.d.isEmpty();
        this.d.add(frameCallback);
        if (zIsEmpty) {
            n();
        }
    }

    void b(FrameCallback frameCallback) {
        this.d.remove(frameCallback);
        if (this.d.isEmpty()) {
            o();
        }
    }

    int c() {
        return k().getWidth();
    }

    int d() {
        return k().getHeight();
    }

    int e() {
        return this.b.getByteSize() + m();
    }

    int f() {
        if (this.j != null) {
            return this.j.a;
        }
        return -1;
    }

    private int m() {
        return Util.getBitmapByteSize(k().getWidth(), k().getHeight(), k().getConfig());
    }

    ByteBuffer g() {
        return this.b.getData().asReadOnlyBuffer();
    }

    int h() {
        return this.b.getFrameCount();
    }

    int i() {
        return this.b.getTotalIterationCount();
    }

    private void n() {
        if (this.f) {
            return;
        }
        this.f = true;
        this.k = false;
        p();
    }

    private void o() {
        this.f = false;
    }

    void j() {
        this.d.clear();
        q();
        o();
        if (this.j != null) {
            this.a.clear(this.j);
            this.j = null;
        }
        if (this.l != null) {
            this.a.clear(this.l);
            this.l = null;
        }
        if (this.o != null) {
            this.a.clear(this.o);
            this.o = null;
        }
        this.b.clear();
        this.k = true;
    }

    Bitmap k() {
        return this.j != null ? this.j.a() : this.m;
    }

    private void p() {
        if (!this.f || this.g) {
            return;
        }
        if (this.h) {
            Preconditions.checkArgument(this.o == null, "Pending target must be null when starting from the first frame");
            this.b.resetFrameIndex();
            this.h = false;
        }
        if (this.o != null) {
            a aVar = this.o;
            this.o = null;
            a(aVar);
        } else {
            this.g = true;
            long jUptimeMillis = SystemClock.uptimeMillis() + this.b.getNextDelay();
            this.b.advance();
            this.l = new a(this.c, this.b.getCurrentFrameIndex(), jUptimeMillis);
            this.i.apply((BaseRequestOptions<?>) RequestOptions.signatureOf(r())).load((Object) this.b).into((RequestBuilder<Bitmap>) this.l);
        }
    }

    private void q() {
        if (this.m != null) {
            this.e.put(this.m);
            this.m = null;
        }
    }

    void l() {
        Preconditions.checkArgument(!this.f, "Can't restart a running animation");
        this.h = true;
        if (this.o != null) {
            this.a.clear(this.o);
            this.o = null;
        }
    }

    void a(c cVar) {
        this.p = cVar;
    }

    void a(a aVar) {
        if (this.p != null) {
            this.p.a();
        }
        this.g = false;
        if (this.k) {
            this.c.obtainMessage(2, aVar).sendToTarget();
            return;
        }
        if (!this.f) {
            this.o = aVar;
            return;
        }
        if (aVar.a() != null) {
            q();
            a aVar2 = this.j;
            this.j = aVar;
            for (int size = this.d.size() - 1; size >= 0; size--) {
                this.d.get(size).onFrameReady();
            }
            if (aVar2 != null) {
                this.c.obtainMessage(2, aVar2).sendToTarget();
            }
        }
        p();
    }

    private class b implements Handler.Callback {
        static final int a = 1;
        static final int b = 2;

        b() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                GifFrameLoader.this.a((a) message.obj);
                return true;
            }
            if (message.what != 2) {
                return false;
            }
            GifFrameLoader.this.a.clear((a) message.obj);
            return false;
        }
    }

    static class a extends SimpleTarget<Bitmap> {
        final int a;
        private final Handler b;
        private final long c;
        private Bitmap d;

        a(Handler handler, int i, long j) {
            this.b = handler;
            this.a = i;
            this.c = j;
        }

        Bitmap a() {
            return this.d;
        }

        @Override // com.ss.union.game.sdk.core.glide.request.target.Target
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
            this.d = bitmap;
            this.b.sendMessageAtTime(this.b.obtainMessage(1, this), this.c);
        }
    }

    private static RequestBuilder<Bitmap> a(RequestManager requestManager, int i, int i2) {
        return requestManager.asBitmap().apply((BaseRequestOptions<?>) RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE).useAnimationPool(true).skipMemoryCache(true).override(i, i2));
    }

    private static Key r() {
        return new ObjectKey(Double.valueOf(Math.random()));
    }
}
