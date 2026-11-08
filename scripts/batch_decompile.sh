#!/bin/bash
# 批量反编译核心类型
export DOTNET_ROOT="D:\\RETools\\dotnet"
export DOTNET_ROLL_FORWARD=Major
I=/d/RETools/tools/ilspycmd.exe
DLL="D:\\My-Best-Konfu\\kfnt_mod\\analysis\\Assembly-CSharp.analysis.dll"
DLL2="D:\\My-Best-Konfu\\kfnt_mod\\analysis\\Assembly-CSharp-firstpass.analysis.dll"
OUT=/d/My-Best-Konfu/kfnt_mod/decompiled/ac
OUT2=/d/My-Best-Konfu/kfnt_mod/decompiled/fp
mkdir -p $OUT $OUT2

for T in UserBaseDataHandler AccountObject DouyinAccountObject AccountInfoManager LGSDKManager LoginObject LoginHttp LoginGetObject LoginStatus ArchiveGetObject ArchivePutObject LoadingWindow LoadingController DataHandlerObject; do
  $I -t $T "$DLL" > $OUT/$T.cs 2>/dev/null && echo "OK $T ($(wc -l < $OUT/$T.cs) lines)" || echo "MISS $T"
done

for T in LGSDKManager CommonConfig GameLib.CacheUtils FileUtils; do
  $I -t $T "$DLL2" > $OUT2/$T.cs 2>/dev/null && echo "OK(fp) $T ($(wc -l < $OUT2/$T.cs) lines)" || echo "MISS(fp) $T"
done
