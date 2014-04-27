package com.phicomm.application.subscriber.service;

import java.util.List;

import com.phicomm.application.subscriber.model.PhoneUser;

public interface IPhoneUserService {
	public void add(PhoneUser phoneuser);
	public void update(PhoneUser phoneuser);
	public void delete(long id);
	public PhoneUser load(long id);
	public List<PhoneUser> list();

	public List<PhoneUser> listUid(long uid,String phoneType,String phoneVer);
	public long countUid(long uid);
}
