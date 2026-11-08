package com.ss.union.game.sdk.common.download;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface GameDownloadStatusChangeListener {
    void onDownloadActive(GameDownloadShortInfo gameDownloadShortInfo, int i);

    void onDownloadFailed(GameDownloadShortInfo gameDownloadShortInfo);

    void onDownloadFinished(GameDownloadShortInfo gameDownloadShortInfo);

    void onDownloadPaused(GameDownloadShortInfo gameDownloadShortInfo, int i);

    void onDownloadStart();

    void onIdle();

    void onInstalled(GameDownloadShortInfo gameDownloadShortInfo);
}
