import { Button, Form, Input, Select, message } from "antd";
import upFetch from "../../api/upFetch";

const Register = () => {
  const [form] = Form.useForm();
  const { Option } = Select;

  const onSubmitFailed = (errorInfo) => {
    console.log("Failed:", errorInfo);
  };
  
  const onSubmit = async (values) => {
    const response = await upFetch("/api/v1/user", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(values),
    });
    const data = await response.json();
    const msg = data.message;
    const ERROR_MSG_DURATION = 3;
    if (response.ok) {
      message.success("Registration successful", ERROR_MSG_DURATION);
      form.resetFields();
    }
    else message.error(msg, ERROR_MSG_DURATION);
  };

  return (
    <div className="loginForm">
      <Form
        form={form}
        name="basic"
        labelCol={{
          span: 8,
        }}
        wrapperCol={{
          span: 16,
        }}
        style={{
          maxWidth: 600,
        }}
        initialValues={{
          remember: true,
        }}
        onFinish={onSubmit}
        onFinishFailed={onSubmitFailed}
        autoComplete="off"
      >
        <Form.Item
          label="name"
          name="name"
          rules={[
            {
              required: true,
              message: "Please input your username!",
            },
          ]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          label="email"
          name="email"
          rules={[
            {
              required: true,
              message: "Please input your email!",
            },
          ]}
        >
          <Input type="email" />
        </Form.Item>
        <Form.Item
          label="password"
          name="password"
          rules={[
            {
              required: true,
              message: "Please input your password!",
            },
          ]}
        >
          <Input.Password />
        </Form.Item>
        <Form.Item
          label="Role"
          name="role"
          rules={[
            {
              required: true,
              message: "Please select a role!",
            },
          ]}
        >
          <Select placeholder="Select a role">
            <Option value="SCIENTIST">Scientist</Option>
            <Option value="PROJECT_LEADER">Project Leader</Option>
            <Option value="DIRECTOR">Director</Option>
          </Select>
        </Form.Item>
        <Form.Item
          wrapperCol={{
            offset: 8,
            span: 16,
          }}
        >
          <Button type="primary" htmlType="submit">
            Submit
          </Button>
        </Form.Item>
      </Form>
    </div>
  );
}

export default Register;
