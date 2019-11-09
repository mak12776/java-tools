package tools;

import tools.types.ByteTest;

public class ByteTools
{
	public static boolean isBlank(byte b)
	{
		return (b == ' ') || (b == '\t');
	}
	
	public static boolean isNewline(byte b)
	{
		return (b == '\n');
	}
	
	public static boolean isCarriageReturn(byte b)
	{
		return (b == '\r');
	}

	public static boolean isLower(byte b)
	{
		return ('a' <= b) && (b <= 'z');
	}
	
	public static boolean isUpper(byte b)
	{
		return ('A' <= b) && (b <= 'Z');
	}
	
	public static boolean isDigit(byte b)
	{
		return ('0' <= b) && (b <= '9');
	}
	
	public static boolean isLetter(byte b)
	{
		return  (('a' <= b) && (b <= 'z')) ||
				(('A' <= b) && (b <= 'Z'));
	}
	
	public static boolean isHexDigit(byte b)
	{
		return  (('0' <= b) && (b <= '9')) ||
				(('a' <= b) && (b <= 'f')) ||
				(('A' <= b) && (b <= 'F'));
	}
	
	public static boolean byteIn(byte b, byte[] bytes)
	{
		for (int i = 0; i < bytes.length; i += 1)
		{
			if (bytes[i] == b)
				return true;
		}
		return false;
	}
	
	public static int compare(byte[] buffer, int bufferOffset, byte[] bytes, int bytesOffset, int length)
	{
		int diff = 0;
		for (int i = 0; i < length; i += 1)
		{
			diff = bytes[bytesOffset + i] - buffer[bufferOffset + i];
			if (diff != 0) 
				break;
		}
		return diff;
	}
	
	public static boolean isEqual(byte[] buffer, int bufferOffset, byte[] bytes, int bytesOffset, int length)
	{
		return compare(buffer, bufferOffset, bytes, bytesOffset, length) == 0;
	}
	
	public static int find(byte[] buffer, int start, int end, ByteTest test)
	{
		int index;
		
		index = start;
		while (index < end)
		{
			if (test.test(buffer[index]))
				return index;
			index += 1;
		}
		return end;
	}
	
	public static int rfind(byte[] buffer, int start, int end, ByteTest test)
	{
		int index;
		
		index = end;
		while (index > start)
		{
			index -= 1;
			if (test.test(buffer[index]))
				return index;
		}
		return end;
	}
	
	public static int find(byte[] buffer, int start, int end, byte b1)
	{
		return find(buffer, start, end, new ByteTest()
		{
			@Override
			public boolean test(byte b)
			{
				return b == b1;
			}
		});
	}
	
	public static int find(byte[] buffer, int start, int end, byte[] array)
	{
		return find(buffer, start, end, new ByteTest()
		{
			@Override
			public boolean test(byte b)
			{
				return byteIn(b, array);
			}
		});
	}
	
	public static int rfind(byte[] buffer, int start, int end, byte b1)
	{
		return rfind(buffer, start, end, new ByteTest()
		{
			@Override
			public boolean test(byte b)
			{
				return b == b1;
			}
		});
	}
	
	public static int rfind(byte[] buffer, int start, int end, byte[] array)
	{
		return rfind(buffer, start, end, new ByteTest()
		{
			@Override
			public boolean test(byte b)
			{
				return byteIn(b, array);
			}
		});
	}
}