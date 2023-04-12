package  org.daming.hoteler.workflow.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import  org.daming.hoteler.workflow.pojo.Room;
import  org.daming.hoteler.workflow.pojo.enums.RoomStatus;
import  org.daming.hoteler.workflow.pojo.handler.RoomStatusTypeHandler;

import java.util.List;

/**
 * @author daming
 * @version 2023-04-08 11:16
 **/
@Mapper
public interface RoomMapper {

    @Select("insert into rooms (name, price, status, create_dt, create_user, update_dt, update_user) values (#{name}, #{price}, #{status, typeHandler= org.daming.hoteler.workflow.pojo.handler.RoomStatusTypeHandler}, statement_timestamp(),'system', statement_timestamp(), 'system') returning id")
    String create(Room room);

    @Delete("update rooms set update_dt = statement_timestamp(), update_user = 'system', deleted_at = statement_timestamp() where id = #{id}")
    void delete(String id);

    @Select("select id, name, price, status from rooms where id = #{id}::uuid and deleted_at is null")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "price", property = "price"),
            @Result(column = "status", property = "status", typeHandler = RoomStatusTypeHandler.class)
    })
    Room get(String id);

    @Select("select id, name, price, status from rooms where name = #{name} and deleted_at is null")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "price", property = "price"),
            @Result(column = "status", property = "status", typeHandler = RoomStatusTypeHandler.class)
    })
    Room getByName(String name);

    @Select("select id, name, price, status from rooms where deleted_at is null")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "price", property = "price"),
            @Result(column = "status", property = "status", typeHandler = RoomStatusTypeHandler.class)
    })
    List<Room> list();

    @Update("update rooms set name = #{name}, price = #{price}, status = #{status, typeHandler= org.daming.hoteler.workflow.pojo.handler.RoomStatusTypeHandler}, update_dt = statement_timestamp(), update_user = 'system' where id = #{id}")
    void update(Room room);

    @Update("update rooms set status = #{status, typeHandler= org.daming.hoteler.workflow.pojo.handler.RoomStatusTypeHandler}, update_dt = statement_timestamp(), update_user = 'system' where id = #{id}")
    void updateStatus(@Param("id") String id, @Param("status") RoomStatus status);
}
