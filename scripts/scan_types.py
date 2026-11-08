"""扫描分析版DLL: 按关键词列类型, 抽取US堆里的URL/域名"""
import sys, re
sys.path.insert(0, r'D:\My-Best-Konfu\scripts\pylibs')
import dnfile

KEYS = ['net', 'http', 'server', 'login', 'update', 'hot', 'sdk', 'ad', 'pay',
        'version', 'report', 'stat', 'account', 'auth', 'real', 'channel',
        'config', 'remote', 'url', 'cdn', 'patch', 'check', 'web', 'request']

def scan(path):
    print('==== %s ====' % path.split('\\')[-1])
    pe = dnfile.dnPE(path)
    md = pe.net.mdtables
    # 1) 类型名关键词
    hits = []
    for t in md.TypeDef.rows:
        full = (t.TypeNamespace + '.' if t.TypeNamespace else '') + t.TypeName
        low = full.lower()
        if any(k in low for k in KEYS):
            hits.append(full)
    print('-- 关键词类型 (%d) --' % len(hits))
    for h in hits[:80]:
        print('  ', h)
    # 2) US堆 URL
    us = pe.net.user_strings
    urls = set()
    raw = pe.net.metadata.streams.get(b'#US') or pe.net.metadata.streams.get('#US')
    if raw is None:
        for name, s in pe.net.metadata.streams.items():
            if getattr(s, 'struct', None) and getattr(s.struct, 'Name', b'') == b'#US':
                raw = s
    if raw is not None:
        data = raw.__data__ if hasattr(raw, '__data__') else b''
        for m in re.finditer(rb'(?:https?|wss?|ftp)://[\x20-\x7e]{4,120}', data):
            urls.add(m.group(0).decode('ascii', 'replace').rstrip())
    print('-- US堆 URL (%d) --' % len(urls))
    for u in sorted(urls)[:60]:
        print('  ', u)

scan(sys.argv[1])
if len(sys.argv) > 2:
    scan(sys.argv[2])
