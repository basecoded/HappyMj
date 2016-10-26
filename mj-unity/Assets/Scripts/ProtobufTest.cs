using UnityEngine;
using System;
using ProtoBuf;
using System.IO;
using Message;
using LitJson;
using Communication;

public class ProtobufTest : MonoBehaviour {

	private CommunicationCenter communicationCenter;

	// Use this for initialization
	void Start () {

		communicationCenter = new CommunicationCenter ();
		communicationCenter.TryConnect ();

		JsonData json = new JsonData ();
		json["userId"] = "test001";
		json["name"] = "weixin001";
		json["head"] = "head001";

		Debug.Log (json.ToJson());

		Request request = new Request () {command = "1001", data = json.ToJson()};
		communicationCenter.SendMessage (request);

		byte[] bytes = Serialize<Request> (request);
		Debug.Log (bytes.Length);

		Request result = Deserialize<Request> ((object)bytes);
		Debug.Log (result);

	}
	
	// Update is called once per frame
	void Update () {
	
	}

	public static byte[] Serialize<T>(T instance)
	{
		byte[] bytes;
		using (var ms = new MemoryStream())
		{
			Serializer.SerializeWithLengthPrefix(ms, instance, PrefixStyle.Base128);
			bytes = new byte[ms.Position];
			var fullBytes = ms.GetBuffer();
			Array.Copy(fullBytes, bytes, bytes.Length);
		}
		return bytes;
	}

	public static T Deserialize<T>(object obj)
	{
		byte[] bytes = (byte[]) obj;
		int length;
		Serializer.TryReadLengthPrefix (bytes, 0, bytes.Length, PrefixStyle.Base128, out length);
		Debug.Log ("length: " + length);

		using (var ms = new MemoryStream(bytes))
		{
			return Serializer.DeserializeWithLengthPrefix<T>(ms, PrefixStyle.Fixed32BigEndian);
		}
	}



}
