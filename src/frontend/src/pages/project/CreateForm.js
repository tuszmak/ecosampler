import React, { useState } from "react";

import { Button, Form, Input, Radio, message } from "antd";
import { useLocation, useNavigate, useParams } from "react-router-dom";

const path = "/api/v1/project/addForm";
export const CreateForm = () => {
  const [projectID, setProjectID] = useState(useParams());
  const navigate = useNavigate();
  const onFinish = async (values) => {
    try {
      await fetch(path + projectID.id, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(values),
      });

      navigate(`/project/${projectID.id}`);
    } catch (error) {
      message.error("This is a duplicate Form name or something is wrong with server.")
    }
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
