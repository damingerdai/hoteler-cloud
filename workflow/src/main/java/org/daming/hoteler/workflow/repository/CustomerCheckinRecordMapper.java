package org.daming.hoteler.workflow.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.workflow.pojo.CustomerCheckinRecord;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author gming001
 * @version 2024-01-04 20:35
 */
@Mapper
public interface CustomerCheckinRecordMapper {

    @Select("""
     insert into customer_checkin_record
     (customer_id, room_id, begin_date, end_date, create_dt, create_user, update_dt, update_user)
     values ( #{customerId}::uuid, #{roomId}::uuid, #{beginDate}, #{endDate}, statement_timestamp(), 'system', statement_timestamp(), 'system')
     returning id
     """)
    String create(CustomerCheckinRecord customerCheckinRecord) throws HotelerException;

    @Update("""
    update customer_checkin_record
    set customer_id = #{customerId}::uuid, room_id = #{roomId}::uuid, begin_date = #{beginDate}, end_date = #{endDate},
    update_dt = statement_timestamp(), update_user = 'system' where id = #{id}
    """)
    void update(CustomerCheckinRecord customerCheckinRecord) throws HotelerException;

    @Select("""
      select
        id, customer_id as customerId, room_id as roomId,
        begin_date as beginDate, end_date as endDate
      from customer_checkin_record where id = #{id}  and deleted_at is null 
    """)
    CustomerCheckinRecord get(String id) throws HotelerException;

    @Update("""
      update customer_checkin_record
        set update_dt = statement_timestamp(), update_user = 'system', deleted_at = statement_timestamp()
      where id = #{id}
    """)
    void delete(String id) throws HotelerException;

    @Select("""
    select id, customer_id as customerId, room_id as roomId, begin_date as beginDate, end_date as endDate
    from customer_checkin_record
    where deleted_at is null order by create_dt desc, update_dt desc    
    """)
    List<CustomerCheckinRecord> list() throws HotelerException;

    @Select("select count(*) from customer_checkin_record where #{beginDate} <= begin_date and begin_date <= #{endDate} and end_date <= #{endDate} and deleted_at is null")
    int getUserRoomCounts(@Param("beginDate") LocalDateTime beginDate, @Param("endDate")LocalDateTime endDate);
}
