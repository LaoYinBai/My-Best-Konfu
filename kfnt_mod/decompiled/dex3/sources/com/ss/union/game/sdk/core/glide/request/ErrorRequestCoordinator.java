package com.ss.union.game.sdk.core.glide.request;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class ErrorRequestCoordinator implements Request, RequestCoordinator {
    private final RequestCoordinator a;
    private Request b;
    private Request c;

    public ErrorRequestCoordinator(RequestCoordinator requestCoordinator) {
        this.a = requestCoordinator;
    }

    public void setRequests(Request request, Request request2) {
        this.b = request;
        this.c = request2;
    }

    @Override // com.ss.union.game.sdk.core.glide.request.Request
    public void begin() {
        if (this.b.isRunning()) {
            return;
        }
        this.b.begin();
    }

    @Override // com.ss.union.game.sdk.core.glide.request.Request
    public void clear() {
        this.b.clear();
        if (this.c.isRunning()) {
            this.c.clear();
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.request.Request
    public boolean isRunning() {
        return (this.b.isFailed() ? this.c : this.b).isRunning();
    }

    @Override // com.ss.union.game.sdk.core.glide.request.Request
    public boolean isComplete() {
        return (this.b.isFailed() ? this.c : this.b).isComplete();
    }

    @Override // com.ss.union.game.sdk.core.glide.request.Request
    public boolean isResourceSet() {
        return (this.b.isFailed() ? this.c : this.b).isResourceSet();
    }

    @Override // com.ss.union.game.sdk.core.glide.request.Request
    public boolean isCleared() {
        return (this.b.isFailed() ? this.c : this.b).isCleared();
    }

    @Override // com.ss.union.game.sdk.core.glide.request.Request
    public boolean isFailed() {
        return this.b.isFailed() && this.c.isFailed();
    }

    @Override // com.ss.union.game.sdk.core.glide.request.Request
    public void recycle() {
        this.b.recycle();
        this.c.recycle();
    }

    @Override // com.ss.union.game.sdk.core.glide.request.Request
    public boolean isEquivalentTo(Request request) {
        if (!(request instanceof ErrorRequestCoordinator)) {
            return false;
        }
        ErrorRequestCoordinator errorRequestCoordinator = (ErrorRequestCoordinator) request;
        return this.b.isEquivalentTo(errorRequestCoordinator.b) && this.c.isEquivalentTo(errorRequestCoordinator.c);
    }

    @Override // com.ss.union.game.sdk.core.glide.request.RequestCoordinator
    public boolean canSetImage(Request request) {
        return a() && a(request);
    }

    private boolean a() {
        return this.a == null || this.a.canSetImage(this);
    }

    @Override // com.ss.union.game.sdk.core.glide.request.RequestCoordinator
    public boolean canNotifyStatusChanged(Request request) {
        return c() && a(request);
    }

    @Override // com.ss.union.game.sdk.core.glide.request.RequestCoordinator
    public boolean canNotifyCleared(Request request) {
        return b() && a(request);
    }

    private boolean b() {
        return this.a == null || this.a.canNotifyCleared(this);
    }

    private boolean c() {
        return this.a == null || this.a.canNotifyStatusChanged(this);
    }

    private boolean a(Request request) {
        return request.equals(this.b) || (this.b.isFailed() && request.equals(this.c));
    }

    @Override // com.ss.union.game.sdk.core.glide.request.RequestCoordinator
    public boolean isAnyResourceSet() {
        return d() || isResourceSet();
    }

    private boolean d() {
        return this.a != null && this.a.isAnyResourceSet();
    }

    @Override // com.ss.union.game.sdk.core.glide.request.RequestCoordinator
    public void onRequestSuccess(Request request) {
        if (this.a != null) {
            this.a.onRequestSuccess(this);
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.request.RequestCoordinator
    public void onRequestFailed(Request request) {
        if (!request.equals(this.c)) {
            if (this.c.isRunning()) {
                return;
            }
            this.c.begin();
        } else if (this.a != null) {
            this.a.onRequestFailed(this);
        }
    }
}
