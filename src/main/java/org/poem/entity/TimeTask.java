package org.poem.entity;

import org.poem.basic.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TIME_TASK")
public class TimeTask extends IdEntity {

    /**
     * 任务名称
     */
    @Column(name = "TASK_NAME")
    private String taskName;

    /**
     * 定时任务的时间的表达式
     */
    @Column(name = "CRON_SCHEDULE_EXPRESSION")
    private String cronScheduleExpression;

    /**
     * 定时任务的开启和关闭
     */
    @Column(name = "TIME_TASK_SWITCH")
    private Boolean timeTaskSwitch;

    /**
     * 定时任务需要执行的类
     */
    @Column(name = "TASK_CLASS_NAME")
    private String taskClassName;

    /**
     * 定时任务需要执行的方法
     */
    @Column(name = "TASK_CLASS_METHOD")
    private String taskClassMethod;
    /**
     * 描述
     */
    @Column(name = "DESCRIPTION")
    private String description;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getCronScheduleExpression() {
        return cronScheduleExpression;
    }

    public void setCronScheduleExpression(String cronScheduleExpression) {
        this.cronScheduleExpression = cronScheduleExpression;
    }

    public Boolean getTimeTaskSwitch() {
        return timeTaskSwitch;
    }

    public void setTimeTaskSwitch(Boolean timeTaskSwitch) {
        this.timeTaskSwitch = timeTaskSwitch;
    }

    public String getTaskClassName() {
        return taskClassName;
    }

    public void setTaskClassName(String taskClassName) {
        this.taskClassName = taskClassName;
    }

    public String getTaskClassMethod() {
        return taskClassMethod;
    }

    public void setTaskClassMethod(String taskClassMethod) {
        this.taskClassMethod = taskClassMethod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "定时任务:[" + this.taskClassName + "],任务信息:["+ this.getTaskClassName() + "." + this.getTaskClassMethod() + ":" + this.getCronScheduleExpression()+ "]";
    }
}
