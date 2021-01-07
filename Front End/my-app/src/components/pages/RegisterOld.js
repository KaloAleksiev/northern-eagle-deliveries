import axios from 'axios';
import React, { Component } from 'react'
import { Button, Form } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

export class RegisterOld extends Component {

    handleSubmit = e => {
        e.preventDefault();

        const data = {
            name: this.name,
            email: this.email,
            password: this.password,
            phoneNumber: this.phoneNumber
        }
        
        axios.post('http://localhost:8080/auth/signup', data)
            .then(
                res => {
                    console.log(res)
                }
            )
            .catch(
                err => {
                    console.log(err)
                }
            )
    }

    render() {
        return (
            <React.Fragment>
                <h1>Create your own account</h1>
                <Form onSubmit={this.handleSubmit}>
                    <Form.Group>
                        <Form.Label>Name</Form.Label>
                        <Form.Control type="text" id="name" placeholder="Your name..." onChange={e => this.name = e.target.value} />
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Email Address</Form.Label>
                        <Form.Control type="email" id="email" placeholder="example@email.com" onChange={e => this.email = e.target.value} />
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" id="password" placeholder="Your password..." onChange={e => this.password = e.target.value} />
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Phone Number</Form.Label>
                        <Form.Control type="text" id="phone" placeholder="Your phone number..." onChange={e => this.phoneNumber = e.target.value} />
                    </Form.Group>

                    <Button variant="primary" type="submit" onClick={this.registerNewUser}>
                        Register
                    </Button>
                </Form>
            </React.Fragment>
        )
    }
}

export default RegisterOld