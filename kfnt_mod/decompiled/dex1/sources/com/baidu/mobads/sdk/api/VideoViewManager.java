package com.baidu.mobads.sdk.api;

import android.graphics.Rect;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class VideoViewManager {
    public static final String TAG = "VideoViewManager";
    private CpuVideoView mCurVideoView;
    private ArrayList<CpuVideoView> mVideoViews;

    private VideoViewManager() {
        this.mVideoViews = new ArrayList<>();
    }

    public static VideoViewManager getInstance() {
        return Inner.viewManager;
    }

    private static final class Inner {
        private static final VideoViewManager viewManager = new VideoViewManager();

        private Inner() {
        }
    }

    public boolean isExistedOtherPlay() {
        Iterator<CpuVideoView> it = this.mVideoViews.iterator();
        while (it.hasNext()) {
            if (it.next().isPlaying()) {
                return true;
            }
        }
        return false;
    }

    public void removeItem(CpuVideoView cpuVideoView) {
        this.mVideoViews.remove(cpuVideoView);
        if (this.mVideoViews.isEmpty()) {
            release();
        }
    }

    private void release() {
        this.mCurVideoView = null;
    }

    public void onWindowFocusChanged(CpuVideoView cpuVideoView, boolean z) {
        if (z && cpuVideoView == this.mCurVideoView) {
            this.mCurVideoView.play();
        } else {
            if (z) {
                return;
            }
            Iterator<CpuVideoView> it = this.mVideoViews.iterator();
            while (it.hasNext()) {
                it.next().pause();
            }
        }
    }

    public void manageItem(CpuVideoView cpuVideoView) {
        if (!this.mVideoViews.contains(cpuVideoView)) {
            this.mVideoViews.add(cpuVideoView);
        }
        Iterator<CpuVideoView> it = this.mVideoViews.iterator();
        while (it.hasNext()) {
            CpuVideoView next = it.next();
            if (next != cpuVideoView) {
                next.pause();
            }
        }
        this.mCurVideoView = cpuVideoView;
        this.mCurVideoView.play();
    }

    public void addAdVideoItem(CpuVideoView cpuVideoView) {
        if (this.mVideoViews.contains(cpuVideoView)) {
            return;
        }
        this.mVideoViews.add(cpuVideoView);
    }

    public boolean isExistOtherAdOrHighPriPlaying() {
        for (int i = 0; i < this.mVideoViews.size(); i++) {
            if ("ad".equals(this.mVideoViews.get(i).getCPUData().getType())) {
                if (this.mVideoViews.get(i).isPlaying()) {
                    return true;
                }
            } else if (this.mVideoViews.get(i).mStatusByUserOrSys == 1) {
                return true;
            }
        }
        return false;
    }

    public CpuVideoView findBestView() {
        CpuVideoView cpuVideoView = null;
        for (int i = 0; i < this.mVideoViews.size(); i++) {
            if ("ad".equals(this.mVideoViews.get(i).getCPUData().getType())) {
                CpuVideoView cpuVideoView2 = this.mVideoViews.get(i);
                if (cpuVideoView == null || getRawRectY(cpuVideoView2) <= getRawRectY(cpuVideoView)) {
                    cpuVideoView = cpuVideoView2;
                }
            }
        }
        return cpuVideoView;
    }

    private int getRawRectY(CpuVideoView cpuVideoView) {
        Rect rect = new Rect();
        cpuVideoView.getGlobalVisibleRect(rect);
        return rect.top;
    }
}
