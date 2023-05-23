import { useParams } from "react-router-dom";
import useFetch from "../../hook/useFetch";
import { Form, Button } from "antd";
import { QuestionItem } from "./QuestionItem";
import useAuth from "../../hook/useAuth";


const path = "api/v1/question/getQuestions/";

const Survey = () => {

  const { formID } = useParams();
  const { auth } = useAuth();
  const { data: questions, error, isPending } = useFetch(path + formID);

  const onFinish = (values) => {
    const { id } = auth;
    const valuesArray = Object.entries(values).map(([key, value]) => ({ [key]: value }));
    console.log('Success:', { valuesArray, id, formID });
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
        {questions.map(question => {
          return (
            <QuestionItem
              key={question.id}
              question={question}
            />
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

