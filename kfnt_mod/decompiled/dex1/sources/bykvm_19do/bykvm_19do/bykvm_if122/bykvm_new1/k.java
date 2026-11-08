package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import com.baidu.mobads.sdk.internal.av;
import com.bytedance.msdk.adapter.util.UIUtils;
import com.bytedance.sdk.account.platform.api.IGoogleService;
import com.bytedance.sdk.openadsdk.api.plugin.PluginConstants;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class k {
    public static String a() {
        return a(true);
    }

    private static String a(int i) {
        return i != 120 ? i != 240 ? i != 320 ? i != 480 ? i != 640 ? "mdpi" : "xxxhdpi" : "xxhdpi" : "xhdpi" : "hdpi" : "ldpi";
    }

    @SuppressLint({"NewApi"})
    private static String a(String str) throws SocketException {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (str == null || networkInterface.getName().equalsIgnoreCase(str)) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b2 : hardwareAddress) {
                        sb.append(String.format("%02X:", Byte.valueOf(b2)));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static String a(boolean z) {
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                for (InetAddress inetAddress : Collections.list(((NetworkInterface) it.next()).getInetAddresses())) {
                    if (!inetAddress.isLoopbackAddress()) {
                        boolean z2 = inetAddress instanceof Inet4Address;
                        String upperCase = inetAddress.getHostAddress().toUpperCase();
                        if (z) {
                            if (z2) {
                                return upperCase;
                            }
                        } else if (!z2) {
                            int iIndexOf = upperCase.indexOf(37);
                            return iIndexOf < 0 ? upperCase : upperCase.substring(0, iIndexOf);
                        }
                    }
                }
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0044, code lost:
    
        if ((((54 * 54) + (27 * 27)) + (24 * 24)) < (((54 * 27) + (27 * 24)) + (54 * 24))) goto L16;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:21:0x0067. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0005. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0015. Please report as an issue. */
    /* JADX WARN: Path cross not found for [B:16:0x0046, B:92:?], limit reached: 91 */
    /* JADX WARN: Removed duplicated region for block: B:13:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0090 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0166 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0000 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0067 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x001a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0061 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0061 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0044 -> B:18:0x0061). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x005f -> B:18:0x0061). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.json.JSONObject a(android.content.Context r8) {
        /*
            Method dump skipped, instructions count: 406
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.k.a(android.content.Context):org.json.JSONObject");
    }

    public static String b() {
        if (Build.VERSION.SDK_INT < 21) {
            return Locale.getDefault().getLanguage();
        }
        String languageTag = Locale.getDefault().toLanguageTag();
        return !TextUtils.isEmpty(languageTag) ? languageTag : "";
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0000, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x011b, code lost:
    
        continue;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:14:0x0034. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x000c. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.json.JSONObject b(android.content.Context r6) {
        /*
            Method dump skipped, instructions count: 318
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.k.b(android.content.Context):org.json.JSONObject");
    }

    public static int c(Context context) {
        if (e(context)) {
            return 3;
        }
        return d(context) ? 2 : 1;
    }

    public static String c() throws SocketException {
        String strA = a("wlan0");
        if (TextUtils.isEmpty(strA)) {
            strA = a("eth0");
        }
        return TextUtils.isEmpty(strA) ? "DU:MM:YA:DD:RE:SS" : strA;
    }

    private static String d() {
        String str;
        StringBuilder sb = new StringBuilder();
        if (t.t()) {
            str = "MIUI-";
        } else {
            if (!t.q()) {
                String strF = t.f();
                if (t.d(strF)) {
                    sb.append("EMUI-");
                }
                if (!TextUtils.isEmpty(strF)) {
                    sb.append(strF);
                    str = "-";
                }
                sb.append(Build.VERSION.INCREMENTAL);
                return sb.toString();
            }
            str = "FLYME-";
        }
        sb.append(str);
        sb.append(Build.VERSION.INCREMENTAL);
        return sb.toString();
    }

    public static boolean d(Context context) {
        try {
            return (context.getResources().getConfiguration().screenLayout & 15) >= 3;
        } catch (Exception unused) {
            return false;
        }
    }

    private static int e() {
        int rawOffset = TimeZone.getDefault().getRawOffset() / 3600000;
        if (rawOffset < -12) {
            rawOffset = -12;
        }
        if (rawOffset > 12) {
            return 12;
        }
        return rawOffset;
    }

    public static boolean e(Context context) {
        try {
            return (context.getResources().getConfiguration().uiMode & 15) == 4;
        } catch (Exception unused) {
            return false;
        }
    }

    public static long f() throws NumberFormatException, IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            long j = Long.parseLong(bufferedReader.readLine().split("\\s+")[1]);
            bufferedReader.close();
            return j * PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        } catch (IOException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    public static long g() {
        if (!j()) {
            return -1L;
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return statFs.getBlockSize() * statFs.getBlockCount();
    }

    public static JSONObject h() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        Context contextD = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d();
        try {
            jSONObject.put(com.bytedance.b.a.c.h.x, n.d(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()));
            jSONObject.put("user_unique_id", n.d(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()) != null ? n.d(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()) : UUID.randomUUID().toString());
            jSONObject.put(PluginConstants.KEY_SDK_VERSION, "3.1.0.1");
            jSONObject.put("os", "Android");
            jSONObject.put("os_version", Build.VERSION.RELEASE);
            jSONObject.put("device_model", Build.MODEL);
            jSONObject.put("resolution", UIUtils.getScreenHeight(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()) + "x" + UIUtils.getScreenWidth(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()));
            jSONObject.put("language", Locale.getDefault().getLanguage());
            jSONObject.put("timezone", e());
            jSONObject.put("access", q.d(contextD));
            jSONObject.put("openudid", n.a(contextD));
            jSONObject.put("aid", "5685");
            jSONObject.put(IGoogleService.ResponseKey.DISPLAY_NAME, g.b());
            jSONObject.put("app_version", g.a());
            jSONObject.put("package", e0.a());
            jSONObject.put("region", Locale.getDefault().getCountry());
            jSONObject.put("tz_name", Calendar.getInstance().getTimeZone().getID());
            jSONObject.put("tz_offset", Calendar.getInstance().getTimeZone().getOffset(System.currentTimeMillis()) / 1000);
            jSONObject.put("rom", d());
            jSONObject.put("device_manufacturer", Build.MANUFACTURER);
            ArrayList<String> arrayListA = h.a(contextD, "MD5");
            if (arrayListA != null && !arrayListA.isEmpty()) {
                jSONObject.put("sig_hash", Build.MANUFACTURER);
            }
            jSONObject.put("display_density", a(UIUtils.getDensityDpi(contextD)));
            jSONObject.put("os_api", Build.VERSION.SDK_INT);
            jSONObject.put("density_dpi", UIUtils.getDensityDpi(contextD));
            jSONObject.put("device_brand", Build.BRAND);
            jSONObject.put("cpu_abi", Build.CPU_ABI);
            jSONObject.put("build_serial", n.j(contextD));
            jSONObject.put("version_code", e0.d());
            jSONObject.put("udid", n.e(contextD));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("imei_md5", p.a(n.e(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d())));
            jSONObject2.put("gaid", f.c().a());
            jSONObject2.put("applog_did", n.d(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()));
            jSONObject2.put("publisher_did", bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().r());
            jSONObject2.put(av.f, n.a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()));
            jSONObject2.put("oaid", r.a());
            jSONObject.put("custom", jSONObject2);
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x001a, code lost:
    
        switch(95) {
            case 52: goto L19;
            case 53: goto L59;
            case 54: goto L58;
            default: goto L49;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0000, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0017, code lost:
    
        if (((((87 - 1) * 87) * ((87 * 2) - 1)) % 6) != 0) goto L61;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:17:0x003d. Please report as an issue. */
    /* JADX WARN: Path cross not found for [B:61:?, B:8:0x001a], limit reached: 60 */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003d A[EDGE_INSN: B:17:0x003d->B:58:0x0000 BREAK  A[LOOP:1: B:11:0x0026->B:62:?]] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0000 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x002e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0028 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x004e -> B:8:0x001a). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String i() {
        /*
            Method dump skipped, instructions count: 282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.k.i():java.lang.String");
    }

    public static boolean j() {
        return "mounted".equals(Environment.getExternalStorageState());
    }
}
