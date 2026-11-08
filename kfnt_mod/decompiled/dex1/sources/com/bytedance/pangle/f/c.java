package com.bytedance.pangle.f;

import android.util.ArrayMap;
import android.util.Pair;
import androidx.annotation.RequiresApi;
import androidx.core.view.InputDeviceCompat;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RequiresApi(api = 21)
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class c {

    static class a extends Exception {
    }

    static C0142c a(String str) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(str, "r");
        Throwable th = null;
        try {
            C0142c c0142cA = a(randomAccessFile, f.a(randomAccessFile, -262969152));
            randomAccessFile.close();
            return c0142cA;
        } catch (Throwable th2) {
            if (0 != 0) {
                try {
                    randomAccessFile.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
            } else {
                randomAccessFile.close();
            }
            throw th2;
        }
    }

    private static C0142c a(RandomAccessFile randomAccessFile, m mVar) throws InvalidKeySpecException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, IOException, CertificateException, InvalidAlgorithmParameterException {
        ArrayMap arrayMap = new ArrayMap();
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            try {
                ByteBuffer byteBufferA = f.a(mVar.f4621a);
                int i = 0;
                C0142c c0142cA = null;
                while (byteBufferA.hasRemaining()) {
                    try {
                        i++;
                        c0142cA = a(f.a(byteBufferA), arrayMap, certificateFactory);
                    } catch (a unused) {
                    } catch (IOException | SecurityException | BufferUnderflowException e) {
                        throw new SecurityException("Failed to parse/verify signer #" + i + " block", e);
                    }
                }
                if (i <= 0 || c0142cA == null) {
                    throw new SecurityException("No signers found");
                }
                if (i != 1) {
                    throw new SecurityException("APK Signature Scheme V3 only supports one signer: multiple signers found.");
                }
                if (arrayMap.isEmpty()) {
                    throw new SecurityException("No content digests found");
                }
                f.a(arrayMap, randomAccessFile, mVar);
                if (arrayMap.containsKey(3)) {
                    c0142cA.f4608c = f.a((byte[]) arrayMap.get(3), randomAccessFile.length(), mVar);
                }
                return c0142cA;
            } catch (IOException e2) {
                throw new SecurityException("Failed to read list of signers", e2);
            }
        } catch (CertificateException e3) {
            throw new RuntimeException("Failed to obtain X.509 CertificateFactory", e3);
        }
    }

    private static C0142c a(ByteBuffer byteBuffer, Map<Integer, byte[]> map, CertificateFactory certificateFactory) throws InvalidKeySpecException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, IOException, InvalidAlgorithmParameterException {
        ByteBuffer byteBufferA = f.a(byteBuffer);
        int i = byteBuffer.getInt();
        int i2 = byteBuffer.getInt();
        ByteBuffer byteBufferA2 = f.a(byteBuffer);
        byte[] bArrB = f.b(byteBuffer);
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        int i4 = -1;
        byte[] bArrB2 = null;
        while (true) {
            int i5 = 8;
            boolean z = true;
            if (!byteBufferA2.hasRemaining()) {
                if (i4 == -1) {
                    if (i3 == 0) {
                        throw new SecurityException("No signatures found");
                    }
                    throw new SecurityException("No supported signatures found");
                }
                String strC = f.c(i4);
                Pair<String, ? extends AlgorithmParameterSpec> pairD = f.d(i4);
                String str = (String) pairD.first;
                AlgorithmParameterSpec algorithmParameterSpec = (AlgorithmParameterSpec) pairD.second;
                try {
                    PublicKey publicKeyGeneratePublic = KeyFactory.getInstance(strC).generatePublic(new X509EncodedKeySpec(bArrB));
                    Signature signature = Signature.getInstance(str);
                    signature.initVerify(publicKeyGeneratePublic);
                    if (algorithmParameterSpec != null) {
                        signature.setParameter(algorithmParameterSpec);
                    }
                    signature.update(byteBufferA);
                    if (!signature.verify(bArrB2)) {
                        throw new SecurityException(str + " signature did not verify");
                    }
                    byteBufferA.clear();
                    ByteBuffer byteBufferA3 = f.a(byteBufferA);
                    ArrayList arrayList2 = new ArrayList();
                    int i6 = 0;
                    byte[] bArrB3 = null;
                    while (byteBufferA3.hasRemaining()) {
                        i6++;
                        try {
                            ByteBuffer byteBufferA4 = f.a(byteBufferA3);
                            if (byteBufferA4.remaining() < i5) {
                                throw new IOException("Record too short");
                            }
                            int i7 = byteBufferA4.getInt();
                            arrayList2.add(Integer.valueOf(i7));
                            if (i7 == i4) {
                                bArrB3 = f.b(byteBufferA4);
                            }
                            i5 = 8;
                        } catch (IOException | BufferUnderflowException e) {
                            throw new IOException("Failed to parse digest record #".concat(String.valueOf(i6)), e);
                        }
                    }
                    if (!arrayList.equals(arrayList2)) {
                        throw new SecurityException("Signature algorithms don't match between digests and signatures records");
                    }
                    int iA = f.a(i4);
                    byte[] bArrPut = map.put(Integer.valueOf(iA), bArrB3);
                    if (bArrPut != null && !MessageDigest.isEqual(bArrPut, bArrB3)) {
                        throw new SecurityException(f.b(iA) + " contents digest does not match the digest specified by a preceding signer");
                    }
                    ByteBuffer byteBufferA5 = f.a(byteBufferA);
                    ArrayList arrayList3 = new ArrayList();
                    int i8 = 0;
                    while (byteBufferA5.hasRemaining()) {
                        i8++;
                        byte[] bArrB4 = f.b(byteBufferA5);
                        try {
                            arrayList3.add(new p((X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(bArrB4)), bArrB4));
                        } catch (CertificateException e2) {
                            throw new SecurityException("Failed to decode certificate #".concat(String.valueOf(i8)), e2);
                        }
                    }
                    if (arrayList3.isEmpty()) {
                        throw new SecurityException("No certificates listed");
                    }
                    if (!Arrays.equals(bArrB, ((X509Certificate) arrayList3.get(0)).getPublicKey().getEncoded())) {
                        throw new SecurityException("Public key mismatch between certificate and signature record");
                    }
                    if (byteBufferA.getInt() != i) {
                        throw new SecurityException("minSdkVersion mismatch between signed and unsigned in v3 signer block.");
                    }
                    if (byteBufferA.getInt() != i2) {
                        throw new SecurityException("maxSdkVersion mismatch between signed and unsigned in v3 signer block.");
                    }
                    return a(f.a(byteBufferA), arrayList3, certificateFactory);
                } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | SignatureException | InvalidKeySpecException e3) {
                    throw new SecurityException("Failed to verify " + str + " signature", e3);
                }
            }
            i3++;
            try {
                ByteBuffer byteBufferA6 = f.a(byteBufferA2);
                if (byteBufferA6.remaining() < 8) {
                    throw new SecurityException("Signature record too short");
                }
                int i9 = byteBufferA6.getInt();
                arrayList.add(Integer.valueOf(i9));
                if (i9 != 769 && i9 != 1057 && i9 != 1059 && i9 != 1061) {
                    switch (i9) {
                        case InputDeviceCompat.SOURCE_KEYBOARD /* 257 */:
                        case 258:
                        case 259:
                        case 260:
                            break;
                        default:
                            switch (i9) {
                                case InputDeviceCompat.SOURCE_DPAD /* 513 */:
                                case 514:
                                    break;
                                default:
                                    z = false;
                                    break;
                            }
                    }
                }
                if (z && (i4 == -1 || f.a(i9, i4) > 0)) {
                    bArrB2 = f.b(byteBufferA6);
                    i4 = i9;
                }
            } catch (IOException | BufferUnderflowException e4) {
                throw new SecurityException("Failed to parse signature record #".concat(String.valueOf(i3)), e4);
            }
        }
    }

    private static C0142c a(ByteBuffer byteBuffer, List<X509Certificate> list, CertificateFactory certificateFactory) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, IOException, InvalidAlgorithmParameterException {
        X509Certificate[] x509CertificateArr = (X509Certificate[]) list.toArray(new X509Certificate[list.size()]);
        b bVarA = null;
        while (byteBuffer.hasRemaining()) {
            ByteBuffer byteBufferA = f.a(byteBuffer);
            if (byteBufferA.remaining() < 4) {
                throw new IOException("Remaining buffer too short to contain additional attribute ID. Remaining: " + byteBufferA.remaining());
            }
            if (byteBufferA.getInt() == 1000370060) {
                if (bVarA != null) {
                    throw new SecurityException("Encountered multiple Proof-of-rotation records when verifying APK Signature Scheme v3 signature");
                }
                bVarA = a(byteBufferA, certificateFactory);
                try {
                    if (bVarA.f4604a.size() > 0 && !Arrays.equals(bVarA.f4604a.get(bVarA.f4604a.size() - 1).getEncoded(), x509CertificateArr[0].getEncoded())) {
                        throw new SecurityException("Terminal certificate in Proof-of-rotation record does not match APK signing certificate");
                    }
                } catch (CertificateEncodingException e) {
                    throw new SecurityException("Failed to encode certificate when comparing Proof-of-rotation record and signing certificate", e);
                }
            }
        }
        return new C0142c(x509CertificateArr, bVarA);
    }

    private static b a(ByteBuffer byteBuffer, CertificateFactory certificateFactory) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, IOException, InvalidAlgorithmParameterException {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i = 0;
        try {
            byteBuffer.getInt();
            HashSet hashSet = new HashSet();
            int i2 = -1;
            p pVar = null;
            while (byteBuffer.hasRemaining()) {
                i++;
                ByteBuffer byteBufferA = f.a(byteBuffer);
                ByteBuffer byteBufferA2 = f.a(byteBufferA);
                int i3 = byteBufferA.getInt();
                int i4 = byteBufferA.getInt();
                byte[] bArrB = f.b(byteBufferA);
                if (pVar != null) {
                    Pair<String, ? extends AlgorithmParameterSpec> pairD = f.d(i2);
                    PublicKey publicKey = pVar.getPublicKey();
                    Signature signature = Signature.getInstance((String) pairD.first);
                    signature.initVerify(publicKey);
                    if (pairD.second != null) {
                        signature.setParameter((AlgorithmParameterSpec) pairD.second);
                    }
                    signature.update(byteBufferA2);
                    if (!signature.verify(bArrB)) {
                        throw new SecurityException("Unable to verify signature of certificate #" + i + " using " + ((String) pairD.first) + " when verifying Proof-of-rotation record");
                    }
                }
                byteBufferA2.rewind();
                byte[] bArrB2 = f.b(byteBufferA2);
                int i5 = byteBufferA2.getInt();
                if (pVar != null && i2 != i5) {
                    throw new SecurityException("Signing algorithm ID mismatch for certificate #" + i + " when verifying Proof-of-rotation record");
                }
                pVar = new p((X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(bArrB2)), bArrB2);
                if (hashSet.contains(pVar)) {
                    throw new SecurityException("Encountered duplicate entries in Proof-of-rotation record at certificate #" + i + ".  All signing certificates should be unique");
                }
                hashSet.add(pVar);
                arrayList.add(pVar);
                arrayList2.add(Integer.valueOf(i3));
                i2 = i4;
            }
            return new b(arrayList, arrayList2);
        } catch (IOException | BufferUnderflowException e) {
            throw new IOException("Failed to parse Proof-of-rotation record", e);
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | SignatureException e2) {
            throw new SecurityException("Failed to verify signature over signed data for certificate #0 when verifying Proof-of-rotation record", e2);
        } catch (CertificateException e3) {
            throw new SecurityException("Failed to decode certificate #0 when verifying Proof-of-rotation record", e3);
        }
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        public final List<X509Certificate> f4604a;

        /* renamed from: b, reason: collision with root package name */
        public final List<Integer> f4605b;

        public b(List<X509Certificate> list, List<Integer> list2) {
            this.f4604a = list;
            this.f4605b = list2;
        }
    }

    /* renamed from: com.bytedance.pangle.f.c$c, reason: collision with other inner class name */
    public static class C0142c {

        /* renamed from: a, reason: collision with root package name */
        public final X509Certificate[] f4606a;

        /* renamed from: b, reason: collision with root package name */
        public final b f4607b;

        /* renamed from: c, reason: collision with root package name */
        public byte[] f4608c;

        public C0142c(X509Certificate[] x509CertificateArr, b bVar) {
            this.f4606a = x509CertificateArr;
            this.f4607b = bVar;
        }
    }
}
