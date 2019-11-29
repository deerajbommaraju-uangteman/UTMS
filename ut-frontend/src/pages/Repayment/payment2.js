import React, { Component } from 'react';
import { Card, Button, Col, Row  } from 'react-bootstrap';

class payment2 extends Component {
  state = {
    
  };

  

  render() {
    return (
        <Col md="12">
        <Row>
        <Col md="6" className="mt-3 text-center offset-md-3">
        <Card.Title className="">Pay Loans</Card.Title>
        <Card.Text>
           <b>1234343535454</b>
        </Card.Text>
            <Card className=" mb-3">
            
                <Card.Body>
                    <Card.Text>
                        please Complete Payment before 23:59 WIB
                    </Card.Text>
                    <Card.Text>
                        <b>Amount to be paid by Today</b>
                    </Card.Text>
                    <Card.Text>
                       <h1>Rp. 1,657,500</h1>
                    </Card.Text>
                    <Card.Text>
                        <b>No virtual Account you</b>
                    </Card.Text>
                    <Card.Text>
                       <h1>1454654655600</h1>
                    </Card.Text>
                    <Card.Text>
                        No virtual Account is valid until29-october-2019
                    </Card.Text>
                    <Card.Text>Note: Please pay a nominal amount according to figures above. The payment amount and Virtual Account number are only valid for today.</Card.Text>
                    <Card.Text>
                        <Button variant="outline-primary">Change Payment method</Button>
                    </Card.Text>
                </Card.Body>
            </Card>
        </Col>
        
           
        </Row>
    </Col>
          
    );
  }
}

export default payment2;