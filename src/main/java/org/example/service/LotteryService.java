package org.example.service;


import org.example.dto.Prize;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LotteryService {

    private final List<Prize> prizes = new ArrayList<>();
    private final Map<String, Long> userDrawTimestamps = new ConcurrentHashMap<>();

    public LotteryService() {
        prizes.add(new Prize("iPhone 16", 0.05, 5));
        prizes.add(new Prize("Apple Watch", 0.10, 10));
        prizes.add(new Prize("AirPods", 0.20, 20));
        prizes.add(new Prize("銘謝惠顧", 0.65, Integer.MAX_VALUE));
    }

    public synchronized String drawPrize(String sessionId) {
        long now = System.currentTimeMillis();

        Long lastDrawTime = userDrawTimestamps.get(sessionId);

        if (lastDrawTime != null && now - lastDrawTime < 1000) {
            return "您剛剛已經抽過獎了，請稍後 1 秒再試！";
        }

        userDrawTimestamps.put(sessionId, now);

        double randomValue = Math.random();
        double cumulativeProbability = 0.0;

        for (Prize prize : prizes) {
            cumulativeProbability += prize.getProbability();

            if (randomValue <= cumulativeProbability) {
                if (prize.getQuantity() > 0) {
                    prize.setQuantity(prize.getQuantity() - 1);
                    return "你抽獎結果為：" + prize.getName();
                } else {
                    return "獎品 " + prize.getName() + " 已經抽完，請再試一次！";
                }
            }
        }
        return "發生錯誤，請稍後再試！";
    }
}