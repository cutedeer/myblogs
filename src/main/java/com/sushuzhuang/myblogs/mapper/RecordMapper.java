package com.sushuzhuang.myblogs.mapper;

import com.sushuzhuang.myblogs.domain.Record;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RecordDAO继承基类
 */
@Repository
public interface RecordMapper  {

    List<Record> loadAll();

    Integer increaseReadAmount(Record record);

}