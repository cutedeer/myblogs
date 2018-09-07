package com.sushuzhuang.myblogs.services.impl;

import com.sushuzhuang.myblogs.domain.Record;
import com.sushuzhuang.myblogs.mapper.RecordMapper;
import com.sushuzhuang.myblogs.services.RecordService;
import com.sushuzhuang.myblogs.utils.DateFormatTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;

    private DateFormatTool dateFormatTool ;

    @Override
    public List<Record> loadAll() {
        dateFormatTool=new  DateFormatTool();
        List<Record> recordList =  recordMapper.loadAll();
        for(Record record : recordList) {
                record.setShowDate(dateFormatTool.format(record.getPublishDate(),"yyyy-MM-dd HH:mm:ss"));
        }
        return  recordList;
    }

    @Override
    public Integer increaseReadAmount(Record record) {
        return recordMapper.increaseReadAmount(record);
    }
}
