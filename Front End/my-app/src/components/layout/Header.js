import React, {Component} from 'react'
import { Link } from 'react-router-dom';

export class Header extends Component {
        render(){
            return (
                <header style={ headerStyle }>
                    <h1> Northern Eagle Deliveries </h1>
                    <Link style={linkStyle} to="/Home">Home</Link> | <Link style={linkStyle} to="/Register">Register</Link> | <Link style={linkStyle} to="/Login">Login</Link> | <Link style={linkStyle} to="/Tracker">Tracker</Link>
                </header>
            )
    }
}

const headerStyle = {
    background: '#333',
    color: '#fff',
    textAlign: 'center',
    padding: '10px'
}

const linkStyle = {
    color: '#fff',
    textDecoration: 'none'
}

export default Header
