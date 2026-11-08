package com.ss.union.game.sdk.feedback.picture.select;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.common.util.LGExecutor;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.core.glide.Glide;
import com.ss.union.game.sdk.core.glide.RequestBuilder;
import com.ss.union.game.sdk.core.luban.CompressionPredicate;
import com.ss.union.game.sdk.core.luban.Luban;
import com.ss.union.game.sdk.core.luban.OnCompressListener;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {
    private static volatile Integer a;

    public static File a(Context context) {
        return context.getExternalFilesDir(d.a);
    }

    public static File b(Context context) {
        return context.getExternalFilesDir(d.b);
    }

    public static void a() {
        LGExecutor.CACHED_EXECUTOR.execute(new Runnable() { // from class: com.ss.union.game.sdk.feedback.picture.select.c.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    c.b(c.a(GlobalApplicationUtils.getContext()));
                    c.b(c.b(GlobalApplicationUtils.getContext()));
                } catch (Exception unused) {
                }
            }
        });
    }

    public static void a(Activity activity, File file, OnCompressListener onCompressListener) {
        a(activity, file, d.d, onCompressListener);
    }

    public static void a(Activity activity, File file, int i, final OnCompressListener onCompressListener) {
        File fileB = b(activity);
        if (!fileB.exists()) {
            fileB.mkdirs();
        }
        Luban.with(activity).load(file).filter(new CompressionPredicate() { // from class: com.ss.union.game.sdk.feedback.picture.select.c.3
            @Override // com.ss.union.game.sdk.core.luban.CompressionPredicate
            public boolean apply(String str) {
                return (TextUtils.isEmpty(str) || str.endsWith(".gif")) ? false : true;
            }
        }).setTargetDir(fileB.getAbsolutePath()).setCompressListener(new OnCompressListener() { // from class: com.ss.union.game.sdk.feedback.picture.select.c.2
            @Override // com.ss.union.game.sdk.core.luban.OnCompressListener
            public void onStart() {
                if (onCompressListener != null) {
                    onCompressListener.onStart();
                }
            }

            @Override // com.ss.union.game.sdk.core.luban.OnCompressListener
            public void onSuccess(File file2) {
                if (onCompressListener != null) {
                    onCompressListener.onSuccess(file2);
                }
            }

            @Override // com.ss.union.game.sdk.core.luban.OnCompressListener
            public void onError(Throwable th) {
                if (onCompressListener != null) {
                    onCompressListener.onError(th);
                }
            }
        }).ignoreBy(i).launch();
    }

    public static void a(ImageView imageView, String str) {
        if (a == null) {
            a = Integer.valueOf(ResourceUtils.getDrawableIdByName("lg_feedback_placeholder"));
        }
        Glide.with(imageView.getContext()).load(str).placeholder(a.intValue()).error(a.intValue()).dontAnimate().into(imageView);
    }

    public static RequestBuilder<Drawable> b(ImageView imageView, String str) {
        if (a == null) {
            a = Integer.valueOf(ResourceUtils.getDrawableIdByName("lg_feedback_placeholder"));
        }
        return (RequestBuilder) Glide.with(imageView).load(str).placeholder(a.intValue()).error(a.intValue()).dontAnimate();
    }

    public static RequestBuilder<Drawable> a(ImageView imageView, Uri uri) {
        if (a == null) {
            a = Integer.valueOf(ResourceUtils.getDrawableIdByName("lg_feedback_placeholder"));
        }
        return (RequestBuilder) Glide.with(imageView).load(uri).placeholder(a.intValue()).error(a.intValue()).dontAnimate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(File file) {
        File[] fileArrListFiles;
        if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                b(file2);
            }
        }
        file.delete();
    }
}
