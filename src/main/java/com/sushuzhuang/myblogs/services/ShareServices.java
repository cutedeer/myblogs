package com.sushuzhuang.myblogs.services;

import com.sushuzhuang.myblogs.domain.Share;

import java.util.List;

public interface ShareServices {
    List<Share> loadAll();

    List<Share> loadByTag(String tag);
}
