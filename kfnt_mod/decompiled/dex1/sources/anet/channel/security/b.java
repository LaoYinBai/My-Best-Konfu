package anet.channel.security;

import android.content.Context;
import android.text.TextUtils;
import anet.channel.util.ALog;
import com.alibaba.wireless.security.open.SecurityGuardManager;
import com.alibaba.wireless.security.open.SecurityGuardParamContext;
import com.alibaba.wireless.security.open.dynamicdatastore.IDynamicDataStoreComponent;
import com.alibaba.wireless.security.open.securesignature.ISecureSignatureComponent;
import com.alibaba.wireless.security.open.staticdataencrypt.IStaticDataEncryptComponent;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class b implements ISecurity {

    /* renamed from: a, reason: collision with root package name */
    private static String f487a = "awcn.DefaultSecurityGuard";

    /* renamed from: b, reason: collision with root package name */
    private static boolean f488b = false;

    /* renamed from: c, reason: collision with root package name */
    private static Map<String, Integer> f489c;
    private String d;

    @Override // anet.channel.security.ISecurity
    public boolean isSecOff() {
        return false;
    }

    static {
        try {
            Class.forName("com.alibaba.wireless.security.open.SecurityGuardManager");
            f488b = true;
            f489c = new HashMap();
            f489c.put(ISecurity.SIGN_ALGORITHM_HMAC_SHA1, 3);
            f489c.put(ISecurity.CIPHER_ALGORITHM_AES128, 16);
        } catch (Throwable unused) {
            f488b = false;
        }
    }

    b(String str) {
        this.d = null;
        this.d = str;
    }

    @Override // anet.channel.security.ISecurity
    public String sign(Context context, String str, String str2, String str3) {
        if (!f488b || context == null || TextUtils.isEmpty(str2) || !f489c.containsKey(str)) {
            return null;
        }
        try {
            ISecureSignatureComponent secureSignatureComp = SecurityGuardManager.getInstance(context).getSecureSignatureComp();
            if (secureSignatureComp != null) {
                SecurityGuardParamContext securityGuardParamContext = new SecurityGuardParamContext();
                securityGuardParamContext.appKey = str2;
                securityGuardParamContext.paramMap.put("INPUT", str3);
                securityGuardParamContext.requestType = f489c.get(str).intValue();
                return secureSignatureComp.signRequest(securityGuardParamContext, this.d);
            }
        } catch (Throwable th) {
            ALog.e(f487a, "Securityguard sign request failed.", null, th, new Object[0]);
        }
        return null;
    }

    @Override // anet.channel.security.ISecurity
    public byte[] decrypt(Context context, String str, String str2, byte[] bArr) {
        Integer num;
        IStaticDataEncryptComponent staticDataEncryptComp;
        if (!f488b || context == null || bArr == null || TextUtils.isEmpty(str2) || !f489c.containsKey(str) || (num = f489c.get(str)) == null) {
            return null;
        }
        try {
            SecurityGuardManager securityGuardManager = SecurityGuardManager.getInstance(context);
            if (securityGuardManager != null && (staticDataEncryptComp = securityGuardManager.getStaticDataEncryptComp()) != null) {
                return staticDataEncryptComp.staticBinarySafeDecryptNoB64(num.intValue(), str2, bArr, this.d);
            }
        } catch (Throwable th) {
            ALog.e(f487a, "staticBinarySafeDecryptNoB64", null, th, new Object[0]);
        }
        return null;
    }

    @Override // anet.channel.security.ISecurity
    public boolean saveBytes(Context context, String str, byte[] bArr) {
        IDynamicDataStoreComponent dynamicDataStoreComp;
        if (context == null || bArr == null || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            SecurityGuardManager securityGuardManager = SecurityGuardManager.getInstance(context);
            if (securityGuardManager == null || (dynamicDataStoreComp = securityGuardManager.getDynamicDataStoreComp()) == null) {
                return false;
            }
            return dynamicDataStoreComp.putByteArray(str, bArr) != 0;
        } catch (Throwable th) {
            ALog.e(f487a, "saveBytes", null, th, new Object[0]);
            return false;
        }
    }

    @Override // anet.channel.security.ISecurity
    public byte[] getBytes(Context context, String str) {
        IDynamicDataStoreComponent dynamicDataStoreComp;
        if (context == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            SecurityGuardManager securityGuardManager = SecurityGuardManager.getInstance(context);
            if (securityGuardManager == null || (dynamicDataStoreComp = securityGuardManager.getDynamicDataStoreComp()) == null) {
                return null;
            }
            return dynamicDataStoreComp.getByteArray(str);
        } catch (Throwable th) {
            ALog.e(f487a, "getBytes", null, th, new Object[0]);
            return null;
        }
    }
}
