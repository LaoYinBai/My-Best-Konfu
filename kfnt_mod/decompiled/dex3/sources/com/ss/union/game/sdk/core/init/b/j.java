package com.ss.union.game.sdk.core.init.b;

import com.ss.union.game.sdk.common.util.MainThreadExecutor;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.executeTime.impl.InitTime;
import com.ss.union.game.sdk.common.util.flow.FlowItem;
import com.ss.union.game.sdk.core.base.init.callback.LGRequestPermissionCallback;
import com.ss.union.game.sdk.core.init.fragment.PermissionFragment;
import com.ss.union.game.sdk.core.util.LogDevKitUtils;
import com.umeng.message.MsgConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class j extends FlowItem {
    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public String toString() {
        return "PermissionInit";
    }

    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public void doIt() {
        a();
    }

    private void a() {
        String[] stringArray = ResourceUtils.getStringArray("lg_permission_request", new String[]{MsgConstant.PERMISSION_READ_PHONE_STATE, MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE, "android.permission.READ_EXTERNAL_STORAGE", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"});
        LogDevKitUtils.logPermission("checkPermission: " + Arrays.toString(stringArray));
        if (stringArray == null || stringArray.length <= 0) {
            a(new ArrayList(), new ArrayList());
        } else {
            InitTime.getInstance().ignoreStart(InitTime.INIT_TIME_TAG);
            PermissionFragment.a(false, stringArray, new LGRequestPermissionCallback() { // from class: com.ss.union.game.sdk.core.init.b.j.1
                @Override // com.ss.union.game.sdk.core.base.init.callback.LGRequestPermissionCallback
                public void onRequestPermissionResult(List<String> list, List<String> list2) {
                    j.this.a(list, list2);
                    InitTime.getInstance().ignoreEnd(InitTime.INIT_TIME_TAG);
                }
            });
        }
    }

    public void a(List<String> list, List<String> list2) {
        MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.core.init.b.j.2
            @Override // java.lang.Runnable
            public void run() {
                j.this.finish();
            }
        });
    }
}
