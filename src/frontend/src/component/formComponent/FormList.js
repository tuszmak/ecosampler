import React, { useEffect, useState } from "react";

import { Button, Space, Table, Tag } from "antd";
import { useLocation, useParams } from "react-router-dom";
import useFetch from "../../hook/useFetch";
const columns = [
  {
    title: "Name",
    dataIndex: "name",
    key: "id",
    render: (text) => <a>{text}</a>,
  },
  
];


export const FormList = () => {
  const params = useParams();
  console.log(params.id);
  const { data, error, isPending } = useFetch(
    `api/v1/form/getForms/${params.id}`
  );
  
  console.log(data);
  if (isPending) return <h1>Loading</h1>;

  return (
    <>
      <Table columns={columns} dataSource={data} rowKey="id"/>
      <Button href={`/create-form/${params.id}`}>Create form</Button>
    </>
  );
};
