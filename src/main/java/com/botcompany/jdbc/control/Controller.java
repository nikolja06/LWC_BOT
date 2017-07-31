package com.botcompany.jdbc.control;


import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Controller {

    public SendMessage addNewMember(Update update){
        SendMessage sendMessage = new SendMessage();
        String query = update.getMessage().getText();

        return sendMessage;
    }

//    private ReplyKeyboard createInlineButtons(String... btnText){
//        InlineKeyboardMarkup ikm = new InlineKeyboardMarkup();
//        List<InlineKeyboardButton> buttons = new ArrayList<>();
//        for (String text : btnText) {
//            buttons.add(new InlineKeyboardButton().setText(text).setCallbackData(text));
//        }
//        ikm.setKeyboard(Collections.singletonList(buttons));
//        return ikm;
//    }
}
