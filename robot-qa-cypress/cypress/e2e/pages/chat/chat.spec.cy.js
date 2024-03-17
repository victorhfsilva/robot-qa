import chat from ".";
import { ELEMENTS } from "./elements";
import { DATA } from "./data";
import login from "../login"

describe("Chat", () => {

    beforeEach(() => {
        const user = Cypress.env('user');
        const password = Cypress.env('password');

        cy.visit("/login");
        login.login(user, password);

        cy.visit("/chat")
    })

    
    DATA.forEach(robot => {
        robot.qa.forEach(qa => {
            it(`Ask ${qa.question} to robot ${robot.name} `, () => {
                chat.ask(robot.name, qa.question);
                cy.get(ELEMENTS.answer).contains(qa.answer)
            })
        })
    })
    

})