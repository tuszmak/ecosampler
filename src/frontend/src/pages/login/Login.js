import { Button, Checkbox, Form, Input, message } from "antd";
import { useLocation, useNavigate } from "react-router-dom";
import useAuth from "../../hook/useAuth";

const LOGIN_API_URL = "/api/v1/login";
const HOME_URL = "/";
const ERROR_MSG_DURATION = 3;

const Login = () => {
  const { setAuth } = useAuth();
  const navigate = useNavigate();
  const location = useLocation();
  const from = location.state?.from?.pathname || HOME_URL;
  const [login] = Form.useForm();

  const reset = () => {
    login.resetFields();
  };
  const onFinish = async (values) => {
    const { email, password } = values;

    try {
      const response = await fetch(LOGIN_API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          email,
          password,
        }),
      });

      const data = await response.json();
      if (response.ok) {
        setAuth({ ...data });
        localStorage.setItem("user", JSON.stringify(data));
        navigate(from, { replace: true });
      } else {
        message.error(data.message, ERROR_MSG_DURATION);
      }
    } catch (err) {
      message.error("Problem with the server", ERROR_MSG_DURATION);
    } finally {
      reset();
    }
  };

  return (
    <div className="loginForm">
      <Form
        form={login}
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
        onFinish={onFinish}
        autoComplete="off"
      >
        <Form.Item
          label="Email"
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
          label="Password"
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
          name="remember"
          valuePropName="checked"
          wrapperCol={{
            offset: 8,
            span: 16,
          }}
        >
          <Checkbox>Remember me</Checkbox>
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
};

export default Login;
