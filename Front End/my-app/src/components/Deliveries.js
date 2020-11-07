import React, { Component } from 'react';
import DeliveryItem from './DeliveryItem';
import PropTypes from 'prop-types';

class Deliveries extends Component {
    
    render() {
        return this.props.deliveries.map(delivery => (
        <DeliveryItem key={ delivery.delivery_id } delivery={ delivery } markDelivered={ this.props.markDelivered } delDelivery={ this.props.delDelivery } />
        ));
    }
}

// PropTypes
Deliveries.propTypes = {
    deliveries: PropTypes.array.isRequired,
    markDelivered: PropTypes.func.isRequired,
    delDelivery: PropTypes.func.isRequired
}

export default Deliveries
