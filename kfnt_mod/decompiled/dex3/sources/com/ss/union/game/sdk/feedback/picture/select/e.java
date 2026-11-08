package com.ss.union.game.sdk.feedback.picture.select;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.ss.union.game.sdk.common.activityresult.InlineActivityResult;
import com.ss.union.game.sdk.common.activityresult.Result;
import com.ss.union.game.sdk.common.activityresult.callbacks.ActivityResultListener;
import com.ss.union.game.sdk.common.util.ActivityUtils;
import com.ss.union.game.sdk.common.util.LGExecutor;
import com.ss.union.game.sdk.common.util.MainThreadExecutor;
import com.ss.union.game.sdk.core.glide.RequestBuilder;
import com.ss.union.game.sdk.core.glide.load.DataSource;
import com.ss.union.game.sdk.core.glide.load.engine.GlideException;
import com.ss.union.game.sdk.core.glide.request.BaseRequestOptions;
import com.ss.union.game.sdk.core.glide.request.RequestListener;
import com.ss.union.game.sdk.core.glide.request.target.Target;
import com.ss.union.game.sdk.core.luban.OnCompressListener;
import com.ss.union.game.sdk.core.provider.LGCommonFileProvider;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class e {
    private String b;
    private WeakReference<ImageView> c;
    private Activity d;
    private RequestBuilder<Drawable> e;
    private final List<b> a = new ArrayList();
    private boolean f = true;
    private boolean g = false;
    private boolean h = false;

    public static e a(ImageView imageView) {
        return new e().b(imageView);
    }

    private e b(ImageView imageView) {
        this.c = new WeakReference<>(imageView);
        this.d = ActivityUtils.unwrap(imageView.getContext());
        return this;
    }

    public e a(RequestBuilder<Drawable> requestBuilder) {
        this.e = requestBuilder;
        return this;
    }

    public e a(boolean z) {
        this.f = z;
        return this;
    }

    public e a(b bVar) {
        if (bVar != null) {
            this.a.add(bVar);
        }
        return this;
    }

    public void a() {
        final Activity activity = this.d;
        if (activity == null || activity.isFinishing()) {
            a("activity is null or finished", new NullPointerException("activity is null or finished"));
        } else {
            InlineActivityResult.startForResult(activity, c(), new ActivityResultListener() { // from class: com.ss.union.game.sdk.feedback.picture.select.e.1
                @Override // com.ss.union.game.sdk.common.activityresult.callbacks.ActivityResultListener
                public void onSuccess(Result result) {
                    e.this.a(activity, result);
                }

                @Override // com.ss.union.game.sdk.common.activityresult.callbacks.ActivityResultListener
                public void onFailed(Result result) {
                    e.this.a(result);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Activity activity) {
        if (this.g && this.h) {
            a(activity, new File(this.b));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Activity activity, Result result) {
        Intent data = result.getData();
        if (data == null || data.getData() == null) {
            a("intent or data is null", new IllegalStateException("intent or data is null"));
            return;
        }
        if (activity == null || activity.isFinishing()) {
            a("activity is null or finishing", new IllegalStateException("activity is null or finishing"));
            return;
        }
        final Uri data2 = data.getData();
        LGExecutor.CACHED_EXECUTOR.execute(new Runnable() { // from class: com.ss.union.game.sdk.feedback.picture.select.e.6
            /* JADX WARN: Removed duplicated region for block: B:39:0x008c A[EXC_TOP_SPLITTER, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() throws java.lang.Throwable {
                /*
                    r5 = this;
                    r0 = 0
                    android.app.Activity r1 = r2     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L73
                    android.content.ContentResolver r1 = r1.getContentResolver()     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L73
                    android.net.Uri r2 = r3     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L73
                    java.io.InputStream r1 = r1.openInputStream(r2)     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L73
                    if (r1 != 0) goto L23
                    java.lang.String r0 = "fail to open Image inputStream"
                    com.ss.union.game.sdk.feedback.picture.select.e r2 = com.ss.union.game.sdk.feedback.picture.select.e.this     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L89
                    java.lang.IllegalStateException r3 = new java.lang.IllegalStateException     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L89
                    r3.<init>(r0)     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L89
                    com.ss.union.game.sdk.feedback.picture.select.e.a(r2, r0, r3)     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L89
                    if (r1 == 0) goto L20
                    r1.close()     // Catch: java.lang.Exception -> L20
                L20:
                    return
                L21:
                    r0 = move-exception
                    goto L77
                L23:
                    com.ss.union.game.sdk.feedback.picture.select.e r0 = com.ss.union.game.sdk.feedback.picture.select.e.this     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L89
                    java.lang.String r0 = com.ss.union.game.sdk.feedback.picture.select.e.a(r0)     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L89
                    boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L89
                    if (r0 == 0) goto L3c
                    com.ss.union.game.sdk.feedback.picture.select.e r0 = com.ss.union.game.sdk.feedback.picture.select.e.this     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L89
                    com.ss.union.game.sdk.feedback.picture.select.e r2 = com.ss.union.game.sdk.feedback.picture.select.e.this     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L89
                    android.app.Activity r3 = r2     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L89
                    java.lang.String r2 = com.ss.union.game.sdk.feedback.picture.select.e.a(r2, r3)     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L89
                    com.ss.union.game.sdk.feedback.picture.select.e.a(r0, r2)     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L89
                L3c:
                    java.io.File r0 = new java.io.File     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L89
                    com.ss.union.game.sdk.feedback.picture.select.e r2 = com.ss.union.game.sdk.feedback.picture.select.e.this     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L89
                    java.lang.String r2 = com.ss.union.game.sdk.feedback.picture.select.e.a(r2)     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L89
                    r0.<init>(r2)     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L89
                    r2 = 0
                    com.ss.union.game.sdk.common.util.FileUtils.copyInputStreamToFile(r1, r0, r2)     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L89
                    if (r1 == 0) goto L50
                    r1.close()     // Catch: java.lang.Exception -> L50
                L50:
                    java.io.File r0 = new java.io.File
                    com.ss.union.game.sdk.feedback.picture.select.e r1 = com.ss.union.game.sdk.feedback.picture.select.e.this
                    java.lang.String r1 = com.ss.union.game.sdk.feedback.picture.select.e.a(r1)
                    r0.<init>(r1)
                    com.ss.union.game.sdk.feedback.picture.select.e r1 = com.ss.union.game.sdk.feedback.picture.select.e.this
                    com.ss.union.game.sdk.feedback.picture.select.e.a(r1, r0)
                    com.ss.union.game.sdk.feedback.picture.select.e r0 = com.ss.union.game.sdk.feedback.picture.select.e.this
                    r1 = 1
                    com.ss.union.game.sdk.feedback.picture.select.e.a(r0, r1)
                    com.ss.union.game.sdk.feedback.picture.select.e r0 = com.ss.union.game.sdk.feedback.picture.select.e.this
                    android.app.Activity r1 = r2
                    com.ss.union.game.sdk.feedback.picture.select.e.b(r0, r1)
                    return
                L6e:
                    r1 = move-exception
                    r4 = r1
                    r1 = r0
                    r0 = r4
                    goto L8a
                L73:
                    r1 = move-exception
                    r4 = r1
                    r1 = r0
                    r0 = r4
                L77:
                    r0.printStackTrace()     // Catch: java.lang.Throwable -> L89
                    com.ss.union.game.sdk.feedback.picture.select.e r2 = com.ss.union.game.sdk.feedback.picture.select.e.this     // Catch: java.lang.Throwable -> L89
                    java.lang.String r3 = r0.getMessage()     // Catch: java.lang.Throwable -> L89
                    com.ss.union.game.sdk.feedback.picture.select.e.a(r2, r3, r0)     // Catch: java.lang.Throwable -> L89
                    if (r1 == 0) goto L88
                    r1.close()     // Catch: java.lang.Exception -> L88
                L88:
                    return
                L89:
                    r0 = move-exception
                L8a:
                    if (r1 == 0) goto L8f
                    r1.close()     // Catch: java.lang.Exception -> L8f
                L8f:
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.ss.union.game.sdk.feedback.picture.select.e.AnonymousClass6.run():void");
            }
        });
        if (this.c != null && this.c.get() != null) {
            ImageView imageView = this.c.get();
            RequestBuilder<Drawable> requestBuilderA = c.a(imageView, data2);
            if (this.e != null) {
                requestBuilderA.apply((BaseRequestOptions<?>) this.e);
            }
            requestBuilderA.listener(new RequestListener<Drawable>() { // from class: com.ss.union.game.sdk.feedback.picture.select.e.7
                @Override // com.ss.union.game.sdk.core.glide.request.RequestListener
                public boolean onLoadFailed(GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                    e.this.a(glideException);
                    return false;
                }

                @Override // com.ss.union.game.sdk.core.glide.request.RequestListener
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                    e.this.d();
                    e.this.h = true;
                    e.this.a(activity);
                    return false;
                }
            }).into(imageView);
            return;
        }
        a(new Exception("imageView not exist"));
    }

    private Intent c() {
        Intent intent = new Intent();
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType(d.e);
        intent.setAction("android.intent.action.OPEN_DOCUMENT");
        return intent;
    }

    public void b() {
        final Activity activity = this.d;
        if (activity == null || activity.isFinishing()) {
            a("activity is null or finished", new NullPointerException("activity is null or finished"));
            return;
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(activity.getPackageManager()) == null) {
            a("no activity handle capture intent", new ActivityNotFoundException("no activity handle capture intent"));
            return;
        }
        if (TextUtils.isEmpty(this.b)) {
            this.b = b(activity);
        }
        if (TextUtils.isEmpty(this.b)) {
            a("empty picture path", new IllegalArgumentException("empty picture path"));
            return;
        }
        intent.putExtra("output", LGCommonFileProvider.getUriForFile(activity, activity.getPackageName() + ".LGCommonFileProvider", new File(this.b)));
        try {
            a(activity, intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        InlineActivityResult.startForResult(activity, intent, new ActivityResultListener() { // from class: com.ss.union.game.sdk.feedback.picture.select.e.8
            @Override // com.ss.union.game.sdk.common.activityresult.callbacks.ActivityResultListener
            public void onSuccess(Result result) {
                e.this.b(activity, result);
            }

            @Override // com.ss.union.game.sdk.common.activityresult.callbacks.ActivityResultListener
            public void onFailed(Result result) {
                e.this.a(result);
            }
        });
    }

    private void a(Context context, @NonNull Intent intent) {
        List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        if (listQueryIntentActivities.isEmpty()) {
            return;
        }
        for (int i = 0; i < listQueryIntentActivities.size(); i++) {
            ResolveInfo resolveInfo = listQueryIntentActivities.get(i);
            if (resolveInfo != null && (resolveInfo.activityInfo.applicationInfo.flags & 1) != 0) {
                intent.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Result result) {
        if (result.getResultCode() == 0) {
            a("", new a(""));
        } else {
            a("", result.getCause());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final Activity activity, Result result) {
        final File file = new File(this.b);
        if (!file.exists() || file.length() <= 0) {
            a("picture file not exists or length <= 0 ", new IllegalStateException("picture file not exists or length <= 0 "));
            return;
        }
        a(file);
        if (this.c != null && this.c.get() != null) {
            ImageView imageView = this.c.get();
            RequestBuilder<Drawable> requestBuilderB = c.b(imageView, this.b);
            if (this.e != null) {
                requestBuilderB.apply((BaseRequestOptions<?>) this.e);
            }
            requestBuilderB.listener(new RequestListener<Drawable>() { // from class: com.ss.union.game.sdk.feedback.picture.select.e.9
                @Override // com.ss.union.game.sdk.core.glide.request.RequestListener
                public boolean onLoadFailed(GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                    e.this.a(glideException);
                    return false;
                }

                @Override // com.ss.union.game.sdk.core.glide.request.RequestListener
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                    e.this.d();
                    e.this.a(activity, file);
                    return false;
                }
            }).into(imageView);
            return;
        }
        a(new Exception("imageView not exist"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Activity activity, File file) {
        if (activity == null || activity.isFinishing() || !this.f) {
            return;
        }
        c.a(activity, file, new OnCompressListener() { // from class: com.ss.union.game.sdk.feedback.picture.select.e.10
            @Override // com.ss.union.game.sdk.core.luban.OnCompressListener
            public void onStart() {
                e.this.e();
            }

            @Override // com.ss.union.game.sdk.core.luban.OnCompressListener
            public void onSuccess(File file2) {
                e.this.b(file2);
            }

            @Override // com.ss.union.game.sdk.core.luban.OnCompressListener
            public void onError(Throwable th) {
                e.this.b(th);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final File file) {
        MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.feedback.picture.select.e.11
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Iterator it = e.this.a.iterator();
                    while (it.hasNext()) {
                        ((b) it.next()).a(file);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final String str, final Throwable th) {
        MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.feedback.picture.select.e.12
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Iterator it = e.this.a.iterator();
                    while (it.hasNext()) {
                        ((b) it.next()).a(str, th);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Throwable th) {
        MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.feedback.picture.select.e.13
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Iterator it = e.this.a.iterator();
                    while (it.hasNext()) {
                        ((b) it.next()).a(th);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.feedback.picture.select.e.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Iterator it = e.this.a.iterator();
                    while (it.hasNext()) {
                        ((b) it.next()).a();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.feedback.picture.select.e.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Iterator it = e.this.a.iterator();
                    while (it.hasNext()) {
                        ((b) it.next()).b();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final File file) {
        MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.feedback.picture.select.e.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Iterator it = e.this.a.iterator();
                    while (it.hasNext()) {
                        ((b) it.next()).b(file);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final Throwable th) {
        MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.feedback.picture.select.e.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Iterator it = e.this.a.iterator();
                    while (it.hasNext()) {
                        ((b) it.next()).b(th);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b(Activity activity) {
        String str = "lg_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date()) + "_";
        File fileA = c.a(activity);
        if (!fileA.exists()) {
            fileA.mkdirs();
        }
        try {
            return File.createTempFile(str, d.c, fileA).getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    static class a extends Exception {
        public a(String str) {
            super(str);
        }
    }
}
