import React, { Component } from 'react';
import { MDBRow, MDBCol } from 'mdbreact';
import { Card, Button  } from 'react-bootstrap';
import axios from 'axios';

class payment extends Component {
  state = {
    LoanApplicationID : '',
    paymentType : ''

  };

  handleSubmit = (e) => {
   // this.setState({ paymentType: e.target.value });
   // console.log(e.target.value);
   const searchParams  = new URLSearchParams(window.location.search);
   console.log("loanId:"+ searchParams.get('LoanApplicationID'));

   axios({
    method: 'POST',
    url: 'http://10.15.15.65:9093/user/makeLoanRepayment',
    contentType: "application/json",
    data: {
        "LoanApplicationID": searchParams.get('LoanApplicationID'),
        "paymentType": e.target.value
            },
    //config: { headers: {'Content-Type': 'application/json' }}}
    })
   .then((result) => {
        //handle success
        //window.alert(data);
        console.log(result);
        console.log(this.state);
        window.alert(result.data["response"]);
         // this.setState({ ...this.state, [event.target.name]: result.data["RepaymentAmount"]});
        // window.location.assign("http://10.15.15.65:3000/form4?ApplicantId="+data.data);
    })
    .catch((e) => {
        //handle error
        console.log({...this.state})
        window.alert(e);
    });
  }

  // submitHandler = event => {
  //    event.preventDefault();
  //   event.target.className += 'text-center';
  //   console.log({...this.state})
  //   axios({
  //   method: 'POST',
  //   url: 'http://10.15.15.65:9093/user/makeLoanRepayment',
  //   contentType: "application/json",
  //   data: {...this.state},
  //   //config: { headers: {'Content-Type': 'application/json' }}}
  //   })
  //  .then((result) => {
  //       //handle success
  //       //window.alert(data);
  //       console.log(result);
  //       console.log(this.state);
  //       window.alert(result.data["RepaymentAmount"]);
  //        // this.setState({ ...this.state, [event.target.name]: result.data["RepaymentAmount"]});
  //       // window.location.assign("http://10.15.15.65:3000/form4?ApplicantId="+data.data);
  //   })
  //   .catch((e) => {
  //       //handle error
  //       // window.alert(e.data['message']);
  //   });
   
  // };

  

  render() {
    return (
        <MDBCol md="12">
        <MDBRow>
        <MDBCol md="6" className="mt-3">
            <Card className="text-center">
                <Card.Text>
                        Select payment method to generate VA number
                </Card.Text>
                <Card.Body>
                    <Card.Text>
                        <Button variant="outline-primary" name='paymentType' onClick={(event) => {this.setState({ paymentType: event.target.value });this.handleSubmit(event)} } value="doku-bca">Bank Transfer to BCA </Button>
                    </Card.Text>
                    <Card.Text>
                        <Button variant="outline-primary" name='paymentType' onClick={(event) => {this.setState({ paymentType: event.target.value });this.handleSubmit(event)} } value="cimb">Bank Transfer to CIMB Niaga</Button>
                    </Card.Text>
                    <Card.Text>
                        <Button variant="outline-primary" name='paymentType' onClick={(event) => {this.setState({ paymentType: event.target.value });this.handleSubmit(event)} } value="doku">Bank Transfer to Alfamart</Button>
                    </Card.Text>
                    <Card.Text>
                        <Button variant="outline-primary" name='paymentType' onClick={(event) => {this.setState({ paymentType: event.target.value });this.handleSubmit(event)} } value="atm_bersama">Bank Transfer to Artajasa</Button>
                    </Card.Text>
                </Card.Body>
            
            </Card>
        </MDBCol>
        <MDBCol md="6"  className="mt-3">
            <Card>
                <Card.Body>
                    <Card.Text className="row" name="Amount">
                        Payment Amount:<h3></h3>
                    </Card.Text>
                    <Card.Text className="row">
                        Loan Type:<h3>Pay day</h3>
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