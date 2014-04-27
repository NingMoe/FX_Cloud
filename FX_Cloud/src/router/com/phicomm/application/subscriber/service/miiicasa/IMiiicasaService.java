package com.phicomm.application.subscriber.service.miiicasa;

import com.phicomm.application.subscriber.model.User;
import com.phicomm.application.subscriber.model.miiicasa.MiiicasaResponse;

public interface IMiiicasaService {

	//Pemco user register API
	public MiiicasaResponse invokeUserRegisterAPI(User user) throws Exception;

	public MiiicasaResponse invokeUserStatusSyncAPI(User user) throws Exception;
	
	
}
