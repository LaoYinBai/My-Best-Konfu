"""dump关键类的成员 + 全方法名关键词搜索 + US堆URL提取(自解析元数据根)"""
import sys, re, struct
sys.path.insert(0, r'D:\My-Best-Konfu\scripts\pylibs')
import dnfile

FOCUS = ['AccountInfoManager', 'AccountObject', 'DouyinAccountObject', 'AdsManager',
         'LoadingController', 'LGSDKManager', 'CommonConfig', 'VersionHelper',
         'VersionFileInfo', 'VersionFileModel', 'LGAccountService', 'LGAndroidAccount',
         'GameConfig', 'SdkConfig', 'ServerList', 'NoticeManager', 'Announcement']

MKEYS = ['login', 'logout', 'update', 'download', 'checkversion', 'serverlist',
         'init', 'request', 'url', 'hotupdate', 'getconfig', 'notice', 'realname']

def us_urls(pe):
    out = set()
    root = pe.net.metadata
    base_rva = root.struct.Rva if hasattr(root.struct, 'Rva') else None
    # 直接从文件字节定位: 用 pefile 拿 metadata 数据
    data = None
    for s in root.streams_list:
        if s.struct.Name == b'#US':
            data = s.get_data_at_offset(0, s.struct.Size) if hasattr(s, 'get_data_at_offset') else None
            if data is None and hasattr(s, '__data__'):
                data = s.__data__
    if not data:
        return out
    for m in re.finditer(rb'(?:https?|wss?)://[\x21-\x7e]{4,140}', data):
        out.add(m.group(0).decode('ascii', 'replace').rstrip())
    return out

def main(path):
    pe = dnfile.dnPE(path)
    md = pe.net.mdtables
    print('######## %s ########' % path)
    # 关键类成员
    for t in md.TypeDef.rows:
        full = (t.TypeNamespace + '.' if t.TypeNamespace else '') + t.TypeName
        if full in FOCUS or t.TypeName in FOCUS:
            print('== 类 %s ==' % full)
            for m in t.MethodList:
                try:
                    r = m.row
                    print('   .method %s  (IL 0x%x)' % (r.Name, r.Rva))
                except Exception:
                    pass
            for f in t.FieldList:
                try:
                    print('   .field %s' % f.row.Name)
                except Exception:
                    pass
    # 方法名关键词
    print('-- 方法名关键词命中 (前60) --')
    cnt = 0
    seen = set()
    for t in md.TypeDef.rows:
        full = (t.TypeNamespace + '.' if t.TypeNamespace else '') + t.TypeName
        for m in t.MethodList:
            try:
                n = m.row.Name
            except Exception:
                continue
            low = n.lower()
            if any(k in low for k in MKEYS) and (full, n) not in seen:
                seen.add((full, n))
                print('   %s.%s' % (full, n))
                cnt += 1
                if cnt >= 60:
                    break
        if cnt >= 60:
            break
    urls = us_urls(pe)
    print('-- US堆URL (%d) --' % len(urls))
    for u in sorted(urls)[:50]:
        print('  ', u)

main(sys.argv[1])
if len(sys.argv) > 2:
    main(sys.argv[2])
