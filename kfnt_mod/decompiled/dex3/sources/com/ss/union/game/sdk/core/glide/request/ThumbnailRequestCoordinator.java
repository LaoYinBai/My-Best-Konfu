package com.ss.union.game.sdk.core.glide.request;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ThumbnailRequestCoordinator implements Request, RequestCoordinator {
    private final RequestCoordinator a;
    private Request b;
    private Request c;
    private boolean d;

    ThumbnailRequestCoordinator() {
        this(null);
    }

    public ThumbnailRequestCoordinator(RequestCoordinator requestCoordinator) {
        this.a = requestCoordinator;
    }

    public void setRequests(Request request, Request request2) {
        this.b = request;
        this.c = request2;
    }

    @Override // com.ss.union.game.sdk.core.glide.request.RequestCoordinator
    public boolean canSetImage(Request request) {
        return a() && (request.equals(this.b) || !this.b.isResourceSet());
    }

    private boolean a() {
        return this.a == null || this.a.canSetImage(this);
    }

    @Override // com.ss.union.game.sdk.core.glide.request.RequestCoordinator
    public boolean canNotifyStatusChanged(Request request) {
        return c() && request.equals(this.b) && !isAnyResourceSet();
    }

    @Override // com.ss.union.game.sdk.core.glide.request.RequestCoordinator
    public boolean canNotifyCleared(Request request) {
        return b() && request.equals(this.b);
    }

    private boolean b() {
        return this.a == null || this.a.canNotifyCleared(this);
    }

    private boolean c() {
        return this.a == null || this.a.canNotifyStatusChanged(this);
    }

    @Override // com.ss.union.game.sdk.core.glide.request.RequestCoordinator
    public boolean isAnyResourceSet() {
        return d() || isResourceSet();
    }

    @Override // com.ss.union.game.sdk.core.glide.request.RequestCoordinator
    public void onRequestSuccess(Request request) {
        if (request.equals(this.c)) {
            return;
        }
        if (this.a != null) {
            this.a.onRequestSuccess(this);
        }
        if (this.c.isComplete()) {
            return;
        }
        this.c.clear();
    }

    @Override // com.ss.union.game.sdk.core.glide.request.RequestCoordinator
    public void onRequestFailed(Request request) {
        if (request.equals(this.b) && this.a != null) {
            this.a.onRequestFailed(this);
        }
    }

    private boolean d() {
        return this.a != null && this.a.isAnyResourceSet();
    }

    @Override // com.ss.union.game.sdk.core.glide.request.Request
    public void begin() {
        this.d = true;
        if (!this.b.isComplete() && !this.c.isRunning()) {
            this.c.begin();
        }
        if (!this.d || this.b.isRunning()) {
            return;
        }
        this.b.begin();
    }

    @Override // com.ss.union.game.sdk.core.glide.request.Request
    public void clear() {
        this.d = false;
        this.c.clear();
        this.b.clear();
    }

    @Override // com.ss.union.game.sdk.core.glide.request.Request
    public boolean isRunning() {
        return this.b.isRunning();
    }

    @Override // com.ss.union.game.sdk.core.glide.request.Request
    public boolean isComplete() {
        return this.b.isComplete() || this.c.isComplete();
    }

    @Override // com.ss.union.game.sdk.core.glide.request.Request
    public boolean isResourceSet() {
        return this.b.isResourceSet() || this.c.isResourceSet();
    }

    @Override // com.ss.union.game.sdk.core.glide.request.Request
    public boolean isCleared() {
        return this.b.isCleared();
    }

    @Override // com.ss.union.game.sdk.core.glide.request.Request
    public boolean isFailed() {
        return this.b.isFailed();
    }

    @Override // com.ss.union.game.sdk.core.glide.request.Request
    public void recycle() {
        this.b.recycle();
        this.c.recycle();
    }

    @Override // com.ss.union.game.sdk.core.glide.request.Request
    public boolean isEquivalentTo(Request request) {
        if (!(request instanceof ThumbnailRequestCoordinator)) {
            return false;
        }
        ThumbnailRequestCoordinator thumbnailRequestCoordinator = (ThumbnailRequestCoordinator) request;
        if (this.b == null) {
            if (thumbnailRequestCoordinator.b != null) {
                return false;
            }
        } else if (!this.b.isEquivalentTo(thumbnailRequestCoordinator.b)) {
            return false;
        }
        if (this.c == null) {
            if (thumbnailRequestCoordinator.c != null) {
                return false;
            }
        } else if (!this.c.isEquivalentTo(thumbnailRequestCoordinator.c)) {
            return false;
        }
        return true;
    }
}
