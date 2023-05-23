import { Button, FloatButton, Space, Table, Tag } from "antd";
import { Link, useLocation, useNavigate, useParams } from "react-router-dom";
import { PlusCircleOutlined } from "@ant-design/icons";

import useDownFetch from "../../hook/useDownFetch";
import useAuth from "../../hook/useAuth";

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
  const { auth } = useAuth();
  const {id} = useParams();
  const navigate = useNavigate();
  const { data, error, isPending } = useDownFetch(path + id);
  if (isPending) return <h1>Loading</h1>;
  return (
    <>
      <Table columns={columns} dataSource={data} rowKey="id" />
      {(auth?.role === "DIRECTOR" || auth?.role === "PROJECT_LEADER") && 
      <FloatButton
        onClick={()=>{navigate(`/create-form/${id}`)}}  
        shape="circle"
        type="primary"
        style={{
          right: 94,
        }}
        icon={<PlusCircleOutlined />}
      />  }
    </>
  );
};
