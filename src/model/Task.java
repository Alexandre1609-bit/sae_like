package model;
import java.time.LocalDate;
import java.util.Date;

public class Task {
    private String title;
    private Integer taskId;
    private boolean taskStatus;
    private java.time.LocalDate startDate;
    private String description;
    private User accountantId;

    public Task(String title, Integer taskId, boolean taskStatus, java.time.LocalDate startDate, User accountantId) {
        this.title = title;
        this.taskId = taskId;
        this.taskStatus = taskStatus;
        this.startDate = startDate;
        this.accountantId = accountantId;
        this.description = null;


    }
    public Task() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public User getAccountant() {
        return accountantId;
    }

    public void setAccountant(User accountant) {
        this.accountantId = accountant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public boolean isTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(boolean taskStatus) {
        this.taskStatus = taskStatus;
    }
}