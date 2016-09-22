package com.game.docking.util.pkg;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Date;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PacketFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(PacketFactory.class);
    public static final short PROTOCAL_VERSION = 1;
    public static final short CRYPT_TYPE_1 = 1; // 加密
    public static final short CRYPT_TYPE_2 = 2; // 非加密

    public static Packet createEmpty(short protocal, short cryptType, short op) {
        switch (cryptType) {
        case CRYPT_TYPE_1: {
            Packet p = new CryptPacket1();
            p.protocolVer = protocal;
            p.cryptType = CRYPT_TYPE_1;
            p.operation = op;
            return p;
        }
        case CRYPT_TYPE_2: {
            Packet p = new CryptPacket2();
            p.protocolVer = protocal;
            p.cryptType = CRYPT_TYPE_2;
            p.operation = op;
            return p;
        }
        default:
            LOGGER.info(new Date() + " wrong crypt type");
            return null;
        }
    }

    public static Packet createFromNet(byte[] packedData, boolean isGzip) {
        byte[] data = null;
        if (isGzip) {
            data = unGZip(packedData);
        } else {
            data = packedData;
        }

        ByteBuffer bb = ByteBuffer.wrap(data);
        bb.order(ByteOrder.LITTLE_ENDIAN);

        short protocol = bb.getShort();
        short cryptType = bb.getShort();
        short operation = bb.getShort();

        Packet p = createEmpty(protocol, cryptType, operation);
        if (p != null) {
            int remaining = bb.remaining();
            byte[] buff = new byte[remaining];
            bb.get(buff);
            p.unpack(buff);
        }

        return p;
    }

    public static byte[] gZip(byte[] data) throws IOException {
        byte[] b = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(bos);
        gzip.write(data);
        gzip.finish();
        gzip.close();
        b = bos.toByteArray();
        bos.close();
        return b;
    }

    public static byte[] unGZip(byte[] data) {
        byte[] b = null;
        GZIPInputStream gzip = null;
        ByteArrayInputStream bis = null;
        ByteArrayOutputStream baos = null;
        try {
            bis = new ByteArrayInputStream(data);
            gzip = new GZIPInputStream(bis);
            byte[] buf = new byte[1024];
            int num = -1;
            baos = new ByteArrayOutputStream();
            while ((num = gzip.read(buf, 0, buf.length)) != -1) {
                baos.write(buf, 0, num);
            }
            baos.flush();
            b = baos.toByteArray();
        } catch (Exception e) {
            LOGGER.info("解压异常:{}", e);
        } finally {
            try {
                if (baos != null)
                    baos.close();
            } catch (IOException e) {
                LOGGER.info("关闭流异常:{}", e);
            }
            try {
                if (gzip != null)
                    gzip.close();
            } catch (IOException e) {
                LOGGER.info("关闭流异常:{}", e);
            }
            try {
                if (bis != null)
                    bis.close();
            } catch (IOException e) {
                LOGGER.info("关闭流异常:{}", e);
            }
        }
        return b;
    }
}
