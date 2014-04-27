package com.phicomm.application.subscriber.dao;


import java.util.List;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.phicomm.application.subscriber.dto.TopicAuditDto;
import com.phicomm.application.subscriber.model.AuditAdvise;
import com.phicomm.application.subscriber.model.Pager;
import com.phicomm.application.subscriber.model.Topic;

@Repository("topicDao")
public class TopicDao extends BaseDao<Topic> implements ITopicDao {
	

	@Override
	public Pager<Topic> find(Integer cid, String title, Integer status) {
		return find(null,cid,title,status);
	}
	
	private String getTopicSelect() {
		return "select new Topic(t.id,t.title,t.status,t.publishStartDate,t.publishEndDate,t.createDate,t.author,t.publishRange,t.publishRate)";
	}
	@Override
	public Pager<Topic> find(Long uid, Integer cid, String title,
			Integer status) {
		String hql = getTopicSelect()+" from Topic t where 1=1";
		/*if(status!=null) {
			hql+=" and t.status="+status;
		}
		if(title!=null&&!title.equals("")) {
			hql+=" and t.title like '%"+title+"%'";
		}
 		if(uid!=null&&uid>0) {
			hql+=" and t.user.id="+uid;
		}
		if(cid!=null&&cid>0) {
			hql+=" and t.channel.id="+cid;
		}*/
		return this.find(hql);
	}

	@Override
	public Pager<Topic> searchTopicByKeyword(String keyword) {
		String hql = getTopicSelect()+" from Topic t where t.status=1 and t.keyword like '%"+keyword+"%'";
		return this.find(hql);
	}

	@Override
	public Pager<Topic> searchTopic(String con) {
		String hql = getTopicSelect();
		if(con==null || "".equals(con))  hql += "from Topic t";
		else
			hql += " from Topic t where " +
					"(title like '%"+con+"%' or content like '%"+con+"%')";
		return this.find(hql);
	}

	@Override
	public Pager<Topic> findRecommendTopic(Integer cid) {
		String hql = getTopicSelect()+" from Topic t where t.status=1 and t.recommend=1";
		if(cid==null||cid==0) {
			return this.find(hql);
		} else {
			hql +=" and t.channel.id=?";
			return this.find(hql, cid);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TopicAuditDto> listAudit(Long tid) {
		//return this.getSession().createQuery("select new com.phicomm.application.subscriber.dto.TopicAuditDto(a.advise,a.auditTime,a.isTwice,m.employeeID) "+
			//	"from AuditAdvise a left join fetch com.phicomm.application.subscriber.model.Manager m where a.topic.id=?").setParameter(0, tid).list();
		return this.getSession().createSQLQuery("SELECT a.advise as advise,a.audit_time as auditTime,a.status as status,m.employeeID FROM fx_aduit_advise a LEFT JOIN fx_manager m ON(a.u_id=m.`id`) WHERE a.`t_id`=?")
				.setParameter(0, tid).setResultTransformer(Transformers.aliasToBean(TopicAuditDto.class)).list();
	}

	@Override
	public String loadCity(String cid) {
		return (String) this.getSession().createSQLQuery("select cityName from fx_city where cid=?").setParameter(0, cid).uniqueResult();
	}



}
