describe('Token', () => {

    it('gets token', () => {
        cy.api({
            method: 'POST',
            url: 'http://localhost:8080/api/v1/robots/token',
            body: {
                username: 'admin',
                password: 'password123'
            }
        }).then(response => {
            expect(response.status).to.equal(200);
        })
    })
})

