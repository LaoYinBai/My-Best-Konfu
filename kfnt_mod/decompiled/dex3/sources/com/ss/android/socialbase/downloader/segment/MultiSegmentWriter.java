package com.ss.android.socialbase.downloader.segment;

import com.ss.android.socialbase.downloader.common.AppStatusManager;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.IDownloadCache;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class MultiSegmentWriter {
    private static final boolean DEBUG = false;
    private static final int MIN_CACHE_BYTES = 65536;
    private static final int MIN_CACHE_TIME_MS = 100;
    private static final int MIN_SYNC_STEP_BYTE = 65536;
    private static final long MIN_SYNC_TIME_MS = 500;
    private static final String TAG = "MultiSegmentWriter";
    private final IDownloadRunnableCallback callback;
    private final DownloadInfo downloadInfo;
    private BaseException exception;
    private final boolean hasSyncStrategy;
    private final boolean isMonitorRw;
    private final IBufferPool pool;
    private final DownloadSetting setting;
    private final long syncIntervalMsBg;
    private final long syncIntervalMsFg;
    private long syncTimeNs;
    private final List<SegmentOutput> outputs = new LinkedList();
    private final List<SegmentOutput> doneOutputs = new ArrayList();
    private volatile boolean threadDirty = false;
    private volatile boolean paused = false;
    private volatile boolean canceled = false;
    private volatile long lastSyncBytes = 0;
    private volatile long lastSyncTimestamp = 0;
    private final IDownloadCache downloadCache = DownloadComponentManager.getDownloadCache();
    private final AppStatusManager appStatusManager = AppStatusManager.getInstance();

    private boolean isNeedSync(long j, long j2) {
        return j > 65536 && j2 > 500;
    }

    MultiSegmentWriter(DownloadInfo downloadInfo, IDownloadRunnableCallback iDownloadRunnableCallback, IBufferPool iBufferPool) {
        this.downloadInfo = downloadInfo;
        this.callback = iDownloadRunnableCallback;
        this.pool = iBufferPool;
        this.setting = DownloadSetting.obtain(downloadInfo.getId());
        this.hasSyncStrategy = this.setting.optInt(DownloadSettingKeys.SYNC_STRATEGY, 0) == 1;
        if (this.hasSyncStrategy) {
            long jOptInt = this.setting.optInt(DownloadSettingKeys.SYNC_INTERVAL_MS_FG, 5000);
            long jOptInt2 = this.setting.optInt(DownloadSettingKeys.SYNC_INTERVAL_MS_BG, 1000);
            this.syncIntervalMsFg = Math.max(jOptInt, 500L);
            this.syncIntervalMsBg = Math.max(jOptInt2, 500L);
        } else {
            this.syncIntervalMsFg = 0L;
            this.syncIntervalMsBg = 0L;
        }
        this.isMonitorRw = this.setting.optInt(DownloadSettingKeys.MONITOR_RW) == 1;
    }

    void assignOutput(SegmentOutput segmentOutput) {
        synchronized (this) {
            this.outputs.add(segmentOutput);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:71:0x00cd, code lost:
    
        if (r13 <= 0) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00d0, code lost:
    
        r3.onProgress(r13);
     */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x022e A[Catch: all -> 0x040f, TryCatch #49 {all -> 0x040f, blocks: (B:178:0x022a, B:180:0x022e, B:183:0x0234, B:185:0x023a, B:186:0x023d, B:187:0x0253, B:221:0x02d6, B:222:0x02d8, B:258:0x035e, B:260:0x0368, B:262:0x036c, B:296:0x03ed, B:298:0x03f3, B:299:0x03f6, B:300:0x040e), top: B:385:0x0027, inners: #57 }] */
    /* JADX WARN: Removed duplicated region for block: B:185:0x023a A[Catch: all -> 0x040f, TryCatch #49 {all -> 0x040f, blocks: (B:178:0x022a, B:180:0x022e, B:183:0x0234, B:185:0x023a, B:186:0x023d, B:187:0x0253, B:221:0x02d6, B:222:0x02d8, B:258:0x035e, B:260:0x0368, B:262:0x036c, B:296:0x03ed, B:298:0x03f3, B:299:0x03f6, B:300:0x040e), top: B:385:0x0027, inners: #57 }] */
    /* JADX WARN: Removed duplicated region for block: B:189:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x0368 A[Catch: all -> 0x040f, TryCatch #49 {all -> 0x040f, blocks: (B:178:0x022a, B:180:0x022e, B:183:0x0234, B:185:0x023a, B:186:0x023d, B:187:0x0253, B:221:0x02d6, B:222:0x02d8, B:258:0x035e, B:260:0x0368, B:262:0x036c, B:296:0x03ed, B:298:0x03f3, B:299:0x03f6, B:300:0x040e), top: B:385:0x0027, inners: #57 }] */
    /* JADX WARN: Removed duplicated region for block: B:298:0x03f3 A[Catch: all -> 0x040f, TryCatch #49 {all -> 0x040f, blocks: (B:178:0x022a, B:180:0x022e, B:183:0x0234, B:185:0x023a, B:186:0x023d, B:187:0x0253, B:221:0x02d6, B:222:0x02d8, B:258:0x035e, B:260:0x0368, B:262:0x036c, B:296:0x03ed, B:298:0x03f3, B:299:0x03f6, B:300:0x040e), top: B:385:0x0027, inners: #57 }] */
    /* JADX WARN: Removed duplicated region for block: B:312:0x0423  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0066 A[Catch: all -> 0x0037, Throwable -> 0x0042, BaseException -> 0x004c, StreamClosedException -> 0x0056, TRY_ENTER, TRY_LEAVE, TryCatch #36 {StreamClosedException -> 0x0056, BaseException -> 0x004c, all -> 0x0037, Throwable -> 0x0042, blocks: (B:11:0x0032, B:31:0x0066), top: B:403:0x0032 }] */
    /* JADX WARN: Removed duplicated region for block: B:320:0x0449  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x02e3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:357:0x0262 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:361:0x00f6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:363:0x0281 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:365:0x0302 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:367:0x042b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:375:0x0399 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:386:0x044b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:394:0x0077 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:414:0x008c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void loopAndWrite(com.ss.android.socialbase.downloader.segment.IInput r36) throws com.ss.android.socialbase.downloader.exception.BaseException {
        /*
            Method dump skipped, instructions count: 1184
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.downloader.segment.MultiSegmentWriter.loopAndWrite(com.ss.android.socialbase.downloader.segment.IInput):void");
    }

    private void checkAndSync(long j, boolean z) throws IOException {
        long j2 = j - this.lastSyncTimestamp;
        if (this.hasSyncStrategy) {
            if (j2 > (this.appStatusManager.isAppForeground() ? this.syncIntervalMsFg : this.syncIntervalMsBg)) {
                flushAndSync();
                this.lastSyncTimestamp = j;
                return;
            }
            return;
        }
        long curBytes = this.downloadInfo.getCurBytes() - this.lastSyncBytes;
        if (z || isNeedSync(curBytes, j2)) {
            flushAndSync();
            this.lastSyncTimestamp = j;
        }
    }

    public long getLastSyncBytes() {
        return this.lastSyncBytes;
    }

    private void outputDone(IOutput iOutput) {
        synchronized (this) {
            this.doneOutputs.add((SegmentOutput) iOutput);
        }
    }

    private void flushAndSync() throws IOException {
        boolean z = this.isMonitorRw;
        long jNanoTime = z ? System.nanoTime() : 0L;
        DownloadInfo downloadInfo = this.downloadInfo;
        IDownloadCache iDownloadCache = this.downloadCache;
        List<SegmentOutput> list = this.outputs;
        List<SegmentOutput> list2 = this.doneOutputs;
        Map<Long, Segment> segmentMap = iDownloadCache.getSegmentMap(downloadInfo.getId());
        if (segmentMap == null) {
            segmentMap = new HashMap<>(4);
        }
        boolean z2 = false;
        synchronized (this) {
            flush(list);
            try {
                sync(list);
                z2 = true;
            } catch (Throwable th) {
                th.printStackTrace();
            }
            updateSegmentToMap(list, segmentMap);
            if (list2.size() > 0) {
                close(list2);
                list.removeAll(list2);
                list2.clear();
            }
        }
        if (z2) {
            downloadInfo.updateRealDownloadTime(true);
            iDownloadCache.updateSegments(downloadInfo.getId(), segmentMap);
            iDownloadCache.updateDownloadInfo(downloadInfo);
            this.lastSyncBytes = downloadInfo.getCurBytes();
        }
        if (z) {
            this.syncTimeNs += System.nanoTime() - jNanoTime;
        }
    }

    private void flush(List<SegmentOutput> list) throws IOException {
        Iterator<SegmentOutput> it = list.iterator();
        while (it.hasNext()) {
            it.next().flush();
        }
    }

    private void sync(List<SegmentOutput> list) throws IOException {
        Iterator<SegmentOutput> it = list.iterator();
        while (it.hasNext()) {
            it.next().sync();
        }
    }

    private void close(List<SegmentOutput> list) {
        Iterator<SegmentOutput> it = list.iterator();
        while (it.hasNext()) {
            it.next().close();
        }
    }

    private void updateSegmentToMap(List<SegmentOutput> list, Map<Long, Segment> map) {
        Iterator<SegmentOutput> it = list.iterator();
        while (it.hasNext()) {
            Segment segment = it.next().getSegment();
            Segment segment2 = map.get(Long.valueOf(segment.getStartOffset()));
            if (segment2 == null) {
                map.put(Long.valueOf(segment.getStartOffset()), new Segment(segment));
            } else {
                segment2.setCurrentOffset(segment.getCurrentOffset());
                segment2.setEndOffset(segment.getEndOffset());
            }
        }
    }

    public void cancel() {
        this.canceled = true;
        this.threadDirty = true;
    }

    public void pause() {
        this.paused = true;
        this.threadDirty = true;
    }
}
