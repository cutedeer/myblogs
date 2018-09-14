package com.sushuzhuang.myblogs.services.impl;

import com.sushuzhuang.myblogs.domain.MessageBoard;
import com.sushuzhuang.myblogs.domain.dto.MessageBoardDto;
import com.sushuzhuang.myblogs.mapper.MessageBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageBoardServiceImpl {

    @Autowired
    private MessageBoardMapper messageBoardMapper;

    public MessageBoard insert (MessageBoard messageBoard){
        messageBoard.setLeaveTime(new Date());
        messageBoard.setDeleteFlag(0);
        messageBoardMapper.insertSelective(messageBoard);
        return messageBoard;
    }

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    public Integer delete(Long id){
       MessageBoard messageBoard = new MessageBoard();
       messageBoard.setId(id);
       messageBoard.setDeleteFlag(1);
       messageBoardMapper.updateByPrimaryKeySelective(messageBoard);
        return 0;
    }

    /**
     * 查询所有留言
     * @param replyId 回复目标id
     * @return
     */
    public List<MessageBoardDto> select(Long replyId){
        return messageBoardMapper.selectAll(replyId);
    }

}
