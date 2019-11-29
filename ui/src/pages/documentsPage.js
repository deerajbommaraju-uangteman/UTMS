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
  state = {};
  signDocument(e) {
    var val=e.target.id;
    axios({
    method: 'POST',
    url: 'http://10.15.15.65:9091/ut/investor/lender-signed-Document',
    data: {"documentID":e.target.id},
    })
    .then(function (response) {
        //handle success
        //console.log(response);
        //window.alert("success");
        window.location.reload();
    })
    .catch(function (response) {
      window.alert("Something went Wrong. Try Again : "+response);
    });

  }
  
  componentDidMount() {
    axios.get(`http://10.15.15.65:9091/ut/investor/investor-documents`)
      .then(res => {
        var temp1 = res.data;
        for(var i=0;i<temp1["rows"].length;i++){
          for(var j=0;j<temp1["button"].length;j++){
            var iddata = temp1["rows"][i]["documentLenderID"];
            if(temp1["rows"][i]['lenderAgreementStatus']=='UnSigned'){
              var button1 = <span>
              <button type={"button"} id={iddata} onClick={this.signDocument}>signDocument</button>&nbsp;&nbsp;&nbsp;
            </span>;
            temp1["rows"][i]['lenderAgreementStatus'] = button1;
            }
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
  </div>
);

} }         
export default table;