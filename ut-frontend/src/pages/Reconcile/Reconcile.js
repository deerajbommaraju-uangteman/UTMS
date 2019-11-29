import React, { Component } from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import TopNavbar from './Navbar';
import Routes from '../../Routes';

class Reconcile extends Component {
	render() {
		return (
			<Router>
				<Route exact path="/reconcile" component={TopNavbar} />
				<main style={{ marginTop: '1.5rem' }}>
					<h1>Welcome to Workbench</h1>
				</main>
			</Router>
		);
	}
}

export default Reconcile;
