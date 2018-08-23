package com.sushuzhuang.myblogs.services.impl;

import com.sushuzhuang.myblogs.domain.Share;
import com.sushuzhuang.myblogs.mapper.ShareMapper;
import com.sushuzhuang.myblogs.services.ShareServices;


import com.sushuzhuang.myblogs.utils.DateFormatTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShareServiceImpl implements ShareServices {

    @Autowired
    private ShareMapper shareMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DateFormatTool dateFormatTool;

    private static final Logger LOGGER = LoggerFactory.getLogger(ShareServiceImpl.class);

    @Override
    public List<Share> loadAll() {
        return shareMapper.loadAll();
    }



    @Override
    public List<Share> loadByTag(String tag) {
        String key = "share-"+tag;
        ListOperations<String, List<Share>> operations = redisTemplate.opsForList();
        // 缓存是否存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            List<Share> shares  =redisTemplate.opsForList().range(key, 0, 1);
            LOGGER.info("ShareServices.loadByTag() : 从缓存中获取了share >> " + shares.toString());
            return shares;
        }

        // 从 DB 中获取信息
        List<Share> shares = shareMapper.loadByTag(tag);

        // 插入缓存
        operations.leftPush(key, shares);

        LOGGER.info("ShareServices.loadByTag() : share插入缓存 >> " + shares.toString());
        return shareMapper.loadByTag(tag);
    }

    }
