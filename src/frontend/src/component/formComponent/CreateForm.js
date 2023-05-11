import React, { useState } from "react";

import { Button, Form, Input, Radio } from "antd";
import { useLocation, useNavigate, useParams } from "react-router-dom";

export const CreateForm = () => {
  const [projectID, setProjectID] = useState(useParams());
  const navigate = useNavigate();
  const onFinish = async (values) => {
    const response = await fetch(`/api/v1/project/addForm/${projectID.id}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(values),
    });
    if (response.status == 200) {
      navigate(`/project/${projectID.id}`);
    }
  };
  const onFinishFailed = (errorInfo) => {
    console.log("Failed:", errorInfo);
  };
  return (
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
      onFinish={onFinish}
      onFinishFailed={onFinishFailed}
      autoComplete="off"
    >
      <Form.Item label="Form name" name="name" required="true">
        <Input />
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
  );
};
