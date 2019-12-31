package libs.types;

import java.io.FileInputStream;
import java.io.IOException;

import libs.types.bytes.Buffer;

public class BufferStream 
{
	private FileInputStream stream;
	private Buffer buffer;
	
	public BufferStream(FileInputStream stream, int bufferSize)
	{
		this.stream = stream;
		this.buffer = new Buffer(bufferSize);
	}
	
	public Buffer next() throws IOException
	{
		if (buffer.readFile(stream) == 0)
			return null;
		return buffer;
	}
}
