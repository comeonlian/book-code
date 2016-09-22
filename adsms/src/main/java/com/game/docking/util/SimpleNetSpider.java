package com.game.docking.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SimpleNetSpider {
    public static void main(String[] args) {

        try {
            String url = "http://wap.dm.10086.cn/portalone/u.do?id=1216867";
//            String url = "http://wap.dm.10086.cn/auth/login?service=http%3A%2F%2Fwap.dm.10086.cn%3A80%2Fm%2Fdm%2Fl%2Foacfs%3Fneedlogin%3D1%26purl%3D%252Fm%252Fdm%252Fx%253Fsid%253D1216867%2526sort%253Ddesc%2526page%253D1%2526purl%253D%25252Fm%25252Fp%25252Findex.jsp%2526src%253D0%2526x%253D000001216867%26x%3D000001216867%26vt%3D3%26f%3D8323%26pg%3D1094%26vt%3D3%26f%3D8323%26pg%3D1094&amp;display=xpbs&amp;extension=sessionID::81C645A84D8B1AB8E0BF25D2E3B731CD_portal::03_platform::01_accessMode::04_virtualP::92861876257_gatewayIP::218.18.1.123";
            URL u = new URL(url);
            URLConnection connection = u.openConnection();
            HttpURLConnection htCon = (HttpURLConnection) connection;
            htCon.setRequestProperty("user-agent", "Dalvik/2.1.0 (Linux; U; Android 5.1; MILaI M6 Build/LMY47D)");
            int code = htCon.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                File file = new File("E://res//1.html");
                PrintWriter pw = new PrintWriter(file);
                System.out.println("find the website");
                BufferedReader in = new BufferedReader(new InputStreamReader(htCon.getInputStream(), "utf-8"));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println(inputLine);
                    pw.write(inputLine);
                }
                in.close();
                pw.close();
            } else {
                System.out.println("Can not access the website");
            }
        } catch (MalformedURLException e) {
            System.out.println("Wrong URL");
        } catch (IOException e) {
            System.out.println("Can not connect");
        }
    }
}
