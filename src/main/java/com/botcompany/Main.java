package com.botcompany;


import com.botcompany.jdbc.model.User;
import com.botcompany.jdbc.service.UserServiceImpl;
import com.botcompany.jdbc.service.interfaces.UserService;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Main {

    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        User user = new User();
//        user.setFirstName("Nick");
//        user.setLastName("Chupikov");
        user = userService.getAllUsers().get(0);
        user.setLastName("Klok");
        userService.update(user);
        System.out.println(userService.getAllUsers());

//        ApiContextInitializer.init();
//
//        TelegramBotsApi botsApi = new TelegramBotsApi();
//
//        try {
//            botsApi.registerBot(new TryBot());
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
    }
}
