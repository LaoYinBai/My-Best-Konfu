package com.bumptech.glide.load.engine.cache;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.util.LruCache;
import com.bumptech.glide.util.Util;
import com.bytedance.common.utility.StringEncryptUtils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class SafeKeyGenerator {
    private final LruCache<Key, String> loadIdToSafeHash = new LruCache<>(1000);

    SafeKeyGenerator() {
    }

    public String getSafeKey(Key key) {
        String strSha256BytesToHex;
        synchronized (this.loadIdToSafeHash) {
            strSha256BytesToHex = this.loadIdToSafeHash.get(key);
        }
        if (strSha256BytesToHex == null) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(StringEncryptUtils.SHA_256);
                key.updateDiskCacheKey(messageDigest);
                strSha256BytesToHex = Util.sha256BytesToHex(messageDigest.digest());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e2) {
                e2.printStackTrace();
            }
            synchronized (this.loadIdToSafeHash) {
                this.loadIdToSafeHash.put(key, strSha256BytesToHex);
            }
        }
        return strSha256BytesToHex;
    }
}
