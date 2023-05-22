import { Form, Modal } from "antd";
import AssignsPeople from "./AssignsPeople";
import { useEffect, useState } from "react";
import useDownFetch from "../../hook/useDownFetch";
import upFetch from "../../api/upFetch";

const ModifyUserAssigns = ({
  projectId,
  isModalOpen,
  handleCancel,
  handleOk,
}) => {
  const [form] = Form.useForm();
  const { data: projectUsers, isPending } = useDownFetch(
    `api/v1/project/users/${projectId}`
  );
  const [selectProjectLeader, setSelectProjectLeader] = useState({
    url: "api/v1/user/by-role?role=PROJECT_LEADER",
    name: "projectLeaderIDs",
    label: "Select Project Leader",
  });
  const [selectScientists, setSelectScientists] = useState({
    url: "api/v1/user/by-role?role=SCIENTIST",
    name: "scientistsIDs",
    label: "Select Scientists",
  });

  const generateUserChangesPayload = (values) => {
    const newList = [...values.projectLeaderIDs, ...values.scientistsIDs];
    const originalList = projectUsers.map((user) => user.id);
    const addUserIDs = newList.filter((id) => !originalList.includes(id));
    const removeUserIDs = originalList.filter((id) => !newList.includes(id));
    return { addUserIDs, removeUserIDs };
  };
  const sendUserChanges = async (addUserIDs, removeUserIDs) => {
    const url = `/api/v1/project/modify-users/${projectId}`;
    const options = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ addUserIDs, removeUserIDs }),
    };
    const response = await upFetch(url, options);
    console.log(response.ok);
  };

  useEffect(() => {
    if (projectUsers) {
      setSelectProjectLeader({
        ...selectProjectLeader,
        defaultValue: projectUsers
          .filter((user) => user.role === "PROJECT_LEADER")
          .map((user) => user.id),
      });

      setSelectScientists({
        ...selectScientists,
        defaultValue: projectUsers
          .filter((user) => user.role === "SCIENTIST")
          .map((user) => user.id),
      });
    }
  }, [projectUsers]);

  if (isPending) return <>Loading...</>;

  return (
    <>
      <Modal
        title="Assigns People"
        open={isModalOpen}
        onOk={() => {
          form
            .validateFields()
            .then((values) => {
              const { addUserIDs, removeUserIDs } =
                generateUserChangesPayload(values);
              sendUserChanges(addUserIDs, removeUserIDs);
              form.resetFields();
              handleOk();
            })
            .catch((info) => {
              console.log("Validate Failed:", info);
            });
        }}
        onCancel={handleCancel}
      >
        <Form layout={"vertical"} form={form}>
          <AssignsPeople {...selectProjectLeader} />
          <AssignsPeople {...selectScientists} />
        </Form>
      </Modal>
    </>
  );
};
export default ModifyUserAssigns;
