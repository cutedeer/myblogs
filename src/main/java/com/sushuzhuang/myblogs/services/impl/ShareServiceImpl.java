package com.sushuzhuang.myblogs.services.impl;

import com.sushuzhuang.myblogs.domain.Share;
import com.sushuzhuang.myblogs.mapper.ShareMapper;
import com.sushuzhuang.myblogs.services.ShareServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShareServiceImpl implements ShareServices {

    @Autowired
    private ShareMapper shareMapper;

    @Override
    public List<Share> loadAll() {
        return shareMapper.loadAll();
    }

    @Override
    public List<Share> loadByTag(String tag) {
        return shareMapper.loadByTag(tag);
    }
}
