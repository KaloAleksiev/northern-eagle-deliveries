describe("Testing login functionality", () => {
    it("Successfully logs in", () => {
        cy.visit("localhost:3000/login")
        cy.get("#tbEmail").type("john.doe@gmail.com")
        cy.get("#tbPassword").type("pass123")
        cy.intercept('POST', "http://localhost:8080/auth/signin").as("signinRequest")
        cy.get("#btnSignIn").click()
        cy.wait('@signinRequest').then((interception) => {
            expect(interception).property('response').to.have.property('statusCode').equal(200)
        })
    })

    it("Attempts to log in with a wrong password", () => {
        cy.visit("localhost:3000/login")
        cy.get("#tbEmail").type("john.doe@gmail.com")
        cy.get("#tbPassword").type("wrongpass")
        cy.intercept('POST', "http://localhost:8080/auth/signin").as("signinRequest")
        cy.get("#btnSignIn").click()
        cy.wait('@signinRequest').then((interception) => {
            expect(interception).property('response').to.have.property('statusCode').equal(401)
        })
    })
})