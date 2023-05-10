import React from "react";

import { Button, Form, Input, Radio } from "antd";
import { useState } from "react";
import { useLocation } from "react-router-dom";
const onFinish = async (values) => {
  const response = await fetch("/api/v1/form/", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(values),
  });
//   console.log("Success:", values);
console.log(response.status);
};
const onFinishFailed = (errorInfo) => {
  console.log("Failed:", errorInfo);
};
export const CreateForm = () => {
  console.log(useLocation().pathname.split("create-form/")[1]);
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
