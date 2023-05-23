import { Form, Input, Checkbox } from "antd";
import TextArea from "antd/es/input/TextArea";


export const QuestionItem = ({question: {id, description, fieldStyle }}) => {
  switch (fieldStyle) {
    case "SHORT_TEXT": return (
      <Form.Item
        initialValue={""}
        label={description}
        name={id}
      >
        <Input type="text" />
      </Form.Item>
    );
    case "LONG_TEXT": return (
      <Form.Item
        initialValue={""}
        label={description}
        name={id}
      >
        <TextArea
          placeholder="Provide a thorough explanation or analysis..."
          autoSize={{
            minRows: 3,
            maxRows: 6,
          }}
        />
      </Form.Item>


    );
    case "CHECK_BOX": return (
      <Form.Item
        initialValue={false}
        name={id}
        label={description}
        valuePropName="checked"
        wrapperCol={{
          offset: 8,
          span: 16,
        }}
      >
        <Checkbox></Checkbox>
      </Form.Item>
    );
    case "NUMBER": return (
      <Form.Item
        initialValue={0}
        label={description}
        name={id}
      >
        <Input type="number" />
      </Form.Item>
    );
    default: return (
      <Form.Item
        initialValue={""}
        label={description}
        name={id}
      >
        <Input type="text" />
      </Form.Item>
    );
  }

};
