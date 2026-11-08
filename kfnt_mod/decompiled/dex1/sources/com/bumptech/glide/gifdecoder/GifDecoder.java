package com.bumptech.glide.gifdecoder;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import androidx.fragment.app.FragmentTransaction;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GifDecoder {
    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private static final int DISPOSAL_BACKGROUND = 2;
    private static final int DISPOSAL_NONE = 1;
    private static final int DISPOSAL_PREVIOUS = 3;
    private static final int DISPOSAL_UNSPECIFIED = 0;
    private static final int INITIAL_FRAME_POINTER = -1;
    private static final int MAX_STACK_SIZE = 4096;
    private static final int NULL_CODE = -1;
    public static final int STATUS_FORMAT_ERROR = 1;
    public static final int STATUS_OK = 0;
    public static final int STATUS_OPEN_ERROR = 2;
    public static final int STATUS_PARTIAL_DECODE = 3;
    private static final String TAG = "GifDecoder";
    private int[] act;
    private BitmapProvider bitmapProvider;
    private byte[] data;
    private int framePointer;
    private byte[] mainPixels;
    private int[] mainScratch;
    private GifHeaderParser parser;
    private byte[] pixelStack;
    private short[] prefix;
    private Bitmap previousImage;
    private ByteBuffer rawData;
    private boolean savePrevious;
    private int status;
    private byte[] suffix;
    private final byte[] block = new byte[256];
    private GifHeader header = new GifHeader();

    public interface BitmapProvider {
        Bitmap obtain(int i, int i2, Bitmap.Config config);

        void release(Bitmap bitmap);
    }

    public GifDecoder(BitmapProvider bitmapProvider) {
        this.bitmapProvider = bitmapProvider;
    }

    public int getWidth() {
        return this.header.width;
    }

    public int getHeight() {
        return this.header.height;
    }

    public byte[] getData() {
        return this.data;
    }

    public int getStatus() {
        return this.status;
    }

    public void advance() {
        this.framePointer = (this.framePointer + 1) % this.header.frameCount;
    }

    public int getDelay(int i) {
        if (i < 0 || i >= this.header.frameCount) {
            return -1;
        }
        return this.header.frames.get(i).delay;
    }

    public int getNextDelay() {
        if (this.header.frameCount <= 0 || this.framePointer < 0) {
            return -1;
        }
        return getDelay(this.framePointer);
    }

    public int getFrameCount() {
        return this.header.frameCount;
    }

    public int getCurrentFrameIndex() {
        return this.framePointer;
    }

    public void resetFrameIndex() {
        this.framePointer = -1;
    }

    public int getLoopCount() {
        return this.header.loopCount;
    }

    public synchronized Bitmap getNextFrame() {
        if (this.header.frameCount <= 0 || this.framePointer < 0) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "unable to decode frame, frameCount=" + this.header.frameCount + " framePointer=" + this.framePointer);
            }
            this.status = 1;
        }
        if (this.status != 1 && this.status != 2) {
            int i = 0;
            this.status = 0;
            GifFrame gifFrame = this.header.frames.get(this.framePointer);
            int i2 = this.framePointer - 1;
            GifFrame gifFrame2 = i2 >= 0 ? this.header.frames.get(i2) : null;
            if (gifFrame.lct == null) {
                this.act = this.header.gct;
            } else {
                this.act = gifFrame.lct;
                if (this.header.bgIndex == gifFrame.transIndex) {
                    this.header.bgColor = 0;
                }
            }
            if (gifFrame.transparency) {
                int i3 = this.act[gifFrame.transIndex];
                this.act[gifFrame.transIndex] = 0;
                i = i3;
            }
            if (this.act == null) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "No Valid Color Table");
                }
                this.status = 1;
                return null;
            }
            Bitmap pixels = setPixels(gifFrame, gifFrame2);
            if (gifFrame.transparency) {
                this.act[gifFrame.transIndex] = i;
            }
            return pixels;
        }
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Unable to decode frame, status=" + this.status);
        }
        return null;
    }

    public int read(InputStream inputStream, int i) throws IOException {
        if (inputStream != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i > 0 ? i + 4096 : 16384);
                byte[] bArr = new byte[16384];
                while (true) {
                    int i2 = inputStream.read(bArr, 0, bArr.length);
                    if (i2 == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i2);
                }
                byteArrayOutputStream.flush();
                read(byteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                Log.w(TAG, "Error reading data from stream", e);
            }
        } else {
            this.status = 2;
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e2) {
                Log.w(TAG, "Error closing stream", e2);
            }
        }
        return this.status;
    }

    public void clear() {
        this.header = null;
        this.data = null;
        this.mainPixels = null;
        this.mainScratch = null;
        if (this.previousImage != null) {
            this.bitmapProvider.release(this.previousImage);
        }
        this.previousImage = null;
    }

    public void setData(GifHeader gifHeader, byte[] bArr) {
        this.header = gifHeader;
        this.data = bArr;
        this.status = 0;
        this.framePointer = -1;
        this.rawData = ByteBuffer.wrap(bArr);
        this.rawData.rewind();
        this.rawData.order(ByteOrder.LITTLE_ENDIAN);
        this.savePrevious = false;
        Iterator<GifFrame> it = gifHeader.frames.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            } else if (it.next().dispose == 3) {
                this.savePrevious = true;
                break;
            }
        }
        this.mainPixels = new byte[gifHeader.width * gifHeader.height];
        this.mainScratch = new int[gifHeader.width * gifHeader.height];
    }

    private GifHeaderParser getHeaderParser() {
        if (this.parser == null) {
            this.parser = new GifHeaderParser();
        }
        return this.parser;
    }

    public int read(byte[] bArr) {
        this.data = bArr;
        this.header = getHeaderParser().setData(bArr).parseHeader();
        if (bArr != null) {
            this.rawData = ByteBuffer.wrap(bArr);
            this.rawData.rewind();
            this.rawData.order(ByteOrder.LITTLE_ENDIAN);
            this.mainPixels = new byte[this.header.width * this.header.height];
            this.mainScratch = new int[this.header.width * this.header.height];
            this.savePrevious = false;
            Iterator<GifFrame> it = this.header.frames.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (it.next().dispose == 3) {
                    this.savePrevious = true;
                    break;
                }
            }
        }
        return this.status;
    }

    private Bitmap setPixels(GifFrame gifFrame, GifFrame gifFrame2) {
        int i;
        int i2 = this.header.width;
        int i3 = this.header.height;
        int[] iArr = this.mainScratch;
        int i4 = 0;
        if (gifFrame2 != null && gifFrame2.dispose > 0) {
            if (gifFrame2.dispose == 2) {
                Arrays.fill(iArr, !gifFrame.transparency ? this.header.bgColor : 0);
            } else if (gifFrame2.dispose == 3 && this.previousImage != null) {
                this.previousImage.getPixels(iArr, 0, i2, 0, 0, i2, i3);
            }
        }
        decodeBitmapData(gifFrame);
        int i5 = 0;
        int i6 = 1;
        int i7 = 8;
        while (i4 < gifFrame.ih) {
            if (gifFrame.interlace) {
                if (i5 >= gifFrame.ih) {
                    i6++;
                    switch (i6) {
                        case 2:
                            i5 = 4;
                            break;
                        case 3:
                            i5 = 2;
                            i7 = 4;
                            break;
                        case 4:
                            i5 = 1;
                            i7 = 2;
                            break;
                    }
                }
                i = i5 + i7;
            } else {
                i = i5;
                i5 = i4;
            }
            int i8 = i5 + gifFrame.iy;
            if (i8 < this.header.height) {
                int i9 = i8 * this.header.width;
                int i10 = gifFrame.ix + i9;
                int i11 = gifFrame.iw + i10;
                if (this.header.width + i9 < i11) {
                    i11 = this.header.width + i9;
                }
                int i12 = gifFrame.iw * i4;
                while (i10 < i11) {
                    int i13 = i12 + 1;
                    int i14 = this.act[this.mainPixels[i12] & 255];
                    if (i14 != 0) {
                        iArr[i10] = i14;
                    }
                    i10++;
                    i12 = i13;
                }
            }
            i4++;
            i5 = i;
        }
        if (this.savePrevious && (gifFrame.dispose == 0 || gifFrame.dispose == 1)) {
            if (this.previousImage == null) {
                this.previousImage = getNextBitmap();
            }
            this.previousImage.setPixels(iArr, 0, i2, 0, 0, i2, i3);
        }
        Bitmap nextBitmap = getNextBitmap();
        nextBitmap.setPixels(iArr, 0, i2, 0, 0, i2, i3);
        return nextBitmap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v20, types: [short] */
    /* JADX WARN: Type inference failed for: r2v23 */
    private void decodeBitmapData(GifFrame gifFrame) {
        int i;
        short s;
        if (gifFrame != null) {
            this.rawData.position(gifFrame.bufferFrameStart);
        }
        int i2 = gifFrame == null ? this.header.width * this.header.height : gifFrame.ih * gifFrame.iw;
        if (this.mainPixels == null || this.mainPixels.length < i2) {
            this.mainPixels = new byte[i2];
        }
        if (this.prefix == null) {
            this.prefix = new short[4096];
        }
        if (this.suffix == null) {
            this.suffix = new byte[4096];
        }
        if (this.pixelStack == null) {
            this.pixelStack = new byte[FragmentTransaction.TRANSIT_FRAGMENT_OPEN];
        }
        int i3 = read();
        int i4 = 1;
        int i5 = 1 << i3;
        int i6 = i5 + 1;
        int i7 = i5 + 2;
        int i8 = i3 + 1;
        int i9 = (1 << i8) - 1;
        for (int i10 = 0; i10 < i5; i10++) {
            this.prefix[i10] = 0;
            this.suffix[i10] = (byte) i10;
        }
        int i11 = -1;
        int i12 = i8;
        int i13 = i7;
        int i14 = i9;
        int i15 = 0;
        int block = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        int i21 = 0;
        int i22 = -1;
        while (true) {
            if (i15 >= i2) {
                break;
            }
            int i23 = 3;
            if (block == 0) {
                block = readBlock();
                if (block <= 0) {
                    this.status = 3;
                    break;
                }
                i18 = 0;
            }
            i17 += (this.block[i18] & 255) << i19;
            i18 += i4;
            block += i11;
            int i24 = i19 + 8;
            int i25 = i20;
            int i26 = i22;
            int i27 = i15;
            int i28 = i16;
            int i29 = i13;
            int i30 = i12;
            while (i24 >= i30) {
                int i31 = i17 & i14;
                i17 >>= i30;
                i24 -= i30;
                if (i31 != i5) {
                    if (i31 > i29) {
                        this.status = i23;
                    } else if (i31 != i6) {
                        if (i26 == -1) {
                            this.pixelStack[i21] = this.suffix[i31];
                            i26 = i31;
                            i25 = i26;
                            i21++;
                        } else {
                            if (i31 >= i29) {
                                i = i8;
                                this.pixelStack[i21] = (byte) i25;
                                s = i26;
                                i21++;
                            } else {
                                i = i8;
                                s = i31;
                            }
                            while (s >= i5) {
                                this.pixelStack[i21] = this.suffix[s];
                                s = this.prefix[s];
                                i21++;
                                i24 = i24;
                            }
                            int i32 = i24;
                            int i33 = this.suffix[s] & 255;
                            int i34 = i21 + 1;
                            int i35 = i5;
                            byte b2 = (byte) i33;
                            this.pixelStack[i21] = b2;
                            if (i29 < 4096) {
                                this.prefix[i29] = (short) i26;
                                this.suffix[i29] = b2;
                                i29++;
                                if ((i29 & i14) == 0 && i29 < 4096) {
                                    i30++;
                                    i14 += i29;
                                }
                            }
                            i21 = i34;
                            while (i21 > 0) {
                                i21--;
                                this.mainPixels[i28] = this.pixelStack[i21];
                                i27++;
                                i28++;
                            }
                            i25 = i33;
                            i26 = i31;
                            i8 = i;
                            i24 = i32;
                            i5 = i35;
                        }
                        i23 = 3;
                    }
                    i22 = i26;
                    i12 = i30;
                    i13 = i29;
                    i15 = i27;
                    i16 = i28;
                    i20 = i25;
                    i4 = 1;
                    i11 = -1;
                    i19 = i24;
                    break;
                }
                i30 = i8;
                i29 = i7;
                i14 = i9;
                i26 = -1;
                i11 = -1;
            }
            i22 = i26;
            i12 = i30;
            i13 = i29;
            i15 = i27;
            i16 = i28;
            i4 = 1;
            i20 = i25;
            i19 = i24;
            i8 = i8;
        }
        while (i16 < i2) {
            this.mainPixels[i16] = 0;
            i16++;
        }
    }

    private int read() {
        try {
            return this.rawData.get() & 255;
        } catch (Exception unused) {
            this.status = 1;
            return 0;
        }
    }

    private int readBlock() {
        int i = read();
        int i2 = 0;
        if (i > 0) {
            while (i2 < i) {
                int i3 = i - i2;
                try {
                    this.rawData.get(this.block, i2, i3);
                    i2 += i3;
                } catch (Exception e) {
                    Log.w(TAG, "Error Reading Block", e);
                    this.status = 1;
                }
            }
        }
        return i2;
    }

    private Bitmap getNextBitmap() {
        Bitmap bitmapObtain = this.bitmapProvider.obtain(this.header.width, this.header.height, BITMAP_CONFIG);
        if (bitmapObtain == null) {
            bitmapObtain = Bitmap.createBitmap(this.header.width, this.header.height, BITMAP_CONFIG);
        }
        setAlpha(bitmapObtain);
        return bitmapObtain;
    }

    @TargetApi(12)
    private static void setAlpha(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 12) {
            bitmap.setHasAlpha(true);
        }
    }
}
