package com.game.quartz3;

import org.springframework.stereotype.Service;

@Service("jobService")
public class JobService {

	public void job1() {
		System.out.println("任务进行中。。。");
	}
}
