package org.daming.hoteler.workflow.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.workflow.pojo.Customer;

/**
 * @author gming001
 * @version 2023-10-05 09:24
 */
@Mapper
public interface PingMapper {

    @Select("SELECT 'pong'")
    String ping() throws HotelerException;
}
