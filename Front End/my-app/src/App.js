import React, { Component } from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import Header from './components/layout/Header';
import Deliveries from './components/Deliveries';
import AddDelivery from './components/AddDelivery';
import About from './components/pages/About';
//import { v4 as uuid } from "uuid"; 
import axios from 'axios';

import './App.css';

class App extends Component {

  state = {
    deliveries: []
  }

  componentDidMount() {
    axios.get('https://jsonplaceholder.typicode.com/todos?_limit=10')
     .then(res => this.setState({ deliveries: res.data }))
  }

  // Toggle Delivered
  markDelivered = (id) => {
    this.setState({ deliveries: this.state.deliveries.map(delivery => {
      if (delivery.id === id) {
        delivery.delivered = !delivery.delivered
      }
      return delivery;  
    }) });
  }

  // Delete Delivery
  delDelivery = (id) => {
    axios.delete(`https://jsonplaceholder.typicode.com/todos/${id}`)
      .then(res => this.setState({ deliveries: [...this.state.deliveries.filter(delivery => delivery.id !== id)] }));
  }

  // Add Delivery
  addDelivery = (title) => {
    axios.post('https://jsonplaceholder.typicode.com/todos', {
      title,
      address: 'Frans Staelstraat 27',
      sent: true,
      delivered: false
    })
      .then(res => this.setState({ deliveries: [...this.state.deliveries, res.data] }));
  }

  render() {
    return (
      <Router>
        <div className="App">
          <div className="container">
            <Header />
            <Route exact path='/' render={props => (
              <React.Fragment>
                <AddDelivery addDelivery={this.addDelivery} />
                <Deliveries deliveries={this.state.deliveries} markDelivered={this.markDelivered} delDelivery={this.delDelivery} />
              </React.Fragment>
            )} />
            <Route path='/About' component={About} />
          </div>
        </div>
      </Router>
    );
  }
}

export default App;
