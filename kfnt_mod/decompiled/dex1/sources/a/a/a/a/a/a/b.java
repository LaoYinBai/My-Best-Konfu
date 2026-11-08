package a.a.a.a.a.a;

import android.app.Activity;
import android.content.Context;
import com.bytedance.sdk.permission.dynamic_permission.callback.DynamicPermissionCallback;
import com.bytedance.sdk.permission.dynamic_permission.callback.SingleDynamicPermissionCallback;
import com.bytedance.sdk.permission.dynamic_permission.entity.DynamicPermissionResult;
import com.bytedance.sdk.permission.dynamic_permission.entity.DynamicPermissionResultType;
import com.bytedance.sdk.permission.dynamic_permission.in.IDynamicPermission;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b implements IDynamicPermission {
    @Override // com.bytedance.sdk.permission.dynamic_permission.in.IDynamicPermission
    public boolean isGranted(Context context, String str) {
        return true;
    }

    @Override // com.bytedance.sdk.permission.dynamic_permission.in.IDynamicPermission
    public boolean isSelectedNoPrompt(Activity activity, String str) {
        return false;
    }

    @Override // com.bytedance.sdk.permission.dynamic_permission.in.IDynamicPermission
    public void requestPermission(Context context, DynamicPermissionCallback dynamicPermissionCallback, String... strArr) {
        if (dynamicPermissionCallback == null || strArr == null) {
            return;
        }
        HashMap map = new HashMap();
        for (String str : strArr) {
            map.put(str, new DynamicPermissionResult(str, DynamicPermissionResultType.GRANTED));
        }
        dynamicPermissionCallback.onResult(map, true);
    }

    @Override // com.bytedance.sdk.permission.dynamic_permission.in.IDynamicPermission
    public void requestPermission(Context context, String... strArr) {
        requestPermission(context, null, strArr);
    }

    @Override // com.bytedance.sdk.permission.dynamic_permission.in.IDynamicPermission
    public void requestSinglePermission(Context context, String str, SingleDynamicPermissionCallback singleDynamicPermissionCallback) {
        if (singleDynamicPermissionCallback != null) {
            singleDynamicPermissionCallback.onResult(new DynamicPermissionResult(str, DynamicPermissionResultType.GRANTED));
        }
    }
}
