import { Button, Drawer, Form, Input, message } from "antd";
import TextArea from "antd/es/input/TextArea";
import QuestionTypeDDMenu from "../../component/QuestionTypeDDMenu";

const NewQuestionDrawer = ({ onClose, open, addNewProject }) => {
  const [form] = Form.useForm();
  const [messageApi, contextHolder] = message.useMessage();

  const cancelLoadingMessage = () => {
    messageApi.destroy("loading");
  };
  const errorMessage = (msg) => {
    cancelLoadingMessage();
    messageApi.open({
      type: "error",
      content: msg,
    });
  };
  const loadingMessage = () => {
    messageApi.open({
      type: "loading",
      content: "Your question is on the way...",
      key: "loading",
      duration: 0,
    });
  };
  const successMessage = () => {
    cancelLoadingMessage();
    messageApi.open({
      type: "success",
      content: "Your question is submitted!",
      duration: 3,
    });
  };
  const onFinish = async (values) => {
    loadingMessage();
    const option = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(values),
    };
    try {
      const result = await fetch("/api/v1/project", option);
      if (!result.ok) {
        cancelLoadingMessage();
        const error = await result.json();
        if (result.status === 400) {
          form.setFields([
            {
              name: "name",
              errors: [error.message],
            },
          ]);
          return;
        }
        if (result.status === 404) {
          errorMessage(error.message);
          return;
        }
      }
      const newProject = await result.json();
      successMessage();
      addNewProject(newProject);
      onReset();
      onClose();
    } catch (err) {
      errorMessage("Problem with the Server");
    }
  };
  const onReset = () => {
    form.resetFields();
  };
  return (
    <Drawer
      title="Add New Project"
      placement="right"
      onClose={onClose}
      open={open}
    >
      {contextHolder}
      <Form layout={"vertical"} form={form} onFinish={onFinish}>
        <Form.Item
          label="Question Name"
          name="name"
          rules={[{ required: true, message: "Please input Question Name!" }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          label="Project Type"
          name="fieldType"
          rules={[
            { required: true, message: "Please input Project Description!" },
          ]}
        >
          <QuestionTypeDDMenu />
        </Form.Item>
        <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
          <Button type="primary" htmlType="submit">
            Submit
          </Button>
        </Form.Item>
      </Form>
    </Drawer>
  );
};

export default NewQuestionDrawer;
