import React, { Component } from 'react';
import 'antd/dist/antd.css';
import { Layout, Icon, Table, Input, Button } from 'antd';
import Highlighter from 'react-highlight-words';
import SiderDemo from './dashboard1';

const { Content, Footer } = Layout;

const data = [
	{
		key: '1',
		name: 'John Brow',
		age: 32,
		address: 'New York No. 1 Lake Park'
	},
	{
		key: '2',
		name: 'Joe Black',
		age: 42,
		address: 'London No. 1 Lake Park'
	},
	{
		key: '3',
		name: 'Jim Green',
		age: 32,
		address: 'Sidney No. 1 Lake Park'
	},
	{
		key: '4',
		name: 'Jim Red',
		age: 32,
		address: 'London No. 2 Lake Park'
	}
];
class DbTable extends Component {
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
				width: '30%',
				...this.getColumnSearchProps('name')
			},
			{
				title: 'Age',
				dataIndex: 'age',
				key: 'age',
				width: '20%',
				...this.getColumnSearchProps('age')
			},
			{
				title: 'Address',
				dataIndex: 'address',
				key: 'address',
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

				<SiderDemo style={{ position: 'absolute' }} />

				<Layout>
					{/* <Header style={{ background: "#fff", padding: 0 }} /> */}
					<Content style={{ margin: '16px 16px', backgroundColor: '#fff' }}>
						<div style={{ padding: 24, background: '#fff', minHeight: 360 }}>
							<Table columns={columns} dataSource={data} />;
						</div>
					</Content>
					<Footer style={{ textAlign: 'center' }}>Ant Design ©2018 Created by Ant UED</Footer>
				</Layout>
			</div>
		);
	}
}

export default DbTable;
