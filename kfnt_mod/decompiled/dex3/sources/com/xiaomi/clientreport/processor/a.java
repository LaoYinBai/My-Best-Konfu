package com.xiaomi.clientreport.processor;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.qq.e.comm.constants.ErrorCode;
import com.ss.union.game.sdk.core.base.event.INetUploadStrategy;
import com.xiaomi.clientreport.data.EventClientReport;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.push.ac;
import com.xiaomi.push.bm;
import com.xiaomi.push.bq;
import com.xiaomi.push.h;
import com.xiaomi.push.y;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a implements IEventProcessor {
    protected Context a;

    /* renamed from: a, reason: collision with other field name */
    private HashMap<String, ArrayList<com.xiaomi.clientreport.data.a>> f42a;

    public a(Context context) {
        a(context);
    }

    public static String a(com.xiaomi.clientreport.data.a aVar) {
        return String.valueOf(aVar.production);
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0062, code lost:
    
        r8 = "eventData read from cache file failed cause lengthBuffer < 1 || lengthBuffer > 4K";
        r4 = r4;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.List<java.lang.String> a(java.lang.String r8) throws java.lang.Throwable {
        /*
            r7 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 4
            byte[] r2 = new byte[r1]
            byte[] r3 = new byte[r1]
            r4 = 0
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L71
            java.io.File r6 = new java.io.File     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L71
            r6.<init>(r8)     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L71
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L71
        L15:
            int r8 = r5.read(r2)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            r4 = -1
            if (r8 != r4) goto L1d
            goto L65
        L1d:
            if (r8 == r1) goto L25
            java.lang.String r8 = "eventData read from cache file failed because magicNumber error"
        L21:
            com.xiaomi.channel.commonutils.logger.b.d(r8)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            goto L65
        L25:
            int r8 = com.xiaomi.push.ac.a(r2)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            r6 = -573785174(0xffffffffddccbbaa, float:-1.8440715E18)
            if (r8 == r6) goto L31
            java.lang.String r8 = "eventData read from cache file failed because magicNumber error"
            goto L21
        L31:
            int r8 = r5.read(r3)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            if (r8 != r4) goto L38
            goto L65
        L38:
            if (r8 == r1) goto L3d
            java.lang.String r8 = "eventData read from cache file failed cause lengthBuffer error"
            goto L21
        L3d:
            int r8 = com.xiaomi.push.ac.a(r3)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            r4 = 1
            if (r8 < r4) goto L62
            r4 = 4096(0x1000, float:5.74E-42)
            if (r8 <= r4) goto L49
            goto L62
        L49:
            byte[] r4 = new byte[r8]     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            int r6 = r5.read(r4)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            if (r6 == r8) goto L54
            java.lang.String r8 = "eventData read from cache file failed cause buffer size not equal length"
            goto L21
        L54:
            java.lang.String r8 = r7.bytesToString(r4)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            boolean r4 = android.text.TextUtils.isEmpty(r8)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            if (r4 != 0) goto L15
            r0.add(r8)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6b
            goto L15
        L62:
            java.lang.String r8 = "eventData read from cache file failed cause lengthBuffer < 1 || lengthBuffer > 4K"
            goto L21
        L65:
            com.xiaomi.push.y.a(r5)
            goto L78
        L69:
            r8 = move-exception
            goto L79
        L6b:
            r8 = move-exception
            r4 = r5
            goto L72
        L6e:
            r8 = move-exception
            r5 = r4
            goto L79
        L71:
            r8 = move-exception
        L72:
            com.xiaomi.channel.commonutils.logger.b.a(r8)     // Catch: java.lang.Throwable -> L6e
            com.xiaomi.push.y.a(r4)
        L78:
            return r0
        L79:
            com.xiaomi.push.y.a(r5)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.clientreport.processor.a.a(java.lang.String):java.util.List");
    }

    private void a(RandomAccessFile randomAccessFile, FileLock fileLock) throws IOException {
        if (fileLock != null && fileLock.isValid()) {
            try {
                fileLock.release();
            } catch (IOException e) {
                com.xiaomi.channel.commonutils.logger.b.a(e);
            }
        }
        y.a(randomAccessFile);
    }

    private void a(String str, String str2) {
        EventClientReport eventClientReportA = com.xiaomi.clientreport.manager.a.a(this.a).a(ErrorCode.SERVER_JSON_PARSE_ERROR, "24:" + str + Constants.ACCEPT_TIME_SEPARATOR_SP + str2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(eventClientReportA.toJsonString());
        a(arrayList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v10, types: [java.nio.channels.FileLock] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v7 */
    private com.xiaomi.clientreport.data.a[] a(com.xiaomi.clientreport.data.a[] aVarArr) throws Throwable {
        Closeable closeable;
        RandomAccessFile randomAccessFile;
        BufferedOutputStream bufferedOutputStream;
        String strB = b(aVarArr[0]);
        FileLock fileLockIsEmpty = TextUtils.isEmpty(strB);
        try {
            if (fileLockIsEmpty != 0) {
                return null;
            }
            try {
                File file = new File(strB + ".lock");
                y.m650a(file);
                randomAccessFile = new RandomAccessFile(file, "rw");
                try {
                    fileLockIsEmpty = randomAccessFile.getChannel().lock();
                } catch (Exception e) {
                    e = e;
                    fileLockIsEmpty = 0;
                    bufferedOutputStream = null;
                } catch (Throwable th) {
                    th = th;
                    fileLockIsEmpty = 0;
                    closeable = null;
                }
            } catch (Exception e2) {
                e = e2;
                fileLockIsEmpty = 0;
                randomAccessFile = null;
                bufferedOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                fileLockIsEmpty = 0;
                randomAccessFile = null;
                closeable = null;
            }
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(strB), true));
                try {
                    int i = 0;
                    for (com.xiaomi.clientreport.data.a aVar : aVarArr) {
                        if (aVar != null) {
                            byte[] bArrStringToBytes = stringToBytes(aVar.toJsonString());
                            if (bArrStringToBytes == null || bArrStringToBytes.length < 1 || bArrStringToBytes.length > 4096) {
                                com.xiaomi.channel.commonutils.logger.b.d("event data throw a invalid item ");
                            } else {
                                if (!bq.m138a(this.a, strB)) {
                                    int length = aVarArr.length - i;
                                    com.xiaomi.clientreport.data.a[] aVarArr2 = new com.xiaomi.clientreport.data.a[length];
                                    System.arraycopy(aVarArr, i, aVarArr2, 0, length);
                                    y.a(bufferedOutputStream);
                                    a(randomAccessFile, (FileLock) fileLockIsEmpty);
                                    return aVarArr2;
                                }
                                bufferedOutputStream.write(ac.a(-573785174));
                                bufferedOutputStream.write(ac.a(bArrStringToBytes.length));
                                bufferedOutputStream.write(bArrStringToBytes);
                                bufferedOutputStream.flush();
                                i++;
                            }
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    com.xiaomi.channel.commonutils.logger.b.a("event data write to cache file failed cause exception", e);
                    y.a(bufferedOutputStream);
                    a(randomAccessFile, fileLockIsEmpty);
                    return null;
                }
            } catch (Exception e4) {
                e = e4;
                bufferedOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                closeable = null;
                y.a(closeable);
                a(randomAccessFile, fileLockIsEmpty);
                throw th;
            }
            y.a(bufferedOutputStream);
            a(randomAccessFile, fileLockIsEmpty);
            return null;
        } catch (Throwable th4) {
            th = th4;
        }
    }

    private String b(com.xiaomi.clientreport.data.a aVar) {
        File externalFilesDir = this.a.getExternalFilesDir(INetUploadStrategy.KEY_EVENT);
        String strA = a(aVar);
        if (externalFilesDir == null) {
            return null;
        }
        String str = externalFilesDir.getAbsolutePath() + File.separator + strA;
        for (int i = 0; i < 100; i++) {
            String str2 = str + i;
            if (bq.m138a(this.a, str2)) {
                return str2;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0038 A[PHI: r3 r4 r5
  0x0038: PHI (r3v15 java.nio.channels.FileLock) = (r3v3 java.nio.channels.FileLock), (r3v1 java.nio.channels.FileLock), (r3v1 java.nio.channels.FileLock) binds: [B:70:0x0111, B:32:0x0093, B:19:0x0036] A[DONT_GENERATE, DONT_INLINE]
  0x0038: PHI (r4v12 java.io.RandomAccessFile) = (r4v3 java.io.RandomAccessFile), (r4v1 java.io.RandomAccessFile), (r4v1 java.io.RandomAccessFile) binds: [B:70:0x0111, B:32:0x0093, B:19:0x0036] A[DONT_GENERATE, DONT_INLINE]
  0x0038: PHI (r5v9 java.io.File) = (r5v3 java.io.File), (r5v1 java.io.File), (r5v1 java.io.File) binds: [B:70:0x0111, B:32:0x0093, B:19:0x0036] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.xiaomi.clientreport.processor.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a() throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 307
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.clientreport.processor.a.a():void");
    }

    public void a(Context context) {
        this.a = context;
    }

    @Override // com.xiaomi.clientreport.processor.d
    /* renamed from: a, reason: collision with other method in class */
    public void mo30a(com.xiaomi.clientreport.data.a aVar) {
        if ((aVar instanceof EventClientReport) && this.f42a != null) {
            EventClientReport eventClientReport = (EventClientReport) aVar;
            String strA = a((com.xiaomi.clientreport.data.a) eventClientReport);
            ArrayList<com.xiaomi.clientreport.data.a> arrayList = this.f42a.get(strA);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
            arrayList.add(eventClientReport);
            this.f42a.put(strA, arrayList);
        }
    }

    public void a(List<String> list) {
        bq.a(this.a, list);
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m31a(com.xiaomi.clientreport.data.a[] aVarArr) throws Throwable {
        if (aVarArr == null || aVarArr.length == 0 || aVarArr[0] == null) {
            com.xiaomi.channel.commonutils.logger.b.m22a("event data write to cache file failed because data null");
            return;
        }
        do {
            aVarArr = a(aVarArr);
            if (aVarArr == null || aVarArr.length <= 0) {
                return;
            }
        } while (aVarArr[0] != null);
    }

    @Override // com.xiaomi.clientreport.processor.d
    public void b() throws Throwable {
        if (this.f42a == null) {
            return;
        }
        if (this.f42a.size() > 0) {
            Iterator<String> it = this.f42a.keySet().iterator();
            while (it.hasNext()) {
                ArrayList<com.xiaomi.clientreport.data.a> arrayList = this.f42a.get(it.next());
                if (arrayList != null && arrayList.size() > 0) {
                    com.xiaomi.clientreport.data.a[] aVarArr = new com.xiaomi.clientreport.data.a[arrayList.size()];
                    arrayList.toArray(aVarArr);
                    m31a(aVarArr);
                }
            }
        }
        this.f42a.clear();
    }

    @Override // com.xiaomi.clientreport.processor.IEventProcessor
    public String bytesToString(byte[] bArr) {
        byte[] bArrA;
        if (bArr != null && bArr.length >= 1) {
            if (!com.xiaomi.clientreport.manager.a.a(this.a).m27a().isEventEncrypted()) {
                return bm.a(bArr);
            }
            String strA = bq.a(this.a);
            if (!TextUtils.isEmpty(strA) && (bArrA = bq.a(strA)) != null && bArrA.length > 0) {
                try {
                    return bm.a(Base64.decode(h.a(bArrA, bArr), 2));
                } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
                    com.xiaomi.channel.commonutils.logger.b.a(e);
                }
            }
        }
        return null;
    }

    @Override // com.xiaomi.clientreport.processor.IEventProcessor
    public void setEventMap(HashMap<String, ArrayList<com.xiaomi.clientreport.data.a>> map) {
        this.f42a = map;
    }

    @Override // com.xiaomi.clientreport.processor.IEventProcessor
    public byte[] stringToBytes(String str) {
        byte[] bArrA;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (!com.xiaomi.clientreport.manager.a.a(this.a).m27a().isEventEncrypted()) {
            return bm.m134a(str);
        }
        String strA = bq.a(this.a);
        byte[] bArrM134a = bm.m134a(str);
        if (!TextUtils.isEmpty(strA) && bArrM134a != null && bArrM134a.length > 1 && (bArrA = bq.a(strA)) != null) {
            try {
                if (bArrA.length > 1) {
                    return h.b(bArrA, Base64.encode(bArrM134a, 2));
                }
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.logger.b.a(e);
            }
        }
        return null;
    }
}
