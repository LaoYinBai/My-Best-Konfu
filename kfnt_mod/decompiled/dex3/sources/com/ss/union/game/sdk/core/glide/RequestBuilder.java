package com.ss.union.game.sdk.core.glide;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.ss.union.game.sdk.core.glide.load.engine.DiskCacheStrategy;
import com.ss.union.game.sdk.core.glide.request.BaseRequestOptions;
import com.ss.union.game.sdk.core.glide.request.ErrorRequestCoordinator;
import com.ss.union.game.sdk.core.glide.request.FutureTarget;
import com.ss.union.game.sdk.core.glide.request.Request;
import com.ss.union.game.sdk.core.glide.request.RequestCoordinator;
import com.ss.union.game.sdk.core.glide.request.RequestFutureTarget;
import com.ss.union.game.sdk.core.glide.request.RequestListener;
import com.ss.union.game.sdk.core.glide.request.RequestOptions;
import com.ss.union.game.sdk.core.glide.request.SingleRequest;
import com.ss.union.game.sdk.core.glide.request.ThumbnailRequestCoordinator;
import com.ss.union.game.sdk.core.glide.request.target.PreloadTarget;
import com.ss.union.game.sdk.core.glide.request.target.Target;
import com.ss.union.game.sdk.core.glide.signature.ApplicationVersionSignature;
import com.ss.union.game.sdk.core.glide.util.Executors;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import com.ss.union.game.sdk.core.glide.util.Util;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RequestBuilder<TranscodeType> extends BaseRequestOptions<RequestBuilder<TranscodeType>> implements b<RequestBuilder<TranscodeType>>, Cloneable {
    protected static final RequestOptions DOWNLOAD_ONLY_OPTIONS = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA).priority(Priority.LOW).skipMemoryCache(true);
    private final Context a;
    private final RequestManager b;
    private final Class<TranscodeType> c;
    private final Glide d;
    private final GlideContext e;
    private TransitionOptions<?, ? super TranscodeType> f;
    private Object g;
    private List<RequestListener<TranscodeType>> h;
    private RequestBuilder<TranscodeType> i;
    private RequestBuilder<TranscodeType> j;
    private Float k;
    private boolean l;
    private boolean m;
    private boolean n;

    @Override // com.ss.union.game.sdk.core.glide.request.BaseRequestOptions
    public /* bridge */ /* synthetic */ BaseRequestOptions apply(BaseRequestOptions baseRequestOptions) {
        return apply((BaseRequestOptions<?>) baseRequestOptions);
    }

    @SuppressLint({"CheckResult"})
    protected RequestBuilder(Glide glide, RequestManager requestManager, Class<TranscodeType> cls, Context context) {
        this.l = true;
        this.d = glide;
        this.b = requestManager;
        this.c = cls;
        this.a = context;
        this.f = requestManager.a(cls);
        this.e = glide.b();
        a(requestManager.a());
        apply((BaseRequestOptions<?>) requestManager.b());
    }

    @SuppressLint({"CheckResult"})
    protected RequestBuilder(Class<TranscodeType> cls, RequestBuilder<?> requestBuilder) {
        this(requestBuilder.d, requestBuilder.b, cls, requestBuilder.a);
        this.g = requestBuilder.g;
        this.m = requestBuilder.m;
        apply((BaseRequestOptions<?>) requestBuilder);
    }

    @SuppressLint({"CheckResult"})
    private void a(List<RequestListener<Object>> list) {
        Iterator<RequestListener<Object>> it = list.iterator();
        while (it.hasNext()) {
            addListener((RequestListener) it.next());
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.request.BaseRequestOptions
    public RequestBuilder<TranscodeType> apply(BaseRequestOptions<?> baseRequestOptions) {
        Preconditions.checkNotNull(baseRequestOptions);
        return (RequestBuilder) super.apply(baseRequestOptions);
    }

    public RequestBuilder<TranscodeType> transition(TransitionOptions<?, ? super TranscodeType> transitionOptions) {
        this.f = (TransitionOptions) Preconditions.checkNotNull(transitionOptions);
        this.l = false;
        return this;
    }

    public RequestBuilder<TranscodeType> listener(RequestListener<TranscodeType> requestListener) {
        this.h = null;
        return addListener(requestListener);
    }

    public RequestBuilder<TranscodeType> addListener(RequestListener<TranscodeType> requestListener) {
        if (requestListener != null) {
            if (this.h == null) {
                this.h = new ArrayList();
            }
            this.h.add(requestListener);
        }
        return this;
    }

    public RequestBuilder<TranscodeType> error(RequestBuilder<TranscodeType> requestBuilder) {
        this.j = requestBuilder;
        return this;
    }

    public RequestBuilder<TranscodeType> thumbnail(RequestBuilder<TranscodeType> requestBuilder) {
        this.i = requestBuilder;
        return this;
    }

    public RequestBuilder<TranscodeType> thumbnail(RequestBuilder<TranscodeType>... requestBuilderArr) {
        RequestBuilder<TranscodeType> requestBuilderThumbnail = null;
        if (requestBuilderArr == null || requestBuilderArr.length == 0) {
            return thumbnail((RequestBuilder) null);
        }
        for (int length = requestBuilderArr.length - 1; length >= 0; length--) {
            RequestBuilder<TranscodeType> requestBuilder = requestBuilderArr[length];
            if (requestBuilder != null) {
                requestBuilderThumbnail = requestBuilderThumbnail == null ? requestBuilder : requestBuilder.thumbnail(requestBuilderThumbnail);
            }
        }
        return thumbnail(requestBuilderThumbnail);
    }

    public RequestBuilder<TranscodeType> thumbnail(float f) {
        if (f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
        }
        this.k = Float.valueOf(f);
        return this;
    }

    @Override // com.ss.union.game.sdk.core.glide.b
    public RequestBuilder<TranscodeType> load(Object obj) {
        return a(obj);
    }

    private RequestBuilder<TranscodeType> a(Object obj) {
        this.g = obj;
        this.m = true;
        return this;
    }

    @Override // com.ss.union.game.sdk.core.glide.b
    public RequestBuilder<TranscodeType> load(Bitmap bitmap) {
        return a(bitmap).apply((BaseRequestOptions<?>) RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE));
    }

    @Override // com.ss.union.game.sdk.core.glide.b
    public RequestBuilder<TranscodeType> load(Drawable drawable) {
        return a(drawable).apply((BaseRequestOptions<?>) RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE));
    }

    @Override // com.ss.union.game.sdk.core.glide.b
    public RequestBuilder<TranscodeType> load(String str) {
        return a(str);
    }

    @Override // com.ss.union.game.sdk.core.glide.b
    public RequestBuilder<TranscodeType> load(Uri uri) {
        return a(uri);
    }

    @Override // com.ss.union.game.sdk.core.glide.b
    public RequestBuilder<TranscodeType> load(File file) {
        return a(file);
    }

    @Override // com.ss.union.game.sdk.core.glide.b
    public RequestBuilder<TranscodeType> load(Integer num) {
        return a(num).apply((BaseRequestOptions<?>) RequestOptions.signatureOf(ApplicationVersionSignature.obtain(this.a)));
    }

    @Override // com.ss.union.game.sdk.core.glide.b
    @Deprecated
    public RequestBuilder<TranscodeType> load(URL url) {
        return a(url);
    }

    @Override // com.ss.union.game.sdk.core.glide.b
    public RequestBuilder<TranscodeType> load(byte[] bArr) {
        RequestBuilder<TranscodeType> requestBuilderA = a(bArr);
        if (!requestBuilderA.isDiskCacheStrategySet()) {
            requestBuilderA = requestBuilderA.apply((BaseRequestOptions<?>) RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE));
        }
        return !requestBuilderA.isSkipMemoryCacheSet() ? requestBuilderA.apply((BaseRequestOptions<?>) RequestOptions.skipMemoryCacheOf(true)) : requestBuilderA;
    }

    @Override // com.ss.union.game.sdk.core.glide.request.BaseRequestOptions
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public RequestBuilder<TranscodeType> mo3clone() {
        RequestBuilder<TranscodeType> requestBuilder = (RequestBuilder) super.mo3clone();
        requestBuilder.f = requestBuilder.f.m4clone();
        return requestBuilder;
    }

    public <Y extends Target<TranscodeType>> Y into(Y y) {
        return (Y) a((RequestBuilder<TranscodeType>) y, (RequestListener) null, Executors.mainThreadExecutor());
    }

    <Y extends Target<TranscodeType>> Y a(Y y, RequestListener<TranscodeType> requestListener, Executor executor) {
        return (Y) a(y, requestListener, this, executor);
    }

    private <Y extends Target<TranscodeType>> Y a(Y y, RequestListener<TranscodeType> requestListener, BaseRequestOptions<?> baseRequestOptions, Executor executor) {
        Preconditions.checkNotNull(y);
        if (!this.m) {
            throw new IllegalArgumentException("You must call #load() before calling #into()");
        }
        Request requestB = b(y, requestListener, baseRequestOptions, executor);
        Request request = y.getRequest();
        if (requestB.isEquivalentTo(request) && !a(baseRequestOptions, request)) {
            requestB.recycle();
            if (!((Request) Preconditions.checkNotNull(request)).isRunning()) {
                request.begin();
            }
            return y;
        }
        this.b.clear((Target<?>) y);
        y.setRequest(requestB);
        this.b.a(y, requestB);
        return y;
    }

    private boolean a(BaseRequestOptions<?> baseRequestOptions, Request request) {
        return !baseRequestOptions.isMemoryCacheable() && request.isComplete();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.ss.union.game.sdk.core.glide.request.target.ViewTarget<android.widget.ImageView, TranscodeType> into(android.widget.ImageView r4) {
        /*
            r3 = this;
            com.ss.union.game.sdk.core.glide.util.Util.assertMainThread()
            com.ss.union.game.sdk.core.glide.util.Preconditions.checkNotNull(r4)
            boolean r0 = r3.isTransformationSet()
            if (r0 != 0) goto L4c
            boolean r0 = r3.isTransformationAllowed()
            if (r0 == 0) goto L4c
            android.widget.ImageView$ScaleType r0 = r4.getScaleType()
            if (r0 == 0) goto L4c
            int[] r0 = com.ss.union.game.sdk.core.glide.RequestBuilder.AnonymousClass1.a
            android.widget.ImageView$ScaleType r1 = r4.getScaleType()
            int r1 = r1.ordinal()
            r0 = r0[r1]
            switch(r0) {
                case 1: goto L43;
                case 2: goto L3a;
                case 3: goto L31;
                case 4: goto L31;
                case 5: goto L31;
                case 6: goto L28;
                default: goto L27;
            }
        L27:
            goto L4c
        L28:
            com.ss.union.game.sdk.core.glide.request.BaseRequestOptions r0 = r3.mo3clone()
            com.ss.union.game.sdk.core.glide.request.BaseRequestOptions r0 = r0.optionalCenterInside()
            goto L4d
        L31:
            com.ss.union.game.sdk.core.glide.request.BaseRequestOptions r0 = r3.mo3clone()
            com.ss.union.game.sdk.core.glide.request.BaseRequestOptions r0 = r0.optionalFitCenter()
            goto L4d
        L3a:
            com.ss.union.game.sdk.core.glide.request.BaseRequestOptions r0 = r3.mo3clone()
            com.ss.union.game.sdk.core.glide.request.BaseRequestOptions r0 = r0.optionalCenterInside()
            goto L4d
        L43:
            com.ss.union.game.sdk.core.glide.request.BaseRequestOptions r0 = r3.mo3clone()
            com.ss.union.game.sdk.core.glide.request.BaseRequestOptions r0 = r0.optionalCenterCrop()
            goto L4d
        L4c:
            r0 = r3
        L4d:
            com.ss.union.game.sdk.core.glide.GlideContext r1 = r3.e
            java.lang.Class<TranscodeType> r2 = r3.c
            com.ss.union.game.sdk.core.glide.request.target.ViewTarget r4 = r1.buildImageViewTarget(r4, r2)
            r1 = 0
            java.util.concurrent.Executor r2 = com.ss.union.game.sdk.core.glide.util.Executors.mainThreadExecutor()
            com.ss.union.game.sdk.core.glide.request.target.Target r4 = r3.a(r4, r1, r0, r2)
            com.ss.union.game.sdk.core.glide.request.target.ViewTarget r4 = (com.ss.union.game.sdk.core.glide.request.target.ViewTarget) r4
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.union.game.sdk.core.glide.RequestBuilder.into(android.widget.ImageView):com.ss.union.game.sdk.core.glide.request.target.ViewTarget");
    }

    @Deprecated
    public FutureTarget<TranscodeType> into(int i, int i2) {
        return submit(i, i2);
    }

    public FutureTarget<TranscodeType> submit() {
        return submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
    }

    public FutureTarget<TranscodeType> submit(int i, int i2) {
        RequestFutureTarget requestFutureTarget = new RequestFutureTarget(i, i2);
        return (FutureTarget) a((RequestBuilder<TranscodeType>) requestFutureTarget, requestFutureTarget, Executors.directExecutor());
    }

    public Target<TranscodeType> preload(int i, int i2) {
        return into((RequestBuilder<TranscodeType>) PreloadTarget.obtain(this.b, i, i2));
    }

    public Target<TranscodeType> preload() {
        return preload(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
    }

    @Deprecated
    public <Y extends Target<File>> Y downloadOnly(Y y) {
        return (Y) getDownloadOnlyRequest().into((RequestBuilder<File>) y);
    }

    @Deprecated
    public FutureTarget<File> downloadOnly(int i, int i2) {
        return getDownloadOnlyRequest().submit(i, i2);
    }

    protected RequestBuilder<File> getDownloadOnlyRequest() {
        return new RequestBuilder(File.class, this).apply((BaseRequestOptions<?>) DOWNLOAD_ONLY_OPTIONS);
    }

    /* renamed from: com.ss.union.game.sdk.core.glide.RequestBuilder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            try {
                b[Priority.LOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[Priority.NORMAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[Priority.HIGH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[Priority.IMMEDIATE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            a = new int[ImageView.ScaleType.values().length];
            try {
                a[ImageView.ScaleType.CENTER_CROP.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[ImageView.ScaleType.FIT_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[ImageView.ScaleType.FIT_START.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[ImageView.ScaleType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[ImageView.ScaleType.FIT_XY.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                a[ImageView.ScaleType.CENTER.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                a[ImageView.ScaleType.MATRIX.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    private Priority a(Priority priority) {
        switch (priority) {
            case LOW:
                return Priority.NORMAL;
            case NORMAL:
                return Priority.HIGH;
            case HIGH:
            case IMMEDIATE:
                return Priority.IMMEDIATE;
            default:
                throw new IllegalArgumentException("unknown priority: " + getPriority());
        }
    }

    private Request b(Target<TranscodeType> target, RequestListener<TranscodeType> requestListener, BaseRequestOptions<?> baseRequestOptions, Executor executor) {
        return a(target, requestListener, (RequestCoordinator) null, this.f, baseRequestOptions.getPriority(), baseRequestOptions.getOverrideWidth(), baseRequestOptions.getOverrideHeight(), baseRequestOptions, executor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Request a(Target<TranscodeType> target, RequestListener<TranscodeType> requestListener, RequestCoordinator requestCoordinator, TransitionOptions<?, ? super TranscodeType> transitionOptions, Priority priority, int i, int i2, BaseRequestOptions<?> baseRequestOptions, Executor executor) {
        RequestCoordinator requestCoordinator2;
        RequestCoordinator errorRequestCoordinator;
        if (this.j != null) {
            errorRequestCoordinator = new ErrorRequestCoordinator(requestCoordinator);
            requestCoordinator2 = errorRequestCoordinator;
        } else {
            requestCoordinator2 = null;
            errorRequestCoordinator = requestCoordinator;
        }
        Request requestB = b(target, requestListener, errorRequestCoordinator, transitionOptions, priority, i, i2, baseRequestOptions, executor);
        if (requestCoordinator2 == null) {
            return requestB;
        }
        int overrideWidth = this.j.getOverrideWidth();
        int overrideHeight = this.j.getOverrideHeight();
        if (Util.isValidDimensions(i, i2) && !this.j.isValidOverride()) {
            overrideWidth = baseRequestOptions.getOverrideWidth();
            overrideHeight = baseRequestOptions.getOverrideHeight();
        }
        ErrorRequestCoordinator errorRequestCoordinator2 = requestCoordinator2;
        errorRequestCoordinator2.setRequests(requestB, this.j.a(target, requestListener, requestCoordinator2, this.j.f, this.j.getPriority(), overrideWidth, overrideHeight, this.j, executor));
        return errorRequestCoordinator2;
    }

    private Request b(Target<TranscodeType> target, RequestListener<TranscodeType> requestListener, RequestCoordinator requestCoordinator, TransitionOptions<?, ? super TranscodeType> transitionOptions, Priority priority, int i, int i2, BaseRequestOptions<?> baseRequestOptions, Executor executor) {
        if (this.i != null) {
            if (this.n) {
                throw new IllegalStateException("You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()");
            }
            TransitionOptions<?, ? super TranscodeType> transitionOptions2 = this.i.l ? transitionOptions : this.i.f;
            Priority priority2 = this.i.isPrioritySet() ? this.i.getPriority() : a(priority);
            int overrideWidth = this.i.getOverrideWidth();
            int overrideHeight = this.i.getOverrideHeight();
            if (Util.isValidDimensions(i, i2) && !this.i.isValidOverride()) {
                overrideWidth = baseRequestOptions.getOverrideWidth();
                overrideHeight = baseRequestOptions.getOverrideHeight();
            }
            ThumbnailRequestCoordinator thumbnailRequestCoordinator = new ThumbnailRequestCoordinator(requestCoordinator);
            Request requestA = a(target, requestListener, baseRequestOptions, thumbnailRequestCoordinator, transitionOptions, priority, i, i2, executor);
            this.n = true;
            Request requestA2 = this.i.a(target, requestListener, thumbnailRequestCoordinator, transitionOptions2, priority2, overrideWidth, overrideHeight, this.i, executor);
            this.n = false;
            thumbnailRequestCoordinator.setRequests(requestA, requestA2);
            return thumbnailRequestCoordinator;
        }
        if (this.k != null) {
            ThumbnailRequestCoordinator thumbnailRequestCoordinator2 = new ThumbnailRequestCoordinator(requestCoordinator);
            thumbnailRequestCoordinator2.setRequests(a(target, requestListener, baseRequestOptions, thumbnailRequestCoordinator2, transitionOptions, priority, i, i2, executor), a(target, requestListener, baseRequestOptions.mo3clone().sizeMultiplier(this.k.floatValue()), thumbnailRequestCoordinator2, transitionOptions, a(priority), i, i2, executor));
            return thumbnailRequestCoordinator2;
        }
        return a(target, requestListener, baseRequestOptions, requestCoordinator, transitionOptions, priority, i, i2, executor);
    }

    private Request a(Target<TranscodeType> target, RequestListener<TranscodeType> requestListener, BaseRequestOptions<?> baseRequestOptions, RequestCoordinator requestCoordinator, TransitionOptions<?, ? super TranscodeType> transitionOptions, Priority priority, int i, int i2, Executor executor) {
        return SingleRequest.obtain(this.a, this.e, this.g, this.c, baseRequestOptions, i, i2, priority, target, requestListener, this.h, requestCoordinator, this.e.getEngine(), transitionOptions.a(), executor);
    }
}
