import useAuth from "../../hook/useAuth";
import useDownFetch from "../../hook/useDownFetch";
import ProjectList from "./ProjectList";

const Projects = () => {
  const { auth } = useAuth();
  const path =
    auth?.role === "DIRECTOR"
      ? "/api/v1/project"
      : `/api/v1/project/by-user/${auth.id}`;
  const { data, error, isPending } = useDownFetch(path);
  if (isPending) return <h1>Loading</h1>;
  return <ProjectList projects={data} />;
};

export default Projects;
