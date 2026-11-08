package kotlin.coroutines.experimental.migration;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.b.a.d;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007\u001a\f\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0007\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\tH\u0007\u001a\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0007\u001a\f\u0010\u000b\u001a\u00020\u0006*\u00020\u0005H\u0007\u001a\f\u0010\f\u001a\u00020\t*\u00020\bH\u0007\u001a^\u0010\r\u001a\"\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000e\"\u0004\b\u0000\u0010\u000f\"\u0004\b\u0001\u0010\u0010\"\u0004\b\u0002\u0010\u0011*\"\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000eH\u0000\u001aL\u0010\r\u001a\u001c\u0012\u0004\u0012\u0002H\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0013\"\u0004\b\u0000\u0010\u000f\"\u0004\b\u0001\u0010\u0011*\u001c\u0012\u0004\u0012\u0002H\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0013H\u0000\u001a:\u0010\r\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0014\"\u0004\b\u0000\u0010\u0011*\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0014H\u0000¨\u0006\u0015"}, d2 = {"toContinuation", "Lkotlin/coroutines/Continuation;", "T", "Lkotlin/coroutines/experimental/Continuation;", "toContinuationInterceptor", "Lkotlin/coroutines/ContinuationInterceptor;", "Lkotlin/coroutines/experimental/ContinuationInterceptor;", "toCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "Lkotlin/coroutines/experimental/CoroutineContext;", "toExperimentalContinuation", "toExperimentalContinuationInterceptor", "toExperimentalCoroutineContext", "toExperimentalSuspendFunction", "Lkotlin/Function3;", "T1", "T2", "R", "", "Lkotlin/Function2;", "Lkotlin/Function1;", "kotlin-stdlib-coroutines"}, k = 2, mv = {1, 1, 15})
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class CoroutinesMigrationKt {
    @SinceKotlin(version = "1.3")
    @d
    public static final <T> Continuation<T> toExperimentalContinuation(@d kotlin.coroutines.Continuation<? super T> continuation) {
        Continuation<T> continuation2;
        Intrinsics.checkParameterIsNotNull(continuation, "$this$toExperimentalContinuation");
        ContinuationMigration continuationMigration = (ContinuationMigration) (!(continuation instanceof ContinuationMigration) ? null : continuation);
        return (continuationMigration == null || (continuation2 = continuationMigration.getContinuation()) == null) ? new ExperimentalContinuationMigration(continuation) : continuation2;
    }

    @SinceKotlin(version = "1.3")
    @d
    public static final <T> kotlin.coroutines.Continuation<T> toContinuation(@d Continuation<? super T> continuation) {
        kotlin.coroutines.Continuation<T> continuation2;
        Intrinsics.checkParameterIsNotNull(continuation, "$this$toContinuation");
        ExperimentalContinuationMigration experimentalContinuationMigration = (ExperimentalContinuationMigration) (!(continuation instanceof ExperimentalContinuationMigration) ? null : continuation);
        return (experimentalContinuationMigration == null || (continuation2 = experimentalContinuationMigration.getContinuation()) == null) ? new ContinuationMigration(continuation) : continuation2;
    }

    @SinceKotlin(version = "1.3")
    @d
    public static final CoroutineContext toExperimentalCoroutineContext(@d kotlin.coroutines.CoroutineContext coroutineContext) {
        EmptyCoroutineContext emptyCoroutineContextPlus;
        Intrinsics.checkParameterIsNotNull(coroutineContext, "$this$toExperimentalCoroutineContext");
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) coroutineContext.get(ContinuationInterceptor.INSTANCE);
        ContextMigration contextMigration = (ContextMigration) coroutineContext.get(ContextMigration.INSTANCE);
        kotlin.coroutines.CoroutineContext coroutineContextMinusKey = coroutineContext.minusKey(ContinuationInterceptor.INSTANCE).minusKey(ContextMigration.INSTANCE);
        if (contextMigration == null || (emptyCoroutineContextPlus = contextMigration.getContext()) == null) {
            emptyCoroutineContextPlus = EmptyCoroutineContext.INSTANCE;
        }
        if (coroutineContextMinusKey != kotlin.coroutines.EmptyCoroutineContext.INSTANCE) {
            emptyCoroutineContextPlus = emptyCoroutineContextPlus.plus(new ExperimentalContextMigration(coroutineContextMinusKey));
        }
        return continuationInterceptor == null ? emptyCoroutineContextPlus : emptyCoroutineContextPlus.plus(toExperimentalContinuationInterceptor(continuationInterceptor));
    }

    @SinceKotlin(version = "1.3")
    @d
    public static final kotlin.coroutines.CoroutineContext toCoroutineContext(@d CoroutineContext coroutineContext) {
        kotlin.coroutines.EmptyCoroutineContext emptyCoroutineContextPlus;
        Intrinsics.checkParameterIsNotNull(coroutineContext, "$this$toCoroutineContext");
        kotlin.coroutines.experimental.ContinuationInterceptor continuationInterceptor = (kotlin.coroutines.experimental.ContinuationInterceptor) coroutineContext.get(kotlin.coroutines.experimental.ContinuationInterceptor.INSTANCE);
        ExperimentalContextMigration experimentalContextMigration = (ExperimentalContextMigration) coroutineContext.get(ExperimentalContextMigration.INSTANCE);
        CoroutineContext coroutineContextMinusKey = coroutineContext.minusKey(kotlin.coroutines.experimental.ContinuationInterceptor.INSTANCE).minusKey(ExperimentalContextMigration.INSTANCE);
        if (experimentalContextMigration == null || (emptyCoroutineContextPlus = experimentalContextMigration.getContext()) == null) {
            emptyCoroutineContextPlus = kotlin.coroutines.EmptyCoroutineContext.INSTANCE;
        }
        if (coroutineContextMinusKey != EmptyCoroutineContext.INSTANCE) {
            emptyCoroutineContextPlus = emptyCoroutineContextPlus.plus(new ContextMigration(coroutineContextMinusKey));
        }
        return continuationInterceptor == null ? emptyCoroutineContextPlus : emptyCoroutineContextPlus.plus(toContinuationInterceptor(continuationInterceptor));
    }

    @SinceKotlin(version = "1.3")
    @d
    public static final kotlin.coroutines.experimental.ContinuationInterceptor toExperimentalContinuationInterceptor(@d ContinuationInterceptor continuationInterceptor) {
        kotlin.coroutines.experimental.ContinuationInterceptor interceptor;
        Intrinsics.checkParameterIsNotNull(continuationInterceptor, "$this$toExperimentalContinuationInterceptor");
        ContinuationInterceptorMigration continuationInterceptorMigration = (ContinuationInterceptorMigration) (!(continuationInterceptor instanceof ContinuationInterceptorMigration) ? null : continuationInterceptor);
        return (continuationInterceptorMigration == null || (interceptor = continuationInterceptorMigration.getInterceptor()) == null) ? new ExperimentalContinuationInterceptorMigration(continuationInterceptor) : interceptor;
    }

    @SinceKotlin(version = "1.3")
    @d
    public static final ContinuationInterceptor toContinuationInterceptor(@d kotlin.coroutines.experimental.ContinuationInterceptor continuationInterceptor) {
        ContinuationInterceptor interceptor;
        Intrinsics.checkParameterIsNotNull(continuationInterceptor, "$this$toContinuationInterceptor");
        ExperimentalContinuationInterceptorMigration experimentalContinuationInterceptorMigration = (ExperimentalContinuationInterceptorMigration) (!(continuationInterceptor instanceof ExperimentalContinuationInterceptorMigration) ? null : continuationInterceptor);
        return (experimentalContinuationInterceptorMigration == null || (interceptor = experimentalContinuationInterceptorMigration.getInterceptor()) == null) ? new ContinuationInterceptorMigration(continuationInterceptor) : interceptor;
    }

    @d
    public static final <R> Function1<Continuation<? super R>, Object> toExperimentalSuspendFunction(@d Function1<? super kotlin.coroutines.Continuation<? super R>, ? extends Object> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "$this$toExperimentalSuspendFunction");
        return new ExperimentalSuspendFunction0Migration(function1);
    }

    @d
    public static final <T1, R> Function2<T1, Continuation<? super R>, Object> toExperimentalSuspendFunction(@d Function2<? super T1, ? super kotlin.coroutines.Continuation<? super R>, ? extends Object> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "$this$toExperimentalSuspendFunction");
        return new ExperimentalSuspendFunction1Migration(function2);
    }

    @d
    public static final <T1, T2, R> Function3<T1, T2, Continuation<? super R>, Object> toExperimentalSuspendFunction(@d Function3<? super T1, ? super T2, ? super kotlin.coroutines.Continuation<? super R>, ? extends Object> function3) {
        Intrinsics.checkParameterIsNotNull(function3, "$this$toExperimentalSuspendFunction");
        return new ExperimentalSuspendFunction2Migration(function3);
    }
}
