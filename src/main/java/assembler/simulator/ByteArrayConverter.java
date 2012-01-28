package assembler.simulator;

import java.nio.ByteBuffer;

public final class ByteArrayConverter {
	private static final long CLEAR_TOP_32_BITS_MASK = 0x00000000FFFFFFFFL;

	private static final long CLEAR_BOTTOM_32_BITS_MASK = 0xFFFFFFFF00000000L;

	private static final int BITS_IN_A_WORD = 32;

	private static final int BYTES_IN_INT = Integer.SIZE / Byte.SIZE;

	private static final int BYTES_IN_LONG = Long.SIZE / Byte.SIZE;

	private static final int BYTES_IN_SHORT = Short.SIZE / Byte.SIZE;

	private ByteArrayConverter() {
		// No instantiation
	}

	public static byte[] convertToByteArray(int value) {
		return ByteBuffer.allocate(BYTES_IN_INT).putInt(value).array();
	}

	public static byte[] convertToByteArray(long value) {
		return ByteBuffer.allocate(BYTES_IN_LONG).putLong(value).array();
	}

	public static byte[] convertToByteArray(short value) {
		return ByteBuffer.allocate(BYTES_IN_SHORT).putShort(value).array();
	}

	public static int intFromByteArray(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getInt();
	}

	public static long longFromByteArray(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getLong();
	}

	public static short shortFromByteArray(byte[] bytes) {
		return ByteBuffer.wrap(bytes).getShort();
	}

	public static int[] splitDoubleWordIntoTwoWords(long doubleWord) {
		// Create two integer values from the actual bytes
		byte[] doubleWordBytes = ByteArrayConverter
				.convertToByteArray(doubleWord);

		byte[][] wordBytes = splitByteArrayIntoMultiple(doubleWordBytes,
				BYTES_IN_INT);

		int firstValue = ByteArrayConverter.intFromByteArray(wordBytes[0]);
		int secondValue = ByteArrayConverter.intFromByteArray(wordBytes[1]);
		return new int[] { firstValue, secondValue };
	}

	public static long combineTwoWordsIntoDoubleWord(int registerValue,
			int secondRegisterValue) {
		long firstRegisterBytes = registerValue;
		firstRegisterBytes <<= BITS_IN_A_WORD;
		firstRegisterBytes &= CLEAR_BOTTOM_32_BITS_MASK; // cleared bottom
															// 32-bits

		long secondRegisterBytes = secondRegisterValue & CLEAR_TOP_32_BITS_MASK; // cleared
																					// top
																					// 32-bits

		return firstRegisterBytes | secondRegisterBytes;
	}

	static byte[][] splitByteArrayIntoMultiple(byte[] originalByteArray,
			int numberOfBytesInEachArray) {
		int originalByteArrayLength = originalByteArray.length;
		if (originalByteArrayLength % numberOfBytesInEachArray != 0) {
			throw new IllegalArgumentException(originalByteArrayLength
					+ " is not evenly divisible by " + numberOfBytesInEachArray
					+ "!");
		}

		int numberOfArraysToCreate = originalByteArrayLength
				/ numberOfBytesInEachArray;

		byte[][] arrays = new byte[numberOfArraysToCreate][];

		for (int i = 0; i < numberOfArraysToCreate; i++) {
			arrays[i] = new byte[numberOfBytesInEachArray];
			System.arraycopy(originalByteArray, i * numberOfBytesInEachArray,
					arrays[i], 0, numberOfBytesInEachArray);
		}

		return arrays;
	}

}
