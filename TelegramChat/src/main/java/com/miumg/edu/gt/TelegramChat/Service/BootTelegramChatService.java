/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miumg.edu.gt.TelegramChat.Service;

import lombok.extern.slf4j.Slf4j;
import java.util.Objects;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
/**
 *
 * @author strdh
 */
@Slf4j
public class BootTelegramChatService extends TelegramLongPollingBot {
    
    private final String botName;
    private final Map<Long, String> userStates = new HashMap<>();

    @Autowired
    private SmsService smsService;

    public BootTelegramChatService(String botName, String botToken) {
        super (botToken);
        this.botName = botName;
    }
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            Long chatId = message.getChatId();
            String messageText = message.getText();

            log.info("Mensaje recibido: {}", messageText);
            log.info("Id del Chat: {}", chatId);

            try {
                if (Objects.equals(messageText, "/start")) {
                    iniciarConversacion(chatId);
                } else if ("waitting_name".equals(userStates.get(chatId))) {
                    obtenerNombreUsuario(chatId, messageText);
                } else {
                    enviarMensaje(chatId, "No entiendo ese comando. Prueba con /start.");
                }
            } catch (TelegramApiException e) {
                log.error("Error durante la ejecución de la API de Telegram: {}", e.getMessage());
            }
        }
    }


    private void iniciarConversacion(Long chatId) throws TelegramApiException {
        userStates.put(chatId, "waitting_name");
        enviarMensaje(chatId, "¡Hola! ¿Cuál es tu nombre?");
    }

    private void obtenerNombreUsuario(Long chatId, String name) throws TelegramApiException {
        userStates.remove(chatId); // Limpiamos el estado
        String response = "¡Mucho gusto, " + name + "! ¿En qué puedo ayudarte?";
        enviarMensaje(chatId, response);
    }

    private void enviarMensaje(Long chatId, String texto) throws TelegramApiException {
        SendMessage mensaje = new SendMessage(chatId.toString(), texto);
        execute(mensaje);
    }

    @Override
    public String getBotUsername() {
        return this.botName;
    }
}
