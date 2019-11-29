import React, { Component } from 'react';
import { MDBRow, MDBCol } from 'mdbreact';
import { Card, Button  } from 'react-bootstrap';

class payment extends Component {
  state = {
    
  };

  

  render() {
    return (
        <MDBCol md="12">
        <MDBRow>
        <MDBCol md="6" className="mt-3">
            <Card className="text-center">
                <Card.Text>
                        See How to Pay
                </Card.Text>
                <Card.Body>
                    <Card.Text>
                        <Button variant="outline-primary">Bank Transfer to BCA </Button>
                    </Card.Text>
                    <Card.Text>
                        <Button variant="outline-primary">Bank Transfer to ATM</Button>
                    </Card.Text>
                    <Card.Text>
                        <Button variant="outline-primary">Bank Transfer to Alfamart</Button>
                    </Card.Text>
                    <Card.Text>
                        <Button variant="outline-primary">Bank Transfer to CIMB Niaga</Button>
                    </Card.Text>
                </Card.Body>
            </Card>
        </MDBCol>
        <MDBCol md="6"  className="mt-3">
            <Card>
                <Card.Body>
                    <Card.Text className="row">
                        payment Amount:<h3>1234</h3>
                    </Card.Text>
                    <Card.Text className="row">
                        Loan Type:<h3>pay Day</h3>
                    </Card.Text>
                    <Card.Text className="row">
                        Pay Limit:<h3>3 November</h3> 
                    </Card.Text>
                    <hr />
                    <Card.Text>
                        See Loan Agreement
                    </Card.Text>
                
                    
                </Card.Body>
            </Card>
            </MDBCol>
        </MDBRow>
    </MDBCol>
          
    );
  }
}

export default payment;