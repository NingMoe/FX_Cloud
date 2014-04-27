package com.phicomm.application.subscriber.model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
	private static ThreadPoolExecutor threadPool = null;
	public static synchronized ThreadPoolExecutor createPingThreadPool(int aThreadPoolMinNum,
			int aThreadPoolMaxNum,long keepAliveTime)
			{
			     if(threadPool == null)
			     {
			         threadPool =
			              new ThreadPoolExecutor(aThreadPoolMinNum,aThreadPoolMaxNum,
							keepAliveTime,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(500),
							new ThreadPoolExecutor.CallerRunsPolicy());
			     }
			    
			     return threadPool;
			}
}
