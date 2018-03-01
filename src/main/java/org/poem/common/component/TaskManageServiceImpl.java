package org.poem.common.component;

import org.poem.api.task.TaskManageService;
import org.poem.common.key.JobDetailPrimaryKey;
import org.poem.entity.TimeTask;
import org.poem.job.ScheduledJobExecutor;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * quartz 定时任务管理
 */
@Service
public class TaskManageServiceImpl implements TaskManageService {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(TaskManageServiceImpl.class);

    /**
     *
     */
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    /**
     * 添加和修改 定时任务
     * @param timeTasks
     */
    @Override
    public void addOrUpdateTask(List<TimeTask> timeTasks) throws SchedulerException {
        if(CollectionUtils.isEmpty(timeTasks)){
            logger.info("没有任何的定时任务需要执行！");
            return;
        }
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey;
        for (TimeTask timeTask : timeTasks) {
            triggerKey = TriggerKey.triggerKey(timeTask.getId());
            //检查任务是否存在
            if(scheduler.checkExists(triggerKey)){
                //任务存在
                CronTrigger cronTrigger = (CronTrigger)scheduler.getTrigger(triggerKey);
                String cronExpression = cronTrigger.getCronExpression();
                //检查任务是否改变
                if(timeTask.getTimeTaskSwitch() && cronExpression.equals(timeTask.getCronScheduleExpression())){
                    logger.info("定时任务没有变化->> " + timeTask + "");
                    return;
                }
                //停止触发器
                scheduler.pauseTrigger(triggerKey);
                //移除触发器
                scheduler.unscheduleJob(triggerKey);
                scheduler.deleteJob(new JobKey(timeTask.getId()));//删除任务
                logger.info("删除定时任务->> " + timeTask + "");
            }
            //添加任务
            if(timeTask.getTimeTaskSwitch()){
                JobDetail jobDetail = JobBuilder.newJob(ScheduledJobExecutor.class).withIdentity(new JobKey(timeTask.getId())).build();
                jobDetail.getJobDataMap().put(JobDetailPrimaryKey.DETAIL_KEY,timeTask);
                //设置表达式
                CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(timeTask.getCronScheduleExpression());
                //创建一个新的Tigger
                Trigger trigger = TriggerBuilder.newTrigger().withIdentity(timeTask.getId()).withSchedule(cronScheduleBuilder).build();
                scheduler.scheduleJob(jobDetail,trigger);
                logger.info("添加定时任务->> " + timeTask + "");
            }
        }
    }
}
