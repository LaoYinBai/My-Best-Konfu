package com.um;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.push.i;
import com.bytedance.push.third.IPushAdapter;
import com.bytedance.push.third.e;
import com.bytedance.push.utils.h;
import com.sigmob.sdk.common.Constants;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.ss.android.message.a;
import com.ss.android.push.b;
import com.taobao.accs.client.GlobalConfig;
import com.umeng.UmengMessageHandler;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengCallback;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.entity.UMessage;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UmPushAdapter implements IPushAdapter {
    private static int UM_PUSH = -1;
    private IUmengRegisterCallback mUmengRegisterCallback;

    public boolean isPushAvailable(Context context, int i) {
        return true;
    }

    public boolean requestNotificationPermission(int i) {
        return false;
    }

    public static int getUmPush() {
        if (UM_PUSH == -1) {
            UM_PUSH = e.a(a.a()).a(UmPushAdapter.class.getName());
        }
        return UM_PUSH;
    }

    public void registerPush(final Context context, final int i) {
        if (context == null || i != getUmPush()) {
            String str = context == null ? "空 context" : "通道注册错误";
            if (i.m().a(context)) {
                i.o().a(i, 101, Constants.FAIL, str);
                return;
            }
            return;
        }
        try {
            if (22 == Build.VERSION.SDK_INT && h.a()) {
                GlobalConfig.setEnableForground(context, false);
            }
            i.l().c("UmengPush", "registerUmengPush");
            initUmeng(context);
            final PushAgent pushAgent = PushAgent.getInstance(context);
            if (i.m().a(context)) {
                pushAgent.setPushIntentServiceClass(UmengMessageHandler.class);
            }
            this.mUmengRegisterCallback = new IUmengRegisterCallback() { // from class: com.um.UmPushAdapter.1
                @Override // com.umeng.message.IUmengRegisterCallback
                public void onSuccess(String str2) {
                    if (i.m().a(context)) {
                        try {
                            pushAgent.enable(new IUmengCallback() { // from class: com.um.UmPushAdapter.1.1
                                @Override // com.umeng.message.IUmengCallback
                                public void onSuccess() {
                                    i.l().c("UmengPush", "enable onSuccess ");
                                }

                                @Override // com.umeng.message.IUmengCallback
                                public void onFailure(String str3, String str4) {
                                    i.l().c("UmengPush", "enable onFailure " + str3 + " " + str4);
                                    i.o().a(i, 104, "2", "s = " + str3 + " ,s1 = " + str4);
                                }
                            });
                        } catch (Throwable th) {
                            th.printStackTrace();
                            i.o().a(i, 104, "3", Log.getStackTraceString(th));
                        }
                    }
                    try {
                        i.l().c("UmengPush", "register onSuccess " + str2);
                        pushAgent.onAppStart();
                        if (!TextUtils.isEmpty(str2)) {
                            i.m().a(context, UmPushAdapter.getUmPush(), str2);
                        } else if (i.m().a(context)) {
                            i.o().a(i, 102, Constants.FAIL, "token 为空");
                        }
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                        if (i.m().a(context)) {
                            i.o().a(i, 104, DownloadConstants.DOWNLOAD_FINISH_REASON_SHOW_DIALOG_ERROR, Log.getStackTraceString(th2));
                        }
                    }
                }

                @Override // com.umeng.message.IUmengRegisterCallback
                public void onFailure(String str2, String str3) {
                    if (i.m().a(context)) {
                        i.o().a(i, 104, "1", "s = " + str2 + " ,s1 = " + str3);
                    }
                    try {
                        i.l().b("UmengPush", "register onFailure " + str2 + " " + str3);
                        StringBuilder sb = new StringBuilder();
                        sb.append(str2);
                        sb.append(" ");
                        sb.append(str3);
                        i.m().a(UmPushAdapter.getUmPush(), str2, sb.toString());
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            };
            pushAgent.register(this.mUmengRegisterCallback);
        } catch (Throwable th) {
            th.printStackTrace();
            if (i.m().a(context)) {
                i.o().a(i, 104, Constants.FAIL, Log.getStackTraceString(th));
            }
        }
    }

    private void initUmeng(Context context) {
        b bVarA = i.m().a();
        String str = (String) bVarA.f();
        String str2 = (String) bVarA.e();
        String str3 = (String) bVarA.d();
        UMConfigure.setLogEnabled(i.l().a());
        UMConfigure.setEncryptEnabled(false);
        UMConfigure.init(context, str, str3, 1, str2);
    }

    public void setAlias(Context context, String str, int i) {
        if (context == null || i != getUmPush()) {
            return;
        }
        try {
            i.l().c("UmengPush", "setAlias");
            PushAgent pushAgent = PushAgent.getInstance(context.getApplicationContext());
            if (pushAgent != null) {
                pushAgent.addAlias(str, context.getPackageName(), new UTrack.ICallBack() { // from class: com.um.UmPushAdapter.2
                    @Override // com.umeng.message.UTrack.ICallBack
                    public void onMessage(boolean z, String str2) {
                        i.l().c("UmengPush", "addAlias onMessage " + z + " " + str2);
                    }
                });
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void unregisterPush(Context context, int i) {
        if (context == null || i != getUmPush()) {
            return;
        }
        try {
            i.l().c("UmengPush", "unregisterPush");
            initUmeng(context);
            PushAgent.getInstance(context).disable(new IUmengCallback() { // from class: com.um.UmPushAdapter.3
                @Override // com.umeng.message.IUmengCallback
                public void onSuccess() {
                    i.l().c("UmengPush", "turnOffPush onSuccess ");
                }

                @Override // com.umeng.message.IUmengCallback
                public void onFailure(String str, String str2) {
                    i.l().b("UmengPush", "turnOffPush onFailure " + str + " " + str2);
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void trackPush(Context context, int i, Object obj) {
        if (i == getUmPush() && (obj instanceof String)) {
            try {
                UTrack.getInstance(context).trackMsgClick(new UMessage(new JSONObject((String) obj)));
            } catch (Throwable unused) {
            }
        }
    }

    public boolean checkThirdPushConfig(String str, Context context) throws Exception {
        return ManifestChecker.checkManifest(str, context);
    }
}
