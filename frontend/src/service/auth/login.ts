import { postRequest } from "../../utility/request";

export async function handleLogin(username: string, password: string) {
  return await postRequest({
    url: `http://localhost:8080/login`,
    body: {
      username: username,
      password: password,
    },
  })
    .then((res: Response) => res.json())
    .catch((err) => console.log(err));
}
