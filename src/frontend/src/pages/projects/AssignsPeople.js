import { Form, Select } from "antd";
import useFetch from "../../hook/useFetch";
import { useEffect, useState } from "react";

const AssignsPeople = ({ url, label, name, defaultValue = [] }) => {
  const [options, setOptions] = useState([]);
  const { data, isPending } = useFetch(url);
  useEffect(() => {
    if (data) {
      setOptions(
        data.map((user) => ({
          label: user.name,
          value: user.id,
        }))
      );
    }
  }, [data]);

  if (isPending) return <p>Loading...</p>;

  return (
    <Form.Item
      label={label}
      name={name}
      rules={[{ required: true, message: "Please Select!" }]}
      initialValue={defaultValue}
    >
      <Select
        mode="multiple"
        allowClear
        style={{
          width: "100%",
        }}
        placeholder="Please select"
        options={options}
      />
    </Form.Item>
  );
};

export default AssignsPeople;
