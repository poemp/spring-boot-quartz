package org.poem.api.task;

import org.poem.entity.TimeTask;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * @author poem
 */
public interface TaskManageService  {

    /**
     * 添加任务
     * @param timeTasks
     */
    public  void addOrUpdateTask(List<TimeTask> timeTasks) throws SchedulerException;
}
