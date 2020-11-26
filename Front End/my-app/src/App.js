import React, { Component } from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import Header from './components/layout/Header';
import Deliveries from './components/Deliveries';
import AddDelivery from './components/AddDelivery';
import Register from './components/pages/Register';
import Login from './components/pages/Login';
import Tracker from './components/pages/Tracker';
//import { v4 as uuid } from "uuid"; 
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

import './App.css';

class App extends Component {

  state = {
    deliveries: []
  }

  
  
  componentDidMount() {
    axios.get('http://localhost:8080/deliveries')
      .then(res => this.setState({ deliveries: res.data }))
  }
  
  

  /*
  componentDidMount() {
    axios.get('https://jsonplaceholder.typicode.com/todos?_limit=10')
     .then(res => this.setState({ deliveries: res.data }))
  }
  */
  

  /*
  componentDidMount() {
    axios.get('http://localhost:8080/tracker')
      .then(res => console.log(res.data))
  }
  */
 
  getAllDeliveries = () => {
    axios.get('http://localhost:8080/tracker')
      .then(res => console.log(res))
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
    axios.delete(`http://localhost:8080/deliveries/${id}`)
      .then(res => this.setState({ deliveries: [...this.state.deliveries.filter(delivery => delivery.id !== id)] }));
  }

  // Add User
  addDelivery = () => {
    axios({
      method: 'post',
      url: 'http://localhost:8080/newuser',
      data: {
        name: 'Alex P',
        email: 'alexp@gmail.com',
        password: 'pass123',
        phone: '+31672948271',
        position: 'Customer'
      }
    });
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
            <Route path='/Register' component={Register} />
            <Route path='/Tracker' component={Tracker} />
            <Route path='/Login' component={Login} />
          </div>
        </div>
      </Router>
    );
  }
}

export default App;
