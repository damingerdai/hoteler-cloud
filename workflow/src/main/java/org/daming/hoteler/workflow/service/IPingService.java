package org.daming.hoteler.workflow.service;

import org.daming.hoteler.common.exceptions.HotelerException;

/**
 * @author gming001
 * @version 2023-10-05 09:25
 */
public interface IPingService {

    String ping() throws HotelerException;
}
