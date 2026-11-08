package b;

import com.bytedance.sdk.openadsdk.TTAdConstant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class i {
    final String bj;

    /* renamed from: a, reason: collision with root package name */
    static final Comparator<String> f1242a = new Comparator<String>() { // from class: b.i.1
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(String str, String str2) {
            int iMin = Math.min(str.length(), str2.length());
            for (int i2 = 4; i2 < iMin; i2++) {
                char cCharAt = str.charAt(i2);
                char cCharAt2 = str2.charAt(i2);
                if (cCharAt != cCharAt2) {
                    return cCharAt < cCharAt2 ? -1 : 1;
                }
            }
            int length = str.length();
            int length2 = str2.length();
            if (length != length2) {
                return length < length2 ? -1 : 1;
            }
            return 0;
        }
    };
    private static final Map<String, i> bk = new TreeMap(f1242a);

    /* renamed from: b, reason: collision with root package name */
    public static final i f1243b = a("SSL_RSA_WITH_NULL_MD5", 1);

    /* renamed from: c, reason: collision with root package name */
    public static final i f1244c = a("SSL_RSA_WITH_NULL_SHA", 2);
    public static final i d = a("SSL_RSA_EXPORT_WITH_RC4_40_MD5", 3);
    public static final i e = a("SSL_RSA_WITH_RC4_128_MD5", 4);
    public static final i f = a("SSL_RSA_WITH_RC4_128_SHA", 5);
    public static final i g = a("SSL_RSA_EXPORT_WITH_DES40_CBC_SHA", 8);
    public static final i h = a("SSL_RSA_WITH_DES_CBC_SHA", 9);
    public static final i i = a("SSL_RSA_WITH_3DES_EDE_CBC_SHA", 10);
    public static final i j = a("SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA", 17);
    public static final i k = a("SSL_DHE_DSS_WITH_DES_CBC_SHA", 18);
    public static final i l = a("SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA", 19);
    public static final i m = a("SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA", 20);
    public static final i n = a("SSL_DHE_RSA_WITH_DES_CBC_SHA", 21);
    public static final i o = a("SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA", 22);
    public static final i p = a("SSL_DH_anon_EXPORT_WITH_RC4_40_MD5", 23);
    public static final i q = a("SSL_DH_anon_WITH_RC4_128_MD5", 24);
    public static final i r = a("SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA", 25);
    public static final i s = a("SSL_DH_anon_WITH_DES_CBC_SHA", 26);
    public static final i t = a("SSL_DH_anon_WITH_3DES_EDE_CBC_SHA", 27);
    public static final i u = a("TLS_KRB5_WITH_DES_CBC_SHA", 30);
    public static final i v = a("TLS_KRB5_WITH_3DES_EDE_CBC_SHA", 31);
    public static final i w = a("TLS_KRB5_WITH_RC4_128_SHA", 32);
    public static final i x = a("TLS_KRB5_WITH_DES_CBC_MD5", 34);
    public static final i y = a("TLS_KRB5_WITH_3DES_EDE_CBC_MD5", 35);
    public static final i z = a("TLS_KRB5_WITH_RC4_128_MD5", 36);
    public static final i A = a("TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA", 38);
    public static final i B = a("TLS_KRB5_EXPORT_WITH_RC4_40_SHA", 40);
    public static final i C = a("TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5", 41);
    public static final i D = a("TLS_KRB5_EXPORT_WITH_RC4_40_MD5", 43);
    public static final i E = a("TLS_RSA_WITH_AES_128_CBC_SHA", 47);
    public static final i F = a("TLS_DHE_DSS_WITH_AES_128_CBC_SHA", 50);
    public static final i G = a("TLS_DHE_RSA_WITH_AES_128_CBC_SHA", 51);
    public static final i H = a("TLS_DH_anon_WITH_AES_128_CBC_SHA", 52);
    public static final i I = a("TLS_RSA_WITH_AES_256_CBC_SHA", 53);
    public static final i J = a("TLS_DHE_DSS_WITH_AES_256_CBC_SHA", 56);
    public static final i K = a("TLS_DHE_RSA_WITH_AES_256_CBC_SHA", 57);
    public static final i L = a("TLS_DH_anon_WITH_AES_256_CBC_SHA", 58);
    public static final i M = a("TLS_RSA_WITH_NULL_SHA256", 59);
    public static final i N = a("TLS_RSA_WITH_AES_128_CBC_SHA256", 60);
    public static final i O = a("TLS_RSA_WITH_AES_256_CBC_SHA256", 61);
    public static final i P = a("TLS_DHE_DSS_WITH_AES_128_CBC_SHA256", 64);
    public static final i Q = a("TLS_RSA_WITH_CAMELLIA_128_CBC_SHA", 65);
    public static final i R = a("TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA", 68);
    public static final i S = a("TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA", 69);
    public static final i T = a("TLS_DHE_RSA_WITH_AES_128_CBC_SHA256", 103);
    public static final i U = a("TLS_DHE_DSS_WITH_AES_256_CBC_SHA256", 106);
    public static final i V = a("TLS_DHE_RSA_WITH_AES_256_CBC_SHA256", 107);
    public static final i W = a("TLS_DH_anon_WITH_AES_128_CBC_SHA256", 108);
    public static final i X = a("TLS_DH_anon_WITH_AES_256_CBC_SHA256", 109);
    public static final i Y = a("TLS_RSA_WITH_CAMELLIA_256_CBC_SHA", 132);
    public static final i Z = a("TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA", 135);
    public static final i aa = a("TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA", 136);
    public static final i ab = a("TLS_PSK_WITH_RC4_128_SHA", 138);
    public static final i ac = a("TLS_PSK_WITH_3DES_EDE_CBC_SHA", 139);
    public static final i ad = a("TLS_PSK_WITH_AES_128_CBC_SHA", 140);
    public static final i ae = a("TLS_PSK_WITH_AES_256_CBC_SHA", 141);
    public static final i af = a("TLS_RSA_WITH_SEED_CBC_SHA", 150);
    public static final i ag = a("TLS_RSA_WITH_AES_128_GCM_SHA256", 156);
    public static final i ah = a("TLS_RSA_WITH_AES_256_GCM_SHA384", 157);
    public static final i ai = a("TLS_DHE_RSA_WITH_AES_128_GCM_SHA256", 158);
    public static final i aj = a("TLS_DHE_RSA_WITH_AES_256_GCM_SHA384", 159);
    public static final i ak = a("TLS_DHE_DSS_WITH_AES_128_GCM_SHA256", 162);
    public static final i al = a("TLS_DHE_DSS_WITH_AES_256_GCM_SHA384", 163);
    public static final i am = a("TLS_DH_anon_WITH_AES_128_GCM_SHA256", TTAdConstant.IMAGE_MODE_LIVE);
    public static final i an = a("TLS_DH_anon_WITH_AES_256_GCM_SHA384", 167);
    public static final i ao = a("TLS_EMPTY_RENEGOTIATION_INFO_SCSV", 255);
    public static final i ap = a("TLS_FALLBACK_SCSV", 22016);
    public static final i aq = a("TLS_ECDH_ECDSA_WITH_NULL_SHA", 49153);
    public static final i ar = a("TLS_ECDH_ECDSA_WITH_RC4_128_SHA", 49154);
    public static final i as = a("TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA", 49155);
    public static final i at = a("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA", 49156);
    public static final i au = a("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA", 49157);
    public static final i av = a("TLS_ECDHE_ECDSA_WITH_NULL_SHA", 49158);
    public static final i aw = a("TLS_ECDHE_ECDSA_WITH_RC4_128_SHA", 49159);
    public static final i ax = a("TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA", 49160);
    public static final i ay = a("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", 49161);
    public static final i az = a("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA", 49162);
    public static final i aA = a("TLS_ECDH_RSA_WITH_NULL_SHA", 49163);
    public static final i aB = a("TLS_ECDH_RSA_WITH_RC4_128_SHA", 49164);
    public static final i aC = a("TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA", 49165);
    public static final i aD = a("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA", 49166);
    public static final i aE = a("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA", 49167);
    public static final i aF = a("TLS_ECDHE_RSA_WITH_NULL_SHA", 49168);
    public static final i aG = a("TLS_ECDHE_RSA_WITH_RC4_128_SHA", 49169);
    public static final i aH = a("TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA", 49170);
    public static final i aI = a("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", 49171);
    public static final i aJ = a("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", 49172);
    public static final i aK = a("TLS_ECDH_anon_WITH_NULL_SHA", 49173);
    public static final i aL = a("TLS_ECDH_anon_WITH_RC4_128_SHA", 49174);
    public static final i aM = a("TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA", 49175);
    public static final i aN = a("TLS_ECDH_anon_WITH_AES_128_CBC_SHA", 49176);
    public static final i aO = a("TLS_ECDH_anon_WITH_AES_256_CBC_SHA", 49177);
    public static final i aP = a("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", 49187);
    public static final i aQ = a("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384", 49188);
    public static final i aR = a("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256", 49189);
    public static final i aS = a("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384", 49190);
    public static final i aT = a("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", 49191);
    public static final i aU = a("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384", 49192);
    public static final i aV = a("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256", 49193);
    public static final i aW = a("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384", 49194);
    public static final i aX = a("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", 49195);
    public static final i aY = a("TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", 49196);
    public static final i aZ = a("TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256", 49197);
    public static final i ba = a("TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384", 49198);
    public static final i bb = a("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", 49199);
    public static final i bc = a("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", 49200);
    public static final i bd = a("TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256", 49201);
    public static final i be = a("TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384", 49202);
    public static final i bf = a("TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA", 49205);
    public static final i bg = a("TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA", 49206);
    public static final i bh = a("TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256", 52392);
    public static final i bi = a("TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256", 52393);

    public static synchronized i a(String str) {
        i iVar;
        iVar = bk.get(str);
        if (iVar == null) {
            iVar = new i(str);
            bk.put(str, iVar);
        }
        return iVar;
    }

    static List<i> a(String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(a(str));
        }
        return Collections.unmodifiableList(arrayList);
    }

    private i(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.bj = str;
    }

    private static i a(String str, int i2) {
        return a(str);
    }

    public String a() {
        return this.bj;
    }

    public String toString() {
        return this.bj;
    }
}
