package com.botcompany.control

import org.telegram.telegrambots.api.objects.Update

class ControlGet extends BaseControl implements IControl{

    def update
    def steps
    def isStepChanged
    def currentStep
    def message


    @Override
    def start(Update update) {
        this.update = update
        message = createMessage(update, createInlineButtons(currentStep.getButtons()), currentStep.getMessage())
        return this
    }

    @Override
    def hasAnswer() {
        return update.hasMessage() ||
                update.hasCallBAckQuery() ||
                update.hasChosenInlineQuery() ||
                update.hasEditedMessage()
    }

    @Override
    def hasNextStep() {
        return !steps.get(steps.size() - 1).equals(currentStep)
    }

    @Override
    def nextStep() {
        isStepChanged = true
        return currentStep = steps.get(currentStep.getStepNumber() + 1)
    }

    @Override
    def execute() {
        return message
    }
}
