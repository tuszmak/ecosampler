import { Button, Drawer, Form, Input, message } from "antd";
import TextArea from "antd/es/input/TextArea";

const NewProjectDrawer = ({ onClose, open, addNewProject }) => {
  const [form] = Form.useForm();

  const onFinish = async (values) => {
    console.log(form);
    const option = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(values),
    };

    const result = await fetch("/api/v1/project", option);
    if (result.status === 400) {
      const error = await result.json();
      form.setFields([
        {
          name: "name",
          errors: [error.message],
        },
      ]);
      return;
    }
    if (!result.ok) {
      const ERROR_MSG_DURATION = 3;
      message.error("Problem with the Server", ERROR_MSG_DURATION);
      return;
    }
    const newProject = await result.json();
    addNewProject(newProject);
    onReset();
    onClose();
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
      <Form layout={"vertical"} form={form} onFinish={onFinish}>
        <Form.Item
          label="Project Name"
          name="name"
          rules={[{ required: true, message: "Please input Project Name!" }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          label="Project Description"
          name="description"
          rules={[
            { required: true, message: "Please input Project Description!" },
          ]}
        >
          <TextArea rows={4} />
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

export default NewProjectDrawer;
