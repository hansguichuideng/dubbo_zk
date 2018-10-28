package com.summer.mypay.service.websocket;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.summer.mypay.pojo.ClientMessage;
import com.summer.mypay.pojo.ReturnResult;
import com.summer.mypay.websocket.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * websocket服务类,由于外界访问与客户端之间没有在一个连接,故进行包装
 */
@Service
public class WebSocketService {


    private static WebSocketService me;


    @Autowired
    private WebSocketHandler webSocketHandler;


    //读写锁
    protected ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


    {
        me = this;
    }

    public ReturnResult sendMessage(ClientMessage clientMessage) {
        return webSocketHandler.sendMessage(clientMessage);
    }


    /**
     * 客户端返回结果集
     */
    Cache<String, Object> client_index_content = CacheBuilder.newBuilder().expireAfterAccess(1, TimeUnit.MINUTES).build();


    public void writeWebScoketResult(String mid, String content) {
        try {
            lock.writeLock().lock();
            client_index_content.put(mid, content);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Object readWebScoketResult(String mid) {
        try {
            lock.readLock().lock();
            return client_index_content.getIfPresent(mid);
        } finally {
            lock.readLock().unlock();
        }
    }


    /**
     * 发读取后,rwmove掉
     *
     * @return
     */
    public Object readWebScoketResultAndRemove(String mid) {
        try {
            lock.readLock().lock();
            Object result = client_index_content.getIfPresent(mid);

            client_index_content.invalidate(mid);

            return result;
        } finally {
            lock.readLock().unlock();
        }
    }


}
