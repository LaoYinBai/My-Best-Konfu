package com.ss.union.game.sdk.core.luban;

import android.graphics.BitmapFactory;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
enum a {
    SINGLE;

    private static final String b = "Luban";
    private static final String c = ".jpg";
    private final byte[] d = {-1, -40, -1};

    a() {
    }

    boolean a(InputStream inputStream) {
        return a(c(inputStream));
    }

    int b(InputStream inputStream) {
        return b(c(inputStream));
    }

    private boolean a(byte[] bArr) {
        if (bArr == null || bArr.length < 3) {
            return false;
        }
        return Arrays.equals(this.d, new byte[]{bArr[0], bArr[1], bArr[2]});
    }

    private int b(byte[] bArr) {
        int i;
        int i2;
        if (bArr == null) {
            return 0;
        }
        int i3 = 0;
        while (i3 + 3 < bArr.length) {
            int i4 = i3 + 1;
            if ((bArr[i3] & UByte.MAX_VALUE) == 255) {
                int i5 = bArr[i4] & UByte.MAX_VALUE;
                if (i5 != 255) {
                    i4++;
                    if (i5 != 216 && i5 != 1) {
                        if (i5 != 217 && i5 != 218) {
                            int iA = a(bArr, i4, 2, false);
                            if (iA < 2 || (i2 = i4 + iA) > bArr.length) {
                                Log.e(b, "Invalid length");
                                return 0;
                            }
                            if (i5 == 225 && iA >= 8 && a(bArr, i4 + 2, 4, false) == 1165519206 && a(bArr, i4 + 6, 2, false) == 0) {
                                i3 = i4 + 8;
                                i = iA - 8;
                                break;
                            }
                            i3 = i2;
                        }
                    }
                }
                i3 = i4;
            }
            i3 = i4;
        }
        i = 0;
        if (i > 8) {
            int iA2 = a(bArr, i3, 4, false);
            if (iA2 != 1229531648 && iA2 != 1296891946) {
                Log.e(b, "Invalid byte order");
                return 0;
            }
            boolean z = iA2 == 1229531648;
            int iA3 = a(bArr, i3 + 4, 4, z) + 2;
            if (iA3 < 10 || iA3 > i) {
                Log.e(b, "Invalid offset");
                return 0;
            }
            int i6 = i3 + iA3;
            int i7 = i - iA3;
            int iA4 = a(bArr, i6 - 2, 2, z);
            while (true) {
                int i8 = iA4 - 1;
                if (iA4 <= 0 || i7 < 12) {
                    break;
                }
                if (a(bArr, i6, 2, z) == 274) {
                    int iA5 = a(bArr, i6 + 8, 2, z);
                    if (iA5 == 1) {
                        return 0;
                    }
                    if (iA5 == 3) {
                        return 180;
                    }
                    if (iA5 == 6) {
                        return 90;
                    }
                    if (iA5 == 8) {
                        return 270;
                    }
                    Log.e(b, "Unsupported orientation");
                    return 0;
                }
                i6 += 12;
                i7 -= 12;
                iA4 = i8;
            }
        }
        Log.e(b, "Orientation not found");
        return 0;
    }

    String a(InputStreamProvider inputStreamProvider) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStreamProvider.open(), null, options);
            return options.outMimeType.replace("image/", ".");
        } catch (Exception unused) {
            return c;
        }
    }

    boolean a(int i, String str) {
        if (i <= 0) {
            return true;
        }
        File file = new File(str);
        return file.exists() && file.length() > ((long) (i << 10));
    }

    private int a(byte[] bArr, int i, int i2, boolean z) {
        int i3;
        if (z) {
            i += i2 - 1;
            i3 = -1;
        } else {
            i3 = 1;
        }
        int i4 = 0;
        while (true) {
            int i5 = i2 - 1;
            if (i2 <= 0) {
                return i4;
            }
            i4 = (bArr[i] & UByte.MAX_VALUE) | (i4 << 8);
            i += i3;
            i2 = i5;
        }
    }

    private byte[] c(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return new byte[0];
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            try {
                try {
                    int i = inputStream.read(bArr, 0, bArr.length);
                    if (i != -1) {
                        byteArrayOutputStream.write(bArr, 0, i);
                    } else {
                        try {
                            break;
                        } catch (IOException unused) {
                        }
                    }
                } catch (Exception unused2) {
                    byte[] bArr2 = new byte[0];
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException unused3) {
                    }
                    return bArr2;
                }
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused4) {
                }
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
}
