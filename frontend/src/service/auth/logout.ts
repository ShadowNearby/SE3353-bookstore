import { postRequest, requestUrl } from "../../utility/request";
import { IMessage } from "../../interface";

export async function logout(): Promise<IMessage> {
  localStorage.clear();
  return await postRequest({ url: requestUrl + "logout", body: {} })
    .then((res) => res.json())
    .catch((err) => console.error(err));
}
