import { postRequest, requestUrl } from "../../utility/request";

export const checkSession = async () => {
  return await postRequest({
    url: requestUrl + "check",
    body: {},
  })
    .then((res) => res.json())
    .catch((err) => console.error(err));
};
