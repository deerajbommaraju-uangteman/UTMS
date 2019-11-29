import React, { Component } from 'react';
import axios from 'axios';
import { MDBContainer, MDBRow, MDBCol, MDBBtn } from 'mdbreact';
import DocsLink from './../components/docsLink';
import SectionContainer from './../components/sectionContainer';
import DatePicker from "react-datepicker"; 
import "react-datepicker/dist/react-datepicker.css";

var urlParams = new URLSearchParams(window.location.search);
console.log(urlParams.get('ID')); // "edit"
class fourthForm extends Component {
  //var par= getParams(this.props.location)
  state = {
    Fullname: '',
    PlaceOfBirth:'',
    Gender:'',
    //DateOfBirth: '',
    Religion:'',
    MaritalStatus:'',
    ID : parseInt(urlParams.get('ID')),
    FormID : 'form4'

  };

  submitHandler = event => {
    console.log({...this.state});
    axios({
    method: 'POST',
    url: 'http://10.15.15.65:9090/application-form/received',
    contentType: "application/json",
    data: {...this.state},
    //config: { headers: {'Content-Type': 'application/json' }}}
    })
    .then(function (response) {
        //handle success
        //window.alert(response);
        window.location.assign("http://10.15.15.65:3000/form5?ID="+response.data);
    })
    .catch(function (response) {
        //handle error
        console.log(response);
    });
    event.preventDefault();
    event.target.className += ' was-validated';
  };

  changeHandler = event => {
    this.setState({ ...this.state, [event.target.name]: event.target.value});
  };
  handleChange = date => {
    this.setState({
      DateOfBirth: new Date()
    });
  };

  render() {
    return (
      <MDBContainer>
        <DocsLink
          title='Form'
          href='https://mdbootstrap.com/docs/react/forms/validation/'
        />
        <SectionContainer header='Validations'>
          <form
            className='needs-validation'
            onSubmit={this.submitHandler}
            noValidate
          >
            
              <MDBCol md='4' className='mb-3'>
                <label
                  htmlFor='defaultFormRegisterNameEx'
                  className='grey-text'
                >
                  Full name
                </label>
                <input
                  value={this.state.Fullname}
                  name='Fullname'
                  onChange={this.changeHandler}
                  type='text'
                  id='defaultFormRegisterNameEx'
                  className='form-control'
                  placeholder='Full name'
                  required
                />
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>
              <MDBCol md='4' className='mb-3'>
                <label
                  htmlFor='defaultFormRegisterName'
                  className='grey-text'
                >
                  Place of Birth
                </label>
                <input
                  value={this.state.PlaceOfBirth}
                  name='PlaceOfBirth'
                  onChange={this.changeHandler}
                  type='text'
                  id='defaultFormRegisterName'
                  className='form-control'
                  placeholder='place of birth'
                  required
                />
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>
              <MDBCol md='4' className='mb-3'>
              <label
                  className='grey-text'
                >
                  Date Of Birth
                </label>
               
              <DatePicker className='form-control'
                selected={this.state.DateOfBirth}
                onChange={this.handleChange}
                />
            </MDBCol>
                
              <MDBCol md='4' className='mb-3'>
              <label 
                  className='grey-text'
                >
                  Gender
                </label>
              <div className='custom-control custom-radio'>
                <input
                 checked={this.state.Gender === "M"}
                 value="M"
                 onChange={this.changeHandler}
                  type='radio'
                  className='custom-control-input'
                  id='customControlValidation2'
                  name='Gender'
                  required
                />
                <label
                  className='custom-control-label'
                  htmlFor='customControlValidation2'
                >
                  male
                </label>
              </div>
              <div className='custom-control custom-radio mb-3'>
                <input
                checked={this.state.Gender === "F"}
                value="F"
                 onChange={this.changeHandler}
                  type='radio'
                  className='custom-control-input'
                  id='customControlValidation3'
                  name='Gender'
                  required
                />
                <label
                  className='custom-control-label'
                  htmlFor='customControlValidation3'
                >
                  female
                </label>
                <div className='invalid-feedback'>
                  Select any radio button
                </div>
              </div>
              </MDBCol>
              <MDBCol md='4' className='mb-3'>
              <label
                  className='grey-text'
                >
                  Religion
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='Religion' value={this.state.Religion} className='custom-select bMDBRowser-default' required>
                  <option value=''>Choose Religion</option>
                  <option value='hindu'>Hindu</option>
                  <option value='muslim'>Muslim</option>
                  <option value='sikh'>sikh</option>
                </select>
                <div className='invalid-feedback'>
                  Select one
                </div>
              </div>
              </MDBCol>
              <MDBCol md='4' className='mb-3'>
              <label
                  className='grey-text'
                >
                  Martial Status
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='MaritalStatus' value={this.state.MaritalStatus} className='custom-select bMDBRowser-default' required>
                  <option value=''>Choose Martial Status</option>
                  <option value='single'>Single</option>
                  <option value='married'>Married</option>
                  <option value='divorced'>Divorced</option>
                </select>
                <div className='invalid-feedback'>
                  Select one
                </div>
              </div>
              </MDBCol>
            
            <MDBBtn color='unique' type='submit'>
              Submit Form
            </MDBBtn>
          </form>
        </SectionContainer>
      </MDBContainer>
    );
  }
}

export default fourthForm;
