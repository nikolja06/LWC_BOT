package com.botcompany.control

import org.springframework.beans.factory.annotation.Autowired


class ControlFactory {

    @Autowired
    ControlGet controlGet

    def getControl(){return this.controlGet}
}
