package org.daming.hoteler.workflow.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.workflow.pojo.Customer;

import java.util.List;

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


    @Update("""
    UPDATE customers 
        set name = #{name}, 
        gender = #{gender}::gender,
        card_id = #{cardId},
        phone = #{phone},
        update_dt = statement_timestamp(),
        update_user = 'system' 
    WHERE id = #{name}      
    """)
    void update(Customer customer) throws HotelerException;

    @Select("""
    SELECT id, name, gender, card_id as cardId, phone FROM customers WHERE deleted_at is null       
    """)
    List<Customer> list() throws HotelerException;
}
