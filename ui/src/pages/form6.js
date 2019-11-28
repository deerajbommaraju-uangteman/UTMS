import React, { Component } from 'react';
import axios from 'axios';
import { MDBContainer, MDBRow, MDBCol, MDBBtn } from 'mdbreact';
import DocsLink from './../components/docsLink';
import SectionContainer from './../components/sectionContainer';

var urlParams = new URLSearchParams(window.location.search);
class sixthForm extends Component {
  state = {
    TelephoneNumber: '',
    Address:'',
    MrtwID:'',
    Province:'',
    CityOfResidence:'',
    DistrictOfResidence:'',
    VillageOfResidence:'',
    ZipCodeOfResidence:'',
    HouseStatus:'',
    ID : parseInt(urlParams.get('ID')),
    FormID : 'form6'

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
        window.location.assign("http://10.15.15.65:3000/form7?ID="+response.data);
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
                  Phone Number
                </label>
                <input
                  value={this.state.TelephoneNumber}
                  name='TelephoneNumber'
                  onChange={this.changeHandler}
                  type='text'
                  id='defaultFormRegisterNameEx'
                  className='form-control'
                  placeholder='phone Number'
                  required
                />
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>
              <MDBCol md='4' className='mb-3'>
                <label
                  htmlFor='defaultFormRegisterName'
                  className='grey-text'
                >
                  Address
                </label>
                <input
                  value={this.state.Address}
                  name='Address'
                  onChange={this.changeHandler}
                  type='text'
                  id='defaultFormRegisterName'
                  className='form-control'
                  placeholder='Address'
                  required
                />
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>
              <MDBCol md='4' className='mb-3'>
                <label
                  htmlFor='defaultFormRegisterName'
                  className='grey-text'
                >
                  RT/RW
                </label>
                <input
                  value={this.state.MrtwID}
                  name='MrtwID'
                  onChange={this.changeHandler}
                  type='text'
                  id='defaultFormRegisterName'
                  className='form-control'
                  placeholder='RT/RW'
                  required
                />
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>
           
              <MDBCol md='4' className='mb-3'>
              <label
                  className='grey-text'
                >
                  province Of Residence
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='Province' value={this.state.Province} className='custom-select bMDBRowser-default' required>
                  <option value=''>Choose Province Of Residence</option>
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
                 City Of Residence
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='CityOfResidence' value={this.state.CityOfResidence} className='custom-select bMDBRowser-default' required>
                  <option value=''>Choose City Of Residence</option>
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
                 District Of Residence
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='DistrictOfResidence' value={this.state.DistrictOfResidence} className='custom-select bMDBRowser-default' required>
                  <option value=''>Choose District Of Residence</option>
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
                 Village Of Residence
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='VillageOfResidence' value={this.state.VillageOfResidence} className='custom-select bMDBRowser-default' required>
                  <option value=''>Choose Village Of Residence</option>
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
                 ZipCode Of Residence
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='ZipCodeOfResidence' value={this.state.ZipCodeOfResidence} className='custom-select bMDBRowser-default' required>
                  <option value=''>Choose ZipCode Of Residence</option>
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
                 House Status
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='HouseStatus' value={this.state.HouseStatus} className='custom-select bMDBRowser-default' required>
                  <option value=''>House Status</option>
                  <option value='own'>own</option>
                  <option value='rent'>rent</option>
                  <option value='parents'>parents</option> 
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

export default sixthForm;
