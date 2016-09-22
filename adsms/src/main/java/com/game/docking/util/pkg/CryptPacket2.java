package com.game.docking.util.pkg;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class CryptPacket2 extends Packet {
	public int keyLen;
	public String key;
	public int dataLen;

	@Override
	public boolean unpack(byte[] in) {
		//ByteBuffer bb = ByteBuffer.allocate(in.length);
		//bb.put(in);
		//bb.rewind();

		ByteBuffer bb = ByteBuffer.wrap(in);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		
		keyLen = bb.getInt();
		byte[] buff = new byte[keyLen];
		bb.get(buff);
		key = new String(buff);
		
		dataLen = bb.getInt();
		cryptedData = new byte[dataLen];
		bb.get(cryptedData);
		
		originalData = decrypt(cryptedData, cryptType);
		if (originalData != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean pack(byte[] originalData) throws Exception {
		this.originalData = originalData;
		
		key = "init key here";// TODO init key
		keyLen = key.getBytes().length;
		
		cryptedData = encrypt(originalData, cryptType);
		if (cryptedData != null) {
			dataLen = cryptedData.length;
			return true;
		}
		return false;
	}

	@Override
	protected byte[] encrypt(byte[] data, short cryptType) {
		// TODO Auto-generated method stub
		return data;
	}

	@Override
	protected byte[] decrypt(byte[] encryptedData, short cryptType) {
		// TODO Auto-generated method stub
		return encryptedData;
	}

	@Override
	public int getPacketLength() {
		int length = 0;
		length += 2;//protocalVer;
		length += 2;//cryptType;
		length += 2;//operation
		length += 4;//keyLength
		length += keyLen;//key's length
		length += 4;//dataLen
		length += dataLen;//data's length
		return length;
	}

	@Override
	public byte[] getPackedDada() throws Exception {
		if (cryptedData == null || dataLen <= 0) {
			pack(originalData);
		}
		int capacity = getPacketLength();
		
		ByteBuffer bb = ByteBuffer.allocate(capacity);
		bb.order(ByteOrder.LITTLE_ENDIAN);
		
		bb.putShort(protocolVer);
		bb.putShort(cryptType);
		bb.putShort(operation);
		bb.putInt(keyLen);
		bb.put(key.getBytes());
		bb.putInt(dataLen);
		bb.put(cryptedData);
		bb.rewind();
		
		return bb.array();
	}

}
