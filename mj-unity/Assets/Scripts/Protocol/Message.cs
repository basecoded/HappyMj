using ProtoBuf;

namespace Message {

	[ProtoContract]
	public class Request  {

		[ProtoMember(1)]
		public string command;

		[ProtoMember(2)]
		public string data;

		public string ToString() 
		{
			return "[command: "+ command +", data: "+ data +"]";
		}

	}

	[ProtoContract]
	public class Response  {

		[ProtoMember(1)]
		public string command;

		[ProtoMember(2)]
		public string data;

		public string ToString() 
		{
			return "[command: "+ command +", data: "+ data +"]";
		}
	}

}
