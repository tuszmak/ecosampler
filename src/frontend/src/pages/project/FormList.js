import { Button, Space, Table, Tag } from "antd";
import { Link, useLocation, useParams } from "react-router-dom";
import useFetch from "../../hook/useFetch";
import { useLocation, useParams } from "react-router-dom";
import useDownFetch from "../../hook/useDownFetch";

const columns = [
  {
    title: "Name",
    dataIndex: "name",
    key: "id",
    render: (text, record) => <Link to={"/form/" + record.id}>{text}</Link>,
  },
];
const path = "api/v1/form/by-project-id/";

export const FormList = () => {
  const params = useParams();
  const { data, error, isPending } = useDownFetch(path + params.id);
  if (isPending) return <h1>Loading</h1>;
  return (
    <>
      <Table columns={columns} dataSource={data} rowKey="id" />
      <Button href={`/create-form/${params.id}`}>Create form</Button>
    </>
  );
};
