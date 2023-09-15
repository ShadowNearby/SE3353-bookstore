import { postRequest, requestUrl } from "../../utility/request";

export const checkAdmin = async () => {
  return await postRequest({
    url: requestUrl + "admin/check",
    body: {},
  })
    .then((res) => res.json())
    .catch((err) => console.error(err));
};
