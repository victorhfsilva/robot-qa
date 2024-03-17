import { ELEMENTS } from "./elements";
import login from "../login";

describe('Robots', () => {

    it('displays robots correctly after login', () => {
        const user = Cypress.env('user');
        const password = Cypress.env('password');

        cy.visit("/login");
        login.login(user, password);

        cy.visit("/robots")
        ELEMENTS.forEach(element => {
            cy.contains(element.name);
            cy.contains(element.description);
            element.questions.forEach(question => {
                cy.contains(question)
            })
        });
    });
})