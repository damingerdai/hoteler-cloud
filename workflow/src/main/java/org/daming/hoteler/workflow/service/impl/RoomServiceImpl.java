package  org.daming.hoteler.workflow.service.impl;

import org.daming.hoteler.common.constants.ErrorCodeConstants;
import org.daming.hoteler.common.errors.IErrorService;
import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.workflow.pojo.Room;
import org.daming.hoteler.workflow.pojo.enums.RoomStatus;
import org.daming.hoteler.workflow.repository.RoomMapper;
import org.daming.hoteler.workflow.service.IRoomService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author daming
 * @version 2023-04-07 23:17
 **/
@Service
public class RoomServiceImpl implements IRoomService {

    private final RoomMapper roomMapper;
    private final IErrorService errorService;

    @Transactional
    @Override
    public String create(Room room) throws HotelerException {
        try {
            var id = this.roomMapper.create(room);
            room.setId(id);

            return id;
        } catch (DataAccessException dae) {
            throw this.errorService.createHotelerException(ErrorCodeConstants.SQL_ERROR_CODE, new Object[]{ dae.getMessage() }, dae.getRootCause());
        }

    }

    @Override
    public Room get(String id) throws HotelerException {
        try {
            return this.roomMapper.get(id);
        } catch (DataAccessException dae) {
            throw this.errorService.createHotelerException(ErrorCodeConstants.SQL_ERROR_CODE, new Object[]{ dae.getMessage() }, dae.getRootCause());
        }
    }

    @Override
    public void update(Room room) throws HotelerException {
        try {
            this.roomMapper.update(room);
        } catch (DataAccessException dae) {
            throw this.errorService.createHotelerException(ErrorCodeConstants.SQL_ERROR_CODE, new Object[]{ dae.getMessage() }, dae.getRootCause());
        }
    }

    @Override
    public List<Room> list(Room room) throws HotelerException {
        //return this.roomMapper.get();
        throw new RuntimeException("尚未实现");
    }

    @Override
    public List<Room> list() throws HotelerException {
        try {
            return this.roomMapper.list();
        } catch (DataAccessException dae) {
            throw this.errorService.createHotelerException(ErrorCodeConstants.SQL_ERROR_CODE, new Object[]{ dae.getMessage() }, dae.getRootCause());
        }
    }

    @Override
    public void delete(String id) throws HotelerException {
        try {
            this.roomMapper.delete(id);
        } catch (DataAccessException dae) {
            throw this.errorService.createHotelerException(ErrorCodeConstants.SQL_ERROR_CODE, new Object[]{ dae.getMessage() }, dae.getRootCause());
        }
    }

    @Override
    public void updateStatus(String id, RoomStatus status) throws HotelerException {
        try {
            this.roomMapper.updateStatus(id, status);
        } catch (DataAccessException dae) {
            throw this.errorService.createHotelerException(ErrorCodeConstants.SQL_ERROR_CODE, new Object[]{ dae.getMessage() }, dae.getRootCause());
        }

    }

    public RoomServiceImpl(RoomMapper roomMapper, IErrorService errorService) {
        super();
        this.roomMapper = roomMapper;
        this.errorService = errorService;
    }
}
