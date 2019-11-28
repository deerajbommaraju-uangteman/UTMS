import React, { Component } from 'react';
import { MDBContainer,  MDBCol, MDBBtn } from 'mdbreact';
import DocsLink from '../../components/docsLink';
import SectionContainer from '../../components/sectionContainer';

class tenthForm extends Component {
  state = {
    earningsPerMonth: '',
    expenditurePerMonth:'',
    kprInstallments:'',
    typeOfWork:'',
    industrySector:'',
    byYear:'',
    byMonth:'',
    position:'',
    dependents:'',
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
                  className='grey-text'
                >
                  Type of Work
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='typeOfWork' value={this.state.typeOfWork} className='custom-select bMDBRowser-default' required>
                  <option value=''>Choose Type of Work</option>
                  <option value='A'>A</option>
                  <option value='B'>B</option>
                  <option value='C'>C</option>
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
                 Industry Sector
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='industrySector' value={this.state.industrySector} className='custom-select bMDBRowser-default' required>
                  <option value=''>Choose Industry Sector</option>
                  <option value='software'>Software</option>
                  <option value='civil'>Civil</option>
                  <option value='mechanical'>Mechancial</option>
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
                 Length of Work
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='byYear' value={this.state.byYear} className='custom-select bMDBRowser-default' required>
                  <option value=''>By Year</option>
                  <option value='1'>1</option>
                  <option value='2'>2</option>
                  <option value='3'>3</option>
                </select>
                <select onChange={this.changeHandler} name='byMonth' value={this.state.byMonth} className='custom-select bMDBRowser-default' required>
                  <option value=''>By Months</option>
                  <option value='1'>1</option>
                  <option value='2'>2</option>
                  <option value='3'>3</option>
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
                position
                </label>
              <div className='form-group'>
                <select onChange={this.changeHandler} name='position' value={this.state.position} className='custom-select bMDBRowser-default' required>
                  <option value=''>Choose position</option>
                  <option value='junior'>junior</option>
                  <option value='senior'>senior</option>
                  <option value='cheif'>Chief</option> 
                </select>
                <div className='invalid-feedback'>
                  Select one
                </div>
              </div>
              </MDBCol>
            
           
              <MDBCol md='4' className='mb-3'>
                <label
                  htmlFor='defaultFormRegisterNameEx'
                  className='grey-text'
                >
                  Earnings Per Month
                </label>
                <input
                  value={this.state.earningsPerMonth}
                  name='earningsPerMonth'
                  onChange={this.changeHandler}
                  type='text'
                  id='defaultFormRegisterNameEx'
                  className='form-control'
                  placeholder='Earnings Per Month'
                  required
                />
                <div className='invalid-feedback'>please enter valid number!</div>
              </MDBCol>
              <MDBCol md='4' className='mb-3'>
                <label
                  htmlFor='defaultFormRegisterName'
                  className='grey-text'
                >
                  Expenditure Per Month
                </label>
                <input
                  value={this.state.expenditurePerMonth}
                  name='expenditurePerMonth'
                  onChange={this.changeHandler}
                  type='text'
                  id='defaultFormRegisterName'
                  className='form-control'
                  placeholder='Expenditure Per Month'
                  required
                />
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>
              <MDBCol md='4' className='mb-3'>
                <label
                  htmlFor='defaultFormRegisterName'
                  className='grey-text'
                >
                  KPR Installments
                </label>
                <input
                  value={this.state.kprInstallments}
                  name='kprInstallments'
                  onChange={this.changeHandler}
                  type='text'
                  id='defaultFormRegisterName'
                  className='form-control'
                  placeholder='KPR Installments'
                  required
                />
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>
              <MDBCol md='4' className='mb-3'>
                <label
                  htmlFor='defaultFormRegisterName'
                  className='grey-text'
                >
                  how much are your Dependents
                </label>
                <input
                  value={this.state.dependents}
                  name='dependents'
                  onChange={this.changeHandler}
                  type='text'
                  id='defaultFormRegisterName'
                  className='form-control'
                  placeholder='no of Dependents'
                  required
                />
                <div className='valid-feedback'>Looks good!</div>
              </MDBCol>
        
           
            
            <MDBBtn color='unique' type='submit' href="/form11">
              Submit Form
            </MDBBtn>
          </form>
        </SectionContainer>
      </MDBContainer>
    );
  }
}

export default tenthForm;
