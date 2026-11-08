package com.ss.android.downloadlib.am.m2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Pair;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.am.m2.IAidlService;
import com.ss.android.socialbase.appdownloader.util.DecryptUtils;
import com.umeng.commonsdk.proguard.o;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AidlClient {
    private static String ACTION = "";
    private static String MARKET = "";
    private static String PACKAGE = "";
    private static volatile AidlClient mInstance;
    private Context mContext;
    public IAidlService mServerClient;
    private boolean bSupportAidl = true;
    private boolean bindWaivePriority = false;
    private volatile boolean bindingService = false;
    private final List<Pair<AidlMsg, ICallback>> mRequestCache = new ArrayList();
    public final List<OnServiceConnectedChangeListener> mServiceConnectedChangeListeners = new ArrayList();
    private final ServiceConnection mServiceConnection = new ServiceConnection() { // from class: com.ss.android.downloadlib.am.m2.AidlClient.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            synchronized (AidlClient.this.syncObject) {
                AidlClient.this.setBindingService(false);
                AidlClient.this.mServerClient = IAidlService.Stub.asInterface(iBinder);
                AidlClient.this.doCachedRequest();
                Iterator<OnServiceConnectedChangeListener> it = AidlClient.this.mServiceConnectedChangeListeners.iterator();
                while (it.hasNext()) {
                    it.next().onConnected();
                }
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            synchronized (AidlClient.this.syncObject) {
                AidlClient.this.setBindingService(false);
                AidlClient.this.mServerClient = null;
                Iterator<OnServiceConnectedChangeListener> it = AidlClient.this.mServiceConnectedChangeListeners.iterator();
                while (it.hasNext()) {
                    it.next().onDisConnected();
                }
            }
        }
    };
    private String mSourceInfo = "";
    public final Object syncObject = new Object();

    public interface OnServiceConnectedChangeListener {
        void onConnected();

        void onDisConnected();
    }

    private AidlClient() {
    }

    public static AidlClient getInstance() {
        if (mInstance == null) {
            synchronized (AidlClient.class) {
                if (mInstance == null) {
                    mInstance = new AidlClient();
                }
            }
        }
        return mInstance;
    }

    public boolean init(Context context, boolean z) {
        if (TextUtils.isEmpty(ACTION)) {
            JSONObject downloadSettings = GlobalInfo.getDownloadSettings();
            String strOptString = downloadSettings.optString(o.at);
            ACTION = DecryptUtils.decrypt(downloadSettings.optString("q"), strOptString);
            MARKET = DecryptUtils.decrypt(downloadSettings.optString("u"), strOptString);
            PACKAGE = DecryptUtils.decrypt(downloadSettings.optString("w"), strOptString);
        }
        this.bindWaivePriority = z;
        if (context == null) {
            return true;
        }
        this.mContext = context.getApplicationContext();
        if (TextUtils.isEmpty(PACKAGE)) {
            PACKAGE = this.mContext.getPackageName();
        }
        if (this.mServerClient != null || isBindingService()) {
            return true;
        }
        return this.mContext.bindService(getExplicitIntent(context), this.mServiceConnection, 33);
    }

    public void onDestroy() {
        if (this.mServerClient != null) {
            this.mContext.unbindService(this.mServiceConnection);
            this.mServerClient = null;
        }
        this.mServiceConnectedChangeListeners.clear();
        this.mRequestCache.clear();
    }

    public Intent getExplicitIntent(Context context) {
        Intent intent = new Intent();
        intent.setAction(ACTION);
        List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (listQueryIntentServices == null || listQueryIntentServices.size() != 1) {
            return null;
        }
        for (ResolveInfo resolveInfo : listQueryIntentServices) {
            String str = resolveInfo.serviceInfo.packageName;
            String str2 = resolveInfo.serviceInfo.name;
            if (MARKET.equals(str)) {
                ComponentName componentName = new ComponentName(str, str2);
                Intent intent2 = new Intent(intent);
                intent2.setComponent(componentName);
                return intent2;
            }
        }
        return null;
    }

    public void doAction(AidlMsg aidlMsg, ICallback iCallback) {
        synchronized (this.syncObject) {
            aidlMsg.sourceApk = PACKAGE;
            if (TextUtils.isEmpty(aidlMsg.sourceApkInfo)) {
                aidlMsg.sourceApkInfo = this.mSourceInfo;
            }
            if (this.mServerClient != null) {
                try {
                    this.mServerClient.doAction(aidlMsg, iCallback);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else if (isBindingService() || init(this.mContext, this.bindWaivePriority)) {
                this.mRequestCache.add(Pair.create(aidlMsg, iCallback));
            }
        }
    }

    public void doCachedRequest() {
        for (Pair<AidlMsg, ICallback> pair : this.mRequestCache) {
            try {
                this.mServerClient.doAction((AidlMsg) pair.first, (ICallback) pair.second);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        this.mRequestCache.clear();
    }

    public boolean isBindingService() {
        return this.bindingService;
    }

    public void setBindingService(boolean z) {
        this.bindingService = z;
    }
}
