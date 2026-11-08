package com.ss.union.game.sdk.core.init.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bytedance.sdk.permission.PermissionMaster;
import com.bytedance.sdk.permission.dynamic_permission.callback.DynamicPermissionCallback;
import com.bytedance.sdk.permission.dynamic_permission.entity.DynamicPermissionResult;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.common.mvp.BasePresenter;
import com.ss.union.game.sdk.common.util.ActivityUtils;
import com.ss.union.game.sdk.common.util.MainThreadExecutor;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.SPUtils;
import com.ss.union.game.sdk.common.util.SettingUtils;
import com.ss.union.game.sdk.core.base.event.PageStater;
import com.ss.union.game.sdk.core.base.init.callback.LGRequestPermissionCallback;
import com.ss.union.game.sdk.core.init.a.b;
import com.ss.union.game.sdk.core.util.LogDevKitUtils;
import com.umeng.message.MsgConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PermissionFragment extends BaseFragment<LGRequestPermissionCallback, BasePresenter> {
    public static final int a = 172800000;
    public static final String b = "key_skip_time_space";
    public static final String c = "key_target_permission";
    public static final int d = 10010;
    private static final String e = "PermissionRequest";
    private View f;
    private TextView g;
    private TextView h;
    private TextView i;
    private TextView j;
    private String[] l;
    private List<String> m;
    private List<String> n;
    private boolean k = false;
    private int o = -1;

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_fragment_permission";
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void loadData() {
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean onBackPressed() {
        return true;
    }

    public static boolean a(Context context, String str) {
        return PermissionMaster.createDynamicPermissionMaster().isGranted(context, str);
    }

    public static void a(boolean z, String[] strArr, LGRequestPermissionCallback lGRequestPermissionCallback) {
        a aVarB;
        Activity topActivity = ActivityUtils.getTopActivity();
        if (topActivity == null || strArr == null || strArr.length <= 0) {
            LogDevKitUtils.logPermission("startRequestPermission error IllegalArgumentException");
            if (lGRequestPermissionCallback != null) {
                lGRequestPermissionCallback.onRequestPermissionResult(new ArrayList(), Arrays.asList(strArr));
                return;
            }
            return;
        }
        if (z) {
            aVarB = a(topActivity, strArr);
        } else {
            aVarB = b(topActivity, strArr);
        }
        if (aVarB.a.size() > 0) {
            LogDevKitUtils.logPermission("startRequestPermission: " + aVarB.a);
            new OperationBuilder(b(z, (String[]) aVarB.a.toArray(new String[aVarB.a.size()]), lGRequestPermissionCallback)).animation(OperationBuilder.Animation.NONE).show();
            return;
        }
        LogDevKitUtils.logPermission("startRequestPermission There is no permission to request");
        if (lGRequestPermissionCallback != null) {
            lGRequestPermissionCallback.onRequestPermissionResult(aVarB.b, aVarB.c);
        }
    }

    private static a a(Activity activity, String[] strArr) {
        a aVar = new a();
        for (String str : strArr) {
            if (!PermissionMaster.createDynamicPermissionMaster().isGranted(activity, str)) {
                aVar.c.add(str);
                aVar.a.add(str);
            } else {
                aVar.b.add(str);
            }
        }
        return aVar;
    }

    private static a b(Activity activity, String[] strArr) {
        a aVar = new a();
        for (String str : strArr) {
            if (!PermissionMaster.createDynamicPermissionMaster().isGranted(activity, str)) {
                aVar.c.add(str);
                long jA = a(str);
                boolean z = jA != -1;
                boolean z2 = System.currentTimeMillis() - jA >= 172800000;
                if (z) {
                    if (z2 && a(activity, str)) {
                        aVar.a.add(str);
                    }
                } else {
                    aVar.a.add(str);
                }
            } else {
                aVar.b.add(str);
            }
        }
        return aVar;
    }

    private static boolean a(Activity activity, String str) {
        return !PermissionMaster.createDynamicPermissionMaster().isSelectedNoPrompt(activity, str);
    }

    private static class a {
        List<String> a;
        List<String> b;
        List<String> c;

        private a() {
            this.a = new ArrayList();
            this.b = new ArrayList();
            this.c = new ArrayList();
        }
    }

    private static PermissionFragment b(boolean z, String[] strArr, LGRequestPermissionCallback lGRequestPermissionCallback) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(b, z);
        bundle.putStringArray(c, strArr);
        PermissionFragment permissionFragment = new PermissionFragment();
        permissionFragment.setArguments(bundle);
        permissionFragment.setCallback(lGRequestPermissionCallback);
        return permissionFragment;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean initArgument(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        this.k = bundle.getBoolean(b, false);
        this.l = bundle.getStringArray(c);
        LogDevKitUtils.logPermission("PermissionFragment argument isSkipTime =" + this.k);
        LogDevKitUtils.logPermission("PermissionFragment argument permissions =" + Arrays.toString(this.l));
        return true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        this.f = findViewById("lg_request_permission_content");
        this.g = (TextView) findViewById("lg_request_permission_title");
        this.h = (TextView) findViewById("lg_request_permission_waring");
        this.i = (TextView) findViewById("lg_request_permission_allow");
        this.j = (TextView) findViewById("lg_request_permission_deny");
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() {
        if (this.f != null) {
            this.f.postDelayed(new Runnable() { // from class: com.ss.union.game.sdk.core.init.fragment.PermissionFragment.1
                @Override // java.lang.Runnable
                public void run() {
                    PermissionFragment.this.a();
                }
            }, 100L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        h();
        if (b()) {
            a(this.l);
        } else {
            e();
        }
    }

    private boolean b() {
        return this.l != null && this.l.length > 0;
    }

    private void a(String[] strArr) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        for (String str : strArr) {
            a(str, jCurrentTimeMillis);
        }
        PermissionMaster.createDynamicPermissionMaster().requestPermission(getActivity(), new DynamicPermissionCallback() { // from class: com.ss.union.game.sdk.core.init.fragment.PermissionFragment.2
            public void onResult(Map<String, DynamicPermissionResult> map, boolean z) {
                MainThreadExecutor.postDelayed(new Runnable() { // from class: com.ss.union.game.sdk.core.init.fragment.PermissionFragment.2.1
                    @Override // java.lang.Runnable
                    public void run() throws JSONException {
                        PermissionFragment.this.d();
                    }
                }, 100L);
            }
        }, strArr);
    }

    @Override // android.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) throws JSONException {
        super.onActivityResult(i, i2, intent);
        if (i == 10010) {
            d();
        }
    }

    private void c() throws JSONException {
        if (getActivity() == null) {
            return;
        }
        if (PermissionMaster.createDynamicPermissionMaster().isGranted(getActivity(), "android.permission.ACCESS_FINE_LOCATION")) {
            LogDevKitUtils.logPermission("PermissionFragment permission result TimeLimit Location GET");
            PageStater.V1.onEvent(b.a, b.b, "1");
        } else if (a(getActivity(), "android.permission.ACCESS_FINE_LOCATION")) {
            LogDevKitUtils.logPermission("PermissionFragment permission result TimeLimit Location DENY");
            PageStater.V1.onEvent(b.a, b.b, "2");
        } else {
            LogDevKitUtils.logPermission("PermissionFragment permission result TimeLimit Location FOREVER DENY");
            PageStater.V1.onEvent(b.a, b.b, "3");
        }
        if (PermissionMaster.createDynamicPermissionMaster().isGranted(getActivity(), "android.permission.READ_EXTERNAL_STORAGE") && PermissionMaster.createDynamicPermissionMaster().isGranted(getActivity(), MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE)) {
            LogDevKitUtils.logPermission("PermissionFragment permission result TimeLimit SD GET");
            PageStater.V1.onEvent(b.a, b.c, "1");
        } else if (a(getActivity(), "android.permission.READ_EXTERNAL_STORAGE") && a(getActivity(), MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE)) {
            LogDevKitUtils.logPermission("PermissionFragment permission result TimeLimit SD DENY");
            PageStater.V1.onEvent(b.a, b.c, "2");
        } else {
            LogDevKitUtils.logPermission("PermissionFragment permission result TimeLimit SD FOREVER DENY");
            PageStater.V1.onEvent(b.a, b.c, "3");
        }
        if (PermissionMaster.createDynamicPermissionMaster().isGranted(getActivity(), MsgConstant.PERMISSION_READ_PHONE_STATE)) {
            LogDevKitUtils.logPermission("PermissionFragment permission result TimeLimit phone GET");
            PageStater.V1.onEvent(b.a, b.d, "1");
        } else if (a(getActivity(), MsgConstant.PERMISSION_READ_PHONE_STATE)) {
            LogDevKitUtils.logPermission("PermissionFragment permission result TimeLimit phone DENY");
            PageStater.V1.onEvent(b.a, b.d, "2");
        } else {
            LogDevKitUtils.logPermission("PermissionFragment permission result TimeLimit phone FOREVER DENY");
            PageStater.V1.onEvent(b.a, b.d, "3");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0031, code lost:
    
        r4 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void d() throws org.json.JSONException {
        /*
            r8 = this;
            boolean r0 = r8.b()
            if (r0 == 0) goto L86
            boolean r0 = r8.k
            if (r0 == 0) goto L51
            java.lang.String[] r0 = r8.l
            int r1 = r0.length
            r2 = 0
            r3 = 0
        Lf:
            r4 = 1
            if (r3 >= r1) goto L31
            r5 = r0[r3]
            com.bytedance.sdk.permission.dynamic_permission.in.IDynamicPermission r6 = com.bytedance.sdk.permission.PermissionMaster.createDynamicPermissionMaster()
            android.app.Activity r7 = r8.getActivity()
            boolean r6 = r6.isGranted(r7, r5)
            if (r6 != 0) goto L2e
            android.app.Activity r0 = r8.getActivity()
            boolean r0 = a(r0, r5)
            if (r0 == 0) goto L32
            r2 = 1
            goto L31
        L2e:
            int r3 = r3 + 1
            goto Lf
        L31:
            r4 = 0
        L32:
            if (r2 == 0) goto L3d
            java.lang.String r0 = "PermissionFragment permission result show deny dialog"
            com.ss.union.game.sdk.core.util.LogDevKitUtils.logPermission(r0)
            r8.f()
            goto L89
        L3d:
            if (r4 == 0) goto L48
            java.lang.String r0 = "PermissionFragment permission result show forever deny dialog"
            com.ss.union.game.sdk.core.util.LogDevKitUtils.logPermission(r0)
            r8.g()
            goto L89
        L48:
            java.lang.String r0 = "PermissionFragment permission result get permission"
            com.ss.union.game.sdk.core.util.LogDevKitUtils.logPermission(r0)
            r8.e()
            goto L89
        L51:
            r8.c()
            com.bytedance.sdk.permission.dynamic_permission.in.IDynamicPermission r0 = com.bytedance.sdk.permission.PermissionMaster.createDynamicPermissionMaster()
            android.app.Activity r1 = r8.getActivity()
            java.lang.String r2 = "android.permission.READ_PHONE_STATE"
            boolean r0 = r0.isGranted(r1, r2)
            if (r0 != 0) goto L82
            android.app.Activity r0 = r8.getActivity()
            java.lang.String r1 = "android.permission.READ_PHONE_STATE"
            boolean r0 = a(r0, r1)
            if (r0 == 0) goto L79
            java.lang.String r0 = "PermissionFragment permission result TimeLimit show deny dialog"
            com.ss.union.game.sdk.core.util.LogDevKitUtils.logPermission(r0)
            r8.f()
            goto L89
        L79:
            java.lang.String r0 = "PermissionFragment permission result TimeLimit show forever deny dialog"
            com.ss.union.game.sdk.core.util.LogDevKitUtils.logPermission(r0)
            r8.g()
            goto L89
        L82:
            r8.e()
            goto L89
        L86:
            r8.e()
        L89:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.union.game.sdk.core.init.fragment.PermissionFragment.d():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogDevKitUtils.logPermission("PermissionFragment permission end");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (b()) {
            for (String str : this.l) {
                if (PermissionMaster.createDynamicPermissionMaster().isGranted(getActivity(), str)) {
                    arrayList.add(str);
                } else {
                    arrayList2.add(str);
                }
            }
        }
        this.m = arrayList;
        this.n = arrayList2;
        if (getCallback() != null) {
            getCallback().onRequestPermissionResult(this.m, this.n);
        }
        back();
    }

    private void f() throws JSONException {
        if (this.f != null) {
            PageStater.V1.onEvent(b.h, b.i);
            this.f.setVisibility(0);
            this.h.setText(ResourceUtils.getString("lg_request_permission_waring1"));
            this.i.setText(ResourceUtils.getString("lg_request_permission_allow"));
            this.i.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.init.fragment.PermissionFragment.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) throws JSONException {
                    PageStater.V1.onEvent(b.h, b.j);
                    PermissionFragment.this.a();
                }
            });
            this.j.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.init.fragment.PermissionFragment.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) throws JSONException {
                    PageStater.V1.onEvent(b.h, b.k);
                    PermissionFragment.this.e();
                }
            });
        }
    }

    private void g() throws JSONException {
        if (this.f != null) {
            PageStater.V1.onEvent(b.h, b.l);
            this.f.setVisibility(0);
            this.h.setText(ResourceUtils.getString("lg_request_permission_waring2"));
            this.i.setText(ResourceUtils.getString("lg_request_permission_go_setting"));
            this.i.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.init.fragment.PermissionFragment.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) throws JSONException {
                    PageStater.V1.onEvent(b.h, b.m);
                    SettingUtils.Permission.goToPermissionSettingPage(PermissionFragment.this, 10010);
                }
            });
            this.j.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.init.fragment.PermissionFragment.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) throws JSONException {
                    PageStater.V1.onEvent(b.h, b.n);
                    PermissionFragment.this.e();
                }
            });
        }
    }

    private void h() {
        if (this.f != null) {
            this.f.setVisibility(8);
        }
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment, android.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.o == configuration.orientation) {
            return;
        }
        this.o = configuration.orientation;
        ViewGroup.LayoutParams layoutParams = this.f.getLayoutParams();
        layoutParams.width = ResourceUtils.getDimen("lg_request_permission_container_width");
        this.f.setLayoutParams(layoutParams);
    }

    public void a(String str, long j) {
        try {
            SPUtils.getInstance("lg_init_config").put(str + "_time", j);
        } catch (Throwable unused) {
        }
    }

    private static long a(String str) {
        try {
            return SPUtils.getInstance("lg_init_config").getLong(str + "_time", -1L);
        } catch (Throwable unused) {
            return -1L;
        }
    }
}
