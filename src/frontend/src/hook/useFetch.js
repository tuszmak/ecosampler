import { useState, useEffect } from "react";
/**
 * Custom hook for Fetch data with error handling, and loading state;
 *
 * usage const {data, isPending, error} = useFetch('url')
 *
 * data: the fetched data for rename
 *    use destruction pattern {data: locations, ...}
 * isPending: true until is data or error
 * error: if response not ok or other fetch error
 *
 * @param {String} url
 * @returns Object data isPending error
 */
const useFetch = (url) => {
  const [data, setData] = useState(null);
  const [isPending, setIsPending] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const abort = new AbortController();
    fetch((url[0] !== "/" ? "/" : "") + url, { signal: abort.signal })
      .then((res) => {
        if (!res.ok) throw Error("There must be a problem");
        return res.json();
      })
      .then((data) => {
        setIsPending(false);
        setError(null);
        setData(data);
      })
      .catch((err) => {
        if (err.name === "AbortError") return;
        setIsPending(false);
        setError(err.message);
      });

    return () => abort.abort();
  }, [url]);

  return { data, isPending, error };
};

export default useFetch;
