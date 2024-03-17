import { ELEMENTS } from "./elements";

class chat {
    ask(robot, question){
        cy.get(ELEMENTS.robotsSelect).select(robot);
        cy.get(ELEMENTS.questionTextArea).type(question);
        cy.contains(ELEMENTS.askButtonText).click();
        cy.get(ELEMENTS.questionTextArea).clear();
    }
}

export default new chat();