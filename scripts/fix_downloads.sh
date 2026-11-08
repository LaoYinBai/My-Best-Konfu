#!/bin/bash
# 补齐缺失工具下载
set -x
UA="Mozilla/5.0 (Windows NT 10.0; Win64; x64)"
T=/d/RETools/tmp
R=/d/RETools
cd $T

# platform-tools
unzip -q -o pt.zip -d $R/platform-tools && echo PT_OK
# jadx
curl -sL --retry 2 -A "$UA" -o jadx.zip "https://github.com/skylot/jadx/releases/download/v1.5.3/jadx-1.5.3.zip" && unzip -q -o jadx.zip -d $R/jadx && echo JADX_OK
# apktool (固定已知版本)
curl -sL --retry 2 -A "$UA" -o $R/apktool/apktool.jar "https://github.com/iBotPeaches/Apktool/releases/download/v2.11.1/apktool_2.11.1.jar" && echo APKTOOL_OK
# uber-apk-signer
curl -sL --retry 2 -A "$UA" -o $R/signer/uber-apk-signer.jar "https://github.com/patrickfav/uber-apk-signer/releases/download/v1.3.0/uber-apk-signer-1.3.0.jar" && echo SIGNER_OK
# dnSpyEx
curl -sL --retry 2 -A "$UA" -o dnspy.zip "https://github.com/dnSpyEx/dnSpy/releases/download/v6.5.1/dnSpy-net-win64.zip" && unzip -q -o dnspy.zip -d $R/dnspy && echo DNSPY_OK
# .NET SDK (ilspycmd 安装需要)
SDK_URL=$(curl -sL --max-time 30 "https://builds.dotnet.microsoft.com/dotnet/release-metadata/8.0/releases.json" | grep -oE "https://[^ \"]*dotnet-sdk-8\.0\.[0-9]+-win-x64\.zip" | head -1)
echo "SDK: $SDK_URL"
curl -sL --retry 2 -A "$UA" -o sdk.zip "$SDK_URL" && unzip -q -o sdk.zip -d $R/dotnet && echo SDK_OK
echo ALL_DONE
