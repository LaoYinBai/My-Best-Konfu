package a.a.a.a.a.a;

import android.app.Activity;
import com.bytedance.sdk.permission.dynamic_permission.callback.SingleDynamicPermissionCallback;
import com.bytedance.sdk.permission.dynamic_permission.entity.DynamicPermissionResult;
import com.bytedance.sdk.permission.dynamic_permission.entity.DynamicPermissionResultType;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e implements a.a.a.a.a.b.a {

    /* renamed from: a, reason: collision with root package name */
    public String f323a;

    /* renamed from: b, reason: collision with root package name */
    public SingleDynamicPermissionCallback f324b;

    public e(String str, SingleDynamicPermissionCallback singleDynamicPermissionCallback) {
        this.f323a = str;
        this.f324b = singleDynamicPermissionCallback;
    }

    @Override // a.a.a.a.a.b.a
    public void a(Activity activity, String[] strArr, int[] iArr) {
        if (this.f324b != null) {
            for (int i = 0; i < strArr.length; i++) {
                if (strArr[i].equals(this.f323a)) {
                    DynamicPermissionResult dynamicPermissionResult = iArr[i] == 0 ? new DynamicPermissionResult(this.f323a, DynamicPermissionResultType.GRANTED) : a.a.a.a.a.c.b.a(activity, strArr[i]) ? new DynamicPermissionResult(this.f323a, DynamicPermissionResultType.SELECTED_NO_PROMPTED) : new DynamicPermissionResult(this.f323a, DynamicPermissionResultType.UN_GRANTED);
                    SingleDynamicPermissionCallback singleDynamicPermissionCallback = this.f324b;
                    if (singleDynamicPermissionCallback != null) {
                        singleDynamicPermissionCallback.onResult(dynamicPermissionResult);
                        this.f324b = null;
                        return;
                    }
                    return;
                }
            }
        }
    }

    @Override // a.a.a.a.a.b.a
    public String[] a() {
        return new String[]{this.f323a};
    }
}
