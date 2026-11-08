package com.liulishuo.filedownloader.services;

import com.liulishuo.filedownloader.connection.DefaultConnectionCountAdapter;
import com.liulishuo.filedownloader.connection.FileDownloadUrlConnection;
import com.liulishuo.filedownloader.database.FileDownloadDatabase;
import com.liulishuo.filedownloader.database.RemitDatabase;
import com.liulishuo.filedownloader.services.ForegroundServiceConfig;
import com.liulishuo.filedownloader.stream.FileDownloadRandomAccessFile;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadProperties;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DownloadMgrInitialParams {
    private final InitCustomMaker mMaker;

    public DownloadMgrInitialParams() {
        this.mMaker = null;
    }

    public DownloadMgrInitialParams(InitCustomMaker initCustomMaker) {
        this.mMaker = initCustomMaker;
    }

    public int getMaxNetworkThreadCount() {
        if (this.mMaker == null) {
            return getDefaultMaxNetworkThreadCount();
        }
        Integer num = this.mMaker.mMaxNetworkThreadCount;
        if (num != null) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "initial FileDownloader manager with the customize maxNetworkThreadCount: %d", num);
            }
            return FileDownloadProperties.getValidNetworkThreadCount(num.intValue());
        }
        return getDefaultMaxNetworkThreadCount();
    }

    public FileDownloadDatabase createDatabase() {
        if (this.mMaker == null || this.mMaker.mDatabaseCustomMaker == null) {
            return createDefaultDatabase();
        }
        FileDownloadDatabase fileDownloadDatabaseCustomMake = this.mMaker.mDatabaseCustomMaker.customMake();
        if (fileDownloadDatabaseCustomMake != null) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "initial FileDownloader manager with the customize database: %s", fileDownloadDatabaseCustomMake);
            }
            return fileDownloadDatabaseCustomMake;
        }
        return createDefaultDatabase();
    }

    public FileDownloadHelper.OutputStreamCreator createOutputStreamCreator() {
        if (this.mMaker == null) {
            return createDefaultOutputStreamCreator();
        }
        FileDownloadHelper.OutputStreamCreator outputStreamCreator = this.mMaker.mOutputStreamCreator;
        if (outputStreamCreator != null) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "initial FileDownloader manager with the customize output stream: %s", outputStreamCreator);
            }
            return outputStreamCreator;
        }
        return createDefaultOutputStreamCreator();
    }

    public FileDownloadHelper.ConnectionCreator createConnectionCreator() {
        if (this.mMaker == null) {
            return createDefaultConnectionCreator();
        }
        FileDownloadHelper.ConnectionCreator connectionCreator = this.mMaker.mConnectionCreator;
        if (connectionCreator != null) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "initial FileDownloader manager with the customize connection creator: %s", connectionCreator);
            }
            return connectionCreator;
        }
        return createDefaultConnectionCreator();
    }

    public FileDownloadHelper.ConnectionCountAdapter createConnectionCountAdapter() {
        if (this.mMaker == null) {
            return createDefaultConnectionCountAdapter();
        }
        FileDownloadHelper.ConnectionCountAdapter connectionCountAdapter = this.mMaker.mConnectionCountAdapter;
        if (connectionCountAdapter != null) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "initial FileDownloader manager with the customize connection count adapter: %s", connectionCountAdapter);
            }
            return connectionCountAdapter;
        }
        return createDefaultConnectionCountAdapter();
    }

    public FileDownloadHelper.IdGenerator createIdGenerator() {
        if (this.mMaker == null) {
            return createDefaultIdGenerator();
        }
        FileDownloadHelper.IdGenerator idGenerator = this.mMaker.mIdGenerator;
        if (idGenerator != null) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "initial FileDownloader manager with the customize id generator: %s", idGenerator);
            }
            return idGenerator;
        }
        return createDefaultIdGenerator();
    }

    public ForegroundServiceConfig createForegroundServiceConfig() {
        if (this.mMaker == null) {
            return createDefaultForegroundServiceConfig();
        }
        ForegroundServiceConfig foregroundServiceConfig = this.mMaker.mForegroundServiceConfig;
        if (foregroundServiceConfig != null) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "initial FileDownloader manager with the customize foreground service config: %s", foregroundServiceConfig);
            }
            return foregroundServiceConfig;
        }
        return createDefaultForegroundServiceConfig();
    }

    private ForegroundServiceConfig createDefaultForegroundServiceConfig() {
        return new ForegroundServiceConfig.Builder().needRecreateChannelId(true).build();
    }

    private FileDownloadHelper.IdGenerator createDefaultIdGenerator() {
        return new DefaultIdGenerator();
    }

    private int getDefaultMaxNetworkThreadCount() {
        return FileDownloadProperties.getImpl().downloadMaxNetworkThreadCount;
    }

    private FileDownloadDatabase createDefaultDatabase() {
        return new RemitDatabase();
    }

    private FileDownloadHelper.OutputStreamCreator createDefaultOutputStreamCreator() {
        return new FileDownloadRandomAccessFile.Creator();
    }

    private FileDownloadHelper.ConnectionCreator createDefaultConnectionCreator() {
        return new FileDownloadUrlConnection.Creator();
    }

    private FileDownloadHelper.ConnectionCountAdapter createDefaultConnectionCountAdapter() {
        return new DefaultConnectionCountAdapter();
    }

    public static class InitCustomMaker {
        FileDownloadHelper.ConnectionCountAdapter mConnectionCountAdapter;
        FileDownloadHelper.ConnectionCreator mConnectionCreator;
        FileDownloadHelper.DatabaseCustomMaker mDatabaseCustomMaker;
        ForegroundServiceConfig mForegroundServiceConfig;
        FileDownloadHelper.IdGenerator mIdGenerator;
        Integer mMaxNetworkThreadCount;
        FileDownloadHelper.OutputStreamCreator mOutputStreamCreator;

        public void commit() {
        }

        public InitCustomMaker idGenerator(FileDownloadHelper.IdGenerator idGenerator) {
            this.mIdGenerator = idGenerator;
            return this;
        }

        public InitCustomMaker connectionCountAdapter(FileDownloadHelper.ConnectionCountAdapter connectionCountAdapter) {
            this.mConnectionCountAdapter = connectionCountAdapter;
            return this;
        }

        public InitCustomMaker database(FileDownloadHelper.DatabaseCustomMaker databaseCustomMaker) {
            this.mDatabaseCustomMaker = databaseCustomMaker;
            return this;
        }

        public InitCustomMaker maxNetworkThreadCount(int i) {
            if (i > 0) {
                this.mMaxNetworkThreadCount = Integer.valueOf(i);
            }
            return this;
        }

        public InitCustomMaker outputStreamCreator(FileDownloadHelper.OutputStreamCreator outputStreamCreator) {
            this.mOutputStreamCreator = outputStreamCreator;
            if (this.mOutputStreamCreator == null || this.mOutputStreamCreator.supportSeek() || FileDownloadProperties.getImpl().fileNonPreAllocation) {
                return this;
            }
            throw new IllegalArgumentException("Since the provided FileDownloadOutputStream does not support the seek function, if FileDownloader pre-allocates file size at the beginning of the download, it will can not be resumed from the breakpoint. If you need to ensure that the resumption is available, please add and set the value of 'file.non-pre-allocation' field to 'true' in the 'filedownloader.properties' file which is in your application assets folder manually for resolving this problem.");
        }

        public InitCustomMaker connectionCreator(FileDownloadHelper.ConnectionCreator connectionCreator) {
            this.mConnectionCreator = connectionCreator;
            return this;
        }

        public InitCustomMaker foregroundServiceConfig(ForegroundServiceConfig foregroundServiceConfig) {
            this.mForegroundServiceConfig = foregroundServiceConfig;
            return this;
        }

        public String toString() {
            return FileDownloadUtils.formatString("component: database[%s], maxNetworkCount[%s], outputStream[%s], connection[%s], connectionCountAdapter[%s]", this.mDatabaseCustomMaker, this.mMaxNetworkThreadCount, this.mOutputStreamCreator, this.mConnectionCreator, this.mConnectionCountAdapter);
        }
    }
}
