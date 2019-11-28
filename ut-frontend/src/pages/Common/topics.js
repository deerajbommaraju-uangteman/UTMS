import React, { Component } from 'react';
import { Tab, Row, Col, Nav } from 'react-bootstrap';
import Table1 from './table1';
class Topics extends Component {
	constructor(props) {
		super(props);
		this.state = {};
	}
	render() {
		return (
			<Tab.Container id="left-tabs-example" defaultActiveKey="first">
				<Row style={{ padding: 0, margin: 0 }}>
					<Col sm={3}>
						<Nav variant="pills" className="flex-column">
							<Row>
								<Nav.Item>
									<Nav.Link eventKey="first">Tab 1</Nav.Link>
								</Nav.Item>
								<Nav.Item>
									<Nav.Link eventKey="second">Tab 2</Nav.Link>
								</Nav.Item>
							</Row>
						</Nav>
					</Col>
					<Col sm={12}>
						<Tab.Content>
							<Tab.Pane eventKey="first">
								<Table1 />
							</Tab.Pane>
							<Tab.Pane eventKey="second">
								<Table1 />
							</Tab.Pane>
						</Tab.Content>
					</Col>
				</Row>
			</Tab.Container>
		);
	}
}

export default Topics;
