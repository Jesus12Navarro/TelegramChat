/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miumg.edu.gt.TelegramChat.Service;

import com.miumg.edu.gt.TelegramChat.Model.ChatSms;
import com.miumg.edu.gt.TelegramChat.Repository.ChatSmsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author strdh
 */

@Slf4j
@Service
public class SmsService {
    
    @Autowired
    private ChatSmsRepository chatSmsRepository;

    public ChatSms saveMessage(ChatSms message) {
        return chatSmsRepository.save(message);
    }

    public boolean deleteById(Long productId) {
        //return products.removeIf(p -> p.getProductId().equals(productId));
        if(chatSmsRepository.existsById(productId)) {
            chatSmsRepository.deleteById(productId);
            return true;
        }
        return false;
    }
}
