package com.botcompany.controls;


import org.telegram.telegrambots.api.objects.Update;

public interface IController {

    void run(Update update) throws Exception;
}
