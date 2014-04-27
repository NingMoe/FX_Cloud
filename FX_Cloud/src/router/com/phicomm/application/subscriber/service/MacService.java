package com.phicomm.application.subscriber.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.phicomm.application.subscriber.dao.IMacDao;
import com.phicomm.application.subscriber.model.Mac;

@Service("macService")
public class MacService implements IMacService {

	private IMacDao macDao;
	
	public IMacDao getMacDao() {
		return macDao;
	}

	@Resource
	public void setMacDao(IMacDao macDao) {
		this.macDao = macDao;
	}
	
	@Override
	public void add(Mac mac) {
		//User u = userDao.loadByUsername(user.getMAIL_ADDRESS(),user.getPASSWORD());
		//if(u!=null) throw new UserException("此邮箱已被注册！");
		macDao.add(mac);
	}

	@Override
	public void update(Mac mac) {
		macDao.update(mac);
	}

	@Override
	public void delete(long id) {
		macDao.delete(id);
	}

	@Override
	public Mac load(long id) {
		return macDao.load(id);
	}
	
	@Override
	public Mac loadByMac(String mac){
		return macDao.loadByMac(mac);
	}

	@Override
	public List<Mac> list() {
		return macDao.list();
	}
	


}
