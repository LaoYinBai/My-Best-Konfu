package com.vivo.push.model;

import com.vivo.push.NoPorGuard;
import java.util.HashMap;
import java.util.Map;

@NoPorGuard
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UPSNotificationMessage {
    public static final int CUSTOM = 3;
    public static final int ISMACROREPLACE = 1;
    public static final int OPENACTIVITY = 4;
    public static final int OPENAPP = 1;
    public static final int OPENURL = 2;
    public static final int UNKNOWN = 0;
    private String mAdClickCheckUrl;
    private int mCompatibleType;
    private String mContent;
    private String mCoverUrl;
    private String mIconUrl;
    private int mIsMacroReplace;
    private long mMsgId;
    private int mNotifyType;
    private Map<String, String> mParams = new HashMap();
    private String mPurePicUrl;
    private boolean mShowTime;
    private String mSkipContent;
    private int mSkipType;
    private int mTargetType;
    private String mTitle;
    private String mTragetContent;

    public long getMsgId() {
        return this.mMsgId;
    }

    public void setMsgId(long j) {
        this.mMsgId = j;
    }

    public String getTragetContent() {
        return this.mTragetContent;
    }

    public void setTragetContext(String str) {
        this.mTragetContent = str;
    }

    public int getTargetType() {
        return this.mTargetType;
    }

    public void setTargetType(int i) {
        this.mTargetType = i;
    }

    public String getContent() {
        return this.mContent;
    }

    public void setContent(String str) {
        this.mContent = str;
    }

    public String getCoverUrl() {
        return this.mCoverUrl;
    }

    public void setCoverUrl(String str) {
        this.mCoverUrl = str;
    }

    public String getIconUrl() {
        return this.mIconUrl;
    }

    public void setIconUrl(String str) {
        this.mIconUrl = str;
    }

    public int getNotifyType() {
        return this.mNotifyType;
    }

    public void setNotifyType(int i) {
        this.mNotifyType = i;
    }

    public String getPurePicUrl() {
        return this.mPurePicUrl;
    }

    public void setPurePicUrl(String str) {
        this.mPurePicUrl = str;
    }

    public boolean isShowTime() {
        return this.mShowTime;
    }

    public void setShowTime(boolean z) {
        this.mShowTime = z;
    }

    public String getSkipContent() {
        return this.mSkipContent;
    }

    public void setSkipContent(String str) {
        this.mSkipContent = str;
    }

    public int getSkipType() {
        return this.mSkipType;
    }

    public void setSkipType(int i) {
        this.mSkipType = i;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void clearCoverUrl() {
        this.mIconUrl = "";
    }

    public void clearPurePicUrl() {
        this.mPurePicUrl = "";
    }

    public Map<String, String> getParams() {
        return this.mParams;
    }

    public void setParams(Map<String, String> map) {
        this.mParams = map;
    }

    public boolean isMacroReplace() {
        return this.mIsMacroReplace == 1;
    }

    public int getIsMacroReplace() {
        return this.mIsMacroReplace;
    }

    public void setIsMacroReplace(int i) {
        this.mIsMacroReplace = i;
    }

    public String getAdClickCheckUrl() {
        return this.mAdClickCheckUrl;
    }

    public void setAdClickCheckUrl(String str) {
        this.mAdClickCheckUrl = str;
    }

    public int getCompatibleType() {
        return this.mCompatibleType;
    }

    public void setCompatibleType(int i) {
        this.mCompatibleType = i;
    }

    public String toString() {
        return "UPSNotificationMessage{mTargetType=" + this.mTargetType + ", mTragetContent='" + this.mTragetContent + "', mTitle='" + this.mTitle + "', mContent='" + this.mContent + "', mNotifyType=" + this.mNotifyType + ", mPurePicUrl='" + this.mPurePicUrl + "', mIconUrl='" + this.mIconUrl + "', mCoverUrl='" + this.mCoverUrl + "', mSkipContent='" + this.mSkipContent + "', mSkipType=" + this.mSkipType + ", mShowTime=" + this.mShowTime + ", mMsgId=" + this.mMsgId + ", mParams=" + this.mParams + '}';
    }
}
