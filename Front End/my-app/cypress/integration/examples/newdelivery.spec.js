describe("Testing new delivery creation functionality", () => {
    beforeEach(() => {
        cy.visit("localhost:3000/login")
        cy.get("#tbEmail").type("kaloyan.aleksiev22@gmail.com")
        cy.get("#tbPassword").type("pass123")
        cy.intercept('POST', "http://localhost:8080/auth/signin").as("signinRequest")
        cy.get("#btnSignIn").click()
        cy.wait('@signinRequest').then((interception) => {
            expect(interception).property('response').to.have.property('statusCode').equal(200)
        })
    })
    it("Successfully creates a new delivery", () => {
        cy.visit("localhost:3000/newdelivery")
        cy.get("#tbAddress").type("Test address")
        cy.get("#tbWeight").type("10.0")
        cy.get("#tbPaid").type("No")
        cy.get("#tbPrice").type("12.50")
        cy.get("#tbSenderId").type("2")
        cy.intercept('POST', "http://localhost:8080/mod/newdelivery").as("newDeliveryRequest")
        cy.get("#btnSubmit").click()
        cy.wait('@newDeliveryRequest').then((interception) => {
            expect(interception).property('response').to.have.property('statusCode').equal(200)
        })
    })
})