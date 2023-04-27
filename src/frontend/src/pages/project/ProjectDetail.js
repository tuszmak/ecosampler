import { useParams } from "react-router-dom";

const ProjectDetail = () => {
  let { id } = useParams();
  return (<h1>Project Page {id}</h1>);
}

export default ProjectDetail;
