package com.tachikoma.core.component.imageview;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.text.TextUtils;
import com.kwad.sdk.e.d;
import com.kwad.sdk.e.f;
import com.sigmob.sdk.common.Constants;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.Tachikoma;
import com.tachikoma.core.api.IWebImageHandlerInner;
import com.tachikoma.core.component.TKBase;
import com.tachikoma.core.config.BlockOptConfig;
import com.tachikoma.core.log.Logger;
import com.tachikoma.core.manager.TKModuleManager;
import com.tachikoma.core.utility.FileUtil;
import com.tachikoma.core.utility.TKAsync;
import com.tachikoma.core.utility.TKColorUtil;
import com.tachikoma.core.utility.TKConverter;
import com.tachikoma.core.utility.TKUtility;
import com.tachikoma.core.utility.UriUtil;
import java.io.File;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKImage extends TKBase<RoundImageView> {
    public int blurRadius;
    private final Handler mHandler;
    public String mPlaceHolder;
    private long setId;

    @Deprecated
    public String src;
    public String uri;

    public TKImage(Context context, List<Object> list) {
        super(context, list);
        this.mHandler = new Handler();
        this.setId = 0L;
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 > i2 && i7 / i5 > i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    private BitmapFactory.Options getBoundsOptions(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return options;
    }

    private BitmapFactory.Options getSampleSizeOptions(String str) {
        BitmapFactory.Options boundsOptions = getBoundsOptions(str);
        boundsOptions.inJustDecodeBounds = false;
        boundsOptions.inSampleSize = calculateInSampleSize(boundsOptions, (int) getDomNode().getYogaNode().getWidth().value, (int) getDomNode().getYogaNode().getHeight().value);
        return boundsOptions;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUrls(List<TKCDNUrlInner> list, int i, int i2) {
        IWebImageHandlerInner iWebImageHandlerInner = (IWebImageHandlerInner) TKModuleManager.getInstance().getHandler(this.mTKJSContext, IWebImageHandlerInner.class);
        if (list == null || list.isEmpty()) {
            iWebImageHandlerInner.load("", getView(), 0);
            return;
        }
        if (TextUtils.isEmpty(this.mPlaceHolder)) {
            if (iWebImageHandlerInner != null) {
                iWebImageHandlerInner.load(getView(), list, i, i2, this.blurRadius);
            }
        } else {
            String strConcat = getRootDir().concat(this.mPlaceHolder);
            BitmapDrawable bitmapDrawable = new File(strConcat).exists() ? new BitmapDrawable(BitmapFactory.decodeFile(strConcat, getSampleSizeOptions(strConcat))) : null;
            if (iWebImageHandlerInner != null) {
                iWebImageHandlerInner.load(getView(), list, i, i2, bitmapDrawable, this.blurRadius);
            }
        }
    }

    private void setUrlsAfterOpt(final String str, final int i, final int i2) {
        final long j = this.setId + 1;
        this.setId = j;
        setUrls((List<TKCDNUrlInner>) null, i, i2);
        TKAsync.execute(new Runnable() { // from class: com.tachikoma.core.component.imageview.TKImage.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    final List listA = f.a(str, new d<TKCDNUrlInner>() { // from class: com.tachikoma.core.component.imageview.TKImage.2.1
                        public TKCDNUrlInner createInstance() {
                            return new TKCDNUrlInner();
                        }
                    });
                    TKImage.this.mHandler.post(new Runnable() { // from class: com.tachikoma.core.component.imageview.TKImage.2.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (j == TKImage.this.setId) {
                                TKImage.this.setUrls((List<TKCDNUrlInner>) listA, i, i2);
                            }
                        }
                    });
                } catch (Exception e) {
                    Logger.logE("TKImage TKCDNUrlInner fromJson exception", e);
                }
            }
        });
    }

    private void setUrlsBeforeOpt(String str, int i, int i2) {
        List<TKCDNUrlInner> listA;
        try {
            listA = f.a(str, new d<TKCDNUrlInner>() { // from class: com.tachikoma.core.component.imageview.TKImage.1
                public TKCDNUrlInner createInstance() {
                    return new TKCDNUrlInner();
                }
            });
        } catch (Exception e) {
            Logger.logE("TKImage TKCDNUrlInner fromJson exception", e);
            listA = null;
        }
        if (listA == null || listA.isEmpty()) {
            return;
        }
        IWebImageHandlerInner iWebImageHandlerInner = (IWebImageHandlerInner) TKModuleManager.getInstance().getHandler(this.mTKJSContext, IWebImageHandlerInner.class);
        if (TextUtils.isEmpty(this.mPlaceHolder)) {
            if (iWebImageHandlerInner != null) {
                iWebImageHandlerInner.load(getView(), listA, i, i2, this.blurRadius);
            }
        } else {
            String strConcat = getRootDir().concat(this.mPlaceHolder);
            BitmapDrawable bitmapDrawable = new File(strConcat).exists() ? new BitmapDrawable(BitmapFactory.decodeFile(strConcat, getSampleSizeOptions(strConcat))) : null;
            if (iWebImageHandlerInner != null) {
                iWebImageHandlerInner.load(getView(), listA, i, i2, bitmapDrawable, this.blurRadius);
            }
        }
    }

    private void showAssetImage() {
        getView().setImageResource(TKUtility.getResourceId(UriUtil.getResName(this.uri), "drawable", null));
    }

    private void showBundleImage() {
        String strConcat = getRootDir().concat(UriUtil.getMappedPath(this.uri, UriUtil.BUNDLE_PREFIX));
        if (FileUtil.exists(strConcat)) {
            getView().setImageBitmap(BitmapFactory.decodeFile(strConcat, getSampleSizeOptions(strConcat)));
        }
    }

    private void showFileImage() {
        String mappedPath = UriUtil.getMappedPath(this.uri, UriUtil.FILE_PREFIX);
        if (FileUtil.exists(mappedPath)) {
            getView().setImageBitmap(BitmapFactory.decodeFile(mappedPath, getSampleSizeOptions(mappedPath)));
        }
    }

    private void showNetImage() {
        IWebImageHandlerInner iWebImageHandlerInner = (IWebImageHandlerInner) TKModuleManager.getInstance().getHandler(this.mTKJSContext, IWebImageHandlerInner.class);
        if (iWebImageHandlerInner == null) {
            Logger.logE("TKImage", new IllegalStateException("IWebImageHandlerInner is null"));
        } else if (TextUtils.isEmpty(this.mPlaceHolder)) {
            iWebImageHandlerInner.load(this.uri, getView(), this.blurRadius);
        } else {
            String strConcat = getRootDir().concat(this.mPlaceHolder);
            iWebImageHandlerInner.load(getView(), this.uri, FileUtil.exists(strConcat) ? new BitmapDrawable(BitmapFactory.decodeFile(strConcat, getSampleSizeOptions(strConcat))) : null, this.blurRadius);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.tachikoma.core.component.TKBase
    public RoundImageView createViewInstance(Context context) {
        return new RoundImageView(context);
    }

    @Override // com.tachikoma.core.component.TKBase, com.tachikoma.core.common.ILifeCycle
    public void onCreate() {
        super.onCreate();
    }

    @Override // com.tachikoma.core.component.TKBase, com.tachikoma.core.common.ILifeCycle
    public void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    @Override // com.tachikoma.core.component.TKBase
    protected void onShadowSet(float f) {
        super.onShadowSet(f);
        getView().onShadowSet(f);
    }

    public void setBlurRadius(int i) {
        this.blurRadius = i;
    }

    @Override // com.tachikoma.core.component.TKBase
    public void setBorderColor(String str) {
        super.setBorderColor(str);
        getView().setBorderColor(Color.parseColor(TKColorUtil.rgba2argb(str)));
    }

    @Override // com.tachikoma.core.component.TKBase
    public void setBorderRadius(int i) {
        super.setBorderRadius(i);
        getView().setBorderRadius(i);
    }

    @Override // com.tachikoma.core.component.TKBase
    public void setBorderWidth(double d) {
        super.setBorderWidth(d);
        getView().setBorderWidth(TKConverter.dp2px(Tachikoma.sApplication, (float) d));
    }

    @Override // com.tachikoma.core.component.TKBase
    public void setBottomLeftRadius(int i) {
        super.setBottomLeftRadius(i);
        getView().setBottomLeftRoundRadius(TKConverter.dp2px(Tachikoma.sApplication, i));
    }

    @Override // com.tachikoma.core.component.TKBase
    public void setBottomRightRadius(int i) {
        super.setBottomRightRadius(i);
        getView().setBottomRightRoundRadius(TKConverter.dp2px(Tachikoma.sApplication, i));
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setContentMode(java.lang.String r3) {
        /*
            r2 = this;
            int r0 = r3.hashCode()
            r1 = -1881872635(0xffffffff8fd4e705, float:-2.09938E-29)
            if (r0 == r1) goto L37
            r1 = -1008619738(0xffffffffc3e1af26, float:-451.36835)
            if (r0 == r1) goto L2d
            r1 = 94852023(0x5a753b7, float:1.5735357E-35)
            if (r0 == r1) goto L23
            r1 = 951526612(0x38b724d4, float:8.73298E-5)
            if (r0 == r1) goto L19
            goto L41
        L19:
            java.lang.String r0 = "contain"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L41
            r3 = 1
            goto L42
        L23:
            java.lang.String r0 = "cover"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L41
            r3 = 2
            goto L42
        L2d:
            java.lang.String r0 = "origin"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L41
            r3 = 0
            goto L42
        L37:
            java.lang.String r0 = "stretch"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L41
            r3 = 3
            goto L42
        L41:
            r3 = -1
        L42:
            switch(r3) {
                case 0: goto L61;
                case 1: goto L58;
                case 2: goto L4f;
                case 3: goto L46;
                default: goto L45;
            }
        L45:
            goto L6c
        L46:
            android.view.View r3 = r2.getView()
            com.tachikoma.core.component.imageview.RoundImageView r3 = (com.tachikoma.core.component.imageview.RoundImageView) r3
            android.widget.ImageView$ScaleType r0 = android.widget.ImageView.ScaleType.FIT_XY
            goto L69
        L4f:
            android.view.View r3 = r2.getView()
            com.tachikoma.core.component.imageview.RoundImageView r3 = (com.tachikoma.core.component.imageview.RoundImageView) r3
            android.widget.ImageView$ScaleType r0 = android.widget.ImageView.ScaleType.CENTER_CROP
            goto L69
        L58:
            android.view.View r3 = r2.getView()
            com.tachikoma.core.component.imageview.RoundImageView r3 = (com.tachikoma.core.component.imageview.RoundImageView) r3
            android.widget.ImageView$ScaleType r0 = android.widget.ImageView.ScaleType.FIT_CENTER
            goto L69
        L61:
            android.view.View r3 = r2.getView()
            com.tachikoma.core.component.imageview.RoundImageView r3 = (com.tachikoma.core.component.imageview.RoundImageView) r3
            android.widget.ImageView$ScaleType r0 = android.widget.ImageView.ScaleType.FIT_START
        L69:
            r3.setScaleType(r0)
        L6c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tachikoma.core.component.imageview.TKImage.setContentMode(java.lang.String):void");
    }

    public void setPlaceholder(String str) {
        this.mPlaceHolder = str;
    }

    @Deprecated
    public void setSrc(String str) {
        this.src = str;
        if (this.src.startsWith("//")) {
            this.src = "https:" + this.src;
        }
        if (!this.src.startsWith(Constants.HTTP)) {
            String strConcat = getRootDir().concat(this.src);
            if (new File(strConcat).exists()) {
                getView().setImageBitmap(BitmapFactory.decodeFile(strConcat, getSampleSizeOptions(strConcat)));
                return;
            } else {
                getView().setImageResource(TKUtility.getResourceId(this.src, "drawable", null));
                return;
            }
        }
        IWebImageHandlerInner iWebImageHandlerInner = (IWebImageHandlerInner) TKModuleManager.getInstance().getHandler(this.mTKJSContext, IWebImageHandlerInner.class);
        if (TextUtils.isEmpty(this.mPlaceHolder)) {
            if (iWebImageHandlerInner != null) {
                iWebImageHandlerInner.load(this.src, getView(), this.blurRadius);
            }
        } else {
            String strConcat2 = getRootDir().concat(this.mPlaceHolder);
            BitmapDrawable bitmapDrawable = new File(strConcat2).exists() ? new BitmapDrawable(BitmapFactory.decodeFile(strConcat2, getSampleSizeOptions(strConcat2))) : null;
            if (iWebImageHandlerInner != null) {
                iWebImageHandlerInner.load(getView(), this.src, bitmapDrawable, this.blurRadius);
            }
        }
    }

    @Override // com.tachikoma.core.component.TKBase
    public void setTopLeftRadius(int i) {
        super.setTopLeftRadius(i);
        getView().setTopLeftRoundRadius(TKConverter.dp2px(Tachikoma.sApplication, i));
    }

    @Override // com.tachikoma.core.component.TKBase
    public void setTopRightRadius(int i) {
        super.setTopRightRadius(i);
        getView().setTopRightRoundRadius(TKConverter.dp2px(Tachikoma.sApplication, i));
    }

    public void setUri(String str) {
        this.uri = str;
        if (str == null || str.length() <= 0) {
            return;
        }
        try {
            if (str.startsWith(UriUtil.ASSET_PREFIX)) {
                showAssetImage();
                return;
            }
            if (!str.startsWith(UriUtil.BUNDLE_PREFIX)) {
                if (str.startsWith(UriUtil.FILE_PREFIX)) {
                    showFileImage();
                    return;
                } else if (str.startsWith(UriUtil.HTTPS_PREFIX) || str.startsWith(UriUtil.HTTP_PREFIX)) {
                    showNetImage();
                    return;
                }
            }
            showBundleImage();
        } catch (Throwable th) {
            Logger.logE("TKImage", th);
        }
    }

    public void setUrls(String str, int i, int i2) {
        if (BlockOptConfig.blockOpt()) {
            setUrlsAfterOpt(str, i, i2);
        } else {
            setUrlsBeforeOpt(str, i, i2);
        }
    }
}
