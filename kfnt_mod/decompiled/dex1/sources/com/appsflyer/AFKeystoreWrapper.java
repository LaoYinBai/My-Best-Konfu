package com.appsflyer;

import android.content.Context;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import com.bytedance.common.utility.DeviceUtils;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Calendar;
import javax.security.auth.x500.X500Principal;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class AFKeystoreWrapper {

    /* renamed from: ˋ, reason: contains not printable characters */
    private KeyStore f69;

    /* renamed from: ॱ, reason: contains not printable characters */
    private Context f72;

    /* renamed from: ˏ, reason: contains not printable characters */
    private final Object f71 = new Object();

    /* renamed from: ˎ, reason: contains not printable characters */
    private String f70 = "";

    /* renamed from: ˊ, reason: contains not printable characters */
    private int f68 = 0;

    public AFKeystoreWrapper(Context context) throws NoSuchAlgorithmException, IOException, CertificateException {
        this.f72 = context;
        AFLogger.afInfoLog("Initialising KeyStore..");
        try {
            this.f69 = KeyStore.getInstance("AndroidKeyStore");
            this.f69.load(null);
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            AFLogger.afErrorLog("Couldn't load keystore instance of type: AndroidKeyStore", e);
        }
    }

    /* renamed from: ˊ, reason: contains not printable characters */
    final void m44(String str) {
        this.f70 = str;
        this.f68 = 0;
        m42(m41());
    }

    /* renamed from: ॱ, reason: contains not printable characters */
    final void m47() {
        String strM41 = m41();
        synchronized (this.f71) {
            this.f68++;
            AFLogger.afInfoLog("Deleting key with alias: ".concat(String.valueOf(strM41)));
            try {
                synchronized (this.f71) {
                    this.f69.deleteEntry(strM41);
                }
            } catch (KeyStoreException e) {
                StringBuilder sb = new StringBuilder("Exception ");
                sb.append(e.getMessage());
                sb.append(" occurred");
                AFLogger.afErrorLog(sb.toString(), e);
            }
        }
        m42(m41());
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0025, code lost:
    
        r1 = r4.split(com.bytedance.sdk.account.platform.api.IWeiboService.Scope.EMPTY_SCOPE);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002d, code lost:
    
        if (r1.length != 3) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002f, code lost:
    
        com.appsflyer.AFLogger.afInfoLog("Found a matching AF key with alias:\n".concat(java.lang.String.valueOf(r4)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003c, code lost:
    
        r3 = r1[1].trim().split("=");
        r1 = r1[2].trim().split("=");
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0056, code lost:
    
        if (r3.length != 2) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0059, code lost:
    
        if (r1.length != 2) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x005b, code lost:
    
        r7.f70 = r3[1].trim();
        r7.f68 = java.lang.Integer.parseInt(r1[1].trim());
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0070, code lost:
    
        r1 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0074, code lost:
    
        r3 = new java.lang.StringBuilder("Couldn't list KeyStore Aliases: ");
        r3.append(r1.getClass().getName());
        com.appsflyer.AFLogger.afErrorLog(r3.toString(), r1);
     */
    /* renamed from: ˎ, reason: contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final boolean m46() {
        /*
            r7 = this;
            java.lang.Object r0 = r7.f71
            monitor-enter(r0)
            java.security.KeyStore r1 = r7.f69     // Catch: java.lang.Throwable -> L91
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L8e
            java.security.KeyStore r1 = r7.f69     // Catch: java.lang.Throwable -> L72 java.lang.Throwable -> L91
            java.util.Enumeration r1 = r1.aliases()     // Catch: java.lang.Throwable -> L72 java.lang.Throwable -> L91
        Lf:
            boolean r4 = r1.hasMoreElements()     // Catch: java.lang.Throwable -> L72 java.lang.Throwable -> L91
            if (r4 == 0) goto L8e
            java.lang.Object r4 = r1.nextElement()     // Catch: java.lang.Throwable -> L72 java.lang.Throwable -> L91
            java.lang.String r4 = (java.lang.String) r4     // Catch: java.lang.Throwable -> L72 java.lang.Throwable -> L91
            if (r4 == 0) goto Lf
            java.lang.String r5 = "com.appsflyer"
            boolean r5 = r4.startsWith(r5)     // Catch: java.lang.Throwable -> L72 java.lang.Throwable -> L91
            if (r5 == 0) goto Lf
            java.lang.String r1 = ","
            java.lang.String[] r1 = r4.split(r1)     // Catch: java.lang.Throwable -> L72 java.lang.Throwable -> L91
            int r5 = r1.length     // Catch: java.lang.Throwable -> L72 java.lang.Throwable -> L91
            r6 = 3
            if (r5 != r6) goto L8e
            java.lang.String r5 = "Found a matching AF key with alias:\n"
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch: java.lang.Throwable -> L72 java.lang.Throwable -> L91
            java.lang.String r4 = r5.concat(r4)     // Catch: java.lang.Throwable -> L72 java.lang.Throwable -> L91
            com.appsflyer.AFLogger.afInfoLog(r4)     // Catch: java.lang.Throwable -> L72 java.lang.Throwable -> L91
            r3 = r1[r2]     // Catch: java.lang.Throwable -> L70 java.lang.Throwable -> L91
            java.lang.String r3 = r3.trim()     // Catch: java.lang.Throwable -> L70 java.lang.Throwable -> L91
            java.lang.String r4 = "="
            java.lang.String[] r3 = r3.split(r4)     // Catch: java.lang.Throwable -> L70 java.lang.Throwable -> L91
            r4 = 2
            r1 = r1[r4]     // Catch: java.lang.Throwable -> L70 java.lang.Throwable -> L91
            java.lang.String r1 = r1.trim()     // Catch: java.lang.Throwable -> L70 java.lang.Throwable -> L91
            java.lang.String r5 = "="
            java.lang.String[] r1 = r1.split(r5)     // Catch: java.lang.Throwable -> L70 java.lang.Throwable -> L91
            int r5 = r3.length     // Catch: java.lang.Throwable -> L70 java.lang.Throwable -> L91
            if (r5 != r4) goto L8f
            int r5 = r1.length     // Catch: java.lang.Throwable -> L70 java.lang.Throwable -> L91
            if (r5 != r4) goto L8f
            r3 = r3[r2]     // Catch: java.lang.Throwable -> L70 java.lang.Throwable -> L91
            java.lang.String r3 = r3.trim()     // Catch: java.lang.Throwable -> L70 java.lang.Throwable -> L91
            r7.f70 = r3     // Catch: java.lang.Throwable -> L70 java.lang.Throwable -> L91
            r1 = r1[r2]     // Catch: java.lang.Throwable -> L70 java.lang.Throwable -> L91
            java.lang.String r1 = r1.trim()     // Catch: java.lang.Throwable -> L70 java.lang.Throwable -> L91
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.Throwable -> L70 java.lang.Throwable -> L91
            r7.f68 = r1     // Catch: java.lang.Throwable -> L70 java.lang.Throwable -> L91
            goto L8f
        L70:
            r1 = move-exception
            goto L74
        L72:
            r1 = move-exception
            r2 = 0
        L74:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L91
            java.lang.String r4 = "Couldn't list KeyStore Aliases: "
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L91
            java.lang.Class r4 = r1.getClass()     // Catch: java.lang.Throwable -> L91
            java.lang.String r4 = r4.getName()     // Catch: java.lang.Throwable -> L91
            r3.append(r4)     // Catch: java.lang.Throwable -> L91
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L91
            com.appsflyer.AFLogger.afErrorLog(r3, r1)     // Catch: java.lang.Throwable -> L91
            goto L8f
        L8e:
            r2 = 0
        L8f:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L91
            return r2
        L91:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.AFKeystoreWrapper.m46():boolean");
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    private void m42(String str) {
        AFLogger.afInfoLog("Creating a new key with alias: ".concat(String.valueOf(str)));
        try {
            Calendar calendar = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(1, 5);
            AlgorithmParameterSpec algorithmParameterSpecBuild = null;
            synchronized (this.f71) {
                if (!this.f69.containsAlias(str)) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        algorithmParameterSpecBuild = new KeyGenParameterSpec.Builder(str, 3).setCertificateSubject(new X500Principal("CN=AndroidSDK, O=AppsFlyer")).setCertificateSerialNumber(BigInteger.ONE).setCertificateNotBefore(calendar.getTime()).setCertificateNotAfter(calendar2.getTime()).build();
                    } else if (Build.VERSION.SDK_INT >= 18 && !DeviceUtils.ROM_OPPO.equals(Build.BRAND)) {
                        algorithmParameterSpecBuild = new KeyPairGeneratorSpec.Builder(this.f72).setAlias(str).setSubject(new X500Principal("CN=AndroidSDK, O=AppsFlyer")).setSerialNumber(BigInteger.ONE).setStartDate(calendar.getTime()).setEndDate(calendar2.getTime()).build();
                    }
                    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
                    keyPairGenerator.initialize(algorithmParameterSpecBuild);
                    keyPairGenerator.generateKeyPair();
                } else {
                    AFLogger.afInfoLog("Alias already exists: ".concat(String.valueOf(str)));
                }
            }
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder("Exception ");
            sb.append(th.getMessage());
            sb.append(" occurred");
            AFLogger.afErrorLog(sb.toString(), th);
        }
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    private String m41() {
        StringBuilder sb = new StringBuilder();
        sb.append("com.appsflyer,");
        synchronized (this.f71) {
            sb.append("KSAppsFlyerId=");
            sb.append(this.f70);
            sb.append(IWeiboService.Scope.EMPTY_SCOPE);
            sb.append("KSAppsFlyerRICounter=");
            sb.append(this.f68);
        }
        return sb.toString();
    }

    /* renamed from: ˊ, reason: contains not printable characters */
    final String m43() {
        String str;
        synchronized (this.f71) {
            str = this.f70;
        }
        return str;
    }

    /* renamed from: ˋ, reason: contains not printable characters */
    final int m45() {
        int i;
        synchronized (this.f71) {
            i = this.f68;
        }
        return i;
    }
}
