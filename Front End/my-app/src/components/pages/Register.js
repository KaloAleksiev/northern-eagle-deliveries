import axios from 'axios';
import React, { Component } from 'react'
import { Button, Form } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

export class Register extends Component {

    /*
    registerNewUser = (event) => {
        event.preventDefault();
        let name = event.target.parentNode.name.value;
        let email = event.target.parentNode.email.value;
        let password = event.target.parentNode.password.value;
        let phone = event.target.parentNode.phone.value;
        axios.get(`http://localhost:8080/tracker/${id}`)
            .then(res => console.log(res.data))
    }
    */

    render() {
        return (
            <React.Fragment>
                <h1>Create your own account</h1>
                <Form>
                    <Form.Group>
                        <Form.Label>Name</Form.Label>
                        <Form.Control type="name" id="name" placeholder="Your name..." />
                    </Form.Group>

                    <Form.Group controlId="formBasicEmail">
                        <Form.Label>Email Address</Form.Label>
                        <Form.Control type="email" id="email" placeholder="example@email.com" />
                    </Form.Group>

                    <Form.Group controlId="formBasicPassword">
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" id="password" placeholder="Your password..." />
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Phone Number</Form.Label>
                        <Form.Control type="text" id="phone" placeholder="Your phone number..." />
                    </Form.Group>

                    <Button variant="primary" type="submit" onClick={this.registerNewUser}>
                        Register
                    </Button>
                </Form>
            </React.Fragment>
        )
    }
}

export default Register