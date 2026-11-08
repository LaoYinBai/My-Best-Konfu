package com.kwad.tachikoma;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.kuaishou.tachikoma.api.app.IAnimatedImage;
import com.kuaishou.tachikoma.api.app.IHostEnv;
import com.kuaishou.tachikoma.api.app.IHostService;
import com.kuaishou.tachikoma.api.app.WebImageHandler;
import com.kuaishou.tachikoma.api.model.TKCDNUrl;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.imageloader.core.DisplayImageOptionsCompat;
import com.kwad.sdk.core.response.model.AdTemplate;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b implements IHostService {
    @Override // com.kuaishou.tachikoma.api.app.IHostService
    public IAnimatedImage obtainAnimatedImage() {
        return null;
    }

    @Override // com.kuaishou.tachikoma.api.app.IHostService
    public IHostEnv obtainHostEnv() {
        return new IHostEnv() { // from class: com.kwad.tachikoma.b.2
            @Override // com.kuaishou.tachikoma.api.app.IHostEnv
            public boolean isDark() {
                return false;
            }

            @Override // com.kuaishou.tachikoma.api.app.IHostEnv
            public boolean isDebug() {
                return false;
            }

            @Override // com.kuaishou.tachikoma.api.app.IHostEnv
            public boolean isShowLog() {
                return com.kwad.sdk.core.d.a.a;
            }
        };
    }

    @Override // com.kuaishou.tachikoma.api.app.IHostService
    public WebImageHandler obtainWebImageService() {
        return new WebImageHandler() { // from class: com.kwad.tachikoma.b.1
            @Override // com.kuaishou.tachikoma.api.app.WebImageHandler
            public void load(ImageView imageView, String str, Drawable drawable, int i) {
                KSImageLoader.loadImage(imageView, str, (AdTemplate) null, new DisplayImageOptionsCompat.Builder().bitmapConfig(Bitmap.Config.ARGB_8888).cacheOnDisk(true).cacheInMemory(true).showImageOnFail(drawable).showImageForEmptyUri(drawable).showImageOnLoading(drawable).build());
            }

            @Override // com.kuaishou.tachikoma.api.app.WebImageHandler
            public void load(ImageView imageView, List<TKCDNUrl> list, int i, int i2, int i3) {
                if (list == null || list.isEmpty()) {
                    return;
                }
                KSImageLoader.loadImage(imageView, list.get(0).getUrl(), null);
            }

            @Override // com.kuaishou.tachikoma.api.app.WebImageHandler
            public void load(ImageView imageView, List<TKCDNUrl> list, int i, int i2, Drawable drawable, int i3) {
                if (list == null || list.isEmpty()) {
                    return;
                }
                TKCDNUrl tKCDNUrl = list.get(0);
                KSImageLoader.loadImage(imageView, tKCDNUrl.getUrl(), (AdTemplate) null, new DisplayImageOptionsCompat.Builder().bitmapConfig(Bitmap.Config.ARGB_8888).cacheOnDisk(true).cacheInMemory(true).showImageOnFail(drawable).showImageForEmptyUri(drawable).showImageOnLoading(drawable).build());
            }

            @Override // com.kuaishou.tachikoma.api.app.WebImageHandler
            public void load(String str, ImageView imageView, int i) {
                KSImageLoader.loadImage(imageView, str, null);
            }
        };
    }
}
