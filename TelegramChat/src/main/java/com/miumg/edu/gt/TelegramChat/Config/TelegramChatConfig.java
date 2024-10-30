/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miumg.edu.gt.TelegramChat.Config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import com.miumg.edu.gt.TelegramChat.Service.BootTelegramChatService;

/**
 *
 * @author strdh
 */
@Configuration
@Slf4j
public class TelegramChatConfig {
    
    @Bean
    public BootTelegramChatService telegramBot(@Value("${bot.name}") String botName,
                       @Value("${bot.token}")String botToken){
        BootTelegramChatService telegramBot = new BootTelegramChatService(botName,botToken);
        try {
            var telegramBotApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotApi.registerBot(telegramBot);
        }catch (TelegramApiException e){
            log.error("Exception during TelegramBot registration: {}", e.getMessage());
        }
        return telegramBot;

    }
}
