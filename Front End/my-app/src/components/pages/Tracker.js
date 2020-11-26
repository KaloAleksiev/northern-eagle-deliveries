import axios from 'axios';
import React, { Component } from 'react'
import { Button, Form } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

export class Tracker extends Component {

    
    getDeliveryStatus = (event) => {
        event.preventDefault();
        let id = event.target.parentNode.deliveryId.value;
        axios.get(`http://localhost:8080/tracker/${id}`)
        .then(res => console.log(res.data))
    }

    render() {
        return (
            <React.Fragment>
                <h1>Track your delivery</h1>
                <Form>
                    <Form.Group>
                        <Form.Label>Delivery ID:</Form.Label>
                        <Form.Control type="text" id="deliveryId" name="deliveryId" placeholder="Your delivery number..." />
                    </Form.Group>
                    <Button type="submit" onClick={this.getDeliveryStatus}> Check status</Button>
                </Form>
            </React.Fragment>
        )
    }
    
}

export default Tracker