#!/bin/bash
# 下载逆向工具链到 D:\RETools (全部GitHub/官方直链)
set -x
R=/d/RETools
mkdir -p $R/{jdk,jadx,apktool,signer,platform-tools,dnspy,tmp}
UA="Mozilla/5.0 (Windows NT 10.0; Win64; x64)"
cd $R/tmp

# 1. Temurin JDK 21 (windows x64 zip)
curl -sL -A "$UA" -o jdk21.zip "https://api.adoptium.net/v3/binary/latest/21/ga/windows/x64/jdk/hotspot/normal/eclipse" && \
  unzip -q jdk21.zip -d $R/jdk && echo "JDK OK"

# 2. jadx (DEX反编译)
curl -sL -A "$UA" -o jadx.zip "https://github.com/skylot/jadx/releases/download/v1.5.3/jadx-1.5.3.zip" && \
  unzip -q jadx.zip -d $R/jadx && echo "JADX OK"

# 3. apktool 最新 release jar (用API拿资产名)
APKTOOL_URL=$(curl -sL -A "$UA" "https://api.github.com/repos/iBotPeaches/Apktool/releases/latest" | grep -oE "https://[^ ]*apktool_[^ ]*\.jar" | head -1)
curl -sL -A "$UA" -o $R/apktool/apktool.jar "$APKTOOL_URL" && echo "APKTOOL OK: $APKTOOL_URL"

# 4. uber-apk-signer (签名+zipalign一体)
curl -sL -A "$UA" -o $R/signer/uber-apk-signer.jar "https://github.com/patrickfav/uber-apk-signer/releases/download/v1.3.0/uber-apk-signer-1.3.0.jar" && echo "SIGNER OK"

# 5. Android platform-tools (adb/fastboot)
curl -sL -A "$UA" -o pt.zip "https://dl.google.com/android/repository/platform-tools-latest-windows.zip" && \
  unzip -q pt.zip -d $R/platform-tools && echo "PLATFORM-TOOLS OK"

# 6. dnSpyEx (Mono DLL 反编译/修改 GUI)
curl -sL -A "$UA" -o dnspy.zip "https://github.com/dnSpyEx/dnSpy/releases/download/v6.5.1/dnSpy-net-win64.zip" && \
  unzip -q dnspy.zip -d $R/dnspy && echo "DNSPY OK"

# 7. jadx-mcp-plugin (REA inspect_android_package 需要)
JADX_MCP_URL=$(curl -sL -A "$UA" "https://api.github.com/repos/zinja-coder/jadx-mcp-plugin/releases/latest" | grep -oE "https://[^ ]*\.jar" | head -1)
[ -n "$JADX_MCP_URL" ] && curl -sL -A "$UA" -o $R/jadx/jadx-mcp-plugin.jar "$JADX_MCP_URL" && echo "JADX-MCP OK: $JADX_MCP_URL" || echo "JADX-MCP: 需要手动找"

echo "=== 全部下载完成 ==="
ls -la $R/jdk $R/jadx $R/apktool $R/signer $R/platform-tools $R/dnspy
