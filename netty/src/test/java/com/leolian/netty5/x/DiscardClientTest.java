package com.leolian.netty5.x;

import org.junit.Test;

import com.leolian.nettty5.x.DiscardClient;

public class DiscardClientTest {
	
	@Test
	public void testDiscardClient() throws Exception{
		DiscardClient client = new DiscardClient("localhost", 8999);
		
		//System.out.println(client.run(1));
		
		String result = "";
		long begin,end;
		for(int i=0; i<1000; i++){
			begin = System.currentTimeMillis();
			result = client.run(i);
			if(null==result||result.length()==0)
				throw new RuntimeException();
			end = System.currentTimeMillis();
			System.out.println("Receive from server: "+result);
			System.out.println("第 "+i+" 次循环耗时 "+(end-begin) +" ms");
		}
		
	}
	
	@Test
	public void testDecoder() throws Exception{
		DiscardClient client = new DiscardClient("localhost", 8999);
		System.out.println(client.run(1));
	}
	
	
}
