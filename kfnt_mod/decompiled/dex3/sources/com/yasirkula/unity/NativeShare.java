package com.yasirkula.unity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Parcelable;
import android.util.Log;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NativeShare {
    private static String authority;

    public static void Share(Context context, String str, String str2, String[] strArr, String[] strArr2, String str3, String str4, String str5) {
        String str6;
        String mimeTypeFromExtension;
        if (strArr.length > 0 && GetAuthority(context) == null) {
            Log.e("Unity", "Can't find ContentProvider, share not possible!");
            return;
        }
        Intent intent = new Intent();
        if (str3.length() > 0) {
            intent.putExtra("android.intent.extra.SUBJECT", str3);
        }
        if (str4.length() > 0) {
            intent.putExtra("android.intent.extra.TEXT", str4);
        }
        if (strArr.length > 0) {
            String str7 = null;
            String str8 = null;
            for (int i = 0; i < strArr.length; i++) {
                if (strArr2[i].length() > 0) {
                    mimeTypeFromExtension = strArr2[i];
                } else {
                    int iLastIndexOf = strArr[i].lastIndexOf(46);
                    if (iLastIndexOf < 0 || iLastIndexOf == strArr.length - 1) {
                        str7 = "*";
                        str8 = str7;
                        break;
                    }
                    mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(strArr[i].substring(iLastIndexOf + 1).toLowerCase(Locale.ENGLISH));
                }
                if (mimeTypeFromExtension == null || mimeTypeFromExtension.length() == 0) {
                    str7 = "*";
                    str8 = str7;
                    break;
                }
                int iIndexOf = mimeTypeFromExtension.indexOf(47);
                if (iIndexOf <= 0 || iIndexOf == mimeTypeFromExtension.length() - 1) {
                    str7 = "*";
                    str8 = str7;
                    break;
                }
                String strSubstring = mimeTypeFromExtension.substring(0, iIndexOf);
                String strSubstring2 = mimeTypeFromExtension.substring(iIndexOf + 1);
                if (str7 == null) {
                    str7 = strSubstring;
                } else if (!str7.equals(strSubstring)) {
                    str7 = "*";
                    str8 = str7;
                    break;
                }
                if (str8 == null) {
                    str8 = strSubstring2;
                } else if (!str8.equals(strSubstring2)) {
                    str8 = "*";
                }
            }
            str6 = str7 + "/" + str8;
            if (strArr.length == 1) {
                intent.setAction("android.intent.action.SEND");
                intent.putExtra("android.intent.extra.STREAM", NativeShareContentProvider.getUriForFile(context, authority, new File(strArr[0])));
            } else {
                intent.setAction("android.intent.action.SEND_MULTIPLE");
                ArrayList<? extends Parcelable> arrayList = new ArrayList<>(strArr.length);
                for (String str9 : strArr) {
                    arrayList.add(NativeShareContentProvider.getUriForFile(context, authority, new File(str9)));
                }
                intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
            }
        } else {
            str6 = "text/plain";
            intent.setAction("android.intent.action.SEND");
        }
        if (str5.length() > 0) {
            intent.putExtra("android.intent.extra.TITLE", str5);
        }
        intent.setType(str6);
        intent.setFlags(1);
        if (str.length() > 0) {
            intent.setPackage(str);
            if (str2.length() > 0) {
                intent.setClassName(str, str2);
            }
        }
        if (context.getPackageManager().queryIntentActivities(intent, 65536).size() == 1) {
            context.startActivity(intent);
        } else {
            context.startActivity(Intent.createChooser(intent, str5));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x004a, code lost:
    
        com.yasirkula.unity.NativeShare.authority = r3.authority;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String GetAuthority(android.content.Context r6) {
        /*
            java.lang.String r0 = com.yasirkula.unity.NativeShare.authority
            if (r0 != 0) goto L5a
            android.content.pm.PackageManager r0 = r6.getPackageManager()     // Catch: java.lang.Exception -> L52
            java.lang.String r1 = r6.getPackageName()     // Catch: java.lang.Exception -> L52
            r2 = 8
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r1, r2)     // Catch: java.lang.Exception -> L52
            android.content.pm.ProviderInfo[] r0 = r0.providers     // Catch: java.lang.Exception -> L52
            if (r0 == 0) goto L5a
            int r1 = r0.length     // Catch: java.lang.Exception -> L52
            r2 = 0
        L18:
            if (r2 >= r1) goto L5a
            r3 = r0[r2]     // Catch: java.lang.Exception -> L52
            java.lang.String r4 = r3.name     // Catch: java.lang.Exception -> L52
            if (r4 == 0) goto L4f
            java.lang.String r4 = r3.packageName     // Catch: java.lang.Exception -> L52
            if (r4 == 0) goto L4f
            java.lang.String r4 = r3.authority     // Catch: java.lang.Exception -> L52
            if (r4 == 0) goto L4f
            java.lang.String r4 = r3.name     // Catch: java.lang.Exception -> L52
            java.lang.Class<com.yasirkula.unity.NativeShareContentProvider> r5 = com.yasirkula.unity.NativeShareContentProvider.class
            java.lang.String r5 = r5.getName()     // Catch: java.lang.Exception -> L52
            boolean r4 = r4.equals(r5)     // Catch: java.lang.Exception -> L52
            if (r4 == 0) goto L4f
            java.lang.String r4 = r3.packageName     // Catch: java.lang.Exception -> L52
            java.lang.String r5 = r6.getPackageName()     // Catch: java.lang.Exception -> L52
            boolean r4 = r4.equals(r5)     // Catch: java.lang.Exception -> L52
            if (r4 == 0) goto L4f
            java.lang.String r4 = r3.authority     // Catch: java.lang.Exception -> L52
            int r4 = r4.length()     // Catch: java.lang.Exception -> L52
            if (r4 <= 0) goto L4f
            java.lang.String r6 = r3.authority     // Catch: java.lang.Exception -> L52
            com.yasirkula.unity.NativeShare.authority = r6     // Catch: java.lang.Exception -> L52
            goto L5a
        L4f:
            int r2 = r2 + 1
            goto L18
        L52:
            r6 = move-exception
            java.lang.String r0 = "Unity"
            java.lang.String r1 = "Exception:"
            android.util.Log.e(r0, r1, r6)
        L5a:
            java.lang.String r6 = com.yasirkula.unity.NativeShare.authority
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yasirkula.unity.NativeShare.GetAuthority(android.content.Context):java.lang.String");
    }

    public static boolean TargetExists(Context context, String str, String str2) throws PackageManager.NameNotFoundException {
        try {
            if (str2.length() == 0) {
                context.getPackageManager().getPackageInfo(str, 0);
                return true;
            }
            ActivityInfo[] activityInfoArr = context.getPackageManager().getPackageInfo(str, 1).activities;
            if (activityInfoArr != null) {
                for (ActivityInfo activityInfo : activityInfoArr) {
                    if (activityInfo.name.equals(str2)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static String FindMatchingTarget(Context context, String str, String str2) {
        ActivityInfo[] activityInfoArr;
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(1);
        if (installedPackages == null) {
            return "";
        }
        Pattern patternCompile = Pattern.compile(str);
        Pattern patternCompile2 = str2.length() > 0 ? Pattern.compile(str2) : null;
        for (PackageInfo packageInfo : installedPackages) {
            if (patternCompile.matcher(packageInfo.packageName).find() && (activityInfoArr = packageInfo.activities) != null) {
                for (ActivityInfo activityInfo : activityInfoArr) {
                    if (patternCompile2 == null || patternCompile2.matcher(activityInfo.name).find()) {
                        return packageInfo.packageName + ">" + activityInfo.name;
                    }
                }
            }
        }
        return "";
    }
}
