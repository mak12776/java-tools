package libs.machines;

public class AnalyzerMachine
{
	private byte[] instBuffer;
	private byte[] buffer;
	private int[] data;
	
	private int ip;
	private int bp;
	private int dp;
	
	private boolean test;
	
	//	bs: buffer size
	//	ds:	data size
	
	public AnalyzerMachine(byte[] instBuffer, int dataSize, int startPointer, int initialBufferPointer, int initalDataPointer)
	{
		this.instBuffer = instBuffer;
		this.data = new int[dataSize];
		this.buffer = null;
		
		this.ip = startPointer;
		this.bp = initialBufferPointer;
		this.dp = initalDataPointer;
	}
	
	private byte nextByte()
	{
		return instBuffer[ip++];
	}
	
	private short nextShort()
	{
		return (short) ((instBuffer[ip++] << 16) | (instBuffer[ip++]));
	}
	
	private int nextInt()
	{
		return (instBuffer[ip++] << 24) | (instBuffer[ip++] << 16) | (instBuffer[ip++] << 8) | (instBuffer[ip++]);
	}
	
	// instruction
	
	public static final byte INST_NOOP = 				0x00;
	
	private static final byte BASE1 =					INST_NOOP;
	
	public static final byte INST_COPY_IM32_BP =		BASE1 + 1;
	public static final byte INST_COPY_IM32_DP =		BASE1 + 2;
	
	private static final byte BASE2 =					INST_COPY_IM32_DP;
	
	public static final byte INST_TEST_BS_EQ_IM32 =		BASE2 + 1;
	public static final byte INST_TEST_BS_NE_IM32 =		BASE2 + 2;
	
	public static final byte INST_TEST_BS_LT_IM32 =		BASE2 + 3;
	public static final byte INST_TEST_BS_LE_IM32 =		BASE2 + 4;
	
	public static final byte INST_TEST_BS_GT_IM32 =		BASE2 + 5;
	public static final byte INST_TEST_BS_GE_IM32 =		BASE2 + 6;
	
	private static final byte BASE3 =					INST_TEST_BS_GE_IM32;
	
	public static final byte INST_TEST_DS_EQ_IM32 = 	BASE3 + 1;
	public static final byte INST_TEST_DS_NE_IM32 = 	BASE3 + 2;
	
	public static final byte INST_TEST_DS_LT_IM32 = 	BASE3 + 3;
	public static final byte INST_TEST_DS_LE_IM32 = 	BASE3 + 4;
	
	public static final byte INST_TEST_DS_GT_IM32 = 	BASE3 + 5;
	public static final byte INST_TEST_DS_GE_IM32 = 	BASE3 + 6;
	
	private static final byte BASE4 =					INST_TEST_DS_GE_IM32;
	
	public static final byte INST_JUMP_A32 =			BASE4 + 1;
	public static final byte INST_TJMP_A32 =			BASE4 + 2;
	public static final byte INST_FJMP_A32 =			BASE4 + 3;
	
	public void run(byte[] bytes)
	{		
		buffer = bytes;
		int jp;
		
		while (ip < instBuffer.length)
		{
			switch (nextByte())
			{
			case INST_NOOP:
				break;
				
			case INST_COPY_IM32_BP:
				bp = nextInt();
				break;
				
			case INST_COPY_IM32_DP:
				dp = nextInt();
				break;
			
			case INST_TEST_BS_EQ_IM32:
				test = (buffer.length == nextInt());
				break;
				
			case INST_TEST_BS_NE_IM32:
				test = (buffer.length != nextInt());
				break;
				
			case INST_TEST_BS_LT_IM32:
				test = (buffer.length < nextInt());
				break;
				
			case INST_TEST_BS_LE_IM32:
				test = (buffer.length <= nextInt());
				break;
				
			case INST_TEST_BS_GT_IM32:
				test = (buffer.length > nextInt());
				break;
				
			case INST_TEST_BS_GE_IM32:
				test = (buffer.length >= nextInt());
				break;	
			
			case INST_JUMP_A32:
				ip = nextInt();
				break;
				
			case INST_TJMP_A32:
				jp = nextInt();
				if (test) ip = jp;
				break;
				
			case INST_FJMP_A32:
				jp = nextInt();
				if (!test) ip = jp;
				break;
				
				
			}
		}
	}
}