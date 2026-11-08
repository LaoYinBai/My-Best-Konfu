package com.umeng.message.inapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.common.UmengMessageDeviceConfig;
import com.umeng.message.entity.UInAppMessage;
import com.umeng.message.inapp.UImageLoadTask;
import com.umeng.message.proguard.h;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Calendar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class b implements IUmengInAppMessageCallback, UImageLoadTask.ImageLoaderCallback {
    private static final String a = "com.umeng.message.inapp.b";
    private static final int f = 10;
    private Context b;
    private String c;
    private boolean d;
    private UInAppMessage e;
    private IUmengInAppMsgCloseCallback g;

    @Override // com.umeng.message.inapp.IUmengInAppMessageCallback
    public void onSplashMessage(UInAppMessage uInAppMessage) {
    }

    public b(Activity activity, String str, IUmengInAppMsgCloseCallback iUmengInAppMsgCloseCallback) {
        this.b = activity;
        this.c = str;
        this.g = iUmengInAppMsgCloseCallback;
    }

    public b(Context context, String str) {
        this.b = context.getApplicationContext();
        this.c = str;
        this.d = true;
    }

    private boolean a(String str) {
        if (!UmengMessageDeviceConfig.getAppVersionCode(this.b).equals(InAppMessageManager.getInstance(this.b).g())) {
            InAppMessageManager.getInstance(this.b).d("");
        }
        InAppMessageManager.getInstance(this.b).e(UmengMessageDeviceConfig.getAppVersionCode(this.b));
        String strF = InAppMessageManager.getInstance(this.b).f();
        JSONArray jSONArray = null;
        if (!TextUtils.isEmpty(strF)) {
            try {
                jSONArray = new JSONArray(strF);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (jSONArray == null) {
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.put(str);
            InAppMessageManager.getInstance(this.b).d(jSONArray2.toString());
            return true;
        }
        if (a(jSONArray, str)) {
            return true;
        }
        if (jSONArray.length() >= 10) {
            return false;
        }
        jSONArray.put(str);
        InAppMessageManager.getInstance(this.b).d(jSONArray.toString());
        return true;
    }

    private boolean a(JSONArray jSONArray, String str) {
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                if (jSONArray.getString(i).equals(str)) {
                    return true;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    void a() {
        if (TextUtils.isEmpty(this.c.trim())) {
            if (PushAgent.DEBUG) {
                Toast.makeText(this.b, "插屏消息的标签不能为空", 1).show();
            }
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 0, "插屏消息的标签不能为空");
            return;
        }
        if (a(this.c)) {
            if (InAppMessageManager.a) {
                d.a(this.b).a(this.c, this);
                return;
            } else if (System.currentTimeMillis() - InAppMessageManager.getInstance(this.b).b(this.c) > InAppMessageManager.b) {
                d.a(this.b).a(this.c, this);
                return;
            } else {
                onCardMessage(null);
                return;
            }
        }
        UMLog uMLog2 = UMConfigure.umDebugLog;
        UMLog.mutlInfo(a, 0, "插屏消息的最大标签数为 10");
    }

    private void a(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            UmengCardMessage umengCardMessage = new UmengCardMessage();
            umengCardMessage.a(this.g);
            Bundle bundle = new Bundle();
            bundle.putString(MsgConstant.INAPP_LABEL, this.c);
            bundle.putString("msg", this.e.getRaw().toString());
            bundle.putByteArray("bitmapByte", byteArray);
            umengCardMessage.setArguments(bundle);
            umengCardMessage.show(((Activity) this.b).getFragmentManager(), this.c);
            InAppMessageManager.getInstance(this.b).a(this.e.msg_id, 1);
            InAppMessageManager.getInstance(this.b).f(this.c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void b() {
        try {
            UmengCardMessage umengCardMessage = new UmengCardMessage();
            umengCardMessage.a(this.g);
            Bundle bundle = new Bundle();
            bundle.putString(MsgConstant.INAPP_LABEL, this.c);
            bundle.putString("msg", this.e.getRaw().toString());
            umengCardMessage.setArguments(bundle);
            umengCardMessage.show(((Activity) this.b).getFragmentManager(), this.c);
            InAppMessageManager.getInstance(this.b).a(this.e.msg_id, 1);
            InAppMessageManager.getInstance(this.b).f(this.c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.umeng.message.inapp.IUmengInAppMessageCallback
    public void onCardMessage(UInAppMessage uInAppMessage) {
        UInAppMessage uInAppMessage2;
        String strC = InAppMessageManager.getInstance(this.b).c(this.c);
        if (TextUtils.isEmpty(strC)) {
            uInAppMessage2 = null;
        } else {
            try {
                uInAppMessage2 = new UInAppMessage(new JSONObject(strC));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (uInAppMessage != null) {
            if (uInAppMessage2 != null && !uInAppMessage.msg_id.equals(uInAppMessage2.msg_id)) {
                InAppMessageManager.getInstance(this.b).a(new File(h.d(this.b, uInAppMessage2.msg_id)));
            }
            this.e = uInAppMessage;
        } else if (uInAppMessage2 == null) {
            return;
        } else {
            this.e = uInAppMessage2;
        }
        if (this.e.show_type == 1 && !b(this.c)) {
            InAppMessageManager.getInstance(this.b).a(this.e.msg_id, 0);
        }
        if (InAppMessageManager.getInstance(this.b).b(this.e) && InAppMessageManager.getInstance(this.b).c(this.e)) {
            if (this.e.msg_type == 5 || this.e.msg_type == 6) {
                InAppMessageManager.getInstance(this.b).a(this.e, this.c);
                b();
            } else {
                UImageLoadTask uImageLoadTask = new UImageLoadTask(this.b, this.e);
                uImageLoadTask.a(this);
                uImageLoadTask.execute(this.e.image_url);
            }
        }
    }

    @Override // com.umeng.message.inapp.UImageLoadTask.ImageLoaderCallback
    public void onLoadImage(Bitmap[] bitmapArr) {
        if (!this.d) {
            a(bitmapArr[0]);
        }
        InAppMessageManager.getInstance(this.b).a(this.e, this.c);
    }

    private boolean b(String str) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(InAppMessageManager.getInstance(this.b).g(str));
        Calendar calendar2 = Calendar.getInstance();
        return calendar.get(6) == calendar2.get(6) && calendar.get(1) == calendar2.get(1);
    }
}
