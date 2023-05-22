import { PlusCircleOutlined, UserSwitchOutlined } from "@ant-design/icons";
import { Table, FloatButton, Button } from "antd";
import { useState } from "react";
import { Link } from "react-router-dom";
import NewProjectDrawer from "./NewProjectDrawer";
import useAuth from "../../hook/useAuth";
import ModifyUserAssigns from "./ModifyUserAssigns";

const ProjectList = ({ projects }) => {
  const [projectList, setProjectList] = useState(projects);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [projectId, setProjectId] = useState(null);
  const { auth } = useAuth();

  const showModal = (id) => {
    setIsModalOpen(true);
    setProjectId(id);
  };
  const handleOk = (addUserIDs, removeUserIDs) => {
    setIsModalOpen(false);
  };
  const handleCancel = () => {
    setIsModalOpen(false);
  };

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
    {
      title: "Name",
      key: "name",
      render: (text, record) => (
        <Button
          shape="circle"
          type="primary"
          onClick={() => showModal(record.id)}
          icon={<UserSwitchOutlined />}
        />
      ),
    },
  ];

  return (
    <div>
      <h1>Projects</h1>
      <Table dataSource={projectList} columns={columns} rowKey="id" />

      {auth?.role === "DIRECTOR" && (
        <AddNewProject addNewProject={addNewProject} />
      )}

      {isModalOpen && (
        <ModifyUserAssigns
          projectId={projectId}
          isModalOpen={isModalOpen}
          handleCancel={handleCancel}
          handleOk={handleOk}
        />
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
