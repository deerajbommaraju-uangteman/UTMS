import React, { Component } from 'react';
import axios from 'axios';
import { MDBContainer, MDBRow, MDBCol, MDBBtn } from 'mdbreact';
import DocsLink from './../components/docsLink';
import SectionContainer from './../components/sectionContainer';

var urlParams = new URLSearchParams(window.location.search);
class ninthForm extends Component {
  state = {
    EmployerName: '',
    EmployerTelephone:'',
    EmployerAddress:'',
    EmployerProvince:'',
    EmployerCity:'',
    EmployerDistrict:'',
    EmployerVillage:'',
    EmployerPostalCode:'',
    ID : parseInt(urlParams.get('ID')),
    FormID : 'form9'

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
        window.location.assign("http://10.15.15.65:3000/dvHome");
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
                  Office Name
                </label>
                <input
                  value={this.state.OfficeName}
                  name='OfficeName'
                  onChange={this.changeHandler}
                  type='text'
                  id='defaultFormRegisterNameEx'
                  className='form-control'
                  placeholder='Office Name'
                  required
                />
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>
              <MDBCol md='4' className='mb-3'>
                <label
                  htmlFor='defaultFormRegisterName'
                  className='grey-text'
                >
                  Office Telephone Number
                </label>
                <input
                  value={this.state.EmployerTelephone}
                  name='EmployerTelephone'
                  onChange={this.changeHandler}
                  type='text'
                  id='defaultFormRegisterName'
                  className='form-control'
                  placeholder='Office Telephone Number'
                  required
                />
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>
              <MDBCol md='4' className='mb-3'>
                <label
                  htmlFor='defaultFormRegisterName'
                  className='grey-text'
                >
                  Office Address
                </label>
                <input
                  value={this.state.EmployerAddress}
                  name='EmployerAddress'
                  onChange={this.changeHandler}
                  type='text'
                  id='defaultFormRegisterName'
                  className='form-control'
                  placeholder='Office Address'
                  required
                />
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>
            
              <MDBCol md='4' className='mb-3'>
              <label
                  className='grey-text'
                >
                  province Of Office
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='EmployerProvince' value={this.state.EmployerProvince} className='custom-select bMDBRowser-default' required>
                  <option value=''>Choose Province Of Office</option>
                  <option value='jakarata'>Jakata</option>
                  <option value='bali'>Bali</option>
                  <option value='java'>Java</option>
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
                 City Of Office
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='EmployerCity' value={this.state.EmployerCity} className='custom-select bMDBRowser-default' required>
                  <option value=''>Choose City Of Office</option>
                  <option value='bali'>Bali</option>
                  <option value='jakrta'>Jakarta</option>
                  <option value='hyderabad'>Hyderabad</option>
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
                 District Of Office
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='EmployerDistrict' value={this.state.EmployerDistrict} className='custom-select bMDBRowser-default' required>
                  <option value=''>Choose District Of Office</option>
                  <option value='k'>karimnagar</option>
                  <option value='r'>Ranga Reddy</option>
                  <option value='hyderabad'>Hyderabad</option>
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
                 Village Of Office
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='EmployerVillage' value={this.state.EmployerVillage} className='custom-select bMDBRowser-default' required>
                  <option value=''>Choose Village Of Office</option>
                  <option value='hyderabad'>Hyderabad</option>
                  <option value='k'>karimnagar</option>
                  <option value='r'>Ranga Reddy</option> 
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
                 ZipCode Of Office
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='EmployerPostalCode' value={this.state.EmployerPostalCode} className='custom-select bMDBRowser-default' required>
                  <option value=''>Choose ZipCode Of Office</option>
                  <option value='01'>Hyderabad</option>
                  <option value='02'>karimnagar</option>
                  <option value='03'>Ranga Reddy</option> 
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

export default ninthForm;
