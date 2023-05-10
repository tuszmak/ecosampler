import { PlusCircleOutlined } from "@ant-design/icons";
import { Table, FloatButton } from "antd";
import { useState } from "react";
import { Link } from "react-router-dom";
import NewProjectDrawer from "./NewProjectDrawer";

const ProjectList = ({ projects }) => {
  const [open, setOpen] = useState(false);
  const [projectList, setProjectList] = useState(projects);

  const addNewProject = (newProject) => {
    setProjectList([newProject, ...projectList]);
  };

  const showDrawer = () => {
    setOpen(true);
  };

  const onClose = () => {
    setOpen(false);
  };

  const columns = [
    {
      title: "Name",
      dataIndex: "name",
      key: "name",
      render: (text, record) => (
        <Link to={`/project/${record.id}`}>{text}</Link>
      ),
    },
    {
      title: "Description",
      dataIndex: "description",
      key: "description",
    },
  ];

  return (
    <div>
      <h1>Projects</h1>
      <Table dataSource={projectList} columns={columns} rowKey="id" />;
      <FloatButton
        onClick={showDrawer}
        shape="circle"
        type="primary"
        style={{
          right: 94,
        }}
        icon={<PlusCircleOutlined />}
      />
      <NewProjectDrawer
        onClose={onClose}
        open={open}
        addNewProject={addNewProject}
      />
    </div>
  );
};

export default ProjectList;
