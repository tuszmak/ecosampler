import {Button, Checkbox, Form, Input, message} from 'antd';
import { useNavigate} from "react-router-dom";
import {useLocation} from 'react-router-dom';
import useAuth from '../../hook/useAuth';
import {useRef} from 'react';

const LOGIN_API_URL = "/api/v1/login";
const HOME_URL = "/";
const ERROR_MSG_DURATION = 3;

const Login = () => {
    const {setAuth} = useAuth();
    const formRef = useRef(null);
    const navigate = useNavigate();
    const location = useLocation();
    const from = location.state?.from?.pathname || HOME_URL;

    const onFinish = async (values) => {
        const name = values.name;
        const password = values.password;

        const response = await fetch(
            LOGIN_API_URL,
            {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({name, password})
            }
        );

        const data = await response.json();
        const msg = await data.message;

        if (response.ok) {
            const role = data.role;
            setAuth({name, password, role});
            navigate(from, {replace: true});
        } else {
            formRef.current.resetFields();
            message.error(msg, ERROR_MSG_DURATION);
        }
    };

    return (
        <div className='loginForm'>
            <Form
                name="basic"
                ref={formRef}
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
                    label="Username"
                    name="name"
                    rules={[
                        {
                            required: true,
                            message: 'Please input your username!',
                        },
                    ]}
                >
                    <Input/>
                </Form.Item>
                <Form.Item
                    label="Password"
                    name="password"
                    rules={[
                        {
                            required: true,
                            message: 'Please input your password!',
                        },
                    ]}
                >
                    <Input.Password/>
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
            </Form></div>
    )
}

export default Login;