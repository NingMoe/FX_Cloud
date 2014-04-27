package com.phicomm.application.subscriber.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.phicomm.application.subscriber.dao.IMsgDao;
import com.phicomm.application.subscriber.model.Msg;


@Service("msgService")
public class MsgService implements IMsgService {

	private IMsgDao msgDao;
	
	
	
	public IMsgDao getMsgDao() {
		return msgDao;
	}

	@Resource
	public void setMsgDao(IMsgDao msgDao) {
		this.msgDao = msgDao;
	}

	@Override
	public void add(Msg msg) {
		msgDao.add(msg);
	}

}
