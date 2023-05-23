import React, { useEffect, useState } from "react";
import { PlusCircleOutlined } from "@ant-design/icons";
import { Button, FloatButton, Space, Table, Tag } from "antd";
import { Link, useLocation, useParams } from "react-router-dom";

import NewProjectDrawer from "../projects/NewProjectDrawer";
import useDownFetch from "../../hook/useDownFetch";
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
const path = "api/v1/form/by-form-id/";

export const Form = () => {
  const {id} = useParams();
  const { data, error, isPending } = useDownFetch(path + id);
  if (isPending) return <h1>Loading</h1>;
  return (
    <>
      <Table columns={columns} dataSource={data} rowKey="id" />
        <AddNewForm />
    </>
  );
};

const AddNewForm = () => {
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
    </>
  );
};
