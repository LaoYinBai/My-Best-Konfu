package com.bytedance.pangle.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.RequiresApi;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.ZeusConstants;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.plugin.Plugin;
import com.bytedance.pangle.transform.ZeusTransformUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.json.JSONException;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ContentProviderManager {
    public static final String PLUGIN_PKG_NAME = "plugin_pkg_name";
    public static final String PLUGIN_PROCESS_NAME = "process_name";
    public static final String PROVIDER_PARAM_FEILD = "provider_params";
    public static final String PROVIDER_PLUGIN_AUTHORITY = "provider_params";
    public static final String PROVIDER_PROXY_URI = "provider_proxy_uri";
    public static final String PROVIDER_URI = "uri";
    private static ContentProviderManager sInstance;
    private final Map<b, a> mContentProviderMap = new HashMap();
    private final Map<String, String> mAuthorityProcessNameMap = new HashMap();
    private final Map<String, c> mSystemProviderInfoMap = new HashMap();

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        public final String f4668a;

        /* renamed from: b, reason: collision with root package name */
        public final String f4669b;

        /* renamed from: c, reason: collision with root package name */
        public final String f4670c;

        public b(String str, String str2, String str3) {
            this.f4668a = str2;
            this.f4669b = str3;
            this.f4670c = str;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return TextUtils.equals(this.f4670c, bVar.f4670c) && TextUtils.equals(this.f4669b, bVar.f4669b) && TextUtils.equals(this.f4668a, bVar.f4668a);
        }

        public int hashCode() {
            if (Build.VERSION.SDK_INT >= 19) {
                return Objects.hash(this.f4668a, this.f4669b, this.f4670c);
            }
            return (this.f4668a + this.f4669b + this.f4668a).hashCode();
        }
    }

    protected static final class c extends b {
        public final ProviderInfo d;

        public c(String str, String str2, ProviderInfo providerInfo) {
            super(str, str2, providerInfo.authority);
            this.d = providerInfo;
        }
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public final b f4665a;

        /* renamed from: b, reason: collision with root package name */
        public final ProviderInfo f4666b;

        /* renamed from: c, reason: collision with root package name */
        public final PluginContentProvider f4667c;

        public a(b bVar, ProviderInfo providerInfo, PluginContentProvider pluginContentProvider) {
            this.f4666b = providerInfo;
            this.f4665a = bVar;
            this.f4667c = pluginContentProvider;
        }
    }

    private ContentProviderManager() {
    }

    public static ContentProviderManager getInstance() {
        if (sInstance == null) {
            synchronized (ContentProviderManager.class) {
                if (sInstance == null) {
                    sInstance = new ContentProviderManager();
                }
            }
        }
        return sInstance;
    }

    public Map<String, c> getSystemProviderInfoMap() {
        return this.mSystemProviderInfoMap;
    }

    public String getPluginProcessNameByAuthority(String str) {
        return this.mAuthorityProcessNameMap.get(str);
    }

    @RequiresApi(api = 16)
    public Cursor query(ContentResolver contentResolver, Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal, String str3) {
        return com.bytedance.pangle.provider.a.a(contentResolver, uri, strArr, str, strArr2, str2, cancellationSignal, str3);
    }

    public Cursor query(ContentResolver contentResolver, Uri uri, String[] strArr, String str, String[] strArr2, String str2, String str3) {
        return com.bytedance.pangle.provider.a.a(contentResolver, uri, strArr, str, strArr2, str2, str3);
    }

    @RequiresApi(api = 26)
    public Cursor query(ContentResolver contentResolver, Uri uri, String[] strArr, Bundle bundle, CancellationSignal cancellationSignal, String str) {
        return com.bytedance.pangle.provider.a.a(contentResolver, uri, strArr, bundle, cancellationSignal, str);
    }

    public String getType(ContentResolver contentResolver, Uri uri, String str) {
        return com.bytedance.pangle.provider.a.a(contentResolver, uri, str);
    }

    public Uri insert(ContentResolver contentResolver, Uri uri, ContentValues contentValues, String str) {
        return com.bytedance.pangle.provider.a.a(contentResolver, uri, contentValues, str);
    }

    public Uri insert(ContentResolver contentResolver, Uri uri, ContentValues contentValues, Bundle bundle, String str) {
        return com.bytedance.pangle.provider.a.a(contentResolver, uri, contentValues, bundle, str);
    }

    public int delete(ContentResolver contentResolver, Uri uri, String str, String[] strArr, String str2) {
        return com.bytedance.pangle.provider.a.a(contentResolver, uri, str, strArr, str2);
    }

    public int delete(ContentResolver contentResolver, Uri uri, Bundle bundle, String str) {
        return com.bytedance.pangle.provider.a.a(contentResolver, uri, bundle, str);
    }

    public int update(ContentResolver contentResolver, Uri uri, ContentValues contentValues, String str, String[] strArr, String str2) {
        return com.bytedance.pangle.provider.a.a(contentResolver, uri, contentValues, str, strArr, str2);
    }

    public int update(ContentResolver contentResolver, Uri uri, ContentValues contentValues, Bundle bundle, String str) {
        return com.bytedance.pangle.provider.a.b(contentResolver, uri, contentValues, bundle, str);
    }

    public Bundle call(ContentResolver contentResolver, Uri uri, String str, String str2, Bundle bundle, String str3) {
        return com.bytedance.pangle.provider.a.a(contentResolver, uri, str, str2, bundle, str3);
    }

    public final Bundle call(ContentResolver contentResolver, String str, String str2, String str3, Bundle bundle, String str4) {
        return com.bytedance.pangle.provider.a.a(contentResolver, str, str2, str3, bundle, str4);
    }

    protected boolean isPluginProvider(Uri uri) {
        if (uri == null) {
            return true;
        }
        String authority = uri.getAuthority();
        Set<String> setKeySet = this.mAuthorityProcessNameMap.keySet();
        return setKeySet != null && setKeySet.contains(authority);
    }

    public PluginContentProvider getPluginProvider(b bVar) {
        a aVar = this.mContentProviderMap.get(bVar);
        if (aVar == null) {
            return null;
        }
        return aVar.f4667c;
    }

    public void installContentProviders(Collection<ProviderInfo> collection, Plugin plugin) throws JSONException {
        if (collection == null || collection.size() == 0 || plugin == null) {
            return;
        }
        for (ProviderInfo providerInfo : collection) {
            if (ZeusLogger.isDebug()) {
                StringBuilder sb = new StringBuilder(128);
                sb.append("Install plugin provider [authority:");
                sb.append(providerInfo.authority);
                sb.append("] - [className:");
                sb.append(providerInfo.name);
                sb.append("]");
                ZeusLogger.v(ZeusLogger.TAG_PROVIDER, sb.toString());
            }
            installProvider(providerInfo, plugin);
        }
    }

    private void installProvider(ProviderInfo providerInfo, Plugin plugin) throws JSONException {
        if (providerInfo == null) {
            ZeusLogger.e(ZeusLogger.TAG_PROVIDER, "ProviderInfo is null !! can not install plugin provider ， plugin-mPkgName：【" + plugin.mPkgName + "】");
            return;
        }
        ZeusLogger.v(ZeusLogger.TAG_PROVIDER, "Start install plugin provider [authority:" + providerInfo.authority + "] [className:" + providerInfo.name + "]");
        try {
            PluginContentProvider pluginContentProviderInstantiateProvider = instantiateProvider(plugin.mClassLoader, providerInfo.name);
            pluginContentProviderInstantiateProvider.attachInfo(ZeusTransformUtils.wrapperContext(plugin.mHostApplication, plugin.mPkgName), providerInfo);
            ZeusLogger.v(ZeusLogger.TAG_PROVIDER, "Install plugin provider finish and invoke plugin provider attachInfo(onCreate) method finish [className:" + providerInfo.name + "]");
            b bVar = new b(providerInfo.packageName, providerInfo.processName, providerInfo.authority);
            this.mContentProviderMap.put(bVar, new a(bVar, providerInfo, pluginContentProviderInstantiateProvider));
            this.mAuthorityProcessNameMap.put(providerInfo.authority, providerInfo.processName);
        } catch (Exception e) {
            ZeusLogger.e(ZeusLogger.TAG_PROVIDER, "Instantiating Exception : ", e);
        }
    }

    private PluginContentProvider instantiateProvider(ClassLoader classLoader, String str) {
        return (PluginContentProvider) classLoader.loadClass(str).newInstance();
    }

    public void initSystemContentProviderInfo() {
        try {
            ProviderInfo[] providerInfoArr = Zeus.getAppApplication().getPackageManager().getPackageInfo(Zeus.getAppApplication().getPackageName(), 8).providers;
            if (providerInfoArr == null || providerInfoArr.length == 0) {
                return;
            }
            for (ProviderInfo providerInfo : providerInfoArr) {
                if (providerInfo != null && providerInfo.authority != null && providerInfo.authority.contains(ZeusConstants.f4513b)) {
                    try {
                        ZeusLogger.w(ZeusLogger.TAG_PROVIDER, "Need to init system provider info start [packageNam:=" + providerInfo.packageName + "],[processName=" + providerInfo.processName + "],[authority:" + providerInfo.authority + "]");
                        if (providerInfo.authority.contains(Zeus.getAppApplication().getPackageName() + ZeusConstants.f4513b)) {
                            String strA = com.bytedance.pangle.helper.c.a(providerInfo.processName);
                            this.mSystemProviderInfoMap.put(strA, new c(Zeus.getAppApplication().getPackageName(), strA, providerInfo));
                            ZeusLogger.w(ZeusLogger.TAG_PROVIDER, "Init system provider info finish [packageNam:=" + providerInfo.packageName + "],[processName=" + providerInfo.processName + "],[authority:" + providerInfo.authority + "]");
                        }
                    } catch (Exception e) {
                        ZeusLogger.e(ZeusLogger.TAG_PROVIDER, "Init system contentProviderInfo [authority:" + providerInfo.authority + "],exception：", e);
                    }
                }
            }
        } catch (Throwable th) {
            ZeusLogger.e(ZeusLogger.TAG_PROVIDER, "init System ContentProviderInfo exception：", th);
        }
    }
}
