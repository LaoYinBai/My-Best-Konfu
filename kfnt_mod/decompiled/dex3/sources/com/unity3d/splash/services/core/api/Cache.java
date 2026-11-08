package com.unity3d.splash.services.core.api;

import android.annotation.TargetApi;
import android.media.MediaMetadataRetriever;
import android.util.Base64;
import android.util.SparseArray;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.unity3d.splash.services.core.cache.CacheDirectory;
import com.unity3d.splash.services.core.cache.CacheDirectoryType;
import com.unity3d.splash.services.core.cache.CacheError;
import com.unity3d.splash.services.core.cache.CacheThread;
import com.unity3d.splash.services.core.device.Device;
import com.unity3d.splash.services.core.log.DeviceLog;
import com.unity3d.splash.services.core.misc.Utilities;
import com.unity3d.splash.services.core.properties.ClientProperties;
import com.unity3d.splash.services.core.properties.SdkProperties;
import com.unity3d.splash.services.core.request.WebRequestError;
import com.unity3d.splash.services.core.webview.bridge.WebViewCallback;
import com.unity3d.splash.services.core.webview.bridge.WebViewExposed;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Cache {
    @WebViewExposed
    public static void deleteFile(String str, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (new File(fileIdToFilename(str)).delete()) {
            webViewCallback.invoke(new Object[0]);
        } else {
            webViewCallback.error(CacheError.FILE_IO_ERROR, new Object[0]);
        }
    }

    @WebViewExposed
    public static void download(String str, String str2, JSONArray jSONArray, Boolean bool, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (CacheThread.isActive()) {
            webViewCallback.error(CacheError.FILE_ALREADY_CACHING, new Object[0]);
            return;
        }
        if (!Device.isActiveNetworkConnected()) {
            webViewCallback.error(CacheError.NO_INTERNET, new Object[0]);
            return;
        }
        try {
            CacheThread.download(str, fileIdToFilename(str2), Request.getHeadersMap(jSONArray), bool.booleanValue());
            webViewCallback.invoke(new Object[0]);
        } catch (Exception e) {
            DeviceLog.exception("Error mapping headers for the request", e);
            webViewCallback.error(WebRequestError.MAPPING_HEADERS_FAILED, str, str2);
        }
    }

    private static String fileIdToFilename(String str) {
        return SdkProperties.getCacheDirectory() + "/" + SdkProperties.getCacheFilePrefix() + str;
    }

    @WebViewExposed
    public static void getCacheDirectoryExists(WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        File cacheDirectory = SdkProperties.getCacheDirectory();
        if (cacheDirectory == null) {
            webViewCallback.error(CacheError.CACHE_DIRECTORY_NULL, new Object[0]);
        } else {
            webViewCallback.invoke(Boolean.valueOf(cacheDirectory.exists()));
        }
    }

    @WebViewExposed
    public static void getCacheDirectoryType(WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        CacheDirectory cacheDirectoryObject = SdkProperties.getCacheDirectoryObject();
        if (cacheDirectoryObject == null || cacheDirectoryObject.getCacheDirectory(ClientProperties.getApplicationContext()) == null) {
            webViewCallback.error(CacheError.CACHE_DIRECTORY_NULL, new Object[0]);
            return;
        }
        if (!cacheDirectoryObject.getCacheDirectory(ClientProperties.getApplicationContext()).exists()) {
            webViewCallback.error(CacheError.CACHE_DIRECTORY_DOESNT_EXIST, new Object[0]);
            return;
        }
        CacheDirectoryType type = cacheDirectoryObject.getType();
        if (type == null) {
            webViewCallback.error(CacheError.CACHE_DIRECTORY_TYPE_NULL, new Object[0]);
        } else {
            webViewCallback.invoke(type.name());
        }
    }

    @WebViewExposed
    public static void getFileContent(String str, String str2, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Object objEncodeToString;
        String strFileIdToFilename = fileIdToFilename(str);
        File file = new File(strFileIdToFilename);
        if (!file.exists()) {
            webViewCallback.error(CacheError.FILE_NOT_FOUND, str, strFileIdToFilename);
            return;
        }
        try {
            byte[] fileBytes = Utilities.readFileBytes(file);
            if (str2 == null) {
                webViewCallback.error(CacheError.UNSUPPORTED_ENCODING, str, strFileIdToFilename, str2);
                return;
            }
            if (str2.equals("UTF-8")) {
                objEncodeToString = Charset.forName("UTF-8").decode(ByteBuffer.wrap(fileBytes)).toString();
            } else {
                if (!str2.equals("Base64")) {
                    webViewCallback.error(CacheError.UNSUPPORTED_ENCODING, str, strFileIdToFilename, str2);
                    return;
                }
                objEncodeToString = Base64.encodeToString(fileBytes, 2);
            }
            webViewCallback.invoke(objEncodeToString);
        } catch (IOException e) {
            webViewCallback.error(CacheError.FILE_IO_ERROR, str, strFileIdToFilename, e.getMessage() + ", " + e.getClass().getName());
        }
    }

    @WebViewExposed
    public static void getFileInfo(String str, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        try {
            webViewCallback.invoke(getFileJson(str));
        } catch (JSONException e) {
            DeviceLog.exception("Error creating JSON", e);
            webViewCallback.error(CacheError.JSON_ERROR, new Object[0]);
        }
    }

    private static JSONObject getFileJson(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(AdDownloadModel.JsonKey.ID, str);
        File file = new File(fileIdToFilename(str));
        if (file.exists()) {
            jSONObject.put("found", true);
            jSONObject.put("size", file.length());
            jSONObject.put("mtime", file.lastModified());
        } else {
            jSONObject.put("found", false);
        }
        return jSONObject;
    }

    @WebViewExposed
    public static void getFilePath(String str, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (new File(fileIdToFilename(str)).exists()) {
            webViewCallback.invoke(fileIdToFilename(str));
        } else {
            webViewCallback.error(CacheError.FILE_NOT_FOUND, new Object[0]);
        }
    }

    @WebViewExposed
    public static void getFiles(WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        File cacheDirectory = SdkProperties.getCacheDirectory();
        if (cacheDirectory == null) {
            return;
        }
        DeviceLog.debug("Unity Ads cache: checking app directory for Unity Ads cached files");
        File[] fileArrListFiles = cacheDirectory.listFiles(new FilenameFilter() { // from class: com.unity3d.splash.services.core.api.Cache.1
            @Override // java.io.FilenameFilter
            public final boolean accept(File file, String str) {
                return str.startsWith(SdkProperties.getCacheFilePrefix());
            }
        });
        if (fileArrListFiles == null || fileArrListFiles.length == 0) {
            webViewCallback.invoke(new JSONArray());
        }
        try {
            JSONArray jSONArray = new JSONArray();
            for (File file : fileArrListFiles) {
                String strSubstring = file.getName().substring(SdkProperties.getCacheFilePrefix().length());
                DeviceLog.debug("Unity Ads cache: found " + strSubstring + ", " + file.length() + " bytes");
                jSONArray.put(getFileJson(strSubstring));
            }
            webViewCallback.invoke(jSONArray);
        } catch (JSONException e) {
            DeviceLog.exception("Error creating JSON", e);
            webViewCallback.error(CacheError.JSON_ERROR, new Object[0]);
        }
    }

    @WebViewExposed
    public static void getFreeSpace(WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        webViewCallback.invoke(Long.valueOf(Device.getFreeSpace(SdkProperties.getCacheDirectory())));
    }

    @WebViewExposed
    public static void getHash(String str, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        webViewCallback.invoke(Utilities.Sha256(str));
    }

    @TargetApi(10)
    private static SparseArray getMetaData(String str, JSONArray jSONArray) throws JSONException, IOException, IllegalArgumentException {
        File file = new File(str);
        SparseArray sparseArray = new SparseArray();
        if (!file.exists()) {
            throw new IOException("File: " + file.getAbsolutePath() + " doesn't exist");
        }
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(file.getAbsolutePath());
        for (int i = 0; i < jSONArray.length(); i++) {
            int i2 = jSONArray.getInt(i);
            String strExtractMetadata = mediaMetadataRetriever.extractMetadata(i2);
            if (strExtractMetadata != null) {
                sparseArray.put(i2, strExtractMetadata);
            }
        }
        return sparseArray;
    }

    @WebViewExposed
    public static void getMetaData(String str, JSONArray jSONArray, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        try {
            SparseArray metaData = getMetaData(fileIdToFilename(str), jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            for (int i = 0; i < metaData.size(); i++) {
                JSONArray jSONArray3 = new JSONArray();
                jSONArray3.put(metaData.keyAt(i));
                jSONArray3.put(metaData.valueAt(i));
                jSONArray2.put(jSONArray3);
            }
            webViewCallback.invoke(jSONArray2);
        } catch (IOException e) {
            webViewCallback.error(CacheError.FILE_IO_ERROR, e.getMessage());
        } catch (RuntimeException e2) {
            webViewCallback.error(CacheError.INVALID_ARGUMENT, e2.getMessage());
        } catch (JSONException e3) {
            webViewCallback.error(CacheError.JSON_ERROR, e3.getMessage());
        }
    }

    @WebViewExposed
    public static void getProgressInterval(WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        webViewCallback.invoke(Integer.valueOf(CacheThread.getProgressInterval()));
    }

    @WebViewExposed
    public static void getTimeouts(WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        webViewCallback.invoke(Integer.valueOf(CacheThread.getConnectTimeout()), Integer.valueOf(CacheThread.getReadTimeout()));
    }

    @WebViewExposed
    public static void getTotalSpace(WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        webViewCallback.invoke(Long.valueOf(Device.getTotalSpace(SdkProperties.getCacheDirectory())));
    }

    @WebViewExposed
    public static void isCaching(WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        webViewCallback.invoke(Boolean.valueOf(CacheThread.isActive()));
    }

    @WebViewExposed
    public static void recreateCacheDirectory(WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (SdkProperties.getCacheDirectory().exists()) {
            webViewCallback.error(CacheError.CACHE_DIRECTORY_EXISTS, new Object[0]);
            return;
        }
        SdkProperties.setCacheDirectory(null);
        if (SdkProperties.getCacheDirectory() == null) {
            webViewCallback.error(CacheError.CACHE_DIRECTORY_NULL, new Object[0]);
        } else {
            webViewCallback.invoke(new Object[0]);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:64:? A[RETURN, SYNTHETIC] */
    @com.unity3d.splash.services.core.webview.bridge.WebViewExposed
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void setFileContent(java.lang.String r7, java.lang.String r8, java.lang.String r9, com.unity3d.splash.services.core.webview.bridge.WebViewCallback r10) throws java.lang.Throwable {
        /*
            java.lang.String r0 = fileIdToFilename(r7)
            r1 = 3
            r2 = 2
            r3 = 1
            r4 = 0
            java.lang.String r5 = "UTF-8"
            byte[] r5 = r9.getBytes(r5)     // Catch: java.io.UnsupportedEncodingException -> L9b
            if (r8 == 0) goto L39
            int r6 = r8.length()
            if (r6 <= 0) goto L39
            java.lang.String r6 = "Base64"
            boolean r6 = r8.equals(r6)
            if (r6 == 0) goto L23
            byte[] r5 = android.util.Base64.decode(r9, r2)
            goto L39
        L23:
            java.lang.String r9 = "UTF-8"
            boolean r9 = r8.equals(r9)
            if (r9 != 0) goto L39
            com.unity3d.splash.services.core.cache.CacheError r9 = com.unity3d.splash.services.core.cache.CacheError.UNSUPPORTED_ENCODING
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r1[r4] = r7
            r1[r3] = r0
            r1[r2] = r8
        L35:
            r10.error(r9, r1)
            return
        L39:
            r9 = 0
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L57 java.io.IOException -> L59 java.io.FileNotFoundException -> L6c
            r6.<init>(r0)     // Catch: java.lang.Throwable -> L57 java.io.IOException -> L59 java.io.FileNotFoundException -> L6c
            r6.write(r5)     // Catch: java.lang.Throwable -> L50 java.io.IOException -> L53 java.io.FileNotFoundException -> L55
            r6.flush()     // Catch: java.lang.Throwable -> L50 java.io.IOException -> L53 java.io.FileNotFoundException -> L55
            r6.close()     // Catch: java.lang.Exception -> L49
            goto L86
        L49:
            r7 = move-exception
            java.lang.String r8 = "Error closing FileOutputStream"
            com.unity3d.splash.services.core.log.DeviceLog.exception(r8, r7)
            goto L86
        L50:
            r7 = move-exception
            r9 = r6
            goto L8e
        L53:
            r9 = r6
            goto L59
        L55:
            r9 = r6
            goto L6c
        L57:
            r7 = move-exception
            goto L8e
        L59:
            com.unity3d.splash.services.core.cache.CacheError r5 = com.unity3d.splash.services.core.cache.CacheError.FILE_IO_ERROR     // Catch: java.lang.Throwable -> L57
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L57
            r1[r4] = r7     // Catch: java.lang.Throwable -> L57
            r1[r3] = r0     // Catch: java.lang.Throwable -> L57
            r1[r2] = r8     // Catch: java.lang.Throwable -> L57
            r10.error(r5, r1)     // Catch: java.lang.Throwable -> L57
            if (r9 == 0) goto L85
            r9.close()     // Catch: java.lang.Exception -> L7f
            goto L85
        L6c:
            com.unity3d.splash.services.core.cache.CacheError r5 = com.unity3d.splash.services.core.cache.CacheError.FILE_NOT_FOUND     // Catch: java.lang.Throwable -> L57
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L57
            r1[r4] = r7     // Catch: java.lang.Throwable -> L57
            r1[r3] = r0     // Catch: java.lang.Throwable -> L57
            r1[r2] = r8     // Catch: java.lang.Throwable -> L57
            r10.error(r5, r1)     // Catch: java.lang.Throwable -> L57
            if (r9 == 0) goto L85
            r9.close()     // Catch: java.lang.Exception -> L7f
            goto L85
        L7f:
            r7 = move-exception
            java.lang.String r8 = "Error closing FileOutputStream"
            com.unity3d.splash.services.core.log.DeviceLog.exception(r8, r7)
        L85:
            r3 = 0
        L86:
            if (r3 == 0) goto L8d
            java.lang.Object[] r7 = new java.lang.Object[r4]
            r10.invoke(r7)
        L8d:
            return
        L8e:
            if (r9 == 0) goto L9a
            r9.close()     // Catch: java.lang.Exception -> L94
            goto L9a
        L94:
            r8 = move-exception
            java.lang.String r9 = "Error closing FileOutputStream"
            com.unity3d.splash.services.core.log.DeviceLog.exception(r9, r8)
        L9a:
            throw r7
        L9b:
            com.unity3d.splash.services.core.cache.CacheError r9 = com.unity3d.splash.services.core.cache.CacheError.UNSUPPORTED_ENCODING
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r1[r4] = r7
            r1[r3] = r0
            r1[r2] = r8
            goto L35
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.splash.services.core.api.Cache.setFileContent(java.lang.String, java.lang.String, java.lang.String, com.unity3d.splash.services.core.webview.bridge.WebViewCallback):void");
    }

    @WebViewExposed
    public static void setProgressInterval(Integer num, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        CacheThread.setProgressInterval(num.intValue());
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void setTimeouts(Integer num, Integer num2, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        CacheThread.setConnectTimeout(num.intValue());
        CacheThread.setReadTimeout(num2.intValue());
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void stop(WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (!CacheThread.isActive()) {
            webViewCallback.error(CacheError.NOT_CACHING, new Object[0]);
        } else {
            CacheThread.cancel();
            webViewCallback.invoke(new Object[0]);
        }
    }
}
