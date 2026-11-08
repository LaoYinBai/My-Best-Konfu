package com.ss.union.game.sdk.core.glide.manager;

import android.util.Log;
import com.ss.union.game.sdk.core.glide.request.Request;
import com.ss.union.game.sdk.core.glide.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RequestTracker {
    private static final String a = "RequestTracker";
    private final Set<Request> b = Collections.newSetFromMap(new WeakHashMap());
    private final List<Request> c = new ArrayList();
    private boolean d;

    public void runRequest(Request request) {
        this.b.add(request);
        if (!this.d) {
            request.begin();
            return;
        }
        request.clear();
        if (Log.isLoggable(a, 2)) {
            Log.v(a, "Paused, delaying request");
        }
        this.c.add(request);
    }

    void a(Request request) {
        this.b.add(request);
    }

    public boolean clearRemoveAndRecycle(Request request) {
        return a(request, true);
    }

    private boolean a(Request request, boolean z) {
        boolean z2 = true;
        if (request == null) {
            return true;
        }
        boolean zRemove = this.b.remove(request);
        if (!this.c.remove(request) && !zRemove) {
            z2 = false;
        }
        if (z2) {
            request.clear();
            if (z) {
                request.recycle();
            }
        }
        return z2;
    }

    public boolean isPaused() {
        return this.d;
    }

    public void pauseRequests() {
        this.d = true;
        for (Request request : Util.getSnapshot(this.b)) {
            if (request.isRunning()) {
                request.clear();
                this.c.add(request);
            }
        }
    }

    public void pauseAllRequests() {
        this.d = true;
        for (Request request : Util.getSnapshot(this.b)) {
            if (request.isRunning() || request.isComplete()) {
                request.clear();
                this.c.add(request);
            }
        }
    }

    public void resumeRequests() {
        this.d = false;
        for (Request request : Util.getSnapshot(this.b)) {
            if (!request.isComplete() && !request.isRunning()) {
                request.begin();
            }
        }
        this.c.clear();
    }

    public void clearRequests() {
        Iterator it = Util.getSnapshot(this.b).iterator();
        while (it.hasNext()) {
            a((Request) it.next(), false);
        }
        this.c.clear();
    }

    public void restartRequests() {
        for (Request request : Util.getSnapshot(this.b)) {
            if (!request.isComplete() && !request.isCleared()) {
                request.clear();
                if (!this.d) {
                    request.begin();
                } else {
                    this.c.add(request);
                }
            }
        }
    }

    public String toString() {
        return super.toString() + "{numRequests=" + this.b.size() + ", isPaused=" + this.d + "}";
    }
}
