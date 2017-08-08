package com.botcompany.utils;


import com.botcompany.controls.ControllerGet;
import com.botcompany.controls.IController;
import com.botcompany.enumerations.Commands;
import org.telegram.telegrambots.api.objects.Update;

public class ControllerFactory {

    private ControllerGet controllerGet;

    public IController initController(String command){


        IController controller = null;

//        if (command.name().toLowerCase().startsWith("/get"))
//            controller = controllerGet;
//
//        if (command.name().toLowerCase())

        return controller;
    }
}
