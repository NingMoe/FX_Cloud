package com.phicomm.application.subscriber.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.phicomm.application.subscriber.dao.IReleaseDao;
import com.phicomm.application.subscriber.model.Release;

@Service("releaseService")
public class ReleaseService implements IReleaseService{

	private IReleaseDao releaseDao;
		
	public IReleaseDao getReleaseDao() {
		return releaseDao;
	}

	@Resource
	public void setReleaseDao(IReleaseDao releaseDao) {
		this.releaseDao = releaseDao;
	}

	@Override
	public void add(Release release) {
		releaseDao.add(release);
	}

}
