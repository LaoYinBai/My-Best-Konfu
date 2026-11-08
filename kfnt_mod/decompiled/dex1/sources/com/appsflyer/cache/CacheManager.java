package com.appsflyer.cache;

import android.content.Context;
import android.util.Log;
import com.appsflyer.AppsFlyerLib;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CacheManager {
    public static final String AF_CACHE_DIR = "AFRequestCache";
    public static final int CACHE_MAX_SIZE = 40;

    /* renamed from: ˋ, reason: contains not printable characters */
    private static CacheManager f169 = new CacheManager();

    public static CacheManager getInstance() {
        return f169;
    }

    private CacheManager() {
    }

    public List<RequestCacheData> getCachedRequests(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            File file = new File(context.getFilesDir(), AF_CACHE_DIR);
            if (!file.exists()) {
                file.mkdir();
            } else {
                for (File file2 : file.listFiles()) {
                    StringBuilder sb = new StringBuilder("Found cached request");
                    sb.append(file2.getName());
                    Log.i(AppsFlyerLib.LOG_TAG, sb.toString());
                    arrayList.add(m146(file2));
                }
            }
        } catch (Exception unused) {
            Log.i(AppsFlyerLib.LOG_TAG, "Could not cache request");
        }
        return arrayList;
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    private static RequestCacheData m146(File file) throws Throwable {
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
            try {
                char[] cArr = new char[(int) file.length()];
                fileReader.read(cArr);
                RequestCacheData requestCacheData = new RequestCacheData(cArr);
                requestCacheData.setCacheKey(file.getName());
                try {
                    fileReader.close();
                } catch (IOException unused) {
                }
                return requestCacheData;
            } catch (Exception unused2) {
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException unused3) {
                    }
                }
                return null;
            } catch (Throwable th) {
                th = th;
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException unused4) {
                    }
                }
                throw th;
            }
        } catch (Exception unused5) {
            fileReader = null;
        } catch (Throwable th2) {
            th = th2;
            fileReader = null;
        }
    }

    public void init(Context context) {
        try {
            if (new File(context.getFilesDir(), AF_CACHE_DIR).exists()) {
                return;
            }
            new File(context.getFilesDir(), AF_CACHE_DIR).mkdir();
        } catch (Exception unused) {
            Log.i(AppsFlyerLib.LOG_TAG, "Could not create cache directory");
        }
    }

    public void cacheRequest(RequestCacheData requestCacheData, Context context) throws Throwable {
        OutputStreamWriter outputStreamWriter = null;
        try {
            try {
                File file = new File(context.getFilesDir(), AF_CACHE_DIR);
                if (!file.exists()) {
                    file.mkdir();
                    return;
                }
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null && fileArrListFiles.length > 40) {
                    Log.i(AppsFlyerLib.LOG_TAG, "reached cache limit, not caching request");
                    return;
                }
                Log.i(AppsFlyerLib.LOG_TAG, "caching request...");
                File file2 = new File(new File(context.getFilesDir(), AF_CACHE_DIR), Long.toString(System.currentTimeMillis()));
                file2.createNewFile();
                OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(new FileOutputStream(file2.getPath(), true));
                try {
                    outputStreamWriter2.write("version=");
                    outputStreamWriter2.write(requestCacheData.getVersion());
                    outputStreamWriter2.write(10);
                    outputStreamWriter2.write("url=");
                    outputStreamWriter2.write(requestCacheData.getRequestURL());
                    outputStreamWriter2.write(10);
                    outputStreamWriter2.write("data=");
                    outputStreamWriter2.write(requestCacheData.getPostData());
                    outputStreamWriter2.write(10);
                    outputStreamWriter2.flush();
                    try {
                        outputStreamWriter2.close();
                    } catch (IOException unused) {
                    }
                } catch (Exception unused2) {
                    outputStreamWriter = outputStreamWriter2;
                    Log.i(AppsFlyerLib.LOG_TAG, "Could not cache request");
                    if (outputStreamWriter != null) {
                        try {
                            outputStreamWriter.close();
                        } catch (IOException unused3) {
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    outputStreamWriter = outputStreamWriter2;
                    if (outputStreamWriter != null) {
                        try {
                            outputStreamWriter.close();
                        } catch (IOException unused4) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception unused5) {
        }
    }

    public void deleteRequest(String str, Context context) {
        File file = new File(new File(context.getFilesDir(), AF_CACHE_DIR), str);
        StringBuilder sb = new StringBuilder("Deleting ");
        sb.append(str);
        sb.append(" from cache");
        Log.i(AppsFlyerLib.LOG_TAG, sb.toString());
        if (file.exists()) {
            try {
                file.delete();
            } catch (Exception e) {
                StringBuilder sb2 = new StringBuilder("Could not delete ");
                sb2.append(str);
                sb2.append(" from cache");
                Log.i(AppsFlyerLib.LOG_TAG, sb2.toString(), e);
            }
        }
    }

    public void clearCache(Context context) {
        try {
            File file = new File(context.getFilesDir(), AF_CACHE_DIR);
            if (!file.exists()) {
                file.mkdir();
                return;
            }
            for (File file2 : file.listFiles()) {
                StringBuilder sb = new StringBuilder("Found cached request");
                sb.append(file2.getName());
                Log.i(AppsFlyerLib.LOG_TAG, sb.toString());
                deleteRequest(m146(file2).getCacheKey(), context);
            }
        } catch (Exception unused) {
            Log.i(AppsFlyerLib.LOG_TAG, "Could not cache request");
        }
    }
}
