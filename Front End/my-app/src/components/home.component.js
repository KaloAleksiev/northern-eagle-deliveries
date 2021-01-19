import React, { Component } from "react";

import UserService from "../services/user.service";

export default class Home extends Component {
    constructor(props) {
        super(props);

        this.state = {
            content: ""
        };
    }

    componentDidMount() {
        UserService.getPublicContent().then(
            response => {
                this.setState({
                    content: response.data
                });
            },
            error => {
                this.setState({
                    content:
                        (error.response && error.response.data) ||
                        error.message ||
                        error.toString()
                });
            }
        );
    }

    render() {
        return (
            <div className="container">
                <header className="jumbotron">
                    <h1 className="center">Welcome to Northern Eagle Deliveries!</h1>
                </header>
                <div className="row">
                    <div className="column left">
                        <img
                            src={require("../images/truck.png")}
                            alt="truck-img"
                            className="truck-img-card"
                        />
                    </div>
                    <div className="column right">
                        <h3>
                            <br></br>
                            <p><strong>We will deliver your package swiftly and safely!</strong></p>
                            <p><a href="/register">Create an account</a> to get started.</p>
                            <p>To check the status of your registered deliveries, <a href="/login">log into your account.</a></p>
                        </h3>
                    </div>
                </div>

                <footer>
                    <h5 className="center">You are welcome to visit one of our offices during working hours in order to send a package.</h5>
                    <p className="center">We are open 08:00 - 18:30 on weekdays and 10:00 - 16:00 on weekends.</p>
                </footer>

            </div>
        );
    }
}