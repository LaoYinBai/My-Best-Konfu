package com.ss.union.game.sdk.core.glide;

import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder;
import com.ss.union.game.sdk.core.glide.load.DecodeFormat;
import com.ss.union.game.sdk.core.glide.load.ImageHeaderParser;
import com.ss.union.game.sdk.core.glide.load.ResourceDecoder;
import com.ss.union.game.sdk.core.glide.load.ResourceEncoder;
import com.ss.union.game.sdk.core.glide.load.data.InputStreamRewinder;
import com.ss.union.game.sdk.core.glide.load.engine.Engine;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.ArrayPool;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool;
import com.ss.union.game.sdk.core.glide.load.engine.cache.MemoryCache;
import com.ss.union.game.sdk.core.glide.load.engine.prefill.BitmapPreFiller;
import com.ss.union.game.sdk.core.glide.load.engine.prefill.PreFillType;
import com.ss.union.game.sdk.core.glide.load.model.AssetUriLoader;
import com.ss.union.game.sdk.core.glide.load.model.ByteArrayLoader;
import com.ss.union.game.sdk.core.glide.load.model.ByteBufferEncoder;
import com.ss.union.game.sdk.core.glide.load.model.ByteBufferFileLoader;
import com.ss.union.game.sdk.core.glide.load.model.DataUrlLoader;
import com.ss.union.game.sdk.core.glide.load.model.FileLoader;
import com.ss.union.game.sdk.core.glide.load.model.GlideUrl;
import com.ss.union.game.sdk.core.glide.load.model.MediaStoreFileLoader;
import com.ss.union.game.sdk.core.glide.load.model.ResourceLoader;
import com.ss.union.game.sdk.core.glide.load.model.StreamEncoder;
import com.ss.union.game.sdk.core.glide.load.model.StringLoader;
import com.ss.union.game.sdk.core.glide.load.model.UnitModelLoader;
import com.ss.union.game.sdk.core.glide.load.model.UriLoader;
import com.ss.union.game.sdk.core.glide.load.model.UrlUriLoader;
import com.ss.union.game.sdk.core.glide.load.model.stream.HttpGlideUrlLoader;
import com.ss.union.game.sdk.core.glide.load.model.stream.HttpUriLoader;
import com.ss.union.game.sdk.core.glide.load.model.stream.MediaStoreImageThumbLoader;
import com.ss.union.game.sdk.core.glide.load.model.stream.MediaStoreVideoThumbLoader;
import com.ss.union.game.sdk.core.glide.load.model.stream.UrlLoader;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.BitmapDrawableDecoder;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.BitmapDrawableEncoder;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.BitmapEncoder;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.ByteBufferBitmapDecoder;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.DefaultImageHeaderParser;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.Downsampler;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.ResourceBitmapDecoder;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.StreamBitmapDecoder;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.UnitBitmapDecoder;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.VideoDecoder;
import com.ss.union.game.sdk.core.glide.load.resource.bytes.ByteBufferRewinder;
import com.ss.union.game.sdk.core.glide.load.resource.drawable.ResourceDrawableDecoder;
import com.ss.union.game.sdk.core.glide.load.resource.drawable.UnitDrawableDecoder;
import com.ss.union.game.sdk.core.glide.load.resource.file.FileDecoder;
import com.ss.union.game.sdk.core.glide.load.resource.gif.ByteBufferGifDecoder;
import com.ss.union.game.sdk.core.glide.load.resource.gif.GifDrawable;
import com.ss.union.game.sdk.core.glide.load.resource.gif.GifDrawableEncoder;
import com.ss.union.game.sdk.core.glide.load.resource.gif.GifFrameResourceDecoder;
import com.ss.union.game.sdk.core.glide.load.resource.gif.StreamGifDecoder;
import com.ss.union.game.sdk.core.glide.load.resource.transcode.BitmapBytesTranscoder;
import com.ss.union.game.sdk.core.glide.load.resource.transcode.BitmapDrawableTranscoder;
import com.ss.union.game.sdk.core.glide.load.resource.transcode.DrawableBytesTranscoder;
import com.ss.union.game.sdk.core.glide.load.resource.transcode.GifDrawableBytesTranscoder;
import com.ss.union.game.sdk.core.glide.manager.ConnectivityMonitorFactory;
import com.ss.union.game.sdk.core.glide.manager.RequestManagerRetriever;
import com.ss.union.game.sdk.core.glide.module.GlideModule;
import com.ss.union.game.sdk.core.glide.module.ManifestParser;
import com.ss.union.game.sdk.core.glide.request.RequestListener;
import com.ss.union.game.sdk.core.glide.request.RequestOptions;
import com.ss.union.game.sdk.core.glide.request.target.ImageViewTargetFactory;
import com.ss.union.game.sdk.core.glide.request.target.Target;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import com.ss.union.game.sdk.core.glide.util.Util;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Glide implements ComponentCallbacks2 {
    private static final String a = "image_manager_disk_cache";
    private static final String b = "Glide";
    private static volatile Glide c;
    private static volatile boolean d;
    private final Engine e;
    private final BitmapPool f;
    private final MemoryCache g;
    private final BitmapPreFiller h;
    private final GlideContext i;
    private final Registry j;
    private final ArrayPool k;
    private final RequestManagerRetriever l;
    private final ConnectivityMonitorFactory m;
    private final List<RequestManager> n = new ArrayList();
    private MemoryCategory o = MemoryCategory.NORMAL;

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
    }

    public static File getPhotoCacheDir(Context context) {
        return getPhotoCacheDir(context, "image_manager_disk_cache");
    }

    public static File getPhotoCacheDir(Context context, String str) {
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            File file = new File(cacheDir, str);
            if (file.mkdirs() || (file.exists() && file.isDirectory())) {
                return file;
            }
            return null;
        }
        if (Log.isLoggable(b, 6)) {
            Log.e(b, "default disk cache dir is null");
        }
        return null;
    }

    public static Glide get(Context context) {
        if (c == null) {
            synchronized (Glide.class) {
                if (c == null) {
                    a(context);
                }
            }
        }
        return c;
    }

    private static void a(Context context) throws PackageManager.NameNotFoundException {
        if (d) {
            throw new IllegalStateException("You cannot call Glide.get() in registerComponents(), use the provided Glide instance instead");
        }
        d = true;
        b(context);
        d = false;
    }

    @Deprecated
    public static synchronized void init(Glide glide) {
        if (c != null) {
            tearDown();
        }
        c = glide;
    }

    public static synchronized void init(Context context, GlideBuilder glideBuilder) {
        if (c != null) {
            tearDown();
        }
        a(context, glideBuilder);
    }

    public static synchronized void tearDown() {
        if (c != null) {
            c.getContext().getApplicationContext().unregisterComponentCallbacks(c);
            c.e.shutdown();
        }
        c = null;
    }

    private static void b(Context context) throws PackageManager.NameNotFoundException {
        a(context, new GlideBuilder());
    }

    private static void a(Context context, GlideBuilder glideBuilder) throws PackageManager.NameNotFoundException {
        Context applicationContext = context.getApplicationContext();
        a aVarC = c();
        List<GlideModule> listEmptyList = Collections.emptyList();
        if (aVarC == null || aVarC.isManifestParsingEnabled()) {
            listEmptyList = new ManifestParser(applicationContext).parse();
        }
        if (aVarC != null && !aVarC.a().isEmpty()) {
            Set<Class<?>> setA = aVarC.a();
            Iterator<GlideModule> it = listEmptyList.iterator();
            while (it.hasNext()) {
                GlideModule next = it.next();
                if (setA.contains(next.getClass())) {
                    if (Log.isLoggable(b, 3)) {
                        Log.d(b, "AppGlideModule excludes manifest GlideModule: " + next);
                    }
                    it.remove();
                }
            }
        }
        if (Log.isLoggable(b, 3)) {
            Iterator<GlideModule> it2 = listEmptyList.iterator();
            while (it2.hasNext()) {
                Log.d(b, "Discovered GlideModule from manifest: " + it2.next().getClass());
            }
        }
        glideBuilder.a(aVarC != null ? aVarC.b() : null);
        Iterator<GlideModule> it3 = listEmptyList.iterator();
        while (it3.hasNext()) {
            it3.next().applyOptions(applicationContext, glideBuilder);
        }
        if (aVarC != null) {
            aVarC.applyOptions(applicationContext, glideBuilder);
        }
        Glide glideA = glideBuilder.a(applicationContext);
        Iterator<GlideModule> it4 = listEmptyList.iterator();
        while (it4.hasNext()) {
            it4.next().registerComponents(applicationContext, glideA, glideA.j);
        }
        if (aVarC != null) {
            aVarC.registerComponents(applicationContext, glideA, glideA.j);
        }
        applicationContext.registerComponentCallbacks(glideA);
        c = glideA;
    }

    private static a c() {
        try {
            return (a) Class.forName("com.bumptech.glide.GeneratedAppGlideModuleImpl").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (ClassNotFoundException unused) {
            if (Log.isLoggable(b, 5)) {
                Log.w(b, "Failed to find GeneratedAppGlideModule. You should include an annotationProcessor compile dependency on com.github.bumptech.glide:compiler in your application and a @GlideModule annotated AppGlideModule implementation or LibraryGlideModules will be silently ignored");
            }
            return null;
        } catch (IllegalAccessException e) {
            a(e);
            return null;
        } catch (InstantiationException e2) {
            a(e2);
            return null;
        } catch (NoSuchMethodException e3) {
            a(e3);
            return null;
        } catch (InvocationTargetException e4) {
            a(e4);
            return null;
        }
    }

    private static void a(Exception exc) {
        throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", exc);
    }

    Glide(Context context, Engine engine, MemoryCache memoryCache, BitmapPool bitmapPool, ArrayPool arrayPool, RequestManagerRetriever requestManagerRetriever, ConnectivityMonitorFactory connectivityMonitorFactory, int i, RequestOptions requestOptions, Map<Class<?>, TransitionOptions<?, ?>> map, List<RequestListener<Object>> list, boolean z) {
        this.e = engine;
        this.f = bitmapPool;
        this.k = arrayPool;
        this.g = memoryCache;
        this.l = requestManagerRetriever;
        this.m = connectivityMonitorFactory;
        this.h = new BitmapPreFiller(memoryCache, bitmapPool, (DecodeFormat) requestOptions.getOptions().get(Downsampler.DECODE_FORMAT));
        Resources resources = context.getResources();
        this.j = new Registry();
        this.j.register(new DefaultImageHeaderParser());
        List<ImageHeaderParser> imageHeaderParsers = this.j.getImageHeaderParsers();
        Downsampler downsampler = new Downsampler(imageHeaderParsers, resources.getDisplayMetrics(), bitmapPool, arrayPool);
        ByteBufferGifDecoder byteBufferGifDecoder = new ByteBufferGifDecoder(context, imageHeaderParsers, bitmapPool, arrayPool);
        ResourceDecoder<ParcelFileDescriptor, Bitmap> resourceDecoderParcel = VideoDecoder.parcel(bitmapPool);
        ByteBufferBitmapDecoder byteBufferBitmapDecoder = new ByteBufferBitmapDecoder(downsampler);
        StreamBitmapDecoder streamBitmapDecoder = new StreamBitmapDecoder(downsampler, arrayPool);
        ResourceDrawableDecoder resourceDrawableDecoder = new ResourceDrawableDecoder(context);
        ResourceLoader.StreamFactory streamFactory = new ResourceLoader.StreamFactory(resources);
        ResourceLoader.UriFactory uriFactory = new ResourceLoader.UriFactory(resources);
        ResourceLoader.FileDescriptorFactory fileDescriptorFactory = new ResourceLoader.FileDescriptorFactory(resources);
        ResourceLoader.AssetFileDescriptorFactory assetFileDescriptorFactory = new ResourceLoader.AssetFileDescriptorFactory(resources);
        BitmapEncoder bitmapEncoder = new BitmapEncoder(arrayPool);
        BitmapBytesTranscoder bitmapBytesTranscoder = new BitmapBytesTranscoder();
        GifDrawableBytesTranscoder gifDrawableBytesTranscoder = new GifDrawableBytesTranscoder();
        ContentResolver contentResolver = context.getContentResolver();
        this.j.append(ByteBuffer.class, new ByteBufferEncoder()).append(InputStream.class, new StreamEncoder(arrayPool)).append(Registry.BUCKET_BITMAP, ByteBuffer.class, Bitmap.class, byteBufferBitmapDecoder).append(Registry.BUCKET_BITMAP, InputStream.class, Bitmap.class, streamBitmapDecoder).append(Registry.BUCKET_BITMAP, ParcelFileDescriptor.class, Bitmap.class, resourceDecoderParcel).append(Registry.BUCKET_BITMAP, AssetFileDescriptor.class, Bitmap.class, VideoDecoder.asset(bitmapPool)).append(Bitmap.class, Bitmap.class, UnitModelLoader.Factory.getInstance()).append(Registry.BUCKET_BITMAP, Bitmap.class, Bitmap.class, new UnitBitmapDecoder()).append(Bitmap.class, (ResourceEncoder) bitmapEncoder).append(Registry.BUCKET_BITMAP_DRAWABLE, ByteBuffer.class, BitmapDrawable.class, new BitmapDrawableDecoder(resources, byteBufferBitmapDecoder)).append(Registry.BUCKET_BITMAP_DRAWABLE, InputStream.class, BitmapDrawable.class, new BitmapDrawableDecoder(resources, streamBitmapDecoder)).append(Registry.BUCKET_BITMAP_DRAWABLE, ParcelFileDescriptor.class, BitmapDrawable.class, new BitmapDrawableDecoder(resources, resourceDecoderParcel)).append(BitmapDrawable.class, (ResourceEncoder) new BitmapDrawableEncoder(bitmapPool, bitmapEncoder)).append(Registry.BUCKET_GIF, InputStream.class, GifDrawable.class, new StreamGifDecoder(imageHeaderParsers, byteBufferGifDecoder, arrayPool)).append(Registry.BUCKET_GIF, ByteBuffer.class, GifDrawable.class, byteBufferGifDecoder).append(GifDrawable.class, (ResourceEncoder) new GifDrawableEncoder()).append(GifDecoder.class, GifDecoder.class, UnitModelLoader.Factory.getInstance()).append(Registry.BUCKET_BITMAP, GifDecoder.class, Bitmap.class, new GifFrameResourceDecoder(bitmapPool)).append(Uri.class, Drawable.class, resourceDrawableDecoder).append(Uri.class, Bitmap.class, new ResourceBitmapDecoder(resourceDrawableDecoder, bitmapPool)).register(new ByteBufferRewinder.Factory()).append(File.class, ByteBuffer.class, new ByteBufferFileLoader.Factory()).append(File.class, InputStream.class, new FileLoader.StreamFactory()).append(File.class, File.class, new FileDecoder()).append(File.class, ParcelFileDescriptor.class, new FileLoader.FileDescriptorFactory()).append(File.class, File.class, UnitModelLoader.Factory.getInstance()).register(new InputStreamRewinder.Factory(arrayPool)).append(Integer.TYPE, InputStream.class, streamFactory).append(Integer.TYPE, ParcelFileDescriptor.class, fileDescriptorFactory).append(Integer.class, InputStream.class, streamFactory).append(Integer.class, ParcelFileDescriptor.class, fileDescriptorFactory).append(Integer.class, Uri.class, uriFactory).append(Integer.TYPE, AssetFileDescriptor.class, assetFileDescriptorFactory).append(Integer.class, AssetFileDescriptor.class, assetFileDescriptorFactory).append(Integer.TYPE, Uri.class, uriFactory).append(String.class, InputStream.class, new DataUrlLoader.StreamFactory()).append(Uri.class, InputStream.class, new DataUrlLoader.StreamFactory()).append(String.class, InputStream.class, new StringLoader.StreamFactory()).append(String.class, ParcelFileDescriptor.class, new StringLoader.FileDescriptorFactory()).append(String.class, AssetFileDescriptor.class, new StringLoader.AssetFileDescriptorFactory()).append(Uri.class, InputStream.class, new HttpUriLoader.Factory()).append(Uri.class, InputStream.class, new AssetUriLoader.StreamFactory(context.getAssets())).append(Uri.class, ParcelFileDescriptor.class, new AssetUriLoader.FileDescriptorFactory(context.getAssets())).append(Uri.class, InputStream.class, new MediaStoreImageThumbLoader.Factory(context)).append(Uri.class, InputStream.class, new MediaStoreVideoThumbLoader.Factory(context)).append(Uri.class, InputStream.class, new UriLoader.StreamFactory(contentResolver)).append(Uri.class, ParcelFileDescriptor.class, new UriLoader.FileDescriptorFactory(contentResolver)).append(Uri.class, AssetFileDescriptor.class, new UriLoader.AssetFileDescriptorFactory(contentResolver)).append(Uri.class, InputStream.class, new UrlUriLoader.StreamFactory()).append(URL.class, InputStream.class, new UrlLoader.StreamFactory()).append(Uri.class, File.class, new MediaStoreFileLoader.Factory(context)).append(GlideUrl.class, InputStream.class, new HttpGlideUrlLoader.Factory()).append(byte[].class, ByteBuffer.class, new ByteArrayLoader.ByteBufferFactory()).append(byte[].class, InputStream.class, new ByteArrayLoader.StreamFactory()).append(Uri.class, Uri.class, UnitModelLoader.Factory.getInstance()).append(Drawable.class, Drawable.class, UnitModelLoader.Factory.getInstance()).append(Drawable.class, Drawable.class, new UnitDrawableDecoder()).register(Bitmap.class, BitmapDrawable.class, new BitmapDrawableTranscoder(resources)).register(Bitmap.class, byte[].class, bitmapBytesTranscoder).register(Drawable.class, byte[].class, new DrawableBytesTranscoder(bitmapPool, bitmapBytesTranscoder, gifDrawableBytesTranscoder)).register(GifDrawable.class, byte[].class, gifDrawableBytesTranscoder);
        this.i = new GlideContext(context, arrayPool, this.j, new ImageViewTargetFactory(), requestOptions, map, list, engine, z, i);
    }

    public BitmapPool getBitmapPool() {
        return this.f;
    }

    public ArrayPool getArrayPool() {
        return this.k;
    }

    public Context getContext() {
        return this.i.getBaseContext();
    }

    ConnectivityMonitorFactory a() {
        return this.m;
    }

    GlideContext b() {
        return this.i;
    }

    public void preFillBitmapPool(PreFillType.Builder... builderArr) {
        this.h.preFill(builderArr);
    }

    public void clearMemory() {
        Util.assertMainThread();
        this.g.clearMemory();
        this.f.clearMemory();
        this.k.clearMemory();
    }

    public void trimMemory(int i) {
        Util.assertMainThread();
        this.g.trimMemory(i);
        this.f.trimMemory(i);
        this.k.trimMemory(i);
    }

    public void clearDiskCache() {
        Util.assertBackgroundThread();
        this.e.clearDiskCache();
    }

    public RequestManagerRetriever getRequestManagerRetriever() {
        return this.l;
    }

    public MemoryCategory setMemoryCategory(MemoryCategory memoryCategory) {
        Util.assertMainThread();
        this.g.setSizeMultiplier(memoryCategory.getMultiplier());
        this.f.setSizeMultiplier(memoryCategory.getMultiplier());
        MemoryCategory memoryCategory2 = this.o;
        this.o = memoryCategory;
        return memoryCategory2;
    }

    private static RequestManagerRetriever c(Context context) {
        Preconditions.checkNotNull(context, "You cannot start a load on a not yet attached View or a Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).");
        return get(context).getRequestManagerRetriever();
    }

    public static RequestManager with(Context context) {
        return c(context).get(context);
    }

    public static RequestManager with(Activity activity) {
        return c(activity).get(activity);
    }

    public static RequestManager with(FragmentActivity fragmentActivity) {
        return c(fragmentActivity).get(fragmentActivity);
    }

    public static RequestManager with(Fragment fragment) {
        return c(fragment.getActivity()).get(fragment);
    }

    @Deprecated
    public static RequestManager with(android.app.Fragment fragment) {
        return c(fragment.getActivity()).get(fragment);
    }

    public static RequestManager with(View view) {
        return c(view.getContext()).get(view);
    }

    public Registry getRegistry() {
        return this.j;
    }

    boolean a(Target<?> target) {
        synchronized (this.n) {
            Iterator<RequestManager> it = this.n.iterator();
            while (it.hasNext()) {
                if (it.next().a(target)) {
                    return true;
                }
            }
            return false;
        }
    }

    void a(RequestManager requestManager) {
        synchronized (this.n) {
            if (this.n.contains(requestManager)) {
                throw new IllegalStateException("Cannot register already registered manager");
            }
            this.n.add(requestManager);
        }
    }

    void b(RequestManager requestManager) {
        synchronized (this.n) {
            if (!this.n.contains(requestManager)) {
                throw new IllegalStateException("Cannot unregister not yet registered manager");
            }
            this.n.remove(requestManager);
        }
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        trimMemory(i);
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        clearMemory();
    }
}
