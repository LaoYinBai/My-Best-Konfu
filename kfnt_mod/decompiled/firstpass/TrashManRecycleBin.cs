using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public sealed class TrashManRecycleBin
{
	public GameObject prefab;

	public int instancesToPreallocate = 5;

	public int instancesToAllocateIfEmpty = 1;

	public bool imposeHardLimit;

	public int hardLimit = 5;

	public bool cullExcessPrefabs;

	public int instancesToMaintainInPool = 5;

	public float cullInterval = 10f;

	public bool automaticallyRecycleParticleSystems;

	public bool persistBetweenScenes;

	private Stack<GameObject> _gameObjectPool;

	private float _timeOfLastCull = float.MinValue;

	private int _spawnedInstanceCount;

	public event Action<GameObject> onSpawnedEvent;

	public event Action<GameObject> onDespawnedEvent;

	private void allocateGameObjects(int count)
	{
		if (imposeHardLimit && _gameObjectPool.Count + count > hardLimit)
		{
			count = hardLimit - _gameObjectPool.Count;
		}
		for (int i = 0; i < count; i++)
		{
			GameObject val = Object.Instantiate<GameObject>(prefab.gameObject);
			((Object)val).name = ((Object)prefab).name;
			Transform transform = val.transform;
			if (Object.op_Implicit((Object)(object)((transform is RectTransform) ? transform : null)))
			{
				val.transform.SetParent(TrashMan.instance.transform, false);
			}
			else
			{
				val.transform.parent = TrashMan.instance.transform;
			}
			val.SetActive(false);
			_gameObjectPool.Push(val);
		}
	}

	private GameObject pop()
	{
		if (imposeHardLimit && _spawnedInstanceCount >= hardLimit)
		{
			return null;
		}
		if (_gameObjectPool.Count > 0)
		{
			_spawnedInstanceCount++;
			return _gameObjectPool.Pop();
		}
		allocateGameObjects(instancesToAllocateIfEmpty);
		return pop();
	}

	public void initialize()
	{
		_gameObjectPool = new Stack<GameObject>(instancesToPreallocate);
		allocateGameObjects(instancesToPreallocate);
	}

	public void cullExcessObjects()
	{
		if (cullExcessPrefabs && _gameObjectPool.Count > instancesToMaintainInPool && Time.time > _timeOfLastCull + cullInterval)
		{
			_timeOfLastCull = Time.time;
			for (int i = instancesToMaintainInPool; i <= _gameObjectPool.Count; i++)
			{
				Object.Destroy((Object)(object)_gameObjectPool.Pop());
			}
		}
	}

	public GameObject spawn()
	{
		GameObject val = pop();
		if ((Object)(object)val != (Object)null)
		{
			if (this.onSpawnedEvent != null)
			{
				this.onSpawnedEvent(val);
			}
			if (automaticallyRecycleParticleSystems)
			{
				ParticleSystem component = val.GetComponent<ParticleSystem>();
				if (Object.op_Implicit((Object)(object)component))
				{
					TrashMan.despawnAfterDelay(val, component.duration + component.startLifetime);
				}
				else
				{
					Debug.LogError((object)"automaticallyRecycleParticleSystems is true but there is no ParticleSystem on this GameObject!");
				}
			}
		}
		return val;
	}

	public void despawn(GameObject go)
	{
		go.SetActive(false);
		_spawnedInstanceCount--;
		_gameObjectPool.Push(go);
		if (this.onDespawnedEvent != null)
		{
			this.onDespawnedEvent(go);
		}
	}

	public void clearBin(bool shouldDestroyAllManagedObjects)
	{
		while (_gameObjectPool.Count > 0)
		{
			GameObject val = _gameObjectPool.Pop();
			if (shouldDestroyAllManagedObjects)
			{
				Object.Destroy((Object)(object)val);
			}
		}
	}

	public void clear()
	{
		if ((Object)(object)prefab != (Object)null)
		{
			Object.Destroy((Object)(object)prefab.gameObject);
			prefab = null;
		}
		clearBin(shouldDestroyAllManagedObjects: true);
	}
}
