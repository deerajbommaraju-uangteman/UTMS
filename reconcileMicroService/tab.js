import React, { Component } from 'react';
import { MDBCol } from 'mdbreact';
import Table1 from './table1'
import { Tab, Row, Col, Nav,  } from 'react-bootstrap';

class tab extends Component {
  state = {
    
  };

  

  render() {
    return (
        <MDBCol>
        <Tab.Container id="left-tabs-example" defaultActiveKey="first">
            <Row className='mt-1'>
                <Col sm={0.1}>
                <Nav variant="pills" className="flex-column">
                    <Nav.Item>
                    <Nav.Link eventKey="first">Tab 1</Nav.Link>
                    </Nav.Item>
                    <Nav.Item>
                    <Nav.Link eventKey="second">Tab 2</Nav.Link>
                    </Nav.Item>
                </Nav>
                </Col>
                <Col sm={11}>
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
        </MDBCol>
       
       
      
    );
  }
}

export default tab;









