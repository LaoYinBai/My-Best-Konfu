package com.kwad.sdk.core.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.ColorInt;
import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.R;
import com.kwad.sdk.core.d.a;
import com.kwad.sdk.core.imageloader.core.DisplayImageOptionsCompat;
import com.kwad.sdk.core.imageloader.core.assist.FailReason;
import com.kwad.sdk.core.imageloader.core.decode.DecodedResult;
import com.kwad.sdk.core.imageloader.core.listener.ImageLoadingListener;
import com.kwad.sdk.core.report.d;
import com.kwad.sdk.core.response.model.AdTemplate;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class KSImageLoader {
    public static DisplayImageOptionsCompat IMGOPTION_ENTRY;
    public static DisplayImageOptionsCompat IMGOPTION_NORMAL = new DisplayImageOptionsCompat.Builder().bitmapConfig(Bitmap.Config.ARGB_8888).cacheOnDisk(true).cacheInMemory(true).build();
    public static DisplayImageOptionsCompat IMGOPTION_TREND;
    public static DisplayImageOptionsCompat IMGOPTION_TUBE;

    private static class InnerImageLoadingListener implements ImageLoadingListener {

        @Nullable
        AdTemplate adTemplate;

        @Nullable
        ImageLoadingListener loadingListener;

        public InnerImageLoadingListener(@Nullable AdTemplate adTemplate, ImageLoadingListener imageLoadingListener) {
            this.adTemplate = adTemplate;
            this.loadingListener = imageLoadingListener;
        }

        @Override // com.kwad.sdk.core.imageloader.core.listener.ImageLoadingListener
        public boolean onDecode(String str, InputStream inputStream, DecodedResult decodedResult) {
            if (this.loadingListener != null) {
                return this.loadingListener.onDecode(str, inputStream, decodedResult);
            }
            return false;
        }

        @Override // com.kwad.sdk.core.imageloader.core.listener.ImageLoadingListener
        public void onLoadingCancelled(String str, View view) {
            if (this.loadingListener != null) {
                this.loadingListener.onLoadingCancelled(str, view);
            }
        }

        @Override // com.kwad.sdk.core.imageloader.core.listener.ImageLoadingListener
        public void onLoadingComplete(String str, View view, DecodedResult decodedResult) {
            if (this.loadingListener != null) {
                this.loadingListener.onLoadingComplete(str, view, decodedResult);
            }
        }

        @Override // com.kwad.sdk.core.imageloader.core.listener.ImageLoadingListener
        public void onLoadingFailed(String str, View view, FailReason failReason) {
            String str2;
            if (this.loadingListener != null) {
                this.loadingListener.onLoadingFailed(str, view, failReason);
            }
            if (this.adTemplate != null) {
                StringBuilder sb = new StringBuilder("ImageLoader:");
                if (failReason != null) {
                    Throwable cause = failReason.getCause();
                    String str3 = "";
                    if (cause != null && cause.getStackTrace().length > 0) {
                        str3 = cause.getMessage() + " @ " + cause.getStackTrace()[0].getFileName() + cause.getStackTrace()[0].getClassName() + cause.getStackTrace()[0].getLineNumber();
                    }
                    switch (failReason.getType()) {
                        case UNKNOWN:
                            str2 = GrsBaseInfo.CountryCodeSource.UNKNOWN;
                            sb.append(str2);
                            break;
                        case IO_ERROR:
                            sb.append("IO_ERROR");
                            sb.append(str3);
                            break;
                        case OUT_OF_MEMORY:
                            str2 = "OUT_OF_MEMORY";
                            sb.append(str2);
                            break;
                        case NETWORK_DENIED:
                            str2 = "NETWORK_DENIED";
                            sb.append(str2);
                            break;
                        case DECODING_ERROR:
                            str2 = "DECODING_ERROR";
                            sb.append(str2);
                            break;
                    }
                }
                d.a(this.adTemplate, str, sb.toString());
            }
        }

        @Override // com.kwad.sdk.core.imageloader.core.listener.ImageLoadingListener
        public void onLoadingStarted(String str, View view) {
            if (this.loadingListener != null) {
                this.loadingListener.onLoadingStarted(str, view);
            }
        }
    }

    public static DisplayImageOptionsCompat IMGOPTION_ENTRY_FS() {
        return IMGOPTION_ENTRY;
    }

    @RequiresApi(api = 17)
    public static Bitmap blur(Context context, Bitmap bitmap, @IntRange(from = 1, to = 25) int i) {
        Bitmap bitmapCopy = bitmap.getConfig() == Bitmap.Config.ARGB_8888 ? bitmap : bitmap.copy(Bitmap.Config.ARGB_8888, true);
        if (bitmapCopy == null) {
            bitmapCopy = bitmap.copy(bitmap.getConfig(), true);
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapCopy.getWidth(), bitmapCopy.getHeight(), bitmapCopy.getConfig());
        try {
            RenderScript renderScriptCreate = RenderScript.create(context);
            Allocation allocationCreateFromBitmap = Allocation.createFromBitmap(renderScriptCreate, bitmapCreateBitmap);
            ScriptIntrinsicBlur scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(renderScriptCreate, allocationCreateFromBitmap.getElement());
            scriptIntrinsicBlurCreate.setInput(allocationCreateFromBitmap);
            scriptIntrinsicBlurCreate.setRadius(i);
            scriptIntrinsicBlurCreate.forEach(allocationCreateFromBitmap);
            allocationCreateFromBitmap.copyTo(bitmapCreateBitmap);
            renderScriptCreate.destroy();
        } catch (Exception e) {
            a.b(e);
        }
        return bitmapCreateBitmap;
    }

    private static void checkInit() {
        if (ImageLoaderProxy.INSTANCE.isInited()) {
            return;
        }
        init(KsAdSDKImpl.get().getContext());
    }

    public static void init(Context context) {
        if (context == null) {
            return;
        }
        IMGOPTION_ENTRY = new DisplayImageOptionsCompat.Builder().bitmapConfig(Bitmap.Config.ARGB_8888).cacheOnDisk(true).cacheInMemory(true).showImageOnLoading(R.drawable.ksad_loading_entry).showImageForEmptyUri(R.drawable.ksad_loading_entry).showImageOnFail(R.drawable.ksad_loading_entry).build();
        IMGOPTION_TUBE = new DisplayImageOptionsCompat.Builder().bitmapConfig(Bitmap.Config.RGB_565).cacheOnDisk(true).cacheInMemory(true).showImageOnLoading(R.drawable.ksad_tube_episode_cover_bg).showImageForEmptyUri(R.drawable.ksad_tube_episode_cover_bg).showImageOnFail(R.drawable.ksad_tube_episode_cover_bg).build();
        IMGOPTION_TREND = new DisplayImageOptionsCompat.Builder().bitmapConfig(Bitmap.Config.RGB_565).cacheOnDisk(true).cacheInMemory(true).showImageOnLoading(R.drawable.ksad_trend_panel_item_cover_bg).showImageForEmptyUri(R.drawable.ksad_trend_panel_item_cover_bg).showImageOnFail(R.drawable.ksad_trend_panel_item_cover_bg).build();
        ImageLoaderProxy.INSTANCE.init(context, ImageLoadFactory.create());
    }

    public static void loadAppIcon(ImageView imageView, @Nullable String str, AdTemplate adTemplate, int i) {
        if (imageView == null || TextUtils.isEmpty(str)) {
            return;
        }
        checkInit();
        ImageLoaderProxy.INSTANCE.load(KsAdSDKImpl.get().getContext(), str, imageView, new DisplayImageOptionsCompat.Builder().showImageOnLoading(imageView.getContext().getResources().getDrawable(R.drawable.ksad_default_app_icon)).showImageForEmptyUri(imageView.getContext().getResources().getDrawable(R.drawable.ksad_default_app_icon)).showImageOnFail(imageView.getContext().getResources().getDrawable(R.drawable.ksad_default_app_icon)).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).setCornerRound(i).build(), new InnerImageLoadingListener(adTemplate, null));
    }

    public static void loadAuthorCircleIcon(ImageView imageView, String str) {
        loadCircleIcon(imageView, str, imageView.getContext().getResources().getDrawable(R.drawable.ksad_photo_default_author_icon));
    }

    public static void loadCircleIcon(ImageView imageView, String str, Drawable drawable) {
        if (imageView == null) {
            return;
        }
        checkInit();
        ImageLoaderProxy.INSTANCE.load(KsAdSDKImpl.get().getContext(), str, imageView, new DisplayImageOptionsCompat.Builder().showImageOnLoading(drawable).showImageForEmptyUri(drawable).showImageOnFail(drawable).cacheInMemory(true).cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565).setStrokeColor(Color.argb(255, 255, 255, 255)).setStrokeWidth(1.0f).build(), (ImageLoadingListener) null);
    }

    public static void loadCircleIcon(ImageView imageView, String str, Drawable drawable, @ColorInt int i) {
        if (imageView == null) {
            return;
        }
        checkInit();
        ImageLoaderProxy.INSTANCE.load(KsAdSDKImpl.get().getContext(), str, imageView, new DisplayImageOptionsCompat.Builder().showImageOnLoading(drawable).showImageForEmptyUri(drawable).showImageOnFail(drawable).cacheInMemory(true).cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565).setCornerRound(1).build(), (ImageLoadingListener) null);
    }

    public static void loadCircleIconWithBorder(ImageView imageView, String str, Drawable drawable, Integer num, float f) {
        if (imageView == null) {
            return;
        }
        checkInit();
        ImageLoaderProxy.INSTANCE.load(KsAdSDKImpl.get().getContext(), str, imageView, new DisplayImageOptionsCompat.Builder().showImageOnLoading(drawable).showImageForEmptyUri(drawable).showImageOnFail(drawable).cacheInMemory(true).cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565).setStrokeColor(num.intValue()).setStrokeWidth(f).build(), (ImageLoadingListener) null);
    }

    public static void loadCircleIconWithoutStroke(ImageView imageView, String str, Drawable drawable) {
        if (imageView == null) {
            return;
        }
        checkInit();
        ImageLoaderProxy.INSTANCE.load(KsAdSDKImpl.get().getContext(), str, imageView, new DisplayImageOptionsCompat.Builder().showImageOnLoading(drawable).showImageForEmptyUri(drawable).showImageOnFail(drawable).cacheInMemory(true).cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565).setCircle(true).build(), (ImageLoadingListener) null);
    }

    public static void loadFeeImage(ImageView imageView, @Nullable String str, AdTemplate adTemplate) {
        loadFeeImage(imageView, str, adTemplate, null);
    }

    public static void loadFeeImage(ImageView imageView, @Nullable String str, AdTemplate adTemplate, ImageLoadingListener imageLoadingListener) {
        if (imageView == null || TextUtils.isEmpty(str)) {
            return;
        }
        checkInit();
        ImageLoaderProxy.INSTANCE.load(KsAdSDKImpl.get().getContext(), str, imageView, new DisplayImageOptionsCompat.Builder().bitmapConfig(Bitmap.Config.RGB_565).cacheInMemory(true).cacheOnDisk(true).setCornerRound(1).build(), new InnerImageLoadingListener(adTemplate, imageLoadingListener));
    }

    public static void loadImage(ImageView imageView, @Nullable String str, AdTemplate adTemplate) {
        loadImage(imageView, str, adTemplate, IMGOPTION_NORMAL);
    }

    public static void loadImage(ImageView imageView, @Nullable String str, AdTemplate adTemplate, DisplayImageOptionsCompat displayImageOptionsCompat) {
        if (imageView == null) {
            return;
        }
        checkInit();
        if (displayImageOptionsCompat == null) {
            displayImageOptionsCompat = IMGOPTION_NORMAL;
        }
        ImageLoaderProxy.INSTANCE.load(KsAdSDKImpl.get().getContext(), str, imageView, displayImageOptionsCompat, new InnerImageLoadingListener(adTemplate, null));
    }

    public static void loadImage(ImageView imageView, @Nullable String str, AdTemplate adTemplate, DisplayImageOptionsCompat displayImageOptionsCompat, ImageLoadingListener imageLoadingListener) {
        if (imageView == null || TextUtils.isEmpty(str)) {
            return;
        }
        checkInit();
        if (displayImageOptionsCompat == null) {
            displayImageOptionsCompat = IMGOPTION_NORMAL;
        }
        ImageLoaderProxy.INSTANCE.load(KsAdSDKImpl.get().getContext(), str, imageView, displayImageOptionsCompat, new InnerImageLoadingListener(adTemplate, imageLoadingListener));
    }

    public static void loadImage(ImageView imageView, @Nullable String str, AdTemplate adTemplate, ImageLoadingListener imageLoadingListener) {
        if (imageView == null || TextUtils.isEmpty(str)) {
            return;
        }
        checkInit();
        ImageLoaderProxy.INSTANCE.load(KsAdSDKImpl.get().getContext(), str, imageView, IMGOPTION_NORMAL, new InnerImageLoadingListener(adTemplate, imageLoadingListener));
    }

    public static void loadImage(String str, AdTemplate adTemplate, DisplayImageOptionsCompat displayImageOptionsCompat, ImageLoadingListener imageLoadingListener) {
        if (TextUtils.isEmpty(str) || KsAdSDKImpl.get().getContext() == null) {
            return;
        }
        checkInit();
        ImageLoaderProxy.INSTANCE.load(KsAdSDKImpl.get().getContext(), str, displayImageOptionsCompat, new InnerImageLoadingListener(adTemplate, imageLoadingListener));
    }

    public static void loadWithRadius(ImageView imageView, @Nullable String str, AdTemplate adTemplate, int i) {
        if (imageView == null || TextUtils.isEmpty(str)) {
            return;
        }
        checkInit();
        ImageLoaderProxy.INSTANCE.load(KsAdSDKImpl.get().getContext(), str, imageView, new DisplayImageOptionsCompat.Builder().showImageOnLoading(imageView.getContext().getResources().getDrawable(R.drawable.ksad_default_app_icon)).showImageForEmptyUri(imageView.getContext().getResources().getDrawable(R.drawable.ksad_default_app_icon)).showImageOnFail(imageView.getContext().getResources().getDrawable(R.drawable.ksad_default_app_icon)).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).setCornerRound(i).build(), new InnerImageLoadingListener(adTemplate, null));
    }

    public static void pause() {
        checkInit();
        ImageLoaderProxy.INSTANCE.pause();
    }

    public static void preloadImage(String str, AdTemplate adTemplate) {
        loadImage(str, adTemplate, IMGOPTION_NORMAL, (ImageLoadingListener) null);
    }

    public static void resume() {
        checkInit();
        ImageLoaderProxy.INSTANCE.resume();
    }
}
