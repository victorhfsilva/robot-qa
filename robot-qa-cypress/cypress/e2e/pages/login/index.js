import {ELEMENTS} from "./elements"

class login {

    login(user, password){
        cy.get(ELEMENTS.nameInput).type(user);
        cy.get(ELEMENTS.passwordInput).type(password);
        cy.get(ELEMENTS.loginButton).click();
    }

}

export default new login();
