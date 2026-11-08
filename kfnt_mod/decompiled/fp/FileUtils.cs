using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using UnityEngine;

public class FileUtils : SingletonData<FileUtils>
{
	public static string[] supportedExtensions = new string[8] { "", ".bytes", ".png", ".asset", ".json", ".ogg", ".m4a", ".wav" };

	public static List<string> cloudAssetExtensions = new List<string> { ".bytes", ".json" };

	public const string FairyGUIExtension = "_fui";

	private const string cloudAssetBundlePrefix = "cloudbundle";

	private string _streamingAssetBasePath;

	private string _cloudAssetBasePath;

	private Dictionary<string, FileStruct> _files;

	private static AssetBundleManifest m_NativeAssetBundleManifest = null;

	private static AssetBundleManifest m_AssetBundleManifest = null;

	public static AssetBundleManifest NativeAssetBundleManifestObject
	{
		get
		{
			return m_NativeAssetBundleManifest;
		}
		set
		{
			m_NativeAssetBundleManifest = value;
		}
	}

	public static AssetBundleManifest AssetBundleManifestObject
	{
		get
		{
			return m_AssetBundleManifest;
		}
		set
		{
			m_AssetBundleManifest = value;
		}
	}

	public static bool supportCloudAsset(string file)
	{
		if (file.StartsWith("cloudbundle"))
		{
			return cloudAssetExtensions.Contains(Path.GetExtension(file));
		}
		return false;
	}

	protected void LoadFilePaths(Dictionary<string, FileStruct> files, string path)
	{
		string[] array = Resources.Load(path).toString().Split(new char[1] { '\n' });
		for (int i = 0; i < array.Length; i++)
		{
			string[] array2 = array[i].Split(new char[1] { ',' });
			if (array2.Length == 4)
			{
				FilePathType filePathType = (FilePathType)array2[1].toInt();
				if (filePathType != FilePathType.LocalBundleSourceAssets && filePathType != FilePathType.CloudBundleSourceAssets)
				{
					FileStruct value = default(FileStruct);
					value.name = array2[0];
					value.pathType = filePathType;
					value.path = array2[2];
					files.Add(value.name, value);
				}
			}
		}
	}

	public void LoadFromFileAsync<T>(string fileName, Action<T> callback) where T : Object
	{
		//IL_00a1: Unknown result type (might be due to invalid IL or missing references)
		//IL_00ac: Unknown result type (might be due to invalid IL or missing references)
		Debug.Log((object)("LoadFromFileAsync fileName:" + fileName));
		FilePathType type;
		string relativePathForFile = GetRelativePathForFile(fileName, out type);
		Debug.Log((object)("LoadFromFileAsync path:" + relativePathForFile));
		if (relativePathForFile == null)
		{
			callback(default(T));
		}
		switch (type)
		{
		case FilePathType.Resources:
			((MonoBehaviour)CoroutineUtils.Instance).StartCoroutine(LoadResourcesAsync(relativePathForFile, callback));
			return;
		case FilePathType.StreamingAssets:
		{
			string fullPathForFile = GetFullPathForFile(relativePathForFile, type);
			if (fullPathForFile == null)
			{
				callback(default(T));
			}
			((MonoBehaviour)CoroutineUtils.Instance).StartCoroutine(LoadLocalAssetBundleAssetAsync(fullPathForFile, callback));
			return;
		}
		case FilePathType.LocalBundleSourceAssets:
		case FilePathType.CloudBundleSourceAssets:
			return;
		}
		if (CommonConfig.supportHotUpdater)
		{
			if (m_NativeAssetBundleManifest.GetAssetBundleHash(relativePathForFile) == m_AssetBundleManifest.GetAssetBundleHash(relativePathForFile))
			{
				string fullPathForFile2 = GetFullPathForFile(relativePathForFile, FilePathType.StreamingAssets);
				if (fullPathForFile2 == null)
				{
					callback(default(T));
				}
				((MonoBehaviour)CoroutineUtils.Instance).StartCoroutine(LoadLocalAssetBundleAssetAsync(fullPathForFile2, callback));
			}
			else
			{
				Debug.Log((object)"LoadFromFileAsync LoadCloudAssetBundleAssetAsync");
				((MonoBehaviour)CoroutineUtils.Instance).StartCoroutine(LoadCloudAssetBundleAssetAsync(relativePathForFile, callback));
			}
		}
		else
		{
			string fullPathForFile3 = GetFullPathForFile(relativePathForFile, type);
			if (fullPathForFile3 == null)
			{
				callback(default(T));
			}
			((MonoBehaviour)CoroutineUtils.Instance).StartCoroutine(LoadLocalAssetBundleAssetAsync(fullPathForFile3, callback));
		}
	}

	private IEnumerator LoadResourcesAsync<T>(string path, Action<T> callback) where T : Object
	{
		ResourceRequest request = Resources.LoadAsync(path);
		yield return request;
		Object asset = request.asset;
		callback((T)(object)((asset is T) ? asset : null));
	}

	private IEnumerator LoadLocalAssetBundleAssetAsync<T>(string path, Action<T> callback) where T : Object
	{
		AssetBundleCreateRequest abRequest = AssetBundle.LoadFromFileAsync(path);
		yield return abRequest;
		AssetBundle ab = abRequest.assetBundle;
		if ((Object)(object)ab == (Object)null)
		{
			Debug.LogWarningFormat("Failed to load AssetBundle: {0}", new object[1] { path });
			yield break;
		}
		AssetBundleRequest request = ab.LoadAssetAsync(Path.GetFileName(path));
		yield return request;
		Object asset = request.asset;
		callback((T)(object)((asset is T) ? asset : null));
		ab.Unload(false);
	}

	private IEnumerator LoadCloudAssetBundleAssetAsync<T>(string path, Action<T> callback) where T : Object
	{
		string fullPathForFile = GetFullPathForFile(path, FilePathType.CloudAssets);
		Debug.Log((object)("LoadCloudAssetBundleAssetAsync url:" + fullPathForFile));
		if (fullPathForFile == null)
		{
			callback(default(T));
			Debug.LogWarningFormat("Failed to load AssetBundle: {0}", new object[1] { path });
			yield break;
		}
		WWW download = WWW.LoadFromCacheOrDownload(fullPathForFile, m_AssetBundleManifest.GetAssetBundleHash(path), 0u);
		yield return download;
		if (download.error != null)
		{
			Debug.LogWarningFormat("Failed to load AssetBundle: {0}", new object[1] { path });
			callback(default(T));
		}
		else
		{
			AssetBundle bundle = download.assetBundle;
			if ((Object)(object)bundle == (Object)null)
			{
				Debug.LogWarningFormat("Failed to load AssetBundle: {0}", new object[1] { path });
				callback(default(T));
			}
			else
			{
				AssetBundleRequest request = bundle.LoadAssetAsync(Path.GetFileName(path));
				yield return request;
				Object asset = request.asset;
				callback((T)(object)((asset is T) ? asset : null));
				bundle.Unload(false);
			}
		}
		download.Dispose();
	}

	public T LoadFromFile<T>(string fileName) where T : Object
	{
		FilePathType type;
		string relativePathForFile = GetRelativePathForFile(fileName, out type);
		if (relativePathForFile == null)
		{
			Debug.LogErrorFormat("Can't find file name {0}", new object[1] { fileName });
			return default(T);
		}
		if (type == FilePathType.Resources)
		{
			return Resources.Load<T>(relativePathForFile);
		}
		string fullPathForFile = GetFullPathForFile(relativePathForFile, type);
		if (fullPathForFile == null)
		{
			return default(T);
		}
		if (type == FilePathType.LocalBundleSourceAssets || type == FilePathType.CloudBundleSourceAssets)
		{
			return default(T);
		}
		AssetBundle val = AssetBundle.LoadFromFile(fullPathForFile);
		if ((Object)(object)val == (Object)null)
		{
			Debug.LogErrorFormat("Can't find assetbundle name {0}", new object[1] { fullPathForFile });
		}
		T[] array = val.LoadAllAssets<T>();
		if (array.Length != 0)
		{
			return array[0];
		}
		Debug.LogErrorFormat("Can't find assetbundle asset {0}", new object[1] { fileName });
		return default(T);
	}

	public AssetBundle LoadBundleFromFile(string fileName)
	{
		string fullPath = GetFullPath(fileName);
		if (fullPath == null)
		{
			return null;
		}
		return AssetBundle.LoadFromFile(fullPath);
	}

	public void LoadBundleFromFileAsync(string fileName, Action<AssetBundle> callback)
	{
		string fullPath = GetFullPath(fileName);
		if (fullPath == null)
		{
			callback(null);
		}
		else
		{
			((MonoBehaviour)CoroutineUtils.Instance).StartCoroutine(LoadAssetBundleAsync(fullPath, callback));
		}
	}

	private IEnumerator LoadAssetBundleAsync(string fullPath, Action<AssetBundle> callback)
	{
		AssetBundleCreateRequest abRequest = AssetBundle.LoadFromFileAsync(fullPath);
		yield return abRequest;
		AssetBundle assetBundle = abRequest.assetBundle;
		if ((Object)(object)assetBundle == (Object)null)
		{
			Debug.LogWarningFormat("Failed to load AssetBundle: {0}", new object[1] { fullPath });
		}
		else
		{
			callback(assetBundle);
		}
	}

	public string GetFullPath(string fileName)
	{
		FilePathType type;
		string relativePathForFile = GetRelativePathForFile(fileName, out type);
		if (relativePathForFile == null)
		{
			return null;
		}
		return GetFullPathForFile(relativePathForFile, type);
	}

	public string GetRelativePathForFile(string fileName, out FilePathType type)
	{
		if (string.IsNullOrEmpty(fileName))
		{
			type = FilePathType.Resources;
			return null;
		}
		Debug.Log((object)("try to find fileName: " + fileName));
		if (fileName.EndsWith(".plist"))
		{
			fileName = fileName.Replace(".plist", ".json");
		}
		if (_files.TryGetValue(fileName, out var value))
		{
			type = value.pathType;
			return value.path;
		}
		if (_files.TryGetValue(fileName.ToLower(), out value))
		{
			type = value.pathType;
			return value.path;
		}
		type = FilePathType.Resources;
		Debug.LogWarningFormat("File: {0} not exist", new object[1] { fileName });
		return null;
	}

	public string GetFullPathForFile(string relativePath, FilePathType type = FilePathType.Resources)
	{
		switch (type)
		{
		case FilePathType.Resources:
			return relativePath;
		case FilePathType.LocalBundleSourceAssets:
			return Path.Combine("Assets/AssetBundle", relativePath);
		case FilePathType.CloudBundleSourceAssets:
			return Path.Combine("Assets/CloudBundle", relativePath);
		default:
		{
			string text = null;
			switch (type)
			{
			case FilePathType.StreamingAssets:
				text = Path.Combine(_streamingAssetBasePath, relativePath);
				break;
			case FilePathType.CloudAssets:
				text = ((!CommonConfig.supportHotUpdater) ? Path.Combine(_streamingAssetBasePath, relativePath) : Path.Combine(_cloudAssetBasePath, relativePath));
				break;
			}
			if (text == null)
			{
				Debug.LogWarningFormat("File: {0} not exist in PathType: {1}, fullPath: {2}", new object[3] { relativePath, type, text });
				return null;
			}
			return text;
		}
		}
	}

	public static string GetPlatformName()
	{
		//IL_0000: Unknown result type (might be due to invalid IL or missing references)
		return GetPlatformForAssetBundles(Application.platform);
	}

	private static string GetPlatformForAssetBundles(RuntimePlatform platform)
	{
		//IL_0000: Unknown result type (might be due to invalid IL or missing references)
		//IL_0002: Invalid comparison between Unknown and I4
		//IL_000e: Unknown result type (might be due to invalid IL or missing references)
		//IL_0010: Invalid comparison between Unknown and I4
		//IL_0004: Unknown result type (might be due to invalid IL or missing references)
		//IL_0006: Invalid comparison between Unknown and I4
		//IL_0012: Unknown result type (might be due to invalid IL or missing references)
		//IL_0015: Invalid comparison between Unknown and I4
		//IL_0008: Unknown result type (might be due to invalid IL or missing references)
		//IL_000a: Invalid comparison between Unknown and I4
		//IL_0017: Unknown result type (might be due to invalid IL or missing references)
		//IL_001a: Invalid comparison between Unknown and I4
		if ((int)platform <= 2)
		{
			if ((int)platform == 1)
			{
				return "OSX";
			}
			if ((int)platform == 2)
			{
				return "Windows";
			}
		}
		else
		{
			if ((int)platform == 8)
			{
				return "iOS";
			}
			if ((int)platform == 11)
			{
				return "Android";
			}
			if ((int)platform == 17)
			{
				return "WebGL";
			}
		}
		return null;
	}

	protected override void OnInit()
	{
		_streamingAssetBasePath = Path.Combine(Application.dataPath + "!assets", GetPlatformName());
		string path = "VersionFiles/FileStruct";
		_files = new Dictionary<string, FileStruct>();
		LoadFilePaths(_files, path);
		AssetBundle obj = LoadBundleFromFile(GetPlatformName());
		m_NativeAssetBundleManifest = obj.LoadAsset<AssetBundleManifest>("AssetBundleManifest");
		obj.Unload(false);
	}

	public void InitCloudUrl()
	{
		_cloudAssetBasePath = CommonConfig.CdnUrl + GetPlatformName();
	}

	public static string spineFileName(string prefix)
	{
		return prefix + ".skel.bytes";
	}

	public static string spineAtlasName(string prefix)
	{
		return prefix + ".atlas.txt";
	}

	public static string spineMatName(string prefix)
	{
		return prefix + "_Material.mat";
	}

	public static string spineSkeletonDataName(string prefix)
	{
		return prefix + "_SkeletonData.asset";
	}
}
You are not using the latest version of the tool, please update.
Latest version is '11.2.0.10050' (yours is '8.2.0.7535-95108c96')
