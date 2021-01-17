import React, { Component } from "react";
import { Table } from "react-bootstrap";
import FilterResults from 'react-filter-search';
import UserService from "../services/user.service";
import { Button } from "react-bootstrap";

export default class Users extends Component {

    constructor(props) {
        super(props);

        this.state = {
            users: [],
            value: ""
        }
    }

    componentDidMount() {
        UserService.getAllUsers()
            .then((res) => {
                this.setState({
                    users: res
                })
            })
    }

    handleChange = event => {
        const { value } = event.target;
        this.setState({ value });
    }

    handleCustomer = event => {
        UserService.makeCustomer(event.target.id)
        window.location.reload()
    }

    handleEmployee = event => {
        UserService.makeEmployee(event.target.id)
        window.location.reload()
    }

    handleAdmin = event => {
        UserService.makeAdmin(event.target.id)
        window.location.reload()
    }

    render() {
        return (
            <>
                <h1> All users </h1>

                <div>
                    <input type="text" value={this.state.value} onChange={this.handleChange} />
                    <FilterResults
                        value={this.state.value}
                        data={this.state.users}
                        renderResults={results => (

                            <Table striped bordered hover className="table">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Email</th>
                                        <th>Phone number</th>
                                        <th>Position</th>
                                        <th>Make Customer</th>
                                        <th>Make Employee</th>
                                        <th>Make Administrator</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {results.map(el => (
                                        <tr key={el.id}>
                                            <td>{el.id}</td>
                                            <td>{el.name}</td>
                                            <td>{el.email}</td>
                                            <td>{el.phoneNumber}</td>
                                            <td>{el.roles[0].name}</td>
                                            <td><Button variant="success" id={el.id} onClick={this.handleCustomer}>Customer</Button> </td>
                                            <td><Button variant="info" id={el.id} onClick={this.handleEmployee}>Employee</Button> </td>
                                            <td><Button variant="primary" id={el.id} onClick={this.handleAdmin}>Admin</Button> </td>
                                        </tr>))}
                                </tbody>
                            </Table>
                        )}
                    />
                </div>

            </>
        )
    }

}