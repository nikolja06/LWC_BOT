package com.botcompany.controls;


import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;

public class BaseControl {

    protected Object editMessageText(Update update, String answer){
        Integer message_id = update.getCallbackQuery().getMessage().getMessageId();
        long chat_id = update.getCallbackQuery().getMessage().getChatId();
        return new EditMessageText()
                .setChatId(chat_id)
                .setMessageId(message_id)
                .setText(answer);
    }


    protected Object editMessageInlineKeybosrd(Long chatId, Integer messageId, InlineKeyboardMarkup inlineKeyboardMarkup){
        return  new EditMessageReplyMarkup()
                .setChatId(chatId)
                .setMessageId(messageId)
                .setReplyMarkup(inlineKeyboardMarkup);
    }
}
