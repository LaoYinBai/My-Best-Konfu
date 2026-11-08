package com.yxcorp.kuaishou.addfp;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import com.umeng.message.MsgConstant;
import com.yxcorp.kuaishou.addfp.android.Orange;
import com.yxcorp.kuaishou.addfp.android.a.c;
import com.yxcorp.kuaishou.addfp.android.a.d;
import com.yxcorp.kuaishou.addfp.android.a.e;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class KWEGIDDFP {
    private static final int EGID_LENGTH = 64;
    public static final int ERROR_TYPE_ACCESS_EXPTION = -1;
    public static final int ERROR_TYPE_NULL = -3;
    public static final int ERROR_TYPE_PROCESS = -4;
    public static final int ERROR_TYPE_UNKNOW = -2;
    private static int PROCESSALLOW = -1;
    public static String mOAID = "KWE_NS";
    private ResponseDfpCallback mCallBack;
    private String mEgid;
    private String mLocal;
    private Context mParamContext;
    private String mPkgName;
    private boolean mUserAgree;

    private KWEGIDDFP() {
        this.mPkgName = "";
        this.mEgid = "";
        this.mLocal = "";
        this.mUserAgree = false;
    }

    /* synthetic */ KWEGIDDFP(a aVar) {
        this();
    }

    public static String doSign(Context context, String str) {
        return Orange.getInstance().getClockWrapper(context, str.getBytes(), 20);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getEGid(ResponseDfpCallback responseDfpCallback) {
        try {
            boolean zA = new e(this.mParamContext).a();
            initOAIDImpl(this.mParamContext);
            getEGidImpl(zA);
            this.mLocal = com.yxcorp.kuaishou.addfp.android.a.a.a().a(this.mParamContext, responseDfpCallback, this.mUserAgree, TextUtils.isEmpty(this.mEgid));
            if (responseDfpCallback == null) {
                return;
            }
            if (TextUtils.isEmpty(this.mEgid) && (TextUtils.isEmpty(this.mLocal) || this.mLocal.startsWith("KWE"))) {
                if (this.mLocal.equals("KWE_PE")) {
                    return;
                }
                responseDfpCallback.onFailed(-2, "need check");
                return;
            }
            responseDfpCallback.onSuccess(this.mEgid, this.mLocal);
        } catch (Throwable th) {
            if (responseDfpCallback != null) {
                try {
                    responseDfpCallback.onFailed(-1, com.yxcorp.kuaishou.addfp.android.b.e.a(th));
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }
        }
    }

    private void getEGidImpl(boolean z) {
        try {
            Pair pairA = c.a(this.mParamContext).a();
            if (pairA != null && pairA.second != null) {
                String str = (String) ((ConcurrentHashMap) pairA.second).get("cache_e");
                if (!TextUtils.isEmpty(str)) {
                    this.mEgid = str;
                }
            }
            if (TextUtils.isEmpty(this.mEgid)) {
                try {
                    String strA = Build.VERSION.SDK_INT < 29 ? d.a(this.mParamContext, "k_w_o_d_out_dtt") : null;
                    String strB = "";
                    if (TextUtils.isEmpty(strA)) {
                        com.yxcorp.kuaishou.addfp.android.b.b.a();
                        if (com.yxcorp.kuaishou.addfp.android.b.e.b(this.mParamContext, new String[]{"android.permission.READ_EXTERNAL_STORAGE", MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE})) {
                            strB = c.a(this.mParamContext).b("Lm91a2R0ZnQ=");
                        }
                    }
                    if (TextUtils.isEmpty(strA) && TextUtils.isEmpty(strB)) {
                        return;
                    }
                    LinkedHashMap linkedHashMapA = c.a(this.mParamContext).a(strA);
                    if (linkedHashMapA == null || linkedHashMapA.size() == 0) {
                        linkedHashMapA = c.a(this.mParamContext).a(strB);
                    }
                    if (linkedHashMapA == null || linkedHashMapA.size() <= 0) {
                        return;
                    }
                    String str2 = (String) linkedHashMapA.get(com.yxcorp.kuaishou.addfp.android.a.a.a().b());
                    if (TextUtils.isEmpty(str2)) {
                        return;
                    }
                    this.mEgid = str2;
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    private void initOAIDImpl(Context context) {
        try {
            com.yxcorp.kuaishou.addfp.a.a.b.a().a(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static KWEGIDDFP instance() {
        return b.a;
    }

    public void getEGidByCallback(Context context, boolean z, ResponseDfpCallback responseDfpCallback) {
        try {
            this.mCallBack = responseDfpCallback;
            this.mParamContext = context;
            this.mUserAgree = z;
            Thread thread = new Thread(new a(this));
            thread.setName("adsence-dfp");
            thread.start();
        } catch (Throwable th) {
            String strA = com.yxcorp.kuaishou.addfp.android.b.e.a(th);
            if (responseDfpCallback != null) {
                responseDfpCallback.onFailed(-1, strA);
            }
        }
    }

    public String getEGidLocal(Context context, String str, boolean z) {
        if (TextUtils.isEmpty(this.mEgid)) {
            return null;
        }
        return this.mEgid;
    }

    public boolean setEgid(Context context, String str) {
        if (TextUtils.isEmpty(str) || !str.startsWith("DFP") || str.length() != 64) {
            return false;
        }
        c.a(context).a(str, "");
        return true;
    }

    public void setLog(boolean z) {
    }
}
