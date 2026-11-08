package com.appsflyer.cache;

import java.util.Scanner;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class RequestCacheData {

    /* renamed from: ˊ, reason: contains not printable characters */
    private String f170;

    /* renamed from: ˋ, reason: contains not printable characters */
    private String f171;

    /* renamed from: ˏ, reason: contains not printable characters */
    private String f172;

    /* renamed from: ॱ, reason: contains not printable characters */
    private String f173;

    public RequestCacheData(String str, String str2, String str3) {
        this.f172 = str;
        this.f170 = str2;
        this.f171 = str3;
    }

    public RequestCacheData(char[] cArr) {
        Scanner scanner = new Scanner(new String(cArr));
        while (scanner.hasNextLine()) {
            String strNextLine = scanner.nextLine();
            if (strNextLine.startsWith("url=")) {
                this.f172 = strNextLine.substring(4).trim();
            } else if (strNextLine.startsWith("version=")) {
                this.f171 = strNextLine.substring(8).trim();
            } else if (strNextLine.startsWith("data=")) {
                this.f170 = strNextLine.substring(5).trim();
            }
        }
        scanner.close();
    }

    public String getVersion() {
        return this.f171;
    }

    public void setVersion(String str) {
        this.f171 = str;
    }

    public String getPostData() {
        return this.f170;
    }

    public void setPostData(String str) {
        this.f170 = str;
    }

    public String getRequestURL() {
        return this.f172;
    }

    public void setRequestURL(String str) {
        this.f172 = str;
    }

    public String getCacheKey() {
        return this.f173;
    }

    public void setCacheKey(String str) {
        this.f173 = str;
    }
}
