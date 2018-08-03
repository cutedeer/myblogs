package com.sushuzhuang.myblogs.services;

import com.sushuzhuang.myblogs.domain.Record;

import java.util.List;

public interface RecordService {
    List<Record> loadAll();

    Integer increaseReadAmount( Record record);
}
