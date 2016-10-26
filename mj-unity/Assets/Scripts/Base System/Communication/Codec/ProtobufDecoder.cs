using ProtoBuf;
using Message;
using System.IO;

namespace Communication
{
    public class ProtobufDecoder : IDataDecoder
    {

        public object Decode(byte[] bytes, int offset, int length)
        {
            using (var ms = new MemoryStream(bytes))
            {
                Response response = Serializer.DeserializeWithLengthPrefix<Response>(ms, PrefixStyle.Base128);
                return response;
            }
        }

    }

}


