/**
 * Fetch implementation automatically add Authorization field to header
 * if you logged in.
 * Use like fetch.
 *
 * @param {String} url
 * @param {Object} option
 * @returns Proems
 */
const upFetch = (url, option) => {
  const { token } = JSON.parse(localStorage.getItem("user"));
  if (token)
    option.headers = {
      ...option.headers,
      Authorization: `Bearer ${token}`,
    };

  return fetch(url, option);
};

export default upFetch;
