package com.bytedance.sdk.open.aweme.base;

import android.os.Bundle;
import com.bytedance.sdk.open.aweme.common.constants.ParamKeyConstants;
import java.util.ArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TikTokImageObject implements IMediaObject {
    private static final String TAG = "TikTokImageObject";
    public ArrayList<String> mImagePaths;

    @Override // com.bytedance.sdk.open.aweme.base.IMediaObject
    public boolean checkArgs() {
        return true;
    }

    @Override // com.bytedance.sdk.open.aweme.base.IMediaObject
    public int type() {
        return 2;
    }

    public TikTokImageObject() {
    }

    public TikTokImageObject(ArrayList<String> arrayList) {
        this.mImagePaths = new ArrayList<>();
        this.mImagePaths.addAll(arrayList);
    }

    @Override // com.bytedance.sdk.open.aweme.base.IMediaObject
    public void serialize(Bundle bundle) {
        bundle.putStringArrayList(ParamKeyConstants.AWEME_EXTRA_MEDIA_MESSAGE_IMAGE_PATH, this.mImagePaths);
    }

    @Override // com.bytedance.sdk.open.aweme.base.IMediaObject
    public void unserialize(Bundle bundle) {
        this.mImagePaths = bundle.getStringArrayList(ParamKeyConstants.AWEME_EXTRA_MEDIA_MESSAGE_IMAGE_PATH);
    }
}
