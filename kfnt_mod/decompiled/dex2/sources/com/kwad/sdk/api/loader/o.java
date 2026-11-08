package com.kwad.sdk.api.loader;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import androidx.annotation.NonNull;
import com.kwad.sdk.api.loader.Reflect;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class o {
    private static Map<String, Resources> a = new HashMap();
    private static final Object b = new Object();

    private static final class a {
        /* JADX INFO: Access modifiers changed from: private */
        public static Resources b(Resources resources, AssetManager assetManager) {
            try {
                Resources resources2 = (Resources) Reflect.a(resources.getClass()).a(new Class[]{AssetManager.class, DisplayMetrics.class, Configuration.class}, assetManager, resources.getDisplayMetrics(), resources.getConfiguration()).a();
                if (resources2 != null) {
                    return resources2;
                }
                throw new RuntimeException("Can not create Resources");
            } catch (Exception unused) {
                return new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
            }
        }
    }

    private static final class b {
        /* JADX INFO: Access modifiers changed from: private */
        public static Resources b(Resources resources, AssetManager assetManager) {
            try {
                return c(resources, assetManager);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    return a.b(resources, assetManager);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
                }
            }
        }

        private static Resources c(Resources resources, AssetManager assetManager) {
            Resources resources2 = (Resources) Reflect.a("android.content.res.HwResources").a(new Class[]{AssetManager.class, DisplayMetrics.class, Configuration.class}, assetManager, resources.getDisplayMetrics(), resources.getConfiguration()).a();
            if (resources2 != null) {
                return resources2;
            }
            throw new RuntimeException("Can not create Resources");
        }
    }

    private static final class c {
        /* JADX INFO: Access modifiers changed from: private */
        public static Resources b(Resources resources, AssetManager assetManager) {
            try {
                return c(resources, assetManager);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    return a.b(resources, assetManager);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
                }
            }
        }

        private static Resources c(Resources resources, AssetManager assetManager) {
            Resources resources2 = (Resources) Reflect.a("android.content.res.MiuiResourcesImpl").a(new Class[]{AssetManager.class, DisplayMetrics.class, Configuration.class}, assetManager, resources.getDisplayMetrics(), resources.getConfiguration()).a();
            if (resources2 != null) {
                return resources2;
            }
            throw new RuntimeException("Can not create Resources");
        }
    }

    private static final class d {
        /* JADX INFO: Access modifiers changed from: private */
        public static Resources b(Resources resources, AssetManager assetManager) {
            try {
                return c(resources, assetManager);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    return a.b(resources, assetManager);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
                }
            }
        }

        private static Resources c(Resources resources, AssetManager assetManager) {
            Resources resources2 = (Resources) Reflect.a("android.content.res.MiuiResources").a(new Class[]{AssetManager.class, DisplayMetrics.class, Configuration.class}, assetManager, resources.getDisplayMetrics(), resources.getConfiguration()).a();
            if (resources2 != null) {
                return resources2;
            }
            throw new RuntimeException("Can not create Resources");
        }
    }

    private static final class e {
        /* JADX INFO: Access modifiers changed from: private */
        public static Resources b(Resources resources, AssetManager assetManager) {
            try {
                return c(resources, assetManager);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    return a.b(resources, assetManager);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
                }
            }
        }

        private static Resources c(Resources resources, AssetManager assetManager) {
            Resources resources2 = (Resources) Reflect.a("android.content.res.NubiaResources").a(new Class[]{AssetManager.class, DisplayMetrics.class, Configuration.class}, assetManager, resources.getDisplayMetrics(), resources.getConfiguration()).a();
            if (resources2 != null) {
                return resources2;
            }
            throw new RuntimeException("Can not create Resources");
        }
    }

    private static final class f {
        /* JADX INFO: Access modifiers changed from: private */
        public static Resources b(Context context, Resources resources, AssetManager assetManager) {
            try {
                return c(context, resources, assetManager);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    return a.b(resources, assetManager);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
                }
            }
        }

        private static Resources c(Context context, Resources resources, AssetManager assetManager) throws IllegalAccessException, IllegalArgumentException {
            Resources resources2 = (Resources) Reflect.a("android.content.res.VivoResources").a(new Class[]{AssetManager.class, DisplayMetrics.class, Configuration.class}, assetManager, resources.getDisplayMetrics(), resources.getConfiguration()).a();
            if (resources2 == null) {
                throw new RuntimeException("Can not create Resources");
            }
            try {
                Reflect.a(resources2).a("init", new Class[]{String.class}, context.getPackageName());
            } catch (Reflect.ReflectException e) {
                e.printStackTrace();
            }
            try {
                Reflect.a(resources2).a("mThemeValues", Reflect.a(resources).b("mThemeValues"));
            } catch (Reflect.ReflectException e2) {
                e2.printStackTrace();
            }
            return resources2;
        }
    }

    private static Resources a(Context context, AssetManager assetManager, DisplayMetrics displayMetrics, Configuration configuration) {
        Resources resources = context.getResources();
        return a(resources) ? d.b(resources, assetManager) : b(resources) ? c.b(resources, assetManager) : e(resources) ? b.b(resources, assetManager) : c(resources) ? f.b(context, resources, assetManager) : d(resources) ? e.b(resources, assetManager) : f(resources) ? a.b(resources, assetManager) : new Resources(assetManager, displayMetrics, configuration);
    }

    @NonNull
    static Resources a(Context context, Resources resources, String str) {
        Resources resourcesB;
        synchronized (b) {
            resourcesB = a.get(str);
            if (resourcesB == null) {
                resourcesB = b(context, resources, str);
                if (resourcesB == null) {
                    throw new RuntimeException("Can not createResources for " + str);
                }
                a.put(str, resources);
            }
        }
        return resourcesB;
    }

    public static void a(AssetManager assetManager, String str) {
        try {
            Reflect.a(assetManager).a("addOverlayPath", new Class[]{String.class}, str);
        } catch (Throwable unused) {
            Reflect.a(assetManager).a("addAssetPath", new Class[]{String.class}, str);
        }
    }

    private static boolean a(Resources resources) {
        return "android.content.res.MiuiResources".equals(resources.getClass().getName());
    }

    public static int b(AssetManager assetManager, String str) {
        Reflect reflectA;
        String str2;
        Class<?>[] clsArr;
        Object[] objArr;
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                reflectA = Reflect.a(assetManager);
                str2 = "addAssetPathAsSharedLibrary";
                clsArr = new Class[]{String.class};
                objArr = new Object[]{str};
            } else {
                reflectA = Reflect.a(assetManager);
                str2 = "addAssetPath";
                clsArr = new Class[]{String.class};
                objArr = new Object[]{str};
            }
            reflectA.a(str2, clsArr, objArr);
        } catch (Throwable unused) {
            Reflect.a(assetManager).a("addAssetPath", new Class[]{String.class}, str);
        }
        return 0;
    }

    private static Resources b(Context context, Resources resources, String str) {
        AssetManager assetManager = (AssetManager) Reflect.a((Class<?>) AssetManager.class).b().a();
        Reflect.a(assetManager).a("addAssetPath", new Class[]{String.class}, str);
        if (Build.VERSION.SDK_INT >= 21) {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            try {
                Object objB = Reflect.a(applicationInfo).b("resourceDirs");
                if (objB != null && objB.getClass().isArray()) {
                    Object[] objArr = (Object[]) objB;
                    if (objArr.length > 0) {
                        for (Object obj : objArr) {
                            if (obj instanceof String) {
                                a(assetManager, (String) obj);
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            try {
                if (applicationInfo.sharedLibraryFiles != null && applicationInfo.sharedLibraryFiles.length > 0) {
                    for (String str2 : applicationInfo.sharedLibraryFiles) {
                        if (str2 != null && str2.endsWith(".apk")) {
                            b(assetManager, str2);
                        }
                    }
                }
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
        return a(context, assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
    }

    private static boolean b(Resources resources) {
        return "android.content.res.MiuiResourcesImpl".equals(resources.getClass().getName());
    }

    private static boolean c(Resources resources) {
        return "android.content.res.VivoResources".equals(resources.getClass().getName());
    }

    private static boolean d(Resources resources) {
        return "android.content.res.NubiaResources".equals(resources.getClass().getName());
    }

    private static boolean e(Resources resources) {
        return "android.content.res.HwResources".equals(resources.getClass().getName());
    }

    private static boolean f(Resources resources) {
        return !"android.content.res.Resources".equals(resources.getClass().getName());
    }
}
