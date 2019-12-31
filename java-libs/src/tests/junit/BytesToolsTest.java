package tests.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import libs.tools.ByteTools;
import libs.types.ByteTest;

public class BytesToolsTest
{	
	public static byte[] Horse = "Horse".getBytes();
	public static byte[] Hello = "Hello".getBytes();
	public static byte[] Hell = "Hell".getBytes();
	public static byte[] Good = "Good".getBytes();
	public static byte[] sent = "Hello World, and Good Bye Every One.".getBytes();
	
	@Test
	public void testIsEqual()
	{
		assertTrue(ByteTools.isEqual(Hello, 0, Hello, 0, Hello.length));
		assertFalse(ByteTools.isEqual(Hello, 0, Horse, 0, Hello.length));
	}
	
	@Test
	public void testFind()
	{
		assertEquals(2, ByteTools.find(Hello, 0, Hello.length, ByteTest.isEqual('l')));
	}
	
	@Test
	public void testFindNot()
	{
		assertEquals(5, ByteTools.findNot(sent, 0, sent.length, ByteTest.Class.isLetter));
		assertEquals(4, ByteTools.findNot(sent, 0, sent.length, ByteTest.inString("Hel")));
	}
	
	
}
