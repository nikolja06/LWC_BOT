package com.botcompany.control

import com.botcompany.entities.Button
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton

class BaseControl {

    protected ReplyKeyboard createInlineButtons(List<Button> buttonList){
        InlineKeyboardMarkup ikm = new InlineKeyboardMarkup()
        List<InlineKeyboardButton> buttons = new ArrayList<>()
        for (Button button : buttonList) {
            buttons.add(new InlineKeyboardButton().setText(button.getText()).setCallbackData(button.getCallBack()))
        }
        ikm.setKeyboard(Collections.singletonList(buttons))
        return ikm
    }

    protected SendMessage createMessage(Update update,ReplyKeyboard keyboard, String text){
        SendMessage message = new SendMessage()
                .setText(text)
                .enableMarkdown(false)
        if (keyboard != null){
            message.enableMarkdown(true)
            message.setReplyMarkup(keyboard)
        }
        if (update.hasMessage()){
            message.setChatId(update.getMessage().getChatId())
        }
        else if (update.hasCallbackQuery()){
            message.setChatId(update.getCallbackQuery().getMessage().getChatId())
        }
        return message
    }


/*    protected ReplyKeyboard generateResponse(){
        ReplyKeyboard replyKeyboard;
        if (!members.isEmpty()){
            replyKeyboard = createInlineButtons(yes, no, back);
            previousMember = currentMember;
            currentMember = members.get(0);
            members.remove(0);
        }
        else {
            replyKeyboard = createInlineButtons("Generate", "Save", "Send");
            text = "What would you like to do?";
        }
        return replyKeyboard;
    }*/
}
