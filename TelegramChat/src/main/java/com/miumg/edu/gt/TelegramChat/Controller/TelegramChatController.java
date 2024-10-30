/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miumg.edu.gt.TelegramChat.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.miumg.edu.gt.TelegramChat.Service.OpAiService;
import com.miumg.edu.gt.TelegramChat.Model.Request;

/**
 *
 * @author strdh
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class TelegramChatController {
    
        private final OpAiService opAiService;

    public TelegramChatController(OpAiService opAiService ) {
        this.opAiService = opAiService;
    }

    @PostMapping("/preguntar")
    public String preguntar(@RequestBody Request message) {
        log.info("Estes es el body del Requerimiento: " + message.getRequest() );
        String requestMessage = message.getRequest();
        try {
            return opAiService.obtenerRespuesta(requestMessage);
        } catch (Exception e) {
            return "Error al obtener respuesta: " + e.getMessage();
        }
    }
}
