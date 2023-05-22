const upFetch = async (url, option) => {
  const { token } = JSON.parse(localStorage.getItem("user"));
  if (token)
    option.headers = {
      ...option.headers,
      Authorization: `Bearer ${token}`,
    };

  return fetch(url, option);
};

export default upFetch;
