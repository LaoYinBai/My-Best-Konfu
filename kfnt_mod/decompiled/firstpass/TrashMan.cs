using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class TrashMan : MonoBehaviour
{
	public static TrashMan instance;

	[HideInInspector]
	public List<TrashManRecycleBin> recycleBinCollection;

	public float cullExcessObjectsInterval = 10f;

	public bool persistBetweenScenes;

	private Dictionary<int, TrashManRecycleBin> _instanceIdToRecycleBin = new Dictionary<int, TrashManRecycleBin>();

	private Dictionary<string, int> _poolNameToInstanceId = new Dictionary<string, int>();

	[HideInInspector]
	public Transform transform;

	private void Awake()
	{
		if ((Object)(object)instance != (Object)null)
		{
			Object.Destroy((Object)(object)((Component)this).gameObject);
		}
		else
		{
			transform = ((Component)this).gameObject.transform;
			instance = this;
			initializePrefabPools();
			if (persistBetweenScenes)
			{
				Object.DontDestroyOnLoad((Object)(object)((Component)this).gameObject);
			}
		}
		if (cullExcessObjectsInterval > 0f)
		{
			((MonoBehaviour)this).StartCoroutine(cullExcessObjects());
		}
		SceneManager.activeSceneChanged += activeSceneChanged;
	}

	private void OnDestroy()
	{
		foreach (TrashManRecycleBin item in recycleBinCollection)
		{
			item.clear();
		}
		recycleBinCollection.Clear();
		foreach (KeyValuePair<int, TrashManRecycleBin> item2 in _instanceIdToRecycleBin)
		{
			item2.Value.clear();
		}
		_instanceIdToRecycleBin.Clear();
		_poolNameToInstanceId.Clear();
		SceneManager.activeSceneChanged -= activeSceneChanged;
		if ((Object)(object)instance == (Object)(object)this)
		{
			instance = null;
		}
	}

	private void activeSceneChanged(Scene oldScene, Scene newScene)
	{
		if (((Scene)(ref oldScene)).name == null)
		{
			return;
		}
		for (int num = recycleBinCollection.Count - 1; num >= 0; num--)
		{
			if (!recycleBinCollection[num].persistBetweenScenes)
			{
				removeRecycleBin(recycleBinCollection[num]);
			}
		}
	}

	private void OnApplicationQuit()
	{
		instance = null;
	}

	private IEnumerator cullExcessObjects()
	{
		WaitForSeconds waiter = new WaitForSeconds(cullExcessObjectsInterval);
		while (true)
		{
			for (int i = 0; i < recycleBinCollection.Count; i++)
			{
				recycleBinCollection[i].cullExcessObjects();
			}
			yield return waiter;
		}
	}

	private void initializePrefabPools()
	{
		if (recycleBinCollection == null)
		{
			return;
		}
		foreach (TrashManRecycleBin item in recycleBinCollection)
		{
			if (item != null && !((Object)(object)item.prefab == (Object)null))
			{
				item.initialize();
				_instanceIdToRecycleBin.Add(((Object)item.prefab).GetInstanceID(), item);
				_poolNameToInstanceId.Add(((Object)item.prefab).name, ((Object)item.prefab).GetInstanceID());
			}
		}
	}

	private static GameObject spawn(int gameObjectInstanceId, Vector3 position, Quaternion rotation)
	{
		//IL_004d: Unknown result type (might be due to invalid IL or missing references)
		//IL_0054: Unknown result type (might be due to invalid IL or missing references)
		//IL_006a: Unknown result type (might be due to invalid IL or missing references)
		//IL_0077: Unknown result type (might be due to invalid IL or missing references)
		//IL_007d: Unknown result type (might be due to invalid IL or missing references)
		//IL_0086: Unknown result type (might be due to invalid IL or missing references)
		//IL_008c: Unknown result type (might be due to invalid IL or missing references)
		if (instance._instanceIdToRecycleBin.TryGetValue(gameObjectInstanceId, out var value))
		{
			GameObject val = value.spawn();
			if ((Object)(object)val != (Object)null)
			{
				Transform val2 = val.transform;
				if (Object.op_Implicit((Object)(object)((val2 is RectTransform) ? val2 : null)))
				{
					val2.SetParent((Transform)null, false);
				}
				else
				{
					val2.parent = null;
				}
				val2.position = position;
				val2.rotation = rotation;
				val2.localScale = new Vector3(1f, 1f, 1f);
				val2.localRotation = default(Quaternion);
				val2.localPosition = default(Vector3);
				val.SetActive(true);
			}
			return val;
		}
		return null;
	}

	private IEnumerator internalDespawnAfterDelay(GameObject go, float delayInSeconds)
	{
		yield return (object)new WaitForSeconds(delayInSeconds);
		despawn(go);
	}

	public static void manageRecycleBin(TrashManRecycleBin recycleBin)
	{
		if (instance._poolNameToInstanceId.ContainsKey(((Object)recycleBin.prefab).name))
		{
			Debug.LogError((object)("Cannot manage the recycle bin because there is already a GameObject with the name (" + ((Object)recycleBin.prefab).name + ") being managed"));
			return;
		}
		instance.recycleBinCollection.Add(recycleBin);
		recycleBin.initialize();
		instance._instanceIdToRecycleBin.Add(((Object)recycleBin.prefab).GetInstanceID(), recycleBin);
		instance._poolNameToInstanceId.Add(((Object)recycleBin.prefab).name, ((Object)recycleBin.prefab).GetInstanceID());
		recycleBin.prefab.SetActive(false);
	}

	public static void removeRecycleBin(TrashManRecycleBin recycleBin, bool shouldDestroyAllManagedObjects = true)
	{
		string name = ((Object)recycleBin.prefab).name;
		if (instance._poolNameToInstanceId.ContainsKey(name))
		{
			instance._poolNameToInstanceId.Remove(name);
			instance._instanceIdToRecycleBin.Remove(((Object)recycleBin.prefab).GetInstanceID());
			instance.recycleBinCollection.Remove(recycleBin);
			recycleBin.clearBin(shouldDestroyAllManagedObjects);
		}
	}

	public static GameObject spawn(GameObject go, Vector3 position = default(Vector3), Quaternion rotation = default(Quaternion))
	{
		//IL_0040: Unknown result type (might be due to invalid IL or missing references)
		//IL_0041: Unknown result type (might be due to invalid IL or missing references)
		//IL_001d: Unknown result type (might be due to invalid IL or missing references)
		//IL_001e: Unknown result type (might be due to invalid IL or missing references)
		if (instance._instanceIdToRecycleBin.ContainsKey(((Object)go).GetInstanceID()))
		{
			return spawn(((Object)go).GetInstanceID(), position, rotation);
		}
		Debug.LogWarning((object)("attempted to spawn go (" + ((Object)go).name + ") but there is no recycle bin setup for it. Falling back to Instantiate"));
		GameObject val = Object.Instantiate<GameObject>(go, position, rotation);
		Transform obj = val.transform;
		if ((Object)(object)((obj is RectTransform) ? obj : null) != (Object)null)
		{
			val.transform.SetParent((Transform)null, false);
		}
		else
		{
			val.transform.parent = null;
		}
		return val;
	}

	public static GameObject spawn(string gameObjectName, Vector3 position = default(Vector3), Quaternion rotation = default(Quaternion))
	{
		//IL_0017: Unknown result type (might be due to invalid IL or missing references)
		//IL_0018: Unknown result type (might be due to invalid IL or missing references)
		int value = -1;
		if (instance._poolNameToInstanceId.TryGetValue(gameObjectName, out value))
		{
			return spawn(value, position, rotation);
		}
		return null;
	}

	public static void despawn(GameObject go)
	{
		if ((Object)(object)go == (Object)null)
		{
			return;
		}
		string name = ((Object)go).name;
		if (!instance._poolNameToInstanceId.TryGetValue(name, out var value))
		{
			Object.DestroyImmediate((Object)(object)go);
			return;
		}
		instance._instanceIdToRecycleBin[value].despawn(go);
		Transform obj = go.transform;
		if ((Object)(object)((obj is RectTransform) ? obj : null) != (Object)null)
		{
			go.transform.SetParent(instance.transform, false);
		}
		else
		{
			go.transform.parent = instance.transform;
		}
	}

	public static void despawnAfterDelay(GameObject go, float delayInSeconds)
	{
		if (!((Object)(object)go == (Object)null))
		{
			((MonoBehaviour)instance).StartCoroutine(instance.internalDespawnAfterDelay(go, delayInSeconds));
		}
	}

	public static TrashManRecycleBin recycleBinForGameObjectName(string gameObjectName)
	{
		if (instance._poolNameToInstanceId.TryGetValue(gameObjectName, out var value))
		{
			return instance._instanceIdToRecycleBin[value];
		}
		return null;
	}

	public static TrashManRecycleBin recycleBinForGameObject(GameObject go)
	{
		if (instance._instanceIdToRecycleBin.TryGetValue(((Object)go).GetInstanceID(), out var value))
		{
			return value;
		}
		return null;
	}
}
