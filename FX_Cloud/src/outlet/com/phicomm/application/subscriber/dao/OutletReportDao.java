package com.phicomm.application.subscriber.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.phicomm.application.subscriber.model.OutletReport;
import com.phicomm.application.subscriber.model.RecordDay;
import com.phicomm.application.subscriber.model.RecordHoursDto;
import com.phicomm.application.subscriber.model.RecordMonth;
import com.phicomm.application.subscriber.model.RecordWeek;
import com.phicomm.application.subscriber.model.RecordYear;
import com.phicomm.application.subscriber.dao.IOutletReportDao;

@SuppressWarnings("unchecked")
@Repository("outletReportDao")
public class OutletReportDao extends HibernateDaoSupport implements IOutletReportDao {
	
	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	@Override
	public void add(OutletReport outletReport) {
		this.getSession().save(outletReport);
	}

	@Override
	public List<OutletReport> loadByOtlIdToDy(long otl_id) {
		return this.getSession().createQuery("from OutletReport otlrp left join fetch otlrp.outlet o where to_days(otlrp.reportTime) = to_days(now()) and o.id=?").setParameter(0, otl_id).list();
	}
	@Override
	public Date loadWeekFDByOtlId(long otl_id) {
		return (Date)this.getSession().createQuery("from RecordWeek rw where rw.outlet.id=? order by rw.recordTime desc")
				.setParameter(0, otl_id).setMaxResults(1).uniqueResult();
	}
	@Override
	public List<RecordDay> loadTWKwh(Date d,long otl_id) {
		return this.getSession().createQuery("from RecordDay rd where rd.outlet.id=? and rd.recordTime>=?").setParameter(0, otl_id).setParameter(1, d).list();
	}
	@Override
	public List<RecordDay> loadTMKwh(long otl_id) {
		return this.getSession().createQuery("from RecordDay rd where date_format(rd.recordTime, '%Y-%m')=date_format(curdate(),'%Y-%m') and rd.outlet.id=?")
				.setParameter(0, otl_id).list();
	}
	@Override
	public List<RecordMonth> loadTYKwh(long otl_id) {
		return this.getSession().createQuery("from RecordMonth rm where date_format(rm.recordTime, '%Y')=date_format(curdate(),'%Y') and rm.outlet.id=?")
				.setParameter(0, otl_id).list();
	}
	@Override
	public List<RecordDay> loadDaysKwh(long otl_id, int days) {
		return this.getSession().createQuery("from RecordDay rd where rd.outlet.id=? order by rd.recordTime desc")
				.setParameter(0, otl_id).setParameter(1, days).setMaxResults(days).list();
	}
	@Override
	public List<RecordWeek> loadWeeksKwh(long otl_id, int weeks) {
		return this.getSession().createQuery("from RecordWeek rw where rw.outlet.id=? order by rw.recordTime desc")
				.setParameter(0, otl_id).setParameter(1, weeks).setMaxResults(weeks).list();
	}
	@Override
	public List<RecordMonth> loadMonthsKwh(long otl_id, int months) {
		return this.getSession().createQuery("from RecordMonth rm where rm.outlet.id=? order by rm.recordTime desc")
				.setParameter(0, otl_id).setParameter(1, months).setMaxResults(months).list();
	}
	@Override
	public List<RecordYear> loadYearsKwh(long otl_id, int years) {
		return this.getSession().createQuery("from RecordYear ry where ry.outlet.id=? order by ry.recordTime desc")
				.setParameter(0, otl_id).setParameter(1, years).setMaxResults(years).list();
	}
	
	@Override
	public List<RecordHoursDto> loadHoursKwh(long otl_id, int hs) {
		return (List<RecordHoursDto>)this.getSession().createQuery("select new com.phicomm.application.subscriber.model.RecordHoursDto(op.outlet.id,hour(op.reportTime),sum(op.kwh)) "+
				"from OutletReport op where op.outlet.id=? and TO_DAYS(op.reportTime)=TO_DAYS(NOW()) group by col_1_0_ order by col_1_0_ desc")
				.setParameter(0, otl_id).setMaxResults(hs).list();
		/*return (List<RecordHoursDto>)this.getSession().createSQLQuery("SELECT otl_id as id,HOUR(report_time) AS hours,"+
				"SUM(rep_kwh) AS kwh FROM cloud_outlet_report WHERE otl_id=? AND TO_DAYS(report_time)=TO_DAYS(NOW()) "+
				"GROUP BY hours ORDER BY hours DESC LIMIT ?").setResultTransformer(Transformers.aliasToBean(RecordHoursDto.class))
				.setParameter(0, otl_id).setParameter(1, hours).list();*/
		
	}
	@Override
	public List<RecordHoursDto> loadToHoursKwh(long otl_id) {
		return (List<RecordHoursDto>)this.getSession().createQuery("select new com.phicomm.application.subscriber.model.RecordHoursDto(op.outlet.id,hour(op.reportTime),sum(op.kwh)) "+
				"from OutletReport op where op.outlet.id=? and TO_DAYS(op.reportTime)=TO_DAYS(NOW()) group by col_1_0_")
				.setParameter(0, otl_id).list();
	}
	@Override
	public List<RecordMonth> loadToMonthKwh(long otl_id) {
		return this.getSession().createQuery("from RecordMonth rm where date_format(rm.recordTime, '%Y')=date_format(curdate(),'%Y') and rm.outlet.id=?")
				.setParameter(0, otl_id).list();
	}
}
