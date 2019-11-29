import React, { Component } from "react";
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
import DocsLink from "../../components/docsLink";
import SectionContainer from "../../components/sectionContainer";
import DatePicker from "react-datepicker"; 
import "react-datepicker/dist/react-datepicker.css";
import '../ModalForm.css'

class ModalFormPage extends Component {
  state = {
    modal2: false,
  };

  toggle = nr => () => {
    let modalNumber = "modal" + nr;
    this.setState({
      [modalNumber]: !this.state[modalNumber]
    });
  };

  render() {
      const header={display:'flex', justifyContent:'center', backgroundColor:'#f5f5f5' }
    return (
      <MDBContainer>
        <DocsLink
          title="Modal Form"
          href="https://mdbootstrap.com/docs/react/modals/basic/"
        />

        <SectionContainer header="Simple modal register" flexCenter>
          <MDBBtn rounded onClick={this.toggle(2)}>
            Launch Modal Register Form
          </MDBBtn>

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
            <Tab eventKey="personal" title="personal">
            <MDBModalBody className="MDBModalBody">
            <Form>
                <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                        Full Name
                    </Form.Label>
                    <Col sm="8">
                    <Form.Control size="sm" type="text" placeholder="Full Name" />
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                        Email
                    </Form.Label>
                    <Col sm="8">
                    <Form.Control size="sm" type="email" placeholder="email" />
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
                    <Form.Control size="sm" type="text" placeholder="place of Birth" />
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
                    <Form.Control size="sm" type="text" placeholder="Child" />
                    </Col>
                </Form.Group>
                <Form.Group as={Row} controlId="formPlaintextEmail">
                    <Form.Label column sm="4">
                        Dependents
                    </Form.Label>
                    <Col sm="8">
                    <Form.Control size="sm" type="text" placeholder="Dependents" />
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
                    <Form.Control size="sm" type="text" placeholder="Mother Full Name" />
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
            <MDBModalHeader
                className="text-center"
                titleClass="w-100 font-weight-bold"
                toggle={this.toggle(2)}
                >
                Sign in
                </MDBModalHeader>
            <Tab eventKey="contact" title="Contact">
               
                <MDBModalBody className="MDBModalBody">
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
               
               <MDBModalBody className="MDBModalBody">
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
               <MDBModalBody className="MDBModalBody">
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
          </MDBModal>
        </SectionContainer>

      </MDBContainer>
    );
  }
}

export default ModalFormPage;
