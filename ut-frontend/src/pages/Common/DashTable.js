import React, { Component } from 'react';
import 'antd/dist/antd.css';
import { Layout, Icon, Table, Input, Button } from 'antd';
import Highlighter from 'react-highlight-words';
import SiderDemo from './dashboard1';

const { Content, Footer } = Layout;

const data = [
	{
		key: '1',
		name: 'John Brown',
		age: 32,
		dob: '3/5/1995',
		address: 'New York No. 1 Lake Park'
	},
	{
		key: '2',
		name: 'Joe Black',
		age: 42,
		dob: '4/5/1995',
		address: 'London No. 1 Lake Park'
	},
	{
		key: '3',
		name: 'Jim Green',
		age: 32,
		dob: 3 / 5 / 1995,
		address: 'Sidney No. 1 Lake Park'
	},
	{
		key: '4',
		name: 'Jim Red',
		dob: 3 / 5 / 1995,
		age: 32,
		address: 'London No. 2 Lake Park'
	},
	{
		key: '5',
		dob: 3 / 5 / 1995,
		name: 'John Brown',
		age: 32,
		address: 'New York No. 1 Lake Park'
	},
	{
		key: '6',
		dob: 3 / 5 / 1995,
		name: 'Joe Black',
		age: 42,
		address: 'London No. 1 Lake Park'
	},
	{
		key: '7',
		dob: 3 / 5 / 1995,
		name: 'Jim Green',
		age: 32,
		address: 'Sidney No. 1 Lake Park'
	},
	{
		key: '8',
		dob: 3 / 5 / 1995,
		name: 'Jim Red',
		age: 32,
		address: 'London No. 2 Lake Park'
	},
	{
		key: '9',
		dob: 3 / 5 / 1995,
		name: 'Jim Red',
		age: 32,
		address: 'London No. 2 Lake Park'
	},
	{
		key: '10',
		dob: 3 / 5 / 1995,
		name: 'Jim Red',
		age: 32,
		address: 'London No. 2 Lake Park'
	},
	{
		key: '11',
		dob: 3 / 5 / 1995,
		name: 'Jim Red',
		age: 32,
		address: 'London No. 2 Lake Park'
	}
];
class DashTable extends Component {
	constructor(props) {
		super(props);
		this.state = {
			searchText: ''
		};
	}
	getColumnSearchProps = (dataIndex) => ({
		filterDropdown: ({ setSelectedKeys, selectedKeys, confirm, clearFilters }) => (
			<div style={{ padding: 8 }}>
				<Input
					ref={(node) => {
						this.searchInput = node;
					}}
					placeholder={`Search ${dataIndex}`}
					value={selectedKeys[0]}
					onChange={(e) => setSelectedKeys(e.target.value ? [ e.target.value ] : [])}
					onPressEnter={() => this.handleSearch(selectedKeys, confirm)}
					style={{ width: 188, marginBottom: 8, display: 'block' }}
				/>
				<Button
					type="primary"
					onClick={() => this.handleSearch(selectedKeys, confirm)}
					icon="search"
					size="small"
					style={{ width: 90, marginRight: 8 }}
				>
					Search
				</Button>
				<Button onClick={() => this.handleReset(clearFilters)} size="small" style={{ width: 90 }}>
					Reset
				</Button>
			</div>
		),
		filterIcon: (filtered) => <Icon type="search" style={{ color: filtered ? '#1890ff' : undefined }} />,
		onFilter: (value, record) => record[dataIndex].toString().toLowerCase().includes(value.toLowerCase()),
		onFilterDropdownVisibleChange: (visible) => {
			if (visible) {
				setTimeout(() => this.searchInput.select());
			}
		},
		render: (text) => (
			<Highlighter
				highlightStyle={{ backgroundColor: '#ffc069', padding: 0 }}
				searchWords={[ this.state.searchText ]}
				autoEscape
				textToHighlight={text.toString()}
			/>
		)
	});

	handleSearch = (selectedKeys, confirm) => {
		confirm();
		this.setState({ searchText: selectedKeys[0] });
	};

	handleReset = (clearFilters) => {
		clearFilters();
		this.setState({ searchText: '' });
	};
	onCollapse = (collapsed) => {
		console.log(collapsed);
		this.setState({ collapsed });
	};

	render() {
		const columns = [
			{
				title: 'Name',
				dataIndex: 'name',
				key: 'name',
				width: '100',
				...this.getColumnSearchProps('name')
			},
			{
				title: 'Age',
				dataIndex: 'age',
				key: 'age',
				width: '100',
				...this.getColumnSearchProps('age')
			},
			{
				title: 'Dob',
				dataIndex: 'dob',
				key: 'dob',
				width: '100',
				...this.getColumnSearchProps('dob')
			},
			{
				title: 'Address',
				dataIndex: 'address',
				key: 'address',
				width: '100',
				...this.getColumnSearchProps('address')
			}
		];

		return (
			<div style={{ display: 'flex', flexDirection: 'row' }}>
				{/* <Header className="header">
          <Menu
            theme="dark"
            mode="horizontal"
            defaultSelectedKeys={["2"]}
            style={{ lineHeight: "64px" }}
          >
            <Menu.Item key="1">nav 1</Menu.Item>
            <Menu.Item key="2">nav 2</Menu.Item>
            <Menu.Item key="3">nav 3</Menu.Item>
          </Menu>
        </Header> */}

				<SiderDemo />
				<Layout>
					{/* <Header style={{ background: "#fff", padding: 0 }} /> */}
					<Content style={{ margin: '16px 16px', backgroundColor: '#fff' }}>
						<div style={{ padding: 24, background: '#fff', width: '1150px' }}>
							<Table columns={columns} dataSource={data} size="small" scroll={{ x: 1200, y: 300 }} />
						</div>
					</Content>
				</Layout>
			</div>
		);
	}
}

export default DashTable;
