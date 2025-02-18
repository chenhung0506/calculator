package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/lottery")
public class LotteryController {

    @Autowired
    private LotteryService lotteryService;

    @GetMapping("/draw")
    public String drawPrize(HttpSession session) {
        String sessionId = session.getId();
        return lotteryService.drawPrize(sessionId);
    }
}