import React, { Component } from "react";
import { Redirect } from 'react-router-dom';
import { connect } from "react-redux";
import DeliveryService from "../services/delivery.service";
import AuthService from "../services/auth.service";
import UserService from "../services/user.service";
import { Table } from "react-bootstrap";
import { Button } from "react-bootstrap";
import FilterResults from 'react-filter-search';
import { history } from '../helpers/history';

class Profile extends Component {

    constructor(props) {
        super(props);

        this.state = {
            deliveries: [],
            value: "",
        }
    }

    componentDidMount() {
        DeliveryService.getDeliveriesBySenderId(this.props.user.id)
            .then((res) => {
                this.setState({
                    deliveries: res
                })
            })
    }

    handleChange = event => {
        const { value } = event.target;
        this.setState({ value });
    }

    handleDelete = event => {
        UserService.deleteAccount(event.target.id)
        AuthService.logout()
        history.push("/home")
        window.location.reload()
    }

    render() {
        const { user: currentUser } = this.props;

        if (!currentUser) {
            return <Redirect to="/login" />;
        }

        return (
            <>
                <div className="container">
                    <header className="jumbotron">
                        <h3>
                            <strong>{currentUser.name}</strong>
                        </h3>
                        <br></br>
                        <p>
                            <strong>Email:</strong> {currentUser.email}
                        </p>
                        <p>
                            <strong>Phone Number:</strong> {currentUser.phoneNumber}
                        </p>
                        <Button variant="danger" id={currentUser.id} onClick={this.handleDelete}>Delete Account </Button>
                    </header>
                </div>

                <h1> Your deliveries </h1>
                <div>
                    <input type="text" value={this.state.value} onChange={this.handleChange} />
                    <FilterResults
                        value={this.state.value}
                        data={this.state.deliveries}
                        renderResults={results => (

                            <Table striped bordered hover className="table">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Address</th>
                                        <th>Price</th>
                                        <th>Paid</th>
                                        <th>Send date</th>
                                        <th>Status</th>
                                        <th>Weight</th>
                                        <th>Sender</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {results.map(el => (
                                        <tr key={el.id}>
                                            <td>{el.id}</td>
                                            <td>{el.address}</td>
                                            <td>{el.price} €</td>
                                            <td>{el.paid}</td>
                                            <td>{el.sendDate}</td>
                                            <td>{el.status}</td>
                                            <td>{el.weight} kg</td>
                                            <td>{el.sender.name}</td>
                                        </tr>))}
                                </tbody>
                            </Table>
                        )}
                    />
                </div>


            </>
        );
    }
}

function mapStateToProps(state) {
    const { user } = state.auth;
    return {
        user,
    };
}

export default connect(mapStateToProps)(Profile);