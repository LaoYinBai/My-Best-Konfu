package com.meizu.cloud.pushsdk.a.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.heytap.mcssdk.mode.CommandMessage;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static a b;
    private static final Object c = new Object();
    private byte[] d;
    private byte[] e;
    private byte[] f;
    private byte[] g;
    private byte[] h;
    private PublicKey i;
    private final SharedPreferences j;
    private final SharedPreferences k;
    private long l = 0;
    String a = "88&*5a9*4&a122ek";

    private a(Context context) throws Throwable {
        this.j = context.getSharedPreferences("com.x.y.1", 0);
        this.k = context.getSharedPreferences("com.x.y.2", 0);
        Integer.parseInt(this.j.getString("keyTimeout", PushConstants.PUSH_TYPE_NOTIFY));
        this.j.getLong("createDate", 0L);
        e();
        if (this.d != null && this.d.length != 0) {
            if (this.e == null || this.e.length == 0) {
                this.i = b(context);
                if (this.i != null) {
                    h();
                    return;
                }
                return;
            }
            return;
        }
        this.i = b(context);
        if (this.i != null) {
            f();
            return;
        }
        this.j.edit().clear().apply();
        try {
            d();
            this.i = b(context);
            if (this.i != null) {
                f();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static a a() {
        if (b != null) {
            return b;
        }
        throw new IllegalStateException("KeyMgr is not initialised - invoke at least once with parameterised init/get");
    }

    private String a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            try {
                try {
                    int i = inputStream.read();
                    if (i == -1) {
                        String string = byteArrayOutputStream.toString();
                        try {
                            byteArrayOutputStream.close();
                            return string;
                        } catch (IOException unused) {
                            return string;
                        }
                    }
                    byteArrayOutputStream.write(i);
                } catch (IOException unused2) {
                    return null;
                }
            } catch (IOException unused3) {
                byteArrayOutputStream.close();
                return null;
            } catch (Throwable th) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused4) {
                }
                throw th;
            }
        }
    }

    public static void a(Context context) {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new a(context);
                }
            }
        }
    }

    private PublicKey b(Context context) {
        b("load publicKey from preference");
        String string = this.k.getString("publicKey", "");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(string, 2)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeySpecException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private void b(String str) {
        DebugLogger.d("HttpKeyMgr", str);
    }

    private void c(String str) {
        DebugLogger.e("HttpKeyMgr", str);
    }

    private void d() throws Throwable {
        InputStream inputStream;
        Throwable th;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(PushConstants.URL_DOWNLOAD_PUBLIC_KEY).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            try {
                httpURLConnection.setRequestMethod("GET");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            httpURLConnection.setRequestProperty("Charset", C.UTF8_NAME);
            try {
                b("code = " + httpURLConnection.getResponseCode());
                inputStream = httpURLConnection.getInputStream();
                if (inputStream != null) {
                    try {
                        String strA = a(inputStream);
                        b("body = " + strA);
                        if (!TextUtils.isEmpty(strA)) {
                            try {
                                JSONObject jSONObject = new JSONObject(strA);
                                if (jSONObject.getInt(CommandMessage.CODE) == 200) {
                                    String string = jSONObject.getString("value");
                                    SharedPreferences.Editor editorEdit = this.k.edit();
                                    editorEdit.putString("publicKey", string);
                                    editorEdit.apply();
                                }
                            } catch (Exception e2) {
                                c("downloadPublicKey message error " + e2.getMessage());
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException unused) {
                            }
                        }
                        httpURLConnection.disconnect();
                        throw th;
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused2) {
                    }
                }
                httpURLConnection.disconnect();
            } catch (Throwable th3) {
                inputStream = null;
                th = th3;
            }
        } catch (MalformedURLException unused3) {
        }
    }

    private void e() {
        b("loadKeys");
        String string = this.j.getString("sKey64", "");
        b("saved sKey64: " + string);
        if (!TextUtils.isEmpty(string)) {
            this.h = string.getBytes();
        }
        String string2 = this.j.getString("aKey64", "");
        b("saved aKey64: " + string2);
        if (!TextUtils.isEmpty(string2)) {
            this.g = string2.getBytes();
            this.e = Base64.decode(this.g, 2);
        }
        String string3 = this.j.getString("rKey64", "");
        b("saved rKey64: " + string3);
        if (TextUtils.isEmpty(string3)) {
            return;
        }
        this.f = string3.getBytes();
        this.d = Base64.decode(this.f, 2);
        b("saved rKey: " + new String(this.d));
    }

    private void f() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        g();
        h();
    }

    private void g() throws NoSuchAlgorithmException {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            this.d = keyGenerator.generateKey().getEncoded();
            this.f = Base64.encode(this.d, 2);
            b("***** rKey64: " + new String(this.f));
            SharedPreferences.Editor editorEdit = this.j.edit();
            editorEdit.putString("rKey64", new String(this.f));
            editorEdit.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void h() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, this.i);
            this.e = cipher.doFinal(this.d);
            this.g = Base64.encode(this.e, 2);
            b("***** aKey64: " + new String(this.g));
            SharedPreferences.Editor editorEdit = this.j.edit();
            editorEdit.putString("aKey64", new String(this.g));
            editorEdit.apply();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        } catch (BadPaddingException e3) {
            e3.printStackTrace();
        } catch (IllegalBlockSizeException e4) {
            e4.printStackTrace();
        } catch (NoSuchPaddingException e5) {
            e5.printStackTrace();
        }
    }

    public void a(String str) {
        this.h = str.getBytes();
        SharedPreferences.Editor editorEdit = this.j.edit();
        editorEdit.putString("sKey64", new String(this.h));
        editorEdit.apply();
    }

    public byte[] a(byte[] bArr) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        String str;
        if (this.d == null || this.d.length == 0) {
            str = "rKey null!";
        } else {
            if (bArr != null && bArr.length != 0) {
                b(">>>>>>>>>> encrypt input >>>>>>>>>>\n" + new String(Base64.encode(bArr, 2)));
                b("<<<<<<<<<< encrypt input <<<<<<<<<<");
                try {
                    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                    cipher.init(1, new SecretKeySpec(this.d, "AES"), new IvParameterSpec(this.d));
                    byte[] bArrDoFinal = cipher.doFinal(bArr);
                    b(">>>>>>>>>> encrypt output >>>>>>>>>>\n" + new String(Base64.encode(bArrDoFinal, 2)));
                    b("<<<<<<<<<< encrypt output <<<<<<<<<<");
                    return bArrDoFinal;
                } catch (InvalidAlgorithmParameterException e) {
                    e.printStackTrace();
                    return null;
                } catch (InvalidKeyException e2) {
                    e2.printStackTrace();
                    return null;
                } catch (NoSuchAlgorithmException e3) {
                    e3.printStackTrace();
                    return null;
                } catch (BadPaddingException e4) {
                    e4.printStackTrace();
                    return null;
                } catch (IllegalBlockSizeException e5) {
                    e5.printStackTrace();
                    return null;
                } catch (NoSuchPaddingException e6) {
                    e6.printStackTrace();
                    return null;
                }
            }
            str = "input null!";
        }
        c(str);
        return null;
    }

    public byte[] b() {
        return this.g;
    }

    public byte[] c() {
        return this.h;
    }
}
