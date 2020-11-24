import axios from 'axios';
import React, { Component } from 'react'

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
                <h1>Delivery Tracker</h1>
                <form>
                    <label htmlFor="deliveryId">Delivery ID:</label><br />
                    <input type="text" id="deliveryId" name="deliveryId"></input><br />
                    <input type="submit" onClick={this.getDeliveryStatus}></input>
                </form>
            </React.Fragment>
        )
    }
    
}

export default Tracker