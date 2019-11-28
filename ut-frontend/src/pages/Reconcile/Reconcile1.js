import React, { Component } from 'react';
import TopNavbar from './Navbar';
import DashTable from '../Common/DashTable';

class Reconcile1 extends Component {
	constructor(props) {
		super(props);
		this.state = {};
	}
	render() {
		return (
			<div>
				<TopNavbar />
				<DashTable />
			</div>
		);
	}
}

export default Reconcile1;
