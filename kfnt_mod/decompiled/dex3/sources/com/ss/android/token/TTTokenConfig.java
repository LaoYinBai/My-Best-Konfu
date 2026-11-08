package com.ss.android.token;

import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.ss.android.downloadlib.constants.DownloadConstants;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TTTokenConfig {
    private IBlackList mBlackList;
    private String mTokenSaveName;
    private Set<String> mHostSet = new CopyOnWriteArraySet();
    private boolean mNeedSign = false;
    private long mUpdateInterval = DownloadConstants.DOWNLOAD_OPTIMIZATION_SPACE_INTERNAL;
    private String mBeatUrl = BDAccountNetApi.Account.getTokenBeatHost();

    public interface IBlackList {
        boolean inBlackList(String str);
    }

    public TTTokenConfig setTokenSaveName(String str) {
        this.mTokenSaveName = str;
        return this;
    }

    public String getTokenSaveName() {
        return this.mTokenSaveName;
    }

    public TTTokenConfig() {
        String topDomain = TokenUtils.getTopDomain(this.mBeatUrl);
        if (topDomain != null) {
            this.mHostSet.add(topDomain);
        }
    }

    protected String getBeatHost() {
        return this.mBeatUrl;
    }

    protected Set<String> getHostList() {
        return this.mHostSet;
    }

    public TTTokenConfig addHostList(Collection<String> collection) {
        if (collection == null || collection.size() == 0) {
            return this;
        }
        this.mHostSet.addAll(collection);
        return this;
    }

    protected long getUpdateInterval() {
        return this.mUpdateInterval;
    }

    public TTTokenConfig setUpdateInterval(long j) {
        this.mUpdateInterval = j;
        return this;
    }

    public TTTokenConfig setTokenSign(boolean z) {
        this.mNeedSign = z;
        return this;
    }

    protected boolean isNeedTokenSign() {
        return this.mNeedSign;
    }

    protected IBlackList getBlackList() {
        return this.mBlackList;
    }

    public TTTokenConfig setBlackList(IBlackList iBlackList) {
        this.mBlackList = iBlackList;
        return this;
    }

    protected void dynamicAddHostList(Collection<String> collection) {
        if (collection == null || collection.size() <= 0) {
            return;
        }
        this.mHostSet.addAll(collection);
    }
}
