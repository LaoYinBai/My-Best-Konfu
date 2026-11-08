package com.tachikoma.core.module;

import android.content.Context;
import android.util.Log;
import com.kwad.v8.V8Function;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.bridge.TKJSContext;
import com.tachikoma.core.module.handler.IBusinessSchoolBridge;
import com.tachikoma.core.utility.V8Proxy;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKBusinessSchoolBridge implements IBusinessSchoolBridge {
    public static final String TAG = "TKBusinessSchoolBridge";
    private TKJSContext mTKJSContext;

    public TKBusinessSchoolBridge(Context context, List<Object> list) {
        this.mTKJSContext = V8Proxy.getTKContext(list);
    }

    private IBusinessSchoolBridge getBridge() {
        return (IBusinessSchoolBridge) this.mTKJSContext.getHandler(IBusinessSchoolBridge.class);
    }

    @Override // com.tachikoma.core.module.handler.IBusinessSchoolBridge
    public void getBusinessCourseFeedList(V8Function v8Function, String str, String str2) {
        IBusinessSchoolBridge bridge = getBridge();
        if (bridge != null) {
            bridge.getBusinessCourseFeedList(v8Function, str, str2);
        } else {
            Log.e(TAG, "bridge is null");
        }
    }

    @Override // com.tachikoma.core.module.handler.IBusinessSchoolBridge
    public void getBusinessCourseInfo(V8Function v8Function) {
        IBusinessSchoolBridge bridge = getBridge();
        if (bridge != null) {
            bridge.getBusinessCourseInfo(v8Function);
        } else {
            Log.e(TAG, "bridge is null");
        }
    }

    @Override // com.tachikoma.core.module.handler.IBusinessSchoolBridge
    public void navigateNativePage(String str, Object obj) {
        IBusinessSchoolBridge bridge = getBridge();
        if (bridge != null) {
            bridge.navigateNativePage(str, obj);
        } else {
            Log.e(TAG, "bridge is null");
        }
    }
}
