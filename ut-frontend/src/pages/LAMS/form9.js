import React, { Component } from 'react';
import { MDBContainer, MDBRow, MDBCol, MDBBtn } from 'mdbreact';
import DocsLink from '../../components/docsLink';
import SectionContainer from '../../components/sectionContainer';

class ninthForm extends Component {
  state = {
    officeName: '',
    officeTelephoneNumber:'',
    officeAddress:'',
    officeProvince:'',
    cityOfOffice:'',
    districtOfOffice:'',
    villageOfOffice:'',
    zipCodeOfOffice:'',
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
                  Office Name
                </label>
                <input
                  value={this.state.officeName}
                  name='officeName'
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
                  value={this.state.officeTelephoneNumber}
                  name='officeTelephoneNumber'
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
                  value={this.state.officeAddress}
                  name='officeAddress'
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
                <select onChange={this.changeHandler} name='officeProvince' value={this.state.officeProvince} className='custom-select bMDBRowser-default' required>
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
                <select onChange={this.changeHandler} name='cityOfOffice' value={this.state.cityOfOffice} className='custom-select bMDBRowser-default' required>
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
                <select onChange={this.changeHandler} name='districtOfOffice' value={this.state.districtOfOffice} className='custom-select bMDBRowser-default' required>
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
                <select onChange={this.changeHandler} name='villageOfOffice' value={this.state.villageOfOffice} className='custom-select bMDBRowser-default' required>
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
                <select onChange={this.changeHandler} name='zipCodeOfOffice' value={this.state.zipCodeOfOffice} className='custom-select bMDBRowser-default' required>
                  <option value=''>Choose ZipCode Of Office</option>
                  <option value='hyderabad'>Hyderabad</option>
                  <option value='k'>karimnagar</option>
                  <option value='r'>Ranga Reddy</option> 
                </select>
                <div className='invalid-feedback'>
                  Select one
                </div>
              </div>
              </MDBCol>
            
            <MDBBtn color='unique' type='submit' href="form10">
              Submit Form
            </MDBBtn>
          </form>
        </SectionContainer>
      </MDBContainer>
    );
  }
}

export default ninthForm;
