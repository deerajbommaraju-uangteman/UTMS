import React, { Component } from 'react';
import { MDBContainer,  MDBCol, MDBBtn } from 'mdbreact';
import DocsLink from '../../components/docsLink';
import SectionContainer from '../../components/sectionContainer';

class secondForm extends Component {
  state = {
    reason:'',
  };

  submitHandler = event => {
    console.log({...this.state})
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
              <div className='custom-control custom-radio'>
                <input
                 checked={this.state.reason === "marriedCost"}
                 value="marriedCost"
                 onChange={this.changeHandler}
                  type='radio'
                  className='custom-control-input'
                  id='customControlValidation2'
                  name='reason'
                  required
                />
                <label
                  className='custom-control-label'
                  htmlFor='customControlValidation2'
                >
                  Married Cost
                </label>
              </div>
              <div className='custom-control custom-radio'>
                <input
                 checked={this.state.reason === "holiday"}
                 value="holiday"
                 onChange={this.changeHandler}
                  type='radio'
                  className='custom-control-input'
                  id='customControlValidation3'
                  name='reason'
                  required
                />
                <label
                  className='custom-control-label'
                  htmlFor='customControlValidation3'
                >
                  Holiday
                </label>
              </div>
              <div className='custom-control custom-radio'>
                <input
                 checked={this.state.reason === "payingDebts"}
                 value="payingDebts"
                 onChange={this.changeHandler}
                  type='radio'
                  className='custom-control-input'
                  id='customControlValidation5'
                  name='reason'
                  required
                />
                <label
                  className='custom-control-label'
                  htmlFor='customControlValidation5'
                >
                  Paying Debts
                </label>
              </div>
              
              <div className='custom-control custom-radio mb-3'>
                <input
                checked={this.state.reason === "ETC"}
                value="ETC"
                 onChange={this.changeHandler}
                  type='radio'
                  className='custom-control-input'
                  id='customControlValidation4'
                  name='reason'
                  required
                />
                <label
                  className='custom-control-label'
                  htmlFor='customControlValidation4'
                >
                  ETC
                </label>
                <div className='invalid-feedback'>
                  Select any radio button
                </div>
              </div>
              
              </MDBCol>
              
            
            <MDBBtn color='unique' type='submit' href="/form3">
              Submit Form
            </MDBBtn>
          </form>
        </SectionContainer>
      </MDBContainer>
    );
  }
}

export default secondForm;
