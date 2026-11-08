package com.google.android.gms.common.moduleinstall;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-base@@18.1.0 */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class ModuleInstallRequest {
    private final List zaa;

    @Nullable
    private final InstallStatusListener zab;

    @Nullable
    private final Executor zac;
    private final boolean zad;

    /* compiled from: com.google.android.gms:play-services-base@@18.1.0 */
    public static class Builder {
        private final List zaa = new ArrayList();
        private boolean zab = true;

        @Nullable
        private InstallStatusListener zac;

        @Nullable
        private Executor zad;

        @NonNull
        @CanIgnoreReturnValue
        public Builder addApi(@NonNull OptionalModuleApi optionalModuleApi) {
            this.zaa.add(optionalModuleApi);
            return this;
        }

        @NonNull
        public ModuleInstallRequest build() {
            return new ModuleInstallRequest(this.zaa, this.zac, this.zad, this.zab, null);
        }

        @NonNull
        public Builder setListener(@NonNull InstallStatusListener installStatusListener) {
            return setListener(installStatusListener, null);
        }

        @NonNull
        @CanIgnoreReturnValue
        public Builder setListener(@NonNull InstallStatusListener installStatusListener, @Nullable Executor executor) {
            this.zac = installStatusListener;
            this.zad = executor;
            return this;
        }
    }

    /* synthetic */ ModuleInstallRequest(List list, InstallStatusListener installStatusListener, Executor executor, boolean z, zac zacVar) {
        Preconditions.checkNotNull(list, "APIs must not be null.");
        Preconditions.checkArgument(!list.isEmpty(), "APIs must not be empty.");
        if (executor != null) {
            Preconditions.checkNotNull(installStatusListener, "Listener must not be null when listener executor is set.");
        }
        this.zaa = list;
        this.zab = installStatusListener;
        this.zac = executor;
        this.zad = z;
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder();
    }

    @NonNull
    public List<OptionalModuleApi> getApis() {
        return this.zaa;
    }

    @Nullable
    public InstallStatusListener getListener() {
        return this.zab;
    }

    @Nullable
    public Executor getListenerExecutor() {
        return this.zac;
    }

    @ShowFirstParty
    public final boolean zaa() {
        return this.zad;
    }
}
