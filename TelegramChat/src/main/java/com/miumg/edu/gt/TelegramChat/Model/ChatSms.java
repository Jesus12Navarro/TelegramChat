/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miumg.edu.gt.TelegramChat.Model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author strdh
 */
@Setter
@Getter
@Entity
@Table(name = "chatSms")
public class ChatSms {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String answer;

    @Column()
    private String client;

    @Column(nullable = false)
    private String chatId;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creationDate;
}
