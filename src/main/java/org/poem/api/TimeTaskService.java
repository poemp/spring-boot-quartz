package org.poem.api;

import org.poem.entity.TimeTask;

import java.util.List;

/**
 * @author poem
 */
public interface TimeTaskService {

    /**
     * 查詢全部的數據
     * @return
     */
    public List<TimeTask> findAllTask();
}
