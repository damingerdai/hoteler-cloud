package org.daming.hoteler.workflow.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.workflow.pojo.Customer;

/**
 * @author daming
 * @version 2023-04-14 17:47
 **/
@Mapper
public interface CustomerMapper {

    @Select("""
        INSERT INTO customers 
            (name, gender, card_id, phone, create_dt, create_user, update_dt, update_user) 
        VALUES (#{name}, #{gender}::gender, #{cardId}, #{phone}, statement_timestamp(), 'system', statement_timestamp(), 'system')
        RETURNING id
     """)
    String create(Customer customer) throws HotelerException;

    @Select("""
            SELECT id, name, gender, card_id as cardId, phone FROM customers WHERE id = #{id}::uuid and deleted_at is null
            """)
    Customer get(String id) throws HotelerException;
}
