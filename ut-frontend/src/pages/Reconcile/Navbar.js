import React, { Component } from 'react';
import { BrowserRouter as Router, NavLink } from 'react-router-dom';
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';
import NavDropdown from 'react-bootstrap/NavDropdown';
import '../Common/dashboard1.css';

class TopNavbar extends Component {
	constructor(props) {
		super(props);
		this.state = {};
	}
	render() {
		return (
			<Navbar bg="light" expand="lg">
				<Navbar.Brand href="/reconcile">Uangteman</Navbar.Brand>
				<Navbar.Toggle aria-controls="basic-navbar-nav" />
				<Navbar.Collapse id="basic-navbar-nav">
					<Nav className="mr-auto">
						<Nav.Link href="#link">Link</Nav.Link>
						<NavDropdown title="Users" id="basic-nav-dropdown">
							<NavDropdown.Item href="/application-workbench">Application workbench</NavDropdown.Item>
							<NavDropdown.Item>
								<NavLink to="/reconcile1" activeClassName="navbar-list">
									Reconcile
								</NavLink>
							</NavDropdown.Item>
							<NavDropdown.Item href="/reconcile-disbursement">Reconcile Disbursement</NavDropdown.Item>
							<NavDropdown.Divider />
							<NavDropdown.Item href="#action/3.4">Separated link</NavDropdown.Item>
						</NavDropdown>
					</Nav>
					{/* <Form inline>
						<FormControl type="text" placeholder="Search" className="mr-sm-2" />
						<Button variant="outline-success">Search</Button>
					</Form> */}
				</Navbar.Collapse>
			</Navbar>
		);
	}
}

export default TopNavbar;
