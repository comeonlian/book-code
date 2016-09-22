package com.game.docking.util.pkg;

public abstract class Packet {
	public short protocolVer;
	public short cryptType;
	public short operation;

	public byte[] originalData;
	protected byte[] cryptedData;
	public boolean isGzip;

	public abstract boolean unpack(byte[] in);

	public abstract boolean pack(byte[] originalData) throws Exception;

	public abstract int getPacketLength();

	protected abstract byte[] encrypt(byte[] data, short cryptType);

	protected abstract byte[] decrypt(byte[] cryptedData, short cryptType);

	public byte[] getCryptedDada() {
		return cryptedData;
	}

	public byte[] getOriginalData() {
		return originalData;
	}
	
	public abstract byte[] getPackedDada() throws Exception;
}
