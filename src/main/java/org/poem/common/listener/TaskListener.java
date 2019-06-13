package org.poem.common.listener;

import org.poem.api.TimeTaskService;
import org.poem.common.component.TaskManageServiceImpl;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * @author poem
 */
@Service
public class TaskListener  implements ApplicationListener{

    @Autowired
    private TimeTaskService timeTaskService;

    @Autowired
    private TaskManageServiceImpl taskManageService;

    /**
     * 启动的时候就把任务全部放进去，开始执行
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            try {
                taskManageService.addOrUpdateTask(this.timeTaskService.findAllTask());
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
    }
}
