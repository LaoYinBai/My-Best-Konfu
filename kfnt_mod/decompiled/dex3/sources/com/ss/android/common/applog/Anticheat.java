package com.ss.android.common.applog;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Anticheat implements IAnticheat {
    public static final int INIT_RETRY_API_DELAY = 10000;
    public static final int MAX_RETRY_API_DELAY = 32000;
    private static final String TAG = "Anticheat";
    private static Anticheat inst;
    private IClient iClient;
    private boolean inUpdateProgress;
    private String mEstr;
    private Handler mHandler;
    private int mUpdateduration;
    private int mRetryDelay = 10000;
    private boolean isInit = false;

    private Anticheat() {
    }

    private void checkInit() {
        if (!this.isInit) {
            throw new IllegalStateException("init() method should be called first.");
        }
    }

    public static Anticheat inst() {
        if (inst == null) {
            synchronized (Anticheat.class) {
                if (inst == null) {
                    inst = new Anticheat();
                }
            }
        }
        return inst;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateEstrFromAPI() {
        if (this.inUpdateProgress) {
            return;
        }
        this.inUpdateProgress = true;
        this.iClient.getEstrFromAPI();
    }

    private void updateEstrWithDelay(int i) {
        this.mHandler.postDelayed(new Runnable() { // from class: com.ss.android.common.applog.Anticheat.1
            @Override // java.lang.Runnable
            public void run() {
                Anticheat.this.updateEstrFromAPI();
            }
        }, i);
    }

    public void init(IClient iClient) {
        if (this.isInit) {
            return;
        }
        this.isInit = true;
        updateEstr(iClient.readEstrFromSharedPreference());
        this.iClient = iClient;
        this.mHandler = new Handler(Looper.getMainLooper());
        updateEstrWithDelay(0);
    }

    @Override // com.ss.android.common.applog.IAnticheat
    public void onUpdateEstrFromApiFail(Exception exc) {
        this.mHandler.postDelayed(new Runnable() { // from class: com.ss.android.common.applog.Anticheat.2
            @Override // java.lang.Runnable
            public void run() {
                Anticheat.this.iClient.getEstrFromAPI();
            }
        }, this.mRetryDelay);
        int i = this.mRetryDelay;
        if (i <= 32000) {
            this.mRetryDelay = i * 2;
        }
    }

    @Override // com.ss.android.common.applog.IAnticheat
    public void onUpdateEstrFromApiSuccess(EstrBean estrBean) {
        String estr = estrBean.getEstr();
        this.mUpdateduration = estrBean.getNext_heartbeat() * 1000;
        if (!TextUtils.equals(this.mEstr, estr)) {
            this.iClient.writeEstrToSharedPreference(estr);
            updateEstr(estr);
        }
        this.inUpdateProgress = false;
        this.mRetryDelay = 10000;
        updateEstrWithDelay(this.mUpdateduration);
    }

    @Override // com.ss.android.common.applog.IAnticheat
    public void updateEstr(String str) {
        checkInit();
        if (TextUtils.isEmpty(str) || str.length() <= 8) {
            return;
        }
        UserInfo.initUser(str);
        this.mEstr = str;
    }
}
