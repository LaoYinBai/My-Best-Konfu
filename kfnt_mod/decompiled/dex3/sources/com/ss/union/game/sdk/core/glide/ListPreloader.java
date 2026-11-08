package com.ss.union.game.sdk.core.glide;

import android.widget.AbsListView;
import com.ss.union.game.sdk.core.glide.request.target.BaseTarget;
import com.ss.union.game.sdk.core.glide.request.target.SizeReadyCallback;
import com.ss.union.game.sdk.core.glide.request.transition.Transition;
import com.ss.union.game.sdk.core.glide.util.Util;
import java.util.List;
import java.util.Queue;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ListPreloader<T> implements AbsListView.OnScrollListener {
    private final int a;
    private final b b;
    private final RequestManager c;
    private final PreloadModelProvider<T> d;
    private final PreloadSizeProvider<T> e;
    private int f;
    private int g;
    private int i;
    private int h = -1;
    private boolean j = true;

    public interface PreloadModelProvider<U> {
        List<U> getPreloadItems(int i);

        RequestBuilder<?> getPreloadRequestBuilder(U u);
    }

    public interface PreloadSizeProvider<T> {
        int[] getPreloadSize(T t, int i, int i2);
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public ListPreloader(RequestManager requestManager, PreloadModelProvider<T> preloadModelProvider, PreloadSizeProvider<T> preloadSizeProvider, int i) {
        this.c = requestManager;
        this.d = preloadModelProvider;
        this.e = preloadSizeProvider;
        this.a = i;
        this.b = new b(i + 1);
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.i = i3;
        if (i > this.h) {
            a(i2 + i, true);
        } else if (i < this.h) {
            a(i, false);
        }
        this.h = i;
    }

    private void a(int i, boolean z) {
        if (this.j != z) {
            this.j = z;
            a();
        }
        a(i, (z ? this.a : -this.a) + i);
    }

    private void a(int i, int i2) {
        int iMin;
        int iMax;
        if (i < i2) {
            iMax = Math.max(this.f, i);
            iMin = i2;
        } else {
            iMin = Math.min(this.g, i);
            iMax = i2;
        }
        int iMin2 = Math.min(this.i, iMin);
        int iMin3 = Math.min(this.i, Math.max(0, iMax));
        if (i < i2) {
            for (int i3 = iMin3; i3 < iMin2; i3++) {
                a((List) this.d.getPreloadItems(i3), i3, true);
            }
        } else {
            for (int i4 = iMin2 - 1; i4 >= iMin3; i4--) {
                a((List) this.d.getPreloadItems(i4), i4, false);
            }
        }
        this.g = iMin3;
        this.f = iMin2;
    }

    private void a(List<T> list, int i, boolean z) {
        int size = list.size();
        if (z) {
            for (int i2 = 0; i2 < size; i2++) {
                a((ListPreloader<T>) list.get(i2), i, i2);
            }
            return;
        }
        for (int i3 = size - 1; i3 >= 0; i3--) {
            a((ListPreloader<T>) list.get(i3), i, i3);
        }
    }

    private void a(T t, int i, int i2) {
        int[] preloadSize;
        RequestBuilder<?> preloadRequestBuilder;
        if (t == null || (preloadSize = this.e.getPreloadSize(t, i, i2)) == null || (preloadRequestBuilder = this.d.getPreloadRequestBuilder(t)) == null) {
            return;
        }
        preloadRequestBuilder.into((RequestBuilder<?>) this.b.a(preloadSize[0], preloadSize[1]));
    }

    private void a() {
        for (int i = 0; i < this.a; i++) {
            this.c.clear(this.b.a(0, 0));
        }
    }

    private static final class b {
        private final Queue<a> a;

        b(int i) {
            this.a = Util.createQueue(i);
            for (int i2 = 0; i2 < i; i2++) {
                this.a.offer(new a());
            }
        }

        public a a(int i, int i2) {
            a aVarPoll = this.a.poll();
            this.a.offer(aVarPoll);
            aVarPoll.b = i;
            aVarPoll.a = i2;
            return aVarPoll;
        }
    }

    private static final class a extends BaseTarget<Object> {
        int a;
        int b;

        @Override // com.ss.union.game.sdk.core.glide.request.target.Target
        public void onResourceReady(Object obj, Transition<? super Object> transition) {
        }

        @Override // com.ss.union.game.sdk.core.glide.request.target.Target
        public void removeCallback(SizeReadyCallback sizeReadyCallback) {
        }

        a() {
        }

        @Override // com.ss.union.game.sdk.core.glide.request.target.Target
        public void getSize(SizeReadyCallback sizeReadyCallback) {
            sizeReadyCallback.onSizeReady(this.b, this.a);
        }
    }
}
