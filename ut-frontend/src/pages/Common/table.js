
import React from 'react';
import 'antd/dist/antd.css';
import '../index.css';
import { Table } from 'antd';

class table extends React.Component {

render(){
  var datbutton = "Delete";
  var button1 = <span>
    <button>{datbutton}</button>
  </span>
  // const columns1 = [    {
  //   title: 'Name',
  //   key: 'name',
  //   type: 'text'
  // }];
  // const row1 = [];
  // for(i=0;i<columns.length;i++){

  // }
  const columns = [
    {
      "title": 'Name',
      "dataIndex": 'name',
      "key": 'name',
    },
    {
      title: 'Age',
      dataIndex: 'age',
      key: 'age',
    },
    {
      title: 'Address',
      dataIndex: 'address',
      key: 'address',
    },
    
    {
      title: 'Action',
      key: 'action',
      render: (text, record) => (
       button1
      ),
    },
  ];
  
  const data = [
    {
      "name" : 'John Brown',
      age: 32,
      key: '1',
      address: 'New York No. 1 Lake Park',
      
    },
    {
      key: '2',
      "name": 'Jim Green',
      address: 'London No. 1 Lake Park',
      age: 42,
     
      
    },
    {
      key: '3',
      age: 32,
      "name": 'Joe Black',
      address: 'Sidney No. 1 Lake Park',
     
    },
  ];
return (
<Table columns={columns} dataSource={data} />
);
} }         
export default table;