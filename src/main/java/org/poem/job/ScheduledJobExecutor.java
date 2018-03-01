package org.poem.job;

import org.poem.common.key.JobDetailPrimaryKey;
import org.poem.common.utils.ApplicationContext;
import org.poem.entity.TimeTask;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * 定时任务的执行
 * 把任务添加进来就可以执行
 * 这个是spring quartz自动执行的
 */
public class ScheduledJobExecutor implements Job {

    /**
     * 日志管理
     */
    private static final Logger logger = LoggerFactory.getLogger(ScheduledJobExecutor.class);

    /**
     * 执行器
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        TimeTask config = (TimeTask) jobExecutionContext.getMergedJobDataMap().get(JobDetailPrimaryKey.DETAIL_KEY);
        Long startTime = System.currentTimeMillis();
        try {
            if (config != null) {
                Class clazz;
                String taskClass = config.getTaskClassName();
                if (!StringUtils.isEmpty(taskClass)) {
                    Object clsObj = ApplicationContext.getBean(config.getTaskClassName());
                    if (null == clsObj) {
                        logger.info("定时任务:" + config.getTaskName() + "指定的类未被Spring找到!");
                        return;
                    }
                    clazz = clsObj.getClass();
                    //得到指定的方法
                    Method method = clazz.getDeclaredMethod(config.getTaskClassMethod());
                    if (null == method) {
                        logger.info("定时任务:" + config.getTaskName() + "指定的方法未被Spring找到!");
                        return;
                    }
                    //执行方法
                    method.invoke(clsObj);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            logger.error("执行任务： "+config+"，错误描述："+e.getMessage());
            e.printStackTrace();
        }
        Long endTime = System.currentTimeMillis();
        logger.info("执行任务：["+config+"]成功，总耗时：["+(endTime-startTime)+"毫秒]");
    }
}
