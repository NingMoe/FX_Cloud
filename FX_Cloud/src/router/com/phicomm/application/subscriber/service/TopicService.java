package com.phicomm.application.subscriber.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.phicomm.application.subscriber.model.AuditAdvise;
import com.phicomm.application.subscriber.dao.IAttachmentDao;
import com.phicomm.application.subscriber.dao.IAuditAdviseDao;
import com.phicomm.application.subscriber.dao.IManagerDao;
import com.phicomm.application.subscriber.dao.ITopicDao;
import com.phicomm.application.subscriber.dao.IUserDao;
import com.phicomm.application.subscriber.dto.TopicAuditDto;
import com.phicomm.application.subscriber.model.Attachment;
import com.phicomm.application.subscriber.model.Manager;
import com.phicomm.application.subscriber.model.Pager;
import com.phicomm.application.subscriber.model.Topic;

@Service("topicService")
public class TopicService implements ITopicService {
	private ITopicDao topicDao;
	private IAttachmentDao attachmentDao;
	private IUserDao userDao;
	private IManagerDao managerDao;
	private IAuditAdviseDao auditAdviseDao;
	
	public IAuditAdviseDao getAuditAdviseDao() {
		return auditAdviseDao;
	}
	@Inject
	public void setAuditAdviseDao(IAuditAdviseDao auditAdviseDao) {
		this.auditAdviseDao = auditAdviseDao;
	}
	public IManagerDao getManagerDao() {
		return managerDao;
	}
	@Inject
	public void setManagerDao(IManagerDao managerDao) {
		this.managerDao = managerDao;
	}
	public IUserDao getUserDao() {
		return userDao;
	}
	@Inject
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	public ITopicDao getTopicDao() {
		return topicDao;
	}
	@Inject
	public void setTopicDao(ITopicDao topicDao) {
		this.topicDao = topicDao;
	}

	public IAttachmentDao getAttachmentDao() {
		return attachmentDao;
	}
	@Inject
	public void setAttachmentDao(IAttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}
	
	private void addTopicAtts(Topic topic,Integer[] aids) {
		if(aids!=null) {
			for(Integer aid:aids) {
				Attachment a = attachmentDao.load(aid);
				if(a==null) continue;
				a.setTopic(topic);
			}
		}
	}

	@Override
	public void add(Topic topic, long uid,Integer[] aids) {
		Manager u = managerDao.load(uid);
		topic.setAuthor(u.getEmployeeID());
		topic.setCreateDate(new Date());
		topic.setUser(u);
		topicDao.add(topic);
		addTopicAtts(topic, aids);
	}

	@Override
	public void add(Topic topic, int uid) {
		add(topic,uid,null);
	}

	@Override
	public void delete(long id) {
		List<Attachment> atts = attachmentDao.listByTopic(id);
		attachmentDao.deleteByTopic(id);
		topicDao.delete(id);
		//删除硬盘上面的文件
		for(Attachment a:atts) {
			AttachmentService.deleteAttachFiles(a);
		}
	}

	@Override
	public void update(Topic topic, Integer[] aids) {
		topicDao.update(topic);
		addTopicAtts(topic, aids);
	}

	@Override
	public void update(Topic topic) {
		update(topic,null);
	}

	@Override
	public Topic load(long id) {
		return topicDao.load(id);
	}

	@Override
	public Pager<Topic> find(Integer cid, String title, Integer status) {
		return topicDao.find(cid, title, status);
	}

	@Override
	public Pager<Topic> find(Long uid, Integer cid, String title,
			Integer status) {
		return topicDao.find(uid, cid, title, status);
	}

	@Override
	public Pager<Topic> searchTopicByKeyword(String keyword) {
		return topicDao.searchTopicByKeyword(keyword);
	}

	@Override
	public Pager<Topic> searchTopic(String con) {
		return topicDao.searchTopic(con);
	}

	@Override
	public Pager<Topic> findRecommendTopic(Integer ci) {
		return topicDao.findRecommendTopic(ci);
	}
	
	/**
	 * 0-未审核
	 * 1-通过审核
	 * 2-未通过
	 */
	@Override
	public List<TopicAuditDto> listAudit(Long tid) {
		return topicDao.listAudit(tid);
	}
	@Override
	public void updateDelete(long id) {
		Topic t = topicDao.load(id);
		t.setStatus((byte) 3);
		topicDao.update(t);
	}
	@Override
	public void updateStatus(long id, String auditAdvise, Manager user,
			int auditstatus) {
		Topic t = topicDao.load(id);
		t.setStatus((byte) auditstatus);
		topicDao.update(t);
		AuditAdvise aa = new AuditAdvise();
		aa.setAdvise(auditAdvise);
		aa.setTopic(t);
		aa.setAuditTime(new Date());
		aa.setUser(user);
		aa.setStatus((byte) auditstatus);
		auditAdviseDao.add(aa);
	}
	@Override
	public String loadCity(String cid) {
		return topicDao.loadCity(cid);
	}

}
