import React from 'react';
import ReactDOM from 'react-dom';
import 'antd/dist/antd.css';
import { Table, Divider, Tag } from 'antd';
import axios from 'axios';

class managerHome extends React.Component {
  constructor(props) {
    super(props);
    this.editDetails = this.editDetails.bind(this);
    this.approveApplicaion = this.approveApplicaion.bind(this);
    this.rejectApplicaion = this.rejectApplicaion.bind(this);
  }
  state = {};

  editDetails(e) {
    console.log(e.target.id);
    axios({
    method: 'POST',
    url: 'http://10.15.15.65:9090/application-processing/managerRejectedLoans',
    //contentType: "application/json",
    data: e.target.id,
    //config: { headers: {'Content-Type': 'application/json' }}}
    })
    .then(function (response) {
        //handle success
        window.alert("success");
        window.location.reload();
        //window.location.assign("http://10.15.15.65:3000/form3?ID="+response.data);
    })
    .catch(function (response) {
        //handle error
        console.log(response);
    });

  }

  rejectApplicaion(e) {
    console.log(e.target.id);
    axios({
    method: 'POST',
    url: 'http://10.15.15.65:9090/application-processing/managerRejectedLoans',
    //contentType: "application/json",
    data: e.target.id,
    //config: { headers: {'Content-Type': 'application/json' }}}
    })
    .then(function (response) {
        //handle success
        window.alert("success");
        window.location.reload();
        //window.location.assign("http://10.15.15.65:3000/form3?ID="+response.data);
    })
    .catch(function (response) {
        //handle error
        console.log(response);
    });

  }
  
  approveApplicaion(e) {
    console.log(e.target.id);
    axios({
    method: 'POST',
    url: 'http://10.15.15.65:9090/application-processing/managerApprovedLoans',
    //contentType: "application/json",
    data: e.target.id,
    //config: { headers: {'Content-Type': 'application/json' }}}
    })
    .then(function (response) {
        //handle success
        window.alert("success");
        window.location.reload();
        //window.location.assign("http://10.15.15.65:3000/form3?ID="+response.data);
    })
    .catch(function (response) {
        //handle error
        console.log(response);
    });

  }
  
  componentDidMount() {
    axios.get(`http://10.15.15.65:9090/application-processing/managerRecievedApplications`)
      .then(res => {
        // const posts = res.data.data.children.map(obj => obj.data);
        console.log(res.data);
        var temp1 = res.data;
        for(var i=0;i<temp1["rows"].length;i++){
          for(var j=0;j<temp1["button"].length;j++){
            var iddata = temp1["rows"][i][temp1["button"][j]["key"]];
            var action = temp1["rows"][i][temp1["button"][j]["action"]]+i.toString();
            var title = temp1["rows"][i][temp1["button"][j]["title"]];
            
          var button1 = <span>
        <button type={"button"} id={iddata} onClick={this.editDetails}>E</button>
        <button type={"button"} id={iddata} onClick={this.approveApplicaion}>A</button>
        <button type={"button"} id={iddata} onClick={this.rejectApplicaion}>R</button>
        </span>;
        temp1["rows"][i][temp1["button"][j]["key"]] = button1;
        }
        }
        this.setState(temp1);
        console.log(res.data);
      
      });
  }

render(){
  console.log(this.state);
  const columns = this.state['columns'];
  const data = this.state['rows'];
return (
<Table columns={columns} dataSource={data}/>
);
} }         
export default managerHome;
