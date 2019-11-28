import React, { Component } from 'react';
import { MDBContainer, MDBCol, MDBBtn } from 'mdbreact';
import DocsLink from '../../components/docsLink';
import SectionContainer from '../../components/sectionContainer';

class seventhForm extends Component {
  state = {
    familyName: '',
    familyPhoneNumber:'',
    familyAddress:'',
    provinceOfFamily:'',
    cityOfFamily:'',
    districtOfFamily:'',
    villageOfFamily:'',
    zipCodeOfFamily:'',
    
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
                  Family Name
                </label>
                <input
                  value={this.state.familyName}
                  name='familyName'
                  onChange={this.changeHandler}
                  type='text'
                  id='defaultFormRegisterNameEx'
                  className='form-control'
                  placeholder='Family Name'
                  required
                />
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>
              <MDBCol md='4' className='mb-3'>
                <label
                  htmlFor='defaultFormRegisterName'
                  className='grey-text'
                >
                  Family Phone Number
                </label>
                <input
                  value={this.state.familyPhoneNumber}
                  name='familyPhoneNumber'
                  onChange={this.changeHandler}
                  type='text'
                  id='defaultFormRegisterName'
                  className='form-control'
                  placeholder='Family Phone Number'
                  required
                />
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>
              <MDBCol md='4' className='mb-3'>
                <label
                  htmlFor='defaultFormRegisterName'
                  className='grey-text'
                >
                  Family Address
                </label>
                <input
                  value={this.state.familyAddress}
                  name='familyAddress'
                  onChange={this.changeHandler}
                  type='text'
                  id='defaultFormRegisterName'
                  className='form-control'
                  placeholder='Family Address'
                  required
                />
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>
            
            
              <MDBCol md='4' className='mb-3'>
              <label
                  className='grey-text'
                >
                  province Of Family
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='provinceOfFamily' value={this.state.provinceOfFamily} className='custom-select bMDBRowser-default' required>
                  <option value=''>Choose Province Of Family</option>
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
                 City Of Family
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='cityOfFamily' value={this.state.cityOfFamily} className='custom-select bMDBRowser-default' required>
                  <option value=''>Choose City Of Family</option>
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
                 District Of Family
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='districtOfFamily' value={this.state.districtOfFamily} className='custom-select bMDBRowser-default' required>
                  <option value=''>Choose District Of Family</option>
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
                 Village Of Family
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='villageOfFamily' value={this.state.villageOfFamily} className='custom-select bMDBRowser-default' required>
                  <option value=''>Choose Village Of Family</option>
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
                 ZipCode Of Family
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='zipCodeOfFamily' value={this.state.zipCodeOfFamily} className='custom-select bMDBRowser-default' required>
                  <option value=''>Choose ZipCode Of Family</option>
                  <option value='hyderabad'>Hyderabad</option>
                  <option value='k'>karimnagar</option>
                  <option value='r'>Ranga Reddy</option> 
                </select>
                <div className='invalid-feedback'>
                  Select one
                </div>
              </div>
              </MDBCol>
            
            
            <MDBBtn color='unique' type='submit' href="/form8">
              Submit Form
            </MDBBtn>
          </form>
        </SectionContainer>
      </MDBContainer>
    );
  }
}

export default seventhForm;
