import { useParams } from "react-router-dom";
import useFetch from "../../hook/useFetch";
import { Form, Button } from "antd";
import { QuestionItem } from "./QuestionItem";

const path = "api/v1/question/getQuestions/";

const Survey = () => {

  const params = useParams();
  const { data, error, isPending } = useFetch(path + params.id);

  const onFinish = (values) => {
    console.log('Success:', values);
  };

  if (isPending) return <h1>Loading</h1>;
  if (error) return <h1>{error}</h1>
  return (
    <>
      <h1>Survey</h1>
      <Form
        name="basic"
        labelCol={{ span: 8 }}
        wrapperCol={{ span: 16 }}
        style={{ maxWidth: 600 }}
        initialValues={{ remember: true }}
        onFinish={onFinish}
        autoComplete="off"
      >
        {data.map(question => {
          return (
            <Form.Item
              key={question.id}
              label={question.description}
              name={question.id}
            >
              {<QuestionItem
                field={question.fieldStyle} />}
            </Form.Item>
          )
        })}
        <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
          <Button type="primary" htmlType="submit">
            Submit
          </Button>
        </Form.Item>
      </Form>
    </>
  );
};

export default Survey;

