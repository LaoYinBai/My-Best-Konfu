package com.facebook.stetho.server.http;

import androidx.annotation.Nullable;
import java.util.ArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class HandlerRegistry {
    private final ArrayList<PathMatcher> mPathMatchers = new ArrayList<>();
    private final ArrayList<HttpHandler> mHttpHandlers = new ArrayList<>();

    public synchronized void register(PathMatcher pathMatcher, HttpHandler httpHandler) {
        this.mPathMatchers.add(pathMatcher);
        this.mHttpHandlers.add(httpHandler);
    }

    public synchronized boolean unregister(PathMatcher pathMatcher, HttpHandler httpHandler) {
        int iIndexOf = this.mPathMatchers.indexOf(pathMatcher);
        if (iIndexOf < 0 || httpHandler != this.mHttpHandlers.get(iIndexOf)) {
            return false;
        }
        this.mPathMatchers.remove(iIndexOf);
        this.mHttpHandlers.remove(iIndexOf);
        return true;
    }

    @Nullable
    public synchronized HttpHandler lookup(String str) {
        int size = this.mPathMatchers.size();
        for (int i = 0; i < size; i++) {
            if (this.mPathMatchers.get(i).match(str)) {
                return this.mHttpHandlers.get(i);
            }
        }
        return null;
    }
}
