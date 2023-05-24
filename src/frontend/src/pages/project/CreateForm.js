import React, {useState} from "react";

import {Button, Form, Input, message, Select, Space} from "antd";
import {useNavigate, useParams} from "react-router-dom";
import {MinusCircleOutlined, PlusOutlined} from "@ant-design/icons";
import {FIELDSTYLES} from "../../constants/const";
import upFetch from "../../api/upFetch";

const path = "/api/v1/project/addForm/";
export const CreateForm = () => {
  const {id} = useParams();
  const [fieldType, setFieldType] = useState("SHORT-TEXT");
  const navigate = useNavigate();
  const fieldStyles = [...FIELDSTYLES];
  const handleChange = (value) => {
    console.log(`selected ${value}`);
    setFieldType(value);
  };
  const onFinish = async (values) => {
    console.log(values);
    try {
      await upFetch(path + id, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(values),
      });

      navigate(`/project/${id}`);
    } catch (error) {
      message.error(
        "This is a duplicate Form name or something is wrong with server."
      );
    }
  };

  return (
    <Form
      name="dynamic_form_nest_item"
      onFinish={onFinish}
      style={{
        maxWidth: 600,
      }}
      autoComplete="off"
    >
      <Form.Item label="Form name" name="name" required="true">
        <Input />
      </Form.Item>
      <Form.List name="questions">
        {(fields, { add, remove }) => (
          <>
            {fields.map(({ key, name, ...restField }) => (
              <Space
                key={key}
                style={{
                  display: "flex",
                  marginBottom: 8,
                }}
                align="baseline"
              >
                <Form.Item
                  {...restField}
                  name={[name, "description"]}
                  rules={[
                    {
                      required: true,
                      message: "Missing question",
                    },
                  ]}
                >
                  <Input placeholder="Question" />
                </Form.Item>
                <Form.Item
                  {...restField}
                  name={[name, "fieldStyle"]}
                  rules={[
                    {
                      required: true,
                      message: "Missing field type",
                    },
                  ]}
                >
                  <Select
                    value={fieldType}
                    style={{
                      width: 120,
                    }}
                    onChange={handleChange}
                    options={fieldStyles.map((value) => ({
                      value: value,
                      label: value,
                    }))}
                  />
                </Form.Item>
                <MinusCircleOutlined onClick={() => remove(name)} />
              </Space>
            ))}
            <Form.Item>
              <Button
                type="dashed"
                onClick={() => add()}
                block
                icon={<PlusOutlined />}
              >
                Add field
              </Button>
            </Form.Item>
          </>
        )}
      </Form.List>
      <Form.Item>
        <Button type="primary" htmlType="submit">
          Submit
        </Button>
      </Form.Item>
    </Form>
  );
};
