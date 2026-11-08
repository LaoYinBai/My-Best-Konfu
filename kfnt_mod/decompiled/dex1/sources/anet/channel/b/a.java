package anet.channel.b;

import android.support.v4.media.session.PlaybackStateCompat;
import anet.channel.util.ALog;
import anet.channel.util.StringUtils;
import anetwork.channel.a.a;
import com.taobao.alivfssdk.cache.AVFSCache;
import com.taobao.alivfssdk.cache.AVFSCacheConfig;
import com.taobao.alivfssdk.cache.AVFSCacheManager;
import com.taobao.alivfssdk.cache.IAVFSCache;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements anetwork.channel.a.a {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f402a = true;

    /* renamed from: b, reason: collision with root package name */
    private static Object f403b;

    /* renamed from: c, reason: collision with root package name */
    private static Object f404c;
    private static Object d;

    static {
        try {
            Class.forName("com.taobao.alivfssdk.cache.AVFSCacheManager");
            f403b = new b();
            f404c = new c();
            d = new d();
        } catch (ClassNotFoundException unused) {
            f402a = false;
            ALog.w("anet.AVFSCacheImpl", "no alivfs sdk!", null, new Object[0]);
        }
    }

    public void a() {
        AVFSCache aVFSCacheCacheForModule;
        if (f402a && (aVFSCacheCacheForModule = AVFSCacheManager.getInstance().cacheForModule("networksdk.httpcache")) != null) {
            AVFSCacheConfig aVFSCacheConfig = new AVFSCacheConfig();
            aVFSCacheConfig.limitSize = 5242880L;
            aVFSCacheConfig.fileMemMaxSize = PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
            aVFSCacheCacheForModule.moduleConfig(aVFSCacheConfig);
        }
    }

    @Override // anetwork.channel.a.a
    public a.C0010a get(String str) {
        if (!f402a) {
            return null;
        }
        try {
            IAVFSCache iAVFSCacheB = b();
            if (iAVFSCacheB != null) {
                return (a.C0010a) iAVFSCacheB.objectForKey(StringUtils.md5ToHex(str));
            }
        } catch (Exception e) {
            ALog.e("anet.AVFSCacheImpl", "get cache failed", null, e, new Object[0]);
        }
        return null;
    }

    @Override // anetwork.channel.a.a
    public void put(String str, a.C0010a c0010a) {
        if (f402a) {
            try {
                IAVFSCache iAVFSCacheB = b();
                if (iAVFSCacheB != null) {
                    iAVFSCacheB.setObjectForKey(StringUtils.md5ToHex(str), c0010a, (IAVFSCache.OnObjectSetCallback) f403b);
                }
            } catch (Exception e) {
                ALog.e("anet.AVFSCacheImpl", "put cache failed", null, e, new Object[0]);
            }
        }
    }

    @Override // anetwork.channel.a.a
    public void remove(String str) {
        if (f402a) {
            try {
                IAVFSCache iAVFSCacheB = b();
                if (iAVFSCacheB != null) {
                    iAVFSCacheB.removeObjectForKey(StringUtils.md5ToHex(str), (IAVFSCache.OnObjectRemoveCallback) f404c);
                }
            } catch (Exception e) {
                ALog.e("anet.AVFSCacheImpl", "remove cache failed", null, e, new Object[0]);
            }
        }
    }

    @Override // anetwork.channel.a.a
    public void clear() {
        if (f402a) {
            try {
                IAVFSCache iAVFSCacheB = b();
                if (iAVFSCacheB != null) {
                    iAVFSCacheB.removeAllObject((IAVFSCache.OnAllObjectRemoveCallback) d);
                }
            } catch (Exception e) {
                ALog.e("anet.AVFSCacheImpl", "clear cache failed", null, e, new Object[0]);
            }
        }
    }

    private IAVFSCache b() {
        AVFSCache aVFSCacheCacheForModule = AVFSCacheManager.getInstance().cacheForModule("networksdk.httpcache");
        if (aVFSCacheCacheForModule != null) {
            return aVFSCacheCacheForModule.getFileCache();
        }
        return null;
    }
}
