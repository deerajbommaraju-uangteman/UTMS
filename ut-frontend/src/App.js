import React, { Component } from 'react';
import { ReactComponent as Logo } from './assets/logo.svg';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import Routes from './Routes';
import TopNavbar from './pages/Common/Navbar';

class App extends Component {
	render() {
		return (
			<Router>
				<Route path="/" exact component={TopNavbar} />
				<main style={{ marginTop: '1.5rem' }}>
					<Routes />
				</main>
			</Router>
		);
	}
}

export default App;
