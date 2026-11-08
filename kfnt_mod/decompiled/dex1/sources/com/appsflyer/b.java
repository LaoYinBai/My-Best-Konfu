package com.appsflyer;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bun.miitmdid.core.Utils;
import com.bytedance.mobile.cpsdk.R;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class b {

    /* renamed from: ˏ, reason: contains not printable characters */
    private static int f161 = 1;

    /* renamed from: ॱ, reason: contains not printable characters */
    private static int f162;

    /* renamed from: ˎ, reason: contains not printable characters */
    private static char[] f160 = {'a', 22944, 46072, 3352, 26455, 49519, 6832, 29836, 52740, 10331, 33376, 56255, 13784, 36638, 59691, 17276, 40089, 63104, 20520, 43567, 1140, 23939, 47044, 4586, 27455, 50544, 7829, 30967, 53993, 11320, 34373, 57237, 14757, 37884, '1', '0', 3346, 21724, 48782, 'x', 27262, 52256, 6130, 31204, 50047, 9521, 36664, 55033, 14475, 33392, 58434, 19978, 37368, 64415, 'b', 22959, 46078, 3343, 26457, 49507, 6884, 29847, 52809, 10330, 33337, 56297, 13769, 36628, 59773, 17185, 40148, 63176, 20552, 43567, 1068, 23939, 46976, 4530, 27444, 50471, 7901, 30940, 53946, 11366, 34325, 57283, 'a', 22944, 46072, 3352, 26455, 49519, 6832, 29836, 52767, 10317, 33314, 56216, 13769, 36610, 59696, 17271, 40082, 63191, 20529, 43563, 1142, 23943, 47059, 4583, 27426, 'a', 22944, 46072, 3352, 26455, 49519, 6832, 29836, 52760, 10335, 33406, 56254, 13791, 36631, 59702, 17271, 40142, 63229, 20505, 43556, 1131, 23945, 47046, 17618, 7443, 63307, 18859, 9188, 34268, 24067, 12351, 35500, 27902, 50833, 40744, 29032, 52156, 44441, 1986, 55303, 45692, 5308, 61074, 37822, 51839, 8231, 40647, 62600, 21168, 35183, 59219, 24001, 48004, 4519, 18475, 42530, 7387, 31474, '.', '\\', 23008, 53155, 38502, 31779, 49858, 43231, 3750, 54642, 47941, 461, 59290, 19893, 5239, 64008, 16589, '/', 22957, 46077, 3337, 26448, 49507, 3543, 21533, 48714, 207, 27314, 52437, 5893, 31093, 50083, 9710, 'C', 22950, 46073, 3337, 26451, 49525, 6817, 29903, 52789, 10310, 33391, 56255, 13784, 36610, 59693, 17277, 40078, 8143, 17958, 44156, 4751, 30932, 57067, 1405, 27487, 53654, 14231, 40418, 50230, 10837, 37087, 63150, 23802, 33546, 59727, 20368, 46563, 7137, 16910, 43081, 3683, 29945, 56032, 268, 26439, 52585, 13311, 39368, 49155, 9770, 35938, 62117, 22743, 48920, 58656, 19315, 45521, 6041, 41089, 63821, 4878, 44485, 51120, 24979, 47686, 54309, 28410, 34996, 8847, 31576, 38159, 12256, 18898, 58264, 15471, 22059, 61691, 2776, 42135, 64879, 5948, 45335, 'C', 22950, 46073, 3337, 26451, 49525, 6817, 29903, 52770, 10331, 33386, 56246, 13773, 36629, 59696, 17239, 40088, 63181, 20505, 43578, 1132, 23951, 47067, 4588, 37519, 52070, 8508, 40911, 62868, 21419, 34877, 58882, 23767, 47745, 4266, 18808, 42756, 7583, 31743, 53694, 3663, 25611, 49872, 14560, 38565, 53066, 9497, 33643, 63988, 22450, 35921, 59931, 16430, 48891, 5325, 19788, 43872, 307, 32765, 54723, 12884, 26743, 50750, 15566, 39561, 62275, 10604, 34620, 64975, 23493, 45549};

    /* renamed from: ˊ, reason: contains not printable characters */
    private static long f159 = 3649797082317871566L;

    b() {
    }

    @Nullable
    /* renamed from: ˎ, reason: contains not printable characters */
    static String m131(Context context, long j) throws NoSuchAlgorithmException {
        String strIntern;
        String strIntern2;
        String strM128;
        String strM1282;
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        if (m135(m128(34, 0, (char) 0).intern())) {
            strIntern = m128(1, 34, (char) 0).intern();
        } else {
            strIntern = m128(1, 35, (char) 0).intern();
            int i = f161 + 3;
            f162 = i % 128;
            int i2 = i % 2;
        }
        sb2.append(strIntern);
        StringBuilder sb4 = new StringBuilder();
        String packageName = context.getPackageName();
        String strM129 = m129(packageName);
        sb2.append(m128(1, 34, (char) 0).intern());
        sb4.append(strM129);
        if ((m134(context) == null ? '_' : (char) 19) != 19) {
            sb2.append(m128(1, 35, (char) 0).intern());
            sb4.append(packageName);
        } else {
            sb2.append(m128(1, 34, (char) 0).intern());
            sb4.append(packageName);
        }
        String strM130 = m130(context);
        if (strM130 == null) {
            int i3 = f161 + 65;
            f162 = i3 % 128;
            sb2.append(((i3 % 2 != 0 ? '5' : '3') != '3' ? m128(0, 14, (char) 0) : m128(1, 35, (char) 0)).intern());
            sb4.append(packageName);
        } else {
            sb2.append(m128(1, 34, (char) 0).intern());
            sb4.append(strM130);
        }
        sb4.append(m132(context, packageName));
        sb.append(sb4.toString());
        try {
            long j2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(m128(18, 36, (char) 3435).intern(), Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            sb.append(simpleDateFormat.format(new Date(j2)));
            int i4 = f161 + 13;
            f162 = i4 % 128;
            int i5 = i4 % 2;
            sb.append(j);
            sb3.append((m135(m128(25, 86, (char) 0).intern()) ? m128(1, 34, (char) 0) : m128(1, 35, (char) 0)).intern());
            if (m135(m128(23, R.styleable.AppCompatTheme_windowActionBar, (char) 0).intern())) {
                int i6 = f162 + 109;
                f161 = i6 % 128;
                strIntern2 = i6 % 2 == 0 ? m128(1, 10, (char) 0).intern() : m128(1, 34, (char) 0).intern();
            } else {
                strIntern2 = m128(1, 35, (char) 0).intern();
                int i7 = f161 + 9;
                f162 = i7 % 128;
                int i8 = i7 % 2;
            }
            sb3.append(strIntern2);
            if (m135(m128(20, 134, (char) 17587).intern())) {
                int i9 = f161 + 23;
                f162 = i9 % 128;
                int i10 = i9 % 2;
                strM128 = m128(1, 34, (char) 0);
            } else {
                strM128 = m128(1, 35, (char) 0);
            }
            sb3.append(strM128.intern());
            if ((m135(m128(15, 154, (char) 37855).intern()) ? (char) 16 : (char) 23) != 23) {
                int i11 = f161 + 3;
                f162 = i11 % 128;
                int i12 = i11 % 2;
                strM1282 = m128(1, 34, (char) 0);
            } else {
                strM1282 = m128(1, 35, (char) 0);
            }
            sb3.append(strM1282.intern());
            String strM241 = t.m241(t.m240(sb.toString()));
            String string = sb2.toString();
            StringBuilder sb5 = new StringBuilder(strM241);
            sb5.setCharAt(17, Integer.toString(Integer.parseInt(string, 2), 16).charAt(0));
            String string2 = sb5.toString();
            String string3 = sb3.toString();
            StringBuilder sb6 = new StringBuilder(string2);
            sb6.setCharAt(27, Integer.toString(Integer.parseInt(string3, 2), 16).charAt(0));
            return m133(sb6.toString(), Long.valueOf(j));
        } catch (PackageManager.NameNotFoundException unused) {
            return m128(32, 54, (char) 0).intern();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0026  */
    /* renamed from: ˏ, reason: contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String m133(java.lang.String r8, java.lang.Long r9) {
        /*
            Method dump skipped, instructions count: 216
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.b.m133(java.lang.String, java.lang.Long):java.lang.String");
    }

    /* renamed from: ॱ, reason: contains not printable characters */
    private static boolean m135(String str) throws ClassNotFoundException {
        int i = f161 + 43;
        f162 = i % 128;
        int i2 = i % 2;
        try {
            Class.forName(str);
            int i3 = f162 + 41;
            f161 = i3 % 128;
            if (!(i3 % 2 != 0)) {
                Object[] objArr = null;
                int length = objArr.length;
            }
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    /* renamed from: ˊ, reason: contains not printable characters */
    private static String m129(String str) {
        int i = f162 + 9;
        f161 = i % 128;
        int i2 = i % 2;
        if ((!str.contains(m128(1, 169, (char) 0).intern()) ? (char) 19 : '\\') != '\\') {
            int i3 = f162 + 63;
            f161 = i3 % 128;
            int i4 = i3 % 2;
            return str;
        }
        String[] strArrSplit = str.split(m128(2, 170, (char) 0).intern());
        int length = strArrSplit.length;
        StringBuilder sb = new StringBuilder();
        int i5 = length - 1;
        sb.append(strArrSplit[i5]);
        sb.append(m128(1, 169, (char) 0).intern());
        int i6 = 1;
        while (true) {
            if (i6 >= i5) {
                sb.append(strArrSplit[0]);
                return sb.toString();
            }
            int i7 = f161 + 99;
            f162 = i7 % 128;
            int i8 = i7 % 2;
            sb.append(strArrSplit[i6]);
            sb.append(m128(1, 169, (char) 0).intern());
            i6++;
            int i9 = f161 + 57;
            f162 = i9 % 128;
            if (i9 % 2 != 0) {
            }
        }
    }

    /* renamed from: ॱ, reason: contains not printable characters */
    private static String m134(Context context) {
        int i = f162 + 123;
        f161 = i % 128;
        if (i % 2 == 0) {
            if ((System.getProperties().containsKey(m128(68, 5866, (char) 53193).intern()) ? 'Z' : (char) 15) == 15) {
                return null;
            }
        } else {
            if (!System.getProperties().containsKey(m128(14, 172, (char) 53193).intern())) {
                return null;
            }
        }
        try {
            Matcher matcher = Pattern.compile(m128(10, 192, (char) 3577).intern()).matcher(context.getCacheDir().getPath().replace(m128(6, 186, (char) 0).intern(), ""));
            if (!matcher.find()) {
                return null;
            }
            int i2 = f162 + 33;
            f161 = i2 % 128;
            int i3 = i2 % 2;
            return matcher.group(1);
        } catch (Exception e2) {
            r rVarM212 = r.m212();
            String strIntern = m128(17, 202, (char) 0).intern();
            StringBuilder sb = new StringBuilder();
            sb.append(m128(41, 219, (char) 8073).intern());
            sb.append(e2);
            rVarM212.m227(strIntern, sb.toString());
            return null;
        }
    }

    /* renamed from: ˋ, reason: contains not printable characters */
    private static String m130(Context context) {
        Object[] objArr = null;
        try {
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
            int i = f161 + 109;
            f162 = i % 128;
            int i2 = i % 2;
            int i3 = f161 + 23;
            f162 = i3 % 128;
            int i4 = i3 % 2;
            int i5 = f162 + 51;
            f161 = i5 % 128;
            if ((i5 % 2 == 0 ? '>' : '(') != '>') {
                return str;
            }
            int length = objArr.length;
            return str;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    private static String m132(Context context, String str) {
        Iterator it;
        try {
            it = ((List) PackageManager.class.getDeclaredMethod(m128(24, 260, (char) 41190).intern(), Integer.TYPE).invoke(context.getPackageManager(), 0)).iterator();
        } catch (IllegalAccessException e2) {
            r rVarM212 = r.m212();
            String strIntern = m128(24, 284, (char) 0).intern();
            StringBuilder sb = new StringBuilder();
            sb.append(m128(47, 308, (char) 37577).intern());
            sb.append(e2);
            rVarM212.m227(strIntern, sb.toString());
        } catch (NoSuchMethodException e3) {
            r rVarM2122 = r.m212();
            String strIntern2 = m128(24, 284, (char) 0).intern();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(m128(47, 308, (char) 37577).intern());
            sb2.append(e3);
            rVarM2122.m227(strIntern2, sb2.toString());
        } catch (InvocationTargetException e4) {
            r rVarM2123 = r.m212();
            String strIntern3 = m128(24, 284, (char) 0).intern();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(m128(47, 308, (char) 37577).intern());
            sb3.append(e4);
            rVarM2123.m227(strIntern3, sb3.toString());
        }
        do {
            if ((it.hasNext() ? 'c' : (char) 23) != 'c') {
                int i = f161 + 93;
                f162 = i % 128;
                if (i % 2 != 0) {
                }
                return Boolean.FALSE.toString();
            }
        } while (!((PackageItemInfo) ((ApplicationInfo) it.next())).packageName.equals(str));
        int i2 = f162 + 83;
        f161 = i2 % 128;
        if (!(i2 % 2 == 0)) {
            return Boolean.TRUE.toString();
        }
        int i3 = 11 / 0;
        return Boolean.TRUE.toString();
    }

    static class e extends HashMap<String, Object> {

        /* renamed from: ˎ, reason: contains not printable characters */
        private final Context f163;

        /* renamed from: ˏ, reason: contains not printable characters */
        private final Map<String, Object> f164;

        e(Map<String, Object> map, Context context) {
            this.f164 = map;
            this.f163 = context;
            put(m136(), m138());
        }

        /* renamed from: ˎ, reason: contains not printable characters */
        private static StringBuilder m137(@NonNull String... strArr) throws Exception {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < 3; i++) {
                arrayList.add(Integer.valueOf(strArr[i].length()));
            }
            Collections.sort(arrayList);
            int iIntValue = ((Integer) arrayList.get(0)).intValue();
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < iIntValue; i2++) {
                Integer numValueOf = null;
                for (int i3 = 0; i3 < 3; i3++) {
                    int iCharAt = strArr[i3].charAt(i2);
                    if (numValueOf != null) {
                        iCharAt ^= numValueOf.intValue();
                    }
                    numValueOf = Integer.valueOf(iCharAt);
                }
                sb.append(Integer.toHexString(numValueOf.intValue()));
            }
            return sb;
        }

        @NonNull
        /* renamed from: ˋ, reason: contains not printable characters */
        private String m136() {
            try {
                String string = Integer.toString(Build.VERSION.SDK_INT);
                String displayLanguage = Locale.getDefault().getDisplayLanguage();
                StringBuilder sb = new StringBuilder(this.f164.get("af_timestamp").toString());
                sb.reverse();
                StringBuilder sbM137 = m137(string, displayLanguage, sb.toString());
                int length = sbM137.length();
                if (length > 4) {
                    sbM137.delete(4, length);
                } else {
                    while (length < 4) {
                        length++;
                        sbM137.append('1');
                    }
                }
                sbM137.insert(0, "kef");
                return sbM137.toString();
            } catch (Exception e) {
                AFLogger.afRDLog("failed generating kef key with exception: ".concat(String.valueOf(e)));
                return "kef9999";
            }
        }

        /* renamed from: ॱ, reason: contains not printable characters */
        private String m138() {
            String string;
            try {
                String string2 = this.f164.get("af_timestamp").toString();
                String string3 = this.f164.get("firstLaunchDate").toString();
                StringBuilder sb = new StringBuilder();
                sb.append(string2);
                sb.append(string3);
                sb.append(BuildConfig.VERSION_NAME);
                String strM240 = t.m240(sb.toString());
                StringBuilder sb2 = new StringBuilder();
                sb2.append("");
                sb2.append(strM240.substring(0, 16));
                string = sb2.toString();
            } catch (Exception e) {
                AFLogger.afRDLog("failed generating kef value with exception: ".concat(String.valueOf(e)));
                StringBuilder sb3 = new StringBuilder();
                sb3.append("");
                sb3.append("babeae0540d91f2018");
                string = sb3.toString();
            }
            try {
                int intExtra = this.f163.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("temperature", -2700);
                boolean zContains = this.f163.getApplicationInfo().nativeLibraryDir.contains(Utils.CPU_ABI_X86);
                int size = ((SensorManager) this.f163.getSystemService("sensor")).getSensorList(-1).size();
                StringBuilder sb4 = new StringBuilder("b");
                sb4.append(intExtra);
                sb4.append("&x");
                sb4.append(zContains ? 1 : 0);
                sb4.append("&s");
                sb4.append(size);
                sb4.append("&p");
                sb4.append(this.f164.size());
                String string4 = sb4.toString();
                StringBuilder sb5 = new StringBuilder();
                sb5.append(string);
                sb5.append(C0088b.m139(string4));
                return sb5.toString();
            } catch (Exception e2) {
                AFLogger.afRDLog("failed generating kef value with exception: ".concat(String.valueOf(e2)));
                StringBuilder sb6 = new StringBuilder();
                sb6.append(string);
                sb6.append("f0q0q1p1p22chtam");
                return sb6.toString();
            }
        }

        /* renamed from: com.appsflyer.b$e$b, reason: collision with other inner class name */
        static class C0088b {

            /* renamed from: ˊ, reason: contains not printable characters */
            private long f165;

            /* renamed from: ˋ, reason: contains not printable characters */
            private String f166;

            /* renamed from: ˏ, reason: contains not printable characters */
            private final Object f167;

            C0088b() {
            }

            @NonNull
            /* renamed from: ˎ, reason: contains not printable characters */
            static String m139(String str) throws Exception {
                byte[] bytes = str.getBytes();
                for (int i = 0; i < bytes.length; i++) {
                    bytes[i] = (byte) (bytes[i] ^ ((i % 2) + 42));
                }
                StringBuilder sb = new StringBuilder();
                for (byte b2 : bytes) {
                    String hexString = Integer.toHexString(b2);
                    if (hexString.length() == 1) {
                        hexString = OnekeyLoginConstants.CU_RESULT_SUCCESS.concat(String.valueOf(hexString));
                    }
                    sb.append(hexString);
                }
                return sb.toString();
            }

            C0088b(long j, String str) {
                this.f167 = new Object();
                this.f165 = 0L;
                this.f166 = "";
                this.f165 = j;
                this.f166 = str;
            }

            C0088b(String str) {
                this(System.currentTimeMillis(), str);
            }

            @NonNull
            /* renamed from: ॱ, reason: contains not printable characters */
            static C0088b m141(String str) {
                if (str == null) {
                    return new C0088b(0L, "");
                }
                String[] strArrSplit = str.split(IWeiboService.Scope.EMPTY_SCOPE);
                if (strArrSplit.length >= 2) {
                    return new C0088b(Long.parseLong(strArrSplit[0]), strArrSplit[1]);
                }
                return new C0088b(0L, "");
            }

            /* renamed from: ˎ, reason: contains not printable characters */
            final boolean m143(C0088b c0088b) {
                return m140(c0088b.f165, c0088b.f166);
            }

            /* renamed from: ˏ, reason: contains not printable characters */
            private boolean m140(long j, String str) {
                synchronized (this.f167) {
                    if (str != null) {
                        if (!str.equals(this.f166)) {
                            if (j - this.f165 > 2000) {
                                this.f165 = j;
                                this.f166 = str;
                                return true;
                            }
                        }
                    }
                    return false;
                }
            }

            public final String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append(this.f165);
                sb.append(IWeiboService.Scope.EMPTY_SCOPE);
                sb.append(this.f166);
                return sb.toString();
            }

            /* renamed from: ˊ, reason: contains not printable characters */
            final String m142() {
                return this.f166;
            }
        }
    }

    /* renamed from: ˊ, reason: contains not printable characters */
    private static String m128(int i, int i2, char c2) {
        char[] cArr = new char[i];
        int i3 = f161 + 87;
        f162 = i3 % 128;
        int i4 = i3 % 2;
        int i5 = 0;
        while (true) {
            if (!(i5 < i)) {
                String str = new String(cArr);
                int i6 = f162 + 123;
                f161 = i6 % 128;
                int i7 = i6 % 2;
                return str;
            }
            int i8 = f162 + 1;
            f161 = i8 % 128;
            if (i8 % 2 != 0) {
                cArr[i5] = (char) ((f160[i2 + i5] ^ (i5 * f159)) ^ c2);
                i5++;
            } else {
                cArr[i5] = (char) ((f160[i2 + i5] / (i5 + f159)) - c2);
                i5 += 100;
            }
            int i9 = f161 + 35;
            f162 = i9 % 128;
            int i10 = i9 % 2;
        }
    }
}
