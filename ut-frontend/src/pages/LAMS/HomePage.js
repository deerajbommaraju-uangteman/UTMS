import React from "react";
import {
  MDBEdgeHeader,
  MDBFreeBird,
  MDBCol,
  MDBRow,
 
} from "mdbreact";
import Tabs from 'react-bootstrap/Tabs';
import Tab from 'react-bootstrap/Tab';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';


import "./HomePage.css";

class HomePage extends React.Component {
  scrollToTop = () => window.scrollTo(0, 0);
  render() {
    return (
      <>
        <MDBEdgeHeader color="indigo darken-3" className="sectionPage" />
        <div className="mt-3 mb-5">
          <MDBFreeBird>
            <MDBRow>
              <MDBCol
                md="11"
                className="mx-auto float-none white z-depth-1 py-2 px-2"
              >
               
                <Tabs defaultActiveKey="home" transition={false} id="noanim-tab-example">
                  <Tab eventKey="home" title="Online Loans">
                    <Button variant="primary" href="/form1">Apply for a Loan</Button>
                  </Tab>
                  <Tab eventKey="profile" title="Loan Status">
                  <Form className="mt-4">
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                      <Form.Label column sm="2">
                        Mobile Number
                      </Form.Label>
                      <Col sm="2">
                      <Form.Control type="number" placeholder="Mobile Number" />
                      </Col>
                      <Form.Label column sm="2">
                        Id Card Number
                      </Form.Label>
                      <Col sm="2">
                        <Form.Control type="number" placeholder="Id Card Number" />
                      </Col>
                      <Button variant="primary" sm="1">Check</Button>
                    </Form.Group>
                  </Form>
                  </Tab>
                  <Tab eventKey="contact" title="Customer Login">
                  <Form className="mt-4">
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                      <Form.Label column sm="2">
                        Email Address
                      </Form.Label>
                      <Col sm="2">
                      <Form.Control type="email" placeholder="Email Address" />
                      </Col>
                      <Form.Label column sm="2">
                        Password
                      </Form.Label>
                      <Col sm="2">
                        <Form.Control type="password" placeholder="Password" />
                      </Col>
                      <Button variant="primary" sm="1">submit</Button>
                    </Form.Group>
                  </Form>
                  </Tab>
                </Tabs>
              </MDBCol>
            </MDBRow>
          </MDBFreeBird>
        </div>
      </>
    );
  }
}

export default HomePage;
