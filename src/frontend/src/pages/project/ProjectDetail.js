import { useParams } from "react-router-dom";
import { FormList } from "./FormList";

const ProjectDetail = () => {
  let { id } = useParams();
  return (
    <>
      <h1>Available Forms</h1>
      <FormList />
    </>
  );
};

export default ProjectDetail;
