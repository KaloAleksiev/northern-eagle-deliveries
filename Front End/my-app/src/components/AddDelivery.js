import React, { Component } from 'react'
import PropTypes from 'prop-types'

export class AddDelivery extends Component {

    state = {
        title: ''
    }

    onChange = (e) => this.setState({ title: e.target.value });

    onSubmit = (e) => {
        e.preventDefault();
        this.props.addDelivery(this.state.title);
        this.setState({ title: '' });
    }

    render() {
        return (
            <form onSubmit={this.onSubmit} style={{ display: 'flex' }}>
                <input 
                    type="text" 
                    name="title" 
                    style={{ flex: '10', padding: '5px' }} 
                    placeholder="Add Delivery..." 
                    value={this.state.title}
                    onChange={ this.onChange }
                />
                <input type="submit" value="Submit" className="btn" style={{ flex: '1' }} />
            </form>
        )
    }
}

// PropTypes
AddDelivery.propTypes = {
    delivery: PropTypes.object,
    markDelivered: PropTypes.func,
    delDelivery: PropTypes.func
}

export default AddDelivery
