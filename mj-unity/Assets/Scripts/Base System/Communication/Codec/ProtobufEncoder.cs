using System;
using ProtoBuf;
using System.IO;
using Message;

namespace Communication
{
    public class ProtobufEncoder : IDataEncoder
    {

        public byte[] Encode<T>(T instance)
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
    }
}
