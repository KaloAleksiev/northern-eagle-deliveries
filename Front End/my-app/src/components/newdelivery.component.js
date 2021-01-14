import React, { Component } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import DeliveryService from "../services/delivery.service";
import UserService from "../services/user.service";
import { Table } from "react-bootstrap";
import FilterResults from 'react-filter-search';

const required = (value) => {
    if (!value) {
        return (
            <div className="alert alert-danger" role="alert">
                This field is required!
            </div>
        );
    }
};

const address = (value) => { };

const weight = (value) => { };

const paid = (value) => { };

const senderId = (value) => { };

export default class NewDelivery extends Component {

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.onChangeSenderId = this.onChangeSenderId.bind(this);
        this.onChangeAddress = this.onChangeAddress.bind(this);
        this.onChangeWeight = this.onChangeWeight.bind(this);
        this.onChangePaid = this.onChangePaid.bind(this);

        this.state = {
            users: [],
            value: "",
            senderId: "",
            address: "",
            weight: "",
            paid: "",
            successful: false
        };
    }

    onChangeSenderId(e) {
        this.setState({
            senderId: e.target.value,
        });
    }

    onChangeAddress(e) {
        this.setState({
            address: e.target.value,
        });
    }

    onChangeWeight(e) {
        this.setState({
            weight: e.target.value,
        });
    }

    onChangePaid(e) {
        this.setState({
            paid: e.target.value,
        });
    }

    onChangePhoneNumber(e) {
        this.setState({
            phoneNumber: e.target.value,
        });
    }

    handleSubmit(e) {

        this.setState({
            successful: false,
        });

        this.form.validateAll();

        if (this.checkBtn.context._errors.length === 0) {
            console.log(this.state.address, this.state.weight, this.state.paid, this.state.senderId)
            DeliveryService.newDelivery(this.state.address, this.state.weight, this.state.paid, this.state.senderId)
        }
    }

    componentDidMount() {
        UserService.getAllUsers()
            .then((res) => {
                console.log(res)
                this.setState({
                    users: res,
                })
            })
    }

    handleChange = event => {
        const { value } = event.target;
        this.setState({ value });
    };


    //let filteredUsers = users.filter(user => user.name === typedName)

    render() {
        const { message } = this.props;
        return (
            <>
                <div class="registertext">
                    <h1> Register a new delivery </h1>
                </div>

                <div class="row">
                    <div class="column left">

                        <Form
                            onSubmit={this.handleSubmit}
                            ref={(c) => {
                                this.form = c;
                            }}
                        >
                            {!this.state.successful && (
                                <div>
                                    <div className="form-group">
                                        <label>Address</label>
                                        <Input
                                            type="text"
                                            className="form-control"
                                            name="address"
                                            value={this.state.address}
                                            onChange={this.onChangeAddress}
                                            validations={[required, address]}
                                        />
                                    </div>

                                    <div className="form-group">
                                        <label>Weight (kg)</label>
                                        <Input
                                            type="number"
                                            className="form-control"
                                            name="weight"
                                            value={this.state.weight}
                                            onChange={this.onChangeWeight}
                                            validations={[required, weight]}
                                        />
                                    </div>

                                    <div className="form-group">
                                        <label>Paid</label>
                                        <Input
                                            type="text"
                                            className="form-control"
                                            name="paid"
                                            value={this.state.paid}
                                            onChange={this.onChangePaid}
                                            validations={[required, paid]}
                                        />
                                    </div>

                                    <div className="form-group">
                                        <label htmlFor="phone">Sender ID</label>
                                        <Input
                                            type="number"
                                            className="form-control"
                                            name="senderId"
                                            value={this.state.senderId}
                                            onChange={this.onChangeSenderId}
                                            validations={[required, senderId]}
                                        />
                                    </div>

                                    <div className="form-group">
                                        <button className="btn btn-primary btn-block">Submit</button>
                                    </div>
                                </div>
                            )}

                            {message && (
                                <div className="form-group">
                                    <div className={this.state.successful ? "alert alert-success" : "alert alert-danger"} role="alert">
                                        {message}
                                    </div>
                                </div>
                            )}
                            <CheckButton
                                style={{ display: "none" }}
                                ref={(c) => {
                                    this.checkBtn = c;
                                }}
                            />
                        </Form>

                    </div>





                    <div class="column right">

                        <div>
                            <input type="text" value={this.state.value} onChange={this.handleChange} />
                            <FilterResults
                                value={this.state.value}
                                data={this.state.users}
                                renderResults={results => (

                                    <Table striped bordered hover class="table">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Name</th>
                                                <th>Email</th>
                                                <th>Phone number</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {results.map(el => (
                                                <tr>
                                                    <td>{el.id}</td>
                                                    <td>{el.name}</td>
                                                    <td>{el.email}</td>
                                                    <td>{el.phoneNumber}</td>
                                                </tr>))}
                                        </tbody>
                                    </Table>
                                )}
                            />
                        </div>


                    </div>
                </div>

            </>

        );

    }

}