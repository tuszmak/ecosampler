import { PlusCircleOutlined } from "@ant-design/icons";
import { Table, FloatButton } from "antd";
import { useState } from "react";
import { Link } from "react-router-dom";
import NewProjectDrawer from "./NewProjectDrawer";
import useAuth from "../../hook/useAuth";

const ProjectList = ({ projects }) => {
  const [projectList, setProjectList] = useState(projects);
  const { auth } = useAuth();

  const addNewProject = (newProject) => {
    setProjectList([newProject, ...projectList]);
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

  console.log(auth);

  return (
    <div>
      <h1>Projects</h1>
      <Table dataSource={projectList} columns={columns} rowKey="id" />;
      {auth?.role === "DIRECTOR" && (
        <AddNewProject addNewProject={addNewProject} />
      )}
    </div>
  );
};

const AddNewProject = ({ addNewProject }) => {
  const [open, setOpen] = useState(false);

  const showDrawer = () => {
    setOpen(true);
  };

  const onClose = () => {
    setOpen(false);
  };

  return (
    <>
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
    </>
  );
};

export default ProjectList;
