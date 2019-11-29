import React from 'react';
import ReactDOM from 'react-dom';
import 'antd/dist/antd.css';
import { Table, Divider, Tag } from 'antd';
import { Button } from 'reactstrap';
import axios from 'axios';

class investorHomePage extends React.Component {
  constructor(props) {
    super(props);
    this.fundLoan = this.fundLoan.bind(this);
    this.rejectLoan = this.rejectLoan.bind(this);
  }
  state = {};
  rejectLoan(e) {
    axios({
    method: 'POST',
    url: 'http://10.15.15.65:9091/ut/investor/rejectLoan',
    data: {"loanAppID":e.target.id},
    })
    .then(function (response) {
        //handle success
        //window.alert("success");
        window.location.reload();
    })
    .catch(function (response) {
        //handle error
        console.log(response);
    });

  }
  
  fundLoan(e) {
    console.log(e.target.id);
    axios({
    method: 'POST',
    url: 'http://10.15.15.65:9091/ut/investor/fundLoan',
    data: {"loanAppID":e.target.id},
    })
    .then(function (response) {
       // window.alert("success");
        window.location.reload();
    })
    .catch(function (response) {
        window.alert("Something went Wrong. Try Again : "+response);
    });

  }

  confirmationFunding(e) {
    window.location.assign("http://10.15.15.65:3000/paymentPage");
  }
  documents(e) {
    window.location.assign("http://10.15.15.65:3000/documentsPage");
  }

  
  componentDidMount() {
    axios.get(`http://10.15.15.65:9091/ut/investor/available-loan`)
      .then(res => {
        var temp1 = res.data;
        for(var i=0;i<temp1["rows"].length;i++){
          for(var j=0;j<temp1["button"].length;j++){
            var iddata = temp1["rows"][i]['loanAppID'];
            var button1 = <span>
              <button type={"button"} id={iddata} onClick={this.fundLoan}>Fund</button>&nbsp;&nbsp;&nbsp;
              <button type={"button"} id={iddata} onClick={this.rejectLoan}>Reject</button>
            </span>;
            temp1["rows"][i][temp1["button"][j]["key"]] = button1;
          }
        this.setState(temp1);
        }
      });
  }

render(){
  const columns = this.state['columns'];
  const data = this.state['rows'];
return (
  <div>
<Table columns={columns} dataSource={data}/>
  <div>
  <Button color="primary" onClick={this.confirmationFunding}>Confirmation of Funding</Button>{' '}
  </div>
  <div>
  <Button color="primary" onClick={this.documents}>Documents</Button>{' '}
  </div>
  </div>
);
} }         
export default investorHomePage;