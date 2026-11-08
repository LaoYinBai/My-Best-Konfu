package com.mbridge.msdk.foundation.download.core;

import android.content.Context;
import android.database.Cursor;
import com.mbridge.msdk.foundation.download.MBDownloadConfig;
import com.mbridge.msdk.foundation.download.database.DatabaseHelper;
import com.mbridge.msdk.foundation.download.database.DownloadModel;
import com.mbridge.msdk.foundation.download.database.IDatabaseHelper;
import com.mbridge.msdk.foundation.download.utils.ILogger;
import com.mbridge.msdk.foundation.download.utils.Objects;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.thrid.okhttp.Dispatcher;
import com.mbridge.msdk.thrid.okhttp.OkHttpClient;
import com.mbridge.msdk.thrid.okhttp.Protocol;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class GlobalComponent {
    private static volatile GlobalComponent INSTANCE;
    private Context context;
    private IDatabaseHelper databaseHelper;
    private ILogger logger;
    private OkHttpClient okHttpClient;
    private String databaseTableName = DownloadModel.TABLE_NAME;
    private int byteBufferSize = 4096;

    private GlobalComponent() {
    }

    public static GlobalComponent getInstance() {
        if (Objects.isNull(INSTANCE)) {
            synchronized (GlobalComponent.class) {
                if (Objects.isNull(INSTANCE)) {
                    INSTANCE = new GlobalComponent();
                }
            }
        }
        return INSTANCE;
    }

    public final void initialize(Context context, MBDownloadConfig mBDownloadConfig) {
        this.context = context.getApplicationContext();
        this.logger = mBDownloadConfig.getLogger();
        if (mBDownloadConfig != null && mBDownloadConfig.getDatabaseHandler() != null && mBDownloadConfig.getDatabaseHelper() != null) {
            this.databaseHelper = new DatabaseHelper(context, mBDownloadConfig.getDatabaseHandler(), mBDownloadConfig.getDatabaseHelper());
        } else {
            this.databaseHelper = new IDatabaseHelper() { // from class: com.mbridge.msdk.foundation.download.core.GlobalComponent.1
                @Override // com.mbridge.msdk.foundation.download.database.IDatabaseHelper
                public void clear() {
                }

                @Override // com.mbridge.msdk.foundation.download.database.IDatabaseHelper
                public void find(String str, String str2, IDatabaseHelper.IDatabaseListener iDatabaseListener) {
                }

                @Override // com.mbridge.msdk.foundation.download.database.IDatabaseHelper
                public List<DownloadModel> findAll() {
                    return null;
                }

                @Override // com.mbridge.msdk.foundation.download.database.IDatabaseHelper
                public void findByDownloadUrl(String str, IDatabaseHelper.IDatabaseListener iDatabaseListener) {
                }

                @Override // com.mbridge.msdk.foundation.download.database.IDatabaseHelper
                public List<DownloadModel> getUnwantedModels(long j) {
                    return null;
                }

                @Override // com.mbridge.msdk.foundation.download.database.IDatabaseHelper
                public void insert(DownloadModel downloadModel) {
                }

                @Override // com.mbridge.msdk.foundation.download.database.IDatabaseHelper
                public Cursor rawQuery(String str, String[] strArr) {
                    return null;
                }

                @Override // com.mbridge.msdk.foundation.download.database.IDatabaseHelper
                public void remove(String str) {
                }

                @Override // com.mbridge.msdk.foundation.download.database.IDatabaseHelper
                public void remove(String str, String str2) {
                }

                @Override // com.mbridge.msdk.foundation.download.database.IDatabaseHelper
                public void update(DownloadModel downloadModel, String str) {
                }

                @Override // com.mbridge.msdk.foundation.download.database.IDatabaseHelper
                public void updateProgress(String str, String str2, DownloadModel downloadModel) {
                }

                @Override // com.mbridge.msdk.foundation.download.database.IDatabaseHelper
                public void updateUnzipResource(String str, String str2, long j) {
                }
            };
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(Protocol.HTTP_1_1);
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(mBDownloadConfig.getMaxRequests());
        dispatcher.setMaxRequestsPerHost(mBDownloadConfig.getMaxRequestsPerHost());
        this.okHttpClient = new OkHttpClient.Builder().connectTimeout(mBDownloadConfig.getDefaultConnectTimeout(), TimeUnit.MILLISECONDS).readTimeout(mBDownloadConfig.getDefaultConnectTimeout(), TimeUnit.MILLISECONDS).writeTimeout(mBDownloadConfig.getDefaultWriteTimeout(), TimeUnit.MILLISECONDS).dispatcher(dispatcher).pingInterval(mBDownloadConfig.getDefaultPingInterval(), TimeUnit.MILLISECONDS).retryOnConnectionFailure(true).protocols(arrayList).build();
    }

    public final ILogger getLogger() {
        if (this.logger == null) {
            this.logger = new ILogger() { // from class: com.mbridge.msdk.foundation.download.core.GlobalComponent.2
                @Override // com.mbridge.msdk.foundation.download.utils.ILogger
                public void log(String str, String str2) {
                    s.a(str, str2);
                }

                @Override // com.mbridge.msdk.foundation.download.utils.ILogger
                public void log(String str, Exception exc) {
                    s.a(str, exc.getMessage());
                }
            };
        }
        return this.logger;
    }

    public final IDatabaseHelper getDatabaseHelper() {
        return this.databaseHelper;
    }

    public final int getByteBufferSize() {
        return this.byteBufferSize;
    }

    public final Context getContext() {
        return this.context;
    }

    public final OkHttpClient getOkHttpClient() {
        return this.okHttpClient;
    }

    public final String getDatabaseTableName() {
        return this.databaseTableName;
    }
}
