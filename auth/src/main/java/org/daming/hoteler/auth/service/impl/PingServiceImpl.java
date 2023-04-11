package org.daming.hoteler.auth.service.impl;

import org.daming.hoteler.auth.repository.IPingDao;
import org.daming.hoteler.auth.service.IPingService;
import org.springframework.stereotype.Service;

/**
 * @author gming001
 * @version 2022-12-04 18:32
 */
@Service
public class PingServiceImpl implements IPingService {

    private final IPingDao pingDao;

    @Override
    public String ping() {
        return this.pingDao.ping();
    }

    public PingServiceImpl(IPingDao pingDao) {
        super();
        this.pingDao = pingDao;
    }
}
