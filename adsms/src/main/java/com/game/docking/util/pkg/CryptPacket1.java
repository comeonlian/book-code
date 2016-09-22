package com.game.docking.util.pkg;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.game.docking.util.Base64;

public class CryptPacket1 extends Packet {
    public static final String ALGORITHM = "DES";

    public int keyLen;
    public String key;
    public int dataLen;

    @Override
    public boolean unpack(byte[] in) {

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
        key = initKey(null);
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
        try {
            return encrypt(data, key);
        } catch (Exception e) {
            // MyLog.e(TAG, "--encrypt--ERR:" + e.getMessage(), e);
        }
        return data;
    }

    @Override
    protected byte[] decrypt(byte[] encryptedData, short cryptType) {
        try {
            return decrypt(encryptedData, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedData;
    }

    @Override
    public int getPacketLength() {
        int length = 0;
        length += 2;// protocalVer;
        length += 2;// cryptType;
        length += 2;// operation
        length += 4;// keyLength
        length += keyLen;// key's length
        length += 4;// dataLen
        length += dataLen;// data's length
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

    private String initKey(String seed) throws Exception {
        SecureRandom secureRandom = null;
        if (seed != null) {
            secureRandom = new SecureRandom(decryptBASE64(seed));
        } else {
            secureRandom = new SecureRandom();
        }
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
        kg.init(secureRandom);
        SecretKey secretKey = kg.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }

    private byte[] encrypt(byte[] data, String key) throws Exception {
        Key k = toKey(decryptBASE64(key));
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    private byte[] decrypt(byte[] data, String key) throws Exception {
        Key k = toKey(decryptBASE64(key));
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    private Key toKey(byte[] key) throws Exception {
        SecretKey secretKey = null;
        if (ALGORITHM.equals("DES") || ALGORITHM.equals("DESede")) {
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            secretKey = keyFactory.generateSecret(dks);
        } else if (ALGORITHM.equals("AES") || ALGORITHM.equals("AESede")) {
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            secretKey = keyFactory.generateSecret(dks);
        } else {
            secretKey = new SecretKeySpec(key, ALGORITHM);
        }
        return secretKey;
    }

    private byte[] decryptBASE64(String key) throws Exception {
        return Base64.decode(key);
    }

    private String encryptBASE64(byte[] key) throws Exception {
        return Base64.encode(key);
    }

}
