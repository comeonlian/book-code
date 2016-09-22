package com.game.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.game.docking.util.pkg.ByteArrayPool;
import com.game.docking.util.pkg.Packet;
import com.game.docking.util.pkg.PacketFactory;
import com.game.docking.util.pkg.PoolingByteArrayOutputStream;

/**
 * Servlet implementation class BaseServiceServlet
 */
public class BaseServiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BaseServiceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int len = request.getContentLength();
        System.out.println("request.len:" + len);
        if (len > 0) {

            InputStream in = request.getInputStream();

            byte[] data = entityToBytes(len, in);
            Packet p = null;
            try {
                p = PacketFactory.createFromNet(data, false);
                System.out.println("DATA:[" + new String(p.originalData) + "]");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (p != null) {
                String s = new String(p.originalData);
                System.out.println(s);
            }

            Packet pkt = PacketFactory.createEmpty(PacketFactory.PROTOCAL_VERSION, PacketFactory.CRYPT_TYPE_1, (short) 0);

            byte[] body = null;
            try {
                pkt.pack("{\"result\":\"ok\"}".getBytes());
                body = pkt.getPackedDada();
            } catch (Exception e) {
                e.printStackTrace();
            }
            OutputStream os = response.getOutputStream();
            if (body == null) {
                response.setStatus(500);
            } else {
                response.setStatus(200);
                // response.setHeader("WWW-Authenticate", "BASIC  realm=\"\"");
                response.setContentLength(pkt.getPacketLength());
                os.write(body);
                os.flush();
            }
            os.close();

        } else {
            response.getWriter().append("Served at: ").append(request.getContextPath());
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    private static byte[] entityToBytes(int len, InputStream in) {
        ByteArrayPool mPool = new ByteArrayPool(4096);
        PoolingByteArrayOutputStream bytes = new PoolingByteArrayOutputStream(mPool, len);
        byte[] buffer = null;
        try {
            if (in == null) {
                System.out.println("InputStream is null");
                return buffer;
            }
            buffer = mPool.getBuf(1024);
            int count;
            while ((count = in.read(buffer)) != -1) {
                bytes.write(buffer, 0, count);
            }
            return bytes.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            mPool.returnBuf(buffer);
            try {
                in.close();
            } catch (IOException e1) {
            }
            try {
                bytes.close();
            } catch (IOException e) {
            }
        }
        return buffer;
    }

}
