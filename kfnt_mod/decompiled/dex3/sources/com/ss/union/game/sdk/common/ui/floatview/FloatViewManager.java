package com.ss.union.game.sdk.common.ui.floatview;

import com.ss.union.game.sdk.common.util.reflect.Reflect;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class FloatViewManager {
    private static FloatViewManager a = new FloatViewManager();
    private Map<Class<? extends BaseFloatView>, BaseFloatView> b = new HashMap();

    public static FloatViewManager getInstance() {
        return a;
    }

    public <T extends BaseFloatView> T create(FloatIntent floatIntent) {
        T t = (T) Reflect.onClass(floatIntent.targetClass).create().get();
        t.attachIntent(floatIntent);
        t.onCreateView();
        t.onViewCreated();
        return t;
    }

    public IFloatView get(Class<? extends IFloatView> cls) {
        return this.b.get(cls);
    }

    public void destroy(Class<? extends IFloatView> cls) {
        BaseFloatView baseFloatView = this.b.get(cls);
        if (baseFloatView == null) {
            return;
        }
        baseFloatView.onDestroyed();
        this.b.remove(cls);
    }

    public void destroyAll() {
        Iterator<Map.Entry<Class<? extends BaseFloatView>, BaseFloatView>> it = this.b.entrySet().iterator();
        while (it.hasNext()) {
            destroy(it.next().getKey());
        }
        this.b.clear();
    }

    static class a implements IFloatView {
        private WeakReference<IFloatView> a;

        public a(IFloatView iFloatView) {
            this.a = new WeakReference<>(iFloatView);
        }

        public <T extends IFloatView> T a() {
            return (T) this.a.get();
        }

        @Override // com.ss.union.game.sdk.common.ui.floatview.IFloatView
        public void onResumed() {
            if (this.a == null || this.a.get() == null) {
                return;
            }
            this.a.get().onResumed();
        }

        @Override // com.ss.union.game.sdk.common.ui.floatview.IFloatView
        public void onPaused() {
            if (this.a == null || this.a.get() == null) {
                return;
            }
            this.a.get().onPaused();
        }

        @Override // com.ss.union.game.sdk.common.ui.floatview.IFloatView
        public void onDestroyed() {
            if (this.a == null || this.a.get() == null) {
                return;
            }
            this.a.get().onDestroyed();
        }

        @Override // com.ss.union.game.sdk.common.ui.floatview.IFloatView
        public boolean isDestroyed() {
            if (this.a == null || this.a.get() == null) {
                return false;
            }
            return this.a.get().isDestroyed();
        }
    }
}
