package com.sigmob.sdk.base.common;

import com.sigmob.sdk.common.mta.PointCategory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public enum a {
    AD_START(PointCategory.START, null),
    AD_FINISH(PointCategory.FINISH, null),
    AD_CLICK("click", null),
    AD_CLOSE_CARD_SHOW("closecard_show", null),
    AD_CLOSE_CARD_CLOSE("closecard_close", null),
    AD_FOUR_ELEMENTS_SHOW("fourElements_show", null),
    AD_FOUR_ELEMENTS_CLOSE("fourElements_close", null),
    AD_SHOW(PointCategory.SHOW, null),
    AD_PLAYING("ad_playing", null),
    AD_PLAY_LOAD("ad_play_load", null),
    AD_PAUSE("pause", null),
    AD_PLAY_START(PointCategory.VIDEO_START, null),
    AD_PLAY_PAUSE(PointCategory.VIDEO_PAUSE, null),
    AD_PLAY_QUARTER(PointCategory.PLAY_QUARTER, null),
    AD_PLAY_TWO_QUARTERS(PointCategory.PLAY_TWO_QUARTERS, null),
    AD_PLAY_THREE_QUARTERS(PointCategory.PLAY_THREE_QUARTERS, null),
    AD_PLAY_COMPLETE("play_complete", null),
    AD_COMPLETE(PointCategory.COMPLETE, null),
    AD_SKIP(PointCategory.SKIP, null),
    AD_CLICK_SKIP("click_skip", null),
    AD_COMPANION_CLICK("companion_click", null),
    AD_VIDEO_CLICK(PointCategory.VIDEO_CLICK, null),
    AD_CLOSE("ad_close", null),
    AD_VCLOSE("ad_vclose", null),
    AD_LOAD(PointCategory.LOAD, null),
    AD_LOAD_SUCCESS("load_success", null),
    AD_LOAD_FAILURE("load_failure", null),
    AD_MUTE("mute", null),
    AD_UNMUTE("unmute", null),
    AD_ROTATION("rotation", null),
    AD_DOWNLOAD_START(PointCategory.DOWNLOAD_START, null),
    AD_DOWNLOAD_FINISH("download_finish", null),
    AD_INSTALL_START("install_start", null),
    AD_INSTALL_FINISH("install_finish", null),
    AD_OPEN_DEEPLINK(PointCategory.OPEN_DEEPLINK, null),
    AD_OPEN_DEEPLINK_FAIL(PointCategory.OPEN_DEEPLINK_FAILED, null),
    AD_ERROR("error", null),
    AD_SHOW_SKIP("show_skip", null),
    AD_REWARD("reward", null),
    AD_VIDEO_START("ad_video_start", null),
    AD_NATIVE_SHOW(PointCategory.AD_SHOW, null),
    AD_FEED_CLICK(PointCategory.FEED_CLICK, null),
    AD_NATIVE_VIDEO_START(PointCategory.VIDEO_START, null),
    AD_NATIVE_VIDEO_PAUSE(PointCategory.VIDEO_PAUSE, null),
    AD_HIDE(PointCategory.AD_HIDE, null),
    AD_VIDEO_LINK(PointCategory.VIDEO_LINK, null),
    AD_NATIVE_VIDEO_RESTART(PointCategory.VIDEO_RESTART, null);

    private final String V;
    private final String W;

    a(String str, String str2) {
        this.V = str;
        this.W = str2;
    }

    public String a() {
        return this.V;
    }
}
