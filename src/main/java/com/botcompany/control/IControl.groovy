package com.botcompany.control

import org.telegram.telegrambots.api.objects.Update

interface IControl {

    def start(Update update)
    def hasAnswer()
    def hasNextStep()
    def nextStep()
    def execute()

}