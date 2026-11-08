package com.ss.android.socialbase.downloader.segment;

import androidx.annotation.NonNull;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SegmentStrategy {
    private static final int MAX_THREAD_COUNT = 16;
    private static final long MIN_CONNECT_TIMEOUT = 2000;
    private static final long MIN_READ_TIMEOUT = 4000;
    private static final long SEGMENT_MIN_INIT_SIZE = 5242880;
    private static final long SEGMENT_MIN_SIZE = 65536;
    public static final String TAG = "SegmentStrategy";
    private final JSONObject config;
    private int threadCount;

    private SegmentStrategy(JSONObject jSONObject) {
        this.config = jSONObject;
    }

    public void updateUrlCount(int i) {
        this.threadCount = calculateThreadCount(i);
    }

    public int getThreadCount() {
        return this.threadCount;
    }

    private int getUrlBalance() {
        return this.config.optInt(DownloadSettingKeys.SegmentConfig.URL_BALANCE, 2);
    }

    public boolean urlBalance() {
        return getUrlBalance() > 0;
    }

    public boolean urlBalanceStrictly() {
        return getUrlBalance() == 1;
    }

    public int getBufferCount() {
        return this.config.optInt(DownloadSettingKeys.SegmentConfig.BUFFER_COUNT, 512);
    }

    public int getBufferSize() {
        return this.config.optInt(DownloadSettingKeys.SegmentConfig.BUFFER_SIZE, 8192);
    }

    public boolean segmentOneByOne() {
        return this.config.optInt(DownloadSettingKeys.SegmentConfig.SEGMENT_MODE, 1) == 0;
    }

    public long getSegmentMinSize() {
        long jOptInt = this.config.optInt(DownloadSettingKeys.SegmentConfig.SEGMENT_MIN_KB, 512) * DownloadConstants.KB;
        return jOptInt < SEGMENT_MIN_SIZE ? SEGMENT_MIN_SIZE : jOptInt;
    }

    public long getSegmentMinInitSize() {
        long jOptInt = this.config.optInt(DownloadSettingKeys.SegmentConfig.SEGMENT_MIN_INIT_MB, 10) * 1048576;
        if (jOptInt < 5242880) {
            return 5242880L;
        }
        return jOptInt;
    }

    public long getSegmentMaxSize() {
        long jOptInt = this.config.optInt(DownloadSettingKeys.SegmentConfig.SEGMENT_MAX_KB, 0) * 1048576;
        if (jOptInt < getSegmentMinSize()) {
            return -1L;
        }
        return jOptInt;
    }

    public long getConnectTimeout() {
        long jOptInt = this.config.optInt(DownloadSettingKeys.SegmentConfig.CONNECT_TIMEOUT, -1);
        if (jOptInt >= MIN_CONNECT_TIMEOUT) {
            return jOptInt;
        }
        return -1L;
    }

    public long getReadTimeout() {
        long jOptInt = this.config.optInt(DownloadSettingKeys.SegmentConfig.READ_TIMEOUT, -1);
        if (jOptInt >= MIN_READ_TIMEOUT) {
            return jOptInt;
        }
        return -1L;
    }

    public int getIpStrategy() {
        return this.config.optInt(DownloadSettingKeys.SegmentConfig.IP_STRATEGY, 0);
    }

    private int calculateThreadCount(int i) {
        int iOptInt = this.config.optInt(DownloadSettingKeys.SegmentConfig.THREAD_COUNT, 4);
        if (iOptInt > 16) {
            iOptInt = 16;
        }
        if (iOptInt > 0) {
            return getUrlBalance() == 1 ? Math.min(iOptInt, i) : iOptInt;
        }
        if (getUrlBalance() > 0) {
            return i;
        }
        return 1;
    }

    public float getMainRatio() {
        return (float) this.config.optDouble(DownloadSettingKeys.SegmentConfig.MAIN_RATIO, 0.0d);
    }

    public int getRatioSegmentStrategy() {
        return this.config.optInt(DownloadSettingKeys.SegmentConfig.RATIO_SEGMENT, 0);
    }

    public float getPoorSpeedRatio() {
        return Math.min(Math.max(0.0f, (float) this.config.optDouble(DownloadSettingKeys.SegmentConfig.POOR_SPEED_RATIO, 0.0d)), 1.0f);
    }

    @NonNull
    public static SegmentStrategy from(@NonNull JSONObject jSONObject) {
        return new SegmentStrategy(jSONObject);
    }
}
