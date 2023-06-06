import { useLocation, useParams, useNavigate } from "react-router-dom";
import { Button, Form, message } from "antd";
import { QuestionItem } from "./QuestionItem";
import useAuth from "../../hook/useAuth";
import useDownFetch from "../../hook/useDownFetch";
import upFetch from "../../api/upFetch";

const PATH = "/api/v1/question/by-form-id/";
const PATH_FOR_SAVE_SAMPLE_DATA = "/api/v1/sampledata"
const ERROR_MSG_DURATION = 3;

const SampleData = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const [messageApi, contextHolder] = message.useMessage();

  const cancelLoadingMessage = () => {
    messageApi.destroy("loading");
  };
  const errorMessage = (msg) => {
    cancelLoadingMessage();
    messageApi.open({
      type: "error",
      content: msg,
    });
  };
  const loadingMessage = () => {
    messageApi.open({
      type: "loading",
      content: "Your sample data is on the way...",
      key: "loading",
      duration: 0,
    });
  };
  const successMessage = () => {
    cancelLoadingMessage();
    messageApi.open({
      type: "success",
      content: "Your sample data is saved!",
      duration: 3,
    });
  };
  const { formID } = useParams();
  const { auth: { id: userID } } = useAuth();
  const { data: questions, error, isPending } = useDownFetch(PATH + formID);

  const onFinish = async (values) => {
    loadingMessage();

    const newAnswers = Object.entries(values).map(([key, value]) => ({ questionID: key, answer: value }));

    const option = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ newAnswers, userID, formID }),
    };
    try {
      const result = await upFetch(PATH_FOR_SAVE_SAMPLE_DATA, option)
      const data = await result.json();

      const searchParams = new URLSearchParams(location.search);
      const projectId = searchParams.get("from");
      navigate(`/project/${projectId}`);

      if (!result.ok) message.error(data.message, ERROR_MSG_DURATION);
      else successMessage();

    } catch (err) {
      errorMessage("Problem with the Server");
    }
  };

  if (isPending) return <h1>Loading</h1>;
  if (error) return <h1>{error}</h1>
  return (
    <>
      <h1>Survey</h1>
      {contextHolder}
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

export default SampleData;

