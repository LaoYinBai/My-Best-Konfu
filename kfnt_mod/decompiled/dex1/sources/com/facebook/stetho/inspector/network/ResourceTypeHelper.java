package com.facebook.stetho.inspector.network;

import com.baidu.mobads.sdk.internal.ae;
import com.facebook.stetho.inspector.protocol.module.Page;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ResourceTypeHelper {
    private final MimeMatcher<Page.ResourceType> mMimeMatcher = new MimeMatcher<>();

    public ResourceTypeHelper() {
        this.mMimeMatcher.addRule("text/css", Page.ResourceType.STYLESHEET);
        this.mMimeMatcher.addRule("image/*", Page.ResourceType.IMAGE);
        this.mMimeMatcher.addRule("application/x-javascript", Page.ResourceType.SCRIPT);
        this.mMimeMatcher.addRule("text/javascript", Page.ResourceType.XHR);
        this.mMimeMatcher.addRule(ae.d, Page.ResourceType.XHR);
        this.mMimeMatcher.addRule("text/*", Page.ResourceType.DOCUMENT);
        this.mMimeMatcher.addRule("*", Page.ResourceType.OTHER);
    }

    public Page.ResourceType determineResourceType(String str) {
        return this.mMimeMatcher.match(stripContentExtras(str));
    }

    public String stripContentExtras(String str) {
        int iIndexOf = str.indexOf(59);
        return iIndexOf >= 0 ? str.substring(0, iIndexOf) : str;
    }
}
