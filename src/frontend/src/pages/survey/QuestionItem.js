import { Form, Button, Input } from "antd";
import TextArea from "antd/es/input/TextArea";


export const QuestionItem = ({field}) => {
   
    switch (field) {
      case "SHORT_TEXT": return (
        <Input type="text" />
      );
      case "LONG_TEXT": return (
        <TextArea
          placeholder="Autosize height with minimum and maximum number of lines"
          autoSize={{
            minRows: 3,
            maxRows: 6,
          }}
        />
      );
      case "CHECK_BOX": return (
        <Input type="checkbox" />
      );
      case "NUMBER": return (
        <Input type="number" />
      );
      default: return (
        <Input type="text" />
      );
    }
  };
