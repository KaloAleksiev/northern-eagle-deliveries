describe("Testing register functionality", () => {
    it("Successfully registers a new user", () => {
        cy.visit("localhost:3000/register")
        cy.get("#tbName").type("Test User")
        cy.get("#tbEmail").type("test@gmail.com")
        cy.get("#tbPassword").type("pass123")
        cy.get("#tbPhoneNumber").type("+31600000000")
        cy.intercept('POST', "http://localhost:8080/auth/signup").as("signupRequest")
        cy.get("#btnSignUp").click()
        cy.wait('@signupRequest').then((interception) => {
            expect(interception).property('response').to.have.property('statusCode').equal(200)
        })
    })

    it("Attempts to register a user with a taken email", () => {
        cy.visit("localhost:3000/register")
        cy.get("#tbName").type("Test User 2")
        cy.get("#tbEmail").type("test@gmail.com")
        cy.get("#tbPassword").type("pass123")
        cy.get("#tbPhoneNumber").type("+31600000000")
        cy.intercept('POST', "http://localhost:8080/auth/signup").as("signupRequest")
        cy.get("#btnSignUp").click()
        cy.wait('@signupRequest').then((interception) => {
            expect(interception).property('response').to.have.property('statusCode').equal(400)
        })
    })


})