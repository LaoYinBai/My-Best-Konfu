package com.ss.union.game.sdk.customerSystem;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.ss.union.game.sdk.common.net.CoreNetClient;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestGet;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.core.LGSDKCore;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.customerSystem.a.a;
import com.ss.union.game.sdk.customerSystem.callback.FindUnReadMessageCountCallback;
import com.ss.union.game.sdk.customerSystem.callback.OpenCustomerSystemCallback;
import com.ss.union.game.sdk.customerSystem.fragment.CustomerSystemFragment;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a implements ICustomerSystemService {
    private static final String a = "https://cs-sandbox.dailygn.com/customer_service/cp/reddot/get";
    private static final String b = "https://cs.snssdk.com/customer_service/cp/reddot/get";
    private static final String c = "LGCustomerSystemManager";
    private static final int d = 0;
    private static final long e = 60000;
    private static final long f = 120000;
    private static final long g = 120000;
    private static final long h = 600000;
    private long i;
    private b j;
    private HandlerThread k;
    private boolean l;
    private FindUnReadMessageCountCallback m;

    private a() {
        this.i = 120000L;
        this.l = false;
    }

    public static a a() {
        return C0094a.a;
    }

    @Override // com.ss.union.game.sdk.customerSystem.ICustomerSystemService
    public void openCustomerSystem(Activity activity, OpenCustomerSystemCallback openCustomerSystemCallback) {
        if (!LGSDKCore.isSdkInitSuccess()) {
            if (openCustomerSystemCallback != null) {
                openCustomerSystemCallback.onFail(-204, "SDK还未初始化完成");
            }
        } else if (!LGAccountDataUtil.isLogined()) {
            openCustomerSystemCallback.onFail(1, ResourceUtils.getString("lg_consumer_system_not_login", "未登录"));
        } else {
            CustomerSystemFragment.a();
            openCustomerSystemCallback.onSuc();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ss.union.game.sdk.customerSystem.ICustomerSystemService
    public void findUnReadMessageCount(final FindUnReadMessageCountCallback findUnReadMessageCountCallback) {
        ((ACoreRequestGet) ((ACoreRequestGet) ((ACoreRequestGet) CoreNetClient.get(a.C0095a.a()).urlParam("aid", AppIdManager.apAppID())).urlParam(User.KEY_OPEN_ID, LGAccountDataUtil.getCurrentUserOpenId())).urlParam("platform_type", "ohayoo")).json(new ICoreNetCallback<JSONObject, ACoreRequestGet>() { // from class: com.ss.union.game.sdk.customerSystem.a.1
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestGet aCoreRequestGet, CoreNetResponse<JSONObject, ACoreRequestGet> coreNetResponse) {
                super.onNetError(aCoreRequestGet, coreNetResponse);
                if (findUnReadMessageCountCallback != null) {
                    findUnReadMessageCountCallback.onFail(coreNetResponse.code(), coreNetResponse.message());
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestGet aCoreRequestGet, CoreNetResponse<JSONObject, ACoreRequestGet> coreNetResponse) {
                super.onNetSuccess(aCoreRequestGet, coreNetResponse);
                if (findUnReadMessageCountCallback == null) {
                    return;
                }
                try {
                    int iOptInt = coreNetResponse.data.optInt("code", -1);
                    if (iOptInt == 0) {
                        findUnReadMessageCountCallback.onUnreadMessageResult(coreNetResponse.data.getJSONObject("data").getInt("num"));
                    } else {
                        findUnReadMessageCountCallback.onFail(iOptInt, coreNetResponse.data.getString("message"));
                    }
                } catch (Throwable unused) {
                    if (findUnReadMessageCountCallback != null) {
                        findUnReadMessageCountCallback.onFail(ICoreNetCallback.ERROR_CODE_SERVER_ERROR, coreNetResponse.message());
                    }
                }
            }
        });
    }

    @Override // com.ss.union.game.sdk.customerSystem.ICustomerSystemService
    public void startPollingUnreadMessageCount(FindUnReadMessageCountCallback findUnReadMessageCountCallback) {
        this.m = findUnReadMessageCountCallback;
        if (this.l) {
            com.ss.union.game.sdk.c.a.c("startPollingUnreadMessageCount return,is Polling now");
            return;
        }
        if (this.k == null) {
            this.k = new HandlerThread("redNumberPull");
            this.k.start();
            this.j = new b(this.k.getLooper());
        }
        if (this.j == null) {
            this.j = new b(this.k.getLooper());
        }
        this.j.sendEmptyMessage(0);
        this.l = true;
    }

    @Override // com.ss.union.game.sdk.customerSystem.ICustomerSystemService
    public void stopPolling() {
        if (this.j != null) {
            this.j.removeCallbacksAndMessages(null);
            this.j = null;
        }
        if (this.k != null) {
            this.k.quit();
            this.k = null;
        }
        this.l = false;
    }

    @Override // com.ss.union.game.sdk.customerSystem.ICustomerSystemService
    public void setPollingUnreadMessageIntervalSecond(long j) {
        com.ss.union.game.sdk.c.a.c("setPollingUnreadMessageIntervalSecond: " + j);
        long j2 = j * 1000;
        if (j2 > 120000 && j2 < 600000) {
            this.i = j2;
        } else {
            this.i = 120000L;
        }
    }

    /* renamed from: com.ss.union.game.sdk.customerSystem.a$a, reason: collision with other inner class name */
    private static class C0094a {
        static a a = new a();

        private C0094a() {
        }
    }

    class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 0) {
                if (!LGAccountDataUtil.isLogined()) {
                    a.this.m.onFail(1, ResourceUtils.getString("lg_consumer_system_not_login", "用户未登录"));
                } else {
                    a.this.findUnReadMessageCount(new FindUnReadMessageCountCallback() { // from class: com.ss.union.game.sdk.customerSystem.a.b.1
                        @Override // com.ss.union.game.sdk.customerSystem.callback.FindUnReadMessageCountCallback
                        public void onUnreadMessageResult(int i) {
                            if (a.this.m != null) {
                                a.this.m.onUnreadMessageResult(i);
                            }
                        }

                        @Override // com.ss.union.game.sdk.customerSystem.callback.FindUnReadMessageCountCallback
                        public void onFail(int i, String str) {
                            if (a.this.m != null) {
                                a.this.m.onFail(i, str);
                            }
                        }
                    });
                }
                sendEmptyMessageDelayed(0, a.this.i);
            }
        }
    }
}
