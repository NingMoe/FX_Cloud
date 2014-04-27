package com.phicomm.application.subscriber.service;

import java.util.List;

import com.phicomm.application.subscriber.dto.TopicAuditDto;
import com.phicomm.application.subscriber.model.Manager;
import com.phicomm.application.subscriber.model.Pager;
import com.phicomm.application.subscriber.model.Topic;


public interface ITopicService {
	public String loadCity(String cid);
	/**
	 * 添加带有附件信息的文章
	 * @param topic 文章对象
	 * @param l 文章的用户
	 */
	public void add(Topic topic,long l,Integer[] aids);
	/**
	 * 添加不带附件信息的文章对象
	 * @param topic
	 * @param cid
	 * @param uid
	 */
	public void add(Topic topic,int uid);
	
	/**
	 * 删除文章，先删除文章的附件信息，还得删除附件的文件对象
	 * @param id
	 */
	public void delete(long id);
	/**
	 * 更新文章，带附件信息更新
	 * @param topic
	 * @param cid
	 * @param aids
	 */
	public void update(Topic topic,Integer[] aids);
	/**
	 * 没有带附件信息的文章更新
	 * @param topic
	 */
	public void update(Topic topic);
	
	public Topic load(long id);
	
	/**
	 * 根据栏目和标题和状态进行文章的检索
	 * @param cid
	 * @param title
	 * @return
	 */
	public Pager<Topic> find(Integer cid,String title,Integer status);
	/**
	 * 根据用户，栏目和标题和状态进行检索
	 * @param l
	 * @param cid
	 * @param title
	 * @return
	 */
	public Pager<Topic> find(Long l,Integer cid,String title,Integer status);
	/**
	 * 根据关键字进行文章的检索，仅仅只是检索关键字类似的
	 * @param keyword
	 * @return
	 */
	public Pager<Topic> searchTopicByKeyword(String keyword);
	/**
	 * 通过某个条件来检索，该条件会在标题，内容和摘要中检索
	 * @param con
	 * @return
	 */
	public Pager<Topic> searchTopic(String con);
	/**
	 * 检索某个栏目的推荐文章，如果cid为空，表示检索全部的文章
	 * @param ci
	 * @return
	 */
	public Pager<Topic> findRecommendTopic(Integer ci);
	public List<TopicAuditDto> listAudit(Long tid);
	public void updateDelete(long id);
	public void updateStatus(long id, String auditAdvise, Manager user,
			int auditstatus);
}
