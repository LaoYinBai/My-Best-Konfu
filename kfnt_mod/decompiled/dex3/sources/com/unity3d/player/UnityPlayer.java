package com.unity3d.player;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.TypedValue;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider;
import com.ss.union.game.sdk.common.util.StringEncryptUtils;
import com.unity3d.player.n;
import com.unity3d.player.s;
import com.unity3d.splash.UnityAds;
import com.unity3d.splash.services.core.device.Device;
import com.unity3d.splash.services.core.device.Storage;
import com.unity3d.splash.services.core.device.StorageManager;
import com.unity3d.splash.services.core.log.DeviceLog;
import com.unity3d.splash.services.core.request.WebRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import kotlin.UByte;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UnityPlayer extends FrameLayout implements com.unity3d.player.f {
    public static Activity currentActivity;
    g a;
    k b;
    AlertDialog c;
    private int d;
    private boolean e;
    private boolean f;
    public boolean finishLaunchScreenAds;
    private p g;
    private final ConcurrentLinkedQueue h;
    private BroadcastReceiver i;
    private boolean j;
    private c k;
    private TelephonyManager l;
    private ClipboardManager m;
    private n n;
    private GoogleARCoreApi o;
    private a p;
    private m q;
    private Camera2Wrapper r;
    private Context s;
    public boolean shouldShowLaunchScreenAds;
    private SurfaceView t;
    private boolean u;
    private s v;
    private Handler w;
    private Timer x;
    private TimerTask y;

    class a implements SensorEventListener {
        a() {
        }

        @Override // android.hardware.SensorEventListener
        public final void onAccuracyChanged(Sensor sensor, int i) {
        }

        @Override // android.hardware.SensorEventListener
        public final void onSensorChanged(SensorEvent sensorEvent) {
        }
    }

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    static final class b {
        public static final int a = 1;
        public static final int b = 2;
        public static final int c = 3;
        private static final /* synthetic */ int[] d = {a, b, c};
    }

    private class c extends PhoneStateListener {
        private c() {
        }

        /* synthetic */ c(UnityPlayer unityPlayer, byte b) {
            this();
        }

        @Override // android.telephony.PhoneStateListener
        public final void onCallStateChanged(int i, String str) {
            UnityPlayer.this.nativeMuteMasterAudio(i == 1);
        }
    }

    class d extends AsyncTask {
        d() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public final Void doInBackground(String... strArr) throws JSONException {
            Boolean.valueOf(false);
            String str = strArr[0];
            String str2 = strArr[1];
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("game_bundle_id", str);
                jSONObject.put("game_bundle_hash", str2);
                WebRequest webRequest = new WebRequest("https://check.unity.cn/api/check-license", "POST", null);
                webRequest.setBody(jSONObject.toString());
                String strMakeRequest = webRequest.makeRequest();
                if (webRequest.getResponseCode() == 200 && Boolean.valueOf(new JSONObject(strMakeRequest).optBoolean("registered")).booleanValue()) {
                    com.unity3d.player.g.Log(4, "Game Bundle Registered");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    class e extends AsyncTask {
        e() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public final Void doInBackground(String... strArr) throws JSONException {
            Boolean.valueOf(false);
            Boolean.valueOf(false);
            Boolean.valueOf(false);
            String str = strArr[0];
            try {
                String str2 = Build.MANUFACTURER + "/" + Build.MODEL + "/" + Build.DEVICE;
                String strC = UnityPlayer.c(str);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("game_bundle_hash", strC);
                jSONObject.put(com.umeng.commonsdk.proguard.o.C, str2);
                jSONObject.put("platform", "android");
                jSONObject.put("unity_hash", UnityPlayer.this.getDeviceId());
                jSONObject.put("splash_sdk_version", "2018.4.36f1");
                jSONObject.put("mcc", strArr[1]);
                jSONObject.put("builder", UnityPlayer.this.getBuilderUserId());
                WebRequest webRequest = new WebRequest("https://check.unity.cn/api/check-license" + UnityPlayer.b(jSONObject), "GET", null);
                String strMakeRequest = webRequest.makeRequest();
                if (webRequest.getResponseCode() == 200) {
                    JSONObject jSONObject2 = new JSONObject(strMakeRequest);
                    Boolean boolValueOf = Boolean.valueOf(jSONObject2.optBoolean("blocked"));
                    Boolean boolValueOf2 = Boolean.valueOf(jSONObject2.optBoolean("show_ads"));
                    String strOptString = jSONObject2.optString("game_id");
                    if (!Boolean.valueOf(jSONObject2.optBoolean("registered")).booleanValue()) {
                        UnityPlayer.this.new d().execute(str, strC);
                    }
                    if (UnityPlayer.currentActivity != null) {
                        String strO = UnityPlayer.this.o();
                        if (strO == null || strO.length() <= 0) {
                            strO = strOptString;
                        } else {
                            boolValueOf2 = true;
                        }
                        SharedPreferences.Editor editorEdit = UnityPlayer.currentActivity.getSharedPreferences("game_detail", 0).edit();
                        editorEdit.putString("game_id", strO);
                        editorEdit.putBoolean("show_ads", boolValueOf2.booleanValue());
                        editorEdit.putBoolean("blocked", boolValueOf.booleanValue());
                        editorEdit.putString("url", jSONObject2.optString("url"));
                        editorEdit.putString("hash", jSONObject2.optString("hash"));
                        editorEdit.putString("version", jSONObject2.optString("version"));
                        editorEdit.commit();
                        if (!boolValueOf.booleanValue() && boolValueOf2.booleanValue() && strO != null && strO != "") {
                            UnityAds.initialize(UnityPlayer.currentActivity, strO, null);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    enum f {
        PAUSE,
        RESUME,
        QUIT,
        SURFACE_LOST,
        SURFACE_ACQUIRED,
        FOCUS_LOST,
        FOCUS_GAINED,
        NEXT_FRAME,
        SPLASH_ADS_DISMISS
    }

    private class g extends Thread {
        Handler a;
        boolean b;
        boolean c;
        int d;
        int e;
        boolean f;
        int g;
        int h;

        private g() {
            this.b = false;
            this.c = false;
            this.d = b.b;
            this.e = 0;
            this.f = false;
            this.g = 5;
            this.h = 5;
        }

        /* synthetic */ g(UnityPlayer unityPlayer, byte b) {
            this();
        }

        private void a(f fVar) {
            if (this.a != null) {
                Message.obtain(this.a, 2269, fVar).sendToTarget();
            }
        }

        public final void a() {
            a(f.QUIT);
        }

        public final void a(Runnable runnable) {
            if (this.a == null) {
                return;
            }
            a(f.PAUSE);
            Message.obtain(this.a, runnable).sendToTarget();
        }

        public final void b() {
            a(f.RESUME);
        }

        public final void b(Runnable runnable) {
            if (this.a == null) {
                return;
            }
            a(f.SURFACE_LOST);
            Message.obtain(this.a, runnable).sendToTarget();
        }

        public final void c() {
            a(f.FOCUS_GAINED);
        }

        public final void c(Runnable runnable) {
            if (this.a == null) {
                return;
            }
            Message.obtain(this.a, runnable).sendToTarget();
            a(f.SURFACE_ACQUIRED);
        }

        public final void d() {
            a(f.FOCUS_LOST);
        }

        public final void d(Runnable runnable) {
            if (this.a != null) {
                Message.obtain(this.a, runnable).sendToTarget();
            }
        }

        public final void e() {
            a(f.SPLASH_ADS_DISMISS);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            setName("UnityMain");
            Looper.prepare();
            this.a = new Handler(new Handler.Callback() { // from class: com.unity3d.player.UnityPlayer.g.1
                private void a() {
                    if (g.this.d == b.c && g.this.c) {
                        UnityPlayer.this.nativeFocusChanged(true);
                        g.this.d = b.a;
                    }
                }

                @Override // android.os.Handler.Callback
                public final boolean handleMessage(Message message) {
                    if (message.what != 2269) {
                        return false;
                    }
                    f fVar = (f) message.obj;
                    if (fVar == f.NEXT_FRAME) {
                        g.this.e--;
                        UnityPlayer.this.executeGLThreadJobs();
                        if (!g.this.b || !g.this.c) {
                            return true;
                        }
                        if (g.this.g >= 0) {
                            if (g.this.g == 0 && UnityPlayer.this.m()) {
                                UnityPlayer.this.a();
                            }
                            g.this.g--;
                        }
                        if (g.this.g == 0 && UnityPlayer.this.shouldShowLaunchScreenAds) {
                            UnityPlayer.this.b();
                        }
                        if (g.this.f && g.this.h >= 0) {
                            if (g.this.h == 0) {
                                UnityPlayer.this.c();
                            }
                            g.this.h--;
                        }
                        if (!UnityPlayer.this.isFinishing() && !UnityPlayer.this.nativeRender()) {
                            UnityPlayer.this.g();
                        }
                    } else if (fVar == f.QUIT) {
                        Looper.myLooper().quit();
                    } else if (fVar == f.RESUME) {
                        g.this.b = true;
                    } else if (fVar == f.PAUSE) {
                        g.this.b = false;
                    } else if (fVar == f.SURFACE_LOST) {
                        g.this.c = false;
                    } else {
                        if (fVar == f.SURFACE_ACQUIRED) {
                            g.this.c = true;
                        } else if (fVar == f.FOCUS_LOST) {
                            if (g.this.d == b.a) {
                                UnityPlayer.this.nativeFocusChanged(false);
                            }
                            g.this.d = b.b;
                        } else if (fVar == f.FOCUS_GAINED) {
                            g.this.d = b.c;
                        } else if (fVar == f.SPLASH_ADS_DISMISS) {
                            g.this.f = true;
                        }
                        a();
                    }
                    if (g.this.b && g.this.e <= 0) {
                        Message.obtain(g.this.a, 2269, f.NEXT_FRAME).sendToTarget();
                        g.this.e++;
                    }
                    return true;
                }
            });
            Looper.loop();
        }
    }

    private abstract class h implements Runnable {
        private h() {
        }

        /* synthetic */ h(UnityPlayer unityPlayer, byte b) {
            this();
        }

        public abstract void a();

        @Override // java.lang.Runnable
        public final void run() {
            if (UnityPlayer.this.isFinishing()) {
                return;
            }
            a();
        }
    }

    static {
        System.loadLibrary("atfv_wk");
        new o().a();
    }

    public UnityPlayer(Context context) {
        super(context);
        this.d = -1;
        byte b2 = 0;
        this.e = false;
        this.f = true;
        this.g = new p();
        this.h = new ConcurrentLinkedQueue();
        this.i = null;
        this.a = new g(this, b2);
        this.j = false;
        this.k = new c(this, b2);
        this.o = null;
        this.p = new a();
        this.r = null;
        this.finishLaunchScreenAds = false;
        this.shouldShowLaunchScreenAds = false;
        this.b = null;
        this.c = null;
        this.w = null;
        this.x = new Timer();
        this.y = null;
        if (context instanceof Activity) {
            currentActivity = (Activity) context;
            this.d = currentActivity.getRequestedOrientation();
        }
        a(currentActivity);
        this.s = context;
        if (currentActivity != null && m()) {
            this.n = new n(this.s, n.a.a()[getSplashMode()]);
            addView(this.n);
        }
        p();
        String strA = a(this.s.getApplicationInfo());
        if (!p.c()) {
            com.unity3d.player.g.Log(6, "Your hardware does not support this application.");
            AlertDialog alertDialogCreate = new AlertDialog.Builder(this.s).setTitle("Failure to initialize!").setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: com.unity3d.player.UnityPlayer.1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    UnityPlayer.this.g();
                }
            }).setMessage("Your hardware does not support this application.\n\n" + strA + "\n\n Press OK to quit.").create();
            alertDialogCreate.setCancelable(false);
            alertDialogCreate.show();
            return;
        }
        initJni(context);
        this.g.c(true);
        q();
        this.t = e();
        this.t.setContentDescription(b(context));
        addView(this.t);
        if (this.shouldShowLaunchScreenAds) {
            this.q = a(this.s);
            if (this.q != null) {
                addView(this.q);
            } else {
                this.finishLaunchScreenAds = true;
            }
        }
        if (this.n != null) {
            bringChildToFront(this.n);
        }
        this.u = false;
        nativeInitWebRequest(UnityWebRequest.class);
        this.l = (TelephonyManager) this.s.getSystemService("phone");
        this.m = (ClipboardManager) this.s.getSystemService("clipboard");
        this.r = new Camera2Wrapper(this.s);
        this.a.start();
    }

    public static void UnitySendMessage(String str, String str2, String str3) {
        if (p.c()) {
            try {
                nativeUnitySendMessage(str, str2, str3.getBytes("UTF-8"));
                return;
            } catch (UnsupportedEncodingException unused) {
                return;
            }
        }
        com.unity3d.player.g.Log(5, "Native libraries not loaded - dropping message for " + str + "." + str2);
    }

    private m a(Context context) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        StorageManager.init(context);
        Storage storage = StorageManager.getStorage(StorageManager.StorageType.PRIVATE);
        if (storage == null) {
            return null;
        }
        Object obj = storage.get("splash-show");
        if (obj != null) {
            try {
                l lVar = new l(new JSONObject(obj.toString()));
                if (lVar.a() && lVar.g() >= System.currentTimeMillis()) {
                    DeviceLog.info("splash show");
                    storage.delete("splash-show");
                    return new m(this.s, this, lVar);
                }
            } catch (JSONException unused) {
            }
        }
        Object obj2 = storage.get("splash-show-no-fill");
        if (obj2 != null) {
            try {
                DeviceLog.info("splash show no fill");
                l lVar2 = new l(new JSONObject(obj2.toString()));
                if (lVar2.a()) {
                    return new m(this.s, this, lVar2);
                }
            } catch (JSONException unused2) {
            }
        }
        DeviceLog.info("splash show nothing");
        return null;
    }

    private static String a(ApplicationInfo applicationInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append(applicationInfo.nativeLibraryDir);
        sb.append("/libmain.so");
        try {
            System.loadLibrary("main");
            if (NativeLoader.load(applicationInfo.nativeLibraryDir)) {
                p.a();
                return "";
            }
            com.unity3d.player.g.Log(6, "NativeLoader.load failure, Unity libraries were not loaded.");
            return "NativeLoader.load failure, Unity libraries were not loaded.";
        } catch (SecurityException e2) {
            return b(e2.toString());
        } catch (UnsatisfiedLinkError e3) {
            return b(e3.toString());
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b2 : bArr) {
            sb.append(String.format("%02x", Integer.valueOf(b2 & UByte.MAX_VALUE)));
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        a(new Runnable() { // from class: com.unity3d.player.UnityPlayer.22
            @Override // java.lang.Runnable
            public final void run() {
                UnityPlayer.this.removeView(UnityPlayer.this.n);
                UnityPlayer.h(UnityPlayer.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, Surface surface) {
        if (this.e) {
            return;
        }
        b(0, surface);
    }

    private static void a(Activity activity) {
        View decorView;
        if (activity == null || !activity.getIntent().getBooleanExtra("android.intent.extra.VR_LAUNCH", false) || activity.getWindow() == null || (decorView = activity.getWindow().getDecorView()) == null) {
            return;
        }
        decorView.setSystemUiVisibility(7);
    }

    private void a(View view, View view2) {
        boolean z;
        if (this.g.d()) {
            z = false;
        } else {
            pause();
            z = true;
        }
        if (view != null) {
            ViewParent parent = view.getParent();
            if (!(parent instanceof UnityPlayer) || ((UnityPlayer) parent) != this) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(view);
                }
                addView(view);
                bringChildToFront(view);
                view.setVisibility(0);
            }
        }
        if (view2 != null && view2.getParent() == this) {
            view2.setVisibility(8);
            removeView(view2);
        }
        if (z) {
            resume();
        }
    }

    private void a(h hVar) {
        if (isFinishing()) {
            return;
        }
        c(hVar);
    }

    private static String b(Context context) {
        return context.getResources().getString(context.getResources().getIdentifier("game_view_content_description", PushMultiProcessSharedProvider.STRING_TYPE, context.getPackageName()));
    }

    private static String b(String str) {
        String str2 = "Failed to load 'libmain.so'\n\n" + str;
        com.unity3d.player.g.Log(6, str2);
        return str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(JSONObject jSONObject) throws UnsupportedEncodingException {
        if (jSONObject == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> itKeys = jSONObject.keys();
        String str = "?";
        while (true) {
            sb.append(str);
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                String strOptString = jSONObject.optString(next);
                if (strOptString != "") {
                    try {
                        strOptString = URLEncoder.encode(strOptString, "utf-8");
                    } catch (UnsupportedEncodingException e2) {
                        e2.printStackTrace();
                    }
                    sb.append(next);
                    sb.append("=");
                    sb.append(strOptString);
                    str = "&";
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        a(new Runnable() { // from class: com.unity3d.player.UnityPlayer.23
            @Override // java.lang.Runnable
            public final void run() {
                if (UnityPlayer.this.q != null) {
                    UnityPlayer.this.bringChildToFront(UnityPlayer.this.q);
                    UnityPlayer.this.q.a();
                }
            }
        });
    }

    private static void b(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    private boolean b(final int i, final Surface surface) {
        if (!p.c() || !this.g.e()) {
            return false;
        }
        final Semaphore semaphore = new Semaphore(0);
        Runnable runnable = new Runnable() { // from class: com.unity3d.player.UnityPlayer.28
            @Override // java.lang.Runnable
            public final void run() {
                UnityPlayer.this.nativeRecreateGfxState(i, surface);
                semaphore.release();
            }
        };
        if (i != 0) {
            runnable.run();
        } else if (surface == null) {
            this.a.b(runnable);
        } else {
            this.a.c(runnable);
        }
        if (surface != null || i != 0) {
            return true;
        }
        try {
            if (semaphore.tryAcquire(4L, TimeUnit.SECONDS)) {
                return true;
            }
            com.unity3d.player.g.Log(5, "Timeout while trying detaching primary window.");
            return true;
        } catch (InterruptedException unused) {
            com.unity3d.player.g.Log(5, "UI thread got interrupted while trying to detach the primary window from the Unity Engine.");
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String c(String str) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(StringEncryptUtils.SHA_256);
            messageDigest.update(str.getBytes());
            return a(messageDigest.digest());
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.q != null) {
            final AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(500L);
            alphaAnimation.setFillAfter(true);
            alphaAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.unity3d.player.UnityPlayer.24
                @Override // android.view.animation.Animation.AnimationListener
                public final void onAnimationEnd(Animation animation) {
                    UnityPlayer.this.a(new Runnable() { // from class: com.unity3d.player.UnityPlayer.24.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            UnityPlayer.this.removeView(UnityPlayer.this.q);
                        }
                    });
                }

                @Override // android.view.animation.Animation.AnimationListener
                public final void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public final void onAnimationStart(Animation animation) {
                }
            });
            a(new Runnable() { // from class: com.unity3d.player.UnityPlayer.25
                @Override // java.lang.Runnable
                public final void run() {
                    UnityPlayer.this.q.startAnimation(alphaAnimation);
                }
            });
        }
    }

    private void c(Runnable runnable) {
        if (p.c()) {
            if (Thread.currentThread() == this.a) {
                runnable.run();
            } else {
                this.h.add(runnable);
            }
        }
    }

    private static boolean d() {
        if (currentActivity == null) {
            return false;
        }
        TypedValue typedValue = new TypedValue();
        return currentActivity.getTheme().resolveAttribute(R.attr.windowIsTranslucent, typedValue, true) && typedValue.type == 18 && typedValue.data == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SurfaceView e() {
        SurfaceView surfaceView = new SurfaceView(this.s);
        surfaceView.setId(this.s.getResources().getIdentifier("unitySurfaceView", AdDownloadModel.JsonKey.ID, this.s.getPackageName()));
        if (d()) {
            surfaceView.getHolder().setFormat(-3);
            surfaceView.setZOrderOnTop(true);
        } else {
            surfaceView.getHolder().setFormat(-1);
        }
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() { // from class: com.unity3d.player.UnityPlayer.26
            @Override // android.view.SurfaceHolder.Callback
            public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                UnityPlayer.this.a(0, surfaceHolder.getSurface());
                UnityPlayer.this.f();
            }

            @Override // android.view.SurfaceHolder.Callback
            public final void surfaceCreated(SurfaceHolder surfaceHolder) {
                UnityPlayer.this.a(0, surfaceHolder.getSurface());
            }

            @Override // android.view.SurfaceHolder.Callback
            public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                UnityPlayer.this.a(0, (Surface) null);
            }
        });
        surfaceView.setFocusable(true);
        surfaceView.setFocusableInTouchMode(true);
        return surfaceView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (p.c() && this.g.e()) {
            this.a.d(new Runnable() { // from class: com.unity3d.player.UnityPlayer.27
                @Override // java.lang.Runnable
                public final void run() {
                    UnityPlayer.this.nativeSendSurfaceChangedEvent();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (!(this.s instanceof Activity) || ((Activity) this.s).isFinishing()) {
            return;
        }
        ((Activity) this.s).finish();
    }

    static /* synthetic */ n h(UnityPlayer unityPlayer) {
        unityPlayer.n = null;
        return null;
    }

    private void h() {
        reportSoftInputStr(null, 1, true);
        if (this.g.g()) {
            if (p.c()) {
                final Semaphore semaphore = new Semaphore(0);
                this.a.a(isFinishing() ? new Runnable() { // from class: com.unity3d.player.UnityPlayer.3
                    @Override // java.lang.Runnable
                    public final void run() {
                        UnityPlayer.this.i();
                        semaphore.release();
                    }
                } : new Runnable() { // from class: com.unity3d.player.UnityPlayer.4
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (!UnityPlayer.this.nativePause()) {
                            semaphore.release();
                            return;
                        }
                        UnityPlayer.p(UnityPlayer.this);
                        UnityPlayer.this.i();
                        semaphore.release(2);
                    }
                });
                try {
                    if (!semaphore.tryAcquire(4L, TimeUnit.SECONDS)) {
                        com.unity3d.player.g.Log(5, "Timeout while trying to pause the Unity Engine.");
                    }
                } catch (InterruptedException unused) {
                    com.unity3d.player.g.Log(5, "UI thread got interrupted while trying to pause the Unity Engine.");
                }
                if (semaphore.drainPermits() > 0) {
                    destroy();
                }
            }
            this.g.d(false);
            this.g.b(true);
            if (this.j) {
                this.l.listen(this.k, 0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        nativeDone();
        this.g.c(false);
    }

    private final native void initJni(Context context);

    private void j() {
        if (this.g.f()) {
            this.g.d(true);
            c(new Runnable() { // from class: com.unity3d.player.UnityPlayer.6
                @Override // java.lang.Runnable
                public final void run() {
                    UnityPlayer.this.nativeResume();
                }
            });
            this.a.b();
        }
    }

    private static void k() {
        if (p.c()) {
            if (!NativeLoader.unload()) {
                throw new UnsatisfiedLinkError("Unable to unload libraries from libmain.so");
            }
            p.b();
        }
    }

    private ApplicationInfo l() {
        return this.s.getPackageManager().getApplicationInfo(this.s.getPackageName(), 128);
    }

    protected static boolean loadLibraryStatic(String str) {
        StringBuilder sb;
        try {
            System.loadLibrary(str);
            return true;
        } catch (Exception e2) {
            sb = new StringBuilder("Unknown error ");
            sb.append(e2);
            com.unity3d.player.g.Log(6, sb.toString());
            return false;
        } catch (UnsatisfiedLinkError unused) {
            sb = new StringBuilder("Unable to find ");
            sb.append(str);
            com.unity3d.player.g.Log(6, sb.toString());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean m() {
        try {
            return l().metaData.getBoolean("unity.splash-enable");
        } catch (Exception unused) {
            return false;
        }
    }

    private boolean n() {
        try {
            return l().metaData.getBoolean("unity.tango-enable");
        } catch (Exception unused) {
            return false;
        }
    }

    private final native void nativeDone();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeFocusChanged(boolean z);

    private final native void nativeInitWebRequest(Class cls);

    private final native boolean nativeInjectEvent(InputEvent inputEvent);

    /* JADX INFO: Access modifiers changed from: private */
    public final native boolean nativeIsAutorotationOn();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeLowMemory();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeMuteMasterAudio(boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public final native boolean nativePause();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeRecreateGfxState(int i, Surface surface);

    /* JADX INFO: Access modifiers changed from: private */
    public final native boolean nativeRender();

    private final native void nativeRestartActivityIndicator();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeResume();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSendSurfaceChangedEvent();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSetInputSelection(int i, int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSetInputString(String str);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSoftInputCanceled();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSoftInputClosed();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSoftInputLostFocus();

    private static native void nativeUnitySendMessage(String str, String str2, byte[] bArr);

    /* JADX INFO: Access modifiers changed from: private */
    public String o() {
        try {
            return l().metaData.getString("unity.splash-ads-game-id");
        } catch (Exception unused) {
            return null;
        }
    }

    private void p() {
        if (this.s instanceof Activity) {
            ((Activity) this.s).getWindow().setFlags(com.ss.union.game.sdk.feedback.picture.select.d.d, com.ss.union.game.sdk.feedback.picture.select.d.d);
        }
    }

    static /* synthetic */ boolean p(UnityPlayer unityPlayer) {
        unityPlayer.u = true;
        return true;
    }

    private void q() {
        Boolean.valueOf(false);
        Boolean.valueOf(false);
        if (currentActivity != null) {
            SharedPreferences sharedPreferences = currentActivity.getSharedPreferences("game_detail", 0);
            String string = sharedPreferences.getString("game_id", "");
            Boolean boolValueOf = Boolean.valueOf(sharedPreferences.getBoolean("show_ads", false));
            Boolean boolValueOf2 = Boolean.valueOf(sharedPreferences.getBoolean("blocked", false));
            if (boolValueOf2.booleanValue() || !boolValueOf.booleanValue() || string == "") {
                this.shouldShowLaunchScreenAds = false;
                if (boolValueOf2.booleanValue()) {
                    r();
                }
            } else {
                this.shouldShowLaunchScreenAds = true;
                UnityAds.initialize(currentActivity, string, null);
            }
        } else {
            this.shouldShowLaunchScreenAds = false;
        }
        new e().execute(getContext().getPackageName(), Device.getSIMMCC(getContext()));
    }

    private void r() {
        this.c = new AlertDialog.Builder(this.s).setTitle("Sorry").setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: com.unity3d.player.UnityPlayer.19
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                UnityPlayer.this.w.sendEmptyMessage(1);
            }
        }).setMessage("The app is using unauthorized engine, please contact the publisher!").setCancelable(false).create();
        this.w = new Handler() { // from class: com.unity3d.player.UnityPlayer.20
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                switch (message.what) {
                    case 0:
                        if (UnityPlayer.this.c != null && !UnityPlayer.this.c.isShowing()) {
                            UnityPlayer.this.c.show();
                            break;
                        }
                        break;
                    case 1:
                        if (UnityPlayer.this.c != null && UnityPlayer.this.c.isShowing()) {
                            UnityPlayer.this.c.hide();
                            break;
                        }
                        break;
                }
                super.handleMessage(message);
            }
        };
        this.y = new TimerTask() { // from class: com.unity3d.player.UnityPlayer.21
            @Override // java.util.TimerTask, java.lang.Runnable
            public final void run() {
                UnityPlayer.this.w.sendEmptyMessage(0);
            }
        };
        this.x.schedule(this.y, 0L, DownloadConstants.DOWNLOAD_OPTIMIZATION_SPACE_INTERNAL);
    }

    static /* synthetic */ s x(UnityPlayer unityPlayer) {
        unityPlayer.v = null;
        return null;
    }

    public void NotifySplashAdsFinished() {
        this.finishLaunchScreenAds = true;
        this.a.e();
    }

    final void a(Runnable runnable) {
        if (this.s instanceof Activity) {
            ((Activity) this.s).runOnUiThread(runnable);
        } else {
            com.unity3d.player.g.Log(5, "Not running Unity from an Activity; ignored...");
        }
    }

    protected void addPhoneCallListener() {
        this.j = true;
        this.l.listen(this.k, 32);
    }

    @Override // com.unity3d.player.f
    public boolean addViewToPlayer(View view, boolean z) {
        a(view, z ? this.t : null);
        boolean z2 = false;
        boolean z3 = view.getParent() == this;
        boolean z4 = z && this.t.getParent() == null;
        boolean z5 = this.t.getParent() == this;
        if (z3 && (z4 || z5)) {
            z2 = true;
        }
        if (!z2) {
            if (!z3) {
                com.unity3d.player.g.Log(6, "addViewToPlayer: Failure adding view to hierarchy");
            }
            if (!z4 && !z5) {
                com.unity3d.player.g.Log(6, "addViewToPlayer: Failure removing old view from hierarchy");
            }
        }
        return z2;
    }

    public void configurationChanged(Configuration configuration) {
        if (this.t instanceof SurfaceView) {
            this.t.getHolder().setSizeFromLayout();
        }
        if (this.v != null) {
            this.v.c();
        }
        GoogleVrProxy googleVrProxyB = GoogleVrApi.b();
        if (googleVrProxyB != null) {
            googleVrProxyB.c();
        }
    }

    public void destroy() {
        if (GoogleVrApi.b() != null) {
            GoogleVrApi.a();
        }
        if (this.r != null) {
            this.r.a();
            this.r = null;
        }
        this.u = true;
        if (!this.g.d()) {
            pause();
        }
        this.a.a();
        try {
            this.a.join(4000L);
        } catch (InterruptedException unused) {
            this.a.interrupt();
        }
        if (this.i != null) {
            this.s.unregisterReceiver(this.i);
        }
        this.i = null;
        if (p.c()) {
            removeAllViews();
        }
        kill();
        k();
    }

    protected void disableLogger() {
        com.unity3d.player.g.a = true;
    }

    public boolean displayChanged(int i, Surface surface) {
        if (i == 0) {
            this.e = surface != null;
            a(new Runnable() { // from class: com.unity3d.player.UnityPlayer.2
                @Override // java.lang.Runnable
                public final void run() {
                    if (UnityPlayer.this.e) {
                        UnityPlayer.this.removeView(UnityPlayer.this.t);
                    } else {
                        UnityPlayer.this.addView(UnityPlayer.this.t);
                    }
                }
            });
        }
        return b(i, surface);
    }

    protected void executeGLThreadJobs() {
        while (true) {
            Runnable runnable = (Runnable) this.h.poll();
            if (runnable == null) {
                return;
            } else {
                runnable.run();
            }
        }
    }

    protected String getBuilderUserId() {
        try {
            return l().metaData.getString("unity.builder");
        } catch (Exception unused) {
            return null;
        }
    }

    protected String getClipboardText() {
        ClipData primaryClip = this.m.getPrimaryClip();
        return primaryClip != null ? primaryClip.getItemAt(0).coerceToText(this.s).toString() : "";
    }

    public String getDeviceId() {
        if (currentActivity == null) {
            return "";
        }
        SharedPreferences sharedPreferences = currentActivity.getSharedPreferences("device_detail", 0);
        String string = sharedPreferences.getString("device_id", null);
        if (string != null) {
            return string;
        }
        String uniqueEventId = Device.getUniqueEventId();
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putString("device_id", uniqueEventId);
        editorEdit.commit();
        return uniqueEventId;
    }

    public Bundle getSettings() {
        return Bundle.EMPTY;
    }

    protected Boolean getShowSplashSlogan() {
        try {
            return Boolean.valueOf(l().metaData.getBoolean("unity.splash-ads-slogan"));
        } catch (Exception unused) {
            return false;
        }
    }

    protected int getShowSplashSloganHeight() {
        try {
            return l().metaData.getInt("unity.splash-ads-slogan-height", 250);
        } catch (Exception unused) {
            return 250;
        }
    }

    protected int getSplashMode() {
        try {
            return l().metaData.getInt("unity.splash-mode");
        } catch (Exception unused) {
            return 0;
        }
    }

    public View getView() {
        return this;
    }

    protected void hideSoftInput() {
        b(new Runnable() { // from class: com.unity3d.player.UnityPlayer.8
            @Override // java.lang.Runnable
            public final void run() {
                if (UnityPlayer.this.b != null) {
                    UnityPlayer.this.b.dismiss();
                    UnityPlayer.this.b = null;
                }
            }
        });
    }

    public void init(int i, boolean z) {
    }

    protected boolean initializeGoogleAr() {
        if (this.o != null || currentActivity == null || !n()) {
            return false;
        }
        this.o = new GoogleARCoreApi();
        this.o.initializeARCore(currentActivity);
        if (this.g.d()) {
            return false;
        }
        this.o.resumeARCore();
        return false;
    }

    protected boolean initializeGoogleVr() {
        final GoogleVrProxy googleVrProxyB = GoogleVrApi.b();
        if (googleVrProxyB == null) {
            GoogleVrApi.a(this);
            googleVrProxyB = GoogleVrApi.b();
            if (googleVrProxyB == null) {
                com.unity3d.player.g.Log(6, "Unable to create Google VR subsystem.");
                return false;
            }
        }
        final Semaphore semaphore = new Semaphore(0);
        final Runnable runnable = new Runnable() { // from class: com.unity3d.player.UnityPlayer.15
            @Override // java.lang.Runnable
            public final void run() {
                UnityPlayer.this.injectEvent(new KeyEvent(0, 4));
                UnityPlayer.this.injectEvent(new KeyEvent(1, 4));
            }
        };
        a(new Runnable() { // from class: com.unity3d.player.UnityPlayer.16
            @Override // java.lang.Runnable
            public final void run() {
                if (!googleVrProxyB.a(UnityPlayer.currentActivity, UnityPlayer.this.s, UnityPlayer.this.e(), runnable)) {
                    com.unity3d.player.g.Log(6, "Unable to initialize Google VR subsystem.");
                }
                if (UnityPlayer.currentActivity != null) {
                    googleVrProxyB.a(UnityPlayer.currentActivity.getIntent());
                }
                semaphore.release();
            }
        });
        try {
            if (semaphore.tryAcquire(4L, TimeUnit.SECONDS)) {
                return googleVrProxyB.a();
            }
            com.unity3d.player.g.Log(5, "Timeout while trying to initialize Google VR.");
            return false;
        } catch (InterruptedException e2) {
            com.unity3d.player.g.Log(5, "UI thread was interrupted while initializing Google VR. " + e2.getLocalizedMessage());
            return false;
        }
    }

    public boolean injectEvent(InputEvent inputEvent) {
        if (p.c()) {
            return nativeInjectEvent(inputEvent);
        }
        return false;
    }

    protected boolean isFinishing() {
        if (!this.u) {
            boolean z = (this.s instanceof Activity) && ((Activity) this.s).isFinishing();
            this.u = z;
            if (!z) {
                return false;
            }
        }
        return true;
    }

    public boolean isLaunchScreenAdsFinished() {
        return this.finishLaunchScreenAds || UnityAds.isSkipLaunchScreenAds();
    }

    public boolean isShouldShowLaunchScreenAds() {
        return this.shouldShowLaunchScreenAds;
    }

    protected void kill() {
        Process.killProcess(Process.myPid());
    }

    protected boolean loadLibrary(String str) {
        return loadLibraryStatic(str);
    }

    public void lowMemory() {
        if (p.c()) {
            c(new Runnable() { // from class: com.unity3d.player.UnityPlayer.5
                @Override // java.lang.Runnable
                public final void run() {
                    UnityPlayer.this.nativeLowMemory();
                }
            });
        }
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        return injectEvent(motionEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return injectEvent(motionEvent);
    }

    public void pause() {
        if (this.o != null) {
            this.o.pauseARCore();
        }
        if (this.v != null) {
            this.v.a();
        }
        GoogleVrProxy googleVrProxyB = GoogleVrApi.b();
        if (googleVrProxyB != null) {
            googleVrProxyB.pauseGvrLayout();
        }
        if (this.q != null) {
            this.q.b();
        }
        h();
    }

    public void quit() {
        destroy();
    }

    @Override // com.unity3d.player.f
    public void removeViewFromPlayer(View view) {
        a(this.t, view);
        boolean z = false;
        boolean z2 = view.getParent() == null;
        boolean z3 = this.t.getParent() == this;
        if (z2 && z3) {
            z = true;
        }
        if (z) {
            return;
        }
        if (!z2) {
            com.unity3d.player.g.Log(6, "removeViewFromPlayer: Failure removing view from hierarchy");
        }
        if (z3) {
            return;
        }
        com.unity3d.player.g.Log(6, "removeVireFromPlayer: Failure agging old view to hierarchy");
    }

    @Override // com.unity3d.player.f
    public void reportError(String str, String str2) {
        com.unity3d.player.g.Log(6, str + ": " + str2);
    }

    protected void reportSoftInputSelection(final int i, final int i2) {
        a(new h() { // from class: com.unity3d.player.UnityPlayer.14
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(UnityPlayer.this, (byte) 0);
            }

            @Override // com.unity3d.player.UnityPlayer.h
            public final void a() {
                UnityPlayer.this.nativeSetInputSelection(i, i2);
            }
        });
    }

    protected void reportSoftInputStr(final String str, final int i, final boolean z) {
        if (i == 1) {
            hideSoftInput();
        }
        a(new h() { // from class: com.unity3d.player.UnityPlayer.13
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(UnityPlayer.this, (byte) 0);
            }

            @Override // com.unity3d.player.UnityPlayer.h
            public final void a() {
                if (z) {
                    UnityPlayer.this.nativeSoftInputCanceled();
                } else if (str != null) {
                    UnityPlayer.this.nativeSetInputString(str);
                }
                if (i == 1) {
                    UnityPlayer.this.nativeSoftInputClosed();
                }
            }
        });
    }

    protected void requestUserAuthorization(String str) {
        if (!j.c || str == null || str.isEmpty() || currentActivity == null) {
            return;
        }
        j.d.a(currentActivity, str);
    }

    public void resume() {
        if (this.o != null) {
            this.o.resumeARCore();
        }
        this.g.b(false);
        if (this.v != null) {
            this.v.b();
        }
        if (this.q != null) {
            this.q.c();
        }
        j();
        if (p.c()) {
            nativeRestartActivityIndicator();
        }
        GoogleVrProxy googleVrProxyB = GoogleVrApi.b();
        if (googleVrProxyB != null) {
            googleVrProxyB.b();
        }
    }

    protected void setCharacterLimit(final int i) {
        a(new Runnable() { // from class: com.unity3d.player.UnityPlayer.10
            @Override // java.lang.Runnable
            public final void run() {
                if (UnityPlayer.this.b != null) {
                    UnityPlayer.this.b.a(i);
                }
            }
        });
    }

    protected void setClipboardText(String str) {
        this.m.setPrimaryClip(ClipData.newPlainText("Text", str));
    }

    protected void setHideInputField(final boolean z) {
        a(new Runnable() { // from class: com.unity3d.player.UnityPlayer.11
            @Override // java.lang.Runnable
            public final void run() {
                if (UnityPlayer.this.b != null) {
                    UnityPlayer.this.b.a(z);
                }
            }
        });
    }

    protected void setSelection(final int i, final int i2) {
        a(new Runnable() { // from class: com.unity3d.player.UnityPlayer.12
            @Override // java.lang.Runnable
            public final void run() {
                if (UnityPlayer.this.b != null) {
                    UnityPlayer.this.b.a(i, i2);
                }
            }
        });
    }

    protected void setSoftInputStr(final String str) {
        a(new Runnable() { // from class: com.unity3d.player.UnityPlayer.9
            @Override // java.lang.Runnable
            public final void run() {
                if (UnityPlayer.this.b == null || str == null) {
                    return;
                }
                UnityPlayer.this.b.a(str);
            }
        });
    }

    public boolean shouldRegisterLaunch(Context context) {
        String simmcc = Device.getSIMMCC(context);
        return (simmcc == null || "".equals(simmcc) || "466".equals(simmcc)) ? false : true;
    }

    protected void showSoftInput(final String str, final int i, final boolean z, final boolean z2, final boolean z3, final boolean z4, final String str2, final int i2, final boolean z5) {
        b(new Runnable() { // from class: com.unity3d.player.UnityPlayer.7
            @Override // java.lang.Runnable
            public final void run() {
                UnityPlayer.this.b = new k(UnityPlayer.this.s, this, str, i, z, z2, z3, str2, i2, z5);
                UnityPlayer.this.b.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.unity3d.player.UnityPlayer.7.1
                    @Override // android.content.DialogInterface.OnCancelListener
                    public final void onCancel(DialogInterface dialogInterface) {
                        UnityPlayer.this.nativeSoftInputLostFocus();
                        UnityPlayer.this.reportSoftInputStr(null, 1, false);
                    }
                });
                UnityPlayer.this.b.show();
            }
        });
    }

    protected boolean showVideoPlayer(String str, int i, int i2, int i3, boolean z, int i4, int i5) {
        if (this.v == null) {
            this.v = new s(this);
        }
        boolean zA = this.v.a(this.s, str, i, i2, i3, z, i4, i5, new s.a() { // from class: com.unity3d.player.UnityPlayer.17
            @Override // com.unity3d.player.s.a
            public final void a() {
                UnityPlayer.x(UnityPlayer.this);
            }
        });
        if (zA) {
            a(new Runnable() { // from class: com.unity3d.player.UnityPlayer.18
                @Override // java.lang.Runnable
                public final void run() {
                    if (UnityPlayer.this.nativeIsAutorotationOn() && (UnityPlayer.this.s instanceof Activity)) {
                        ((Activity) UnityPlayer.this.s).setRequestedOrientation(UnityPlayer.this.d);
                    }
                }
            });
        }
        return zA;
    }

    protected boolean skipPermissionsDialog() {
        if (!j.c || currentActivity == null) {
            return false;
        }
        return j.d.a(currentActivity);
    }

    public void start() {
    }

    public void stop() {
    }

    protected void toggleGyroscopeSensor(boolean z) {
        SensorManager sensorManager = (SensorManager) this.s.getSystemService(com.umeng.commonsdk.proguard.o.Z);
        Sensor defaultSensor = sensorManager.getDefaultSensor(11);
        if (z) {
            sensorManager.registerListener(this.p, defaultSensor, 1);
        } else {
            sensorManager.unregisterListener(this.p);
        }
    }

    public void windowFocusChanged(boolean z) {
        this.g.a(z);
        if (this.g.e()) {
            if (z) {
                this.a.c();
            } else {
                this.a.d();
            }
            j();
        }
    }
}
