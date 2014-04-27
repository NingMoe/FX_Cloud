package com.phicomm.application.subscriber.dao;

import java.util.Date;
import java.util.List;

import com.phicomm.application.subscriber.model.OutletReport;
import com.phicomm.application.subscriber.model.RecordDay;
import com.phicomm.application.subscriber.model.RecordHoursDto;
import com.phicomm.application.subscriber.model.RecordMonth;
import com.phicomm.application.subscriber.model.RecordWeek;
import com.phicomm.application.subscriber.model.RecordYear;

public interface IOutletReportDao {
	/**
	 * 添加上报的功耗信息
	 * @param outletReport
	 */
	public void add(OutletReport outletReport);

	/**
	 * 根据插座ID获取当天的功耗信息
	 * @param otl_id
	 * @return
	 */
	public List<OutletReport> loadByOtlIdToDy(long otl_id);
	
	/**
	 * 根据插座ID获取当前周的第一天的时间
	 * @param otl_id
	 * @return
	 */
	public Date loadWeekFDByOtlId(long otl_id);
	/**
	 * 获取当前周的功耗（不包括当天）
	 * @param d
	 * @param otl_id
	 * @return
	 */
	public List<RecordDay> loadTWKwh(Date d,long otl_id);
	/**
	 * 获取当前月的功耗（不包括当天）
	 * @param otl_id
	 * @return
	 */
	public List<RecordDay> loadTMKwh(long otl_id);
	/**
	 * 获取当前年的功耗（不包括当月）
	 * @param otl_id
	 * @return
	 */
	public List<RecordMonth> loadTYKwh(long otl_id);
	/**
	 * 获取前days天的功耗
	 * @param otl_id
	 * @param days
	 * @return
	 */
	public List<RecordDay> loadDaysKwh(long otl_id,int days);
	/**
	 * 获取前weeks周的功耗
	 * @param otl_id
	 * @param days
	 * @return
	 */
	public List<RecordWeek> loadWeeksKwh(long otl_id,int weeks);
	/**
	 * 获取前months月的功耗
	 * @param otl_id
	 * @param days
	 * @return
	 */
	public List<RecordMonth> loadMonthsKwh(long otl_id,int months);
	/**
	 * 获取前years年的功耗
	 * @param otl_id
	 * @param days
	 * @return
	 */
	public List<RecordYear> loadYearsKwh(long otl_id,int years);
	/**
	 * 统计前几个小时的功耗
	 * @param otl_id
	 * @param hours
	 * @return
	 */
	public List<RecordHoursDto> loadHoursKwh(long otl_id, int hs);
	/**
	 * 统计当天每个小时的功耗
	 * @param otl_id
	 * @param hours
	 * @return
	 */
	public List<RecordHoursDto> loadToHoursKwh(long otl_id);
	/**
	 * 统计当年每月的功耗
	 * @param otl_id
	 * @param hours
	 * @return
	 */
	public List<RecordMonth> loadToMonthKwh(long otl_id);
}
