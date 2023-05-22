import React, { useEffect, useState } from "react";

import { Button, Space, Table, Tag } from "antd";
import { Link, useLocation, useParams } from "react-router-dom";
import useFetch from "../../hook/useFetch";
const columns = [
  {
    title: "Name",
    dataIndex: "name",
    key: "id",
    render: (text, record) => (
    <Link to={`/survey/${record.id}`}>{text}</Link>
    ),
  },
];

const path = "api/v1/form/getForms/";

export const FormList = () => {
  const params = useParams();
  const { data, error, isPending } = useFetch(path + params.id);
  if (isPending) return <h1>Loading</h1>;
  return (
    <>
      <Table columns={columns} dataSource={data} rowKey="id" />
      <Button href={`/create-form/${params.id}`}>Create form</Button>
    </>
  );
};
