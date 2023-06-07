import { FloatButton, Table } from "antd";
import { Link, useNavigate, useParams } from "react-router-dom";
import { PlusCircleOutlined } from "@ant-design/icons";

import useDownFetch from "../../hook/useDownFetch";
import useAuth from "../../hook/useAuth";


const path = "api/v1/form/by-project-id/";

export const FormList = () => {
  const { id } = useParams();
  const columns = [
    {
      title: "Name",
      dataIndex: "name",
      key: "id",
      render: (text, record) => (
        <Link to={`/survey/${record.id}?from=${id}`}>{text}</Link>
      ),
    },
  ];

  const { auth } = useAuth();

  const navigate = useNavigate();
  const { data, error, isPending } = useDownFetch(path + id);
  if (isPending) return <h1>Loading</h1>;
  return (
    <>
      <Table columns={columns} dataSource={data} rowKey="id" />
      {(auth?.role === "DIRECTOR" || auth?.role === "PROJECT_LEADER") &&
        <FloatButton
          onClick={() => { navigate(`/create-form/${id}`) }}
          shape="circle"
          type="primary"
          style={{
            right: 94,
          }}
          icon={<PlusCircleOutlined />}
        />}
    </>
  );
};
