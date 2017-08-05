package com.botcompany.controls;


import com.botcompany.entties.Step;
import org.telegram.telegrambots.api.objects.Update;

import java.util.List;


public class ControllerGet extends BaseControl implements IController{

    private List<Step> steps;
    private Step currentStep;
    private List<String> commands;


    public ControllerGet(List<Step> steps) {
        this.steps = steps;
        currentStep = steps.get(0);
    }

    @Override
    public void run(Update update) throws Exception {

    }


    private String getCommand(Update update) throws Exception {
        String text;
        if (update.hasMessage() && update.getMessage().hasText())
            text = update.getMessage().getText();
        else if (update.hasCallbackQuery())
            text = update.getCallbackQuery().getMessage().getText();
        else
            throw new Exception("Update does not have any message or command!");
        return null;
    }


/*    chatId = update.getCallbackQuery().getMessage().getChatId();
    previousMessageId = update.getCallbackQuery().getMessage().getMessageId();

    editMessageText(update, "edited text");
    editMessageInlineKeybosrd(chatId, previousMessageId, createInlineButtons("firts_btn", "second_btn"));*/


}
