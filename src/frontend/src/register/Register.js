import {Button, Form, Input} from 'antd';

const onSubmitFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
};

const onSubmit = (values) => {
    fetch("/api/v1/user", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(values)
    })
}

const Register = () => (
    <Form
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
                    message: 'Please input your username!',
                },
            ]}
        >
            <Input/>
        </Form.Item>

        <Form.Item
            label="email"
            name="email"
            rules={[
                {
                    required: true,
                    message: 'Please input your email!',
                },
            ]}
        >
            <Input type = "email"/>
        </Form.Item>

        <Form.Item
            label="password"
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
)

export default Register;
