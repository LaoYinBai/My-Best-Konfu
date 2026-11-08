package a.a.a.a.a.a;

import android.app.Activity;
import com.bytedance.sdk.permission.dynamic_permission.callback.DynamicPermissionCallback;
import com.bytedance.sdk.permission.dynamic_permission.entity.DynamicPermissionResult;
import com.bytedance.sdk.permission.dynamic_permission.entity.DynamicPermissionResultType;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d implements a.a.a.a.a.b.a {

    /* renamed from: a, reason: collision with root package name */
    public String[] f321a;

    /* renamed from: b, reason: collision with root package name */
    public DynamicPermissionCallback f322b;

    public d(String[] strArr, DynamicPermissionCallback dynamicPermissionCallback) {
        this.f321a = strArr;
        this.f322b = dynamicPermissionCallback;
    }

    @Override // a.a.a.a.a.b.a
    public void a(Activity activity, String[] strArr, int[] iArr) {
        if (this.f322b != null) {
            HashMap map = new HashMap();
            boolean z = true;
            for (int i = 0; i < strArr.length; i++) {
                String str = strArr[i];
                if (iArr[i] == 0) {
                    map.put(str, new DynamicPermissionResult(str, DynamicPermissionResultType.GRANTED));
                } else {
                    map.put(str, a.a.a.a.a.c.b.a(activity, str) ? new DynamicPermissionResult(str, DynamicPermissionResultType.SELECTED_NO_PROMPTED) : new DynamicPermissionResult(str, DynamicPermissionResultType.UN_GRANTED));
                    z = false;
                }
            }
            this.f322b.onResult(map, z);
            this.f322b = null;
        }
    }

    @Override // a.a.a.a.a.b.a
    public String[] a() {
        return this.f321a;
    }
}
