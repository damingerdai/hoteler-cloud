package org.daming.hoteler.workflow.service.impl;

import org.daming.hoteler.common.exceptions.HotelerException;
import org.daming.hoteler.workflow.repository.PingMapper;
import org.daming.hoteler.workflow.service.IPingService;
import org.springframework.stereotype.Service;

/**
 * @author gming001
 * @version 2023-10-05 09:25
 */
@Service
public class PingServiceImpl implements IPingService {

    private PingMapper pingMapper;

    @Override
    public String ping() throws HotelerException {
        return this.pingMapper.ping();
    }

    public PingServiceImpl(PingMapper pingMapper) {
        super();
        this.pingMapper = pingMapper;
    }
}
