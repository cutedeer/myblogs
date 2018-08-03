package com.sushuzhuang.myblogs.mapper;

import com.sushuzhuang.myblogs.domain.Share;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareMapper {

    List<Share> loadAll();

    List<Share> loadByTag(String tag);
}
