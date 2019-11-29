import React, { Component } from 'react';
import { MDBContainer, MDBCol, MDBBtn } from 'mdbreact';
import DocsLink from '../../components/docsLink';
import SectionContainer from '../../components/sectionContainer';

class sixthForm extends Component {
  state = {
    phoneNumber: '',
    Address:'',
    RtRw:'',
    provinceOfRecidence:'',
    cityOfRecidence:'',
    districtOfResidence:'',
    villageOfRecidence:'',
    zipCodeOfRecidence:'',
    houseStatus:'',
  };

  submitHandler = event => {
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
                  value={this.state.phoneNumber}
                  name='phoneNumber'
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
                  value={this.state.RtRw}
                  name='RtRw'
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
                  province Of Recidence
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='provinceOfRecidence' value={this.state.provinceOfRecidence} className='custom-select bMDBRowser-default' required>
                  <option value=''>Choose Province Of Recidence</option>
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
                 City Of Recidence
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='cityOfRecidence' value={this.state.cityOfRecidence} className='custom-select bMDBRowser-default' required>
                  <option value=''>Choose City Of Recidence</option>
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
                 District Of Recidence
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='districtOfResidence' value={this.state.districtOfResidence} className='custom-select bMDBRowser-default' required>
                  <option value=''>Choose District Of Recidence</option>
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
                 Village Of Recidence
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='villageOfRecidence' value={this.state.villageOfRecidence} className='custom-select bMDBRowser-default' required>
                  <option value=''>Choose Village Of Recidence</option>
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
                 ZipCode Of Recidence
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='zipCodeOfRecidence' value={this.state.zipCodeOfRecidence} className='custom-select bMDBRowser-default' required>
                  <option value=''>Choose ZipCode Of Recidence</option>
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
                <select onChange={this.changeHandler} name='houseStatus' value={this.state.houseStatus} className='custom-select bMDBRowser-default' required>
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
            
            <MDBBtn color='unique' type='submit' href="/form7">
              Submit Form
            </MDBBtn>
          </form>
        </SectionContainer>
      </MDBContainer>
    );
  }
}

export default sixthForm;
