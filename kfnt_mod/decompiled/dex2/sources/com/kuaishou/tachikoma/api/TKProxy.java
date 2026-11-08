package com.kuaishou.tachikoma.api;

import androidx.annotation.Nullable;
import com.kwad.v8.V8Array;
import com.kwad.v8.V8Value;
import com.tachikoma.core.utility.V8Proxy;
import com.tachikoma.core.yoga.layout.YogaLayout;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class TKProxy {
    public static V8Array createV8Array(V8Value v8Value, String... strArr) {
        return V8Proxy.createV8Array(v8Value, strArr);
    }

    @Nullable
    public static Object getAssociateObject(List<Object> list) {
        return V8Proxy.getAssociateJsObject(list);
    }

    public static TKContext getTKContext(List<Object> list) {
        return new TKContext(V8Proxy.getTKContext(list));
    }

    public static void release(V8Value v8Value) {
        V8Proxy.release(v8Value);
    }

    public static boolean setYogaLayoutHeight(Object obj, float f) {
        if (!(obj instanceof YogaLayout)) {
            return false;
        }
        YogaLayout yogaLayout = (YogaLayout) obj;
        if (yogaLayout.getYogaNode() == null) {
            return true;
        }
        yogaLayout.getYogaNode().setHeight(f);
        return true;
    }

    public static boolean setYogaLayoutHeightAuto(Object obj) {
        if (!(obj instanceof YogaLayout)) {
            return false;
        }
        YogaLayout yogaLayout = (YogaLayout) obj;
        if (yogaLayout.getYogaNode() == null) {
            return true;
        }
        yogaLayout.getYogaNode().setHeightAuto();
        return true;
    }

    public static boolean setYogaLayoutWidth(Object obj, float f) {
        if (!(obj instanceof YogaLayout)) {
            return false;
        }
        YogaLayout yogaLayout = (YogaLayout) obj;
        if (yogaLayout.getYogaNode() == null) {
            return true;
        }
        yogaLayout.getYogaNode().setWidth(f);
        return true;
    }

    public static boolean setYogaLayoutWidthAuto(Object obj) {
        if (!(obj instanceof YogaLayout)) {
            return false;
        }
        YogaLayout yogaLayout = (YogaLayout) obj;
        if (yogaLayout.getYogaNode() == null) {
            return true;
        }
        yogaLayout.getYogaNode().setWidthAuto();
        return true;
    }
}
