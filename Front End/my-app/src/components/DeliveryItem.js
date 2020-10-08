import React, { Component } from 'react'
import PropTypes from 'prop-types';

export class DeliveryItem extends Component {
    getStyle = () => {
        return {
            background: '#f4f4f4',
            padding: '10px',
            borderBottom: '1px #ccc dotted',
            textDecoration: this.props.delivery.delivered ? 'line-through' : 'none'
        }
    }

    render() {
        const { id, title } = this.props.delivery;
        return (
            <div style={this.getStyle()}>
                <p> 
                    <input type="checkbox" onChange={ this.props.markDelivered.bind(this, id) } />
                    { title } 
                    <button onClick={ this.props.delDelivery.bind(this, id) } style={ btnStyle }>X</button>
                    </p>
            </div>
        )
    }
}

// PropTypes
DeliveryItem.propTypes = {
    delivery: PropTypes.object.isRequired,
    markDelivered: PropTypes.func.isRequired,
    delDelivery: PropTypes.func.isRequired
}

const btnStyle = {
    background: '#ff0000',
    color: '#fff',
    border: 'none',
    padding: '5px 8px',
    borderRadius: '50%',
    cursor: 'pointer',
    float: 'right'
}

export default DeliveryItem
