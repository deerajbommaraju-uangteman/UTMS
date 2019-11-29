import React, { Component } from 'react';
import { MDBCol } from 'mdbreact';
import { Card, Button  } from 'react-bootstrap';
import axios from 'axios';  

class payLoan extends Component {
    constructor(props) {
    super(props);
    this.viewLoanData = this.viewLoanData.bind(this);
    this.paymentPage = this.paymentPage.bind(this);
  }	
  state = {
    LoanApplicationID : '',
  };

  handleChange = (e) => {
    this.setState({ LoanApplicationID: e.target.value });
  }

  
    viewLoanData(e) {
    console.log(e.target.value);
    axios({
    method: 'POST',
    url: 'http://10.15.15.65:9093/user/postLoginDetails?LoanApplicationID=' + this.state.LoanApplicationID,
    contentType: "application/json",
    data:{"LoanApplicationID": this.state['LoanApplicationID']},
    })
    .then((response) => {
        // window.alert(response.data["repaymentAmount"]);
        this.setState({ "repaymentAmount": response.data["repaymentAmount"], "dueDate" : response.data["dueDate"]});    //this.setState({name: response.data.name});
  })
    .catch((e) => {
     console.error(e);
     window.alert(e);
  });

  }

  paymentPage(e){
    e.preventDefault();
    console.log({...this.state})
    window.location.assign("http://10.15.15.65:3000/payment?LoanApplicationID="+this.state.LoanApplicationID);
  }

// submitHandler = event => {
//     //alert({...this.state})
//     console.log({...this.state})
//     axios({
//     method: 'POST',
//     url: 'http://10.15.15.65:9093/user/postLoginDetails?LoanApplicationID=' + this.state.LoanApplicationID,
//     contentType: "application/json",
//     data: {"LoanApplicationID":e.state.value},
//     //config: { headers: {'Content-Type': 'application/json' }}}
//     })
//     .then(function (data) {
//         //handle success
//         //window.alert(data);
//         // console.log(data.RepaymentAmount);
//         console.log(data);
//         window.alert(data.repaymentAmount);
//          this.setState({ ...this.state, [event.target.name]: data.repaymentAmount});
//         // window.location.assign("http://10.15.15.65:3000/form4?ApplicantId="+data.data);
//     })
//     .catch(function (data) {
//         //handle error
//         window.alert("No active loans");
//     });
//     event.preventDefault();
//     event.target.className += 'text-center';
//   };
 render() {
     return (
        <MDBCol md="6" className="mt-4 mx-auto">
            <Card className="text-center">
                <Card.Body>
                    <Card.Text>
                        Enter your loan Id:
                    </Card.Text>
                    <form className='needs-validation' noValidate>

                       <input type="text" name="LoanApplicationID" onChange={this.handleChange}/>
                       <Button type={"button"} variant="primary" onClick={this.viewLoanData}>Submit</Button>
        
                    <hr/>
                    
                    <div>                    
                    <Card.Text>Your time to pay your loan</Card.Text>
                    <Card.Title>{this.state.dueDate}</Card.Title>
                    <Card.Text>Amount to be paid</Card.Text>
                    <Card.Title>Rs.{this.state.repaymentAmount}</Card.Title>
                    <Button variant="primary" type={"submit"} onClick={this.paymentPage}>Pay Loans</Button>
                    </div>
                    </form> 
                </Card.Body>
            </Card>
        
        </MDBCol>  
     );
	}    
}

export default payLoan;