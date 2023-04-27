import { Table } from "antd";
import { Link } from "react-router-dom";


const ProjectList = ({ projects }) => {
  console.log(projects);

  const columns = [
    {
      title: 'Id',
      dataIndex: 'id',
      key: 'id',
    },
    {
      title: 'Name',
      dataIndex: 'name',
      key: 'name',
      render: (text, record) => <Link to={`/project/${record.id}`}>{text}</Link>,
    }
  ];
  return (
    <div>
      <h1>Projects</h1>
      <Table dataSource={projects} columns={columns} rowKey="id" />;
    </div>
  );
}

export default ProjectList;
