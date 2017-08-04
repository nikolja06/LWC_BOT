package com.botcompany;


import com.botcompany.control.ControlFactory;
import com.botcompany.control.IControl;
import com.vdurmont.emoji.EmojiLoader;
import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.methods.ForwardMessage;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

public class TryBot extends TelegramLongPollingBot{

    private Map<String, Boolean> result;
    private List<String> members;
    private static final String yes = "YES";
    private static final String no = "NO";
    private static final String back = "Back";
    private String previousMember;
    private String currentMember;
    private String text = null;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MMMM.yyyy");


    @Autowired
    ControlFactory controlFactory
    private IControl control


    public void onUpdateReceived(Update update) {

        if (update.hasMessage() || update.hasCallbackQuery()) {

            control = controlFactory.getControl()

//            String message = update.getMessage().getText().trim().toLowerCase();
            String message;
            if (update.hasCallbackQuery()){
                message = update.getCallbackQuery().getData().toLowerCase().trim();
            }
            else if (update.hasMessage() && update.getMessage().getContact() != null) {
                Integer userID = update.getMessage().getContact().getUserID();
                String userFirstName = update.getMessage().getContact().getFirstName() == null ? "" : update.getMessage().getContact().getFirstName();
                String userLastName = update.getMessage().getContact().getLastName() == null ? "" : update.getMessage().getContact().getLastName();
                String userName = userLastName + " " + userFirstName;
                generateReport();
                SendMessage forwardMessage = new SendMessage()
                        .setChatId(userID.longValue())
                        .setText(text);
                SendMessage sendMessage = new SendMessage()
                        .setChatId(update.getMessage().getChatId());
                try {
                    sendMessage(forwardMessage);
                    String smile = EmojiParser.parseToUnicode(":smile:");
                    sendMessage.setText("Report was sent to "
                            + (userName.trim().isEmpty() ? update.getMessage().getContact().getPhoneNumber() : userName)
                            + "!\nThank you for chosen ChupikBot " + smile);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                    String sad = EmojiParser.parseToUnicode(":cry:");
                    sendMessage.setText("Sorry but bot can't initiate conversation with a user - "
                            + (userName.trim().isEmpty() ? update.getMessage().getContact().getPhoneNumber() : userName)
                            + " " + sad);
                }

                try {
                    sendMessage(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                return;
            }
            else{
                message = update.getMessage().getText().toLowerCase().trim();
            }

            ReplyKeyboard replyKeyboard = null;

            switch (message){
                case "/start":
                    result = new HashMap<>();
                    members = Members.getMembers();
                    replyKeyboard = createInlineButtons(yes, no, back);
                    currentMember = members.get(0);
                    break;

                case "yes":
                    result.put(currentMember, true);
                    replyKeyboard = generateResponse();
                    break;

                case "no":
                    result.put(currentMember, false);
                    replyKeyboard = generateResponse();
                    break;

                case "back":
                    members.add(currentMember);
                    currentMember = previousMember;
                    break;

                case "generate":
                    generateReport();
                    replyKeyboard = createInlineButtons("Save", "Send");
                    break;

                case "save":
                    Members.addAttendanceResult(result);
                    text = "Success!";
                    replyKeyboard = createInlineButtons("Send", "Finish");
                    break;

                case "send":
                    text = "Please send me Contact";
                    break;

                case "finish":
                    text = "Thank you!";
                    break;
            }

            SendMessage sendMessage = createMessage(update, replyKeyboard, text == null ? currentMember : text);
            try {
                sendMessage(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private ReplyKeyboard generateResponse(){
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
    }


    private void generateReport(){
        StringBuilder builderYes = new StringBuilder();
        StringBuilder builderNo = new StringBuilder();

        builderYes.append(sdf.format(new Date(System.currentTimeMillis())));
        builderYes.append("\n");

        int count = 1;

        for (Map.Entry<String, Boolean> pair : result.entrySet()) {
            StringBuilder builder = new StringBuilder();
            builder.append(count);
            builder.append(". ");
            builder.append(pair.getKey());
            builder.append(" - ");
            builder.append(pair.getValue() ? "yes" : "no");
            builder.append("\n");
            if (pair.getValue()){
                builderYes.append(builder.toString());
            }
            else {
                builderNo.append(builder.toString());
            }
            count++;
        }

        text = builderYes.append(builderNo.toString()).toString();
    }


    private SendMessage createMessage(Update update, ReplyKeyboard keyboard, String text){
        SendMessage message = new SendMessage()
                .setText(text)
                .enableMarkdown(false);
        if (keyboard != null){
            message.enableMarkdown(true);
            message.setReplyMarkup(keyboard);
        }
        if (update.hasMessage()){
            message.setChatId(update.getMessage().getChatId());
        }
        else if (update.hasCallbackQuery()){
            message.setChatId(update.getCallbackQuery().getMessage().getChatId());
        }
        return message;
    }


    private ReplyKeyboard createInlineButtons(String... btnText){
        InlineKeyboardMarkup ikm = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        for (String text : btnText) {
            buttons.add(new InlineKeyboardButton().setText(text).setCallbackData(text));
        }
        ikm.setKeyboard(Collections.singletonList(buttons));
        return ikm;
    }

/*    private ReplyKeyboard createKeyboard(String... btnText) {
        ReplyKeyboardMarkup rkm = new ReplyKeyboardMarkup();
        KeyboardRow row = new KeyboardRow();
        for (String text : btnText){
            row.add(new KeyboardButton(text));
        }
        rkm.setKeyboard(Collections.singletonList(row));
        return rkm;
    }*/


    public String getBotUsername() {
        return "ChupikBot";
    }

    public String getBotToken() {
        return "405532597:AAHGXWcvt4HhwMIOH_38-lgLS3IEtfdD2mc";
    }
}
