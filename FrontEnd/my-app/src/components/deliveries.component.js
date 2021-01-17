import React, { Component } from "react";
import { Table } from "react-bootstrap";
import FilterResults from 'react-filter-search';
import DeliveryService from "../services/delivery.service";
import { Button } from "react-bootstrap";

export default class Deliveries extends Component {

    constructor(props) {
        super(props);

        this.state = {
            deliveries: [],
            value: ""
        }
    }

    componentDidMount() {
        DeliveryService.getAllDeliveries()
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

    handleSent = event => {
        DeliveryService.setSent(event.target.id)
        window.location.reload()
    }

    handleDelivered = event => {
        DeliveryService.setDelivered(event.target.id)
        window.location.reload()
    }

    handleDelete = event => {
        DeliveryService.deleteDelivery(event.target.id)
        window.location.reload()
    }

    render() {
        return (
            <>
                <h1> All deliveries </h1>
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
                                        <th>Mark as Sent</th>
                                        <th>Mark as Delivered</th>
                                        <th>Delete delivery</th>
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
                                            <td><Button variant="warning" id={el.id} onClick={this.handleSent}>Sent</Button> </td>
                                            <td><Button variant="success" id={el.id} onClick={this.handleDelivered}>Delivered</Button></td>
                                            <td><Button variant="danger" id={el.id} onClick={this.handleDelete}>Delete</Button></td>
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