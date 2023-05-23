import { Form, Select } from "antd";
import { useEffect, useState } from "react";
import useDownFetch from "../../hook/useDownFetch";

const AssignsPeople = ({ url, label, name, defaultValue = [] }) => {
  const [options, setOptions] = useState([]);
  const { data, isPending } = useDownFetch(url);
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
