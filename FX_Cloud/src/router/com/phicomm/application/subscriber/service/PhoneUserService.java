package com.phicomm.application.subscriber.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.phicomm.application.subscriber.dao.IPhoneUserDao;
import com.phicomm.application.subscriber.model.PhoneUser;

@Service("phoneuserService")
public class PhoneUserService implements IPhoneUserService {

	private IPhoneUserDao phoneuserDao;
	
	
	
	public IPhoneUserDao getPhoneUserDao() {
		return phoneuserDao;
	}

	@Resource
	public void setPhoneUserDao(IPhoneUserDao phoneuserDao) {
		this.phoneuserDao = phoneuserDao;
	}

	@Override
	public void add(PhoneUser phoneuser) {
	//	PhoneUser u = phoneuserDao.loadByUsername(phoneuser.getUserID());
	//	if(u!=null) throw new UserException("该用户已存在！");
		phoneuserDao.add(phoneuser);
	}

	@Override
	public void update(PhoneUser phoneuser) {
		phoneuserDao.update(phoneuser);
	}

	@Override
	public void delete(long id) {
		phoneuserDao.delete(id);
	}

	@Override
	public PhoneUser load(long id) {
		return phoneuserDao.load(id);
	}

	@Override
	public List<PhoneUser> list() {
		return phoneuserDao.list();
	}
	
	@Override
	public List<PhoneUser> listUid(long uid,String phoneType,String phoneVer){
		return phoneuserDao.listUid(uid, phoneType, phoneVer);
	}
	
	@Override
	public long countUid(long uid){
		return phoneuserDao.countUid(uid);
	}

}
