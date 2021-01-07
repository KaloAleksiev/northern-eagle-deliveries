import axios from 'axios';
import React, { Component } from 'react'
import { Button, Form } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

export class Login extends Component {

    handleSubmit = e => {
        e.preventDefault();
        const data = {
            email: this.email,
            password: this.password
        };

        axios.post('http://localhost:8080/auth/signin', data)
            .then(res => {
                console.log(res)
            })
            .catch(err => {
                console.log(err)
            })
    }

    render() {
        return (
            <React.Fragment>
                <h1>Sign into your account</h1>
                
                <Form onSubmit={this.handleSubmit}>
                    <Form.Group controlId="formBasicEmail">
                        <Form.Label>Email Address</Form.Label>
                        <Form.Control type="email" placeholder="example@email.com" onChange={e => this.email = e.target.value} />
                    </Form.Group>

                    <Form.Group controlId="formBasicPassword">
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" placeholder="Your password..." onChange={e => this.password = e.target.value} />
                    </Form.Group>

                    <Button variant="primary" type="submit">
                        Log In
                    </Button>
                </Form>
            </React.Fragment>
        )   
    }
}

export default Login