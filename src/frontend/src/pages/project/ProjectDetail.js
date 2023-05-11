import { useParams } from "react-router-dom";
import { FormList } from "./FormList";

const ProjectDetail = () => {
  let { id } = useParams();
  return (
    <>
      <h1>Project Page {id}</h1>
      <FormList />
    </>
  );
};

export default ProjectDetail;
