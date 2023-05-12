import React, { useEffect, useState } from "react";
import { PlusCircleOutlined } from "@ant-design/icons";
import { Button, FloatButton, Space, Table, Tag } from "antd";
import { Link, useLocation, useParams } from "react-router-dom";

import useFetch from "../../hook/useFetch";
import NewProjectDrawer from "../projects/NewProjectDrawer";
import useAuth from "../../hook/useAuth";
import NewQuestionDrawer from "./NewQuestionDrawer";
const columns = [
  {
    title: "Name",
    dataIndex: "name",
    key: "id",
    render: (text, record) => (
      <Link to={"/form/" + record.id}>{record.description}</Link>
    ),
  },
  {
    title: "Field type",
    dataIndex: "fieldType",
    key: "id",
    render: (text, record) => <p>{record.fieldStyle}</p>,
  },
];
const path = "api/v1/form/getQuestions/";

export const Form = () => {
  const params = useParams();
  const { auth } = useAuth();

  const { data, error, isPending } = useFetch(path + params.id);
  if (isPending) return <h1>Loading</h1>;
  return (
    <>
      <Table columns={columns} dataSource={data} rowKey="id" />
      {auth?.role === "DIRECTOR" && (
        <AddNewProject addNewProject={addNewProject} />
      )}
    </>
  );
};
const addNewProject = (newProject) => {
    // setProjectList([newProject, ...projectList]);
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
      <NewQuestionDrawer
        onClose={onClose}
        open={open}
        addNewProject={addNewProject}
      />
    </>
  );
};
