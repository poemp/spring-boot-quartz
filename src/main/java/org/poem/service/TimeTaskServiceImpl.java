package org.poem.service;

import org.poem.api.TimeTaskService;
import org.poem.dao.TimeTaskDao;
import org.poem.entity.TimeTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 查詢全部的數據
 * @author poem
 */
@Service
public class TimeTaskServiceImpl implements TimeTaskService {

    @Autowired
    private TimeTaskDao timeTaskDao;

    /**
     * 查询全部的数据
     * @return
     */
    @Override
    public List<TimeTask> findAllTask() {
        return this.timeTaskDao.findAll();
    }
}
