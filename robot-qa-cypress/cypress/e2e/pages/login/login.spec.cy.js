import { ELEMENTS } from "./elements";
import login from ".";

describe('Login', () => {
    
    it('logins successfully', () => {
        const user = Cypress.env('user');
        const password = Cypress.env('password');

        cy.visit('/login');
        login.login(user, password);

        cy.get(ELEMENTS.isAuthenticated).should('be.visible')
    })

    it('logins unsuccessfully', () => {
        const user = Cypress.env('user');
        const password = Cypress.env('password');

        cy.visit('/login');
        login.login(user, "wrong password");

        cy.get(ELEMENTS.isAuthenticated).should('not.exist')
    })
})