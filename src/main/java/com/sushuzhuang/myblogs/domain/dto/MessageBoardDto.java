package com.sushuzhuang.myblogs.domain.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * message_board
 * @author
 */
public class MessageBoardDto implements Serializable {
    private Long id;

    private Long userId;

    private String message;

    private Date leaveTime;

    private String replyName;

    private Integer deleteFlag;

    private static final long serialVersionUID = 1L;

    private List<MessageBoardDto> messageBoardDtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }


    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getReplyName() {
        return replyName;
    }

    public void setReplyName(String replyName) {
        this.replyName = replyName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "MessageBoardDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", message='" + message + '\'' +
                ", leaveTime=" + leaveTime +
                ", replyName='" + replyName + '\'' +
                ", deleteFlag=" + deleteFlag +
                ", messageBoardDtos=" + messageBoardDtos +
                '}';
    }
}