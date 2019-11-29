import React from 'react';
import ReactDOM from 'react-dom';
import 'antd/dist/antd.css';
import { Table, Divider, Tag } from 'antd';
import { Button } from 'reactstrap';
import axios from 'axios';

class table extends React.Component {
  constructor(props) {
    super(props);
  }
  state = {vaNumber:1,totalAmount:0}; 
  componentDidMount() {
    axios.get(`http://10.15.15.65:9091/ut/investor/confirmation-funding`)
      .then(res => { 
          console.log(res.data);         
        if(res.data['rows']!=null){
            this.setState(res.data);
            this.setState({vaNumber:res.data["additionalData"]["vaNumber"]});
            this.setState({totalAmount:res.data["additionalData"]["totalAmount"]})
        }
      });
  }
  paymentDone() {
      console.log(this.state['rows']);
      axios({
        method: 'POST',
        url: 'http://10.15.15.65:9091/ut/investor/payment-done',
        data: {"loanAppID":this.state['rows']},
        })
        .then(function (response) {
            window.location.reload();
        })
        .catch(function (response) {
          window.alert("Something went Wrong. Try Again : "+response);
        });
  }

render(){
  const columns = this.state['columns'];
  const data = this.state['rows'];
  var vaNumber = this.state.vaNumber;
  var totalAmount = this.state.totalAmount;
return (
    <div>  <div>
    <Table columns={columns} dataSource={data}/>
      </div>
     <div><p><b>VA Number : </b>{this.state.vaNumber}</p></div> 
     <div><p><b>Total Amount : </b>{this.state.totalAmount}</p></div> 
     <div><button id={this.data} type={"button"} onClick={this.paymentDone.bind(this)}>Done Payment</button></div> 
      </div>
);

} }         
export default table;