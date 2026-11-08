package com.ss.union.game.sdk.common.net.http.base.response;

import com.umeng.message.util.HttpRequest;
import java.io.File;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class CoreNetFileWrapper {
    public MediaType contentType;
    public File file;
    public String fileName;
    public long fileSize;

    public CoreNetFileWrapper(File file, String str, MediaType mediaType, long j) {
        this.file = file;
        this.fileName = str;
        this.contentType = mediaType;
        this.fileSize = j;
    }

    public static final class MediaType {
        private static final String a = "([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)";
        private static final String b = "\"([^\"]*)\"";
        private static final Pattern c = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");
        private static final Pattern d = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");
        private final String e;
        private final String f;
        private final String g;
        private final String h;

        private MediaType(String str, String str2, String str3, String str4) {
            this.e = str;
            this.f = str2;
            this.g = str3;
            this.h = str4;
        }

        public static MediaType parse(String str) {
            Matcher matcher = c.matcher(str);
            if (!matcher.lookingAt()) {
                return null;
            }
            String lowerCase = matcher.group(1).toLowerCase(Locale.US);
            String lowerCase2 = matcher.group(2).toLowerCase(Locale.US);
            Matcher matcher2 = d.matcher(str);
            String str2 = null;
            for (int iEnd = matcher.end(); iEnd < str.length(); iEnd = matcher2.end()) {
                matcher2.region(iEnd, str.length());
                if (!matcher2.lookingAt()) {
                    return null;
                }
                String strGroup = matcher2.group(1);
                if (strGroup != null && strGroup.equalsIgnoreCase(HttpRequest.PARAM_CHARSET)) {
                    String strGroup2 = matcher2.group(2);
                    if (strGroup2 != null) {
                        if (strGroup2.startsWith("'") && strGroup2.endsWith("'") && strGroup2.length() > 2) {
                            strGroup2 = strGroup2.substring(1, strGroup2.length() - 1);
                        }
                    } else {
                        strGroup2 = matcher2.group(3);
                    }
                    if (str2 != null && !strGroup2.equalsIgnoreCase(str2)) {
                        return null;
                    }
                    str2 = strGroup2;
                }
            }
            return new MediaType(str, lowerCase, lowerCase2, str2);
        }

        public String type() {
            return this.f;
        }

        public String subtype() {
            return this.g;
        }

        public Charset charset() {
            return charset(null);
        }

        public Charset charset(Charset charset) {
            try {
                return this.h != null ? Charset.forName(this.h) : charset;
            } catch (IllegalArgumentException unused) {
                return charset;
            }
        }

        public String toString() {
            return this.e;
        }

        public boolean equals(Object obj) {
            return (obj instanceof MediaType) && ((MediaType) obj).e.equals(this.e);
        }

        public int hashCode() {
            return this.e.hashCode();
        }
    }
}
