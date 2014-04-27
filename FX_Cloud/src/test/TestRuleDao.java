import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.phicomm.application.subscriber.dao.OutletDao;
import com.phicomm.application.subscriber.dao.OutletReportDao;
import com.phicomm.application.subscriber.dao.RuleDao;
import com.phicomm.application.subscriber.dao.TopicDao;
import com.phicomm.application.subscriber.dto.TopicAuditDto;
import com.phicomm.application.subscriber.model.RecordHoursDto;
import com.phicomm.application.subscriber.model.Rule;
import com.phicomm.application.subscriber.service.IOutletService;
import com.phicomm.application.subscriber.util.JsonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestRuleDao {
	private static final String SDF = "HH:mm:ss";
	
	private IOutletService outletService;
	private OutletDao outletDao;
	private RuleDao ruleDao;
	private OutletReportDao outletReportDao;
	private TopicDao topicDao;
	
	
	public TopicDao getTopicDao() {
		return topicDao;
	}
	@Inject
	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}
	public OutletReportDao getOutletReportDao() {
		return outletReportDao;
	}
	@Inject
	public void setOutletReportDao(OutletReportDao outletReportDao) {
		this.outletReportDao = outletReportDao;
	}
	public RuleDao getRuleDao() {
		return ruleDao;
	}
	@Inject
	public void setRuleDao(RuleDao ruleDao) {
		this.ruleDao = ruleDao;
	}
	public OutletDao getOutletDao() {
		return outletDao;
	}
	@Inject
	public void setOutletDao(OutletDao outletDao) {
		this.outletDao = outletDao;
	}
	public IOutletService getOutletService() {
		return outletService;
	}
	@Inject
	public void setOutletService(IOutletService outletService) {
		this.outletService = outletService;
	}
	
	@Test
	public void testTopic(){
		List<TopicAuditDto> tad = topicDao.listAudit(1L);
		for(TopicAuditDto t:tad){
			System.out.println(t.getAdvise());
		}
	}
	
	@Test
	public void testReportDao(){
		System.out.println("1111");
		List<RecordHoursDto> rhd =  outletReportDao.loadHoursKwh(1, 5);
		for(RecordHoursDto r:rhd){
			System.out.println(r.getId()+","+r.getHours()+","+r.getKwh());
		}
	}
	@Test
	public void test() {
	//	SimpleDateFormat format = new SimpleDateFormat(SDF);
		String json = "{\"interval\":\"1111111\",\"starts\":\"11:20:00\",\"orders\":2,\"status\":2,\"isRepeat\":2}";
		Rule r = (Rule)JsonUtil.getInstance().json2obj(json,Rule.class);
		System.out.println(r.getInterval());
		/*Rule r = new Rule();
		r.setInterval("1111111");
		r.setIsRepeat(Byte.parseByte("2"));
		r.setOrders(1);
		r.setStarts(Time.valueOf("11:20:00"));
		r.setStatus(Byte.parseByte("2"));
		System.out.println(JsonUtil.getInstance().obj2json(r));
		*/
		//ruleDao.deleteByOtl(3);
	}
}
