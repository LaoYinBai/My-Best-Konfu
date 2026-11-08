package com.ss.union.game.sdk.core.glide.load.data;

import android.text.TextUtils;
import android.util.Log;
import com.ss.union.game.sdk.core.glide.Priority;
import com.ss.union.game.sdk.core.glide.load.DataSource;
import com.ss.union.game.sdk.core.glide.load.HttpException;
import com.ss.union.game.sdk.core.glide.load.data.DataFetcher;
import com.ss.union.game.sdk.core.glide.load.model.GlideUrl;
import com.ss.union.game.sdk.core.glide.util.ContentLengthInputStream;
import com.ss.union.game.sdk.core.glide.util.LogTime;
import com.umeng.message.util.HttpRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class HttpUrlFetcher implements DataFetcher<InputStream> {
    static final b a = new a();
    private static final String b = "HttpUrlFetcher";
    private static final int c = 5;
    private static final int d = -1;
    private final GlideUrl e;
    private final int f;
    private final b g;
    private HttpURLConnection h;
    private InputStream i;
    private volatile boolean j;

    interface b {
        HttpURLConnection a(URL url) throws IOException;
    }

    public HttpUrlFetcher(GlideUrl glideUrl, int i) {
        this(glideUrl, i, a);
    }

    HttpUrlFetcher(GlideUrl glideUrl, int i, b bVar) {
        this.e = glideUrl;
        this.f = i;
        this.g = bVar;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
    public void loadData(Priority priority, DataFetcher.DataCallback<? super InputStream> dataCallback) {
        String str;
        StringBuilder sb;
        long logTime = LogTime.getLogTime();
        try {
            try {
                dataCallback.onDataReady(a(this.e.toURL(), 0, null, this.e.getHeaders()));
            } catch (HttpException e) {
                e.printStackTrace();
                if (!Log.isLoggable(b, 2)) {
                    return;
                }
                str = b;
                sb = new StringBuilder();
            } catch (IOException e2) {
                if (Log.isLoggable(b, 3)) {
                    Log.d(b, "Failed to load data for url", e2);
                }
                dataCallback.onLoadFailed(e2);
                if (!Log.isLoggable(b, 2)) {
                    return;
                }
                str = b;
                sb = new StringBuilder();
            }
            if (Log.isLoggable(b, 2)) {
                str = b;
                sb = new StringBuilder();
                sb.append("Finished http url fetcher fetch in ");
                sb.append(LogTime.getElapsedMillis(logTime));
                Log.v(str, sb.toString());
            }
        } catch (Throwable th) {
            if (Log.isLoggable(b, 2)) {
                Log.v(b, "Finished http url fetcher fetch in " + LogTime.getElapsedMillis(logTime));
            }
            throw th;
        }
    }

    private InputStream a(URL url, int i, URL url2, Map<String, String> map) throws HttpException, IOException {
        if (i >= 5) {
            throw new HttpException("Too many (> 5) redirects!");
        }
        if (url2 != null) {
            try {
                if (url.toURI().equals(url2.toURI())) {
                    throw new HttpException("In re-direct loop");
                }
            } catch (URISyntaxException unused) {
            }
        }
        this.h = this.g.a(url);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            this.h.addRequestProperty(entry.getKey(), entry.getValue());
        }
        this.h.setConnectTimeout(this.f);
        this.h.setReadTimeout(this.f);
        this.h.setUseCaches(false);
        this.h.setDoInput(true);
        this.h.setInstanceFollowRedirects(false);
        this.h.connect();
        this.i = this.h.getInputStream();
        if (this.j) {
            return null;
        }
        int responseCode = this.h.getResponseCode();
        if (a(responseCode)) {
            return a(this.h);
        }
        if (!b(responseCode)) {
            if (responseCode == -1) {
                throw new HttpException(responseCode);
            }
            throw new HttpException(this.h.getResponseMessage(), responseCode);
        }
        String headerField = this.h.getHeaderField(HttpRequest.HEADER_LOCATION);
        if (TextUtils.isEmpty(headerField)) {
            throw new HttpException("Received empty or null redirect url");
        }
        URL url3 = new URL(url, headerField);
        cleanup();
        return a(url3, i + 1, url, map);
    }

    private static boolean a(int i) {
        return i / 100 == 2;
    }

    private static boolean b(int i) {
        return i / 100 == 3;
    }

    private InputStream a(HttpURLConnection httpURLConnection) throws IOException {
        if (TextUtils.isEmpty(httpURLConnection.getContentEncoding())) {
            this.i = ContentLengthInputStream.obtain(httpURLConnection.getInputStream(), httpURLConnection.getContentLength());
        } else {
            if (Log.isLoggable(b, 3)) {
                Log.d(b, "Got non empty content encoding: " + httpURLConnection.getContentEncoding());
            }
            this.i = httpURLConnection.getInputStream();
        }
        return this.i;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
    public void cleanup() throws IOException {
        if (this.i != null) {
            try {
                this.i.close();
            } catch (IOException unused) {
            }
        }
        if (this.h != null) {
            this.h.disconnect();
        }
        this.h = null;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
    public void cancel() {
        this.j = true;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
    public Class<InputStream> getDataClass() {
        return InputStream.class;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataFetcher
    public DataSource getDataSource() {
        return DataSource.REMOTE;
    }

    private static class a implements b {
        a() {
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.HttpUrlFetcher.b
        public HttpURLConnection a(URL url) throws IOException {
            return (HttpURLConnection) url.openConnection();
        }
    }
}
