package com.bytedance.applog;

import android.accounts.Account;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bytedance.a.cq;
import com.bytedance.a.s;
import com.bytedance.applog.network.INetworkClient;
import com.bytedance.mpaas.IEncryptor;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class InitConfig {
    public String A;
    public Map<String, Object> B;
    public Account C;
    public boolean D;
    public INetworkClient F;
    public boolean H;
    public ISensitiveInfoProvider P;
    public List<String> V;
    public String Y;
    public boolean Z;

    /* renamed from: a, reason: collision with root package name */
    public String f3556a;

    /* renamed from: c, reason: collision with root package name */
    public String f3558c;
    public String d;
    public IEncryptor e;
    public String f;
    public String g;
    public ILogger h;
    public String i;
    public String j;
    public IPicker k;
    public boolean l;
    public boolean n;
    public String p;
    public boolean q;
    public String r;
    public UriConfig s;
    public String t;
    public String u;
    public int v;
    public int w;
    public int x;
    public String y;
    public String z;

    /* renamed from: b, reason: collision with root package name */
    public boolean f3557b = true;
    public boolean m = false;
    public int o = 0;
    public INetworkClient E = new s();
    public boolean G = true;
    public boolean I = false;
    public boolean J = true;
    public boolean K = false;
    public boolean L = true;
    public boolean M = true;
    public String N = "bd_tea_agent.db";
    public String O = "applog_stats";
    public boolean Q = true;
    public boolean R = true;
    public boolean S = false;
    public boolean T = true;
    public boolean U = true;
    public boolean W = true;
    public boolean X = true;
    public IpcDataChecker a0 = null;
    public String b0 = null;

    public interface IpcDataChecker {
        boolean checkIpcData(String[] strArr);
    }

    public InitConfig(@NonNull String str, @NonNull String str2) {
        this.f3556a = str;
        this.f3558c = str2;
        if (TextUtils.isEmpty(str2)) {
            Log.e("InitConfig", "channel is empty, please check!!!");
        }
    }

    public boolean a() {
        return this.L;
    }

    public boolean autoStart() {
        return this.f3557b;
    }

    public InitConfig clearABCacheOnUserChange(boolean z) {
        this.X = z;
        return this;
    }

    public void clearDidAndIid(String str) {
        this.D = true;
        this.d = str;
    }

    public InitConfig disableDeferredALink() {
        this.W = false;
        return this;
    }

    public Account getAccount() {
        return this.C;
    }

    public String getAid() {
        return this.f3556a;
    }

    public String getAliyunUdid() {
        return this.j;
    }

    public boolean getAnonymous() {
        return this.l;
    }

    public String getAppImei() {
        return this.Y;
    }

    public String getAppName() {
        return this.r;
    }

    public String getChannel() {
        return this.f3558c;
    }

    public String getClearKey() {
        return this.d;
    }

    public Map<String, Object> getCommonHeader() {
        return this.B;
    }

    public String getDbName() {
        return this.N;
    }

    public IEncryptor getEncryptor() {
        return this.e;
    }

    public String getGoogleAid() {
        return this.f;
    }

    @Nullable
    public List<String> getH5BridgeAllowlist() {
        return this.V;
    }

    public IpcDataChecker getIpcDataChecker() {
        return this.a0;
    }

    public String getLanguage() {
        return this.g;
    }

    public boolean getLocalTest() {
        return this.m;
    }

    public ILogger getLogger() {
        return this.h;
    }

    public String getManifestVersion() {
        return this.y;
    }

    public int getManifestVersionCode() {
        return this.x;
    }

    public INetworkClient getNetworkClient() {
        INetworkClient iNetworkClient = this.F;
        return iNetworkClient != null ? iNetworkClient : this.E;
    }

    public boolean getNotReuqestSender() {
        return this.q;
    }

    public IPicker getPicker() {
        return this.k;
    }

    public cq getPreInstallCallback() {
        return null;
    }

    public int getProcess() {
        return this.o;
    }

    public String getRegion() {
        return this.i;
    }

    public String getReleaseBuild() {
        return this.p;
    }

    public ISensitiveInfoProvider getSensitiveInfoProvider() {
        return this.P;
    }

    public String getSpName() {
        return this.O;
    }

    public String getTweakedChannel() {
        return this.u;
    }

    public int getUpdateVersionCode() {
        return this.w;
    }

    public UriConfig getUriConfig() {
        return this.s;
    }

    public String getUserUniqueId() {
        return this.b0;
    }

    public String getVersion() {
        return this.t;
    }

    public int getVersionCode() {
        return this.v;
    }

    public String getVersionMinor() {
        return this.z;
    }

    public String getZiJieCloudPkg() {
        return this.A;
    }

    public boolean isAbEnable() {
        return this.I;
    }

    public boolean isAntiCheatingEnable() {
        return this.K;
    }

    public boolean isAutoActive() {
        return this.G;
    }

    public boolean isAutoTrackEnabled() {
        return this.J;
    }

    public boolean isClearABCacheOnUserChange() {
        return this.X;
    }

    public boolean isClearDidAndIid() {
        return this.D;
    }

    public boolean isCongestionControlEnable() {
        return this.M;
    }

    public boolean isDeferredALinkEnabled() {
        return this.W;
    }

    public boolean isEventFilterEnable() {
        return this.Z;
    }

    public boolean isH5BridgeEnable() {
        return this.S;
    }

    public boolean isH5CollectEnable() {
        return this.T;
    }

    public boolean isImeiEnable() {
        return this.R;
    }

    public boolean isLogEnable() {
        return this.U;
    }

    public boolean isMacEnable() {
        return this.Q;
    }

    public boolean isPlayEnable() {
        return this.n;
    }

    public boolean isSilenceInBackground() {
        return this.H;
    }

    public InitConfig putCommonHeader(Map<String, Object> map) {
        this.B = map;
        return this;
    }

    public void setAbEnable(boolean z) {
        this.I = z;
    }

    public InitConfig setAccount(Account account) {
        this.C = account;
        return this;
    }

    public InitConfig setAliyunUdid(String str) {
        this.j = str;
        return this;
    }

    public InitConfig setAnonymous(boolean z) {
        this.l = z;
        return this;
    }

    public void setAppImei(String str) {
        this.Y = str;
    }

    public InitConfig setAppName(String str) {
        this.r = str;
        return this;
    }

    public void setAutoActive(boolean z) {
        this.G = z;
    }

    public InitConfig setAutoStart(boolean z) {
        this.f3557b = z;
        return this;
    }

    public void setAutoTrackEnabled(boolean z) {
        this.J = z;
    }

    public void setChannel(@NonNull String str) {
        this.f3558c = str;
    }

    public void setCongestionControlEnable(boolean z) {
        this.M = z;
    }

    public InitConfig setDbName(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.N = str;
        }
        return this;
    }

    @NonNull
    public InitConfig setEnablePlay(boolean z) {
        this.n = z;
        return this;
    }

    public InitConfig setEncryptor(IEncryptor iEncryptor) {
        this.e = iEncryptor;
        return this;
    }

    public void setEventFilterEnable(boolean z) {
        this.Z = z;
    }

    @NonNull
    public InitConfig setGoogleAid(String str) {
        this.f = str;
        return this;
    }

    public InitConfig setH5BridgeAllowlist(List<String> list) {
        this.V = list;
        return this;
    }

    public InitConfig setH5BridgeEnable(boolean z) {
        this.S = z;
        return this;
    }

    public InitConfig setH5CollectEnable(boolean z) {
        this.T = z;
        return this;
    }

    public void setHandleLifeCycle(boolean z) {
        this.L = z;
    }

    public InitConfig setImeiEnable(boolean z) {
        this.R = z;
        return this;
    }

    public InitConfig setIpcDataChecker(IpcDataChecker ipcDataChecker) {
        this.a0 = ipcDataChecker;
        return this;
    }

    @NonNull
    public InitConfig setLanguage(String str) {
        this.g = str;
        return this;
    }

    public InitConfig setLocalTest(boolean z) {
        this.m = z;
        return this;
    }

    public InitConfig setLogEnable(boolean z) {
        this.U = z;
        return this;
    }

    public InitConfig setLogger(ILogger iLogger) {
        this.h = iLogger;
        return this;
    }

    public void setMacEnable(boolean z) {
        this.Q = z;
    }

    public InitConfig setManifestVersion(String str) {
        this.y = str;
        return this;
    }

    public InitConfig setManifestVersionCode(int i) {
        this.x = i;
        return this;
    }

    public void setNeedAntiCheating(boolean z) {
        this.K = z;
    }

    public InitConfig setNetworkClient(INetworkClient iNetworkClient) {
        this.F = iNetworkClient;
        return this;
    }

    public InitConfig setNotRequestSender(boolean z) {
        this.q = z;
        return this;
    }

    @NonNull
    public InitConfig setPicker(IPicker iPicker) {
        this.k = iPicker;
        return this;
    }

    public InitConfig setPreInstallChannelCallback(cq cqVar) {
        return this;
    }

    @NonNull
    public InitConfig setProcess(int i) {
        this.o = i;
        return this;
    }

    @NonNull
    public InitConfig setRegion(String str) {
        this.i = str;
        return this;
    }

    @NonNull
    public InitConfig setReleaseBuild(String str) {
        this.p = str;
        return this;
    }

    public void setSensitiveInfoProvider(ISensitiveInfoProvider iSensitiveInfoProvider) {
        this.P = iSensitiveInfoProvider;
    }

    public void setSilenceInBackground(boolean z) {
        this.H = z;
    }

    public InitConfig setSpName(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.O = str;
        }
        return this;
    }

    public InitConfig setTweakedChannel(String str) {
        this.u = str;
        return this;
    }

    public InitConfig setUpdateVersionCode(int i) {
        this.w = i;
        return this;
    }

    public InitConfig setUriConfig(int i) {
        this.s = UriConfig.createUriConfig(i);
        return this;
    }

    public InitConfig setUriConfig(UriConfig uriConfig) {
        this.s = uriConfig;
        return this;
    }

    public InitConfig setUserUniqueId(String str) {
        this.b0 = str;
        return this;
    }

    public InitConfig setVersion(String str) {
        this.t = str;
        return this;
    }

    public InitConfig setVersionCode(int i) {
        this.v = i;
        return this;
    }

    public InitConfig setVersionMinor(String str) {
        this.z = str;
        return this;
    }

    public InitConfig setZiJieCloudPkg(String str) {
        this.A = str;
        return this;
    }
}
