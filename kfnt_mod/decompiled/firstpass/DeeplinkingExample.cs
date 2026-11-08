using System;
using ImaginationOverflow.UniversalDeepLinking;
using UnityEngine;
using UnityEngine.UI;

public class DeeplinkingExample : MonoBehaviour
{
	public GameObject Panel;

	public GameObject Reference;

	public GameObject InstructionText;

	private void Start()
	{
		SetupUi();
		DeepLinkManager.Instance.LinkActivated += Instance_LinkActivated;
	}

	private void SetupUi()
	{
		Reference.SetActive(false);
	}

	private void Instance_LinkActivated(LinkActivation s)
	{
		GameObject obj = Object.Instantiate<GameObject>(Reference, Panel.transform);
		((Component)obj.transform.GetChild(0)).GetComponent<Text>().text = DateTime.Now.ToString("t");
		((Component)obj.transform.GetChild(1)).GetComponent<Text>().text = s.Uri;
		obj.SetActive(true);
		InstructionText.SetActive(false);
		UpdateContentSize();
	}

	private void UpdateContentSize()
	{
		//IL_000e: Unknown result type (might be due to invalid IL or missing references)
		//IL_0019: Unknown result type (might be due to invalid IL or missing references)
		//IL_0029: Unknown result type (might be due to invalid IL or missing references)
		RectTransform component = Panel.GetComponent<RectTransform>();
		component.sizeDelta = new Vector2(component.sizeDelta.x, component.sizeDelta.y + 112f);
	}
}
