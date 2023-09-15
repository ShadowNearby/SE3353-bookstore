export async function handleRegister(
  username: string,
  password: string,
  email: string
) {
  return await fetch("http://localhost:8080/register", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    credentials: "include",
    body: JSON.stringify({
      username: username,
      password: password,
      email: email,
    }),
  })
    .then((res: Response) => res.json())
    .catch((err) => console.log(err));
}
