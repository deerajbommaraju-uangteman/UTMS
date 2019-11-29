import React, { Component } from 'react';
import axios from 'axios';
import { MDBContainer, MDBRow, MDBCol, MDBBtn } from 'mdbreact';
import DocsLink from './../components/docsLink';
import SectionContainer from './../components/sectionContainer';

var urlParams = new URLSearchParams(window.location.search);
class seventhForm extends Component {
  state = {
    Family1Name: '',
    Family1PhoneNumber:'',
    Family1Address:'',
    ProvinceOf1Family:'',
    CityOf1Family:'',
    DistrictOf1Family:'',
    VillageOf1Family:'',
    ZipCodeOf1Family:'',
    ID : parseInt(urlParams.get('ID')),
    FormID : 'form7'

    
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
        window.location.assign("http://10.15.15.65:3000/form8?ID="+response.data);
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
                  Family Name
                </label>
                <input
                  value={this.state.Family1Name}
                  name='Family1Name'
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
                  value={this.state.Family1PhoneNumber}
                  name='Family1PhoneNumber'
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
                  value={this.state.Family1Address}
                  name='Family1Address'
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
                <select onChange={this.changeHandler} name='ProvinceOf1Family' value={this.state.ProvinceOf1Family} className='custom-select bMDBRowser-default' required>
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
                <select onChange={this.changeHandler} name='CityOf1Family' value={this.state.CityOf1Family} className='custom-select bMDBRowser-default' required>
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
                <select onChange={this.changeHandler} name='DistrictOf1Family' value={this.state.DistrictOf1Family} className='custom-select bMDBRowser-default' required>
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
                <select onChange={this.changeHandler} name='VillageOf1Family' value={this.state.VillageOf1Family} className='custom-select bMDBRowser-default' required>
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
                <select onChange={this.changeHandler} name='ZipCodeOf1Family' value={this.state.ZipCodeOf1Family} className='custom-select bMDBRowser-default' required>
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
            
            
            <MDBBtn color='unique' type='submit'>
              Submit Form
            </MDBBtn>
          </form>
        </SectionContainer>
      </MDBContainer>
    );
  }
}

export default seventhForm;
