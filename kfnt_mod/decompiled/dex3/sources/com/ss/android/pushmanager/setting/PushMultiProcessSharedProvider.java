package com.ss.android.pushmanager.setting;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.bytedance.common.push.utility.a;
import com.bytedance.common.push.utility.i;
import com.ss.android.message.a.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PushMultiProcessSharedProvider extends ContentProvider {
    public static final String ALL_TYPE = "all";
    public static final String BOOLEAN_TYPE = "boolean";
    public static final String FLOAT_TYPE = "float";
    public static final String INT_TYPE = "integer";
    private static final String KEY = "key";
    private static final String KEY_APP_FOREGROUND = "current_app_foreground";
    private static final String KEY_APP_STATE = "current_app_state";
    private static final String KEY_COLUMN = "key_column";
    public static final String LONG_TYPE = "long";
    private static final int MATCH_DATA = 65536;
    public static final String SP_CONFIG_NAME = "push_multi_process_config";
    public static final String STRING_TYPE = "string";
    private static final String TAG = "PushMultiProcessSharedP";
    private static final String TYPE = "type";
    private static final String TYPE_COLUMN = "type_column";
    private static final String VALUE_COLUMN = "value_column";
    public static Uri sBaseUri;

    @SuppressLint({"CI_StaticFieldLeak"})
    private static MultiProcessShared sInstance;
    private static UriMatcher sMatcher;
    public static String sShareAuthority;
    private Map<String, Object> mContentValues = new ConcurrentHashMap();
    private volatile boolean mHasCreate = false;
    private final Object mLock = new Object();
    private SharedPreferences mSharedPreferences;

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyContentChanged(Uri uri) {
        getContext().getContentResolver().notifyChange(uri, null);
    }

    private synchronized SharedPreferences getMultiProcessSharedPreferences() {
        if (this.mSharedPreferences == null) {
            this.mSharedPreferences = getContext().getApplicationContext().getSharedPreferences(SP_CONFIG_NAME, Build.VERSION.SDK_INT >= 11 ? 4 : 0);
            return this.mSharedPreferences;
        }
        return this.mSharedPreferences;
    }

    private static boolean isInitWithFailure() {
        return TextUtils.isEmpty(sShareAuthority) || sMatcher == null;
    }

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        if (providerInfo != null) {
            sShareAuthority = providerInfo.authority;
        }
        super.attachInfo(context, providerInfo);
    }

    private static void init(Context context) throws IllegalStateException {
        if (TextUtils.isEmpty(sShareAuthority)) {
            sShareAuthority = getProviderAuthority(context, PushMultiProcessSharedProvider.class.getName());
        }
        if (TextUtils.isEmpty(sShareAuthority)) {
            throw new IllegalStateException("Must Set MultiProcessSharedProvider Authority");
        }
        if (a.b()) {
            a.b("MultiProcessSharedProvider", sShareAuthority);
        }
        sMatcher = new UriMatcher(-1);
        sMatcher.addURI(sShareAuthority, "*/*", 65536);
        sBaseUri = Uri.parse("content://" + sShareAuthority);
    }

    public static String getProviderAuthority(Context context, String str) {
        if (context == null || i.a(str)) {
            return null;
        }
        try {
            for (ProviderInfo providerInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 8).providers) {
                if (str.equals(providerInfo.name)) {
                    return providerInfo.authority;
                }
            }
        } catch (Exception unused) {
        }
        return context.getPackageName() + ".push.SHARE_PROVIDER_AUTHORITY";
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        if (a.b() && !b.c(getContext())) {
            throw new IllegalAccessError("should be create in main process");
        }
        com.ss.android.message.a.a((Application) getContext().getApplicationContext());
        if (sMatcher != null) {
            return true;
        }
        try {
            if (a.b()) {
                a.b("MultiProcessSharedProvider", "init form onCreate");
            }
            init(getContext());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void delayOnCreate() {
        if (this.mHasCreate) {
            return;
        }
        synchronized (this.mLock) {
            if (!this.mHasCreate) {
                loadValues();
                this.mHasCreate = true;
            }
        }
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        delayOnCreate();
        return "vnd.android.cursor.item/vnd." + sShareAuthority + ".item";
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        delayOnCreate();
        if (isInitWithFailure() || sMatcher.match(uri) == 65536) {
            return 0;
        }
        throw new IllegalArgumentException("Unsupported uri " + uri);
    }

    @Override // android.content.ContentProvider
    @SuppressLint({"NewApi"})
    public Uri insert(Uri uri, ContentValues contentValues) {
        ArrayList arrayList;
        SharedPreferences.Editor editorEdit;
        Runnable runnableCreateNotifyRunnable;
        delayOnCreate();
        if (isInitWithFailure()) {
            return null;
        }
        if (sMatcher.match(uri) == 65536) {
            try {
                arrayList = new ArrayList();
                editorEdit = null;
                for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
                    Object value = entry.getValue();
                    String key = entry.getKey();
                    if (a.b()) {
                        a.b("PushService", "MultiProcessShareProvider insert key = " + key + " value = " + value.toString());
                    }
                    boolean z = true;
                    if (value == null) {
                        this.mContentValues.remove(key);
                    } else {
                        Object obj = this.mContentValues.get(key);
                        if (obj == null || !obj.equals(value)) {
                            this.mContentValues.put(key, value);
                        } else {
                            z = false;
                        }
                    }
                    if (z) {
                        if (a.b()) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("MultiProcessShareProvider reallly insert key = ");
                            sb.append(key);
                            sb.append(" value = ");
                            sb.append(value);
                            a.b("PushService", sb.toString() != null ? value.toString() : "null");
                        }
                        if (editorEdit == null) {
                            editorEdit = getMultiProcessSharedPreferences().edit();
                        }
                        if (value == null) {
                            editorEdit.remove(key);
                            runnableCreateNotifyRunnable = null;
                        } else if (value instanceof String) {
                            editorEdit.putString(key, (String) value);
                            runnableCreateNotifyRunnable = createNotifyRunnable(key, STRING_TYPE);
                        } else if (value instanceof Boolean) {
                            editorEdit.putBoolean(key, ((Boolean) value).booleanValue());
                            runnableCreateNotifyRunnable = createNotifyRunnable(key, BOOLEAN_TYPE);
                        } else if (value instanceof Long) {
                            editorEdit.putLong(key, ((Long) value).longValue());
                            runnableCreateNotifyRunnable = createNotifyRunnable(key, LONG_TYPE);
                        } else if (value instanceof Integer) {
                            editorEdit.putInt(key, ((Integer) value).intValue());
                            runnableCreateNotifyRunnable = createNotifyRunnable(key, INT_TYPE);
                        } else if (value instanceof Float) {
                            editorEdit.putFloat(key, ((Float) value).floatValue());
                            runnableCreateNotifyRunnable = createNotifyRunnable(key, FLOAT_TYPE);
                        } else {
                            throw new IllegalArgumentException("Unsupported type " + uri);
                        }
                        if (runnableCreateNotifyRunnable != null) {
                            arrayList.add(runnableCreateNotifyRunnable);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (editorEdit == null) {
                return null;
            }
            if (Build.VERSION.SDK_INT > 8) {
                editorEdit.apply();
            } else {
                editorEdit.commit();
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
            return null;
        }
        throw new IllegalArgumentException("Unsupported uri " + uri);
    }

    private Runnable createNotifyRunnable(final String str, final String str2) {
        return new Runnable() { // from class: com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider.1
            @Override // java.lang.Runnable
            public void run() {
                PushMultiProcessSharedProvider.this.notifyContentChanged(PushMultiProcessSharedProvider.getContentUri(PushMultiProcessSharedProvider.this.getContext(), str, str2));
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v4 */
    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        MatrixCursor matrixCursor;
        Object objValueOf;
        delayOnCreate();
        Cursor cursor = null;
        MatrixCursor.RowBuilder rowBuilderNewRow = 0;
        if (isInitWithFailure()) {
            return null;
        }
        if (sMatcher.match(uri) == 65536) {
            try {
                if (ALL_TYPE.equals(uri.getPathSegments().get(1))) {
                    Map<String, ?> all = getMultiProcessSharedPreferences().getAll();
                    matrixCursor = new MatrixCursor(new String[]{KEY_COLUMN, VALUE_COLUMN, TYPE_COLUMN});
                    try {
                        Iterator<Map.Entry<String, ?>> it = all.entrySet().iterator();
                        while (true) {
                            boolean zHasNext = it.hasNext();
                            cursor = zHasNext;
                            if (zHasNext != 0) {
                                Map.Entry<String, ?> next = it.next();
                                String key = next.getKey();
                                Object value = next.getValue();
                                MatrixCursor.RowBuilder rowBuilderNewRow2 = matrixCursor.newRow();
                                String str3 = STRING_TYPE;
                                if (value instanceof String) {
                                    str3 = STRING_TYPE;
                                } else if (value instanceof Boolean) {
                                    str3 = BOOLEAN_TYPE;
                                    value = Integer.valueOf(((Boolean) value).booleanValue() ? 1 : 0);
                                } else if (value instanceof Integer) {
                                    str3 = INT_TYPE;
                                } else if (value instanceof Long) {
                                    str3 = LONG_TYPE;
                                } else if (value instanceof Float) {
                                    str3 = FLOAT_TYPE;
                                }
                                rowBuilderNewRow2.add(key);
                                rowBuilderNewRow2.add(value);
                                rowBuilderNewRow2.add(str3);
                            }
                        }
                    } catch (Exception unused) {
                        return matrixCursor;
                    }
                } else {
                    String str4 = uri.getPathSegments().get(0);
                    boolean zA = i.a(str4, KEY_APP_STATE);
                    boolean zA2 = i.a(str4, KEY_APP_FOREGROUND);
                    if (!this.mContentValues.containsKey(str4) && !zA && !zA2) {
                        return null;
                    }
                    MatrixCursor matrixCursor2 = new MatrixCursor(new String[]{str4});
                    try {
                        if (zA) {
                            objValueOf = Boolean.valueOf(com.bytedance.common.push.a.a.a().c());
                        } else if (zA2) {
                            objValueOf = Boolean.valueOf(!com.bytedance.common.push.a.a.a().b());
                        } else {
                            objValueOf = this.mContentValues.get(str4);
                        }
                        rowBuilderNewRow = matrixCursor2.newRow();
                        if (objValueOf instanceof Boolean) {
                            objValueOf = Integer.valueOf(((Boolean) objValueOf).booleanValue() ? 1 : 0);
                        }
                        if (a.b()) {
                            a.b("PushService", "MultiProcessShareProvider reallly get key = " + str4 + " value = " + objValueOf.toString());
                        }
                        rowBuilderNewRow.add(objValueOf);
                    } catch (Exception unused2) {
                    }
                    matrixCursor = matrixCursor2;
                    cursor = rowBuilderNewRow;
                }
                return matrixCursor;
            } catch (Exception unused3) {
                return cursor;
            }
        }
        throw new IllegalArgumentException("Unsupported uri " + uri);
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }

    private void loadValues() {
        SharedPreferences multiProcessSharedPreferences = getMultiProcessSharedPreferences();
        if (multiProcessSharedPreferences == null) {
            return;
        }
        for (Map.Entry<String, ?> entry : multiProcessSharedPreferences.getAll().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (key != null && value != null) {
                this.mContentValues.put(key, value);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getStringValue(Cursor cursor, String str) {
        if (cursor == null) {
            return str;
        }
        try {
            if (cursor.moveToFirst()) {
                str = cursor.getString(0);
            }
        } catch (Throwable th) {
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception unused) {
                }
            }
            throw th;
        }
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception unused2) {
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean getBooleanValue(Cursor cursor, boolean z) {
        if (cursor == null) {
            return z;
        }
        try {
            if (cursor.moveToFirst()) {
                z = cursor.getInt(0) > 0;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception unused) {
                }
            }
            throw th;
        }
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception unused2) {
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getIntValue(Cursor cursor, int i) {
        if (cursor == null) {
            return i;
        }
        try {
            if (cursor.moveToFirst()) {
                i = cursor.getInt(0);
            }
        } catch (Throwable th) {
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception unused) {
                }
            }
            throw th;
        }
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception unused2) {
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long getLongValue(Cursor cursor, long j) {
        if (cursor == null) {
            return j;
        }
        try {
            if (cursor.moveToFirst()) {
                j = cursor.getLong(0);
            }
        } catch (Throwable th) {
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception unused) {
                }
            }
            throw th;
        }
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception unused2) {
            }
        }
        return j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static float getFloatValue(Cursor cursor, float f) {
        if (cursor == null) {
            return f;
        }
        try {
            if (cursor.moveToFirst()) {
                f = cursor.getFloat(0);
            }
        } catch (Throwable th) {
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception unused) {
                }
            }
            throw th;
        }
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception unused2) {
            }
        }
        return f;
    }

    public static Editor edit(Context context) {
        return new Editor(context);
    }

    public static synchronized MultiProcessShared getMultiprocessShared(Context context) {
        if (sInstance == null) {
            sInstance = new MultiProcessShared(context);
        }
        return sInstance;
    }

    public static class Editor {
        Context mContext;
        private ContentValues mValues;

        private Editor(Context context) {
            this.mValues = new ContentValues();
            this.mContext = context.getApplicationContext();
        }

        public synchronized void apply() {
            try {
                this.mContext.getContentResolver().insert(PushMultiProcessSharedProvider.getContentUri(this.mContext, "key", "type"), this.mValues);
            } catch (Throwable th) {
                throw th;
            }
        }

        public synchronized void commit() {
            apply();
        }

        public Editor putString(String str, String str2) {
            this.mValues.put(str, str2);
            return this;
        }

        public Editor putLong(String str, long j) {
            this.mValues.put(str, Long.valueOf(j));
            return this;
        }

        public Editor putBoolean(String str, boolean z) {
            this.mValues.put(str, Boolean.valueOf(z));
            return this;
        }

        public Editor putInt(String str, int i) {
            this.mValues.put(str, Integer.valueOf(i));
            return this;
        }

        public Editor putFloat(String str, float f) {
            this.mValues.put(str, Float.valueOf(f));
            return this;
        }

        public void remove(String str) {
            this.mValues.putNull(str);
        }

        public void clear() {
            try {
                this.mContext.getContentResolver().delete(PushMultiProcessSharedProvider.getContentUri(this.mContext, "key", "type"), null, null);
            } catch (Throwable unused) {
            }
        }
    }

    public static class MultiProcessShared {
        private Context mContext;
        private boolean mIsMainProcess;
        private SharedPreferences mSharedPreferences;

        private MultiProcessShared(Context context) {
            this.mIsMainProcess = false;
            this.mIsMainProcess = b.c(context);
            this.mContext = context.getApplicationContext();
            this.mSharedPreferences = this.mContext.getApplicationContext().getSharedPreferences(PushMultiProcessSharedProvider.SP_CONFIG_NAME, 4);
            if (a.b()) {
                a.b("PushService", "MultiProcessShared create");
            }
        }

        public Editor edit() {
            return new Editor(this.mContext);
        }

        public String getString(String str, String str2) {
            try {
                if (!this.mIsMainProcess) {
                    return PushMultiProcessSharedProvider.getStringValue(this.mContext.getContentResolver().query(PushMultiProcessSharedProvider.getContentUri(this.mContext, str, PushMultiProcessSharedProvider.STRING_TYPE), null, null, null, null), str2);
                }
                return this.mSharedPreferences.getString(str, str2);
            } catch (Throwable unused) {
                return str2;
            }
        }

        public long getLong(String str, long j) {
            try {
                if (!this.mIsMainProcess) {
                    return PushMultiProcessSharedProvider.getLongValue(this.mContext.getContentResolver().query(PushMultiProcessSharedProvider.getContentUri(this.mContext, str, PushMultiProcessSharedProvider.LONG_TYPE), null, null, null, null), j);
                }
                return this.mSharedPreferences.getLong(str, j);
            } catch (Throwable unused) {
                return j;
            }
        }

        public float getFloat(String str, float f) {
            try {
                if (!this.mIsMainProcess) {
                    return PushMultiProcessSharedProvider.getFloatValue(this.mContext.getContentResolver().query(PushMultiProcessSharedProvider.getContentUri(this.mContext, str, PushMultiProcessSharedProvider.FLOAT_TYPE), null, null, null, null), f);
                }
                return this.mSharedPreferences.getFloat(str, f);
            } catch (Throwable unused) {
                return f;
            }
        }

        public boolean getBoolean(String str, boolean z) {
            try {
                if (!this.mIsMainProcess) {
                    return PushMultiProcessSharedProvider.getBooleanValue(this.mContext.getContentResolver().query(PushMultiProcessSharedProvider.getContentUri(this.mContext, str, PushMultiProcessSharedProvider.BOOLEAN_TYPE), null, null, null, null), z);
                }
                return this.mSharedPreferences.getBoolean(str, z);
            } catch (Throwable unused) {
                return z;
            }
        }

        public int getInt(String str, int i) {
            try {
                if (!this.mIsMainProcess) {
                    return PushMultiProcessSharedProvider.getIntValue(this.mContext.getContentResolver().query(PushMultiProcessSharedProvider.getContentUri(this.mContext, str, PushMultiProcessSharedProvider.INT_TYPE), null, null, null, null), i);
                }
                return this.mSharedPreferences.getInt(str, i);
            } catch (Throwable unused) {
                return i;
            }
        }

        public boolean isApplicationActive() {
            if (this.mIsMainProcess) {
                return isApplicationActiveInMainProcess();
            }
            return getBoolean(PushMultiProcessSharedProvider.KEY_APP_STATE, false);
        }

        private boolean isApplicationActiveInMainProcess() {
            return com.bytedance.common.push.a.a.a().c();
        }

        public boolean isApplicationForeground() {
            if (this.mIsMainProcess) {
                return isApplicationForegroundInMainProcess();
            }
            return getBoolean(PushMultiProcessSharedProvider.KEY_APP_FOREGROUND, false);
        }

        private boolean isApplicationForegroundInMainProcess() {
            return !com.bytedance.common.push.a.a.a().b();
        }
    }

    public static final synchronized Uri getContentUri(Context context, String str, String str2) {
        if (sBaseUri == null) {
            try {
                if (a.b()) {
                    a.b("MultiProcessSharedProvider", "init form getContentUri");
                }
                init(context);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return sBaseUri.buildUpon().appendPath(str).appendPath(str2).build();
    }
}
