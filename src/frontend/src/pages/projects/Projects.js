import useDownFetch from '../../hook/useDownFetch';
import ProjectList from './ProjectList';

const Projects = () => {
  const { data, error, isPending } = useDownFetch("/api/v1/project");
  if (isPending) return <h1>Loading</h1>;
  return <ProjectList projects={data} />;
};

export default Projects;
