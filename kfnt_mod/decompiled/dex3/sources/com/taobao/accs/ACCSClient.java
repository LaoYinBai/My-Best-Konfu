package com.taobao.accs;

import android.content.Context;
import android.text.TextUtils;
import anet.channel.SessionCenter;
import anet.channel.entity.ENV;
import com.sigmob.sdk.common.mta.PointCategory;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.AccsClientConfig;
import com.taobao.accs.base.AccsAbstractDataListener;
import com.taobao.accs.base.TaoBaseService;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.w;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ACCSClient {
    private static String TAG = "ACCSClient";
    public static Map<String, ACCSClient> mACCSClients = new ConcurrentHashMap(2);
    private static Context mContext;
    private String OTAG = TAG;
    protected c mAccsManager;
    private AccsClientConfig mConfig;

    @Deprecated
    public String getUserUnit() {
        return null;
    }

    public ACCSClient(AccsClientConfig accsClientConfig) {
        this.mConfig = accsClientConfig;
        this.OTAG += accsClientConfig.getTag();
        this.mAccsManager = ACCSManager.getAccsInstance(mContext, accsClientConfig.getAppKey(), accsClientConfig.getTag());
    }

    public static synchronized String init(Context context, String str) throws AccsException {
        AccsClientConfig configByTag;
        if (context != null) {
            if (!TextUtils.isEmpty(str)) {
                configByTag = AccsClientConfig.getConfigByTag(str);
                if (!AccsClientConfig.loadedStaticConfig) {
                    configByTag = new AccsClientConfig.Builder().setAppKey(str).build();
                    ALog.i(TAG, PointCategory.INIT, "create config, appkey as tag");
                }
            }
        }
        throw new AccsException("params error");
        return init(context, configByTag);
    }

    public static synchronized String init(Context context, AccsClientConfig accsClientConfig) throws AccsException {
        if (context == null || accsClientConfig == null) {
            throw new AccsException("init AccsClient params error");
        }
        if ((context.getApplicationInfo().flags & 2) != 0) {
            ALog.isUseTlog = false;
            anet.channel.util.ALog.setUseTlog(false);
        }
        mContext = context.getApplicationContext();
        GlobalClientInfo.a = context.getApplicationContext();
        ALog.d(TAG, PointCategory.INIT, "config", accsClientConfig);
        return accsClientConfig.getTag();
    }

    public static ACCSClient getAccsClient() throws AccsException {
        return getAccsClient(null);
    }

    public static synchronized ACCSClient getAccsClient(String str) throws AccsException {
        if (TextUtils.isEmpty(str)) {
            str = "default";
            ALog.w(TAG, "getAccsClient", "configTag is null, use default!");
        }
        ALog.i(TAG, "getAccsClient", Constants.KEY_CONFIG_TAG, str);
        AccsClientConfig configByTag = AccsClientConfig.getConfigByTag(str);
        if (configByTag == null) {
            ALog.e(TAG, "getAccsClient", "configTag not exist, please init first!!");
            throw new AccsException("configTag not exist");
        }
        ACCSClient aCCSClient = mACCSClients.get(str);
        if (aCCSClient == null) {
            ALog.d(TAG, "getAccsClient create client", new Object[0]);
            ACCSClient aCCSClient2 = new ACCSClient(configByTag);
            mACCSClients.put(str, aCCSClient2);
            aCCSClient2.updateConfig(configByTag);
            return aCCSClient2;
        }
        if (configByTag.equals(aCCSClient.mConfig)) {
            ALog.i(TAG, "getAccsClient exists", new Object[0]);
        } else {
            ALog.i(TAG, "getAccsClient update config", "old config", aCCSClient.mConfig.getTag(), "new config", configByTag.getTag());
            aCCSClient.updateConfig(configByTag);
        }
        return aCCSClient;
    }

    private void updateConfig(AccsClientConfig accsClientConfig) {
        this.mConfig = accsClientConfig;
        this.mAccsManager = ACCSManager.getAccsInstance(mContext, accsClientConfig.getAppKey(), accsClientConfig.getTag());
        if (this.mAccsManager != null) {
            this.mAccsManager.a(accsClientConfig);
        }
    }

    public static synchronized void setEnvironment(Context context, @AccsClientConfig.ENV int i) {
        if (context != null) {
            try {
                try {
                    if ((context.getApplicationInfo().flags & 2) != 0) {
                        ALog.isUseTlog = false;
                        anet.channel.util.ALog.setUseTlog(false);
                    }
                } catch (Throwable th) {
                    ALog.e(TAG, "setEnvironment", th, new Object[0]);
                }
            } catch (Throwable th2) {
                w.a(context, i);
                throw th2;
            }
        }
        if (i < 0 || i > 2) {
            ALog.e(TAG, "env error", "env", Integer.valueOf(i));
            i = 0;
        }
        int i2 = AccsClientConfig.mEnv;
        AccsClientConfig.mEnv = i;
        if (i2 != i && w.e(context)) {
            ALog.i(TAG, "setEnvironment", "preEnv", Integer.valueOf(i2), "toEnv", Integer.valueOf(i));
            w.c(context);
            w.f(context);
            w.d(context);
            if (i == 2) {
                SessionCenter.switchEnvironment(ENV.TEST);
            } else if (i == 1) {
                SessionCenter.switchEnvironment(ENV.PREPARE);
            }
            Iterator<Map.Entry<String, ACCSClient>> it = mACCSClients.entrySet().iterator();
            while (it.hasNext()) {
                try {
                    getAccsClient(it.next().getKey());
                } catch (AccsException e) {
                    ALog.e(TAG, "setEnvironment update client", e, new Object[0]);
                }
            }
        }
        w.a(context, i);
    }

    public void bindApp(String str, IAppReceiver iAppReceiver) {
        if (this.mAccsManager == null) {
            ALog.e(this.OTAG, "bindApp mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.a(mContext, this.mConfig.getAppKey(), this.mConfig.getAppSecret(), str, iAppReceiver);
        }
    }

    public void bindUser(String str) {
        if (this.mAccsManager == null) {
            ALog.e(this.OTAG, "bindUser mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.a(mContext, str);
        }
    }

    public void bindUser(String str, boolean z) {
        if (this.mAccsManager == null) {
            ALog.e(this.OTAG, "bindUser mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.a(mContext, str, z);
        }
    }

    public void unbindUser() {
        if (this.mAccsManager == null) {
            ALog.e(this.OTAG, "unbindUser mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.a(mContext);
        }
    }

    public void bindService(String str) {
        if (this.mAccsManager == null) {
            ALog.e(this.OTAG, "bindService mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.b(mContext, str);
        }
    }

    public void unbindService(String str) {
        if (this.mAccsManager == null) {
            ALog.e(this.OTAG, "unbindService mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.c(mContext, str);
        }
    }

    public String sendData(ACCSManager.AccsRequest accsRequest) {
        if (this.mAccsManager == null) {
            ALog.e(this.OTAG, "sendData mAccsManager null", new Object[0]);
            return null;
        }
        return this.mAccsManager.a(mContext, accsRequest);
    }

    public String sendRequest(ACCSManager.AccsRequest accsRequest) {
        if (this.mAccsManager == null) {
            ALog.e(this.OTAG, "sendRequest mAccsManager null", new Object[0]);
            return null;
        }
        return this.mAccsManager.b(mContext, accsRequest);
    }

    public String sendPushResponse(ACCSManager.AccsRequest accsRequest, TaoBaseService.ExtraInfo extraInfo) {
        if (this.mAccsManager == null) {
            ALog.e(this.OTAG, "sendPushResponse mAccsManager null", new Object[0]);
            return null;
        }
        return this.mAccsManager.a(mContext, accsRequest, extraInfo);
    }

    public boolean isNetworkReachable() {
        if (this.mAccsManager == null) {
            ALog.e(this.OTAG, "isNetworkReachable mAccsManager null", new Object[0]);
            return false;
        }
        return this.mAccsManager.b(mContext);
    }

    public void forceDisableService() {
        if (this.mAccsManager == null) {
            ALog.e(this.OTAG, "forceDisableService mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.c(mContext);
        }
    }

    public void forceEnableService() {
        if (this.mAccsManager == null) {
            ALog.e(this.OTAG, "forceEnableService mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.d(mContext);
        }
    }

    public void startInAppConnection(String str, IAppReceiver iAppReceiver) {
        if (this.mAccsManager == null) {
            ALog.e(this.OTAG, "startInAppConnection mAccsManager null", new Object[0]);
            return;
        }
        c cVar = this.mAccsManager;
        Context context = mContext;
        String appKey = this.mConfig.getAppKey();
        this.mConfig.getAppSecret();
        cVar.a(context, appKey, str, iAppReceiver);
    }

    public void setLoginInfo(ILoginInfo iLoginInfo) {
        if (this.mAccsManager == null) {
            ALog.e(this.OTAG, "setLoginInfo mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.a(mContext, iLoginInfo);
        }
    }

    public void clearLoginInfo() {
        if (this.mAccsManager == null) {
            ALog.e(this.OTAG, "clearLoginInfo mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.e(mContext);
        }
    }

    public boolean cancel(String str) {
        if (this.mAccsManager == null) {
            ALog.e(this.OTAG, "cancel mAccsManager null", new Object[0]);
            return false;
        }
        return this.mAccsManager.a(str);
    }

    public boolean isChannelError(int i) {
        if (this.mAccsManager == null) {
            ALog.e(this.OTAG, "isChannelError mAccsManager null", new Object[0]);
            return true;
        }
        return this.mAccsManager.a(i);
    }

    public Map<String, Boolean> getChannelState() throws Exception {
        if (this.mAccsManager == null) {
            ALog.e(this.OTAG, "getChannelState mAccsManager null", new Object[0]);
            return null;
        }
        return this.mAccsManager.b();
    }

    public Map<String, Boolean> forceReConnectChannel() throws Exception {
        if (this.mAccsManager == null) {
            ALog.e(this.OTAG, "forceReConnectChannel mAccsManager null", new Object[0]);
            return null;
        }
        return this.mAccsManager.c();
    }

    public void registerSerivce(String str, String str2) {
        if (this.mAccsManager == null) {
            ALog.e(this.OTAG, "registerSerivce mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.a(mContext, str, str2);
        }
    }

    public void unRegisterSerivce(String str) {
        if (this.mAccsManager == null) {
            ALog.e(this.OTAG, "unRegisterSerivce mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.d(mContext, str);
        }
    }

    public void registerDataListener(String str, AccsAbstractDataListener accsAbstractDataListener) {
        if (this.mAccsManager == null) {
            ALog.e(this.OTAG, "registerDataListener mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.a(mContext, str, accsAbstractDataListener);
        }
    }

    public void unRegisterDataListener(String str) {
        if (this.mAccsManager == null) {
            ALog.e(this.OTAG, "unRegisterDataListener mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.e(mContext, str);
        }
    }

    public void sendBusinessAck(String str, String str2, String str3, short s, String str4, Map<Integer, String> map) {
        if (this.mAccsManager == null) {
            ALog.e(this.OTAG, "sendBusinessAck mAccsManager null", new Object[0]);
        } else {
            this.mAccsManager.a(str, str2, str3, s, str4, map);
        }
    }

    public boolean isAccsConnected() {
        return this.mAccsManager != null && this.mAccsManager.a();
    }
}
