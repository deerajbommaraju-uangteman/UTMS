import React, { Component } from 'react';
import { MDBCol } from 'mdbreact';
import { Card, Button  } from 'react-bootstrap';

class payLoan extends Component {
  state = {
    
  };

  

  render() {
    return (
        <MDBCol md="6" className="mt-4 mx-auto">
            <Card className="text-center">
                <Card.Body>
                    <Card.Text>
                        Your time to pay for a  Loan
                    </Card.Text>
                    <Card.Title>5 more Days</Card.Title>
                    <Card.Text>(03 Nov 2019)</Card.Text><hr/>
                    <Card.Text>Amount to be paid</Card.Text>
                    <Card.Title>Rp. 1223445</Card.Title>
                   
                    <Button variant="primary" href="/payment">Pay Loans</Button>
                </Card.Body>
            </Card>
        
        </MDBCol>  
    );
  }
}

export default payLoan;