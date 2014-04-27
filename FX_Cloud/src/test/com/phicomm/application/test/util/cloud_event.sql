#show variables like 'event_scheduler';

#######################################################every day##############################################################################
DELIMITER //
DROP PROCEDURE IF EXISTS `pro_cloud_red_day`//
create procedure `pro_cloud_red_day`()
begin
	START TRANSACTION;  
		INSERT INTO `cloud_outlet_report_copy` SELECT * FROM `cloud_outlet_report` FOR UPDATE;
		INSERT INTO `cloud_record_day`(otl_id,now_kwh) SELECT otl_id,SUM(rep_kwh) FROM `cloud_outlet_report` WHERE report_time between DATE_SUB(now(),interval 1 day) and now() GROUP BY otl_id FOR UPDATE;
		DELETE FROM `cloud_outlet_report` WHERE report_time between DATE_SUB(curdate(),interval 1 day) and now();
	COMMIT;
END;
//
DELIMITER ;

DELIMITER //
DROP EVENT IF EXISTS `evt_cloud_red_day`;
CREATE EVENT `evt_cloud_red_day` ON SCHEDULE EVERY 1 DAY STARTS "2014-1-22 0:01:00" DO CALL `pro_cloud_red_day`//
DELIMITER ;

#######################################################every week#############################################################################
DELIMITER //
DROP PROCEDURE IF EXISTS `pro_cloud_red_week`//
create procedure `pro_cloud_red_week`()
begin
	START TRANSACTION;  
		INSERT INTO `cloud_record_week`(otl_id,now_kwh) SELECT otl_id,SUM(now_kwh) FROM `cloud_record_day` WHERE red_time between date_sub(now(),interval 1 week) and now() GROUP BY otl_id FOR UPDATE;
		DELETE FROM `cloud_record_day` WHERE red_time between date_sub(now(),interval 7 day) and now();
	COMMIT;
END;
//
DELIMITER ;

DELIMITER //
DROP EVENT IF EXISTS `evt_cloud_red_week`;
CREATE EVENT `evt_cloud_red_week` ON SCHEDULE EVERY 1 WEEK STARTS "2014-3-17 1:01:00" DO CALL `pro_cloud_red_week`//
DELIMITER ;

#######################################################every month############################################################################
DELIMITER //
DROP PROCEDURE IF EXISTS `pro_cloud_red_month`//
create procedure `pro_cloud_red_month`()
begin
	START TRANSACTION;  
		INSERT INTO `cloud_record_month`(otl_id,now_kwh) SELECT otl_id,SUM(now_kwh) FROM `cloud_record_week` WHERE red_time between date_sub(now(),interval 1 month) and now() GROUP BY otl_id FOR UPDATE;
		DELETE FROM `cloud_record_week` WHERE red_time between date_sub(now(),interval 1 month) and now();
	COMMIT;
END;
//
DELIMITER ;

DELIMITER //
DROP EVENT IF EXISTS `evt_cloud_red_month`;
CREATE EVENT `evt_cloud_red_month` ON SCHEDULE EVERY 1 MONTH STARTS "2014-4-1 2:01:00" DO CALL `pro_cloud_red_month`//
DELIMITER ;

#######################################################every year#############################################################################
DELIMITER //
DROP PROCEDURE IF EXISTS pro_cloud_red_year//
create procedure pro_cloud_red_year()
begin
	START TRANSACTION;  
		INSERT INTO cloud_record_year(otl_id,now_kwh) SELECT otl_id,SUM(now_kwh) FROM cloud_record_month WHERE red_time between date_sub(now(),interval 1 year) and now() GROUP BY otl_id FOR UPDATE;
		DELETE FROM cloud_record_month WHERE red_time between date_sub(now(),interval 1 year) and now();
	COMMIT;
END;
//
DELIMITER ;

DELIMITER //
DROP EVENT IF EXISTS evt_cloud_red_year;
CREATE EVENT evt_cloud_red_year ON SCHEDULE EVERY 1 MONTH STARTS "2015-1-1 3:01:00" DO CALL `pro_cloud_red_year`//
DELIMITER ;



