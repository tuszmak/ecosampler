import { Form, Modal, message } from "antd";
import AssignsPeople from "./AssignsPeople";
import { useEffect, useState } from "react";
import useDownFetch from "../../hook/useDownFetch";
import upFetch from "../../api/upFetch";
import useAuth from "../../hook/useAuth";

const ModifyUserAssigns = ({
  projectId,
  isModalOpen,
  handleCancel,
  handleOk,
}) => {
  const { auth } = useAuth();
  const [form] = Form.useForm();
  const { data: projectUsers, isPending } = useDownFetch(
    `api/v1/project/users/${projectId}`
  );
  const disableWhenUserNotDirector = () => {
    return auth?.role !== "DIRECTOR";
  };
  const [selectProjectLeader, setSelectProjectLeader] = useState({
    url: "api/v1/user/by-role?role=PROJECT_LEADER",
    name: "projectLeaderIDs",
    label: "Select Project Leader",
    isDisabled: disableWhenUserNotDirector(),
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
    if (!response.ok) {
      message.error("Can't connect to server");
      throw Error("Can't connect to server");
    }
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
        onCancel={handleCancel}
        onOk={async () => {
          try {
            await form.validateFields();
            const values = form.getFieldsValue();
            const { addUserIDs, removeUserIDs } =
              generateUserChangesPayload(values);
            await sendUserChanges(addUserIDs, removeUserIDs);
            form.resetFields();
            handleOk();
          } catch (error) {
            if (error?.errorFields)
              return console.log("Validate Failed:", error);
          }
        }}
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
