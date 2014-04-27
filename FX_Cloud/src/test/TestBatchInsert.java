import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.phicomm.application.subscriber.dao.OutletDao;
import com.phicomm.application.subscriber.model.Outlet;
import com.phicomm.application.subscriber.model.Rule;
import com.phicomm.application.subscriber.service.IOutletService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestBatchInsert {
	private static final String SDF = "HH:mm:ss";
	
	private IOutletService outletService;
	private OutletDao outletDao;
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
	public void test() throws SQLException {
		SimpleDateFormat format = new SimpleDateFormat(SDF);
		List<Rule> rs = new ArrayList<Rule>();
		Outlet o = new Outlet();
		o.setMac("aasasasa");
		o.setId(11);
		Rule r1 = new Rule();
		r1.setCreater("5");
		r1.setName("ccc");
		r1.setStarts(Time.valueOf(format.format(new Date())));
		r1.setInterval("0000000");
		r1.setOutlet(o);
		
		for(int i=0;i<2;i++){
			rs.add(r1);
		}
		long start = new Date().getTime();
		outletService.addRuleBatch(rs);
		System.out.println("消耗"+(new Date().getTime()-start));
	}
}
