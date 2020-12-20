package com.li.juc.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : hutools雪花
 * @date : 2020-12-20 16:19:47
 */
@Slf4j
public class IdGeneratorSnowflake {
    private long workId = 0;
    private long datacenterId = 1;
    private Snowflake snowflake = IdUtil.createSnowflake(workId, datacenterId);

//    @PostConstruct //启动项目时加载
    public void init() {
        try {
            workId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            log.info("当前机工的workdId:" + workId);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("当前机器的workID获取失败", e);
            workId = NetUtil.getLocalhostStr().hashCode();
        }

    }

    /**
     * 生成Id
     *
     * @return
     */
    public synchronized long snowflakeId() {
        return snowflake.nextId();
    }

    public static void main(String[] args) {
        IdGeneratorSnowflake idGeneratorSnowflake = new IdGeneratorSnowflake();
        idGeneratorSnowflake.init();
        for (int i = 0; i < 100; i++) {
            System.out.println(idGeneratorSnowflake.snowflakeId());
        }
    }
}
