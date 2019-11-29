import React from 'react';
import ReactDOM from 'react-dom';
import 'antd/dist/antd.css';
import { Table, Divider, Tag } from 'antd';
import axios from 'axios';
import {
  MDBContainer,
  MDBBtn,
  MDBModal,
  MDBModalBody,
  MDBModalHeader,
  MDBModalFooter,
  MDBInput,
} from "mdbreact";
import { Tabs, Tab, Form, Row, Col  } from 'react-bootstrap';
import DatePicker from "react-datepicker"; 
import "react-datepicker/dist/react-datepicker.css";
class dvHome extends React.Component {
  constructor(props) {
    super(props);
    this.addNotes = this.addNotes.bind(this);
    this.editDetails = this.editDetails.bind(this);
    this.approveApplicaion = this.approveApplicaion.bind(this);
    this.rejectApplicaion = this.rejectApplicaion.bind(this);
    this.toggle = this.toggle.bind(this);
    this.submitEditDetails = this.submitEditDetails.bind(this);
    
  }
  state = {
    modal2: false,
    datamodal: {},
  };

submitEditDetails(){
axios({
    method: 'POST',
    url: 'http://10.15.15.65:9090/application-processing/dvSubmitApplicationPersonalDetails',
    contentType: "application/json",
    data: {...this.state.datamodal},
    //config: { headers: {'Content-Type': 'application/json' }}}
    })
    .then(function (response) {
        //handle success
        //window.alert(response);
        console.log(response);
        this.toggle(2);

    })
    .catch(function (response) {
        //handle error
        console.log(response);
    });
};  
  toggle = nr => () => {
    console.log(this.state.data);
    let modalNumber = "modal" + nr;
    this.setState({
      [modalNumber]: !this.state[modalNumber]
    });
  };

addNotes(e){
    axios({
    method: 'POST',
    url: 'http://10.15.15.65:9090/application-processing/dvAddNotes',
    //contentType: "application/json",    
    data: {id : e.target.id},
    //config: { headers: {'Content-Type': 'application/json' }}}
    })
    .then(response => {
        //handle success
        // window.alert("success");
        var temp1 = response.data;
        let modalNumber = "modal" + 2;
        this.state.datamodal=response.data;
      this.setState({
        [modalNumber]: !this.state[modalNumber]
      });
      console.log(this.state[modalNumber]);
    })
    .catch(response=>{
        //handle error
        console.log(response);
    });
  }
  editDetails(e) {
    axios({
    method: 'POST',
    url: 'http://10.15.15.65:9090/application-processing/dvEditApplicationPersonalDetails',
    //contentType: "application/json",    
    data: {id : e.target.id},
    //config: { headers: {'Content-Type': 'application/json' }}}
    })
    .then(response => {
        //handle success
        // window.alert("success");
        var temp1 = response.data;
        let modalNumber = "modal" + 2;
        this.state.datamodal=response.data;
      this.setState({
        [modalNumber]: !this.state[modalNumber]
      });
      console.log(this.state[modalNumber]);
    })
    .catch(response=>{
        //handle error
        console.log(response);
    });
  }
  rejectApplicaion(e) {
    axios({
    method: 'POST',
    url: 'http://10.15.15.65:9090/application-processing/dvRejectedLoans',
    //contentType: "application/json",
    data: e.target.id,
    //config: { headers: {'Content-Type': 'application/json' }}}
    })
    .then(function (response) {
        //handle success
        // window.alert("success");
        window.location.reload();
        //window.location.assign("http://10.15.15.65:3000/form3?ID="+response.data);
    })
    .catch(function (response) {
        //handle error
        console.log(response);
    });

  }
  
  approveApplicaion(e) {
    axios({
    method: 'POST',
    url: 'http://10.15.15.65:9090/application-processing/dvApprovedLoans',
    //contentType: "application/json",
    data: e.target.id,
    //config: { headers: {'Content-Type': 'application/json' }}}
    })
    .then(response => {
        //handle success
        // window.alert("success");
        window.location.reload();
        //window.location.assign("http://10.15.15.65:3000/form3?ID="+response.data);
        console.log(this.state.data);
    })
    .catch(response =>{
        //handle error
        console.log(response);
    });

  }
  
  componentDidMount() {
    axios.get(`http://10.15.15.65:9090/application-processing/dvRecievedApplications`)
      .then(res => {
        // const posts = res.data.data.children.map(obj => obj.data);
        //console.log(res.data);
        var temp1 = res.data;
        if(temp1){
        for(var i=0;i<temp1["rows"].length;i++){
          for(var j=0;j<temp1["button"].length;j++){
            var iddata = temp1["rows"][i][temp1["button"][j]["key"]];
            var action = temp1["rows"][i][temp1["button"][j]["action"]]+i.toString();
            
            var button1 = <span>
              <button type={"button"} id={iddata} onClick={this.addNotes}>Add Note</button>&nbsp;&nbsp;&nbsp;
              <button type={"button"} id={iddata} onClick={this.editDetails}>Edit</button>&nbsp;&nbsp;&nbsp;
              <button type={"button"} id={iddata} onClick={this.approveApplicaion}>Aprrove</button>&nbsp;&nbsp;&nbsp;
              <button type={"button"} id={iddata} onClick={this.rejectApplicaion}>Reject</button>
            </span>;
            temp1["rows"][i][temp1["button"][j]["key"]] = button1;
          }
        this.setState(temp1);
        //console.log(res.data);
        }
        }
      });
  }

render(){
  const header={display:'flex', justifyContent:'center', backgroundColor:'#f5f5f5' }
  //console.log(this.state);
  const columns = this.state['columns'];
  const data = this.state['rows'];
return (
  <div>
<Table columns={columns} dataSource={data}/>
<MDBModal isOpen={this.state.modal2} toggle={this.toggle(2)}>
            <MDBModalHeader
                className="text-center"
                titleClass="w-100 font-weight-bold"
                toggle={this.toggle(2)}
                >
                Application Information
            </MDBModalHeader>
          <Tabs defaultActiveKey="personal" transition={false} id="noanim-tab-example">
            <Tab eventKey="application" title="Application">
            <MDBModalBody>
            
            </MDBModalBody>
            <MDBModalFooter className="justify-content-center">
              <MDBBtn color="deep-orange" onClick={this.toggle(2)}>
                Submit
              </MDBBtn>
              <MDBBtn color="deep-orange" onClick={!this.toggle(2)}>
                Cancel
              </MDBBtn>
            </MDBModalFooter>
            </Tab>
            <MDBModalHeader
              className="text-center"
              titleClass="w-100 font-weight-bold"
              toggle={this.toggle(2)}
            >
              Sign in
            </MDBModalHeader>
            <Tab eventKey="personal" title="personal">
            <MDBModalBody>
            <Form>
                <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                        Full Name
                    </Form.Label>
                    <Col sm="8">
                    <Form.Control editable size="sm" type="text" onChange={e => this.state.datamodal["Fullname"] = e.target.value} defaultValue={this.state.datamodal["Fullname"]}/>
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                        Email
                    </Form.Label>
                    <Col sm="8">
                    <Form.Control size="sm" type="email" placeholder="email" onChange={e => this.state.datamodal["EmailAddress"] = e.target.value} defaultValue={this.state.datamodal["EmailAddress"]} />
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                        Gender
                    </Form.Label>
                    <Col sm="8">
                    <Form.Check size="sm" type="radio" inline label="male" name="gender"/>
                    <Form.Check size="sm" type="radio" inline label="female" name="gender"/>
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                        Place of Birth
                    </Form.Label>
                    <Col sm="4">
                    <Form.Control size="sm" type="text" placeholder="place of Birth" onChange={e => this.state.datamodal["PlaceOfBirth"] = e.target.value} defaultValue={this.state.datamodal["PlaceOfBirth"]}/>
                    </Col>
                    <Col sm="4">
                        <DatePicker className='form-control'
                            selected={this.state.startDate}
                            onChange={this.handleChange}
                        />
                    </Col>
                   
                </Form.Group>
                <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                        Religion
                    </Form.Label>
                    <Col sm="8">
                        <Form.Control as="select" size="sm">
                            <option>Choose...</option>
                            <option>...</option>
                        </Form.Control>
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                        Education
                    </Form.Label>
                    <Col sm="8">
                        <Form.Control as="select" size="sm">
                            <option>Choose...</option>
                            <option>...</option>
                        </Form.Control>
                    </Col>
                </Form.Group>  
                <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                        Marital Status
                    </Form.Label>
                    <Col sm="8">
                        <Form.Control as="select" size="sm">
                            <option>Choose...</option>
                            <option>...</option>
                        </Form.Control>
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                        Child
                    </Form.Label>
                    <Col sm="8">
                    <Form.Control size="sm" type="text" placeholder="Child" onChange={e => this.state.datamodal["AmountChild"] = e.target.value} defaultValue={this.state.datamodal["AmountChild"]}/>
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                        Dependents
                    </Form.Label>
                    <Col sm="8">
                    <Form.Control disabled size="sm" type="text" placeholder="Dependents" />
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                        Race
                    </Form.Label>
                    <Col sm="8">
                        <Form.Control as="select" size="sm">
                            <option>Choose...</option>
                            <option>...</option>
                        </Form.Control>
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                        Mother Full Name
                    </Form.Label>
                    <Col sm="8">
                    <Form.Control size="sm" type="text" placeholder="Mother Full Name" onChange={e => this.state.datamodal["MotherFullName"] = e.target.value} defaultValue={this.state.datamodal["MotherFullName"]}/>
                    </Col>
                </Form.Group>

            </Form>

            </MDBModalBody>
            <MDBModalFooter className="justify-content-center">
              <MDBBtn color="deep-orange" onClick={this.submitEditDetails}>
                Submit
              </MDBBtn>
              <MDBBtn color="deep-orange" onClick={this.toggle(2)}>
                Cancel
              </MDBBtn>
            </MDBModalFooter>
            </Tab>
            <MDBModalHeader
                className="text-center"
                titleClass="w-100 font-weight-bold"
                toggle={this.toggle(2)}
                >
                Sign in
                </MDBModalHeader>
            <Tab eventKey="contact" title="Contact">
               
                <MDBModalBody>
                <form className="mx-3 grey-text">
                <Form.Group controlId="formPlaintextEmail">
                    <p style={header}>KPU Personal id Info</p>
                    <Form.Label column>
                        Personal Id
                    </Form.Label>
                    <Form.Label column>
                        Full Name
                    </Form.Label> <Form.Label column>
                        Gender
                    </Form.Label>
                    <Form.Label column>
                        Province
                    </Form.Label>
                    <Form.Label column>
                        City/Muncipality/Regency
                    </Form.Label>
                    <Form.Label column>
                        Sub-District
                    </Form.Label>
                    <Form.Label column>
                        Village
                    </Form.Label>
                </Form.Group>
                <Form.Group>
                    <p style={header}>Personal Info</p>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            Personal Id
                        </Form.Label>
                        <Col sm="8">
                        <Form.Control size="sm" type="text" placeholder="Personal Id" />
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            Mobile Number
                        </Form.Label>
                        <Col sm="8">
                        <Form.Control size="sm" type="number" placeholder="Mobile Number" />
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            Mobile Number 2
                        </Form.Label>
                        <Col sm="8">
                        <Form.Control size="sm" type="number" placeholder="Mobile Number 2" />
                        </Col>
                    </Form.Group>
                </Form.Group>
                <Form.Group>
                    <p style={header}>KTP Address</p>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            KTP Address
                        </Form.Label>
                        <Col sm="8">
                        <Form.Control size="sm" type="text" placeholder="KTP Address" />
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            Province
                        </Form.Label>
                        <Col sm="8">
                            <Form.Control as="select" size="sm">
                                <option>Choose...</option>
                                <option>...</option>
                            </Form.Control>
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            City/Muncipality/Regency
                        </Form.Label>
                        <Col sm="8">
                            <Form.Control as="select" size="sm">
                                <option>Choose...</option>
                                <option>...</option>
                            </Form.Control>
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            Sub-district
                        </Form.Label>
                        <Col sm="8">
                            <Form.Control as="select" size="sm">
                                <option>Choose...</option>
                                <option>...</option>
                            </Form.Control>
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            Village
                        </Form.Label>
                        <Col sm="8">
                            <Form.Control as="select" size="sm">
                                <option>Choose...</option>
                                <option>...</option>
                            </Form.Control>
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            Post Code
                        </Form.Label>
                        <Col sm="8">
                        <Form.Control size="sm" type="number" placeholder="Post Code" />
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            Phone Number
                        </Form.Label>
                        <Col sm="8">
                        <Form.Control size="sm" type="number" placeholder="Phone Number" />
                        </Col>
                    </Form.Group>
                </Form.Group>
                <Form.Group>
                    <p style={header}>Domicile Address</p>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            Domicile Address
                        </Form.Label>
                        <Col sm="8">
                        <Form.Control size="sm" type="text" placeholder="Domicile Address" />
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            RT
                        </Form.Label>
                        <Col sm="8">
                        <Form.Control size="sm" type="number" placeholder="RT" />
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            RW
                        </Form.Label>
                        <Col sm="8">
                        <Form.Control size="sm" type="number" placeholder="Phone Number" />
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            Province
                        </Form.Label>
                        <Col sm="8">
                            <Form.Control as="select" size="sm">
                                <option>Choose...</option>
                                <option>...</option>
                            </Form.Control>
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            City/Muncipality/Regency
                        </Form.Label>
                        <Col sm="8">
                            <Form.Control as="select" size="sm">
                                <option>Choose...</option>
                                <option>...</option>
                            </Form.Control>
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            sub-district
                        </Form.Label>
                        <Col sm="8">
                            <Form.Control as="select" size="sm">
                                <option>Choose...</option>
                                <option>...</option>
                            </Form.Control>
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            Village
                        </Form.Label>
                        <Col sm="8">
                            <Form.Control as="select" size="sm">
                                <option>Choose...</option>
                                <option>...</option>
                            </Form.Control>
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            Post Code
                        </Form.Label>
                        <Col sm="8">
                        <Form.Control size="sm" type="number" placeholder="Post Code" />
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            Phone Number
                        </Form.Label>
                        <Col sm="8">
                        <Form.Control size="sm" type="number" placeholder="Phone Number" />
                        </Col>
                    </Form.Group>





                    
                    <Form.Group>
                    <p style={header}>Family1 Address</p>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            Family Name
                        </Form.Label>
                        <Col sm="8">
                        <Form.Control size="sm" type="text" placeholder="Family Name" />
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            Family Address
                        </Form.Label>
                        <Col sm="8">
                        <Form.Control size="sm" type="text" placeholder="Family Address" />
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            Province
                        </Form.Label>
                        <Col sm="8">
                            <Form.Control as="select" size="sm">
                                <option>Choose...</option>
                                <option>...</option>
                            </Form.Control>
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            City/Muncipality/Regency
                        </Form.Label>
                        <Col sm="8">
                            <Form.Control as="select" size="sm">
                                <option>Choose...</option>
                                <option>...</option>
                            </Form.Control>
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            sub-district
                        </Form.Label>
                        <Col sm="8">
                            <Form.Control as="select" size="sm">
                                <option>Choose...</option>
                                <option>...</option>
                            </Form.Control>
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            Village
                        </Form.Label>
                        <Col sm="8">
                            <Form.Control as="select" size="sm">
                                <option>Choose...</option>
                                <option>...</option>
                            </Form.Control>
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            Post Code
                        </Form.Label>
                        <Col sm="8">
                        <Form.Control size="sm" type="number" placeholder="Post Code" />
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            Phone Number
                        </Form.Label>
                        <Col sm="8">
                        <Form.Control size="sm" type="number" placeholder="Phone Number" />
                        </Col>
                    </Form.Group>
                    <Form.Group as={Row} controlId="formPlaintextEmail">
                        <Form.Label column sm="4">
                            Other Notes
                        </Form.Label>
                        <Col sm="8">
                        <Form.Control size="sm" type="text" placeholder="Other Notes" />
                        </Col>
                    </Form.Group>
                    
                    </Form.Group>

                    
                </Form.Group>
                </form>
                </MDBModalBody>
                <MDBModalFooter className="justify-content-center">
                <MDBBtn color="deep-orange" onClick={this.toggle(2)}>
                    Submit
                </MDBBtn>
                <MDBBtn color="deep-orange" onClick={this.toggle(2)}>
                    Cancel
                </MDBBtn>
                </MDBModalFooter>
            </Tab>
            <Tab eventKey="bank" title="Bank">
               
               <MDBModalBody>
               <form className="mx-3 grey-text">
               <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                        Bank Name
                    </Form.Label>
                    <Col sm="8">
                    <Form.Control size="sm" type="text" placeholder="bank name" />
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                        Bank Account Name
                    </Form.Label>
                    <Col sm="8">
                    <Form.Control size="sm" type="text" placeholder="Bank Acc Name" />
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                        Bank Account Number
                    </Form.Label>
                    <Col sm="8">
                    <Form.Control size="sm" type="text" placeholder="bank acc num" />
                    </Col>
                </Form.Group>
               </form>
               </MDBModalBody>
               <MDBModalFooter className="justify-content-center">
               <MDBBtn color="deep-orange" onClick={this.toggle(2)}>
                   Submit
               </MDBBtn>
               <MDBBtn color="deep-orange" onClick={this.toggle(2)}>
                   Cancel
               </MDBBtn>
               </MDBModalFooter>
               </Tab>
               <Tab eventKey="other" title="Other">
               <MDBModalBody>
               <Form>
               <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                        Employment type
                    </Form.Label>
                    <Col sm="8">
                        <Form.Control as="select" size="sm">
                            <option>Choose...</option>
                            <option>...</option>
                        </Form.Control>
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                        Employer Name
                    </Form.Label>
                    <Col sm="8">
                    <Form.Control size="sm" type="text" placeholder="employer name" />
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                        Employer Role
                    </Form.Label>
                    <Col sm="8">
                    <Form.Control size="sm" type="text" placeholder="Employer Role" />
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                        Employer Address
                    </Form.Label>
                    <Col sm="8">
                    <Form.Control size="sm" type="text" placeholder="Employer Address" />
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                        Employer phone Number
                    </Form.Label>
                    <Col sm="8">
                    <Form.Control size="sm" type="number" placeholder="Employer Phone Number" />
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                        Monthly Income
                    </Form.Label>
                    <Col sm="8">
                    <Form.Control size="sm" type="number" placeholder="Montly Income" />
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                        Expenses
                    </Form.Label>
                    <Col sm="8">
                    <Form.Control size="sm" type="number" placeholder="Expenses" />
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                        House Loan
                    </Form.Label>
                    <Col sm="8">
                    <Form.Control size="sm" type="text" placeholder="House loan" />
                    </Col>
                </Form.Group>
               
                  
                <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                        Know About UT
                    </Form.Label>
                    <Col sm="8">
                        <Form.Control as="select" size="sm">
                            <option>Choose...</option>
                            <option>...</option>
                        </Form.Control>
                    </Col>
                </Form.Group>
                

            </Form>
               </MDBModalBody>
               <MDBModalFooter className="justify-content-center">
               <MDBBtn color="deep-orange" onClick={this.toggle(2)}>
                   Submit
               </MDBBtn>
               <MDBBtn color="deep-orange" onClick={this.toggle(2)}>
                   Cancel
               </MDBBtn>
               </MDBModalFooter>
               </Tab>
            </Tabs>
          </MDBModal></div>
);
} }         
export default dvHome;
