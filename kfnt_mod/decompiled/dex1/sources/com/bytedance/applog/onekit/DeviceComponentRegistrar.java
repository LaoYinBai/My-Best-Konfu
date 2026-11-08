package com.bytedance.applog.onekit;

import android.text.TextUtils;
import com.bytedance.a.ab;
import com.bytedance.a.w;
import com.bytedance.applog.AppLog;
import com.volcengine.onekit.component.Component;
import com.volcengine.onekit.component.ComponentContainer;
import com.volcengine.onekit.component.ComponentFactory;
import com.volcengine.onekit.component.ComponentRegistrar;
import com.volcengine.onekit.component.Dependency;
import com.volcengine.onekit.service.Analytics;
import com.volcengine.onekit.service.Device;
import java.util.Arrays;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class DeviceComponentRegistrar implements ComponentRegistrar {

    public class a implements ComponentFactory<Device> {
        public a(DeviceComponentRegistrar deviceComponentRegistrar) {
        }

        public Object create(ComponentContainer componentContainer) {
            if (!TextUtils.isEmpty(AppLog.getDid())) {
                return new ab();
            }
            AppLog.addDataObserver(new w(this, componentContainer));
            return null;
        }
    }

    public List<Component> getComponents() {
        return Arrays.asList(Component.builder(Device.class, new Class[0]).addDependency(Dependency.required(Analytics.class)).enablePrivacy().factory(new a(this)).build());
    }
}
