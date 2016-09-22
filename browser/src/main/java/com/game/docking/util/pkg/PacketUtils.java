package com.game.docking.util.pkg;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PacketUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(PacketUtils.class);

    public static byte[] entityToBytes(InputStream in, int length) {
        ByteArrayPool mPool = new ByteArrayPool(4096);
        PoolingByteArrayOutputStream bytes = new PoolingByteArrayOutputStream(mPool, length);
        byte[] buffer = null;
        try {
            if (in == null) {
                LOGGER.info("InputStream is null");
                return buffer;
            }
            buffer = mPool.getBuf(1024);
            int count = 0;
            while ((count = in.read(buffer)) != -1) {
                bytes.write(buffer, 0, count);
            }
            return bytes.toByteArray();
        } catch (IOException e) {
            LOGGER.info("流转换异常：{}", e);
        } finally {
            mPool.returnBuf(buffer);
            try {
                in.close();
            } catch (IOException e1) {
                LOGGER.info("关闭流异常：{}", e1);
            }
            try {
                bytes.close();
            } catch (IOException e) {
                LOGGER.info("关闭字节数组：{}", e);
            }
        }
        return buffer;
    }

    /**
     * 发数据
     */
    public static void sendMessage(HttpServletResponse response, String context, boolean isgzip) {
        Packet pkt = PacketFactory.createEmpty(PacketFactory.PROTOCAL_VERSION, PacketFactory.CRYPT_TYPE_1, (short) 0);
        byte[] body = null;
        try {
            pkt.pack(context.getBytes());
            body = pkt.getPackedDada();
            response.setHeader("ISGZIP", String.valueOf(isgzip));
        } catch (Exception e) {
            LOGGER.info("封装数据包异常：{}", e);
        }
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            if (isgzip) {
                os.write(PacketFactory.gZip(body));
            } else {
                os.write(body);
            }
        } catch (IOException e) {
            LOGGER.info("发送数据包异常：{}", e);
        } finally {
            try {
                if (os != null)
                    os.close();
            } catch (IOException e) {
                LOGGER.info("关闭流异常:{}", e);
            }
        }
    }

    /**
     * 发送数据
     */
    public static void send() throws URISyntaxException {
        String u = "http://192.168.5.75:10010/browser/getConfig.action";
//        String u = "http://183.63.53.103:21080/getConfig.action";
        OutputStream os = null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(u);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            Packet p = PacketFactory.createEmpty(PacketFactory.PROTOCAL_VERSION, PacketFactory.CRYPT_TYPE_1, (short) 0);
            //String str = "{\"nt\":1,\"rl\":\"986.89\",\"ivn\":\"1.0\",\"mac\":\"00:de:1e:ba:bf:58\",\"androidId\":\"f852086b594397c2\",\"vn\":\"1.0.0.1.D\",\"ovn\":\"4.4.2\",\"tst\":1,\"vc\":1,\"sdl\":\"6963.19\",\"src\":\"320*569\",\"apn\":\"46001-,46002-\",\"brd\":\"hongmi\",\"sdt\":\"8192.00\",\"imei\":\"863766020061877\",\"ovc\":19,\"clientId\":\"JJ00010002\",\"ct\":1441874912710,\"inl\":0,\"sign\":1}";
            String str = "{\"nt\":1,\"rl\":\"986.89\",\"ivn\":\"1.0\",\"mac\":\"00:de:1e:ba:bf:58\",\"aid\":\"f852086b594397c2\",\"vn\":\"1.0.0.1.D\",\"ovn\":\"4.4.2\",\"tst\":1,\"vc\":1,\"sdl\":\"6963.19\",\"src\":\"320*569\",\"apn\":\"46001-,46002-\",\"brd\":\"hongmi\",\"sm\":[{\"iccid\":\"89860114851098346414\",\"data\":false,\"sc\":\"+8613010888500\",\"imsi\":\"460019854007577\",\"numeric\":\"46001\",\"slot\":0,\"imei\":\"863766020061877\"},{\"iccid\":\"89860042191444296438\",\"data\":false,\"sc\":\"+8613800775500\",\"imsi\":\"460028186275655\",\"numeric\":\"46002\",\"slot\":1,\"imei\":\"863766020061877\"}],\"sdt\":\"8192.00\",\"imei\":\"863766020061877\",\"ovc\":19,\"cid\":\"JJ00010002\",\"ct\":1441874912710,\"inl\":0,\"sign\":1,\"ipkg\":\"com.aroids.pay\",\"lg\":\"zh-CN\",\"rt\":\"1154.52\",\"pn\":\"MT6571\",\"imsi\":\"460019854007577\",\"wf\":1,\"mdl\":\"XY-A6\"}";
            p.pack(str.getBytes());
            Boolean isgzip = true;
            conn.setRequestProperty("ISGZIP", isgzip.toString());
            conn.setRequestProperty("content-type", "application/octet-stream");
            // conn.setRequestProperty("x-forwarded-for", "119.93.49.172");
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            //conn.setRequestProperty("ISGIP", "true");
            conn.connect();
            os = conn.getOutputStream();
            byte[] outw = p.getPackedDada();
            if (isgzip) {
                outw = PacketFactory.gZip(p.getPackedDada());
            }
            
            LOGGER.info("conn.getContentLength:{}", outw.length);
            LOGGER.info("p.getPacketLength:{}", p.getPacketLength());
            LOGGER.info(new String(p.originalData));
            os.write(outw);
//            os.write(PacketFactory.gZip(p.getPackedDada()));
            os.flush();
            os.close();

            byte[] bt = entityToBytes(conn.getInputStream(), conn.getContentLength());
            LOGGER.info("pp.getPacketLength:{}", bt.length);
            Packet pp = PacketFactory.createFromNet(bt, isgzip);
            LOGGER.info(new String(pp.getOriginalData()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null)
                conn.disconnect();
        }
        return;
    }

    public static void main(String[] args) {
        try {
            PacketUtils.send();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
