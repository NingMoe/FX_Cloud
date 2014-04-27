package com.phicomm.application.subscriber.model;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Resource;

import com.phicomm.application.subscriber.service.IDeviceService;
import com.phicomm.application.subscriber.util.PropertiesUtil;



public class Ping {
	private IDeviceService deviceService;
	
	
	public IDeviceService getDeviceService() {
		return deviceService;
	}


	@Resource
	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}
	

	public static class ThreadPoolTask implements Callable<Device> {
		private Device device;

		public ThreadPoolTask(Device device) {
			this.device = device;

		}

		private static String getPort(){
			Properties prop = PropertiesUtil.getIpProp();
			String port = prop.getProperty("port");
			if(port==null || "".equals(port)) port="30005";
			return port;
		}
		@Override
		public Device call() throws Exception {
			Socket s =null;
			String port = getPort();
			try {
				s = new Socket();
				SocketAddress socketAddress = new InetSocketAddress(device.getDeviceWanIp(),Integer.parseInt(port));
				//s = new Socket(device.getDeviceWanIp(),Integer.parseInt(getPort()));
				s.connect(socketAddress, 3000);
				System.out.println("连接成功");
				device.setActiveStatus("1");
			} catch (Exception e) {
				System.out.println("连接失败,端口为："+device.getDeviceWanIp());
				device.setActiveStatus("0");
				e.printStackTrace();
			}finally{
				try {
					if(s!=null) s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			return device;
			
			
			/*try {
				String s = "";
				Process process;
				int i = 0;
				// System.out.println(System.getProperty("os.name")) ;
				process = Runtime.getRuntime().exec(
						"cmd /c " + "ping -n 2 -w 500 "
								+ device.getDeviceWanIp());
				BufferedReader br = new BufferedReader(new InputStreamReader(
						process.getInputStream()));
				while ((s = br.readLine()) != null) {
					if (s.indexOf(device.getDeviceWanIp()) != -1)
						i++;
				}
				br.close();
				process.waitFor();

				if (i >= 3) {
					device.setActiveStatus("1");
					System.out.println(this.device.getDeviceWanIp() + "---OK");

					return device;
				} else {
					device.setActiveStatus("0");
					System.out
							.println(this.device.getDeviceWanIp() + "---LOSS");

					return device;
				}

			} catch (Exception e) {
				logger.error(e.toString(), e);
			}
			return device;*/
		}

	}    
	 
	 
	 

	public static java.util.List<Device> pingRouter(java.util.List<Device> l) {
		// 创建线程池
		ThreadPoolExecutor pool = ThreadPool.createPingThreadPool(10, 20, 0);

		List<FutureTask<Device>> tasks = new ArrayList<FutureTask<Device>>();

		for (int i = 0; i < l.size(); i++) {
			FutureTask<Device> futureTask = new FutureTask<Device>(
					new ThreadPoolTask(l.get(i)));
			pool.submit(futureTask);
			tasks.add(futureTask);
		}

		l.clear();// 先清除

		for (FutureTask<Device> futureTask : tasks) {
			try {
				// 阻塞一直等待执行完成拿到结果
				System.out.println("future result:" + futureTask.get());
				l.add(futureTask.get());
				// 阻塞一直等待执行完成拿到结果，如果在超时时间内，没有拿到则抛出异常
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		return l;
	}
	

	
	/* public static boolean singlePing(byte[] ip) {      
     	
    	 try {
   		  int timeOut = 3000;
   		  InetAddress address = InetAddress.getByAddress(ip);
   		  
   			  if (address.isReachable(timeOut)) { 				      				  
   				  
   				  return true;    
   			  } else {
   			
   				  return false;  
   			  }
   	  
   	  	} catch (Exception e) {
 
   	  		
   	  	}
    	 
        return true;     
    }     */
}
